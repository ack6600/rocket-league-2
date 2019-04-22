import java.awt.*;
import java.awt.image.BufferedImage;

public class PhysicsObject extends GameObject {
    private final double termVelocityY = 50.0;
    private final double termVelocityX = 50.0;
    private double xAccel, yAccel;
    private double xSpeed, ySpeed;
    private double x, y;

    public PhysicsObject(){

    }
    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int) x, (int) y, getSprite().getWidth(), getSprite().getHeight());
    }

    @Override
    public int[] getCoords() {
        return new int[]{(int) x, (int) y};
    }

    @Override
    public BufferedImage getSprite() {
        return null;
    }

    @Override
    public void runPhysics(int[] controls, boolean[] statuses) {

    }
}
