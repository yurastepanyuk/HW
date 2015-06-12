package com.midgardadc.day5.hw4.reference;


/*
 * Spravo4nik klientov
 * Extends Reference
 */

public class Client extends Reference {
	
	public Client() {
		
	}

	
	//*******************************************************************
	//GETTERS AND SETTERS
	
	/*
	 * search object by ID Client
	 */
	public Client getObjectById(int id){
		
		for (Client element : getDb().getClient()) {
			if (element.getId() == id) {
				return element;
			}
		}
		
		return null;
	}
		
}

