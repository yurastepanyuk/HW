package shop.reports;

import shop.documents.Sale;
import shop.reference.AutoParts;
import shop.reference.Client;
import shop.reports.*;

import java.util.List;


/*
 * klas dlya polu4eniya dannuh po documents
 * extends Reports
 */

public class ReportsDocuments extends Reports {
	
	public void qtySaleForDay(int qtyDay){
		
		int dataFinish = shop.getCurrentDate();
		int dataStart = dataFinish - qtyDay;

		List<Sale> saleList = (List<Sale>) shop.getDb().getDataFromTable(Sale.class);
		
		for (int currentDocDay = dataStart; currentDocDay <= dataFinish; currentDocDay++) {
			int qtySale = 0;
			
			for (Sale docSale : saleList){
				int dateDoc = docSale.getDate();
				
				if (dateDoc == currentDocDay){
					qtySale += 	(int) docSale.getQty();				
				}
				
			}
			
			System.out.print(qtySale + ", ");
			
		}
		
		System.out.println("");		
		
	}
	
	public void transactionListForDay(int day){

		List<Sale> saleList = (List<Sale>) shop.getDb().getDataFromTable(Sale.class);
		
		int numPP = 0;
		
		System.out.println("Num P/P" + "    " + "Name of Client" + "    " + "Name of Auto parts" + "    " + "Price" + "    " + "QTY");
		
		for (Sale docSale : saleList){
			int dateDoc = docSale.getDate();
			
			if (dateDoc == day){
				
				numPP += 1;
				
				int idxClient = docSale.getIdClient();
				int idxAutoParts = docSale.getIdAutoParts();
				float prise = docSale.getCena();
				int qty = (int)docSale.getQty();
				
				//AutoParts autoParts = new AutoParts(shop).getObjectById(idxAutoParts);
				AutoParts autoParts = serviceMethodReference.getReferenceObjectById(idxAutoParts, AutoParts.class);
				Client client = new Client(shop).getObjectById(idxClient);
				
				System.out.println(numPP + "    " + client.getName() + "    " + autoParts.getName() + "    " + prise + "    " + qty);
				
							
			}
			
		}
		
	}

	public void totalTransactionListForDay(int day) {
		List<Sale> saleList = (List<Sale>) shop.getDb().getDataFromTable(Sale.class);
		
		int numPP = 0;
		float totalSumma = 0;
		int totalQty = 0;
		
		System.out.println("Count doc" + "    " + "Total Summa" + "    " + "Total QTY");
		
		for (Sale docSale : saleList){
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
