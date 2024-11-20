//Hadar Pinchasi

import biuoop.DrawSurface;

/**
 * class of animation.
 */
public interface Animation {
    /**
     * running one frame of the program.
     *
     * @param d the drawface
     */
    void doOneFrame(DrawSurface d);

    /**
     * making the animation to stop running.
     *
     * @return if the animation should be stopped.
     */
    boolean shouldStop();

}
