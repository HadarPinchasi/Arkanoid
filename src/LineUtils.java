import java.util.Objects;

/**
 * LineUtils provides utility methods for performing geometric operations
 * on line segments. These operations include calculating intersection points
 * between two lines, checking if a point lies on a line, and handling edge cases
 * for lines that are parallel to the axes.
 * <p>
 * This class assumes that a line is represented by two points, and contains
 * methods that help in geometric calculations such as finding intersection points
 * between lines, checking if a point lies on a line, and dealing with edge cases
 * like vertical or horizontal lines.
 */
public class LineUtils {
    /**
     * Calculates the intersection point of two lines, considering various special cases
     * like parallel lines and vertical lines. The helper methods within this class handle
     * the calculation of intersection points for different scenarios.
     *
     * @param line1 The first line.
     * @param line2 The second line.
     * @return The intersection point, or null if there is no intersection.
     */
    public static Point intersectionWith(Line line1, Line line2) {
        if (line2 == null || line1.equals(line2)) {
            return null; // No intersection if lines are the same
        }
        double thisMinX = getMinX(line1);
        double thisMinY = getMinY(line1);
        double thisMaxX = getMaxX(line1);
        double thisMaxY = getMaxY(line1);
        double otherMinX = getMinX(line2);
        double otherMinY = getMinY(line2);
        double otherMaxX = getMaxX(line2);
        double otherMaxY = getMaxY(line2);

        //if the two line are parallel to Y - axis
        if (line1.parallelToY() && line2.parallelToY()) {
            if ((thisMaxX == otherMinX && thisMaxY == otherMinY) || (thisMaxX == otherMaxX && thisMaxY == otherMaxY)) {
                return new Point(thisMaxX, thisMaxY);
            }
            if ((otherMaxX == thisMinX && otherMaxY == thisMinY) || (otherMinX == thisMinX && otherMinY == thisMinY)) {
                return new Point(thisMinX, thisMinY);
            }
        }
        //if only one is parallel to Y- axis
        if (line1.parallelToY() && !line2.parallelToY()) {
            return oneParallel(line1, line2);
        }
        if (line2.parallelToY() && !line1.parallelToY()) {
            return oneParallel(line2, line1);
        }
        // if different slope
        if (!Objects.equals(line1.getSlope(), line2.getSlope())) {
            double x = ((line1.getYIntercept() - line2.getYIntercept()) / (line2.getSlope() - line1.getSlope()));
            double y = line1.getSlope() * x + line1.getYIntercept();
            Point p = new Point(x, y);
            if (isPointInsideLineSegment(line1, p) && isPointInsideLineSegment(line2, p)) {
                return p;
            }
        } else {
            //same slope, checking if meeting in the edges
            if ((thisMaxX == otherMinX && thisMaxY == otherMinY) || (otherMaxX == thisMinX && otherMaxY == thisMinY)) {
                return new Point(thisMaxX, thisMaxY);
            }
        }
        return null;
    }

    /**
     * Checks if a given point lies on the line segment.
     * <p>
     * This method checks whether the provided point lies within the bounds of the
     * line segment.
     *
     * @param line The line to check against.
     * @param point The point to check.
     * @return True if the point lies on the line segment, false otherwise.
     */
    public static boolean isPointInsideLineSegment(Line line, Point point) {
        double x1, x2, y1, y2;
        x1 = Math.min(line.start().getX(), line.end().getX());
        x2 = Math.max(line.start().getX(), line.end().getX());
        if (line.start().getX() == x1) {
            y1 = line.start().getY();
            y2 = line.end().getY();
        } else {
            y1 = line.end().getY();
            y2 = line.start().getY();
        }
        if ((point.getX() >= x1) && (point.getX() <= x2)) {
            return ((point.getY() >= y1) && (point.getY() <= y2)) || ((point.getY() >= y2) && (point.getY() <= y1));
        }
        return false;
    }

    /**
     * Helper method to get the minimum X coordinate of a line.
     * <p>
     * This method returns the smaller of the X values of the start and end points
     * of the line segment.
     *
     * @param line The line whose minimum X coordinate is required.
     * @return The minimum X coordinate of the line.
     */
    private static double getMinX(Line line) {
        return Math.min(line.start().getX(), line.end().getX());
    }


    /**
     * Helper method to get the maximum X coordinate of a line.
     * <p>
     * This method returns the larger of the X values of the start and end points
     * of the line segment.
     *
     * @param line The line whose maximum X coordinate is required.
     * @return The maximum X coordinate of the line.
     */
    private static double getMaxX(Line line) {
        return Math.max(line.start().getX(), line.end().getX());
    }
    /**
     * Helper method to get the minimum Y coordinate of a line.
     * <p>
     * This method returns the Y value corresponding to the minimum X coordinate
     * of the line segment.
     *
     * @param line The line whose minimum Y coordinate is required.
     * @return The minimum Y coordinate of the line.
     */
    private static double getMinY(Line line) {
        return (line.start().getX() == getMinX(line)) ? line.start().getY() : line.end().getY();
    }
    /**
     * Helper method to get the maximum Y coordinate of a line.
     * <p>
     * This method returns the Y value corresponding to the maximum X coordinate
     * of the line segment.
     *
     * @param line The line whose maximum Y coordinate is required.
     * @return The maximum Y coordinate of the line.
     */
    private static double getMaxY(Line line) {
        return (line.start().getX() == getMaxX(line)) ? line.start().getY() : line.end().getY();
    }

    /**
     * Calculates the intersection point when one line is parallel to the Y-axis
     * and the other is not.
     * <p>
     * This method checks if the two lines intersect when one is vertical and the other
     * has a defined slope. It computes the intersection point based on the X coordinate
     * of the vertical line and the equation of the non-vertical line.
     *
     * @param parallel The line parallel to the Y-axis.
     * @param notParallel The line with a defined slope.
     * @return The intersection point if the lines intersect, or null if they do not.
     */
    private static Point oneParallel(Line parallel, Line notParallel) {
        double x = parallel.start().getX();
        double y = notParallel.getSlope() * x + notParallel.getYIntercept();
        Point suspicious = new Point(x, y);
        if (isPointInsideLineSegment(parallel, suspicious) && isPointInsideLineSegment(notParallel, suspicious)) {
            return suspicious;
        }
        return null;
    }
}