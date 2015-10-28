package Tanks.battlefieldobjects;

import Tanks.Launcher;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.IOException;
import java.net.URL;

public class Rock extends BattleFieldObjects {

    private final static String IMAGE_NAME =  "images/rock.jpg";

    public Rock(int x, int y) {
        super(x, y);
        setColor(new Color(100,100,100));

        URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

        try {
            image = ImageIO.read(pathToResource);
        } catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }
    }

    @Override
    public void destroy() {

    }

}
