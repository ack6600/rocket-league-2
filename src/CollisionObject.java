import java.awt.*;

public abstract class CollisionObject extends GameObject {

    public abstract Rectangle getBoundingBox();

    public boolean collides(CollisionObject collide){
        return collide.getBoundingBox().intersects(this.getBoundingBox());
    }
}
