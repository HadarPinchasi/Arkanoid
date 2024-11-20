//Hadar Pinchasi

import biuoop.DrawSurface;

/**
 * the screen on the case of winning.
 */
public class EndScreen implements Animation {
    private final boolean stop;
    private final Counter score;
    private boolean lose;

    /**
     * a constructor.
     *
     * @param score the score of ending the game.
     */
    public EndScreen(Counter score, boolean lose) {
        this.stop = false;
        this.score = score;
        this.lose= lose;

    }

    @Override
    public void doOneFrame(DrawSurface d) {
        if (lose){
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score.getValue(), 32);
        }
        else {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score.getValue(), 32);
        }
    }

    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
