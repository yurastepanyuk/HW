package com.dreamshop.entity.documents;

import com.dreamshop.entity.catalogs.Item;
import com.dreamshop.repository.ItemRepository;
import com.dreamshop.repository.OrderRepositiry;

public class OrderLine {

    private Order order;
    private Item item;
    private int  qty;
    private int  price;

    public OrderLine() {
    }

    public OrderLine(Long orderId, Long itemId, int qty, int price) {
        this.order = new OrderRepositiry().getOrder(orderId);
        this.item = new ItemRepository().getItem(itemId);
        this.qty = qty;
        this.price = price;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
