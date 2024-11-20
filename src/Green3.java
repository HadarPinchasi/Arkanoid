import biuoop.DrawSurface;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * a information of level 3.
 */
public class Green3 implements LevelInformation {
    private final int numberOfBalls;
    private final List<Velocity> ballsVelocity;
    private final int paddleWidth;
    private final int numberOfBlocksToRemove;
    private final List<Block> blocks;
    private final String levelName;


    /**
     * a constructor.
     */
    public Green3() {
        this.numberOfBalls = 4;
        this.ballsVelocity = new ArrayList<>();
        this.paddleWidth = 130;
        this.numberOfBlocksToRemove = 57;
        this.blocks = new ArrayList<>();
        double x;
        double y = 120;
        java.awt.Color color = java.awt.Color.black;
        double height = 27;

        // Initialize blocks
        for (int i = 0; i < 6; i++) { //choosing colors according to the line.
            x = 716;
            if (i == 0) {
                color = new Color(0x080E73);
            } else if (i == 1) {
                color = (java.awt.Color.RED);
            } else if (i == 2) {
                color = (java.awt.Color.yellow);
            } else if (i == 3) {
                color = (java.awt.Color.blue);
            } else if (i == 4) {
                color = (java.awt.Color.pink);
            } else {
                color = (java.awt.Color.green);
            }
            for (int j = 0; j < 12 - i; j++) {
                Rectangle rect = new Rectangle(new Point(x, y), 44, 27);
                Block block = new Block(rect, color);
                x = x - block.getCollisionRectangle().getWidth();
                blocks.add(block);
            }
            y = y + height;
        }

        this.levelName = "It's raining outside";
    }

    @Override
    public int numberOfBalls() {
        return this.numberOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        double angle2 = 350;
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