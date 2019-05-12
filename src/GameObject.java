import java.awt.*;
import java.awt.image.BufferedImage;

public abstract class GameObject {
    public abstract int[] getCoords();
    public abstract BufferedImage getSprite();
    public abstract void runPhysics(int[] controls, boolean[] statuses, GameObject[] allObjects);
    public abstract Rectangle getBoundingBox();
    public boolean collides(GameObject collide) {
        return collide.getBoundingBox().intersects(this.getBoundingBox());
    }

    public double updateTimedVariable(int increase, int decrease, int[] controls, boolean[] statuses, long lastTime, double currentValue, double changePerSec){
        long deltaTime = System.nanoTime() - lastTime;
        for(int i = 0; i < controls.length; i++) {
            if (controls[i] == increase) {
                if (statuses[i])
                    currentValue += changePerSec * (deltaTime / 1e9);
            }
            if (controls[i] == decrease) {
                if (statuses[i])
                    currentValue -= changePerSec * (deltaTime / 1e9);
            }
        }
        return currentValue;
    }

    public double updateTimedVariable(long lastTime, double currentValue, double changePerSec){
        long deltaTime = System.nanoTime() - lastTime;
        currentValue += changePerSec * (deltaTime / 1e9);
        return currentValue;
    }
}
