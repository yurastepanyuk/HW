package com.dreamshop.entity.accumulations;

import com.dreamshop.entity.catalogs.Item;

public class BalanceItem {

    private Item    item;
    private Integer balance;

    public BalanceItem() {
    }

    public BalanceItem(Item item, Integer balance) {
        this.item = item;
        this.balance = balance;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
