package com.dreamshop.controller;

import com.dreamshop.entity.catalogs.Customer;
import com.dreamshop.entity.documents.Order;
import com.dreamshop.entity.documents.OrderLine;
import com.dreamshop.repository.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/shop")
public class ShopController {

    private static ShopController instance;
    private static DB db;
    private ItemRepository           itemRepository;
    private CustomerRepository       customerRepository;
    private ShipAddresRepository     shipAddresRepository;
    private OrderRepositiry          orderRepositiry;
    private BalanceItemRepository    balanceItemRepository;
    private CreditCustomerRepository creditCustomerRepository;

    @RequestMapping(value = "/chekorder", method = RequestMethod.GET)
    public Customer chekOrder(){
//        Order order = new Order();
//
//        order.setCustomer(customerRepository.getCustomer(1));
//        order.setShipAddres(shipAddresRepository.getAddres(3));
//        order.setTotal(1110);
//
//        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
//        orderLineList.add(new OrderLine(null, 2l, 1, 100));
//        orderLineList.add(new OrderLine(null, 1l, 1, 10));
//        orderLineList.add(new OrderLine(null, 3l, 1, 1000));
//
//        order.setOrderLineList(orderLineList);
//
//        orderRepositiry.chekOrder(order);

        Customer customer = new Customer();
        customer.setId(1);
        customer.setName("FOP Stepanyuk Y.V.");
        customer.setEmail("yu.stepanyuk.ra@gmail.com");

        return customer;
    }

    @RequestMapping(value = "/buyorder", method = RequestMethod.PUT)
    //@ResponseBody
    public String BuyOrder(){

        Order order = new Order();

        order.setCustomer(customerRepository.getCustomer(1));
        order.setShipAddres(shipAddresRepository.getAddres(3));
        order.setTotal(1110);

        List<OrderLine> orderLineList = new ArrayList<OrderLine>();
        orderLineList.add(new OrderLine(null, 2l, 1, 100));
        orderLineList.add(new OrderLine(null, 1l, 1, 10));
        orderLineList.add(new OrderLine(null, 3l, 1, 1000));
        order.setOrderLineList(orderLineList);
        order.setOrderLineList(orderRepositiry.getOrderLineList(1));

        if (orderRepositiry.chekOrder(order)) {
            orderRepositiry.saveOrder(order);
        }

        return "/order.jsp";
    }

    @RequestMapping(value = "/getorders", method = RequestMethod.POST)
    @ResponseBody
    public String getOrders(){
        return "Clients";
    }

    @RequestMapping(value = "/itemsinstock", method = RequestMethod.GET)
    public String ItemsInStock(){
        return "/items.jsp";
    }

    @RequestMapping(value = "/getcustomers", method = RequestMethod.POST)
    public String GetCustomers(){
        return "/clients.jsp";
    }

    @RequestMapping(value = "/getcustomershippingaddresses", method = RequestMethod.POST)
    @ResponseBody
    public String GetCustomerShippingAddresses(){
        return "Shipping adresses";
    }

    public ShopController() {

    }

    public static ShopController getInstance(){

        if (instance == null) {
            instance = new ShopController();
            //instance.initialisation();
        } else {
            db.setNewConnection();
        }

        return instance;
    }

    public void initialisation(){

        try {
			db = new DBRepository(this);
			db.inicialisation();

            itemRepository              = new ItemRepository();
            customerRepository          = new CustomerRepository();
            shipAddresRepository        = new ShipAddresRepository();
            orderRepositiry             = new OrderRepositiry();
            balanceItemRepository       = new BalanceItemRepository();
            creditCustomerRepository    = new CreditCustomerRepository();

        } catch (SQLException e) {
            return;
        }

        db.initialisationDataBase();

    }

    public Connection getConnection(){

        return db.getConnection();

    }

}
