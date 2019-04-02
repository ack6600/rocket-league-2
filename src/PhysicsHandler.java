public class PhysicsHandler {
    private Thread physicsThread;
    private ObjectHandler objectHandler;

    public PhysicsHandler(ObjectHandler objectHandler){
        this.objectHandler = objectHandler;
        this.physicsThread = new Thread();
    }

    public void start(){
        physicsThread.start();
    }
}
