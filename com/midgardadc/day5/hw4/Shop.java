package com.midgardadc.day5.hw4;

import com.midgardadc.day5.hw4.documents.Sale;
import com.midgardadc.day5.hw4.documents.Shopping;
import com.midgardadc.day5.hw4.enums.Category;
import com.midgardadc.day5.hw4.enums.CategoryPrice;
import com.midgardadc.day5.hw4.inforamation.Prices;
import com.midgardadc.day5.hw4.reference.AutoParts;
import com.midgardadc.day5.hw4.reference.Client;
import com.midgardadc.day5.hw4.reports.ReportsBalances;
import com.midgardadc.day5.hw4.reports.ReportsDocuments;
import com.midgardadc.day5.hw4.reports.ReportsInformation;
import com.midgardadc.day5.hw4.reports.ReportsReference;


public class Shop { 
	
	private static DataBase db;
	
	/* Method: Shop()
	 * Parameters:
	 * 		null
	 * Return value:
	 * 		null
	 * Use:
	 * 		Konstruktor klassa. Inicializiruet massivi i zapolnyaet dannimi
	 */
	public Shop(){		
		
	}            
	
	/*
	 * Inicialisaciya magasina
	 */
	public void initialisation(){
		db = new DataBase();
		db.inicialisation();
		
		createAutoPartsAndPrices();
		
		createClients();
		
		createShops();
				
		cerateSales();
		
		createReports();
	}

	private void createReports() {
		ReportsInformation reportInf = new ReportsInformation();
		reportInf.printPriceForGoods();
		
		System.out.println("******************************");
		
		ReportsBalances reportBal = new ReportsBalances();
		reportBal.currentBalancesAutoParts();
		
		System.out.println("******************************");
		
		ReportsDocuments reportsDocuments = new ReportsDocuments();
		reportsDocuments.qtySaleForDay(7);
		
		System.out.println("******************************");
		reportsDocuments.TransactionListForDay(getCurrentDate() - 4);
		
		System.out.println("******************************");
		reportsDocuments.totalTransactionListForDay(getCurrentDate() - 4);
		
		System.out.println("******************************");
		ReportsReference reportsReference = new ReportsReference();
		reportsReference.printAutoPartsByCategory(Category.ELECTROOBORUDOVANIE);
		
	}

	private void createShops() {
		
		Shopping documShop;
		AutoParts autoParts;
		int idxAutoParts;
		
		documShop = new Shopping();
		idxAutoParts = 0;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documShop.setIdClient(0);
		documShop.setIdAutoParts(idxAutoParts);
		documShop.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documShop.setQty(10);
		documShop.setDate(getCurrentDate() - 10);
		documShop.Save();
		
		documShop = new Shopping();		
		idxAutoParts = 1;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documShop.setIdClient(2);
		documShop.setIdAutoParts(idxAutoParts);
		documShop.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documShop.setQty(10);
		documShop.setDate(getCurrentDate() - 10);
		documShop.Save();
		
		documShop = new Shopping();
		idxAutoParts = 4;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documShop.setIdClient(3);
		documShop.setIdAutoParts(idxAutoParts);
		documShop.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documShop.setQty(5);
		documShop.Save();
		
		documShop = new Shopping();
		idxAutoParts = 4;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documShop.setIdClient(3);
		documShop.setIdAutoParts(idxAutoParts);
		documShop.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documShop.setQty(5);
		documShop.Save();
		
		documShop = new Shopping();
		idxAutoParts = 5;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documShop.setIdClient(3);
		documShop.setIdAutoParts(idxAutoParts);
		documShop.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documShop.setQty(10);
		documShop.Save();
		
		
	}

