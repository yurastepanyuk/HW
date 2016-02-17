package shop;

import shop.database.DB;
import shop.database.WorkWithDerby;
import shop.database.WorkWithLists;
import shop.database.WorkWithMySQL;
import shop.documents.Sale;
import shop.documents.ServiceMethodDocument;
import shop.documents.Shopping;
import shop.enums.Category;
import shop.enums.CategoryPrice;
import shop.inforamation.Prices;
import shop.inforamation.ServiceMethodInformation;
import shop.reference.AutoParts;
import shop.reference.Client;
import shop.reference.ServiceMethodReference;
import shop.reports.ReportsBalances;
import shop.reports.ReportsDocuments;
import shop.reports.ReportsInformation;
import shop.reports.ReportsReference;

import java.sql.SQLException;


public class Shop { 
	
	private static DB db;
	private ServiceMethodReference 	 serviceMethodReference;
	private ServiceMethodDocument  	 serviceMethodDocument;
	private ServiceMethodInformation serviceMethodInformation;

	private static Shop instance;

	public static Shop getInstance(){

		if (instance == null) {
			instance = new Shop();
			instance.initialisation();
		} else {
			db.setNewConnection();
		}

		return instance;
	}

	/* Method: Shop()
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		null
	 * Use:
	 * 		Konstruktor klassa. Inicializiruet massivi i zapolnyaet dannimi
	 */
	public Shop(){

		serviceMethodReference   = new ServiceMethodReference(this);
		serviceMethodDocument    = new ServiceMethodDocument(this);
		serviceMethodInformation = new ServiceMethodInformation(this);
	}

	public void initialisation(){

		try {

			throw new SQLException();
//			db = new WorkWithMySQL(this);
//			db.inicialisation();


		} catch (SQLException e) {

			try{
				db = new WorkWithDerby(this);
				db.inicialisation();
			} catch (SQLException e1) {
				db = new WorkWithLists();
				try {
					db.inicialisation();
				} catch (SQLException e2) {
					e1.printStackTrace();
				}
			}

		}

		initialisationData();

	}

	private void initialisationData(){

		createAutoPartsAndPrices();

		createClients();

		createShops();

		cerateSales();

		createReports();
	}

	private void createReports() {
		ReportsInformation reportInf = new ReportsInformation();
		reportInf.setShop(this);
		reportInf.printPriceForGoods();

		System.out.println("******************************");

		ReportsBalances reportBal = new ReportsBalances();
		reportBal.setShop(this);
		reportBal.currentBalancesAutoParts();

		System.out.println("******************************");

		ReportsDocuments reportsDocuments = new ReportsDocuments();
		reportsDocuments.setShop(this);
		reportsDocuments.qtySaleForDay(7);

		System.out.println("******************************");
		reportsDocuments.transactionListForDay(getCurrentDate()-1);

		System.out.println("******************************");
		reportsDocuments.totalTransactionListForDay(getCurrentDate()-1);

		System.out.println("******************************");
		ReportsReference reportsReference = new ReportsReference();
		reportsReference.setShop(this);
		reportsReference.printAutoPartsByCategory(Category.ELECTROOBORUDOVANIE);

	}

