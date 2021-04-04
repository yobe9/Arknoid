package geometryprimitive;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 26/03/2020
 */
public class Line {
    //fields
    //starting and ending points of the line
    private Point start;
    private Point end;
    //saving the original start
    private Point oldStart;

    /**
     * constructor.
     *
     * @param start starting point from main
     * @param end   ending point from main
     */
    public Line(Point start, Point end) {
        //setting the original start
        this.oldStart = start;
        //to keep on the directions of the lines from left to right
        if (start.getX() > end.getX()) {
            Point tmpP = start;
            start = end;
            end = tmpP;
        }
        this.start = start;
        this.end = end;
    }

    /**
     * constructor.
     *
     * @param x1 starting x from main
     * @param y1 starting y from main
     * @param x2 ending x from main
     * @param y2 ending y from main
     */
    public Line(double x1, double y1, double x2, double y2) {
        //creating starting and ending points
        Point startP = new Point(x1, y1);
        Point endP = new Point(x2, y2);
        //setting the original start
        this.oldStart = startP;
        //to keep on the directions of the lines from left to right
        if (startP.getX() > endP.getX()) {
            Point tmpP = startP;
            startP = endP;
            endP = tmpP;
        }
        this.start = startP;
        this.end = endP;
    }

    /**
     * Return the length of the line.
     *
     * @return the length of the line
     */
    public double length() {
        return this.start.distance(this.end);
    }

    /**
     * Returns the middle point of the line.
     * according to (x1+x2)/2
     *
     * @return the middle point of the line
     */
    public Point middle() {
        double xDiff = (this.start.getX() + this.end.getX()) / 2;
        double yDiff = (this.start.getY() + this.end.getY()) / 2;
        Point middle = new Point(xDiff, yDiff);
        return middle;
    }

    /**
     * Returns the start point of the line.
     *
     * @return the start point of the line
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return the end point of the line
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns the old starting point of the line.
     *
     * @return the old starting point of the line
     */
    public Point getOldStart() {
        return this.oldStart;
    }

    /**
     * calculate the incline of the line.
     * according to m=(y1-y2)/(x1-x2)
     *
     * @return the incline of the line
     */
    public double incline() {
        double m = ((this.start.getY() - this.end.getY())
                / (this.start.getX() - this.end.getX()));
        return m;
    }

    /**
     * calculate the parameter of the equation for the line y=mx+b.
     *
     * @return the parameter of the equation
     */
    public double equationParameter() {
        double m = this.incline();
        double b = this.start.getY() - m * this.start.getX();
        return b;
    }

    /**
     * calculate the x value of intersected point of 2 lines
     * according to (b2-b1)/(a1-a2).
     *
     * @param other the second line
     * @return the x value of intersected point of 2 lines
     */
    public double intersectX(Line other) {
        double interX = ((other.equationParameter() - this.equationParameter())
                / (this.incline() - other.incline()));
        return interX;
    }

    /**
     * calculate the y value of intersected point of 2 lines
     * according to y=mx+b.
     *
     * @param other the second line
     * @return the y value of intersected point of 2 lines
     */
    public double intersectY(Line other) {
        double interX = intersectX(other);
        double interY = interX * this.incline() + this.equationParameter();
        return (interY);
    }

    /**
     * checks if point is in range of two lines.
     *
     * @param other the second line
     * @param x     value to check if in range
     * @param y     value to check if in range
     * @return true if in range
     */
    public boolean isInRange(Line other, double x, double y) {
        //finiding minimum and maximum of each line
        double minXMyLine, minYMyline, maxXMyline, maxYMyline;
        double minXOtherLine, minYOtherline, maxXOtherline, maxYOtherline;
        int flag = 0;
        //setting out the first line min and max values
        minXMyLine = Math.min(this.start.getX(), this.end.getX());
        minYMyline = Math.min(this.start.getY(), this.end.getY());
        maxXMyline = Math.max(this.start.getX(), this.end.getX());
        maxYMyline = Math.max(this.start.getY(), this.end.getY());
        //setting out the second line min and max values
        minXOtherLine = Math.min(other.start.getX(), other.end.getX());
        minYOtherline = Math.min(other.start.getY(), other.end.getY());
        maxXOtherline = Math.max(other.start.getX(), other.end.getX());
        maxYOtherline = Math.max(other.start.getY(), other.end.getY());
        //checking if its in range
        if ((x >= minXMyLine) && (x <= maxXMyline)
                && (y >= minYMyline) && (y <= maxYMyline)) {
            flag++;
        }
        if ((x >= minXOtherLine) && (x <= maxXOtherline)
                && (y >= minYOtherline) && (y <= maxYOtherline)) {
            flag++;
        }
        //if it is in range of both return true
        if (flag == 2) {
            return true;
        }
        return false;

    }

