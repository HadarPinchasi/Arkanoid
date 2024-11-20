//Hadar Pinchasi

import biuoop.DrawSurface;

/**
 * the screen that being shown if stopping the game.
 */
public class PauseScreen implements Animation {
    private final boolean stop;


    public PauseScreen() {
        this.stop = false;
    }

    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}