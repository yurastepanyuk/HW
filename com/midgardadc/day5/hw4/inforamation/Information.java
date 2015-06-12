package com.midgardadc.day5.hw4.inforamation;

import com.midgardadc.day5.hw4.Shop;

/*
 * Super class for all Information class
 * Klassi dannoy kategorii hranyat informaciyu dla raznih ibjectot: cenu po kategoriyam, dogovora, harakteristiki tovarov...
 */
public class Information extends Shop {
	
	private String typeInfo;
		
	public void Save(){
		getDb().addNewRecord(this); 		
	}; 

	
}
