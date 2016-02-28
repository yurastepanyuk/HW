package com.dreamshop.entity.documents;

import com.dreamshop.entity.catalogs.Customer;
import com.dreamshop.entity.catalogs.ShipAddres;
import com.dreamshop.repository.CustomerRepository;
import com.dreamshop.repository.OrderRepositiry;
import com.dreamshop.repository.ShipAddresRepository;

import java.util.List;

public class Order extends Document {

    private Customer    customer;
    private ShipAddres  shipAddres;
    private Integer     total;
    private List<OrderLine> orderLineList;

    public Order() {
    }

    public Order(long id, long customer_id, long ship_addres_id, int total) {
        super.id            = id;
        this.customer       = new CustomerRepository().getCustomer(customer_id);
        this.shipAddres     = new ShipAddresRepository().getAddres(ship_addres_id);
        this.total          = total;
        this.orderLineList  = new OrderRepositiry().getOrderLineList(id);
    }

    public void setOrderLineList(List<OrderLine> orderLineList) {
        this.orderLineList = orderLineList;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public ShipAddres getShipAddres() {
        return shipAddres;
    }

    public void setShipAddres(ShipAddres shipAddres) {
        this.shipAddres = shipAddres;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<OrderLine> getOrderLineList() {
        return orderLineList;
    }
}
