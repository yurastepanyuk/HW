package Tanks.enums;

public enum Colors {
	
	WHITE(1), BLACK(2), BLUE(3), DARK_BLUE(4), RED(5), LIGHT_RED(6), DARK_GREEN(7), ORANGE(8), 
	YELLOW(9), LIGHT_BLUE(10), PURPURE(11), DARK_RED(12), BROWN(13), LIGHT_NUT(14), DARK_NUT(15),
	GRAY(16);
	
	private int ID;

	private Colors(){
		this(5);
	}
	
	private Colors(int id) {
		this.ID = id;
	}
	
	public Colors getDefaultColor(){
		return RED;
	}

}
