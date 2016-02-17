package shop.database;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import shop.Launcher;

import java.beans.PropertyVetoException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class ConnectionSingletonDerby {

    private ComboPooledDataSource cpds;

    private static ConnectionSingletonDerby connectionSingleton;

    private Connection connection;

    private ConnectionSingletonDerby() throws PropertyVetoException, ClassNotFoundException, IllegalAccessException, InstantiationException, SQLException, IOException {

//        Class.forName("org.apache.derby.jdbc.EmbeddedDriver").newInstance();
//
//        connectionSingleton = this;
//
//        Connection connection = DriverManager.getConnection("jdbc:derby:dbo_autoparts;create=true");
//
//        this.connection = connection;

		InputStream inputStream;
        Properties property = new Properties();
        inputStream = getClass().getClassLoader().getResourceAsStream("shop/database/derby.properties");
        property.load(inputStream);

		cpds = new ComboPooledDataSource();
        cpds.setDriverClass(property.getProperty("dbDriverClass"));
        cpds.setJdbcUrl(property.getProperty("dbJdbcUrl"));
        cpds.setUser(property.getProperty("dbUser"));
        cpds.setPassword(property.getProperty("dbPassword"));

    //    cpds = new ComboPooledDataSource();
    //    cpds.setDriverClass(WorkWithDerby.getDRIVER());
    //    cpds.setJdbcUrl(WorkWithDerby.getPROTOCOL() + WorkWithDerby.getNAMEDB() +  ";create=true");
    //    cpds.setUser(null);
    //    cpds.setPassword(null);

        cpds.setMinPoolSize(10);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(50);
        cpds.setMaxStatements(50);

    }

    public static ConnectionSingletonDerby getInstance() throws SQLException, PropertyVetoException, IllegalAccessException, InstantiationException, ClassNotFoundException, IOException {
        if (connectionSingleton == null) {
            connectionSingleton = new ConnectionSingletonDerby();
            return connectionSingleton;
        } else {
            return connectionSingleton;
        }
    }

    public Connection getConnection() throws SQLException, PropertyVetoException {

        return this.cpds.getConnection();
       // return this.connection;

    }

}
