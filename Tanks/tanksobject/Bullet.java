package tanks.tanksobject;


import tanks.commoninterface.Destroyable;
import tanks.commoninterface.Drawable;
import tanks.enums.Direction;

import java.awt.*;

public class Bullet implements Drawable, Destroyable {
	
	private int X = -100;
	private int Y = -100;
	private int speed = 5;
	private Direction direction;
	private AbstractTank tankParent;

	private boolean destroyed;

	public Bullet() {

		this(-100, -100, 1, Direction.UP);

	}

	public Bullet(int x, int y, int speed, Direction direction) {
		this.X = x;
		this.Y = y;
		this.speed = speed;
		this.direction = direction;
	}

	public Bullet(int X, int Y, Direction direction) {

		this.X = X;
		this.Y = Y;
		this.direction = direction;

	}

	public Direction getDirection() {
		return direction;
	}

	public int getX() {
		return X;
	}

	public int getY() {
		return Y;
	}

	public int getSpeed() {
		return speed;
	}

	public void updateX(int addX){
		X += addX;
	}

	public void updateY(int addY){
		Y += addY;
	}

	public void destroy(){
		X = -100;
		Y = -100;
		destroyed = true;
		tankParent.setAllowToShot(true);
	}

	@Override
	public boolean isDestroyed() {
		return destroyed;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(new Color(255, 255, 0));
		g.fillRect(getX(), getY(), 14, 14);
	}

	public void setTankParent(AbstractTank tankParent) {
		this.tankParent = tankParent;
	}

	public AbstractTank getTankParent() {
		return tankParent;
	}
}
