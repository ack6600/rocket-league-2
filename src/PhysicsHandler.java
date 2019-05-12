import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PhysicsHandler implements KeyListener {
    private Thread physicsThread;
    private ObjectHandler objectHandler;
    private int[] controlKeycodes;
    private boolean[] statuses;

    public PhysicsHandler(final ObjectHandler objectHandler){
        this.objectHandler = objectHandler;
        Runnable physicsRunnable = new Runnable() {
            @Override
            public void run() {
                while(true){
                    int size = objectHandler.getSize();
                    for(int i = 0; i < size; i++) {
                        objectHandler.getObject(i).runPhysics(controlKeycodes, statuses, objectHandler.getAllObjects());
                    }
                }
            }
        };
        this.physicsThread = new Thread(physicsRunnable);
    }

    public void start() throws Exception {
        if(statuses == null)
            throw new Exception("Controls not initialized");
        physicsThread.start();
    }

    public void setControls(int[] keycodes){
        controlKeycodes = keycodes;
        statuses = new boolean[controlKeycodes.length];
        for(int i = 0; i < statuses.length; i++)
            statuses[i] = false;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for(int i = 0; i < controlKeycodes.length; i++){
            if(e.getKeyCode() == controlKeycodes[i]) {
                statuses[i] = true;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        for(int i = 0; i < controlKeycodes.length; i++){
            if(e.getKeyCode() == controlKeycodes[i])
                statuses[i] = false;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}
