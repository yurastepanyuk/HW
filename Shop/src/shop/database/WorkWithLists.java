package shop.database;

import shop.balances.BalancesAutoParts;
import shop.documents.Document;
import shop.documents.Sale;
import shop.documents.Shopping;
import shop.inforamation.Agreement;
import shop.inforamation.Prices;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

//Soderzhit tables and methods rabotu s DB
public class WorkWithLists implements DB {
	
	//Spravo4niki
	private List<AutoParts>	products;
	private List<Client>	clients;
	
	//Documentu and obs4iy journal transakcij
	private List<Shopping>	shops;
	private List<Sale>		sales;
	private List<Document>	transactions;
	
	//Balances
	private List<BalancesAutoParts>	balancesAutoPartss;
	
	//svedeniya
	private List<Prices>	pricess;
	private List<Agreement>	agriments;
	
	public WorkWithLists(){

	}
	
	//**********************************************************************************
	//WORK WITH DB
	
	/*
	 * inicialisation tables
	 */
	public void inicialisation(){

		products = new ArrayList<>();
		clients	 = new ArrayList<>();
		
		shops		 = new ArrayList<>();
		sales		 = new ArrayList<>();
		transactions = new ArrayList<>();
		
		balancesAutoPartss = new ArrayList<>();
		
		pricess	= new ArrayList<>();
		
		agriments = new ArrayList<>();
		
	}
	
	/*
	 * Add new object in tables
	 */
	public void addNewRecord(Object object){

		if (object instanceof AutoParts) {
			products.add((AutoParts) object);
		} else if (object instanceof Prices) {
			pricess.add((Prices) object);
		} else if (object instanceof Client) {
			clients.add((Client) object);
		} else if (object instanceof Shopping){
			shops.add((Shopping) object);
		} else if (object instanceof BalancesAutoParts){
			balancesAutoPartss.add((BalancesAutoParts) object);
		} else if (object instanceof Sale) {
			sales.add((Sale) object);
		}		
		
	}

	/**
	 * get new ID for object
	 */
	public int getNewId(Object object) {
		
		if (object instanceof AutoParts) {
			return products.size();
		} else if (object instanceof Client) {
			return clients.size();
		} else if (object instanceof Shopping){
			return shops.size();
		} else if (object instanceof Sale){
			return sales.size();
		}
		
		return 0;
	}
		
	//GETTERS AND SETTERS *******************************************************************

	public <T> List<?> getDataFromTable(Class<T> cls){

		String nameTable = cls.getSimpleName();

		if (nameTable.equals("AutoParts")) {
			return products;
		} else if (nameTable.equals("Client")){
			return clients;
		} else if (nameTable.equals("Shopping")){
			return shops;
		} else if (nameTable.equals("Sale")){
			return sales;
		} else if (nameTable.equals("Document")){
			return transactions;
		} else if (nameTable.equals("BalancesAutoParts")){
			return balancesAutoPartss;
		} else if (nameTable.equals("Prices")){
			return pricess;
		} else if (nameTable.equals("Agreement")){
			return agriments;
		}

		return new ArrayList<T>();
	}


	@Override
	public void updateRecord(Object object) {

	}

	@Override
	public void deleteRecord(Object object) {

	}

	@Override
	public <T> T getObjectById(int id, Class<T> cls) {
		return null;
	}

	@Override
	public <T> T getObjectByName(String name, Class<T> cls) {
		return null;
	}


	@Override
	public <T> T getResourceByObject(Object[] object, Class<T> clsTypeResourceReturn) {
		return null;
	}

	@Override
	public Connection getConnection() {
		return null;
	}
}
