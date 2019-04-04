import javax.swing.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class RocketLeague2 {
    private ObjectHandler objectHandler;
    private GraphicsHandler graphicsHandler;
    private PhysicsHandler physicsHandler;
    private JFrame root;

    public static void main(String[] args) {
        RocketLeague2 game = new RocketLeague2(640, 480);
        game.start();
    }

    public RocketLeague2(int width, int height){
        root = new JFrame();
        root.setSize(width, height);
        JPanel jPanel = new JPanel();
        jPanel.setSize(width, height);
        jPanel.setVisible(true);
        root.add(jPanel);
        root.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        objectHandler = new ObjectHandler();
        objectHandler.addObject(new TestGameObject(new File("dom.jpg"), 0, 0));
        objectHandler.addObject(new TestGameObject(new File("gioia1.jpg"), 50, 50));
        objectHandler.addObject(new TestGameObject(new File("gioia2.jpg"), 200, 200));
        objectHandler.addObject(new GroundObject(new File("ground.png"), height));

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
