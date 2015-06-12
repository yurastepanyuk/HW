package com.midgardadc.day5.hw4.reference;

import com.midgardadc.day5.hw4.enums.Category;
import com.midgardadc.day5.hw4.inforamation.Prices;

/*
 * Soderzhit rekvisitu avtozap4astey i svoi metodu
 * Nasleduet Reference
 */

public class AutoParts extends Reference {
	
	private String catalogNumber;
	private Category categoriya;
	
	public AutoParts() {
		
	}
	
	
	/*
	 * otobrat' massiv AutoParts po trebuemoj Category
	 */
	public static AutoParts [] SelectByCategory(Category categoriya){
		return new AutoParts [0];
	}


	//*******************************************************************
	//GETTERS AND SETTERS
	
	/*
	 * search object by ID AutoParts
	 */
	public AutoParts getObjectById(int id){
		
		for (AutoParts element : getDb().getGoods()) {
			if (element.getId() == id) {
				return element;
			}
		}
		
		return null;
	}
		
	public String getCatalogNumber() {
		return catalogNumber;
	}


	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}


	public Category getCategoriya() {
		return categoriya;
	}


	public void setCategoriya(Category categoriya) {
		this.categoriya = categoriya;
	}
	
	

}
