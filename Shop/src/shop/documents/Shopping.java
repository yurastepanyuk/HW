package shop.documents;


import shop.Shop;
import shop.reference.AutoParts;
import shop.reference.Client;

public class Shopping extends Document {

	private AutoParts 	autoParts;
	private Client 		client;
	private int 		idClient;
	private int 		idAutoParts;
	private int 		qty;
	private float 		cena;
	
	public Shopping(Shop shop) {
		super(shop);
	}
	
//	public Shopping getObjectById(int id) {
//
//		return serviceMethod.getObjectById(id, this.getClass());
//
//	}


//	@Override
//	public Shopping newShopping(Client client, AutoParts autoParts, int qty, float cena) {
//		return serviceMethod.newShopping(client, autoParts, qty, cena);
//	}
	
	public AutoParts getAutoParts() {
		return autoParts;
	}
	public void setAutoParts(AutoParts autoParts) {
		this.autoParts = autoParts;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client clienr) {
		this.client = clienr;
	}
	public int getIdClient() {
		return idClient;
	}
	public void setIdClient(int idClient) {
		this.idClient = idClient;
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
	public float getCena() {
		return cena;
	}
	public void setCena(float cena) {
		this.cena = cena;
	}

}
