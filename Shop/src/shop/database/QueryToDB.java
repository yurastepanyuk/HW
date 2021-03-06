package shop.database;

public interface QueryToDB {

	//MYSQL

    public static final String GET_ALL_DATA_ON_AUTOPARTS           = "SELECT id, name, catalognumber, categoriya FROM dbo_autoparts.autoparts";
    public static final String GET_ALL_DATA_ON_CLIENT              = "SELECT id, name, inn FROM dbo_autoparts.clients";
    public static final String GET_ALL_DATA_ON_SHOPPING            = "SELECT dokTitle.id,dokTitle.date,dokTitle.doc_num,dokTitle.clients_id,lineShop.idshopping_line,lineShop.qty,lineShop.price,lineShop.docs_shopping_id,lineShop.autoparts_id FROM dbo_autoparts.docs_shopping AS dokTitle LEFT JOIN dbo_autoparts.shopping_line AS lineShop ON dokTitle.id = lineShop.docs_shopping_id";
    public static final String GET_ALL_DATA_ON_SALE                = "SELECT TitleSales.id, TitleSales.date, TitleSales.doc_num, TitleSales.clients_id, Line.idsales_line, Line.docs_sales_id, Line.autoparts_id, Line.qty, Line.price FROM dbo_autoparts.docs_sales AS TitleSales LEFT JOIN dbo_autoparts.sales_line AS Line ON TitleSales.id = Line.docs_sales_id";
    public static final String GET_ALL_DATA_ON_BALANCEAUTOPARTS    = "SELECT balance_id, autoparts_id, qty FROM dbo_autoparts.balance_auto_parts";
    public static final String GET_ALL_DATA_ON_PRICES              = "SELECT price_id, autoparts_id, kategoriya_cena, price FROM dbo_autoparts.prices";

    public static final String ADD_NEW_AUTOPARTS         = "INSERT INTO dbo_autoparts.autoparts (name,catalognumber,categoriya) VALUES (?,?,?)";
    public static final String ADD_NEW_CLIENT            = "INSERT INTO dbo_autoparts.clients (name,inn) VALUES (?,?)";
    public static final String ADD_NEW_PRICES            = "INSERT INTO dbo_autoparts.prices (price,kategoriya_cena,autoparts_id) VALUES (?,?,?)";
    public static final String ADD_NEW_BALANCEAUTOPARTS  = "INSERT INTO dbo_autoparts.balance_auto_parts (qty,autoparts_id) VALUES (?,?)";
	public static final String ADD_NEW_SHOPPING			 = "INSERT INTO dbo_autoparts.docs_shopping (date, doc_num, clients_id) VALUES (?,?,?)";
	public static final String ADD_NEW_SHOPPING_LINE	 = "INSERT INTO dbo_autoparts.shopping_line (qty, price, docs_shopping_id, autoparts_id) VALUES (?,?,?,?)";
	public static final String ADD_NEW_SALE			 	 = "INSERT INTO dbo_autoparts.docs_sales (date, doc_num, clients_id) VALUES (?,?,?)";
	public static final String ADD_NEW_SALE_LINE	 	 = "INSERT INTO dbo_autoparts.sales_line (qty, price, docs_sales_id, autoparts_id) VALUES (?,?,?,?)"; 

	public static final String UPDATE_AUTOPARTS          = "UPDATE dbo_autoparts.autoparts SET name = ?,catalognumber = ?,categoriya = ? WHERE id = ?";
    public static final String UPDATE_CLIENT             = "UPDATE dbo_autoparts.clients SET name = ?,inn = ? WHERE id = ?";
    public static final String UPDATE_PRICES             = "UPDATE dbo_autoparts.prices SET price = ?,kategoriya_cena =?,autoparts_id = ? WHERE price_id = ?";
    public static final String UPDATE_BALANCEAUTOPARTS   = "UPDATE dbo_autoparts.balance_auto_parts SET qty = ?,autoparts_id = ? WHERE autoparts_id = ?";
	public static final String UPDATE_SHOPPING			 = "UPDATE dbo_autoparts.docs_shopping SET date = ?, doc_num =?, clients_id = ? WHERE id = ?";
	public static final String UPDATE_SHOPPING_LINE	 	 = "UPDATE dbo_autoparts.shopping_line SET qty = ?, price = ?, docs_shopping_id = ?, autoparts_id ? WHERE idshopping_line = ?";
	public static final String UPDATE_SALE			 	 = "UPDATE dbo_autoparts.docs_sales SET date = ?, doc_num = ?, clients_id =? WHERE id = ?";
	public static final String UPDATE_SALE_LINE	 	 	 = "UPDATE dbo_autoparts.sales_line SET qty = ?, price = ?, docs_sales_id = ?, autoparts_id = ? WHERE idsales_line =?"; 

	public static final String GET_BY_ID_AUTOPARTS         = "SELECT * FROM dbo_autoparts.autoparts WHERE id = ?";
    public static final String GET_BY_ID_CLIENT            = "SELECT * FROM dbo_autoparts.clients WHERE id = ?";
    public static final String GET_BY_ID_PRICES            = "SELECT * FROM dbo_autoparts.prices WHERE price_id = ?";
    public static final String GET_BY_ID_BALANCEAUTOPARTS  = "SELECT * FROM dbo_autoparts.balance_auto_parts WHERE autoparts_id = ?";
	public static final String GET_BY_ID_SHOPPING		   = "SELECT * FROM dbo_autoparts.docs_shopping AS dokTitle LEFT JOIN dbo_autoparts.shopping_line AS lineShop ON dokTitle.id = lineShop.docs_shopping_id WHERE dokTitle.id = ?";
	public static final String GET_BY_ID_SHOPPING_LINE	   = "SELECT * FROM dbo_autoparts.shopping_line WHERE idshopping_line = ?";
	public static final String GET_BY_ID_SALE			   = "SELECT * FROM dbo_autoparts.docs_sales AS TitleSales LEFT JOIN dbo_autoparts.sales_line AS Line ON TitleSales.id = Line.docs_sales_id WHERE TitleSales.id = ?";
	public static final String GET_BY_ID_SALE_LINE	 	   = "SELECT * FROM dbo_autoparts.sales_line WHERE idsales_line =?"; 

