import javax.swing.*;
import java.io.File;

public class RocketLeague2 {
    ObjectHandler objectHandler;
    GraphicsHandler graphicsHandler;
    PhysicsHandler physicsHandler;
    JFrame root;

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
        objectHandler.addObject(new GameObject(new File("dom.jpg"), 50, 50));

        graphicsHandler = new GraphicsHandler(objectHandler, jPanel);
        physicsHandler = new PhysicsHandler(objectHandler);
    }

    public void start(){
        root.setVisible(true);
        graphicsHandler.start();
        physicsHandler.start();
    }
}
