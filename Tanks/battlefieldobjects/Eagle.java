package tanks.battlefieldobjects;

import tanks.Launcher;

import javax.imageio.ImageIO;

import java.io.IOException;
import java.net.URL;

public class Eagle extends BattleFieldObjects {

    private final static String IMAGE_NAME = "images/eagle.jpg";

    public Eagle(int x, int y) {

        super(x, y);

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
