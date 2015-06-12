package com.midgardadc.day5.hw4.documents;

import com.midgardadc.day5.hw4.reference.AutoParts;


/*
 * Otrazhaet fact pokupki tovara
 * Hranit dannie o kliente, tovare, koli4estve, cene
 */
public class Shopping extends Document{
	
	private int idClient;
	private int idAutoParts;
	private int qty;
	private float cena;
	
	public Shopping() {
		
	}
	
	
	
	//OBJECT METHODS***********************************************
	
	/*
	 * search object by ID Shopping
	 */
	public Shopping getObjectById(int id) {

		for (Shopping element : getDb().getShop()) {
			if (element.getId() == id) {
				return element;
			}
		}

		return null;
	}
	
	//GETTERS AND SETTERS *****************************************
			
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
	}
	public int getIdAutoParts() {
		return idAutoParts;
	}
	public void setIdAutoParts(int idAutoParts) {
		this.idAutoParts = idAutoParts;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}
	

}
