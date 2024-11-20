//Hadar Pinchasi

import biuoop.DrawSurface;

/**
 * An interface representing a sprite, which is any object that can be drawn
 * on the screen and responds to time-based updates in the game.
 */
public interface Sprite {
    /**
     * Renders the sprite on the given drawing surface.
     *
     * @param d the surface on which the sprite should be drawn.
     *          This surface is provided by the game loop and represents the
     *          current state of the screen.
     */
    void drawOn(DrawSurface d);

    /**
     * Updates the sprite's state to reflect the passage of time.
     * This method is called periodically by the game loop, allowing
     * the sprite to perform animations, handle logic, or update its properties.
     */
    void timePassed();
}
