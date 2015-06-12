package com.midgardadc.day5.hw4.documents;

import com.midgardadc.day5.hw4.reference.AutoParts;
import com.midgardadc.day5.hw4.reference.Client;


/*Otrazhaet fakt realizacii tovara
 * Hranit dannie o kliente, tovare, koli4estve, cene
 */
public class Sale extends Document {	
	
	private int idClient;
	private int idAutoParts;//AutoParts[] autoParts - v zhisni eto tabl 4ast dokumenta 
	private byte qty;
	private float cena;
	
	public Sale() {
		
	}
	
	
	
	//OBJECT METHODS***********************************************
	
	/*
	 * search object by ID Sale
	 */
	public Sale getObjectById(int id) {

		for (Sale element : getDb().getSale()) {
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

	public byte getQty() {
		return qty;
	}

	public void setQty(byte qty) {
		this.qty = qty;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}
	

}
