//Hadar Pinchasi

/**
 * A class that encapsulates information about a collision event.
 * This includes the point where the collision occurs and the collidable
 * object that is involved in the collision.
 */
public class CollisionInfo {
    private final Collidable collidable;
    private final Point collisionPoint;

    /**
     * Constructs a new CollisionInfo instance.
     * Initializes the collision point and the collidable object involved in the collision.
     *
     * @param collisionPoint the Point where the collision takes place.
     * @param collidable     the Collidable object that is involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collidable) {
        this.collidable = collidable;
        this.collisionPoint = collisionPoint;
    }

    /**
     * Retrieves the point where the collision occurs.
     *
     * @return the Point object representing the collision point.
     */
    public Point getCollisionPoint() {
        return this.collisionPoint;
    }


    /**
     * Retrieves the collidable object that was involved in the collision.
     *
     * @return the Collidable object that was involved in the collision.
     */
    public Collidable GetCollisionObject() {
        return this.collidable;
    }
}

