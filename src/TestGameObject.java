import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TestGameObject extends GameObject{
    private double x, y;
    private BufferedImage image;
    private long lastUpdateTime;

    private final double speed = 20.0;

    public TestGameObject(File imagePath, int x, int y){
        try {
            image = ImageIO.read(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        lastUpdateTime = System.nanoTime();
        this.x = x;
        this.y = y;
    }

    public int[] getCoords(){
        return new int[]{(int) x, (int) y};
    }

    public BufferedImage getSprite(){
        return image;
    }

    public void runPhysics(int[] controls, boolean[] statuses){
        long deltaTime = System.nanoTime() - lastUpdateTime;
        for(int i = 0; i < controls.length; i++){
            if(controls[i] == KeyEvent.VK_UP) {
                if (statuses[i])
                    y -= speed * ((double) deltaTime / 1e9d);
            }
            if(controls[i] == KeyEvent.VK_DOWN){
                if(statuses[i])
                    y += speed * ((double) deltaTime / 1e9d);
            }
            if(controls[i] == KeyEvent.VK_RIGHT) {
                if (statuses[i])
                    x += speed * ((double) deltaTime / 1e9d);
            }
            if(controls[i] == KeyEvent.VK_LEFT){
                if(statuses[i])
                    x -= speed * ((double) deltaTime / 1e9d);
            }
        }
        lastUpdateTime = System.nanoTime();
    }
}
