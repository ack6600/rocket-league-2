import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class CarObject extends GameObject {
    private double x, y;
    private BufferedImage sprite;
    private int[] controls;
    private long lastUpdateTime;

    private static final double velocity = 500.0;

    public CarObject(){
        //you should not be a coder
    }
    public CarObject(int startX, int startY, File sprite, int[] controls){
        x = startX;
        y = startY;
        this.controls = controls;
        try {
            this.sprite = ImageIO.read(sprite);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastUpdateTime = System.nanoTime();
    }

    @Override
    public int[] getCoords() {
        return new int[]{(int) x, (int) y};
    }

    @Override
    public BufferedImage getSprite() {
        return sprite;
    }

    @Override
    public void runPhysics(int[] controls, boolean[] statuses, GameObject[] allObjects) {
        y = updateTimedVariable(this.controls[0], this.controls[1], controls, statuses, lastUpdateTime, y, velocity);
        lastUpdateTime = System.nanoTime();
    }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int) x, (int) y, sprite.getWidth(), getSprite().getHeight());
    }

}
