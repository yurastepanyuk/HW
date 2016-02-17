package shop.enums;

public enum CategoryPrice {
	
	ROZNITSA(0), OPTOVAYA(1), DILLER(2);

	private int ID;

	CategoryPrice(int id) {
		this.ID = id;
	}

	public int getID() {
		return ID;
	}

	public static CategoryPrice getById(Integer id) {
		for(CategoryPrice e : values()) {
			if(id.equals(e.ID)) return e;
		}
		return null;
	}
}
