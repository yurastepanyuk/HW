package com.dreamshop.repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionRepository {

    private ComboPooledDataSource cpds;

    private static ConnectionRepository connectionSingleton;

    private ConnectionRepository() throws PropertyVetoException, IOException {
	
		InputStream inputStream;
        Properties property = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream("db.properties");
        property.load(inputStream);
		
		cpds = new ComboPooledDataSource();
        cpds.setDriverClass(property.getProperty("dbDriverClass"));
        cpds.setJdbcUrl(property.getProperty("dbJdbcUrl"));
        cpds.setUser(property.getProperty("dbUser"));
        cpds.setPassword(property.getProperty("dbPassword"));

        //cpds.setInitialPoolSize(3);
        cpds.setMinPoolSize(5);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(50);
        cpds.setMaxStatements(150);
        cpds.setIdleConnectionTestPeriod(100);
        cpds.setCheckoutTimeout(10000);
        cpds.setAutoCommitOnClose(true);
    }

    public static ConnectionRepository getInstance() throws SQLException, PropertyVetoException, IOException {
        if (connectionSingleton == null) {
            connectionSingleton = new ConnectionRepository();
            return connectionSingleton;
        } else {
            return connectionSingleton;
        }
    }

    public Connection getConnection() throws SQLException, PropertyVetoException {
        return cpds.getConnection();
    }

}
