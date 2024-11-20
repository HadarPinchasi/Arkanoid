//Hadar Pinchasi 315322784

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;

/**
 * The Paddle class represents a paddle that is used to hit the ball in the game.
 * The paddle is controlled by the player using the left and right arrow keys on the keyboard.
 */
public class Paddle implements Sprite, Collidable {

    private final biuoop.KeyboardSensor keyboard;
    private Rectangle rectangle;
    private final java.awt.Color color;

    /**
     * Constructs a new Paddle object with the specified rectangle, GUI, and color.
     *
     * @param rectangle the rectangular shape of the paddle
     * @param gui       the GUI object that the paddle is associated with
     * @param color     the color of the paddle
     */
    public Paddle(Rectangle rectangle, GUI gui, java.awt.Color color) {
        this.rectangle = rectangle;
        this.color = color;
        this.keyboard = gui.getKeyboardSensor();
    }

    /**
     * Moves the paddle to the left, keeping it within the game's borders.
     */

    public void moveLeft() {
        double newLocationX = this.rectangle.getUpperLeft().getX() - 8;
        if (newLocationX <= 40) { //keeping the paddle in borders
            newLocationX = 40;
        }
        double newLocationY=this.rectangle.getUpperLeft().getY();
        Point newUpperLeft = new Point(newLocationX, newLocationY);
        this.rectangle = new Rectangle(newUpperLeft, rectangle.getWidth(), rectangle.getHeight());
    }


    /**
     * Moves the paddle to the right, keeping it within the game's borders.
     */

    public void moveRight() {
        double newLocationX = this.rectangle.getUpperLeft().getX() + 8;
        if (newLocationX >= 760 - rectangle.getWidth()) {
            newLocationX = 760 - rectangle.getWidth();//keeping the paddle in borders
        }
        double newLocationY=this.rectangle.getUpperLeft().getY();
        Point newUpperLeft = new Point(newLocationX, newLocationY);
        this.rectangle = new Rectangle(newUpperLeft, rectangle.getWidth(), rectangle.getHeight());
    }


    /**
     * Updates the position of the paddle based on the player's keyboard input.
     * If the left arrow key is pressed, the paddle moves left.
     * If the right arrow key is pressed, the paddle moves right.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            this.moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            this.moveRight();
        }
    }

    /**
     * Draws the paddle on the specified DrawSurface.
     *
     * @param d the DrawSurface on which the paddle is to be drawn
     */
    public void drawOn(DrawSurface d) {
        if (d == null) {
            return;
        }
        d.setColor(this.color);
        d.fillRectangle((int) this.rectangle.getUpperLeft().getX()
                , (int) this.rectangle.getUpperLeft().getY(),
                (int)this.rectangle.getWidth(), (int) this.rectangle.getHeight());
    }

    /**
     * getting the form of the paddle.
     *
     * @return the form of the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }

    /**
     * causing the collidable to change it's direction.
     * calculates the new angle of the ball according to the hitting point.
     * @param hitter the block that is hitting.
     * @param collisionPoint  the point of the collision.
     * @param currentVelocity the current velocity of the collidiable.
     * @return the new velocity.
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double paddleStartX  = this.rectangle.getUpperLeft().getX();
        double collisionX  = collisionPoint.getX();
        double currentSpeed = currentVelocity.getSpeed();
        double segmentWidth  = this.rectangle.getWidth() / 5;
        final double[] newAngles = {300, 330, 0, 30, 60};
        // Calculate the segment index based on the collision point
        int segment = (int) ((collisionX - paddleStartX) / segmentWidth);
        segment = Math.min(Math.max(segment, 0), newAngles.length - 1);  // Ensure segment is within bounds

        // Handle central collision with an inverted Y velocity
        if (segment == 2) {
            return new Velocity(currentVelocity.getdx(), -currentVelocity.getdy());
        }
        // Calculate velocity based on angle for other segments
        return Velocity.fromAngleAndSpeed(newAngles[segment], currentSpeed);
    }

    /**
     * Adding this paddle to the game.
     *
     * @param g the game.
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}