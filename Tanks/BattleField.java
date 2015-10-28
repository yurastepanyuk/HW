package Tanks;

import Tanks.battlefieldobjects.*;
import Tanks.commoninterface.Drawable;
import Tanks.enums.Behavior;
import Tanks.enums.Direction;
import Tanks.tanksobject.AbstractTank;

import java.awt.*;

public class BattleField implements Drawable {
	
	public static final int BF_WIDTH  = 576;
	public static final int BF_HEIGHT = 576;
	
	public static int step = 64;

	public static ActionField actionField;
	public static String [][] battleField;
	public static BattleFieldObjects[][] battleFieldObj;

	public static String Vertikal 	= "abcdefghi";
	public static String Horizontal = "123456789";

	private int [] headquarters;//quadrant staba [9,5]

	BattleField(ActionField actionField){
		
		this.actionField = actionField;
		
		inicialisationBF();

	}	
	
	public int getBfWidth(){
		return BF_WIDTH;
	}
	
	public int getBfHeigt(){
		return BF_HEIGHT;
	}

	public void inicialisationBF(){
				
//		battleField = new String [][]{
//				{" "," "," "," "," "," "," "," "," "},
//				{" "," "," "," "," "," "," "," "," "},
//				{"B","B","B"," "," ","B","B","B","B"},
//				{" "," "," "," "," "," "," "," "," "},
//				{" ","B","B","B","B","B","B"," "," "},
//				{" "," "," "," "," "," "," "," "," "},
//				{"B","B"," "," "," ","B","B","B","B"},
//				{" "," "," "," "," "," "," "," "," "},
//				{" "," "," "," "," "," "," "," "," "}
//				};

		String [][] battleFieldTwo = new String [][]{
				{" "," "," "," "," "," "," "," "," "},
				{" "," "," "," "," "," "," "," "," "},
				{"B","B","B","R","R","R","B","B","B"},
				{" "," "," "," "," "," "," "," ","B"},
				{" ","W","W","W","W","W","W"," ","B"},
				{" ","B","B"," "," "," "," "," "," "},
				{"B","B","B"," "," ","B","B","B","B"},
				{" "," "," "," "," "," "," "," "," "},
				{" "," "," "," ","E"," "," "," "," "}
		};

		this.battleFieldObj = new BattleFieldObjects[9][9];

		for (int i = 0; i <= battleFieldTwo.length-1; i++) {

			String [] bfString  = battleFieldTwo[i];

			for (int j = 0; j <= bfString.length - 1; j++) {

				int x = j * 64;
				int y = i * 64;

				if (bfString[j].equals("B")){
					this.battleFieldObj[i][j] = new Brick(x,y);
				}else if (bfString[j].equals("R")){
					this.battleFieldObj[i][j] = new Rock(x,y);
				}else if (bfString[j].equals("W")){
					this.battleFieldObj[i][j] = new Water(x,y);
				}else if (bfString[j].equals("E")){
					this.battleFieldObj[i][j] = new Eagle(x,y);
					headquarters = new int[]{i,j};
				}else{
					this.battleFieldObj[i][j] = new Empty(x,y);
				}
			}

		}
		
	}

	@Override
	public void draw(Graphics g) {
		for (int j = 0; j < battleFieldObj.length; j++) {
			for (int k = 0; k < battleFieldObj.length; k++) {
				battleFieldObj[j][k].draw(g);
			}
		}
	}

	public int [] getIntKoordinats(String Y,String X){
		
		int [] intKoordinats = new int [2];
		
		intKoordinats[0] = Vertikal.indexOf(Y);
		intKoordinats[1] = Horizontal.indexOf(X);		
		
		return intKoordinats;
	}

	BattleFieldObjects getValueOfXY(int X, int Y){
		
		BattleFieldObjects result;
		
		//Poluchim value tipa "1_8"
		String YX = actionField.getQuadrant(X, Y);
		
		//Poluchim Integer value qudrant
		int symv_ = YX.indexOf("_");
		int y = Integer.valueOf(YX.substring(0, symv_));
		int x = Integer.valueOf(YX.substring(symv_ + 1));
		
		if ((y < 0 || y >= 9) || (x < 0 || x >= 9)) {
			return null;
		}
		
		//get value in quadrant
		result = battleFieldObj[y][x];
		
		return  result;
		
	}	

	public boolean isItObstacle(int X, int Y){
		
		boolean result = false;
		
		BattleFieldObjects valQuadrant = getValueOfXY(X, Y);
		
		if (valQuadrant == null) {
			result = true;
		} else if (!(valQuadrant instanceof Empty)) {
			result = true;
		}
		
		return result;
	}

	public int getQtyBrick(int Y){
		int result = 0;
		
		for (String val : battleField[Y]) {
			if (val.equals("B")) {
				result += 1;
			}
		}
		
		return result;
	}
	
