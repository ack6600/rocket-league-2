import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WallObject extends GameObject {
    private int x, y;
    private BufferedImage sprite;
    private Side side;
    public enum Side{
        Top, Bottom, Left, Right
    }

    public WallObject(Side side, int width, int height){
        try {
            if(side == Side.Top || side == Side.Bottom)
                sprite = ImageIO.read(new File("groundHor.png"));
            else if(side == Side.Left || side == Side.Right)
                sprite = ImageIO.read(new File("groundVert.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(side == Side.Left){
            x = -25;
            y = -10;
        }
        else if(side == Side.Top){
            x = -10;
            y = -25;
        }
        else if(side == Side.Right){
            x = width - 7;
            y = -10;
        }
        else if(side == Side.Bottom){
            x = -10;
            y = height - 7;
        }
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
    public void runPhysics(int[] controls, boolean[] statuses, GameObject[] allObjects) {}

    public Side getSide(){
        return side;
    }

}