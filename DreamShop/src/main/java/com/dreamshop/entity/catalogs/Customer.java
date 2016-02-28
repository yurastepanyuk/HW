package com.dreamshop.entity.catalogs;

public class Customer extends Catalog {

    private String email;

    public Customer() {
    }

    public Customer(int id, String name, String email){
        super.id    = id;
        super.name  = name;
        this.email  = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
