package shop.reference;

import shop.Shop;
import shop.enums.Category;
import shop.inforamation.Prices;

public class AutoParts extends Reference {
	
	private String 		catalogNumber;
	private Category 	categoriya;
	private Prices[]	prices;

	public AutoParts() {
		
	}

	public AutoParts(Shop shop) {
		super(shop);
	}

//	@Override
//	public AutoParts getObjectById(int id) {
//		return serviceMethod.getReferenceObjectById(id, this.getClass());
//	}
//
//	@Override
//	public AutoParts getObjectByName(String name) {
//		return serviceMethod.getReferenceObjectByName(name, this.getClass());
//	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof AutoParts) {
			AutoParts autoPart = (AutoParts)obj;

			if (getId() == autoPart.getId() && getName() != null && getName().equals(autoPart.getName())
					&& getCatalogNumber() != null && getCatalogNumber().equals(autoPart.getCatalogNumber())) {
				return true;
			}
			
		}

		return false;
	}

	public String getCatalogNumber() {
		return catalogNumber;
	}

	public void setCatalogNumber(String catalogNumber) {
		this.catalogNumber = catalogNumber;
	}

	public Category getCategoriya() {
		return categoriya;
	}

	public void setCategoriya(Category categoriya) {
		this.categoriya = categoriya;
	}

	public void setPrices(Prices[] prices) {
		this.prices = prices;
	}
}
