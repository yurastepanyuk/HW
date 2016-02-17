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

	@Override
	public String toString() {
		return getName();
	}

	//*******************************************************************
	//GETTERS AND SETTERS

	public Client getObjectById(int id){

		return (Client)service.getReferenceObjectById(id, this.getClass());
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Client) {
			Client chekClient = (Client) obj;

			if (getId() == chekClient.getId() && getName() != null && getName().equals(chekClient.getName())
					&& getInn() == chekClient.getInn()) {
				return true;
			}

		}

		return false;
	}

	public int getInn() {
		return inn;
	}

	public void setInn(int inn) {
		this.inn = inn;
	}
}

