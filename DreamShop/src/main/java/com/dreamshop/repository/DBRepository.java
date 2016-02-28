package com.dreamshop.repository;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import com.dreamshop.controller.ShopController;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DBRepository implements DB  {

    Connection           connection;
    ConnectionRepository connectionRepository;
    ShopController       shopController;

//    Integer lastID = 0;

    public DBRepository(ShopController shop) {
        this.shopController = shop;
    }


    public void inicialisation() throws SQLException {
        try {

//            Class.forName("org.mariadb.jdbc.Driver");
//            Connection  connection =  DriverManager.getConnection("jdbc:mariadb://localhost:3306/test", "root", "root");
            connectionRepository = ConnectionRepository.getInstance();
            connection = connectionRepository.getConnection();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new SQLException("Don't connection to Maria DB");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            throw new SQLException("Don't connection to Maria DB");
          } catch (IOException e) {
            e.printStackTrace();
            throw new SQLException("Don't connection to Maria DB");
        }
    }

    public void initialisationDataBase(){

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.execute("CREATE DATABASE IF NOT EXISTS dreamshop");
            statement.execute("USE dreamshop");
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Can't create DataBase <dreamshop>");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

        DatabaseMetaData metaData = null;
        try {
            metaData = connection.getMetaData();

            InputStream inputStream;
            Properties property = new Properties();
            inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
            property.load(inputStream);

            ResultSet tables = metaData.getTables(null,property.getProperty("dbNameOfDB"),  null , null);

            if (tables.next() == false) {
                createTables();
                initialisationData();

            }


        } catch (SQLException e) {
            try {
                throw new SQLException("Can't create DataBase <dreamshop>");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void createTables(){

        Statement statement = null;
        try {
            statement = connection.createStatement();
            statement.addBatch(CREATE_ITEM);
            statement.addBatch(CREATE_CUSTOMER);
            statement.addBatch(CREATE_SHIP_ADDRES);
            statement.addBatch(CREATE_ORDER);
            statement.addBatch(CREATE_ORDER_LINE);
            statement.addBatch(CREATE_BALANCE_ITEM);
            statement.addBatch(CREATE_CREDIT_CUSTOMER);
            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void initialisationData() {
        createItems();

        createCustomers();

        createShipAddreses();

        createBalanceItem();

        createCreditsCustomers();

        createOrders();

    }

    private void createCustomers() {

        PreparedStatement statement = null;

        try {

            statement = connection.prepareStatement(ADD_NEW_CUSTOMER, Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, "TOV Ukrtelekom");
            statement.setString(2, "ukt@com.ua");
//            statement.setInt(3, autoParts.getCategoriya().getID());
            statement.executeUpdate();

            statement.setString(1, "TOV LifeCell");
            statement.setString(2, "life@com.ua");
            statement.executeUpdate();

            statement.setString(1, "FOP Stepanyuk Y. V.");
            statement.setString(2, "yu.stepanyuk.ra@gmail.com");
            statement.executeUpdate();

            ResultSet resultSetLastID = statement.getGeneratedKeys();

            while (resultSetLastID.next()){
                int result = resultSetLastID.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    private void createItems(){

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.addBatch("INSERT INTO items (sku, name, price) VALUES ('3602-15CH', 'Potato chips' , 10)");
            statement.addBatch("INSERT INTO items (sku, name, price) VALUES ('1232-10HN', 'Hazelnuts VIN', 100)");
            statement.addBatch("INSERT INTO items (sku, name, price) VALUES ('0002-10JU', 'Apple juice', 1000)");

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void createShipAddreses(){

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.addBatch("INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES ('08606', 1, 'Big Vasilkovskaya','26','165')");
            statement.addBatch("INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES ('08606', 1, 'Little Ciercle','1C','555')");
            statement.addBatch("INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES ('08606', 1, 'Green Toy','2','2')");
            statement.addBatch("INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES ('08606', 2, 'Heroes of Maydan','1','3')");
            statement.addBatch("INSERT INTO ship_addres (postal_code, customer_id, street, house, flat) VALUES ('08606' ,3, '1 Maya','1B','4')");

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void createBalanceItem(){

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.addBatch("INSERT INTO balance_items (item_id, qty) VALUES (1,100)");
            statement.addBatch("INSERT INTO balance_items (item_id, qty) VALUES (2,10)");
            statement.addBatch("INSERT INTO balance_items (item_id, qty) VALUES (3,0)");

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void createCreditsCustomers(){

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.addBatch("INSERT INTO credits_cutomers (customer_id, balance) VALUES (1,10000000)");
            statement.addBatch("INSERT INTO credits_cutomers (customer_id, balance) VALUES (2,0)");
            statement.addBatch("INSERT INTO credits_cutomers (customer_id, balance) VALUES (3,5000)");

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    private void createOrders(){

        Statement statement = null;

        try {

            statement = connection.createStatement();
            statement.addBatch("INSERT INTO orders (customer_id, ship_addres_id, total) VALUES (1,2,130)");

            statement.addBatch("SET @orderId := LAST_INSERT_ID()");

            statement.addBatch("INSERT INTO orders_line (order_id, item_id, qty, price) VALUES (@orderId,1,1,100)");
            statement.addBatch("INSERT INTO orders_line (order_id, item_id, qty, price) VALUES (@orderId,2,3,10)");

            statement.addBatch("SET @customerId := 1");
            statement.addBatch("UPDATE credits_cutomers SET balance = balance - 130 WHERE customer_id = @customerId");

            statement.addBatch("UPDATE balance_items SET qty = qty - 1 WHERE item_id = 1");
            statement.addBatch("UPDATE balance_items SET qty = qty - 3 WHERE item_id = 2");

            statement.executeBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    public Connection getConnection() {
        return connection;
    }

	public Connection getNewConnection()  throws PropertyVetoException, SQLException  {
		return connectionRepository.getConnection();
	}



    public void setNewConnection() {

        try {
            connection = connectionRepository.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Don't connection to Derby DB");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        } catch (PropertyVetoException e) {
            e.printStackTrace();
            try {
                throw new SQLException("Don't connection to Derby DB");
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }

    }

}
