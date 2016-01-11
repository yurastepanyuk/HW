package shop.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import shop.Shop;
import shop.balances.BalancesAutoParts;
import shop.documents.Document;
import shop.documents.Sale;
import shop.documents.Shopping;
import shop.enums.Category;
import shop.enums.CategoryPrice;
import shop.inforamation.Prices;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkWithMySQL implements DB, QueryToDB  {

    Connection connection;
    ConnectionSingletonMySql connectionSingleton;
    Shop shop;

    private final static String URL     = "jdbc:mysql://localhost:3306/dbo_autoparts";
    private final static String NAMEDB  = "dbo_autoparts";
    private final static String NAME    = "sa";
    private final static String PASS    = "sasa";

    Integer lastID = 0;

    public WorkWithMySQL(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void inicialisation() throws SQLException {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connectionSingleton = ConnectionSingletonMySql.getInstance();

            connection = connectionSingleton.getConnection();

//            statement.execute("SET character_set_client='utf8'");
//            statement.execute("SET character_set_connection='utf8'");
//              statement.execute("SET collation_connection='utf8'");

        } catch (SQLException | PropertyVetoException | IOException e) {
            e.printStackTrace();
            throw new SQLException("Don't connection to MySQL DB");
        }
    }

    @Override
    public <T> List<?> getDataFromTable(Class<T> cls) {

        String nameTable = cls.getSimpleName();

        if (nameTable.equals("AutoParts")) {
            return getAllDataOnAutoParts();
        } else if (nameTable.equals("Client")){
            return getAllDataOnClient();
        } else if (nameTable.equals("Shopping")){
            return getAllDataOnShopping();
        } else if (nameTable.equals("Sale")){
            return getAllDataOnSale();
        } else if (nameTable.equals("BalancesAutoParts")){
            return getAllDataOnBalancesAutoParts();
        } else if (nameTable.equals("Prices")){
            return getAllDataOnPrices();
        }
        return null;
    }

    @Override
    public Integer addNewRecord_(Object object) {
        String nameTable = object.getClass().getSimpleName();

        if (nameTable.equals("AutoParts")) {
            return addNewAutoParts((AutoParts) object);
        } else if (nameTable.equals("Client")){
            return addNewClient((Client) object);
        } else if (nameTable.equals("Shopping")){
            return addNewShopping((Shopping) object);
        } else if (nameTable.equals("Sale")){
            return addNewSale((Sale) object);
        } else if (nameTable.equals("BalancesAutoParts")){
            //return addNewBalancesAutoParts((BalancesAutoParts) object);
        } else if (nameTable.equals("Prices")){
            return addNewPrices((Prices) object);
        }

        return null;

    }

    @Override
    public void updateRecord(Object object) {

        String nameTable = object.getClass().getSimpleName();

        if (nameTable.equals("AutoParts")) {
            updateRecordAutoParts((AutoParts) object);
        } else if (nameTable.equals("Client")){
            updateRecordClient((Client) object);
        } else if (nameTable.equals("Shopping")){
            updateRecordShopping((Shopping) object);
        } else if (nameTable.equals("Sale")){
            updateRecordSale((Sale) object);
        } else if (nameTable.equals("BalancesAutoParts")){
            //updateRecordBalancesAutoParts((BalancesAutoParts) object);
        } else if (nameTable.equals("Prices")){
            updateRecordPrices((Prices) object);
        }

    }

    @Override
    public void deleteRecord(Object object) {

        String nameTable = object.getClass().getSimpleName();

        if (nameTable.equals("AutoParts")) {
            deleteRecordAutoParts((AutoParts) object);
        } else if (nameTable.equals("Client")){
            deleteRecordClient((Client) object);
        } else if (nameTable.equals("Shopping")){
            deleteRecordShopping((Shopping) object);
        } else if (nameTable.equals("Sale")){
            deleteRecordSale((Sale) object);
        } else if (nameTable.equals("BalancesAutoParts")){
            deleteRecordBalancesAutoParts((BalancesAutoParts) object);
        } else if (nameTable.equals("Prices")){
            deleteRecordPrices((Prices) object);
        }

    }

    @Override
    public <T> T getObjectById(int id, Class<T> cls) {

        T findObject;

        String nameTable = cls.getSimpleName();


        if (nameTable.equals("AutoParts")) {
            findObject = (T)getByIdAutoParts(id);
        } else if (nameTable.equals("Client")){
            findObject = (T)getByIdClient(id);
        } else if (nameTable.equals("Shopping")){
            findObject = (T)getByIdShopping(id);
        } else if (nameTable.equals("Sale")){
            findObject = (T)getByIdSale(id);
        } else if (nameTable.equals("BalancesAutoParts")){
            findObject = (T)getByIdBalancesAutoParts(id);
        } else if (nameTable.equals("Prices")){
            findObject = (T)getByIdPrices(id);
        } else {
            findObject = null;
        }

        return findObject;
    }

    @Override
    public <T> T getObjectByName(String name, Class<T> cls) {
        T findObject;

        String nameTable = cls.getSimpleName();


        if (nameTable.equals("AutoParts")) {
            findObject = (T)getByNameAutoParts(name);
        } else if (nameTable.equals("Client")){
            findObject = (T)getByNameClient(name);
        } else if (nameTable.equals("Shopping")){
            findObject = (T)getByNameShopping(name);
        } else if (nameTable.equals("Sale")){
            findObject = (T)getByNameSale(name);
        } else if (nameTable.equals("BalancesAutoParts")){
            findObject = (T)getByNameBalancesAutoParts(name);
        } else if (nameTable.equals("Prices")){
            findObject = (T)getByNamePrices(name);
        } else {
            findObject = null;
        }

        return findObject;
    }

    @Override
	public <T> T getResourceByObject(Object [] object, Class<T> clsTypeResourceReturn){
	
		String nameReturnType = clsTypeResourceReturn.getSimpleName();
		
		Object filterObject = object[0];
		
		if (nameReturnType.equals("BalancesAutoParts")){
		
			if (filterObject instanceof AutoParts){
				return (T)getBalanceByAutoParts(((AutoParts)filterObject).getId());
			}
		
		}else if (nameReturnType.equals("Prices")) {
		
			if (filterObject instanceof AutoParts){

                CategoryPrice filterCategory = (CategoryPrice)object[1];
				
				return (T)getPriceByAutoPartsAndCategory(((AutoParts)filterObject).getId(), filterCategory.getID());
			}
		
		} else if (nameReturnType.equals("AutoParts")) {

            if (filterObject instanceof String){

                String nameField = (String)object[1];

                if (nameField.equals("catalogNumber")) {
                    return (T)getAutoPartsByCatalogNumber((String)filterObject);
                }

            }

        }

        return null;
	
	}

    //GET ALL

    private List<Prices> getAllDataOnPrices() {
        List<Prices> resultList = new ArrayList<>();

        try(Statement statement = connection.createStatement(); 
			Statement statementAutoParts = connection.createStatement();) {

            ResultSet resultSet = statement.executeQuery(QueryToDB.GET_ALL_DATA_ON_PRICES);
            while (resultSet.next()){
                Prices price = new Prices(shop);
                price.setIdAutoParts(resultSet.getInt("price_id"));
                price.setIdAutoParts(resultSet.getInt("autoparts_id"));
                price.setKategoriya( CategoryPrice.getById(resultSet.getInt("kategoriya_cena")) );

                String query = QueryToDB.GET_BY_ID_AUTOPARTS;

                ResultSet resultSetAutoParts = statementAutoParts.executeQuery(query.replace("?", Integer.toString(resultSet.getInt("autoparts_id"))));

                AutoParts autoParts = null;

                while (resultSetAutoParts.next()){

                    autoParts = new AutoParts(shop);
                    autoParts.setId(resultSetAutoParts.getInt("id"));
                    autoParts.setName(resultSetAutoParts.getString("name"));
                    autoParts.setCatalogNumber(resultSetAutoParts.getString("catalognumber"));
                    autoParts.setCategoriya(Category.getById(resultSetAutoParts.getInt("categoriya")));

                    break;
                }
                price.setAutoParts(autoParts);

//                BigDecimal price_ = resultSet.getBigDecimal("price");
//                DecimalFormat decimalFormat = new DecimalFormat("###.##");
//                String outputPrice = decimalFormat.format(price_);

                price.setPrise(new Float(resultSet.getFloat("price")));

                resultList.add(price);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return resultList;
    }

    private List<BalancesAutoParts> getAllDataOnBalancesAutoParts() {

        List<BalancesAutoParts> resultList = new ArrayList<>();

        try(Statement statement = connection.createStatement();
            Statement statementAutoParts = connection.createStatement()){

            ResultSet resultSet = statement.executeQuery(QueryToDB.GET_ALL_DATA_ON_BALANCEAUTOPARTS);

            while (resultSet.next()){

                BalancesAutoParts balancesAutoParts = new BalancesAutoParts(shop);

                balancesAutoParts.setidBalancesAutoParts(resultSet.getInt("balance_id"));
                balancesAutoParts.setQty(resultSet.getInt("qty"));

                String queryAutoParts = QueryToDB.GET_BY_ID_AUTOPARTS.replace("?", Integer.toString(resultSet.getInt("autoparts_id")));

                ResultSet resultSetAutoParts = statementAutoParts.executeQuery(queryAutoParts);

                AutoParts autoPart = null;

                while(resultSetAutoParts.next()){
                    autoPart = new AutoParts(shop);
                    autoPart.setId(resultSetAutoParts.getInt("id"));
                    autoPart.setName(resultSetAutoParts.getString("name"));
                    autoPart.setCatalogNumber(resultSetAutoParts.getString("catalognumber"));
                    autoPart.setCategoriya(Category.getById(resultSetAutoParts.getInt("categoriya")));

                    break;
                }

                balancesAutoParts.setAutoParts(autoPart);

                resultList.add(balancesAutoParts);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return resultList;
    }

    private List<Sale> getAllDataOnSale() {

        List<Sale> result = new ArrayList<>();

        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.GET_ALL_DATA_ON_SALE)){
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){

                Sale sale = new Sale(shop);
                sale.setId(resultSet.getInt("id"));
                sale.setAutoParts(getByIdAutoParts(resultSet.getInt("autoparts_id")));
                sale.setCena(resultSet.getFloat("price"));
                sale.setQty((byte)resultSet.getInt("qty"));
                sale.setClient(getByIdClient(resultSet.getInt("clients_id")));
                sale.setNumDocument(resultSet.getString("doc_num"));
                sale.setDateDoc(resultSet.getDate("date"));
                sale.setDate((int)(resultSet.getDate("date").getTime()/(1000*60*60*24)));

                result.add(sale);

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    private List<Shopping> getAllDataOnShopping() {
        return null;
    }

    private List<Client> getAllDataOnClient() {

        List<Client> resultList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QueryToDB.GET_ALL_DATA_ON_CLIENT);
            while (resultSet.next()){

                Client client = new Client(shop);
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setInn(resultSet.getInt("inn"));

                resultList.add(client);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return resultList;
    }

    private List<AutoParts> getAllDataOnAutoParts() {

        List<AutoParts> resultList = new ArrayList<>();

        try(Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(QueryToDB.GET_ALL_DATA_ON_AUTOPARTS);
            while (resultSet.next()){

                AutoParts autoParts = new AutoParts(shop);
                autoParts.setId(resultSet.getInt("id"));
                autoParts.setName(resultSet.getString("name"));
                autoParts.setCatalogNumber(resultSet.getString("catalognumber"));
                autoParts.setCategoriya(Category.getById(resultSet.getInt("categoriya")));

                resultList.add(autoParts);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        }

        return resultList;
    }


    // ADD NEW

    public Integer addNewAutoParts(AutoParts autoParts) {

        Integer result = null;

        try(PreparedStatement statement = connection.prepareStatement(QueryToDB.ADD_NEW_AUTOPARTS);) {

            statement.setString(1, autoParts.getName());
            statement.setString(2, autoParts.getCatalogNumber());
            statement.setInt(3, autoParts.getCategoriya().getID());
            statement.executeUpdate();

            Statement statementLastID = getConnection().createStatement();
            ResultSet resultSetLastID = statementLastID.executeQuery("select last_insert_id()");

            while (resultSetLastID.next()){
                result = resultSetLastID.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            result = null;
        } finally {
            return result;
        }

    }

    public Integer addNewPrices(Prices prices) {
        Integer result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.ADD_NEW_PRICES);
            PreparedStatement preparedStatementCheck = connection.prepareStatement(QueryToDB.GET_PRICES_BY_AUTOPARTS_CATEGORY_ID)){

            preparedStatementCheck.setInt(1, prices.getIdAutoParts() );
            preparedStatementCheck.setInt(2, prices.getKategoriya().getID());

            ResultSet resultSetCheck = preparedStatementCheck.executeQuery();

            if (resultSetCheck.next()) {
                result = resultSetCheck.getInt(1);
                throw new RuntimeException();
            }

            preparedStatement.setFloat(1, prices.getPrise());
            preparedStatement.setInt(2, prices.getKategoriya().getID());
            preparedStatement.setInt(3, prices.getIdAutoParts());

            preparedStatement.executeUpdate();

            Statement statementLastID = getConnection().createStatement();
            ResultSet resultSetLastID = statementLastID.executeQuery("select last_insert_id()");

            while (resultSetLastID.next()){
                result = resultSetLastID.getInt(1);
            }

        }finally {
            return result;
        }

    }

    public Integer addNewClient(Client client) {

        Integer result = null;

        try(PreparedStatement statement = connection.prepareStatement(QueryToDB.ADD_NEW_CLIENT);
            Statement statementChek = connection.createStatement()){

            ResultSet resultSetCheck = statementChek.executeQuery((QueryToDB.GET_BY_NAME_CLIENT).replace("?", "'" + client.getName() + "'"));

            while (resultSetCheck.next()){
                result = resultSetCheck.getInt("id");
                throw new SQLException();
            }

            statement.setString(1, client.getName());
            if (!(client.getInn() == 0)) {
                statement.setInt(2, client.getInn());
            } else {
                statement.setNull(2,java.sql.Types.INTEGER);
            }

            statement.executeUpdate();

            Statement statementLastID = getConnection().createStatement();
            ResultSet resultSetLastID = statementLastID.executeQuery("select last_insert_id()");

            while (resultSetLastID.next()){
                result = resultSetLastID.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            return result;
        }
    }

    public Integer addNewShopping(Shopping shopping) {
        Integer result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.ADD_NEW_SHOPPING);
            Statement statementCheck = connection.createStatement();
            PreparedStatement preparedStatementLine = connection.prepareStatement(QueryToDB.ADD_NEW_SHOPPING_LINE)) {
            //"INSERT INTO dbo_autoparts.docs_shopping (date, doc_num, clients_id) VALUES (?,?,?)";
            //"INSERT INTO dbo_autoparts.shopping_line (qty, price, docs_shopping_id, autoparts_id) VALUES (?,?,?,?)"

            String nameDoc = shopping.getNumDocument();

            if (nameDoc == null || nameDoc.isEmpty()) {
                nameDoc = getNewNumDocument(shopping);
            }

            shopping.setNumDocument(nameDoc);

            String queryCheck = QueryToDB.GET_BY_NAME_SHOPPING.replace("?", "'" + nameDoc + "'");

            ResultSet resultSetCheck = statementCheck.executeQuery(queryCheck);

            if (resultSetCheck.next()) {
                throw new SQLException();
            }

			preparedStatement.setDate(  1, new Date(shopping.getDateDoc().getTime()));
            preparedStatement.setString(2, nameDoc);
            preparedStatement.setInt(   3, shopping.getClient().getId());

            preparedStatement.executeUpdate();

            Statement statementLastID = getConnection().createStatement();
            ResultSet resultSetLastID = statementLastID.executeQuery("select last_insert_id()");

            while (resultSetLastID.next()){
                result = resultSetLastID.getInt(1);
            }

			preparedStatementLine.setInt(1, shopping.getQty());
			preparedStatementLine.setFloat(2, shopping.getCena());
			preparedStatementLine.setInt(3, result);
			preparedStatementLine.setInt(4, shopping.getAutoParts().getId());
			
			preparedStatementLine.executeUpdate();

            BalancesAutoParts balancesAutoPartsChange = new BalancesAutoParts(shop, 0, shopping.getAutoParts(), shopping.getQty());
            updateRecordBalancesAutoParts(balancesAutoPartsChange, true);
			
		} catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public Integer addNewBalancesAutoParts(BalancesAutoParts balancesAutoParts, boolean add) {
        Integer result = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.ADD_NEW_BALANCEAUTOPARTS);
            Statement statement = connection.createStatement()){
            //"INSERT INTO dbo_autoparts.balance_auto_parts (qty,autoparts_id) VALUES (?,?)"

            preparedStatement.setInt(1, balancesAutoParts.getQty() * (add?1:-1));
            preparedStatement.setInt(2, balancesAutoParts.getAutoParts().getId());

            preparedStatement.executeUpdate();

            ResultSet resultSetLastId = statement.executeQuery("SELECT last_insert_id()");

            if (resultSetLastId.next()) {
                result = resultSetLastId.getInt(1);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    public Integer addNewSale(Sale sale) {
        Integer result = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.ADD_NEW_SALE);
             Statement statementCheck = connection.createStatement();
             PreparedStatement preparedStatementLine = connection.prepareStatement(QueryToDB.ADD_NEW_SALE_LINE)) {
            //"INSERT INTO dbo_autoparts.docs_sales (date, doc_num, clients_id) VALUES (?,?,?)";
            //"INSERT INTO dbo_autoparts.sales_line (qty, price, docs_sales_id, autoparts_id) VALUES (?,?,?,?)"

            String nameDoc = sale.getNumDocument();

            if (nameDoc == null || nameDoc.isEmpty()) {
                nameDoc = getNewNumDocument(sale);
            }

            sale.setNumDocument(nameDoc);

            String queryCheck = QueryToDB.GET_BY_NAME_SALE.replace("?", "'" + nameDoc + "'");

            ResultSet resultSetCheck = statementCheck.executeQuery(queryCheck);

            if (resultSetCheck.next()) {
                throw new SQLException();
            }

            preparedStatement.setDate(  1, new Date(sale.getDateDoc().getTime()));
            preparedStatement.setString(2, nameDoc);
            preparedStatement.setInt(   3, sale.getClient().getId());

            preparedStatement.executeUpdate();

            Statement statementLastID = getConnection().createStatement();
            ResultSet resultSetLastID = statementLastID.executeQuery("select last_insert_id()");

            while (resultSetLastID.next()){
                result = resultSetLastID.getInt(1);
            }

            preparedStatementLine.setInt(1, sale.getQty());
            preparedStatementLine.setFloat(2, sale.getCena());
            preparedStatementLine.setInt(3, result);
            preparedStatementLine.setInt(4, sale.getAutoParts().getId());

            preparedStatementLine.executeUpdate();

            BalancesAutoParts balancesAutoPartsChange = new BalancesAutoParts(shop, 0, sale.getAutoParts(), sale.getQty());
            updateRecordBalancesAutoParts(balancesAutoPartsChange, false);

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }


    //UPDATE

    public void updateRecordPrices(Prices prices) {

    }

    public void updateRecordClient(Client client) {

    }

    public void updateRecordShopping(Shopping shopping) {

    }

    public void updateRecordBalancesAutoParts(BalancesAutoParts balancesAutoParts, boolean add) {

        Integer result;

        BalancesAutoParts balancesAutoPartsCurrent = getBalanceByAutoParts(balancesAutoParts.getAutoParts().getId());

        if (balancesAutoPartsCurrent == null) {
            result = addNewBalancesAutoParts(balancesAutoParts, add);
            balancesAutoPartsCurrent.setidBalancesAutoParts(result);
        } else {

            if (add) {
                balancesAutoPartsCurrent.setQty(balancesAutoPartsCurrent.getQty() +  balancesAutoParts.getQty());
            } else {
                balancesAutoPartsCurrent.setQty(balancesAutoPartsCurrent.getQty() -  balancesAutoParts.getQty());
            }

            //updateResources(balancesAutoPartsCurrent);

            try (PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.UPDATE_BALANCEAUTOPARTS)){
            //"UPDATE dbo_autoparts.balance_auto_parts SET qty = ?,autoparts_id = ? WHERE autoparts_id = ?"
                preparedStatement.setInt(1, balancesAutoPartsCurrent.getQty());
                preparedStatement.setInt(2, balancesAutoPartsCurrent.getAutoParts().getId());
                preparedStatement.setInt(3, balancesAutoPartsCurrent.getAutoParts().getId());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public void updateRecordAutoParts(AutoParts autoParts) {

    }

    public void updateRecordSale(Sale sale) {

    }

    //DELETE

    public void deleteRecordAutoParts(AutoParts autoParts) {

    }

    public void deleteRecordPrices(Prices prices) {

    }

    public void deleteRecordClient(Client client) {

    }

    public void deleteRecordShopping(Shopping shopping) {

    }

    public void deleteRecordBalancesAutoParts(BalancesAutoParts balancesAutoParts) {

    }

    public void deleteRecordSale(Sale sale) {

    }


    //GET BY ID

    public AutoParts getByIdAutoParts(int id) {

        AutoParts autoParts = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.GET_BY_ID_AUTOPARTS)){
            //"SELECT * FROM dbo_autoparts.autoparts WHERE id = ?"
            preparedStatement.setInt(1,id);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                autoParts = new AutoParts(shop);
                autoParts.setId(resultSet.getInt("id"));
                autoParts.setName(resultSet.getString("name"));
                autoParts.setCatalogNumber(resultSet.getString("catalognumber"));
                autoParts.setCategoriya(Category.getById(resultSet.getInt("categoriya")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return autoParts;
    }


    public Prices getByIdPrices(int id) {
        return null;
    }


    public Client getByIdClient(int id) {

        Client client = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.GET_BY_ID_CLIENT)){
        //"SELECT * FROM dbo_autoparts.clients WHERE id = ?"; (name,inn)
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                client = new Client(shop);
                client.setId(resultSet.getInt("id"));
                client.setName(resultSet.getString("name"));
                client.setInn(resultSet.getInt("inn"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            return client;
        }

    }


    public Shopping getByIdShopping(int id) {
        return null;
    }


    public BalancesAutoParts getByIdBalancesAutoParts(int id) {
        return null;
    }


    public Sale getByIdSale(int id) {
        return null;
    }


    //GET BY NAME

    public AutoParts getByNameAutoParts(String name) {
        return null;
    }


    public Prices getByNamePrices(String name) {
        return null;
    }


    public Client getByNameClient(String name) {
        return null;
    }


    public Shopping getByNameShopping(String name) {
        return null;
    }


    public BalancesAutoParts getByNameBalancesAutoParts(String name) {
        return null;
    }


    public Sale getByNameSale(String name) {
        return null;
    }

    @Override
    public int getNewId(Object object) {

        Integer result = new Integer(lastID);
        lastID = 0;

        return result;
    }


    //GET RESOURCES BY OBJECTS

    private BalancesAutoParts getBalanceByAutoParts(int idAutoParts){

        BalancesAutoParts balancesAutoParts = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(QueryToDB.GET_BALANCEAUTOPARTS_BY_AUTOPARTS_ID)){
            //"SELECT * FROM dbo_autoparts.balance_auto_parts WHERE autoparts_id = ?"
            preparedStatement.setInt(1, idAutoParts);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                balancesAutoParts = new BalancesAutoParts(shop);
                balancesAutoParts.setidBalancesAutoParts(resultSet.getInt("balance_id"));
                balancesAutoParts.setQty(resultSet.getInt("qty"));
                balancesAutoParts.setAutoParts(getByIdAutoParts(resultSet.getInt("autoparts_id")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return balancesAutoParts;
        }


    }

    private Prices getPriceByAutoPartsAndCategory(int idAutoParts, int idCategoryPrice){
        return null;
    }

    private AutoParts getAutoPartsByCatalogNumber(String filterObject){

        AutoParts result = null;

        try (Statement statement = connection.createStatement()) {

            String query = QueryToDB.GET_AUTOPART_BY_CATALOGNUMBER.replace("?", "'" + filterObject + "'");
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                result = new AutoParts(shop);
                result.setId(resultSet.getInt("id"));
                result.setName(resultSet.getString("name"));
                result.setCatalogNumber(resultSet.getString("catalognumber"));
                result.setCategoriya(Category.getById(resultSet.getInt("categoriya")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            return result;
        }

    }

    @Override
    public Connection getConnection() throws PropertyVetoException, SQLException {
        return connection;
    }
	
	public Connection getNewConnection()  throws PropertyVetoException, SQLException  {
		return connectionSingleton.getConnection();
	}

    public String getNewNumDocument(Document docum){

        String result = "";

        if (docum instanceof Shopping) {

            result = "SP-";

            try{
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("Select count(shopping.id) from dbo_autoparts.docs_shopping as shopping");

                if (resultSet.next()) {
                    result += String.valueOf(resultSet.getInt(1));
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        } else if (docum instanceof Sale) {

            result = "SL-";

            try{
                Statement statement = connection.createStatement();

                ResultSet resultSet = statement.executeQuery("Select count(sale.id) from dbo_autoparts.docs_sales as sale");

                if (resultSet.next()) {
                    result += String.valueOf(resultSet.getInt(1));
                }


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    public static String getURL() {
        return URL;
    }

    public static String getPASS() {
        return PASS;
    }

    public static String getNAME() {
        return NAME;
    }
}
