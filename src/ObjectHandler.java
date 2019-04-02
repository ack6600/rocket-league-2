import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantLock;

public class ObjectHandler {
    private ReentrantLock characterLock;
    private ArrayList<TestGameObject> gameObjects;
    private int size;

    public ObjectHandler(){
        characterLock = new ReentrantLock();
        gameObjects = new ArrayList<>();
    }

    public void addObject(TestGameObject toAdd){
        characterLock.lock();
        try{
            gameObjects.add(toAdd);
            this.size = gameObjects.size();
        }finally {
            characterLock.unlock();
        }
    }

    public TestGameObject getObject(int index){
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
