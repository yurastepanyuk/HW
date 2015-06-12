package com.midgardadc.day5.hw4.reference;

import com.midgardadc.day5.hw4.DataBase;
import com.midgardadc.day5.hw4.Shop;

/*
 * Super class dlya vseh spravo4nikov
 * soderzhit obs4ie rekvisitu i metodu dlya spravo4nikov
 */

public class Reference extends Shop {
	
	private int id;
	private String name;
	
	public Reference() {
	}
		
	public void Save(){
		int newId = getDb().getNewId(this);
		setId(newId);
		getDb().addNewRecord(this); 
		
	}

	
	
	//*******************************************************************
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};
	
	

}
