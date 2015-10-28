package Tanks.enums;

public enum Direction {
	
	NONE(0), UP(1), DOWN(2), LEFT(3), RIGHT(4);
	
	private int ID;
	
	private Direction(int id){
		this.ID = id;
	}
	
	public static Direction getDefaultDirection(){
		return UP;
	};
	
	public int getID(){
		return this.ID;
	}
	
	public static Direction getDirectoinOfId(int id) {
		
		Direction result = null;
		
		if (id == 1){
			result = UP;
		} else if (id == 2){
			result = DOWN;
		} else if (id == 3){
			result = LEFT;
		} else if (id == 4){
			result = RIGHT;
		}
		
		return result;
	}

}