	public static final String GET_BY_NAME_AUTOPARTS         = "SELECT * FROM dbo_autoparts.autoparts WHERE name = ?";
    public static final String GET_BY_NAME_CLIENT            = "SELECT * FROM dbo_autoparts.clients WHERE name = ?";
	public static final String GET_BY_NAME_SHOPPING		     = "SELECT * FROM dbo_autoparts.docs_shopping AS dokTitle LEFT JOIN dbo_autoparts.shopping_line AS lineShop ON dokTitle.id = lineShop.docs_shopping_id WHERE dokTitle.doc_num = ?";
	public static final String GET_BY_NAME_SALE			     = "SELECT * FROM dbo_autoparts.docs_sales AS TitleSales LEFT JOIN dbo_autoparts.sales_line AS Line ON TitleSales.id = Line.docs_sales_id WHERE TitleSales.doc_num = ?";
	
	public static final String GET_PRICES_BY_AUTOPARTS_CATEGORY_ID   = "SELECT * FROM dbo_autoparts.prices WHERE autoparts_id = ? AND kategoriya_cena = ?";
	public static final String GET_BALANCEAUTOPARTS_BY_AUTOPARTS_ID  = "SELECT * FROM dbo_autoparts.balance_auto_parts WHERE autoparts_id = ?";
	public static final String GET_AUTOPART_BY_CATALOGNUMBER		 = "SELECT * FROM dbo_autoparts.autoparts WHERE catalognumber = ?";


	//DERBY

	public static final String CREATE_AUTOPARTS_DERBY = "CREATE TABLE AUTOPARTS " +
			"(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"name VARCHAR(100) NOT NULL, " +
			"catalognumber VARCHAR(100) NOT NULL, " +
			"categoriya INT, " +
			"PRIMARY KEY (id), " +
			"UNIQUE (catalognumber))";
	public static final String CREATE_IDX_AUTOPARTS_DERBY_CATALOGNUMBER = "CREATE UNIQUE INDEX APP.IDX_AUTOPARTSCATNUM ON APP.AUTOPARTS (CATALOGNUMBER)";

	public static final String CREATE_CLIENT_DERBY = "CREATE TABLE clients " +
			"(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"name VARCHAR(100) NOT NULL, " +
			"inn INT  DEFAULT NULL, " +
			"PRIMARY KEY (id))";
	public static final String CREATE_IDX_CLIENT_DERBY_INN = "CREATE INDEX APP.IDX_CLIENTINN_UNIQUE ON APP.CLIENTS (INN)";

	public static final String CREATE_DOCSSHOPPING_DERBY = "CREATE TABLE docs_shopping " +
			"(id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"date DATE NOT NULL, " +
			"doc_num VARCHAR(12), " +
			"clients_id INT NOT NULL, " +
			"PRIMARY KEY (id), " +
			"UNIQUE (doc_num))";
	public static final String CREATE_IDX_DOCSSHOPPING_DERBY_CLIENT = "CREATE UNIQUE INDEX APP.IDX_CLIENTID_UNIQUE ON APP.DOCS_SHOPPING (CLIENTS_ID)";

	public static final String CREATE_DOCSSALES_DERBY = "CREATE TABLE docs_sales " +
			"(id INT NOT NULL GENERATED ALWAYS AS IDENTITY, " + //(START WITH 1, INCREMENT BY 1)
			"date DATE NOT NULL, " +
			"doc_num VARCHAR(12), " +
			"clients_id INT NOT NULL, " +
			"PRIMARY KEY (id), " +
			"UNIQUE (doc_num))";

	public static final String CREATE_PRICES_DERBY = "CREATE TABLE prices " +
			"(price_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"price FLOAT, " +
			"kategoriya_cena SMALLINT NOT NULL, " +
			"autoparts_id INT NOT NULL, " +
			"PRIMARY KEY (price_id)) ";

	public static final String CREATE_BALANCE_AP_DERBY = "CREATE TABLE balance_auto_parts " +
			"(qty INT, " +
			"balance_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"autoparts_id INT, " +
			"PRIMARY KEY (balance_id)) ";


	public static final String CREATE_DOCSSALESLINE_DERBY = "CREATE TABLE sales_line " +
			"(idsales_line INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"qty SMALLINT, " +
			"price FLOAT, " +
			"docs_sales_id INT NOT NULL, " +
			"autoparts_id INT NOT NULL, " +
			"PRIMARY KEY (idsales_line)) ";

	public static final String CREATE_DOCSSHOPLINE_DERBY = "CREATE TABLE shopping_line " +
			"(idshopping_line INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1), " +
			"qty SMALLINT, " +
			"price FLOAT, " +
			"docs_shopping_id INT NOT NULL, " +
			"autoparts_id INT NOT NULL, " +
			"PRIMARY KEY (idshopping_line)) ";

}
