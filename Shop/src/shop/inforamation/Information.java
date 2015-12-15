package shop.inforamation;

import shop.Shop;

public class Information implements MethodsInformation {

	private int	id;
	
	private 	String 	typeInfo;

	protected 	Shop 	shop;

	protected   ServiceMethodInformation serviceMethodInformation;

	public Information(Shop shop) {
		this.shop = shop;
		serviceMethodInformation = new ServiceMethodInformation(shop);
	}

	@Override
	public void save() {
		serviceMethodInformation.save(this);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
