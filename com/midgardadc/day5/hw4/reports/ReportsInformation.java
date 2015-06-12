package com.midgardadc.day5.hw4.reports;

import com.midgardadc.day5.hw4.inforamation.Prices;
import com.midgardadc.day5.hw4.reference.AutoParts;


/*
 * klas dlya polu4eniya dannuh from tables Information
 * extends Reports
 */
public class ReportsInformation extends Reports{
	
	/*
	 * Print price for good
	 */
	public void printPriceForGoods(){
		
		Prices [] selection = getDb().getPrices();
		
		for (Prices prices : selection) {
			int idAutoParts = prices.getIdAutoParts();
			AutoParts autoParts = new AutoParts().getObjectById(idAutoParts);
			
			System.out.println("Tovar " + autoParts.getName() + ", price " + prices.getPrise());
			
		}		
		
	}

}
