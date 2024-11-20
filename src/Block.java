//Hadar Pinchasi

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * the class represent a block in the game.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private final Rectangle rectangle;
    private final Color color;
    private final List<HitListener> hitListeners;

    /**
     * a constructor .
     *
     * @param rectangle the shape of the block.
     * @param color     the color of the block.
     */
    public Block(Rectangle rectangle, Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.hitListeners = new ArrayList<>();
    }

    /**
     * a getter to the shape of the block.
     *
     * @return
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return rectangle;
    }

    /**
     * changing the hit of the collidible according to the hit point.
     *
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the velocity of the object.
     * @return
     */

    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        this.notifyHit(hitter);
        double newDx = currentVelocity.getdx();
        double newDy = currentVelocity.getdy();
        if(LineUtils.isPointInsideLineSegment(rectangle.lineList().get(0), collisionPoint)) {
            newDy = (-1) * newDy;
        }
        if(LineUtils.isPointInsideLineSegment(rectangle.lineList().get(2), collisionPoint)) {
            newDy = (-1) * newDy;
        }
        if(LineUtils.isPointInsideLineSegment(rectangle.lineList().get(1), collisionPoint)) {
            newDx = (-1) * newDx;
        }
        if(LineUtils.isPointInsideLineSegment(rectangle.lineList().get(3), collisionPoint)) {
            newDx = (-1) * newDx;
        }
        return new Velocity(newDx, newDy);

    }

    /**
     * a getter to the color of the block.
     *
     * @return the color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * drawing the block on a surface.
     *
     * @param surface the surface that the block is being drawn on.
     */
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(this.getColor());
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        surface.setColor(Color.black);
        surface.drawRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * implement the timepassed method.
     */
    @Override
    public void timePassed() {
    }

    /**
     * adding the block to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }

    /**
     * removing the block from game.
     *
     * @param game the game that the block is being removed from.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }


    /**
     * Manages listeners for hit events by addin  them.
     * This allows other objects to listen for and respond to hit events.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Removes a listener from the list of hit event listeners.
     *
     * @param hl the HitListener to remove from the list.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notifies all registered HitListeners about a hit event by invoking
     * their hitEvent method. This method makes a copy of the listeners list
     * to prevent concurrent modification issues during notification.
     *
     * @param hitter the Ball object that caused the hit event.
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}
