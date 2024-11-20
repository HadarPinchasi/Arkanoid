//Hadar Pinchasi 315322784

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * the information of the second level.
 */
public class WideEasy implements LevelInformation {
    private final int numberOfBalls;
    private final List<Velocity> ballsVelocity;
    private final int paddleWidth;
    private final int numberOfBlocksToRemove;
    private final List<Block> blocks;
    private final String levelName;




    /**
     * the constructor.
     */
    public WideEasy() {
        this.numberOfBalls = 10;
        this.ballsVelocity = new ArrayList<>();
        this.paddleWidth = 400;
        this.numberOfBlocksToRemove = 15;
        this.blocks = new ArrayList<>();
        double x = 712;
        double y = 200;
        Color colorBlock = null;
        for (int i = 0; i < numberOfBlocksToRemove; i++) {
            Rectangle rectangle = new Rectangle(new Point(x, y), 48, 30);
            if ((i == 0) || (i == 1)) {
                colorBlock = new Color(0x1BDCC5);
            }
            if ((i == 2) || (i == 3)) {
                colorBlock = Color.pink;
            }
            if ((i == 4) || (i == 5)) {
                colorBlock = Color.green;
            }
            if ((i == 6) || (i == 7) || (i == 8)) {
                colorBlock = Color.yellow;
            }
            if ((i == 9) || (i == 10)) {
                colorBlock = Color.lightGray;
            }
            if ((i == 11) || (i == 12)) {
                colorBlock = Color.red;
            }
            if ((i == 13) || (i == 14)) {
                colorBlock = Color.orange;
            }
            Block block = new Block(rectangle, colorBlock);
            x = x - block.getCollisionRectangle().getWidth();
            blocks.add(block);
        }
        this.levelName = "Wide Easy";
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        double angle = 320;
        double speed = 7;
        for (int i = 0; i < numberOfBalls; i++) {
            this.ballsVelocity.add(Velocity.fromAngleAndSpeed(angle, speed));
            angle = angle + 10;
            if (angle == 360) {
                angle = 0;
            }
        }
        return this.ballsVelocity;
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
