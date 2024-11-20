//Hadar Pinchasi 315322784
/**
 * the counting the score.
 */
public class ScoreTrackingListener implements HitListener {
    private final Counter currentScore;

    /**
     * a constructor.
     * @param scoreCounter the counter.
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * the hit event.
     * @param beingHit the block that is being hit.
     * @param hitter   the Ball that's doing the hitting.
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        this.currentScore.increase(5);
    }
}