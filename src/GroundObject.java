import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GroundObject extends GameObject {
    private int x, y;
    private BufferedImage sprite;

    public GroundObject(File image, int windowHeight){
        try {
            sprite = ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.y = windowHeight - (2 * sprite.getHeight() + 6);
        this.x = 0;
    }
    @Override
    public Rectangle getBoundingBox() {
        return new Rectangle(x, y, sprite.getWidth(), sprite.getHeight());
    }

    @Override
    public int[] getCoords() {
        return new int[]{x, y};
    }

    @Override
    public BufferedImage getSprite() {
        return this.sprite;
    }

    @Override
    public void runPhysics(int[] controls, boolean[] statuses) {}

    @Override
    public void runCollisions(GameObject[] allObjects) {

    }
}