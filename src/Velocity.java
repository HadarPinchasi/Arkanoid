//Hadar Pinchasi


/**
 * This class represents the velocity of an object in a 2D space.
 * It defines the change in position along the X and Y axes (dx, dy)
 * and provides functionality to apply this change to a point.
 */
public class Velocity {
    private final double dx;
    private final double dy;

    /**
     * Constructs a new Velocity object with specified changes in the X and Y axes.
     *
     * @param dx the change in the X axis.
     * @param dy the change in the Y axis.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Creates a Velocity instance based on an angle and a speed.
     * The method uses trigonometric functions to convert polar coordinates (angle, speed)
     * into Cartesian coordinates (dx, dy).
     *
     * @param angle The direction of movement in degrees.
     * @param speed The speed of movement.
     * @return a new Velocity instance representing the specified angle and speed.
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = (speed * Math.sin(Math.toRadians(angle)));
        double dy = -(speed * Math.cos(Math.toRadians(angle)));
        return new Velocity(dx, dy);
    }

    /**
     * Gets the change in the X axis.
     *
     * @return the dx value.
     */
    public double getdx() {
        return dx;
    }

    /**
     * Gets the change in the Y axis.
     *
     * @return the dy value.
     */
    public double getdy() {
        return dy;
    }

    /**
     * Applies this velocity to a given point, resulting in a new point.
     *
     * @param p the original point.
     * @return a new Point with updated coordinates (x + dx, y + dy).
     */
    public Point applyToPoint(Point p) {
        return new Point((p.getX() + this.dx), (p.getY() + this.dy));
    }


    /**
     * Calculates and returns the current speed (magnitude) of the velocity.
     * The speed is determined by computing the Euclidean distance based on
     * the dx and dy components of the velocity.
     *
     * @return the speed (magnitude) of the velocity as a double.
     */
    public double getSpeed(){
        double currentDx = this.dx *this.dx;
        double currentDy = this.dy * this.dy;
        return Math.sqrt((currentDx) + (currentDy));

    }

}
