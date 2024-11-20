// Hadar Pinchasi

import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.util.ArrayList;
import java.util.List;

/**
 * The class initializes and runs the Arkanoid game.
 */
public class GameMain {

    /**
     * Main method to start the game. Parses input arguments for level selection
     * and runs the game with the specified or default levels.
     *
     * @param args Command-line arguments: "1" - DirectHit, "2" - WideEasy,
     *             "3" - Green3, "4" - FinalFour. Defaults are used if none provided.
     */
    public static void main(String[] args) {
        // Initialize game components
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboardSensor = gui.getKeyboardSensor();
        List<LevelInformation> defaultLevels = List.of(
                new DirectHit(), new WideEasy(), new Green3(), new FinalFour()
        );

        // Prepare custom levels based on input
        List<LevelInformation> customLevels = new ArrayList<>();
        for (String arg : args) {
            switch (arg) {
                case "1": customLevels.add(new DirectHit()); break;
                case "2": customLevels.add(new WideEasy()); break;
                case "3": customLevels.add(new Green3()); break;
                case "4": customLevels.add(new FinalFour()); break;
                default: break; // Ignore invalid input
            }
        }

        // Run the game
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow gameFlow = new GameFlow(runner, keyboardSensor, gui);
        gameFlow.runLevels(customLevels.isEmpty() ? defaultLevels : customLevels);
    }
}
