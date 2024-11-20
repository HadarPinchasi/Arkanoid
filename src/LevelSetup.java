import java.awt.*;
/**
 * The LevelSetup class initializes and configures all game components for a level.
 * This includes balls, blocks, paddle, and boundaries.
 */
public class LevelSetup {
    private final GameLevel gameLevel;
    private final LevelInformation levelInfo;
    private static final Point BALL_START_POINT = new Point(400, 500);
    private static final int BALL_RADIUS = 9;
    private static final Color BALL_COLOR = Color.WHITE;
    private static final int SCREEN_WIDTH = 800;
    private static final int SCORE_INDICATOR_HEIGHT = 20;


    /**
     * Constructor to initialize LevelSetup with the provided game level and level information.
     *
     * @param gameLevel The current GameLevel instance.
     * @param levelInfo LevelInformation object providing details about the current level setup.
     */
    public LevelSetup(GameLevel gameLevel, LevelInformation levelInfo) {
        this.gameLevel = gameLevel;
        this.levelInfo = levelInfo;
    }
    /**
     * Initializes all components of the level including balls, paddle, blocks, and borders.
     */
    public void initializeLevelComponents(){
        createBallsOnTopOfPaddle();
        setLimits();
        ScoreTrackingListener scoreTrackingListener = createIndicators();
        createPaddle();
        createDeathBlock();
        createBallAdder();
        createBlocks(scoreTrackingListener);
    }

    /**
     * Creates balls positioned on top of the paddle at the start of the level.
     */
    public void createBallsOnTopOfPaddle() {
        for (int i = 0; i < levelInfo.numberOfBalls(); i++) {
            Ball ball = new Ball(BALL_START_POINT, BALL_RADIUS, BALL_COLOR);
            ball.setGameEnvironment(this.gameLevel.getEnvironment());
            Velocity velocity = levelInfo.initialBallVelocities().get(i);
            ball.setVelocity(velocity);
            ball.addToGame(gameLevel);
        }
    }
    /**
     * Sets up the boundaries of the game field to prevent balls from escaping the screen.
     */
    private void setLimits() {
        addBorder(new Point(0, 0),800,40);
        addBorder(new Point(0, 0),40,600);
        addBorder(new Point(760,0),40,600);

    }

    /**
     * Helper method to add a border block at a specified position and size.
     *
     * @param position The position of the border.
     * @param width The width of the border block.
     * @param height The height of the border block.
     */
    private void addBorder(Point position, int width, int height){
        Rectangle borderRect = new Rectangle(position, width, height);
        Block borderBlock = new Block(borderRect, java.awt.Color.GRAY);
        borderBlock.addToGame(gameLevel);
    }
    /**
     * Creates score indicators for tracking and displaying the player's score on screen.
     *
     * @return ScoreTrackingListener that listens to score updates.
     */
    private ScoreTrackingListener createIndicators(){
        gameLevel.setCounterBlocks(new Counter(levelInfo.numberOfBlocksToRemove()));
        gameLevel.setCounterBalls(new Counter(levelInfo.numberOfBalls()));
        Rectangle scoring = new Rectangle(new Point(0, 0), SCREEN_WIDTH, SCORE_INDICATOR_HEIGHT);
        ScoreIndicator scoreIndicator = new ScoreIndicator(scoring, gameLevel, gameLevel.getScore());
        scoreIndicator.addToGame();
        return new ScoreTrackingListener(gameLevel.getScore());
    }
    /**
     * Creates and adds a paddle for the level, allowing player control.
     */
    private void createPaddle(){
        Point paddlePosition = new Point(400, 580);
        Rectangle paddleRectangle  = new Rectangle(paddlePosition, levelInfo.paddleWidth(), 30);
        Paddle paddle = new Paddle(paddleRectangle ,gameLevel.getGui(), java.awt.Color.YELLOW);
        paddle.addToGame(gameLevel);
    }

    /**
     * Creates a block at the bottom of the screen that will remove any balls that hit it, simulating "loss of life."
     */
    private void createDeathBlock(){
        BallRemover ballRemover = new BallRemover(gameLevel.getCounterBalls(), gameLevel);
        Rectangle downLimit = new Rectangle(new Point(0, 600), 800, 40);
        Block deathBlock = new Block(downLimit, java.awt.Color.GRAY);
        Rectangle miniDeathRect = new Rectangle(new Point(40, 300), 40, 40);
        Block miniDeathBlock= new Block(miniDeathRect,Color.black);
        deathBlock.addToGame(gameLevel);
        deathBlock.addHitListener(ballRemover);
        miniDeathBlock.addToGame(gameLevel);
        miniDeathBlock.addHitListener(ballRemover);
    }
   private void createBallAdder(){
       BallAdder ballAdder=new BallAdder(gameLevel.getCounterBalls(),gameLevel);
       Rectangle addingRect= new Rectangle(new Point(720,300),40,40);
       Block addBlock= new Block(addingRect,Color.white);
       addBlock.addToGame(gameLevel);
       addBlock.addHitListener(ballAdder);

   }
    /**
     * Creates blocks for the current level according to the level information and adds score tracking listeners.
     *
     * @param scoreTrackingListener Listener that tracks score updates when blocks are hit.
     */
    private void createBlocks(ScoreTrackingListener scoreTrackingListener){
        BlockRemover blockRemover = new BlockRemover(gameLevel, gameLevel.getCounterBlocks());
        for (Block block : levelInfo.blocks()) {
            block.addHitListener(blockRemover);
            block.addHitListener(scoreTrackingListener);
            block.addToGame(gameLevel);
        }
    }
}
