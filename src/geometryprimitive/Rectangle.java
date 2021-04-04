package geometryprimitive;

import java.util.ArrayList;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class Rectangle {

    //fields
    //according to rectangle definition
    private Point upperLeft;
    private double width;
    private double height;

    /**
     * constructor.
     *
     * @param upperLeft point of the new rectangle
     * @param width     of the new rectangle
     * @param height    of the new rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line that may intersect with the rectangle
     * @return List of intersection points with the specified line
     */
    public java.util.List<Point> intersectionPoints(Line line) {
        java.util.List<Point> interPoints = new ArrayList<Point>();
        //checking intersection with upper line
        if (line.isIntersecting(this.getUpperLine())) {
            interPoints.add(line.intersectionWith(this.getUpperLine()));
        }
        //checking intersection with bottom line
        if (line.isIntersecting(this.getBottomLine())) {
            interPoints.add(line.intersectionWith(this.getBottomLine()));
        }
        //checking intersection with right line
        if (line.isIntersecting(this.getRightLine())) {
            interPoints.add(line.intersectionWith(this.getRightLine()));
        }
        //checking intersection with left line
        if (line.isIntersecting(this.getLeftLine())) {
            interPoints.add(line.intersectionWith(this.getLeftLine()));
        }
        return interPoints;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return this.width;
    }

    /**
     * Return the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return this.height;
    }

    /**
     * Changing the rectangle starting point.
     *
     * @param x parameter of the new starting point
     * @param y parameter of the new starting point
     */
    public void setUpperLeft(double x, double y) {
        Point nUperLeft = new Point(x, y);
        this.upperLeft = nUperLeft;
    }

    /**
     * Return the upper left point of the rectangle.
     *
     * @return the upper left point of the rectangle
     */
    public Point getUpperLeft() {
        return this.upperLeft;
    }

    /**
     * Return the upper right point of the rectangle.
     *
     * @return the upper right point of the rectangle
     */
    public Point getUpperRight() {
        Point upperRight = new Point(this.getWidth() + this.getUpperLeft().getX(),
                this.getUpperLeft().getY());
        return upperRight;
    }

    /**
     * Return the lower left point of the rectangle.
     *
     * @return the lower left point of the rectangle
     */
    public Point getLowerLeft() {
        Point lowerLeft = new Point(this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
        return lowerLeft;
    }

    /**
     * Return the lower right point of the rectangle.
     *
     * @return the lower right point of the rectangle
     */
    public Point getLowerRight() {
        Point lowerRight = new Point(this.getWidth() + this.getUpperLeft().getX(),
                this.getUpperLeft().getY() + this.getHeight());
        return lowerRight;
    }

    /**
     * Return the upper line of the rectangle.
     *
     * @return the upper line of the rectangle
     */
    public Line getUpperLine() {
        Line upperLine = new Line(this.getUpperLeft(), this.getUpperRight());
        return upperLine;
    }

    /**
     * Return the bottom line of the rectangle.
     *
     * @return the bottom line of the rectangle
     */
    public Line getBottomLine() {
        Line bottomLine = new Line(this.getLowerLeft(), this.getLowerRight());
        return bottomLine;
    }

    /**
     * Return the left line of the rectangle.
     *
     * @return the left line of the rectangle
     */
    public Line getLeftLine() {
        Line leftLine = new Line(this.getUpperLeft(), this.getLowerLeft());
        return leftLine;
    }

    /**
     * Return the right line of the rectangle.
     *
     * @return the right line of the rectangle
     */
    public Line getRightLine() {
        Line rightLine = new Line(this.getUpperRight(), this.getLowerRight());
        return rightLine;
    }

}