package com.dreamshop.repository;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface DB {

    public void inicialisation() throws SQLException;

    public void initialisationDataBase();

    public void initialisationData();

    public Connection getConnection();

    public void setNewConnection();

    public static final String ADD_NEW_CUSTOMER         = "INSERT INTO customers (user_name, email) VALUES (?,?)";
    public static final String ADD_NEW_ITEM             = "INSERT INTO items (sku, name, price) VALUES (?,?,?)";
    public static final String ADD_NEW_SHIP_ADDRES      = "INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES (?,?,?,?,?)";
    public static final String ADD_NEW_ORDER            = "INSERT INTO orders (customer_id, ship_addres_id, total) VALUES (?,?,?)";
    public static final String ADD_NEW_ORDER_LINE       = "INSERT INTO orders_line (order_id, item_id, qty, price) VALUES (?,?,?,?)";
    public static final String ADD_NEW_BALANCE_ITEM     = "INSERT INTO balance_items (item_id, qty) VALUES (?,?)";
    public static final String ADD_NEW_CREDIT_CUSTOMER  = "INSERT INTO credits_cutomers (customer_id, balance) VALUES (?,?)";

    public static final String CREATE_ITEM = "CREATE TABLE IF NOT EXISTS items (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "sku VARCHAR(64) NOT NULL,\n" +
            "name VARCHAR(256) NULL DEFAULT NULL,\n" +
            "price INT(10) UNSIGNED NULL DEFAULT '0',\n" +
            "PRIMARY KEY (id),\n" +
            "UNIQUE INDEX sku (sku),\n" +
            "FULLTEXT INDEX name (name)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_CUSTOMER = "CREATE TABLE IF NOT EXISTS customers (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "user_name VARCHAR(128) NOT NULL,\n" +
            "email VARCHAR(64) NULL DEFAULT NULL,\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX user_name (user_name)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_SHIP_ADDRES = "CREATE TABLE IF NOT EXISTS ship_addres (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "postal_code VARCHAR(36) NOT NULL,\n" +
            "customer_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "street VARCHAR(64) NOT NULL,\n" +
            "house VARCHAR(12) NOT NULL,\n" +
            "flat VARCHAR(6) NULL DEFAULT NULL,\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX FK_customer (customer_id),\n" +
            "CONSTRAINT FK_customer FOREIGN KEY (customer_id) REFERENCES customers (id)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_ORDER = "CREATE TABLE IF NOT EXISTS orders (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "customer_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "ship_addres_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "total INT(10) UNSIGNED NULL DEFAULT '0',\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX FK_order_customer (customer_id),\n" +
            "INDEX FK2_order_ship_addres (ship_addres_id),\n" +
            "CONSTRAINT FK2_order_ship_addres FOREIGN KEY (ship_addres_id) REFERENCES ship_addres (id),\n" +
            "CONSTRAINT FK_order_customer FOREIGN KEY (customer_id) REFERENCES customers (id)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_ORDER_LINE = "CREATE TABLE IF NOT EXISTS orders_line (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "order_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "item_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "qty INT(36) UNSIGNED NOT NULL,\n" +
            "price INT(10) UNSIGNED NOT NULL DEFAULT '0',\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX FK_order_line_items (item_id),\n" +
            "INDEX FK_order_line_order (order_id),\n" +
            "CONSTRAINT FK_order_line_items FOREIGN KEY (item_id) REFERENCES items (id),\n" +
            "CONSTRAINT FK_order_line_order FOREIGN KEY (order_id) REFERENCES orders (id)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_BALANCE_ITEM = "CREATE TABLE IF NOT EXISTS balance_items (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "item_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "qty INT(10) NULL DEFAULT '0',\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX item_id (item_id),\n" +
            "CONSTRAINT FK_item_id FOREIGN KEY (item_id) REFERENCES items (id)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";
    public static final String CREATE_CREDIT_CUSTOMER = "CREATE TABLE IF NOT EXISTS credits_cutomers (\n" +
            "id BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT,\n" +
            "customer_id BIGINT(20) UNSIGNED NOT NULL,\n" +
            "balance INT(11) NULL DEFAULT '0',\n" +
            "PRIMARY KEY (id),\n" +
            "INDEX customer_id (customer_id),\n" +
            "CONSTRAINT FK_balance_customer FOREIGN KEY (customer_id) REFERENCES customers (id)\n" +
            ")\n" +
            "COLLATE='utf8_general_ci'\n" +
            "ENGINE=InnoDB\n" +
            ";";



}
