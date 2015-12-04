package shop.reports;

import shop.enums.Category;
import shop.reference.AutoParts;
import shop.reports.*;

import java.util.List;

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

		List<AutoParts> autoParts = (List<AutoParts>) shop.getDb().getDataFromTable(AutoParts.class);

		//AutoParts[] autoParts = shop.getDb().getGoods();
		
		for(AutoParts element : autoParts){
			
			if(element.getCategoriya().equals(category)){
				
				System.out.println(element.getName());
				
			}
			
		}
		
	}

}
