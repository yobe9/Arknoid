package geometryprimitive;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 26/03/2020
 */
public class Point {
    //Fields
    //place on the x scale
    private double x;
    //place on the y scale
    private double y;

    /**
     * constructor.
     *
     * @param x number from main
     * @param y number from main
     */
    public Point(double x, double y) {

        this.x = x;
        this.y = y;
    }

    /**
     * distance -- return the distance of this point to the other point.
     *
     * @param other point from main
     * @return the distance between points
     */
    public double distance(Point other) {
        double xDiff = this.x - other.getX();
        double yDiff = this.y - other.getY();
        return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
    }

    /**
     * equals -- return true is the points are equal, false otherwise.
     *
     * @param other point from main
     * @return true if points are equal
     */
    public boolean equals(Point other) {
        if ((this.x == other.getX()) && (this.y == other.getY())) {
            return true;
        }
        return false;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return Return the x value of this point
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return Return the y value of this point
     */
    public double getY() {
        return this.y;
    }
}
