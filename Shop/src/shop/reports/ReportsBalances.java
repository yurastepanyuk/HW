package shop.reports;

import shop.balances.BalancesAutoParts;
import shop.reference.AutoParts;

import java.util.List;

public class ReportsBalances extends Reports {
	
	public ReportsBalances() {
		
	}	
	
	public void currentBalancesAutoParts() {

		List<BalancesAutoParts> balancesAutoPartses = (List<BalancesAutoParts>) shop.getDb().getDataFromTable(BalancesAutoParts.class);
		
		for (BalancesAutoParts balancesAutoParts : balancesAutoPartses) {
			int idAutoParts = balancesAutoParts.getIdAutoParts();
			
			//AutoParts autoParts = new AutoParts(shop).getObjectById(idAutoParts);
			AutoParts autoParts = serviceMethodReference.getReferenceObjectById(idAutoParts, AutoParts.class);
			
			System.out.println("Tovar " + autoParts.getName() + ", oststok " + balancesAutoParts.getQty());
			
		}
		
	}

}
