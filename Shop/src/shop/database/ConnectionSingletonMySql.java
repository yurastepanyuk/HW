package shop.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionSingletonMySql {

    private ComboPooledDataSource cpds;

    private static ConnectionSingletonMySql connectionSingleton;

    private ConnectionSingletonMySql() throws PropertyVetoException, IOException {
	
		InputStream inputStream;
        Properties property = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream("shop/database/mysql.properties");
        property.load(inputStream);
		
		cpds = new ComboPooledDataSource();
        cpds.setDriverClass(property.getProperty("dbDriverClass"));
        cpds.setJdbcUrl(property.getProperty("dbJdbcUrl"));
        cpds.setUser(property.getProperty("dbUser"));
        cpds.setPassword(property.getProperty("dbPassword"));

        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(10);
        cpds.setMaxPoolSize(50);
        cpds.setMaxStatements(150);
        cpds.setIdleConnectionTestPeriod(100);
        cpds.setCheckoutTimeout(4000);
        cpds.setAutoCommitOnClose(true);
    }

    public static ConnectionSingletonMySql getInstance() throws SQLException, PropertyVetoException, IOException {
        if (connectionSingleton == null) {
            connectionSingleton = new ConnectionSingletonMySql();
            return connectionSingleton;
        } else {
            return connectionSingleton;
        }
    }

    public Connection getConnection() throws SQLException, PropertyVetoException {
        return cpds.getConnection();
    }

}
