import javax.swing.*;
import java.awt.*;

public class GraphicsHandler {
    private Thread graphicsThread;
    private ObjectHandler objectHandler;
    private JPanel jPanel;

    public GraphicsHandler(final ObjectHandler objectHandler, final JPanel jPanel){
        this.objectHandler = objectHandler;
        this.jPanel = jPanel;
        Runnable drawRunnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    Image frame = jPanel.createImage(640, 480);
                    long startTime = System.nanoTime();
                    int size = objectHandler.getSize();
                    for(int i = 0; i < size; i++){
                        GameObject toDraw = objectHandler.getObject(i);
                        frame.getGraphics().drawImage(toDraw.getSprite(), toDraw.getCoords()[0], toDraw.getCoords()[1], null);
                    }
                    int fps = (int) (1/((double)(System.nanoTime() - startTime)/1000000000.0));
                    frame.getGraphics().drawString(String.valueOf(fps), 20, 20);
                    frame.getGraphics().drawString(String.valueOf(RocketLeague2.getScores()[0]), 300, 20);
                    frame.getGraphics().drawString(String.valueOf(RocketLeague2.getScores()[1]), 340, 20);
                    jPanel.getGraphics().drawImage(frame, 0, 0, null);
                }
            }
        };
        this.graphicsThread = new Thread(drawRunnable);
    }

    public void start(){
        graphicsThread.start();
    }
}
