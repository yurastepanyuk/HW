package shop.enums;

public enum Category {
	
	HODOVAYA(0), ELECTROOBORUDOVANIE(1), DVIGATEL(2), OIL(3), SHINA(4);

	private int ID;

	Category(int i) {
		this.ID = i;
	}

	public int getID() {
		return ID;
	}

	public static Category getById(Integer id) {
		for(Category e : values()) {
			if(id.equals(e.ID)) return e;
		}
		return null;
	}

}
