import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GameObject {
    private int x, y;
    private BufferedImage image;

    public GameObject(File imagePath, int x, int y){
        try {
            image = ImageIO.read(imagePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.x = x;
        this.y = y;
    }

    public int[] getCoords(){
        return new int[]{x, y};
    }

    public BufferedImage getSprite(){
        return image;
    }
}
