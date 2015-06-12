package com.midgardadc.day5.hw4.documents;

import com.midgardadc.day5.hw4.DataBase;
import com.midgardadc.day5.hw4.balances.BalancesAutoParts;
import com.midgardadc.day5.hw4.enums.CategoryPrice;
import com.midgardadc.day5.hw4.inforamation.Prices;
import com.midgardadc.day5.hw4.reference.AutoParts;

/*
 * Super class dlya vseh dokumentov
 * soderzhit obs4ie rekvisitu i metodu dlya documents
 */

public class Document  extends Transaction{
	
	private int id;
	private int date;//int currentDate = (int)(System.currentTimeMillis()/(1000*60*60*24));
	private String numDocument;
		
	public void Save(){
		
		int newId = getDb().getNewId(this);
		setId(newId);
		
		if (date == 0){
			setDate(getCurrentDate());
		}
		
		getDb().addNewRecord(this); 
		
		if (this instanceof Shopping) {			
			addBalanceAutoPart();
		} else if (this instanceof Sale) {
			lessBalanceAutoPart();
		}
		
	};
	
	/*
	 * add qty by AutoPart
	 */
	private void addBalanceAutoPart() {
		
		Shopping docum = (Shopping) this;
		
		BalancesAutoParts balanceAS = getRecordByAutoParts(docum.getIdAutoParts());		
					
		if (balanceAS == null) {
			balanceAS = new BalancesAutoParts();
			balanceAS.setIdAutoParts(docum.getIdAutoParts());
			balanceAS.setQty(docum.getQty());
			balanceAS.Save();
			//getDb().addNewRecord(balanceAS); 
		} else {
			balanceAS.setQty(balanceAS.getQty() +  docum.getQty());
		}		
		
	}
	
	/*
	 * chek availability price for AutoParts
	 */
	private BalancesAutoParts getRecordByAutoParts(int id) {
				
		for (BalancesAutoParts element : getDb().getBalancesAutoParts()) {
			
			if (element.getIdAutoParts() == id) {
				return element;
			}
		
		}
		return null;
		
	}

	
	/*
	 * lessening qty by AutoPart
	 */
	private void lessBalanceAutoPart() {
		
		Sale docum = (Sale) this;

		BalancesAutoParts balanceAS = getRecordByAutoParts(docum.getIdAutoParts());

		if (balanceAS == null) {
			balanceAS = new BalancesAutoParts();
			balanceAS.setIdAutoParts(docum.getIdAutoParts());
			balanceAS.setQty((-1)*docum.getQty());
			balanceAS.Save();
		} else {
			balanceAS.setQty(balanceAS.getQty() - docum.getQty());
		}
		
	}


	//GETTERS AND SETTERS *****************************************
	
	/*
	 * get Object by ID Document from Transact
	 */
	public Document getObjectByID(String typeDoc, int id){
		return new Document();
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
				
		if (date == 0) {
			date = getCurrentDate();
		}
		
		this.date = date;
	}

	public String getNumDocument() {
		return numDocument;
	}

	public void setNumDocument(String numDocument) {
		this.numDocument = numDocument;
	}

}
