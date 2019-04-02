import java.awt.image.BufferedImage;

public abstract class GameObject {
    public abstract int[] getCoords();
    public abstract BufferedImage getSprite();
    public abstract void runPhysics(int[] controls, boolean[] statuses);
}
