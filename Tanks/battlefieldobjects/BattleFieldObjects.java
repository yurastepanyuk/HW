package tanks.battlefieldobjects;

import tanks.commoninterface.BattleFieldObject;

import java.awt.*;
import java.awt.image.ImageObserver;

public abstract class BattleFieldObjects implements BattleFieldObject {

    private Color color;

    // current position on BF
    private int x;
    private int y;

    private boolean isDestroyed = false;

    protected Image image;

    public BattleFieldObjects(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed()) {
            g.setPaintMode();
            g.drawImage(image, getX(), getY(), new ImageObserver() {
                @Override
                public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height) {
                    return false;
                }
            });
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int [] getYXnow(){
        int [] xyNow = new int [2];

        xyNow[0] = getY() / 64;
        xyNow[1] = getX() / 64;

        return xyNow;
    }

}
