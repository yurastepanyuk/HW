package shop.documents;


import shop.Shop;
import shop.reference.AutoParts;
import shop.reference.Client;

public class Sale extends Document {

	private AutoParts 	autoParts;
	private Client 		client;
	private int 		idClient;
	private int 		idAutoParts;//AutoParts[] autoParts - v zhisni eto tabl 4ast dokumenta
	private byte 		qty;
	private float 		cena;
	
	public Sale(Shop shop) {
		super(shop);

	}

//	public Sale getObjectById(int id)  {
//
//		return serviceMethod.getObjectById(id, this.getClass());
//
//	}

//	@Override
//	public Sale newSale(Client client, AutoParts autoParts, int qty, float cena) {
//
//		return serviceMethod.newSale(client, autoParts, qty, cena);
//
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

	public void setClient(Client client) {
		this.client = client;
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

	public byte getQty() {
		return qty;
	}

	public void setQty(byte qty) {
		this.qty = qty;
	}

	public float getCena() {
		return cena;
	}

	public void setCena(float cena) {
		this.cena = cena;
	}

}
