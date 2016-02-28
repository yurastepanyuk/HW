package com.dreamshop.repository;

import com.dreamshop.entity.catalogs.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository {

    public CustomerRepository() {
    }

    public Customer getCustomer(long id){
        Customer customer= new Customer();
        customer.setId(1);
        customer.setName("FOP Stepanyuk Y.V.");
        customer.setEmail("yu.stepanyuk.ra@gmail.com");
        return customer;
    };

    public List<Customer> getCustomers(){

        return new ArrayList<Customer>();
    }

}
