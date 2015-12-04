package shop.documents;

import shop.Shop;
import shop.reference.AutoParts;
import shop.reference.Client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Document  extends Transaction implements MethodsDocument {

	private 	int 	id;
	private 	int 	date;//int currentDate = (int)(System.currentTimeMillis()/(1000*60*60*24));
	private 	String 	numDocument;
	private 	Date	dateDoc;

	protected  	ServiceMethodDocument serviceMethod;

	public Document(Shop shop) {
		super(shop);
		serviceMethod = new ServiceMethodDocument(shop);
	}

	@Override
	public void save(){
		serviceMethod.save(this);
	}

	@Override
	public <T> T getObjectById(int id) {
		return null;
	}

	@Override
	public Shopping newShopping(Client client, AutoParts autoParts, int qty, float cena) {
		return serviceMethod.newShopping(client, autoParts, qty, cena);
	}

	@Override
	public Sale newSale(Client client, AutoParts autoParts, int qty, float cena) {
		return serviceMethod.newSale(client, autoParts, qty, cena);
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDate() {
		return date;
	}

	public void setDate(int date) {
				
		if (date == 0) {
			date = getCurrentDate();
		}
		
		this.date = date;
	}

	public String getDateToString(){
		Date dateDoc = new Date(((long)getDate())*(1000*60*60*24));
		SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
		return sdf.format(dateDoc);
	}

	public String getNumDocument() {
		return numDocument;
	}

	public void setNumDocument(String numDocument) {
		this.numDocument = numDocument;
	}

	public Date getDateDoc() {
		return dateDoc;
	}

	public void setDateDoc(Date dateDoc) {
		this.dateDoc = dateDoc;
	}

}
