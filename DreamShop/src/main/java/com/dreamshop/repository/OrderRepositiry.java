package com.dreamshop.repository;

import com.dreamshop.controller.ShopController;
import com.dreamshop.entity.accumulations.BalanceItem;
import com.dreamshop.entity.catalogs.Item;
import com.dreamshop.entity.documents.Order;
import com.dreamshop.entity.documents.OrderLine;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

public class OrderRepositiry {

    public OrderRepositiry() {
    }

    public Order getOrder(long idOrder){
        return null;
    }

    public boolean saveOrder(Order order){


        return false;
    }

    public boolean chekOrder(Order order){

        boolean result = true;

        Integer creditCustomer = new CreditCustomerRepository().getCreditCustomer(order.getCustomer());

        if (creditCustomer < order.getTotal()) {
            result = false;
        }

        BalanceItemRepository balanceItemRepository = new BalanceItemRepository();
        for (OrderLine curLine : order.getOrderLineList() ) {
            Item curItem = curLine.getItem();
            Integer balanceItem = balanceItemRepository.getBalanceItem(curItem);
            if (balanceItem < curLine.getQty()) {
                result = false;
            }
        }

        return result;
    }

    public List<OrderLine> getOrderLineList(long id) {

        return new ArrayList<OrderLine>();
    }
}
