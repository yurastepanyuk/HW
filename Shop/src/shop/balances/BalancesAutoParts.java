package shop.balances;

import shop.Shop;
import shop.reference.AutoParts;

public class BalancesAutoParts extends Balances {

	private AutoParts autoParts;
	private int idAutoParts;
	private int qty;

	public BalancesAutoParts(Shop shop) {
		super(shop);
	}

	//GETTERS AND SETTERS *****************************************

	public void setAutoParts(AutoParts autoParts) {
		this.autoParts = autoParts;
	}
	public AutoParts getAutoParts() {
		return autoParts;
	}
	public int getIdAutoParts() {
		return idAutoParts;
	}
	public void setIdAutoParts(int idAutoParts) {
		this.idAutoParts = idAutoParts;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}

}
