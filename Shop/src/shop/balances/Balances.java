package shop.balances;

import shop.Shop;

/*
 * Super class for all Balanses class
 * Klassi dannoy kategorii hranyat informaciyu o balance po raznim tablicam...
 */
public class Balances {

	protected Shop shop;
	protected ServiceMethodBalance serviceMethodBalance;

	public Balances(Shop shop) {
		this.shop = shop;
		serviceMethodBalance = new ServiceMethodBalance(shop);
	}

	public void save(){
		serviceMethodBalance.save(this);
	}

}
