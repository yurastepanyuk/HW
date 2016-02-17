package shop.database;

import shop.Shop;
import shop.enums.Category;
import shop.enums.CategoryPrice;
import shop.inforamation.Prices;
import shop.reference.AutoParts;

import java.beans.PropertyVetoException;
import java.sql.*;

public final class InitDBDerby implements QueryToDB {

    private static InitDBDerby initDBDerby;

    private WorkWithDerby serviceDB;

    public static InitDBDerby getInitialization(WorkWithDerby serviceDB) throws PropertyVetoException, SQLException {

        if (initDBDerby == null){
            initDBDerby = new InitDBDerby(serviceDB);
            return initDBDerby;
        } else {
            return initDBDerby;
        }

    }

    private InitDBDerby(WorkWithDerby serviceDB) throws SQLException, PropertyVetoException {
        this.serviceDB = serviceDB;

        // Get the MetaData
        Connection connection = serviceDB.getConnection();

        DatabaseMetaData metaData = connection.getMetaData();

        // Get driver information
        System.out.println("Driver Informaion");
        System.out.println(metaData.getDriverName());
        System.out.println(metaData.getDriverVersion());
        // Get schema information
        System.out.println("Schemas");
        ResultSet schemas = metaData.getSchemas();

//        boolean chekSchema = false;
//        while (schemas.next()) {
//            System.out.println(schemas.getString(1));
//
//            if(schemas.getString(1).equals("dbo_autoparts")) {
//                chekSchema = true;
//                break;
//            }
//
//        }
//
//        if (!chekSchema) throw new SQLException("DB already");

        // Get table information
        System.out.println("Tables");
        ResultSet tables = metaData.getTables(null, null, "autoparts".toUpperCase() , null);
        boolean chekIsTable = false;
        if (tables.next()) {
            chekIsTable = true;
        }

        if (!chekIsTable)createTables();

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM sales_line");

        if (!resultSet.next()) createDataOnDB();

    }

    private void createTables() throws SQLException, PropertyVetoException {

        Statement statement = serviceDB.getConnection().createStatement();
        statement.addBatch(CREATE_AUTOPARTS_DERBY);
        statement.addBatch(CREATE_IDX_AUTOPARTS_DERBY_CATALOGNUMBER);
        statement.addBatch(CREATE_CLIENT_DERBY);
        statement.addBatch(CREATE_IDX_CLIENT_DERBY_INN);
        statement.addBatch(CREATE_DOCSSHOPPING_DERBY);
        statement.addBatch(CREATE_IDX_DOCSSHOPPING_DERBY_CLIENT);
        statement.addBatch(CREATE_DOCSSALES_DERBY);
        statement.addBatch(CREATE_PRICES_DERBY);
        statement.addBatch(CREATE_BALANCE_AP_DERBY);
        statement.addBatch(CREATE_DOCSSALESLINE_DERBY);
        statement.addBatch(CREATE_DOCSSHOPLINE_DERBY);

        statement.executeBatch();

    }

    private void createDataOnDB(){

        createAutoPartsAndPrices();

    }

    private void createAutoPartsAndPrices() {

        AutoParts ap = new AutoParts(serviceDB.shop);
        Prices prc;

        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("450 05 02");
        ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
        ap.setName("Akkumulyator 50 A/Ch Evro");
        ap.save();

        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float)1000.50, CategoryPrice.ROZNITSA);
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float)1100.00, CategoryPrice.ROZNITSA);
        }

        ap = new AutoParts(serviceDB.shop);
        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("1987302031");
        ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
        ap.setName("Lamp H3 12V 55W Pure Light");
        ap.save();
        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float) 43.26, CategoryPrice.ROZNITSA);
        }

        ap = new AutoParts(serviceDB.shop);
        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("24312-23001-01");
        ap.setCategoriya(Category.DVIGATEL);
        ap.setName("Remen GRM 111SP254H (DAYCO 111SP254H)");
        ap.save();
        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float) 109.50, CategoryPrice.ROZNITSA);
        }

        ap = new AutoParts(serviceDB.shop);
        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("55361-17630");
        ap.setCategoriya(Category.HODOVAYA);
        ap.setName("Amortisator zadnij right");
        ap.save();
        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float) 450.00, CategoryPrice.ROZNITSA);
        }

        ap = new AutoParts(serviceDB.shop);
        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("GTX 10W-40 4 l");
        ap.setCategoriya(Category.OIL);
        ap.setName("Motor Castrol GTX 10W-40 A3/B4");
        ap.save();
        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float) 750.00, CategoryPrice.ROZNITSA);
        }

        ap = new AutoParts(serviceDB.shop);
        ap.setShop(serviceDB.shop);
        ap.setCatalogNumber("195/60R15 SP 01");
        ap.setCategoriya(Category.SHINA);
        ap.setName("Shina 195/60R15 88V SP Sport 01 Dunlop");
        ap.save();
        if (ap.getId() != 0) {
            prc = new Prices(serviceDB.shop);
            prc.setPrice(ap, (float) 1020.80, CategoryPrice.ROZNITSA);
        }

    }

}
