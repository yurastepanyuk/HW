package com.dreamshop.entity.catalogs;

public class Item extends Catalog{

    private String sku;
    private Integer price;

    public Item() {
    }

    public Item(int id, String name, String sku, Integer price) {
        super.id    = id;
        super.name  = name;
        this.sku    = sku;
        this.price  = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
