package shop.reports;

import shop.inforamation.Prices;
import shop.reference.AutoParts;
import shop.reports.*;

import java.util.List;


/*
 * klas dlya polu4eniya dannuh from tables Information
 * extends Reports
 */
public class ReportsInformation extends Reports {
	
	/*
	 * Print price for good
	 */
	public void printPriceForGoods(){

		List<Prices> pricesList = (List<Prices>) shop.getDb().getDataFromTable(Prices.class);
		
		for (Prices prices : pricesList) {
			int idAutoParts = prices.getIdAutoParts();
			//AutoParts autoParts = new AutoParts(shop).getObjectById(idAutoParts);
			AutoParts autoParts = serviceMethodReference.getReferenceObjectById(idAutoParts, AutoParts.class);

			System.out.println("Tovar " + autoParts.getName() + ", price " + prices.getPrise());
			
		}		
		
	}

}
