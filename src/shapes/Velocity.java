package shapes;

import geometryprimitive.Point;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 28/03/2020
 */

// shapes.Velocity specifies the change in position on the `x` and the `y` axes.
public class Velocity {
    //fields
    private double dx;
    private double dy;

    /**
     * Constructor.
     *
     * @param dx vertical advance
     * @param dy horizontal advance
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructor from angle and speed data.
     *
     * @param speed given speed of the ball
     * @param angle given angle for moving the ball
     * @return new velocity calculating with angle and speed
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        //reducing angle to 360 range
        while (angle > 360.0) {
            angle = angle - 360.0;
        }
        //special angles
        if (angle == 90) {
            double dx = 0;
            double dy = speed * 1.0;
            return new Velocity(dx, dy);
        }
        if (angle == 270) {
            double dx = 0.0;
            double dy = speed * -1.0;
            return new Velocity(dx, dy);
        }
        if (angle == 360 || angle == 0) {
            double dx = speed * 1.0;
            double dy = 0.0;
            return new Velocity(dx, dy);
        }
        //calculating using trigo and radians
        double radiansAngle = Math.toRadians(angle);
        double dx = speed * (Math.sin(radiansAngle));
        double dy = speed * (Math.cos(radiansAngle));
        return new Velocity(dx, dy);
    }

    /**
     * Getting the dx value of the circle's velocity.
     *
     * @return the dx value of the circle's velocity.
     */
    public double getDx() {
        return this.dx;
    }

    /**
     * Getting the dy value of the circle's velocity.
     *
     * @return the dy value of the circle's velocity.
     */
    public double getDy() {
        return this.dy;
    }

    //

    /**
     * Take a point with position (x,y) and return a new point with position (x+dx, y+dy).
     *
     * @param p the point received before adding the velocity influence
     * @return point with after adding the velocity influence
     */
    public Point applyToPoint(Point p) {
        Point addedPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return addedPoint;
    }
}