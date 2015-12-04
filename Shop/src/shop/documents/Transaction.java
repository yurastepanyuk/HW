package shop.documents;

import shop.Shop;


/*
 * Sodergit dannie o transakcii: ID document, date document, vid document, sluzhebnie fields
 */
public class Transaction extends Shop {
	
	private 	int 	idTransaction;
	private 	int 	dateTransaction;
	private 	String 	vidDocum;

	protected 	Shop 	shop;

	public Transaction(Shop shop) {
		this.shop = shop;
	}
	
	public void save(){
		
	}

}
