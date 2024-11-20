//Hadar Pinchasi

import biuoop.DrawSurface;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of sprite objects. The SpriteCollection class
 * manages a dynamic list of sprites and provides methods for updating
 * and drawing all sprites in the collection.
 */

public class SpriteCollection {
    private final List<Sprite> sprites;

    /**
     * Constructor: Initializes a new SpriteCollection with an empty list of sprites.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Returns the list of sprites in the collection.
     *
     * @return a List of Sprite objects currently in the collection.
     */
    public List<Sprite> getSprites() {
        return this.sprites;
    }

    /**
     * Adds a sprite to the collection.
     *
     * @param s the sprite to be added to the collection.
     */
    public void addSprite(Sprite s) {
        sprites.add(s);
    }

    /**
     * Calls the timePassed() method on each sprite in the collection.
     * This method is used to update the state of all sprites over time.
     */
    public void notifyAllTimePassed() {
        List<Sprite> spritesCopy = new ArrayList<Sprite>(this.sprites);
        for (Sprite sprite : spritesCopy) {
            sprite.timePassed();
        }
    }

    /**
     * Draws all sprites on the given DrawSurface.
     *
     * @param d the DrawSurface on which the sprites will be drawn.
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite sprite : sprites) {
            sprite.drawOn(d);
        }
    }
}
