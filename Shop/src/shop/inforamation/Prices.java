package shop.inforamation;

import shop.Shop;
import shop.enums.CategoryPrice;
import shop.reference.AutoParts;

/*
 * realizuet zapis i 4tenie cen tovarov po kategoriyam cen
 */
public class Prices extends Information implements MethodsPrices {

	private int				idPrice;
	private AutoParts		autoParts;
	private int 			idAutoParts;
	private float 			prise;
	private CategoryPrice 	kategoriya;

	public Prices(Shop shop) {
		super(shop);
	}

	public void setPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya){

		serviceMethodInformation.updatePrice(autoParts, prise, kategoriya);
		
	}

	public void addPrice(AutoParts autoParts, float prise, CategoryPrice kategoriya) {

		serviceMethodInformation.addPrice(autoParts, prise, kategoriya);
	}

	public Prices getRecordByAutoParts(AutoParts autoParts, CategoryPrice kategoriya) {

		return serviceMethodInformation.getRecordByAutoParts(autoParts, kategoriya);

	}

	public float getPriceByGoods(AutoParts autoParts, CategoryPrice kategoriya){

		return serviceMethodInformation.getPriceByGoods(autoParts, kategoriya);
	}

	public Prices[] getPricesByGoods(AutoParts autoParts){

		return serviceMethodInformation.getPricesByGoods(autoParts);
	}

	//*******************************************************************
	//GETTERS AND SETTERS
	public int getIdAutoParts() {
		return idAutoParts;
	}

	public void setIdAutoParts(int idAutoParts) {
		this.idAutoParts = idAutoParts;
	}

	public float getPrise() {
		return prise;
	}

	public void setPrise(float prise) {
		this.prise = prise;
	}

	public CategoryPrice getKategoriya() {
		return kategoriya;
	}

	public void setKategoriya(CategoryPrice kategoriya) {
		this.kategoriya = kategoriya;
	}

	public void setAutoParts(AutoParts autoParts) {
		this.autoParts = autoParts;
	}

	public AutoParts getAutoParts() {
		return autoParts;
	}

	public int getIdPrice() {
		return idPrice;
	}

	public void setIdPrice(int idPrice) {
		this.idPrice = idPrice;
	}
}