	String getQudrant(String v, String h) {
		// String Vertikal = "abcdefghi";
		// String Horizontal = "123456789";

		if (v == null || h == null) {
			return "0_0";
		} else if ((Vertikal.indexOf(v) == -1) || (Horizontal.indexOf(h) == -1)) {
			return "0_0";
		}

		int dlinaStoronaQuadrant = 64;

		int numQuadrantV = Vertikal.indexOf(v) * dlinaStoronaQuadrant;
		int numQuadrantH = Horizontal.indexOf(h) * dlinaStoronaQuadrant;

		return Integer.toString(numQuadrantV) + "_"
				+ Integer.toString(numQuadrantH);

	}

	void printCoordinates(String v, String h) {
		// "0_64"
		String koordinataNachaloQuadrant = getQudrant(v, h);

		int razdelitel = koordinataNachaloQuadrant.indexOf("_");

		String koordinataV = koordinataNachaloQuadrant.substring(0, razdelitel);
		String koordinataH = koordinataNachaloQuadrant
				.substring(razdelitel + 1);

		System.out.println(v + h + ":(" + koordinataV + "px;" + koordinataH
				+ "px)");
		

	}

	public BattleFieldObjects scanQuadrant(int Y, int X) {

		if (Y < 0 || Y > 8 || X < 0 || X > 8){
			return new Rock(-100,-100);
		}
		
		return battleFieldObj[Y][X];

	}

	public void updateQuadrant(int Y, int X, BattleFieldObjects newValue) {

		battleFieldObj[Y][X] = newValue;

	}

	public int getDimentionX() {
		int result = 0;
		if (battleField.length != 0){
			result = battleField[1].length;
		}
		return result;

	}

	public int getDimentionY() {
		
		return battleField.length;

	}

	public int[] getHeadquarters() {
		return headquarters;
	}

	public int[] getAvailableQuadrant(int coordinate, String value, int [] currentYX){

		int [] result = new int[2];

		BattleFieldObjects bfObj  = new Rock(-100,-100);

		if (value.equals("Y")){
			int currentX    = currentYX[1];

			for (int i = 1; i <= 9; i++){

				bfObj = scanQuadrant(coordinate, currentX - i);

				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = coordinate;
					result[1] = currentX - i;
					break;
				}

				bfObj = scanQuadrant(coordinate, currentX + i);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = coordinate;
					result[1] = currentX + i;
					break;
				}

			}
		} else if (value.equals("X")) {
			int currentY    = currentYX[0];

			for (int i = 1; i <= 9; i++){

				bfObj = scanQuadrant(currentY - i, coordinate);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = currentY - i;
					result[1] = coordinate;
					break;
				}

				bfObj = scanQuadrant(currentY + i, coordinate);
				if (bfObj instanceof Empty || bfObj instanceof Brick){
					result[0] = currentY + i;
					result[1] = coordinate;
					break;
				}

			}

		}

		return result;

	}

	public int[] getQuadrantDefender(){
		return actionField.getQuadrantDefender();
	}

	public int[] getQuadrantAgressor(){
		return actionField.getQuadrantAgressor();
	}

	public int [][] getQuadrantsOnDirection(int [] quadrant, Direction direction){

		int [][] result = new int[9][2];

		int currentY = quadrant[0];
		int currentX = quadrant[1];

		int idx = 0;

		if (direction == Direction.DOWN) {

			for (int i = 0; i <= 8 ; i++) {
				if (i >= currentY) {
					result[idx] = new int [] {i,currentX};
				} else {
					result[idx] = new int [] {-1,-1};
				}
				idx++;
			}

		} else if (direction == Direction.UP) {

			for (int i = 8; i >= 0 ; i--) {
				if (i <= currentY) {
					result[idx] = new int [] {i,currentX};
				} else {
					result[idx] = new int [] {-1,-1};
				}
				idx++;
			}

		} else if (direction == Direction.RIGHT) {

			for (int i = 0; i <= 8; i++) {
				if (i < currentX) {
					result[idx] = new int [] {-1,-1};
				} else {
					result[idx] = new int [] {currentY,i};
				}
				idx++;
			}

		} else if (direction == Direction.LEFT) {

			for (int i = 8; i >= 0; i--) {
				if (i > currentX) {
					result[idx] = new int [] {-1,-1};
				} else {
					result[idx] = new int [] {currentY,i};
				}

				idx++;
			}

		}

		return result;
	}

	public Object [] getTaregetsObject(AbstractTank tank){

		Object [] result = new  Object [9];
		int idx = 0;

		if (tank.getBehavior() == Behavior.AGRESSOR){

			result[idx] = battleFieldObj[getHeadquarters()[0]] [getHeadquarters()[1]];
			idx++;

			result[idx] = actionField.getDefender();

		} else if (tank.getBehavior() == Behavior.DEFENDER){
			result[idx] = actionField.getAgressor();
			idx++;
		}

		return result;
	}

	public BattleFieldObjects getObjectInQuadrant(int[] quadrant){
		if (quadrant[0] < 0) {
			return null;
		}
		return battleFieldObj[quadrant[0]][quadrant[1]];
	}

}
	
	
