package shop.database;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import shop.Shop;
import shop.balances.BalancesAutoParts;
import shop.documents.Sale;
import shop.documents.Shopping;
import shop.enums.Category;
import shop.enums.CategoryPrice;
import shop.inforamation.Prices;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.math.BigDecimal;
import java.sql.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class WorkWithMySQL implements DB  {

    Connection connection;
    Shop shop;

    private final static String URL     = "jdbc:mysql://localhost:3306/dbo_autoparts";
    private final static String NAME    = "sa";
    private final static String PASS    = "sasa";

    public WorkWithMySQL(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void inicialisation() throws SQLException {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection = DriverManager.getConnection(URL, NAME, PASS);
        } catch (SQLException e) {
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
    public void addNewRecord(Object object) {
        String nameTable = object.getClass().getSimpleName();

        if (nameTable.equals("AutoParts")) {
            addNewAutoParts((AutoParts)object);
        } else if (nameTable.equals("Client")){
            addNewClient((Client)object);
        } else if (nameTable.equals("Shopping")){
            addNewShopping((Shopping)object);
        } else if (nameTable.equals("Sale")){
            addNewSale((Sale) object);
        } else if (nameTable.equals("BalancesAutoParts")){
            addNewBalancesAutoParts((BalancesAutoParts) object);
        } else if (nameTable.equals("Prices")){
            addNewPrices((Prices) object);
        }

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
            updateRecordBalancesAutoParts((BalancesAutoParts) object);
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
		
		}
		
		return null;
	
	}

    //GET ALL

    private List<Prices> getAllDataOnPrices() {
//        Statement statement = null;
//        Statement statementAutoParts = null;
        List<Prices> resultList = new ArrayList<>();

        try(Statement statement = getConnection().createStatement(); Statement statementAutoParts = connection.createStatement();) {

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
        return null;
    }

    private List<Sale> getAllDataOnSale() {
        return null;
    }

    private List<Shopping> getAllDataOnShopping() {
        return null;
    }

    private List<Client> getAllDataOnClient() {

        List<Client> resultList = new ArrayList<>();

        try(Statement statement = getConnection().createStatement()) {

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

        try(Statement statement = getConnection().createStatement()) {

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

    public void addNewAutoParts(AutoParts autoParts) {
//        PreparedStatement statement = null;

        try(PreparedStatement statement = connection.prepareStatement(QueryToDB.ADD_NEW_AUTOPARTS);) {

            statement.setString(1, autoParts.getName());
            statement.setString(2, autoParts.getCatalogNumber());
            statement.setInt(3, autoParts.getCategoriya().getID());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
//            try {
//                statement.close();
//            } catch (SQLException e) {
//                //
//            }
        }

    }

    public void addNewPrices(Prices prices) {

    }

    public void addNewClient(Client client) {

        try(PreparedStatement statement = connection.prepareStatement(QueryToDB.ADD_NEW_CLIENT);
            Statement statementChek = connection.createStatement()){

            ResultSet resultSetCheck = statementChek.executeQuery((QueryToDB.GET_BY_NAME_CLIENT).replace("?", "'" + client.getName() + "'"));

            while (resultSetCheck.next()){
                throw new SQLException();
            }

            statement.setString(1, client.getName());
            if (!(client.getInn() == 0)) {
                statement.setInt(2, client.getInn());
            } else {
                statement.setNull(2,java.sql.Types.INTEGER);
            }

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return;
        } finally {
            //
        }
    }

    public void addNewShopping(Shopping shopping) {

    }

    public void addNewBalancesAutoParts(BalancesAutoParts balancesAutoParts) {

    }

    public void addNewSale(Sale sale) {

    }


    //UPDATE

    public void updateRecordPrices(Prices prices) {

    }

    public void updateRecordClient(Client client) {

    }

    public void updateRecordShopping(Shopping shopping) {

    }

    public void updateRecordBalancesAutoParts(BalancesAutoParts balancesAutoParts) {

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
        return null;
    }


    public Prices getByIdPrices(int id) {
        return null;
    }


    public Client getByIdClient(int id) {
        return null;
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
        return 0;
    }


    //GET RESOURCES BY OBJECTS

    private BalancesAutoParts getBalanceByAutoParts(int idAutoParts){
        return null;
    }

    private Prices getPriceByAutoPartsAndCategory(int idAutoParts, int idCategoryPrice){
        return null;
    }


    @Override
    public Connection getConnection() {
        return connection;
    }
}