	private void createShops() {

		Shopping 	documShop;
		AutoParts 	autoParts;
		Client 		client;
		int 		idxAutoParts;
		float	  	cena;

		autoParts = serviceMethodReference.getReferenceObjectById(1, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(1, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documShop = serviceMethodDocument.newShopping(client, autoParts, 8, cena);
		documShop.setDate(documShop.getDate() - 10);

		autoParts = serviceMethodReference.getReferenceObjectById(2, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(2, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documShop = serviceMethodDocument.newShopping(client, autoParts, 10, cena);
		documShop.setDate(documShop.getDate() - 10);

		autoParts = serviceMethodReference.getReferenceObjectById(5, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(2, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documShop = serviceMethodDocument.newShopping(client, autoParts, 5, cena);

		autoParts = serviceMethodReference.getReferenceObjectById(5, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(2, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documShop = serviceMethodDocument.newShopping(client, autoParts, 5, cena);

		autoParts = serviceMethodReference.getReferenceObjectById(5, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(2, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documShop = serviceMethodDocument.newShopping(client, autoParts, 10, cena);

	}

	/*
	 * Napolnaet DB Sales
	 */
	private void cerateSales() {

		Sale 	  documSale;
		Client 	  client;
		AutoParts autoParts;
		int 	  idxAutoParts;
		float	  cena;

		autoParts = serviceMethodReference.getReferenceObjectById(1, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(2, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documSale = serviceMethodDocument.newSale(client, autoParts, (byte)2, cena, true);
		documSale.setDate(documSale.getDate() - 5);

		autoParts = serviceMethodReference.getReferenceObjectById(2, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(1, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documSale = serviceMethodDocument.newSale(client, autoParts, (byte)1, cena, true);
		documSale.setDate(documSale.getDate() - 4);

		autoParts = serviceMethodReference.getReferenceObjectById(5, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(1, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documSale = serviceMethodDocument.newSale(client, autoParts, (byte)3, cena, true);
		documSale.setDate(documSale.getDate() - 4);

		autoParts = serviceMethodReference.getReferenceObjectById(4, AutoParts.class);
		client    = serviceMethodReference.getReferenceObjectById(1, Client.class);
		cena	  = serviceMethodInformation.getPriceByGoods(autoParts, CategoryPrice.ROZNITSA);
		documSale = serviceMethodDocument.newSale(client, autoParts, (byte)1, cena, true);
		documSale.setDate(documSale.getDate() - 100);

	}

	/*
	 * Napolnaet DB Clients
	 */
	private void createClients() {

		Client client = new Client(this);
		client.setName("FOP Stepanyuk Avtotransport");
		client.save();

		client = new Client(this);
		client.setName("OOO Agro Capital Managment");
		client.save();

		client = new Client(this);
		client.setName("PAT Ukrtelekom");
		client.save();

	}

	private void createAutoPartsAndPrices() {

		AutoParts ap = new AutoParts(this);
		Prices prc;

		ap.setShop(this);
		ap.setCatalogNumber("450 05 02");
		ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
		ap.setName("Akkumulyator 50 A/Ch Evro");
		ap.save();

		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float)1000.50, CategoryPrice.ROZNITSA);
			prc = new Prices(this);
			prc.setPrice(ap, (float)1100.00, CategoryPrice.ROZNITSA);
		}

		ap = new AutoParts(this);
		ap.setShop(this);
		ap.setCatalogNumber("1987302031");
		ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
		ap.setName("Lamp H3 12V 55W Pure Light");
		ap.save();
		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float) 43.26, CategoryPrice.ROZNITSA);
		}

		ap = new AutoParts(this);
		ap.setShop(this);
		ap.setCatalogNumber("24312-23001-01");
		ap.setCategoriya(Category.DVIGATEL);
		ap.setName("Remen GRM 111SP254H (DAYCO 111SP254H)");
		ap.save();
		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float) 109.50, CategoryPrice.ROZNITSA);
		}

		ap = new AutoParts(this);
		ap.setShop(this);
		ap.setCatalogNumber("55361-17630");
		ap.setCategoriya(Category.HODOVAYA);
		ap.setName("Amortisator zadnij right");
		ap.save();
		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float) 450.00, CategoryPrice.ROZNITSA);
		}

		ap = new AutoParts(this);
		ap.setShop(this);
		ap.setCatalogNumber("GTX 10W-40 4 l");
		ap.setCategoriya(Category.OIL);
		ap.setName("Motor Castrol GTX 10W-40 A3/B4");
		ap.save();
		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float) 750.00, CategoryPrice.ROZNITSA);
		}

		ap = new AutoParts(this);
		ap.setShop(this);
		ap.setCatalogNumber("195/60R15 SP 01");
		ap.setCategoriya(Category.SHINA);
		ap.setName("Shina 195/60R15 88V SP Sport 01 Dunlop");
		ap.save();
		if (ap.getId() != 0) {
			prc = new Prices(this);
			prc.setPrice(ap, (float) 1020.80, CategoryPrice.ROZNITSA);
		}

	}

	//******************************************************************
	public int getCurrentDate(){
		return (int)(System.currentTimeMillis()/(1000*60*60*24));
	}

	//*******************************************************************
	//GETTERS AND SETTERS

	public DB getDb() {
		return db;
	}

	public void setDb(DB db) {
		this.db = db;
	}

	public ServiceMethodReference getServiceMethodReference() {
		return serviceMethodReference;
	}

	public ServiceMethodDocument getServiceMethodDocument() {
		return serviceMethodDocument;
	}

	public ServiceMethodInformation getServiceMethodInformation() {
		return serviceMethodInformation;
	}
}
