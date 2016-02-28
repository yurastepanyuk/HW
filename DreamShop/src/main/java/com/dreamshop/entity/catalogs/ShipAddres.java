package com.dreamshop.entity.catalogs;

public class ShipAddres extends Catalog{

    //private String postal_code; it's field name of the parent class
    private String street;
    private String house;
    private String flat;

    public ShipAddres() {
    }

    public String getStreet() {
        return street;
    }

    public ShipAddres(int id, String postal_code, String street, String house, String flat) {
        super.id    = id;
        super.name  = postal_code;
        this.street = street;
        this.house  = house;
        this.flat   = flat;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
    }

    public String getHouse() {
        return house;
    }

    public void setHouse(String house) {
        this.house = house;
    }
}

