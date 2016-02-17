package shop.reference;

import shop.Shop;

public class Reference implements MethodsReference {
	
	private 	int 	id;
	private 	String 	name;

	protected 	Shop 	shop;

	protected ServiceMethodReference serviceMethod;
	
	public Reference() {
	}

	public Reference(Shop shop) {
		this.shop = shop;
		serviceMethod = new ServiceMethodReference(shop);
	}

	@Override
	public void save(){
		serviceMethod.save(this);
	}

	//*******************************************************************
	//GETTERS AND SETTERS
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	};

	@Override
	public String toString() {
		return getName();
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}
}
