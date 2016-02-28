package com.dreamshop.entity.accumulations;

import com.dreamshop.entity.catalogs.Customer;

public class CreditCutomer {

    private Customer customer;
    private Integer  balance;

    public CreditCutomer() {
    }

    public CreditCutomer(Customer customer, Integer balance) {
        this.customer = customer;
        this.balance = balance;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
