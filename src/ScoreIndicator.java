//Hadar Pinchasi .


import biuoop.DrawSurface;

import java.awt.Color;
/**
 * counting the score.
 */
public class ScoreIndicator implements Sprite {
    private final GameLevel game;
    private final Rectangle rectangle;
    private final Counter score;

    /**
     * the constructor.
     * @param rectangle the shape of the score.
     * @param game the game of the score.
     * @param score the counter.
     */
    public ScoreIndicator(Rectangle rectangle, GameLevel game, Counter score) {
        this.game = game;
        this.rectangle = rectangle;
        this.score = score;
    }

    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                (int) this.rectangle.getUpperLeft().getY(),
                (int) this.rectangle.getWidth(), (int) this.rectangle.getHeight());
        String scoring = "Score: " + score.getValue();
        d.setColor(Color.black);
        d.drawText(330, 20, scoring, 20);
        String nameLevel = "Level Name: " + this.game.getLevelInformation().levelName();
        d.setColor(Color.black);
        d.drawText(500, 20, nameLevel, 20);
    }


    public void timePassed() {

    }

    /**
     * adding the score to the game.
     */
    public void addToGame() {
        game.addSprite(this);
    }
}
