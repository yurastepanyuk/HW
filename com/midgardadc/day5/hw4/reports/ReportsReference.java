package com.midgardadc.day5.hw4.reports;

import com.midgardadc.day5.hw4.enums.Category;
import com.midgardadc.day5.hw4.reference.AutoParts;

/*
 * klas dlya polu4eniya dannuh from tables Reference
 * extends Reports
 */
public class ReportsReference extends Reports {
	
	public ReportsReference() {
		// TODO Auto-generated constructor stub
	}
	
	public void printAutoPartsByCategory(Category category){
		
		System.out.println("Print AutoParts by category " + category);
		
		AutoParts[] autoParts = getDb().getGoods();
		
		for(AutoParts element : autoParts){
			
			if(element.getCategoriya().equals(category)){
				
				System.out.println(element.getName());
				
			}
			
		}
		
	}

}
