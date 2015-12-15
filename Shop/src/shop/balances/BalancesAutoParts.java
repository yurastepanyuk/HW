package shop.balances;

import shop.Shop;
import shop.reference.AutoParts;

public class BalancesAutoParts extends Balances {

	private int idBalancesAutoParts;
	private AutoParts autoParts;
	private int qty;

	public BalancesAutoParts(Shop shop) {
		super(shop);
	}

	public BalancesAutoParts(Shop shop, int idBalancesAutoParts, AutoParts autoParts, int qty) {
		super(shop);
		this.idBalancesAutoParts = idBalancesAutoParts;
		this.autoParts = autoParts;
		this.qty = qty;
	}

	//GETTERS AND SETTERS *****************************************

	public void setAutoParts(AutoParts autoParts) {
		this.autoParts = autoParts;
	}
	public AutoParts getAutoParts() {
		return autoParts;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

	public int getidBalancesAutoParts() {
		return idBalancesAutoParts;
	}

	public void setidBalancesAutoParts(int balancesAutoParts) {
		this.idBalancesAutoParts = balancesAutoParts;
	}
}
