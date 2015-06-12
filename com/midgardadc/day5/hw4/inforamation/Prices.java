package com.midgardadc.day5.hw4.inforamation;

import com.midgardadc.day5.hw4.enums.CategoryPrice;
import com.midgardadc.day5.hw4.reference.AutoParts;

/*
 * realizuet zapis i 4tenie cen tovarov po kategoriyam cen
 */
public class Prices extends Information {
	
	private int idAutoParts;
	private float prise;
	private CategoryPrice kategoriya;
	
		
	/*
	 * Add/update price for AutoParts
	 */
	public void setPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya){
		
		Prices prc = getRecordByAutoParts(autoParts.getId());
		
		if (prc == null) {
			addPrice(autoParts, prise, kategoriya);
		} else {
			prc.setPrise(prise);
		}
		
	}

	/*
	 * add new price for AutoParts
	 */
	private void addPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya) {
		
		setIdAutoParts(autoParts.getId());
		setKategoriya(kategoriya);
		setPrise(prise);
		Save();
	}

	/*
	 * chek availability price for AutoParts
	 */
	private Prices getRecordByAutoParts(int id) {
				
		for (Prices element : getDb().getPrices()) {
			
			if (element.idAutoParts == id) {
				return element;
			}
		
		}
		return null;
		
	}

	public float getPriceByGoods(AutoParts autoParts, CategoryPrice kategoriya){
		Prices prc = getRecordByAutoParts(autoParts.getId());
		
		if (prc != null) {
			return prc.getPrise();
		}
		
		return 0f;
	}

		
	//*******************************************************************
	//GETTERS AND SETTERS
	public int getIdAutoParts() {
		return idAutoParts;
	}

	public void setIdAutoParts(int idAutoParts) {
		this.idAutoParts = idAutoParts;
	}

	public float getPrise() {
		return prise;
	}

	public void setPrise(float prise) {
		this.prise = prise;
	}

	public CategoryPrice getKategoriya() {
		return kategoriya;
	}

	public void setKategoriya(CategoryPrice kategoriya) {
		this.kategoriya = kategoriya;
	}
	

}
