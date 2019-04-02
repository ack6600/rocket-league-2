import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectHandler {
    private ReentrantLock characterLock;
    private ArrayList<GameObject> gameObjects;
    private int size;

    public ObjectHandler(){
        characterLock = new ReentrantLock();
        gameObjects = new ArrayList<>();
    }

    public void addObject(GameObject toAdd){
        characterLock.lock();
        try{
            gameObjects.add(toAdd);
            this.size = gameObjects.size();
        }finally {
            characterLock.unlock();
        }
    }

    public GameObject getObject(int index){
        characterLock.lock();
        try{
            return gameObjects.get(index);
        }finally {
            characterLock.unlock();
        }
    }

    public int getSize(){
        characterLock.lock();
        try{
            return size;
        }finally {
            characterLock.unlock();
        }
    }

}