	/*
	 * Napolnaet DB Sales
	 */
	private void cerateSales() {
		
		Sale documSale;
		AutoParts autoParts;
		int idxAutoParts;
		
		documSale = new Sale();
		idxAutoParts = 1;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documSale.setIdClient(2);
		documSale.setIdAutoParts(idxAutoParts);
		documSale.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documSale.setQty((byte) 2);
		documSale.setDate(getCurrentDate() - 5);
		documSale.Save();
		
		documSale = new Sale();
		idxAutoParts = 2;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documSale.setIdClient(1);
		documSale.setIdAutoParts(idxAutoParts);
		documSale.setCena(250);
		documSale.setQty((byte) 1);
		documSale.setDate(getCurrentDate() - 4);
		documSale.Save();
		
		documSale = new Sale();
		idxAutoParts = 5;
		autoParts = new AutoParts().getObjectById(idxAutoParts);
		documSale.setIdClient(1);
		documSale.setIdAutoParts(idxAutoParts);
		documSale.setCena(new Prices().getPriceByGoods(autoParts, CategoryPrice.ROZNITSA));
		documSale.setQty((byte) 3);
		documSale.setDate(getCurrentDate() - 4);
		documSale.Save();
		
		//documSale = new Sale();
		
		
	}
	
	/*
	 * Napolnaet DB Clients
	 */
	private void createClients() {
		
		Client client = new Client();
		client.setName("FOP Stepanyuk Avtotranport");
		client.Save();
		
		client = new Client();
		client.setName("OOO Agro Capital Managment");
		client.Save();
		
		client = new Client();
		client.setName("PAT Ukrtelekom");
		client.Save();
		
	}

	/*
	 * Napolnaet DB AutoParts
	 */
	private void createAutoPartsAndPrices() {
		
		AutoParts ap = new AutoParts();
		ap.setCatalogNumber("450 05 02");
		ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
		ap.setName("Akkumulyator 50 A/Ch Evro");
		ap.Save();
		Prices prc = new Prices();
		prc.setPrice(ap, (float)1000.50, CategoryPrice.ROZNITSA);
		prc = new Prices();
		prc.setPrice(ap, (float)1100.00, CategoryPrice.ROZNITSA);
		
		ap = new AutoParts();
		ap.setCatalogNumber("1987302031");
		ap.setCategoriya(Category.ELECTROOBORUDOVANIE);
		ap.setName("Lamp H3 12V 55W Pure Light");
		ap.Save();
		prc = new Prices();
		prc.setPrice(ap, (float)43.26, CategoryPrice.ROZNITSA);
		
		ap = new AutoParts(); 
		ap.setCatalogNumber("24312-23001-01");
		ap.setCategoriya(Category.DVIGATEL);
		ap.setName("Remen GRM 111SP254H (DAYCO 111SP254H)");
		ap.Save();
		prc = new Prices();
		prc.setPrice(ap, (float)109.50, CategoryPrice.ROZNITSA);
		
		ap = new AutoParts(); 
		ap.setCatalogNumber("55361-17630");
		ap.setCategoriya(Category.HODOVAYA);
		ap.setName("Amortisator zadnij right");
		ap.Save();
		prc = new Prices();
		prc.setPrice(ap, (float)450.00, CategoryPrice.ROZNITSA);
		
		ap = new AutoParts(); 
		ap.setCatalogNumber("GTX 10W-40 4ë");
		ap.setCategoriya(Category.OIL);
		ap.setName("Motor Castrol GTX 10W-40 A3/B4");
		ap.Save();
		prc = new Prices();
		prc.setPrice(ap, (float)750.00, CategoryPrice.ROZNITSA);
		
		ap = new AutoParts(); 
		ap.setCatalogNumber("195/60R15 SP 01");
		ap.setCategoriya(Category.SHINA);
		ap.setName("Shina 195/60R15 88V SP Sport 01 Dunlop");
		ap.Save();
		prc = new Prices();
		prc.setPrice(ap, (float)1020.80, CategoryPrice.ROZNITSA);
		
		
		
	}
	
	//******************************************************************
	public int getCurrentDate(){
		return (int)(System.currentTimeMillis()/(1000*60*60*24));
	}

	//*******************************************************************
	//GETTERS AND SETTERS
	
	public DataBase getDb() {
		return db;
	}

	public void setDb(DataBase db) {
		this.db = db;
	}
	
	
	

}
