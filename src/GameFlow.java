//Hadar Pinchasi
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;

/**
 * The GameFlow class is responsible for managing the flow of the game by
 * running levels sequentially, tracking the player's score, and handling win or lose conditions.
 */
public class GameFlow {
    private final AnimationRunner animationRunner;
    private final KeyboardSensor keyboardSensor;
    private final GUI gui;
    private final Counter score;
    private boolean lose = false;

    /**
     * Constructor to initialize the GameFlow instance.
     *
     * @param animationRunner The AnimationRunner responsible for running animations for each level.
     * @param keyboardSensor The KeyboardSensor to detect player input.
     * @param gui The GUI object that manages the game window.
     */
    public GameFlow(AnimationRunner animationRunner, KeyboardSensor keyboardSensor, GUI gui) {
        this.animationRunner = animationRunner;
        this.gui = gui;
        this.keyboardSensor = keyboardSensor;
        this.score = new Counter(0);
    }

    /**
     * Runs the levels of the game in sequence. Each level will continue until the player either
     * loses all balls or clears all blocks. After all levels are completed or the player loses,
     * an end screen (win or lose) is displayed.
     *
     * @param levels List of LevelInformation objects representing the game levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo,
                    this.keyboardSensor, this.gui,
                     score);
            level.initialize();
            // Run the current level until completion or loss of all balls
         //   while ((level.getCounterBalls().getValue() != 0) && (level.getCounterBlocks().getValue() != 0)) {
                level.run();
        //    }
            if (level.getCounterBalls().getValue() == 0) {
                lose = true;
                break;
            }
        }
        showEndScreen(new EndScreen( this.score,lose));

    }
    /**
     * Helper method to display an end screen for either win or lose outcome and close the GUI if needed.
     *
     * @param endScreen The Animation to be displayed (either win or lose screen).
     */
    private void showEndScreen(Animation endScreen) {
        KeyPressStoppableAnimation keyPressStoppableAnimation =
                new KeyPressStoppableAnimation(this.keyboardSensor, keyboardSensor.SPACE_KEY, endScreen);
        this.animationRunner.run(keyPressStoppableAnimation);
        if (keyPressStoppableAnimation.shouldStop()) {
            gui.close();
        }
    }
}
