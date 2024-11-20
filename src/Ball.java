//Hadar Pinchasi

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * This class represents a ball in a 2D space.
 * The ball has properties such as position (center), radius, color, and velocity.
 * It can move, be drawn on a surface, and interact with game objects in a game environment.
 */
public class Ball implements Sprite {
    private Velocity velocity;
    private Point center;
    private final int r;
    private final Color color;
    private GameEnvironment gameEnvironment;

    /**
     * Constructs a Ball with a specified center, radius, and color.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball.
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.r = r;
        this.color = color;
    }

    /**
     * Gets the X coordinate of the ball's center.
     *
     * @return the X coordinate as an integer.
     */
    public int getCenterX() {
        return (int) this.center.getX();
    }

    /**
     * Gets the Y coordinate of the ball's center.
     *
     * @return the Y coordinate as an integer.
     */
    public int getCenterY() {
        return (int) this.center.getY();
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius size.
     */
    public int getRadiusSize() {
        return this.r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color as a java.awt.Color object.
     */
    public java.awt.Color getColor() {
        return this.color;
    }


    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which the ball is drawn.
     */
    public void drawOn(DrawSurface surface) {
        if (surface == null) {
            return;
        }
        surface.setColor(this.getColor());
        surface.fillCircle(this.getCenterX(), this.getCenterY(), this.getRadiusSize());
    }

    /**
     * Updates the state of the ball for each frame.
     * This method calls moveOneStep(), which handles the ball's movement.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * a function that setting new velocity to the ball.
     *
     * @param v the new velocity.
     */
    public void setVelocity(Velocity v) {
        this.velocity = new Velocity(v.getdx(), v.getdy());
    }

    /**
     * Retrieves the current velocity of the ball.
     * <p>This method returns the ball's current velocity, which defines the rate of change
     * of its position on the X and Y axes.</p>
     *
     * @return the current velocity of the ball as a Velocity object.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * Moves the ball one step forward based on its current velocity.
     * <p>The method calculates the ball's trajectory to check if it will hit any objects
     * within the GameEnvironment. If a collision is detected, the ball is moved
     * to just before the collision point and its velocity is updated using the collision
     * response provided by the colliding object. Otherwise, the ball continues to move
     * freely along its trajectory.</p>
     */
    public void moveOneStep() {
        Point nextPoint = this.getVelocity().applyToPoint(this.center);
        Line trajectory = new Line(this.center, nextPoint);
        CollisionInfo collisionInfo = this.gameEnvironment.getClosestCollision(trajectory);
        if (collisionInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
        } else {
            double distanceY = (collisionInfo.getCollisionPoint().getY() + this.center.getY()) / 2;
            double distanceX = (collisionInfo.getCollisionPoint().getX() + this.center.getX()) / 2;
            this.center = new Point(distanceX, distanceY);
            Velocity newV = collisionInfo.GetCollisionObject().hit(this,
                    collisionInfo.getCollisionPoint(), this.getVelocity());
            this.setVelocity(newV);
            Collidable collidable = collisionInfo.GetCollisionObject();
            boolean x1 = this.center.getX() > collidable.getCollisionRectangle().getUpperLeft().getX();
            boolean x2 = (this.center.getX() < collidable.getCollisionRectangle().getUpperRight().getX());
            boolean y1 = this.center.getY() > collidable.getCollisionRectangle().getUpperLeft().getY();
            boolean y2 = (this.center.getY() < collidable.getCollisionRectangle().getDownLeft().getY());
            if ((x1 && x2) &&(y1 && y2)) {
                double y = collidable.getCollisionRectangle().getUpperLeft().getY() - 1;
                this.center = new Point(this.center.getX(), y);
            }
        }
    }

    /**
     * Sets the game environment in which the ball interacts.
     *
     * @param gameEnvironment the GameEnvironment that defines the ball's interaction space.
     */
    public void setGameEnvironment(GameEnvironment gameEnvironment) {
        this.gameEnvironment = gameEnvironment;
    }

    /**
     * Adds the ball to a game.
     *
     * @param g the {@link GameLevel} instance to which the ball is added.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * removing the ball from the game.
     *
     * @param game the game that removing the ball.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
}
