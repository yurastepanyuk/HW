package com.dreamshop.repository;

import com.dreamshop.entity.catalogs.Customer;
import com.dreamshop.entity.catalogs.ShipAddres;

import java.util.ArrayList;
import java.util.List;

public class ShipAddresRepository {

    public ShipAddresRepository() {
    }

    public ShipAddres getAddres(long id){

        ShipAddres shipAddres = new ShipAddres();
        shipAddres.setId(1);
        shipAddres.setName("08606");
        shipAddres.setStreet("Big Vasilkovskaya");
        shipAddres.setFlat("165");
        shipAddres.setHouse("26");

        return shipAddres;
    }

    public List<ShipAddres> getAddreses(Customer customer){
        return new ArrayList<ShipAddres>();
    }

}
