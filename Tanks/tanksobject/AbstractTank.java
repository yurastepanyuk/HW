package Tanks.tanksobject;

import Tanks.battlefieldobjects.Eagle;
import Tanks.BattleField;
import Tanks.battlefieldobjects.BattleFieldObjects;
import Tanks.battlefieldobjects.Brick;
import Tanks.battlefieldobjects.Rock;
import Tanks.battlefieldobjects.Water;
import Tanks.commoninterface.Tank;
import Tanks.enums.Action;
import Tanks.enums.Behavior;
import Tanks.enums.Colors;
import Tanks.enums.Direction;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class AbstractTank implements Tank {
	
	//1-UP, 2-DOWN, 3-LEFT,4-RIGHT
	// E(vostok)4, W3, N(sever)1, S2
	protected 	Direction 	tankDirection;
	protected 	Direction 	tankDirectionOfMotion;
	
	private 	int 		X = 0;
	private 	int 		Y = 0;

	public 		Colors 		color;
	public 		Color 		color_;
	protected	int 		speed = 15;

	protected 	int 		movePath = 1;//quantity move path

	public  	Bullet bullet;
	public 		BattleField BF;

	private 	boolean 	destroyed;

	public 		int [] 		target;//7,2

	public 		 ArrayList<Object> 	actoins;
	public final CopyOnWriteArrayList<Object> actionsTank = new CopyOnWriteArrayList<>();

	protected 	Image 		image;
	protected	Image 		imageDraw;

	private 	Behavior 	behavior;

	private 	AtomicBoolean allowToShot;

	private  	boolean		handControl;

	private 	int 		stepAction = 0;

	public AbstractTank(BattleField battleField){

		this(battleField, 2*64, 8*64, Direction.getDefaultDirection());

	}

	public AbstractTank(BattleField BattleField, int x, int y, Direction direction){

		this.setBF(BattleField);
		this.setX_(x);
		this.setY(y);
		this.setTankDirection(direction);
		this.setColor(Colors.ORANGE);
		this.bullet 	 = new Bullet();
		this.destroyed 	 = false;
		this.allowToShot = new AtomicBoolean(true);

	}

	public int getX(){
		return X;
	}

	public int getY(){
		return Y;
	}

	public void setX_(int X){
		this.X = X;
	}

	public void setY(int Y){
		this.Y = Y;
	}

	public void updateX(int X){
		this.X += X;
	}

	public void updateY(int Y){
		this.Y += Y;
	}

	public Behavior getBehavior() {
		return behavior;
	}

	public void setBehavior(Behavior behavior) {
		this.behavior = behavior;

		if (behavior == Behavior.AGRESSOR) {
			setTankDirection(Direction.DOWN);
			tankDirectionOfMotion = Direction.DOWN;
		} else{
			setTankDirection(Direction.UP);
			tankDirectionOfMotion = Direction.UP;
		}

	}

	public void setTargetForTank(){
		if (behavior == Behavior.AGRESSOR) {
			setTarget(getBF().getQuadrantDefender());
		} else {
			setTarget(getBF().getQuadrantAgressor());
		}

	}

	public void setSpeed(int speed){
		this.speed = speed;
	}

	public void setColor(Colors color){
		this.color = color;
	}

	public int getSpeed(){
		return speed;
	}

	public Direction getTankDirection() {
		return tankDirection;
	}

	public void setTankDirection(Direction tankDirection) {
		this.tankDirection = tankDirection;
	}

	public BattleField getBF() {
		return this.BF;
	}

	public void setBF(BattleField bF) {
		BF = bF;
	}

	public void printTankInfo() {
		System.out.println(getClass().getSimpleName());
 		System.out.println("Color of tank: "  + color);
 		System.out.println("Speed of tank: " + speed );
	};

	public Color getColor_() {
		return color_;
	}

	public void setColor_(Color color_) {
		this.color_ = color_;
	}

	public Bullet fire() throws Exception{

		int xBullet = X + 25;
		int yBullet = Y + 25;

		if (tankDirection == Direction.UP) {
			yBullet = Y;
		} else if (tankDirection == Direction.DOWN){
			yBullet = Y + 45;
		} else if (tankDirection == Direction.RIGHT){
			xBullet = X + 45;
		} else if (tankDirection == Direction.LEFT){
			xBullet = X;
		} else {
			setTankDirection(behavior==Behavior.DEFENDER?Direction.UP:Direction.DOWN);
		}

		bullet = new Bullet(xBullet, yBullet, tankDirection);
		bullet.setTankParent(this);

		return bullet;
	}

	public boolean moznoSdelatShag(Direction direction) {
		boolean result = true;

		BattleField battleField = getBF();

		if (tankDirection == Direction.RIGHT) {
			// 4 right
			if (((X + step) > (battleField.getBfWidth() - 64)) || battleField.isItObstacle(X + step, Y)) {
				result = false;
			}

		} else if (tankDirection == Direction.LEFT) {
			// 3 left
			if (((X - step) < 0) ||(battleField.isItObstacle(X - step, Y))) {
				result = false;
			}

		} else if (tankDirection ==  Direction.UP) {
			// 1 up
			if (((Y - step) < 0) || (battleField.isItObstacle(X, Y - step))) {
				result = false;
			}

		} else if (tankDirection ==  Direction.DOWN) {
			// 2 down
			if (((Y + step) > (battleField.getBfHeigt() - 64)) || (battleField.isItObstacle(X, Y + step))) {
				result = false;
			}
		}

		return result;
	}

//	public void move() throws Exception {
//
//	}
	
	/* Method: turn (int direction)
	 * Parameters:
	 * 		Direction direction - napravlenie kuda razvernut' tank
	 * Return value:
	 * 		void
	 * Use:
	 * 		Izmenyaet peremennuyu klassa, napravlenie dvizheniya tanka
	 * Example:
	 * 		turn(4) -> tank razvorachivaetsya right
	 */
	public void turn (Direction direction){
		tankDirection = direction;
		//actionField.processTurn(this);
	}
	
	public Direction getDirection(){
		return tankDirection;
	}
	
	/* Method: moveRandom()
	 * Parameters:
	 * 		not
	 * Return value:
	 * 		void
	 * Use:
	 * 		Raschet random of next napravlenie and start move()
	 * Example:
	 * 		moveRandom()
	 */
	public void moveRandom() throws Exception {
		long time = System.currentTimeMillis() / 2;

		String strTime = String.valueOf(time);

		String newNapravlenieStr = strTime.substring(strTime.length() - 1);

		int newNapravlenie = Integer.valueOf(newNapravlenieStr);

		if (tankDirection.getID() % 2 == 0) {
			newNapravlenie = newNapravlenie % 4 + 1;

			if (newNapravlenie > 4) {
				newNapravlenie = 1;
			}

		} else {
			newNapravlenie = newNapravlenie % 4 + 2;

			if (newNapravlenie > 4) {
				newNapravlenie = 1;
			}

		}

		if ((newNapravlenie <= 0) || (newNapravlenie > 4)) {

		} else {
			tankDirection = Direction.getDirectoinOfId(newNapravlenie);
		}
		Thread.sleep(20);
		System.out.println("New DIRECTION = " + tankDirection);
		
		//move();

	}

	public void clean() throws Exception{
		
		//Peredaem strings XY, get integer YX
		//otpravlyaem tank v nizhniy levuy ugol
		BattleField battleField = getBF();
		int [] IntKoordinats  =  battleField.getIntKoordinats("i", "0");
		
		//peremes4aem tank from a left corner to a upper left corner 
		for (int i = IntKoordinats[0]; i >= 0; i--) {
			int shot = battleField.getQtyBrick(i);
			turn(Direction.getDirectoinOfId(4));
			while (shot > 0) {
				fire();
				shot--;				
			}
		}
		
	}

	public void destroy(){

		setX_(-100);
		setY(-100);
		destroyed = true;
			
	}
	
	public Direction getNapravlenieY(int  newY, int oldY) {

		Direction result;

		if (newY > oldY) {
			result = Direction.DOWN;
		} else if (newY < oldY)  {
			result = Direction.UP;
		} else {
			result = Direction.NONE;
		}

		if (result != Direction.NONE) {
			tankDirectionOfMotion = result;
		}

		return result;
	}

	public Direction getNapravlenieX(int newX, int oldX) {

		if (newX > oldX ) {
			return Direction.RIGHT;
		} else if ((newX < oldX )){
			return Direction.LEFT;
		} else {
			return Direction.NONE;
		}

	}

	public int getCountQuadrantY(int newY, int oldY) {

		int countQuarant = newY - oldY;

		if (countQuarant < 0) {
			countQuarant = countQuarant * (-1);
		}
		return countQuarant;
	}

	public int getCountQuadrantX(int newX, int oldX) {

		int countQuarant = newX - oldX;

		if (countQuarant < 0) {
			countQuarant = countQuarant * (-1);
		}

		return countQuarant;
	}

	public int [] getYXnow() {
		int [] xyNow = new int [2];
		
		xyNow[0] = Y / 64;
		xyNow[1] = X / 64;
		

		return xyNow;
	}

	@Override
	public void draw(Graphics g) {

		if (!isDestroyed()) {

			g.drawImage(imageDraw, getX(), getY(), new ImageObserver() {
				@Override
				public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
					return false;
				}
			});
		}

	}

	public void rotateImage(Direction direction){

		double degress = 0;

		ImageIcon icon = new ImageIcon(this.image);
		BufferedImage bufferedImage = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_4BYTE_ABGR);
		Graphics2D graphics2D = (Graphics2D)bufferedImage.getGraphics();

		if (direction == Direction.UP) {
			degress = 0;
		}else if (direction == Direction.RIGHT){
			degress = 90;
		}else if (direction == Direction.LEFT){
			degress = -90;
		}else if (direction == Direction.DOWN){
			degress = 180;
		}else {
			return;
		}
		graphics2D.rotate(Math.toRadians(degress), icon.getIconWidth()/2, icon.getIconHeight()/2);
		graphics2D.drawImage(this.image,0,0,new ImageObserver() {
			@Override
			public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
				return false;
			}
		});
		this.imageDraw = bufferedImage;
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public int getMovePath() {
		return movePath;
	}

	public Colors getColor() {
		return color;
	}

	public int[] getTarget() {
		return target;
	}

	public void setTarget(int[] target) {
		this.target = target;
		setProcessMoveToQuadrant(target[0], target[1], true, null, false);
	}

	public ArrayList<Object> setProcessMoveToQuadrant(int y, int x, boolean set, int[] otherStartQuadrant, boolean firstX){

		int [] yxNow     		= otherStartQuadrant==null?getYXnow():new int[]{otherStartQuadrant[0], otherStartQuadrant[1]};
		int [] currentYX 		= new int[]{yxNow[0], yxNow[1]};
		int [] previousYX		= new int[]{yxNow[0], yxNow[1]};
		boolean targetAvailable = true;

		ArrayList<Object> result  = new ArrayList<>();

		Direction newNapravlenieY = getNapravlenieY(y, yxNow[0]);
		Direction newNapravlenieX = getNapravlenieX(x, yxNow[1]);

		if (newNapravlenieY == Direction.NONE) tankDirectionOfMotion = newNapravlenieX;

		int countQuadrantY = getCountQuadrantY(y, yxNow[0]);
		int countQuadrantX = getCountQuadrantX(x, yxNow[1]);

		if (firstX == false) {

			result.add(newNapravlenieY);

			for (int i = countQuadrantY; i > 0; i--) {

				previousYX[0]= currentYX[0];
				currentYX[0] = newNapravlenieY== Direction.UP?currentYX[0]-1:currentYX[0]+1;

				BattleFieldObjects currentBFObject = BF.scanQuadrant(currentYX[0],currentYX[1]);

				if (currentBFObject instanceof Rock || currentBFObject instanceof Water) {
					if (currentYX[0] > 8){
						currentYX[0] = 8;
					} else if (currentYX[0] < 0){
						currentYX[0] = 0;
					} else {
						targetAvailable = false;
					}

					break;
				}

				result.add(Action.MOVE);
			}

			if (targetAvailable == true) {
				result.add(newNapravlenieX);
				for (int i = countQuadrantX; i > 0; i--) {
					previousYX[1]= currentYX[1];
					currentYX[1] = newNapravlenieX == Direction.LEFT?currentYX[1]-1:currentYX[1]+1;

					BattleFieldObjects currentBFObject = BF.scanQuadrant(currentYX[0],currentYX[1]);

					if (currentBFObject instanceof Rock || currentBFObject instanceof Water) {
						if (currentYX[1] > 8){
							currentYX[1] = 8;
						} else if (currentYX[1] < 0){
							currentYX[1] = 0;
						} else {
							targetAvailable = false;
						}

						break;
					}

					result.add(Action.MOVE);
				}
			}

		} else {

			result.add(newNapravlenieX);
			for (int i = countQuadrantX; i > 0; i--) {
				previousYX[1]= currentYX[1];
				currentYX[1] = newNapravlenieY== Direction.LEFT?currentYX[1]-1:currentYX[1]+1;

				BattleFieldObjects currentBFObject = BF.scanQuadrant(currentYX[0],currentYX[1]);

				if (currentBFObject instanceof Rock || currentBFObject instanceof Water) {
					if (currentYX[1] > 8){
						currentYX[1] = 8;
					} else if (currentYX[1] < 0){
						currentYX[1] = 0;
					} else {
						targetAvailable = false;
					}

					break;
				}

				result.add(Action.MOVE);
			}

			if (targetAvailable == true) {
				result.add(newNapravlenieY);
				for (int i = countQuadrantY; i > 0; i--) {

					previousYX[0]= currentYX[0];
					currentYX[0] = newNapravlenieY== Direction.UP?currentYX[0]-1:currentYX[0]+1;

					BattleFieldObjects currentBFObject = BF.scanQuadrant(currentYX[0],currentYX[1]);

					if (currentBFObject instanceof Rock || currentBFObject instanceof Water) {
						if (currentYX[0] > 8){
							currentYX[0] = 8;
						} else if (currentYX[0] < 0){
							currentYX[0] = 0;
						} else {
							targetAvailable = false;
						}

						break;
					}

					result.add(Action.MOVE);
				}
			}

		}


		if (targetAvailable == false && set == true) {

			ArrayList<Object> resultAvailable = getAvailableProcessMove(previousYX, tankDirectionOfMotion, firstX);
			result.addAll(resultAvailable);
		}

		if (set = true){
			System.out.println(this.toString() + " " +  result.toString());

			this.actionsTank.clear();
			this.actionsTank.addAll(result);
		}

		return result;
	}

	private ArrayList<Object> getAvailableProcessMove(int [] currentYX, Direction tankDirectionOfMotion, boolean firstX){
		ArrayList<Object> result = new ArrayList<>();
		int [] availableQuadrant = new int[2];

		boolean newFirstX = firstX;

		if (tankDirectionOfMotion == Direction.UP) {
			availableQuadrant = BF.getAvailableQuadrant(currentYX[0] - 1, "Y", currentYX);
			newFirstX = firstX==true?false:true;
		} else if (tankDirectionOfMotion == Direction.DOWN){
			availableQuadrant = BF.getAvailableQuadrant(currentYX[0] + 1, "Y", currentYX);
			newFirstX = firstX==true?false:true;
		} else if (tankDirectionOfMotion == Direction.LEFT){
			availableQuadrant = BF.getAvailableQuadrant(currentYX[1] - 1, "X", currentYX);
		} else if (tankDirectionOfMotion == Direction.RIGHT){
			availableQuadrant = BF.getAvailableQuadrant(currentYX[1] + 1, "X", currentYX);
		}

		result = setProcessMoveToQuadrant(availableQuadrant[0], availableQuadrant[1], false, currentYX, newFirstX);

		return result;
	}

	public int isSeeingTargets(){

		int targetFind = 0;
		int qtyShots   = 0;

		int [][] quadrantMoveDirection = getBF().getQuadrantsOnDirection(getYXnow(), getTankDirection());

		Object [] targets = getBF().getTaregetsObject(this);

		if (targets == null) {
			return 0;
		}

		for (int [] quadrant : quadrantMoveDirection) {
			if (quadrant == null || quadrant[0] == -1 || quadrant[1] == -1) continue;

			BattleFieldObjects bfObjectInQuadrant = getBF().getObjectInQuadrant(quadrant);

			if (bfObjectInQuadrant == null || bfObjectInQuadrant instanceof Rock) {
				return 0;
			}

			for (Object target : targets){

				if (target == null) continue;

				if (target instanceof AbstractTank || target instanceof Eagle){

					int [] targetQuadrant = new int[2];

					if (target instanceof AbstractTank){
						targetQuadrant = ((AbstractTank) target).getYXnow();
					} else if (target instanceof Eagle) {
						targetQuadrant = ((BattleFieldObjects) target).getYXnow();
					}

					if (targetQuadrant[0] == quadrant[0] && targetQuadrant[1] == quadrant[1]) {
						targetFind = 1;
						qtyShots++;
					}
				}

				if (bfObjectInQuadrant instanceof Brick){
					qtyShots++;
				}

				if (targetFind == 1) {
					return qtyShots;
				}

			}

		}

		if (targetFind == 0){
			qtyShots = 0;
		}

		return qtyShots;
	}

	public boolean isAllowToShot() {
		return allowToShot.get();
	}

	public void setAllowToShot(boolean allowToShot) {
		this.allowToShot.set(allowToShot);
	}

	@Override
	public void setActionTank(Object action, boolean firstAction) {

		if (firstAction != true) {

			if (action instanceof Direction || action instanceof Action) {
				actionsTank.add(action);
			} else {
				return;
			}

		} else {

			if (!actionsTank.isEmpty()) {
				Object[] currentActionsTank = actionsTank.toArray();
				actionsTank.clear();

				actionsTank.add(action);

				actionsTank.addAll(Arrays.asList(currentActionsTank));

			} else {
				actionsTank.add(action);
			}

		}

	}

	public boolean isHandControl() {
		return handControl;
	}

	public void setHandControl(boolean handControl) {
		this.handControl = handControl;
		actionsTank.clear();
	}

	public Action setUp() {
		if (actionsTank == null) {
			return Action.NONE;
		}

		if (!isHandControl()){
			if (stepAction >= actionsTank.size() || stepAction >= 2) {
				stepAction = 0;
				setTargetForTank();
			}
			if (!(actionsTank.get(stepAction) instanceof Action)) {
				System.out.println("T34 " + (Direction) actionsTank.get(stepAction));
				turn((Direction) actionsTank.get(stepAction++));
				rotateImage(getDirection());
			}
			if (stepAction >= actionsTank.size()) {
				stepAction = 0;
				if (getTarget()!= null)	{
					setProcessMoveToQuadrant(getTarget()[0], getTarget()[1], true, null,false);
				}
			}

			while (stepAction < actionsTank.size() && !(actionsTank.get(stepAction) instanceof Action)) {
				System.out.println("T34 " + (Direction) actionsTank.get(stepAction));
				try {
					turn((Direction) actionsTank.get(stepAction++));
				}catch (Exception e){
					//ignore
				}

				rotateImage(getDirection());
			}

			if(stepAction < actionsTank.size()){
				System.out.println("T34 " + (actionsTank.get(stepAction) instanceof Action ?(Action) actionsTank.get(stepAction): Action.FIRE ) );

				return actionsTank.get(stepAction) instanceof Action ?(Action) actionsTank.get(stepAction++): Action.FIRE;
			}else{
				return Action.NONE;
			}
		} else {

			if (actionsTank.size() == 0) {
				return Action.NONE;
			} else {
				while (!actionsTank.isEmpty() && !(actionsTank.get(0) instanceof Action)){
					System.out.println("T34 " + (Direction) actionsTank.get(0));
					try {
						turn((Direction) actionsTank.get(0));
					}catch (Exception e){
						//ignore
					}

					rotateImage(getDirection());

					actionsTank.remove(0);
				}
			}

			if(!actionsTank.isEmpty()){
				System.out.println("T34 " + actionsTank.get(0));

				Action resultAction = Action.NONE;

				try {
					resultAction = (Action)actionsTank.get(0);
					actionsTank.remove(0);
				} catch (Exception e){
					//ignore
				}

				return resultAction;
			}else{
				return Action.NONE;
			}

		}

	}

}
