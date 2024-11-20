//Hadar Pinchasi

import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * designing the fourth level.
 */
public class FinalFour implements LevelInformation {
    private final int numberOfBalls;
    private final List<Velocity> ballsVelocity;
    private final int paddleWidth;
    private final int numberOfBlocksToRemove;
    private final List<Block> blocks;
    private final String levelName;

    /**
     * the constructor.
     */
    public FinalFour() {
        this.numberOfBalls = 3;
        this.ballsVelocity = new ArrayList<>();
        this.paddleWidth = 120;
        this.numberOfBlocksToRemove = 105;
        this.blocks = new ArrayList<>();
        double x;
        double y = 120;
        java.awt.Color color = java.awt.Color.black;
        double height = 27;
        double width = 48;
        for (int i = 0; i < 7; i++) { //choosing colors according to the line.
            x = 712;
            if (i == 0) {
                color = new Color(0xDC1B6B);
            } else if (i == 1) {
                color = (java.awt.Color.RED);
            } else if (i == 2) {
                color = new Color(0x2A2064);
            } else if (i == 3) {
                color = (java.awt.Color.blue);
            } else if (i == 4) {
                color = new Color(0x1BDCC5);
            } else if (i == 5) {
                color = new Color(0x572B96);
            } else {
                color = (Color.lightGray);
            }
            for (int j = 0; j < 15; j++) {
                Point point = new Point(x, y);
                Rectangle rect = new Rectangle(point, width, height);
                Block block = new Block(rect, color);
                x = x - block.getCollisionRectangle().getWidth();
                blocks.add(block);
            }
            y = y + height;
        }
        this.levelName = "Working Hard";

    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        double angle2 = 320;
        double speed2 = 5;
        for (int i = 0; i < numberOfBalls; i++) {
            this.ballsVelocity.add(Velocity.fromAngleAndSpeed(angle2, speed2));
            angle2 = angle2 + 10;
            if (angle2 == 360) {
                angle2 = 0;
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
