import java.awt.*;

public class BallAdder implements HitListener{
    private final Counter counter;
    private final GameLevel game;

    public BallAdder(Counter counter, GameLevel game) {
        this.counter = counter;
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        this.counter.increase(1);
        Ball ball = new Ball(new Point(720,300) ,9, Color.white);
        ball.setGameEnvironment(this.game.getEnvironment());
        ball.setVelocity(Velocity.fromAngleAndSpeed(20, 7));
        ball.addToGame(game);

    }
}
