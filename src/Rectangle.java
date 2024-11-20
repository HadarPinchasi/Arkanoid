//Hadar Pinchasi

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a rectangle in a 2D space, defined by its upper-left corner,
 * width, and height. The rectangle includes methods for obtaining its
 * boundary lines and calculating intersection points with other lines.
 */
public class Rectangle {
    private final Point upperLeft;
    private final Point upperRight;
    private final Point downLeft;
    private final Line topLine;
    private final Line leftLine;
    private final Line downLine;
    private final Line rightLine;
    private final double width;
    private final double height;

    /**
     * Constructs a new Rectangle with a specified upper-left corner, width, and height.
     *
     * @param upperLeft the point representing the upper-left corner of the rectangle.
     * @param width     the width of the rectangle.
     * @param height    the height of the rectangle.
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.upperRight = new Point(this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.height = height;
        this.topLine = new Line(this.upperLeft, this.upperRight);
        this.downLeft = new Point(this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.leftLine = new Line(this.upperLeft, this.downLeft);
        Point downRight = new Point(this.upperRight.getX(), this.upperRight.getY() + height);
        this.downLine = new Line(this.downLeft, downRight);
        this.rightLine = new Line(this.upperRight, downRight);
    }

    /**
     * Generates a list of the lines that define the boundaries of the rectangle.
     *
     * @return a list of Line objects representing the rectangle's edges.
     */
    public java.util.List<Line> lineList() {
        List<Line> lines = new ArrayList<>();
        lines.add(topLine);
        lines.add(leftLine);
        lines.add(downLine);
        lines.add(rightLine);
        return lines;
    }

    /**
     * Returns the upper-right corner of the rectangle.
     *
     * @return the Point representing the upper-right corner.
     */
    public Point getUpperRight() {
        return this.upperRight;
    }

    /**
     * Finds intersection points between a given line and the rectangle's edges.
     *
     * @param line the specified line to check for intersections.
     * @return a list of intersection points; returns null if there are no intersections.
     */

    public java.util.List<Point> intersectionPoints(Line line) {
        List<Point> intersectionPoints = new ArrayList<>();
        if ((topLine.intersectionWith(line) != null)) {
            intersectionPoints.add(topLine.intersectionWith(line));
        }
        if (leftLine.intersectionWith(line) != null) {
            intersectionPoints.add(leftLine.intersectionWith(line));
        }
        if (downLine.intersectionWith(line) != null) {
            intersectionPoints.add(downLine.intersectionWith(line));
        }
        if (rightLine.intersectionWith(line) != null) {
            intersectionPoints.add(rightLine.intersectionWith(line));
        }
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        return intersectionPoints;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width as a double.
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Returns the height of the rectangle.
     *
     * @return the height as a double.
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Returns the upper-left corner of the rectangle.
     *
     * @return the Point representing the upper-left corner.
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }
    /**
     * Returns the lower-left corner of the rectangle.
     *
     * @return the Point representing the lower-left corner.
     */
    public Point getDownLeft() {
        return this.downLeft;
    }

}
