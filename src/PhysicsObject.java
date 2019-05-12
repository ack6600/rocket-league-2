import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PhysicsObject extends GameObject {
    private final double termVelocityY = 50.0;
    private final double termVelocityX = 50.0;
    private final double xAccel, yAccel;
    private final double friction;
    private double xSpeed, ySpeed;
    private double x, y;
    private long lastUpdateTime;
    private BufferedImage sprite;

    public PhysicsObject(double xAccel, double yAccel, double startX, double startY, File image, double friction){
        this.xAccel = xAccel;
        this.yAccel = yAccel;
        this.friction = friction;
        this.xSpeed = 0;
        this.ySpeed = 0;
        this.x = startX;
        this.y = startY;
        lastUpdateTime = System.nanoTime();
        try {
            sprite = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        return sprite;
    }

    @Override
    public void runPhysics(int[] controls, boolean[] statuses, GameObject[] allObjects) {
        ySpeed = updateTimedVariable(KeyEvent.VK_DOWN, KeyEvent.VK_UP, controls, statuses, lastUpdateTime, ySpeed, yAccel);
        xSpeed = updateTimedVariable(KeyEvent.VK_RIGHT, KeyEvent.VK_LEFT, controls, statuses, lastUpdateTime, xSpeed, xAccel);
        ySpeed = doFriction(ySpeed, lastUpdateTime);
        xSpeed = doFriction(xSpeed, lastUpdateTime);
        if(xSpeed > termVelocityX)
            xSpeed = termVelocityX;
        else if(xSpeed < -termVelocityX)
            xSpeed = -termVelocityX;
        if(ySpeed > termVelocityY)
            ySpeed = termVelocityY;
        else if(ySpeed < -termVelocityY)
            ySpeed = -termVelocityY;
        x = updateTimedVariable(lastUpdateTime, x, xSpeed);
        y = updateTimedVariable(lastUpdateTime, y, ySpeed);
        System.out.printf("y pos %f, y vel %f, y accel %f \n", y, ySpeed, yAccel);
        lastUpdateTime = System.nanoTime();
    }

    private double doFriction(double speed, long lastUpdateTime){
        if(speed == 0)
            return speed;
        else if (speed > 0)
            return updateTimedVariable(lastUpdateTime, speed, -friction);
        else
            return updateTimedVariable(lastUpdateTime, speed, friction);
    }


}
