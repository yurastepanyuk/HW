package tanks.tanksobject;

import tanks.BattleField;
import tanks.Launcher;
import tanks.enums.Action;
import tanks.enums.Behavior;
import tanks.enums.Colors;
import tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.IOException;
import java.net.URL;

public class T34 extends AbstractTank {

	private final static String IMAGE_NAME = "images/t34up.jpg";

	public T34(BattleField battleField, Behavior behavior) {
		super(battleField);
		super.setBehavior(behavior);
		super.setTankDirection(behavior==Behavior.DEFENDER?Direction.UP:Direction.DOWN);
		super.setColor(Colors.YELLOW);
		//super.setSpeed(50);
        super.setColor_(new Color(90, 0, 90));

		URL pathToResources = Launcher.class.getResource(IMAGE_NAME);

		setTargetForTank();

		try{
			image	  = ImageIO.read(pathToResources);
			imageDraw = image;
		}catch (IOException e){
			System.err.println("Can't find image: " + IMAGE_NAME);
		}

		rotateImage(getDirection());
	}
	
	@Override
	public void printTankInfo() {
		super.printTankInfo();
	}

	@Override
	public void cleanAction() {

		if (!actionsTank.isEmpty()) {

			if (actionsTank.get(0) == Action.FIRE){
				actionsTank.clear();
				actionsTank.add(Action.FIRE);
			} else {
				actionsTank.clear();
			}

		}

	}
}
