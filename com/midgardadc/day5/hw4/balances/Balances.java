package com.midgardadc.day5.hw4.balances;

import com.midgardadc.day5.hw4.Shop;

/*
 * Super class for all Balanses class
 * Klassi dannoy kategorii hranyat informaciyu o balance po raznim tablicam...
 */
public class Balances extends Shop {
	
	public void Save(){
		getDb().addNewRecord(this); 		
	}; 

}
