package com.midgardadc.day5.hw4.reports;

import com.midgardadc.day5.hw4.balances.BalancesAutoParts;
import com.midgardadc.day5.hw4.reference.AutoParts;

public class ReportsBalances extends Reports{
	
	public ReportsBalances() {
		
	}	
	
	public void currentBalancesAutoParts() {
	
		BalancesAutoParts [] selection = getDb().getBalancesAutoParts();
		
		for (BalancesAutoParts balancesAutoParts : selection) {
			int idAutoParts = balancesAutoParts.getIdAutoParts();
			
			AutoParts autoParts = new AutoParts().getObjectById(idAutoParts);
			
			System.out.println("Tovar " + autoParts.getName() + ", oststok " + balancesAutoParts.getQty());
			
		}
		
	}

}
