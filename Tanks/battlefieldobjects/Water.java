package tanks.battlefieldobjects;

import tanks.Launcher;

import javax.imageio.ImageIO;
import java.awt.*;

import java.awt.image.*;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.net.URL;

public class Water extends BattleFieldObjects {

   private final static String IMAGE_NAME =  "images/water.jpg";

    public Water(int x, int y) {
        super(x,y);

        URL pathToResource = Launcher.class.getResource(IMAGE_NAME);

        try{
            image = ImageIO.read(pathToResource);
        }catch (IOException e){
            System.err.println("Can't find image: " + IMAGE_NAME);
        }

    }

    @Override
    public void destroy() {

    }

    @Override
    public void draw(Graphics g) {
        Graphics2D g2d              = (Graphics2D)g;
        Composite currentComposite  = g2d.getComposite();

        Composite translicent = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f);

        g2d.setComposite(translicent);

        if (!isDestroyed()) {
            g.setColor(getColor());
            g.fillRect(getX(), getY(), 64, 64);
            g.drawImage(image, getX(), getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }

        g2d.setComposite(currentComposite);

    }

    public static BufferedImage makeImageTranslucent(BufferedImage source,
                                                     double alpha) {
        BufferedImage target = new BufferedImage(source.getWidth(),
                source.getHeight(), Transparency.TRANSLUCENT);
        // Get the images graphics
        Graphics2D g = target.createGraphics();
        // Set the Graphics composite to Alpha
        g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC,
                (float) alpha));
        // Draw the image into the prepared reciver image
        g.drawImage(source, null, 0, 0);
        // let go of all system resources in this Graphics
        g.dispose();
        // Return the image
        return target;
    }

}
