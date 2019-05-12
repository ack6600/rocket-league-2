import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BallObject extends GameObject {
    private double x, y;
    private final int startX, startY;
    private BufferedImage sprite;
    private double velocityX, velocityY;
    private final double maxVelocity;
    private double angle;
    private long lastUpdateTime;

    public BallObject(int startX, int startY, File image, double maxVelocity, double startAngle){
        this.startX = startX;
        this.startY = startY;
        x = startX;
        y = startY;
        try {
            sprite = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.maxVelocity = maxVelocity;
        this.angle = startAngle;
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
        for(GameObject object : allObjects){
            if(object.getClass() == CarObject.class){
                if(this.collides(object)){
                    this.angle += 180.0 - 2.0 * angle;
                    if(object.getCoords()[0] > 300)
                        x -= 10;
                    else
                        x += 10;
                }
            }
            else if(object.getClass() == WallObject.class){
                boolean side = object.getCoords()[0] != -10;
                if(this.collides(object)){
                    if(side){
                        if(x > 300){
                            RocketLeague2.incrementScore(0);
                        }else {
                            RocketLeague2.incrementScore(1);
                        }
                        x = startX;
                        y = startY;
                    }else {
                        this.angle += 180.0 + (2.0 * (90.0 - angle));
                        if(object.getCoords()[1] > 300)
                            y -= 10;
                        else
                            y += 10;
                    }
                }
            }
        }
        velocityX = Math.cos(Math.toRadians(angle)) * maxVelocity;
        velocityY = Math.sin(Math.toRadians(angle)) * maxVelocity;
        x = updateTimedVariable(lastUpdateTime, x, velocityX);
        y = updateTimedVariable(lastUpdateTime, y, velocityY);
        lastUpdateTime = System.nanoTime();
     }

    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle((int) x, (int) y, getSprite().getWidth(), getSprite().getHeight());
    }
}
