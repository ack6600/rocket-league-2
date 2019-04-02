import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class RocketLeague2 {
    private ObjectHandler objectHandler;
    private GraphicsHandler graphicsHandler;
    private PhysicsHandler physicsHandler;
    private JFrame root;

    public static void main(String[] args) {
        RocketLeague2 game = new RocketLeague2();
        game.start();
    }

    public RocketLeague2(){
        root = new JFrame();
        root.setSize(640, 480);
        JPanel jPanel = new JPanel();
        jPanel.setSize(640,480);
        jPanel.setVisible(true);
        root.add(jPanel);
        root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        objectHandler = new ObjectHandler();
        objectHandler.addObject(new TestGameObject(new File("dom.jpg"), 0, 0));
        objectHandler.addObject(new TestGameObject(new File("gioia1.jpg"), 50, 50));
        objectHandler.addObject(new TestGameObject(new File("gioia2.jpg"), 200, 200));

        graphicsHandler = new GraphicsHandler(objectHandler, jPanel);
        physicsHandler = new PhysicsHandler(objectHandler);
        physicsHandler.setControls(new int[]{
                KeyEvent.VK_RIGHT,
                KeyEvent.VK_LEFT,
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
}
