package shop.reference;

import shop.Shop;

public class Client extends Reference {

	private int inn;

	private ServiceMethodReference service;
	
	public Client() {
		
	}

	public Client(Shop shop) {
		super(shop);
		service = new ServiceMethodReference(shop);
	}

	//*******************************************************************
	//GETTERS AND SETTERS

	public Client getObjectById(int id){

		return (Client)service.getReferenceObjectById(id, this.getClass());
	}

	public Client getObjectByName(String name){

		return (Client)service.getReferenceObjectByName(name, this.getClass());
	}

	public int getInn() {
		return inn;
	}

	public void setInn(int inn) {
		this.inn = inn;
	}
}

