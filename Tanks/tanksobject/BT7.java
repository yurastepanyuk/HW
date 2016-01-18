package tanks.tanksobject;

import tanks.BattleField;
import tanks.Launcher;
import tanks.enums.Action;
import tanks.enums.Behavior;
import tanks.enums.Colors;
import tanks.enums.Direction;

import javax.imageio.ImageIO;
import java.io.IOException;

import java.awt.*;
import java.net.URL;
import java.util.ArrayList;

public class BT7 extends AbstractTank {

	private final static String IMAGE_NAME = "images/bt7Up.jpg";

	public BT7(BattleField battleField, Behavior behavior){
		
		super(battleField);
		setBehavior(behavior);
		super.setTankDirection(behavior==Behavior.DEFENDER?Direction.UP:Direction.DOWN);
		super.setColor(Colors.BLUE);
		speed   *= 2;
		super.setColor_(new Color(255, 0, 0));
		movePath = 2;

		super.actoins = new ArrayList<>();

		setTargetForTank();

		URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

		try{
			image	  = ImageIO.read(pathToResource);
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

	}
}
