package shop.reports;

import shop.Shop;
import shop.documents.ServiceMethodDocument;
import shop.inforamation.ServiceMethodInformation;
import shop.reference.ServiceMethodReference;


//super class dlya rabotu s ot4etami i spravo4noy infoy
public class Reports {

    protected 	Shop 	shop;

    protected ServiceMethodReference serviceMethodReference;
    protected ServiceMethodDocument serviceMethodDocument;
    protected ServiceMethodInformation serviceMethodInformation;

    public void setShop(Shop shop) {
        this.shop = shop;

        serviceMethodReference   = new ServiceMethodReference(shop);
        serviceMethodDocument    = new ServiceMethodDocument(shop);
        serviceMethodInformation = new ServiceMethodInformation(shop);

    }
}
