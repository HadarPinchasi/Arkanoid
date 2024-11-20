//Hadar Pinchasi

/**
 * removing the ball.
 */
public class BallRemover implements HitListener {
    private final Counter counter;
    private final GameLevel game;

    /**
     * removing the ball from the game.
     *
     * @param counter counting how many balls.
     * @param game    the game of the balls.
     */
    public BallRemover(Counter counter, GameLevel game) {
        this.counter = counter;
        this.game = game;
    }

    /**
     * called when there is a hit.
     *
     * @param beingHit the object that is being hit.
     * @param hitter   the hitting object.
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.counter.decrease(1);
    }
}
