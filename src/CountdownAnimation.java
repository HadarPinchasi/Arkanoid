import biuoop.DrawSurface;

import java.awt.*;

/**
 * The CountdownAnimation class handles displaying a countdown animation
 * before the game starts, including the background and game screen elements.
 */
public class CountdownAnimation implements Animation {
    private final SpriteCollection spriteCollection;
    private boolean stop;
    private final long startTime;
    private final LevelInformation levelInformation;

    /**
     * Constructs a CountdownAnimation instance.
     *
     * @param gameScreen       the sprites to display during the countdown.
     * @param levelInformation the current level information, including the background.
     */
    public CountdownAnimation(SpriteCollection gameScreen, LevelInformation levelInformation) {
        this.spriteCollection = gameScreen;
        this.stop = false;
        this.startTime = System.currentTimeMillis(); // Record the start time
        this.levelInformation = levelInformation;
    }

    /**
     * Displays one frame of the countdown, including the background, game elements,
     * and a countdown message ("Ready?", "Set", "Go!").
     *
     * @param d the DrawSurface to draw on.
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        // Draw background and game elements
        levelInformation.getBackground().drawOn(d);
        spriteCollection.drawAllOn(d);

        // Calculate remaining seconds for the countdown
        long elapsedTime = System.currentTimeMillis() - startTime;
        int remainingSeconds = 3 - (int) (elapsedTime / 1000);

        // Determine the countdown message
        String message;
        if (remainingSeconds == 3) {
            message = "Ready?";
        } else if (remainingSeconds == 2) {
            message = "Set";
        } else if (remainingSeconds == 1) {
            message = "Go!";
        } else {
            message = "";
        }

        // Draw the countdown message
        if (!message.isEmpty()) {
            Color color = new Color(128, 0, 128).brighter();
            d.setColor(color);
            d.drawText(395, 400, message, 40);
        }

        // Stop the animation when the countdown ends
        if (remainingSeconds == 0) {
            this.stop = true;
        }
    }

    /**
     * Indicates whether the animation should stop.
     *
     * @return true if the countdown is finished; false otherwise.
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
