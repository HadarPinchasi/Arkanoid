//Hadar Pinchasi

import java.util.List;

/**
 * Represents a line segment defined by a start and end point.
 * Provides various methods for calculating properties of the line, such as:
 * - Length
 * - Slope
 * - Intersection points with other lines
 * - Checking if the line is parallel to the Y-axis
 * - Finding the middle and closest intersection points
 */
public class Line {
    private final Point start;
    private final Point end;
    private final Double slope;

    /**
     * Constructs a line segment using two Point objects.
     *
     * @param start The starting point of the line.
     * @param end   The ending point of the line.
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
        this.slope = calculateSlope();
    }


    /**
     * Calculates the slope of the line segment.
     * The slope is defined as the change in y divided by the change in x.
     * For vertical lines (where the x-values are equal), the slope is positive infinity.
     *
     * @return The slope of the line segment, or Double.POSITIVE_INFINITY if vertical.
     */
    private double calculateSlope() {
        if (this.start.getX() == this.end.getX()) {
            return Double.POSITIVE_INFINITY;
        }
        return ((this.start.getY() - this.end.getY()) / (this.start.getX() - this.end.getX()));
    }

    /**
     * returning the slope of the line
     * @return the slope of the line.
     */
    public double getSlope() {
        return slope;
    }

    /**
     * Calculates the y-intercept of the line, which is the y-value when x = 0.
     * The formula is: y = mx + b => b = y - mx
     *
     * @return The y-intercept of the line.
     */
    private double yIntercept() {
        return (this.start.getY() - this.getSlope() * this.start.getX());
    }

    /**
     * Returns the yIntercept of the line
     *
     * @return the yIntercept of the line
     */
    public double getYIntercept(){
        return yIntercept();
    }


    /**
     * Checks if two lines are equal, considering both directions.
     * Compares the start and end points of both lines to determine equality.
     *
     * @param other The line to compare this line to.
     * @return true if the lines are equal (same or opposite direction), false otherwise.
     */
    public boolean equals(Line other) {
        boolean sameDirection = this.start.equals(other.start) && this.end.equals(other.end);
        boolean oppositeDirection = this.start.equals(other.end) && this.end.equals(other.start);
        return sameDirection || oppositeDirection;
    }


    /**
     * Returns the starting point of the line.
     *
     * @return The starting Point.
     */
    public Point start() {
        return this.start;
    }


    /**
     * Returns the ending point of the line.
     *
     * @return The ending Point.
     */
    public Point end() {
        return this.end;
    }

    /**
     * Checks if this line intersects with another line.
     * This method utilizes helper methods in LineUtils to calculate intersection points.
     *
     * @param other The line to check for intersection.
     * @return The intersection point, or null if no intersection.
     */
    public Point intersectionWith(Line other) {
        return LineUtils.intersectionWith(this, other);
    }

    /**
     * Checks if the line is parallel to the Y-axis (vertical).
     * A vertical line will have the same x-coordinates for both points.
     *
     * @return true if the line is parallel to the Y-axis, false otherwise.
     */
    public boolean parallelToY() {
        return (this.start.getX() - this.end.getX()) == 0;
    }


    /**
     * Finds the closest intersection point between the start of a line segment and a given rectangle.
     *
     * <p>This method calculates the intersection points between the line (represented by this instance)
     * and the specified rectangle. It then returns the intersection point that is closest to the
     * starting point of the line segment. If there are no intersection points, it returns {@code null}.
     *
     * @param rect the rectangle to check for intersection points with this line
     * @return the closest intersection point to the start of the line segment, or {@code null}
     *         if no intersections exist
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints == null || intersectionPoints.isEmpty()) {
            return null;
        }

        Point closestPoint = intersectionPoints.get(0);
        double minDistance = this.start.distance(closestPoint);

        for (Point point : intersectionPoints) {
            double distance = this.start.distance(point);
            if (distance < minDistance) {
                minDistance = distance;
                closestPoint = point;
            }
        }

        return closestPoint;
    }
}