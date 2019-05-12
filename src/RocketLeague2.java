import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class RocketLeague2 {
    private ObjectHandler objectHandler;
    private GraphicsHandler graphicsHandler;
    private PhysicsHandler physicsHandler;
    private JFrame root;

    public static int blueScore = 0;
    public static int redScore = 0;

    public static void main(String[] args) {
        RocketLeague2 game = new RocketLeague2(640, 480);
        game.start();
    }

    public RocketLeague2(int width, int height){
        root = new JFrame();
        JPanel jPanel = new JPanel();
        jPanel.setVisible(true);
        jPanel.setSize(width, height);
        jPanel.setPreferredSize(new Dimension(width, height));
        root.setResizable(false);
        root.add(jPanel);
        root.pack();
        root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        objectHandler = new ObjectHandler();
        objectHandler.addObject(new CarObject(jPanel.getWidth() - 56, 10, new File("red.png"), new int[]{KeyEvent.VK_DOWN, KeyEvent.VK_UP}));
        objectHandler.addObject(new CarObject(0, 10, new File("blue.png"), new int[]{KeyEvent.VK_S, KeyEvent.VK_W}));
        objectHandler.addObject(new BallObject(width / 2, height / 2, new File("ball.png"), 500.0, 50.0));
        objectHandler.addObject(new WallObject(WallObject.Side.Left, width, height));
        objectHandler.addObject(new WallObject(WallObject.Side.Top, width, height));
        objectHandler.addObject(new WallObject(WallObject.Side.Right, width, height));
        objectHandler.addObject(new WallObject(WallObject.Side.Bottom, width, height));

        graphicsHandler = new GraphicsHandler(objectHandler, jPanel);
        physicsHandler = new PhysicsHandler(objectHandler);
        physicsHandler.setControls(new int[]{
                KeyEvent.VK_W,
                KeyEvent.VK_S,
                KeyEvent.VK_UP,
                KeyEvent.VK_DOWN
        });
        root.addKeyListener(physicsHandler);
    }

    public void start(){
        root.setVisible(true);
        graphicsHandler.start();
        try {
            physicsHandler.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void incrementScore(int side){
        if(side == 0){
            blueScore++;
        }else{
            redScore++;
        }
    }

    public static int[] getScores(){
        return new int[]{blueScore, redScore};
    }
}
