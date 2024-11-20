//Hadar Pinchasi

/**
 * Responsible for removing blocks from the game upon being hit and for tracking
 * the remaining blocks count.
 */

public class BlockRemover implements HitListener {
    private final GameLevel game;
    private final Counter remainingBlocks;

    /**
     * Constructs a BlockRemover object with the specified game and counter for tracking removed blocks.
     *
     * @param game the GameLevel from which blocks will be removed.
     * @param removedBlocks the counter that tracks the remaining blocks in the game.
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Handles hit events by removing the hit block from the game and updating the remaining blocks count.
     *
     * @param beingHit the Block that was hit.
     * @param hitter the Ball that hit the Block.
     */

    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}


