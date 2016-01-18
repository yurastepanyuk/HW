package tanks.battlefieldobjects;

import tanks.Launcher;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

public class Brick extends BattleFieldObjects {

    private final static String IMAGE_NAME =  "images/bfBricks.jpg";

    public Brick(int x, int y) {
        super(x, y);

        URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

        try{
            image = ImageIO.read(pathToResource);
        }catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed()) {
            g.drawImage(image, getX(), getY(),getX()+64, getY()+64, getX(), getY(),getX()+64, getY()+64, new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        } else {
            g.setColor(Color.BLACK);
            g.fillRect(getX(), getY(), 64, 64);
        }
    }
}
