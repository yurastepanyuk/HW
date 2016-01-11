package shop.documents;

import shop.Shop;
import shop.balances.BalancesAutoParts;
import shop.database.WorkWithLists;
import shop.enums.CategoryPrice;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.util.Date;
import java.util.List;

public class ServiceMethodDocument {

    private Shop shop;

    public ServiceMethodDocument(Shop shop) {
        this.shop = shop;
    }

    public <T>T getObjectById(int id, Class<T> cls)  {

        if (cls.getSimpleName().equals("Sale")) {
            List<Sale> sales = (List<Sale>) shop.getDb().getDataFromTable(Sale.class);

            for (Sale element : sales) {
                if (element.getId() == id) {
                    return (T)element;
                }
            }
        } else if (cls.getSimpleName().equals("Shopping")) {

            List<Shopping> shoppings = (List<Shopping>) shop.getDb().getDataFromTable(Shopping.class);

		    for (Shopping element : shoppings) {
			    if (element.getId() == id) {
				    return (T)element;
			    }
		    }

        }

        return null;
    }

    public void save(Document document){

        int newId = shop.getDb().getNewId(document);
        document.setId(newId);

        //for MySQL
        if (document.getDateDoc() == null) {
            document.setDateDoc(new Date());
        }
        //for ListDB
        if (document.getDate() == 0){
            document.setDate(shop.getCurrentDate());
        }

        if (document.getId() == 0 || shop.getDb() instanceof WorkWithLists) {
            shop.getDb().addNewRecord_(document);

            if (shop.getDb() instanceof WorkWithLists) {

                if (document instanceof Shopping) {
                    addBalanceAutoPart(document);
                } else if (document instanceof Sale) {
                    lessBalanceAutoPart(document);
                }

            }

        }

    };

    public void addBalanceAutoPart(Document document) {

        if (document instanceof Shopping) {

            Shopping docum = (Shopping) document;

            BalancesAutoParts balanceAS = getRecordByAutoParts(docum.getAutoParts());

            if (balanceAS == null) {
                balanceAS = new BalancesAutoParts(shop, 0, docum.getAutoParts(), docum.getQty());
                balanceAS.save();
            } else {
                balanceAS.setQty(balanceAS.getQty() +  docum.getQty());
            }

        }

    }

    public void lessBalanceAutoPart(Document document) {

        if (document instanceof Sale){

            Sale docum = (Sale) document;

            BalancesAutoParts balanceAS = getRecordByAutoParts(docum.getAutoParts());

            if (balanceAS == null) {
                balanceAS = new BalancesAutoParts(shop);
                balanceAS.setAutoParts(docum.getAutoParts());
                balanceAS.setQty((-1)*docum.getQty());
                balanceAS.save();
            } else {
                balanceAS.setQty(balanceAS.getQty() - docum.getQty());
            }

        }

    }

    private BalancesAutoParts getRecordByAutoParts(AutoParts autoParts) {

        List<BalancesAutoParts> balancesAutoPartsList = (List<BalancesAutoParts>)shop.getDb().getDataFromTable(BalancesAutoParts.class);

        for (BalancesAutoParts element : balancesAutoPartsList) {

            if (element.getAutoParts().equals(autoParts)) {
                return element;
            }

        }
        return null;

    }

    private BalancesAutoParts getRecordByAutoParts(int id) {

        List<BalancesAutoParts> balancesAutoPartses = (List<BalancesAutoParts>) shop.getDb().getDataFromTable(BalancesAutoParts.class);

        for (BalancesAutoParts element : balancesAutoPartses) {

            if (element.getAutoParts().getId() == id) {
                return element;
            }

        }
        return null;

    }

    public Shopping newShopping(Client client, AutoParts autoParts, int qty, float cena){
        Shopping documShop = new Shopping(shop);
        documShop.setClient(client);
        documShop.setIdClient(documShop.getClient().getId());
        documShop.setAutoParts(autoParts);
        documShop.setIdAutoParts(autoParts.getId());
        documShop.setQty(qty);
        documShop.setCena(cena);
        documShop.setDate(shop.getCurrentDate());
        documShop.save();

        return documShop;
    }

    public Sale newSale(Client client, AutoParts autoParts, int qty, float cena, boolean save){

        Sale docSale = new Sale(shop);
        docSale.setClient(client);
        docSale.setIdClient(client.getId());
        docSale.setAutoParts(autoParts);
        docSale.setIdAutoParts(autoParts.getId());
        docSale.setQty((byte)qty);
        docSale.setCena(cena);
        docSale.setDate(shop.getCurrentDate());

        if (save) {
            docSale.save();
        }

        return docSale;
    }

    public double getDiscountSummaForSummaSale(float summaSale){

        float discountSumma = 0f;

        float percent = getDiscountPercentForSummaSale(summaSale);

        discountSumma = summaSale * percent/100;

        return discountSumma;
    }

    public float getDiscountPercentForSummaSale(float summaSale){

        float percent = 0;

        if (summaSale >= 1000f) {
            percent = 10f;
        } else if (summaSale >= 500f){
            percent = 5f;
        } else {
            percent = 0.0f;
        }
        return percent;
    }

}
