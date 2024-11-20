//Hadar Pinchasi

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * designing the first level.
 */
public class DirectHit implements LevelInformation {
    private final int numberOfBalls;
    private final List<Velocity> ballsVelocity;
    private final int paddleWidth;
    private final int numberOfBlocksToRemove;
    private final List<Block> blocks;
    private final String levelName;


    /**
     * the constructor.
     */
    public DirectHit() {
        this.numberOfBalls = 1;
        this.ballsVelocity = new ArrayList<>();
        this.ballsVelocity.add(Velocity.fromAngleAndSpeed(0, 3));
        this.paddleWidth = 100;
        this.numberOfBlocksToRemove = 1;
        this.blocks = new ArrayList<>();
        Rectangle rectangle = new Rectangle(new Point(400, 200), 40, 40);
        Block singleBlock = new Block(rectangle, Color.red);
        blocks.add(singleBlock);
        this.levelName = "Direct Hit";

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return ballsVelocity;
    }


    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return BackgroundFactory.createBackground(this.levelName());

    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }
}
