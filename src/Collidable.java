//Hadar Pinchasi

/**
 * An interface representing objects that can be collided with.
 * Objects implementing this interface must provide details about their collision boundaries
 * and specify behavior when a collision occurs.
 */
public interface Collidable {
    /**
     * Retrieves the "collision shape" of the object, represented as a rectangle.
     * This shape defines the boundaries for detecting collisions.
     *
     * @return the Rectangle object representing the collision shape of the object.
     */
    Rectangle getCollisionRectangle();


    /**
     * Notifies the object that a collision has occurred at a specified point with a given velocity.
     * This method calculates and returns the new velocity of the object after the collision,
     * based on the impact point and the properties of the object.
     *
     * @param hitter the Ball object that hits the collidable object.
     * @param collisionPoint the Point where the collision occurs.
     * @param currentVelocity the current Velocity of the hitter at the moment of impact.
     * @return the new Velocity of the hitter after the collision is handled.
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
