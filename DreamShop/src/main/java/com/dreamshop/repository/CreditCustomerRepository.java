package com.dreamshop.repository;

import com.dreamshop.controller.ShopController;
import com.dreamshop.entity.catalogs.Customer;

import java.sql.Connection;

public class CreditCustomerRepository {

    public CreditCustomerRepository() {
    }

    public int getCreditCustomer(Customer customer){

        ShopController shopController = ShopController.getInstance();

        Connection connection = shopController.getConnection();

        return 0;
    }

}
