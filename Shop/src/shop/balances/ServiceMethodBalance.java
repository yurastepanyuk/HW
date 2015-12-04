package shop.balances;

import shop.Shop;

public class ServiceMethodBalance {

    private Shop shop;

    public ServiceMethodBalance() {
    }

    public ServiceMethodBalance(Shop shop) {
        this.shop = shop;
    }

    public void save(Balances balance){
        shop.getDb().addNewRecord(balance);
    }

}
