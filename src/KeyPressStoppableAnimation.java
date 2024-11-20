//Hadar Pinchasi 315322784

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * A class that adds the ability to stop an animation when a specific key is pressed.
 * It wraps an existing animation and monitors for a key press to stop the animation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private final KeyboardSensor sensor;
    private final String key;
    private final Animation animation;
    private boolean stop;

    /**
     * Constructs a KeyPressStoppableAnimation with the specified keyboard sensor, key, and animation.
     *
     * @param sensor    the keyboard sensor to detect key presses
     * @param key       the key that will stop the animation when pressed
     * @param animation the animation to be controlled by this object
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
    }

    /**
     * Performs one frame of the animation.
     * If the specified key is pressed, the animation will stop.
     *
     * @param d the DrawSurface on which the animation will be drawn
     */
    public void doOneFrame(DrawSurface d) {
        if (sensor.isPressed(key)) {
                this.stop = true;
        }
        animation.doOneFrame(d);

    }

    /**
     * Checks whether the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */    public boolean shouldStop() {
        return this.stop;
    }
}
