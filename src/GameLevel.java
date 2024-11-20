//Hadar Pinchasi

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The GameLevel class represents an individual level in the game, managing
 * its environment, animations, and core gameplay logic.
 * It interacts with game entities, handles player inputs, and manages game
 * events like pausing and score updates.
 */

public class GameLevel implements Animation {
    private final SpriteCollection sprites;
    private final GameEnvironment environment;
    private final GUI gui;
    private Counter counterBlocks;
    private Counter counterBalls;
    private final Counter score;
    private AnimationRunner runner;
    private boolean running;
    private final KeyboardSensor keyboard;
    private final LevelInformation levelInformation;

    /**
     * Constructor to initialize GameLevel with level-specific settings and environment.
     *
     * @param levelInformation Details of the current level, such as initial blocks and balls.
     * @param keyboardSensor Keyboard sensor for handling user input.
     * @param gui GUI object representing the game window.
     * @param score Counter to track the player’s score.
     */
    public GameLevel(LevelInformation levelInformation, KeyboardSensor keyboardSensor,
                     GUI gui, Counter score) {
        this.levelInformation = levelInformation;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.keyboard = keyboardSensor;
        this.gui = gui;
        this.score = score;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c The collidable object to be added.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the game, allowing it to be drawn on the screen
     * and updated as part of the game loop.
     *
     * @param s The sprite object to be added to the game.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c The collidable object to be removed.
     */
    public void removeCollidable(Collidable c) {
        this.environment.getColli().remove(c);

    }

    /**
     * Removes a sprite object from the game.
     *
     * @param s The sprite to be removed.
     */
    public void removeSprite(Sprite s) {
        this.sprites.getSprites().remove(s);
    }

    /**
     * Initializes the game level, setting up all level components such as
     * blocks, balls, and paddle according to the level information.
     */
    public void initialize() {
        LevelSetup levelSetup = new LevelSetup(this, this.levelInformation);
        levelSetup.initializeLevelComponents();
    }


    /**
     * Runs the game loop, starting the level's animations and allowing for
     * user interactions and game events.
     */
    public void run() {
        this.running = true;
        this.gui.getDrawSurface();
        this.runner = new AnimationRunner(this.gui);
        this.runner.run(new CountdownAnimation(sprites,levelInformation));
        this.runner.run(this);
    }

    public void doOneFrame(DrawSurface d) {
        this.levelInformation.getBackground().drawOn(d);
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        this.levelInformation.getBackground().timePassed();


        // Handle pause functionality
        if (this.keyboard.isPressed("p")) {
            PauseScreen pauseScreen = new PauseScreen();
            KeyPressStoppableAnimation keyPressStoppableAnimation = new KeyPressStoppableAnimation(this.keyboard,
                    this.keyboard.SPACE_KEY, pauseScreen);
            this.runner.run(keyPressStoppableAnimation);
        }
        // End the level if no balls remain
        if (counterBalls.getValue() == 0) {
            this.running = false;
        }
        // End the level if no blocks remain and increase score
        if (counterBlocks.getValue() == 0) {
            score.increase(100);
            this.running = false;
        }
    }
    /**
     * Retrieves the counter tracking the number of balls in the game.
     *
     * @return Counter object representing the number of balls.
     */
    public Counter getCounterBalls() {
        return this.counterBalls;
    }


    /**
     * Retrieves the counter tracking the number of blocks in the game.
     *
     * @return Counter object representing the number of blocks.
     */
    public Counter getCounterBlocks() {
        return this.counterBlocks;
    }
    /**
     * Sets the counter tracking the number of blocks in the game.
     *
     * @param counter Counter object for the number of blocks.
     */
    public void setCounterBlocks(Counter counter){
        this.counterBlocks=counter;
    }
    /**
     * Sets the counter tracking the number of balls in the game.
     *
     * @param counter Counter object for the number of balls.
     */
    public void setCounterBalls(Counter counter){
        this.counterBalls=counter;
    }
    /**
     * Retrieves the current score counter.
     *
     * @return Counter object representing the player's score.
     */
    public  Counter getScore(){
        return this.score;
    }

    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Retrieves information about the current level, including settings for
     * blocks, balls, and layout.
     *
     * @return LevelInformation object containing level details.
     */
    public LevelInformation getLevelInformation() {
        return this.levelInformation;
    }

    public GameEnvironment getEnvironment(){
        return this.environment;
    }
    public GUI getGui(){
        return this.gui;
    }
}

