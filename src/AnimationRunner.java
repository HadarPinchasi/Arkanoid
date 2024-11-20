//Hadar Pinchasi 315322784

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * class that running the animation.
 */
public class AnimationRunner {
    private final GUI gui;
    private final int framesPerSecond;
    private final Sleeper sleeper;

    /**
     * the constructor.
     *
     * @param gui the gui of the program.
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        this.sleeper= new Sleeper();
        this.framesPerSecond = 60;
    }

    /**
     * running the animation.
     *
     * @param animation the animation that is running.
     */
    public void run(Animation animation) {
        int millisecondsPerFrame = 1000 / framesPerSecond;//How many milliseconds are required for each frame
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            animation.doOneFrame(d);
            gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}