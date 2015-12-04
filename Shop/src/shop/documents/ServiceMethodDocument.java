package shop.documents;

import shop.Shop;
import shop.balances.BalancesAutoParts;
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

        if (document.getDateDoc() == null) {
            document.setDateDoc(new Date());
        }

        if (document.getDate() == 0){
            document.setDate(shop.getCurrentDate());
        }

        shop.getDb().addNewRecord(document);

        if (document instanceof Shopping) {
            addBalanceAutoPart(document);
        } else if (document instanceof Sale) {
            lessBalanceAutoPart(document);
        }

    };

    public void addBalanceAutoPart(Document document) {

        if (document instanceof Shopping) {

            Shopping docum = (Shopping) document;

            BalancesAutoParts balanceAS = getRecordByAutoParts(docum.getAutoParts());

            if (balanceAS == null) {
                balanceAS = new BalancesAutoParts(shop);
                balanceAS.setAutoParts(docum.getAutoParts());
                balanceAS.setIdAutoParts(docum.getIdAutoParts());
                balanceAS.setQty(docum.getQty());
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
                balanceAS.setIdAutoParts(docum.getIdAutoParts());
                balanceAS.setQty((-1)*docum.getQty());
                balanceAS.save();
            } else {
                balanceAS.setQty(balanceAS.getQty() - docum.getQty());
            }

        }

    }

    private BalancesAutoParts getRecordByAutoParts(AutoParts autoParts) {

        for (BalancesAutoParts element : (List<BalancesAutoParts>)shop.getDb().getDataFromTable(BalancesAutoParts.class)) {

            if (element.getAutoParts().equals(autoParts)) {
                return element;
            }

        }
        return null;

    }

    private BalancesAutoParts getRecordByAutoParts(int id) {

        List<BalancesAutoParts> balancesAutoPartses = (List<BalancesAutoParts>) shop.getDb().getDataFromTable(BalancesAutoParts.class);

        for (BalancesAutoParts element : balancesAutoPartses) {

            if (element.getIdAutoParts() == id) {
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

    public Sale newSale(Client client, AutoParts autoParts, int qty, float cena){

        Sale docSale = new Sale(shop);
        docSale.setClient(client);
        docSale.setIdClient(client.getId());
        docSale.setAutoParts(autoParts);
        docSale.setIdAutoParts(autoParts.getId());
        docSale.setQty((byte)qty);
        docSale.setCena(cena);
        docSale.setDate(shop.getCurrentDate());
        docSale.save();

        return docSale;
    }

}
