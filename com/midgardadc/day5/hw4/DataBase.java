package com.midgardadc.day5.hw4;

import java.util.Arrays;

import com.midgardadc.day5.hw4.balances.BalancesAutoParts;
import com.midgardadc.day5.hw4.documents.Document;
import com.midgardadc.day5.hw4.documents.Sale;
import com.midgardadc.day5.hw4.documents.Shopping;
import com.midgardadc.day5.hw4.inforamation.Agreement;
import com.midgardadc.day5.hw4.inforamation.Prices;
import com.midgardadc.day5.hw4.reference.AutoParts;
import com.midgardadc.day5.hw4.reference.Client;
import com.midgardadc.day5.hw4.reference.Reference;

//Soderzhit tables and methods rabotu s DB
public class DataBase {
	
	//Spravo4niki
	private AutoParts[] goods;
	private Client[] client;
	
	//Documentu and obs4iy journal transakcij
	private Shopping[] shop;
	private Sale[] sale;	
	private Document[] transaction;
	
	//Balances
	private BalancesAutoParts[] balancesAutoParts;
	
	//svedeniya
	private Prices[] prices;
	private Agreement[] agriment;
	
	public DataBase(){		

	}
	
	//**********************************************************************************
	//WORK WITH DB
	
	/*
	 * inicialisation tables
	 */
	public void inicialisation(){
		
		goods = new AutoParts[0];
		client = new Client[0]; 
		
		shop = new Shopping [0];
		sale = new Sale[0];
		transaction = new Document[0];
		
		balancesAutoParts = new BalancesAutoParts[0];
		
		prices = new Prices[0];
		
		agriment = new Agreement[0];
		
	}
	
	
	/*
	 * Add new object in tables
	 */
	public void addNewRecord(Object object){
		
		String typeObject = object.getClass().getSimpleName();
		
		addNewRecordInArray(typeObject);
		
		if (object instanceof AutoParts) {
			goods[goods.length - 1] = (AutoParts) object; 
		} else if (object instanceof Prices) {
			prices[prices.length - 1] = (Prices) object; 
		} else if (object instanceof Client) {
			client[client.length - 1] = (Client) object; 
		} else if (object instanceof Shopping){
			shop[shop.length - 1] = (Shopping) object; 
		} else if (object instanceof BalancesAutoParts){
			balancesAutoParts[balancesAutoParts.length - 1] = (BalancesAutoParts) object; 
		} else if (object instanceof Sale) {
			sale[sale.length - 1] = (Sale) object; 
		}
		
		
		
	}
	
	
	
	//**********************************************************************************
	//SERVICE METHODS

	/* Method: addNewRecord(String nameTables)
	* Parameters:
	* 		String nameTable:	name of table
	* Return value:
	* 		int: Index of new record in array 
	* Use:
	* 		metod sozdaet new record in array and returns its index
	*/
	public int addNewRecordInArray(String typeObject){

		if (typeObject.equals("AutoParts")) {
			
			AutoParts [] newGoods = new AutoParts [goods.length + 1];
			System.arraycopy(goods, 0, newGoods, 0, goods.length);
			goods = newGoods;
			
		} else if (typeObject.equals("Prices")) {
			Prices [] newPrices = new Prices [prices.length + 1];
			System.arraycopy(prices, 0, newPrices, 0, prices.length);
			prices = newPrices;
		} else if (typeObject.equals("Client")){
			Client [] newClients = new Client [client.length + 1];
			System.arraycopy(client, 0, newClients, 0, client.length);
			client = newClients;
		} else if (typeObject.equals("Shopping")){
			
			Shopping [] newShopping = new Shopping [shop.length + 1];
			System.arraycopy(shop, 0, newShopping, 0, shop.length);
			shop = newShopping;
			
		} else if (typeObject.equals("BalancesAutoParts")){
			
			BalancesAutoParts [] newBalancesAutoParts = new BalancesAutoParts [balancesAutoParts.length + 1];
			System.arraycopy(balancesAutoParts, 0, newBalancesAutoParts, 0, balancesAutoParts.length);
			balancesAutoParts = newBalancesAutoParts;
			
		} else if (typeObject.equals("Sale")) {
			
			Sale [] newSale = new Sale [sale.length + 1];
			System.arraycopy(sale, 0, newSale, 0, sale.length);
			sale = newSale;
			
		}
		
		return 1;

	} 

	/*
	 * get new ID for object
	 */
	public int getNewId(Object object) {
		
		if (object instanceof AutoParts) {
			return goods.length; 
		} else if (object instanceof Client) {
			return client.length;
		} else if (object instanceof Shopping){
			return shop.length;
		} else if (object instanceof Sale){
			return sale.length;
		}
		
		return 0; 
	}
		
	//GETTERS AND SETTERS *******************************************************************

	public AutoParts[] getGoods() {
		return goods;
	}

	public Client[] getClient() {
		return client;
	}

	public Shopping[] getShop() {
		return shop;
	}

	public Sale[] getSale() {
		return sale;
	}

	public Document[] getTransaction() {
		return transaction;
	}

	public Prices[] getPrices() {
		return prices;
	}

	public Agreement[] getAgriment() {
		return agriment;
	} 
	
	public BalancesAutoParts[] getBalancesAutoParts() {
		return balancesAutoParts;
	}	
	

}
