package tanks.tanksobject;

import tanks.BattleField;
import tanks.Launcher;
import tanks.enums.Action;
import tanks.enums.Behavior;
import tanks.enums.Colors;
import tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

import java.io.IOException;

public class Tiger extends AbstractTank {
	
	public int armor;

	private final static String IMAGE_NAME = "images/tigerUp.jpg";

	public Tiger(BattleField battleField, Behavior behavior) {
		super(battleField);
		setBehavior(behavior);
		super.setTankDirection(behavior==Behavior.DEFENDER?Direction.UP:Direction.DOWN);
		super.setColor(Colors.RED);
		super.setSpeed(15);
		this.armor = 1;
		super.setColor_(new Color(255, 165, 0));

		super.actoins = new ArrayList<>();
		URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

		try{
			image 	  = ImageIO.read(pathToResource);
			imageDraw = image;
		}catch (IOException e){
			System.err.println("Can't find image: " + IMAGE_NAME);
		}

		rotateImage(getDirection());
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}
	
	@Override
	public void printTankInfo() {
		super.printTankInfo();
		System.out.println("ARMOR of tank: " + armor);
	}

	@Override
	public void cleanAction() {

	}
}
