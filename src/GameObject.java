import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    public abstract int[] getCoords();
    public abstract BufferedImage getSprite();
    public abstract void runPhysics(int[] controls, boolean[] statuses);
    public abstract Rectangle getBoundingBox();
    public boolean collides(GameObject collide){
        return collide.getBoundingBox().intersects(this.getBoundingBox());
    }
    public abstract void runCollisions();
}
