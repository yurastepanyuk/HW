package tanks.battlefieldobjects;

import tanks.Launcher;

import javax.imageio.ImageIO;
import java.awt.*;

import java.io.IOException;
import java.net.URL;

public class Empty extends BattleFieldObjects {

    private final static String IMAGE_NAME =  "images/empty.jpg";

    public Empty(int x, int y) {
        super(x, y);
        setColor(new Color(0, 0, 0));

        URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

        try{
            image = ImageIO.read(pathToResource);
        } catch (IOException e){
            System.err.println("Cann't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void draw(Graphics g) {

        g.setColor(getColor());
        g.fillRect(getX(), getY(), 64, 64);

    }

}
