package shop.inforamation;

import shop.Shop;
import shop.enums.CategoryPrice;
import shop.reference.AutoParts;

import java.util.List;

public class ServiceMethodInformation {

    private Shop shop;

    public ServiceMethodInformation() {
    }

    public ServiceMethodInformation(Shop shop) {
        this.shop = shop;
    }

    public void save(Information information){

        shop.getDb().addNewRecord(information);

    }

    public void updatePrice(AutoParts autoParts, float prise, CategoryPrice kategoriya){

        Prices prc = getRecordByAutoParts(autoParts, kategoriya);

        if (prc == null) {
            addPrice(autoParts, prise, kategoriya);
        } else {
            prc.setPrise(prise);
        }

    }

    public void addPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya ) {

        Prices newPrice = new Prices(shop);

        newPrice.setAutoParts(autoParts);
        newPrice.setIdAutoParts(autoParts.getId());
        newPrice.setKategoriya(kategoriya);
        newPrice.setPrise(prise);
        save(newPrice);
    }

    public Prices getRecordByAutoParts(AutoParts autoParts, CategoryPrice kategoriya) {
        Prices [] prices = getPricesByGoods(autoParts);

        for (Prices price : prices){
            if (price == null) {
                continue;
            }
            if (price.getKategoriya() == kategoriya) {
                return price;
            }
        }

        return null;
    }

    public float getPriceByGoods(AutoParts autoParts, CategoryPrice kategoriya){

        Prices [] prices = getPricesByGoods(autoParts);

        for (Prices price : prices){
            if (price == null) {
                break;
            }
            if (price.getKategoriya() == kategoriya) {
                return price.getPrise();
            }
        }

        return 0f;
    }

    public Prices[] getPricesByGoods(AutoParts autoParts){

        List<Prices> prices = (List<Prices>) shop.getDb().getDataFromTable(Prices.class);

        Prices [] result = new Prices[prices.size()];

        if (prices.size() == 0) {
            return result;
        }

        int idx = 0;
        for (Prices element : prices) {

            if (element == null) {
                continue;
            }

            if (element.getAutoParts().equals(autoParts)) {
                result[idx] = element;
                idx++;
            }

        }

        return result;
    }



}
