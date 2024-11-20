//Hadar Pinchasi

import java.util.List;

/**
 * making the information of each level.
 */
public interface LevelInformation {
    /**
     * the number of the balls.
     *
     * @return the number of the balls of the level.
     */
    int numberOfBalls();

    /**
     * initializing the  velocity of each ball.
     *
     * @return The initial velocity of each ball.
     */
    List<Velocity> initialBallVelocities();


    /**
     * the size of the paddle.
     *
     * @return the size of the paddle.
     */
    int paddleWidth();

    /**
     * the level name that will be displayed at the top of the screen.
     *
     * @return the name of the level.
     */
    String levelName();

    /**
     * the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * the blocks of each level.
     *
     * @return The Blocks that make up this level, each block contains its size, color and location.
     */

    List<Block> blocks();

    /**
     * the number of the blocks that should be removed.
     *
     * @return the number.
     */
    int numberOfBlocksToRemove();
}

