//Hadar Pinchasi

/**
 * Represents a point in a 2D coordinate system.
 * This class provides methods for calculating the Euclidean distance between points
 * and for comparing points to determine equality with a high precision.
 */
public class Point {
    private final double x;
    private final double y;

    /**
     * Constructs a Point with specified x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }


    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the point to which the distance is calculated
     * @return the Euclidean distance between the two points
     */
    public double distance(Point other) {
        double d1 = ((this.getX() - other.getX()) * (this.getX() - other.getX()));
        double distance = d1 + ((this.getY() - other.getY()) * (this.getY() - other.getY()));
        return Math.sqrt(distance);
    }

    /**
     * Compares this point with another point for equality, allowing for floating-point precision errors.
     *
     * @param other the point to be compared with this point
     * @return true if the points are equal within a tolerance, false otherwise
     */


    public boolean equals(Point other) {
        final double EPSILON = 1e-10;
        return Math.abs(this.getX() - other.getX()) < EPSILON && Math.abs(this.getY() - other.getY()) < EPSILON;
    }


    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate of the point
     */
    public double getX() {
        return x;
    }

    /**
     * Returns the x-coordinate of the point.
     *
     * @return the x-coordinate of the point
     */
    public double getY() {
        return y;
    }


}
