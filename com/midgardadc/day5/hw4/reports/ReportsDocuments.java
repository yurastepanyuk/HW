package com.midgardadc.day5.hw4.reports;

import com.midgardadc.day5.hw4.documents.Sale;
import com.midgardadc.day5.hw4.reference.AutoParts;
import com.midgardadc.day5.hw4.reference.Client;


/*
 * klas dlya polu4eniya dannuh po documents
 * extends Reports
 */

public class ReportsDocuments extends Reports {
	
	public void qtySaleForDay(int qtyDay){
		
		int dataFinish = getCurrentDate();
		int dataStart = dataFinish - qtyDay;
		
		Sale [] sale = getDb().getSale();
		
		for (int currentDocDay = dataStart; currentDocDay <= dataFinish; currentDocDay++) {
			int qtySale = 0;
			
			for (Sale docSale : sale){
				int dateDoc = docSale.getDate();
				
				if (dateDoc == currentDocDay){
					qtySale += 	(int) docSale.getQty();				
				}
				
			}
			
			System.out.print(qtySale + ", ");
			
		}
		
		System.out.println("");		
		
	}
	
	public void TransactionListForDay(int day){
		
		Sale [] sale = getDb().getSale();
		
		int numPP = 0;
		
		System.out.println("Num P/P" + "    " + "Name of Client" + "    " + "Name of Auto parts" + "    " + "Price" + "    " + "QTY");
		
		for (Sale docSale : sale){
			int dateDoc = docSale.getDate();
			
			if (dateDoc == day){
				
				numPP += 1;
				
				int idxClient = docSale.getIdClient();
				int idxAutoParts = docSale.getIdAutoParts();
				float prise = docSale.getCena();
				int qty = (int)docSale.getQty();
				
				AutoParts autoParts = new AutoParts().getObjectById(idxAutoParts);
				Client client = new Client().getObjectById(idxClient);
				
				System.out.println(numPP + "    " + client.getName() + "    " + autoParts.getName() + "    " + prise + "    " + qty);
				
							
			}
			
		}
		
	}

	public void totalTransactionListForDay(int day) {
		Sale [] sale = getDb().getSale();
		
		int numPP = 0;
		float totalSumma = 0;
		int totalQty = 0;
		
		System.out.println("Count doc" + "    " + "Total Summa" + "    " + "Total QTY");
		
		for (Sale docSale : sale){
			int dateDoc = docSale.getDate();
			
			if (dateDoc == day){
				
				numPP += 1;
				totalQty += (int)docSale.getQty();
				totalSumma += docSale.getCena() * (float)docSale.getQty();				
				
							
			}
						
		}
		
		System.out.println(numPP + "            " + totalSumma + "         " + totalQty);
		
	}

}