    /**
     * checks if a line is a part of other line when they are parallel to the y axis.
     *
     * @param other the second line
     * @return true one line has common area withe the second and they are parallel to y axis
     */
    public boolean partLineParallel(Line other) {
        if (this.start.getX() != other.start.getX()) {
            return false;
        }
        //finding the minumum and maximum of the y value of the lines
        double minThisLine = Math.min(this.start.getY(), this.end.getY());
        double maxThisLine = Math.max(this.start.getY(), this.end.getY());
        double minOtherLine = Math.min(other.start.getY(), other.end.getY());
        double maxOtherLine = Math.max(other.start.getY(), other.end.getY());
        //if the lines have common area
        if (minThisLine < maxOtherLine && minThisLine >= minOtherLine) {
            return true;
        }
        if (minOtherLine < maxThisLine && minOtherLine >= minThisLine) {
            return true;
        }
        return false;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other the second line
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        //checks it is not the same lines
        if (this.equals(other)) {
            return false;
        }
        //if one of the line is point
        if (this.length() == 0) {
            if (isInRange(other, this.start.getX(), this.start.getY())) {
                return true;
            }
        }
        if (other.length() == 0) {
            if (isInRange(other, other.start.getX(), other.start.getY())) {
                return true;
            }
        }
        //in case we have lines which are parallel to y we will check 3 cases
        //in case they both parallel
        if ((this.start.getX() - this.end.getX()) == 0 && (other.start.getX() - other.end.getX()) == 0) {
            //in the case one of the lines starts or ends when the other ends or starts and they are not sublines
            if ((this.start.equals(other.end) || other.start.equals(this.end)
                    || this.start.equals(other.start) || this.end.equals(other.end)) && (!partLineParallel(other))) {
                return true;
            }
            return false;
        } else if ((this.start.getX() - this.end.getX()) == 0) { //in case just 1 parallel
            //getting the y value of optional intersect, y=m*x(of bad line) + b
            double yOfRightLine = (other.incline() * this.start.getX()) + other.equationParameter();
            //checking if the intersect point is in range of the second line
            if (isInRange(other, this.start.getX(), yOfRightLine)) {
                return true;
            }
            return false;
        } else if ((other.start.getX() - other.end.getX()) == 0) { //in case the other one is parallel
            //getting the y value of optional intersect, y=m*x(of bad line) + b
            double yOfRightLine = (this.incline() * other.start.getX()) + this.equationParameter();
            //checking if the intersect point is in range of the second line
            if (isInRange(other, other.start.getX(), yOfRightLine)) {
                return true;
            }
            return false;
        }
        //checking the case we have the same inclines
        if (this.incline() == other.incline()) {
            //in the case the second start at the end of the first or the other way
            if (this.start.equals(other.end) || other.start.equals(this.end)) {
                return true;
            }
            return false;
        }
        //checking the cases we have line which is horizontal
        if (other.incline() == 0) {
            double yOpt = other.start.getY();
            double xOpt = (yOpt - this.equationParameter()) / this.incline();
            return isInRange(other, xOpt, yOpt);
        }
        if (this.incline() == 0) {
            double yOpt = this.start.getY();
            double xOpt = (yOpt - other.equationParameter()) / other.incline();
            return isInRange(other, xOpt, yOpt);
        } else { //all other cases
            //calculate the intersect point
            double interX = this.intersectX(other);
            double interY = this.intersectY(other);
            //checking if the point is in range of the two lines
            return isInRange(other, interX, interY);
        }

    }

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other the second line
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        if (this.isIntersecting(other)) {
            //lines with the same incline
            //we can check only this because it passes isIntersecting
            if (this.start.equals(other.end)) {
                return this.start;
            } else if (this.end.equals(other.start)) {
                return this.end;
            } else if (this.start.equals(other.start)) {
                return this.start;
            } else if (this.end.equals(other.end)) {
                return this.end;
            }
            //if one of the line is point
            if (this.length() == 0) {
                if (isInRange(other, this.start.getX(), this.start.getY())) {
                    return this.start;
                }
            }
            if (other.length() == 0) {
                if (isInRange(other, other.start.getX(), other.start.getY())) {
                    return other.start;
                }
            }
            //lines parallel to y, 2 cases
            if (this.start.getX() == this.end.getX()) {
                double x = this.start.getX();
                //creating appropriate y value
                double y = (other.incline() * this.start.getX()) + other.equationParameter();
                Point interP = new Point(x, y);
                return interP;
            } else if (other.start.getX() == other.end.getX()) {
                double x = other.start.getX();
                //creating appropriate y value
                double y = (this.incline() * other.start.getX()) + this.equationParameter();
                Point interP = new Point(x, y);
                return interP;
            } else if (other.incline() == 0) { //horizontal lines
                double yOpt = other.start.getY();
                double xOpt = (yOpt - this.equationParameter()) / this.incline();
                Point optPoint = new Point(xOpt, yOpt);
                return optPoint;
            } else if (this.incline() == 0) {
                double yOpt = this.start.getY();
                double xOpt = (yOpt - other.equationParameter()) / other.incline();
                Point optPoint = new Point(xOpt, yOpt);
                return optPoint;
            } else { //regular cases
                double interX = this.intersectX(other);
                double interY = this.intersectY(other);
                Point interPoint = new Point(interX, interY);
                return interPoint;
            }
        }
        //if its not intersecting
        return null;
    }

    //

    /**
     * equals -- return true if the lines are equal, false otherwise.
     *
     * @param other the second line
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        //check both ways
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        if (this.start.equals(other.end) && this.end.equals((other.start))) {
            return true;
        }
        return false;
    }

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect a rectangle to check it intersect with the line
     * @return the closest intersection point to the start of the line or null
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        //the closest point to the start of the line
        Point closestPoint = null;
        // initializing the minimal distance with the longest distance + constant of my choice
        double constantDistanceAdding = 100.0;
        double minimalDistance = this.length() + constantDistanceAdding;
        // creating the other 3 points of the rectangle
        Point upperRight = new Point(rect.getWidth() + rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY());
        Point lowerLeft = new Point(rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());
        Point lowerRight = new Point(rect.getWidth() + rect.getUpperLeft().getX(),
                rect.getUpperLeft().getY() + rect.getHeight());
        // creating the 4 lines of the rectangle and insert them into array
        Line[] lineArray = new Line[4];
        Line upperLine = new Line(rect.getUpperLeft(), upperRight);
        lineArray[0] = upperLine;
        Line bottomLine = new Line(lowerLeft, lowerRight);
        lineArray[1] = bottomLine;
        Line leftLine = new Line(rect.getUpperLeft(), lowerLeft);
        lineArray[2] = leftLine;
        Line rightLine = new Line(upperRight, lowerRight);
        lineArray[3] = rightLine;
        // checking which of the lines are intersecting and inserting their details
        for (int i = 0; i <= 3; i++) {
            if (this.isIntersecting(lineArray[i])) {
                if (this.intersectionWith(lineArray[i]).distance(this.oldStart) <= minimalDistance) {
                    minimalDistance = this.intersectionWith(lineArray[i]).distance(this.oldStart);
                    closestPoint = this.intersectionWith(lineArray[i]);
                }
            }
        }
        // checking if the original distance had been changed and returning the closest point
        double epsilon = Math.pow(10, -15);
        if (Math.abs((this.length() + constantDistanceAdding) - minimalDistance) < epsilon) {
            return null;
        } else {
            return closestPoint;
        }
    }

    /**
     * checks if specific point is on horizontal or vertical line.
     *
     * @param checkPoint is the point we want to search if is on the line
     * @return true if it is on the line or false otherwise
     */
    public Boolean isPointOnRectangleLine(Point checkPoint) {
        double minX, minY, maxX, maxY;
        minX = Math.min(this.start.getX(), this.end.getX());
        minY = Math.min(this.start.getY(), this.end.getY());
        maxX = Math.max(this.start.getX(), this.end.getX());
        maxY = Math.max(this.start.getY(), this.end.getY());
        //epsilon
        double epsilon = Math.pow(10, -15);
        //check case that the line is horizontal
        if ((Math.abs(checkPoint.getY() - maxY) < epsilon) && (checkPoint.getX() <= maxX)
                && (checkPoint.getX() >= minX)) {
            return true;
        }
        //check case that line is vertical
        if ((Math.abs(checkPoint.getX() - maxX) < epsilon) && (checkPoint.getY() >= minY)
                && (checkPoint.getY() <= maxY)) {
            return true;
        }
        return false;
    }
}
