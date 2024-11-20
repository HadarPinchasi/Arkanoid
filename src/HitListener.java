//Hadar Pinchasi

/**
 * An interface for objects that listen to hit events.
 * Classes implementing this interface can receive notifications when
 * a Block object is hit by a Ball object.
 */
public interface HitListener {


    /**
     * This method is called when the specified Block object is hit by the specified Ball object.
     *
     * @param beingHit the Block object that was hit.
     * @param hitter   the Ball object that hit the Block.
     */
    void hitEvent(Block beingHit, Ball hitter);
}
