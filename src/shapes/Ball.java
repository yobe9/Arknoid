package shapes;
//import biuoop.GUI;

import biuoop.DrawSurface;
import geometryprimitive.Line;
import geometryprimitive.Point;
import gamelogic.GameEnvironment;
import gamelogic.GameLevel;

//import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 28/03/2020
 */
public class Ball implements Sprite {
    //fields
    private Point circleCenter;
    private int radious;
    private java.awt.Color circleColor;
    private Velocity circleVelocity;
    private GameEnvironment gameBoard;

    /**
     * Constructor.
     *
     * @param center  a point from main
     * @param r       radius from main
     * @param color   color from main
     * @param newgame new game created on main
     */
    public Ball(Point center, int r, java.awt.Color color, GameEnvironment newgame) {
        this.circleCenter = center;
        this.radious = r;
        this.circleColor = color;
        this.gameBoard = newgame;
    }

    /**
     * Constructor.
     *
     * @param x       value of the circle point from main
     * @param y       value of the circle point from main
     * @param r       radius from main
     * @param color   color from main
     * @param newgame new game created on main
     */
    public Ball(int x, int y, int r, java.awt.Color color, GameEnvironment newgame) {
        Point circlePoint = new Point((double) x, (double) y);
        this.circleCenter = circlePoint;
        this.radious = r;
        this.circleColor = color;
        this.gameBoard = newgame;
    }

    /**
     * Getting the x value of the center of the circle.
     *
     * @return the x value of the center of the circle
     */
    public int getX() {
        return (int) this.circleCenter.getX();
    }

    /**
     * Getting the y value of the center of the circle.
     *
     * @return the y value of the center of the circle
     */
    public int getY() {
        return (int) this.circleCenter.getY();
    }

    /**
     * Getting the radius value of the circle.
     *
     * @return the radius value of the circle
     */
    public int getSize() {
        return this.radious;
    }

    /**
     * Getting the color of the circle.
     *
     * @return the color of the circle
     */
    public java.awt.Color getColor() {
        return this.circleColor;
    }

    /**
     * Drawing the ball on the given DrawSurface.
     *
     * @param surface drawing board from main
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.getColor());
        surface.fillCircle(this.getX(), this.getY(), this.getSize());
    }

    /**
     * Moiving the ball once.
     */
    @Override
    public void timePassed() {
        this.moveOneStep();
    }

    /**
     * Setting the velocity of the ball according to velocity.
     *
     * @param v velocity from main
     */
    public void setVelocity(Velocity v) {
        this.circleVelocity = v;
    }

    /**
     * Setting the velocity of the ball according to x and y increasing.
     *
     * @param dx x increasing
     * @param dy y increasing
     */
    public void setVelocity(double dx, double dy) {
        Velocity newVelocity = new Velocity(dx, dy);
        this.circleVelocity = newVelocity;
    }

    /**
     * Returning the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.circleVelocity;
    }

    /**
     * Moving the ball once according to the velocity of the ball.
     * if the ball is arriving to the borders, it changes its place
     */
    public void moveOneStep() {
        //calculating the trajectory line
        Point endPointMovement = this.circleVelocity.applyToPoint(this.circleCenter);
        Line trajectory = new Line(this.circleCenter, endPointMovement);
        //check if there was no collisions
        if (this.gameBoard.getClosestCollision(trajectory) == null) {
            this.circleCenter = endPointMovement;
        } else {
            //getting out the lines of the rectangle
            Line upperLine = gameBoard.getClosestCollision(trajectory).collisionObject()
                    .getCollisionRectangle().getUpperLine();
            Line bottomLine = gameBoard.getClosestCollision(trajectory).collisionObject()
                    .getCollisionRectangle().getBottomLine();
            Line rightLine = gameBoard.getClosestCollision(trajectory).collisionObject()
                    .getCollisionRectangle().getRightLine();
            Line leftLine = gameBoard.getClosestCollision(trajectory).collisionObject()
                    .getCollisionRectangle().getLeftLine();
            //getting out the collision point
            Point collisionPoint = gameBoard.getClosestCollision(trajectory).collisionPoint();
            //checking where is the point on the rectangle, moving the ball and update velocity
            if (upperLine.isPointOnRectangleLine(collisionPoint)) {
                double newX = collisionPoint.getX();
                double newY = collisionPoint.getY() - this.radious;
                Point afterCollision = new Point(newX, newY);
                this.circleCenter = afterCollision;
            }
            //checking bottom line
            if (bottomLine.isPointOnRectangleLine(collisionPoint)) {
                double newX = collisionPoint.getX();
                double newY = collisionPoint.getY() + this.radious;
                Point afterCollision = new Point(newX, newY);
                this.circleCenter = afterCollision;
            }
            //checking right line
            if (rightLine.isPointOnRectangleLine(collisionPoint)) {
                double newX = collisionPoint.getX() + this.radious;
                double newY = collisionPoint.getY();
                Point afterCollision = new Point(newX, newY);
                this.circleCenter = afterCollision;
            }
            //checking left line
            if (leftLine.isPointOnRectangleLine(collisionPoint)) {
                double newX = collisionPoint.getX() - this.radious;
                double newY = collisionPoint.getY();
                Point afterCollision = new Point(newX, newY);
                this.circleCenter = afterCollision;
            }
            //changing the ball's velocity
            Velocity afterHitVelocity = this.gameBoard.getClosestCollision(trajectory)
                    .collisionObject().hit(this, collisionPoint, this.circleVelocity);
            this.setVelocity(afterHitVelocity);
        }
    }

    /**
     * Moving the ball once according to the velocity of the ball.
     * if the ball is arriving to the border it changes directions
     *
     * @param leftSide  the left border of the screen, given from main
     * @param rightSide the right border of the screen, given from main
     */
    public void moveOneStepInsideFrames(int leftSide, int rightSide) {
        int leftBorder = leftSide + this.radious;
        int rightBorder = rightSide - this.radious;
        int upperBorder = leftSide + this.radious;
        int lowerBorder = rightSide - this.radious;
        //arrived to right or left lane
        if ((this.circleCenter.getX() + this.circleVelocity.getDx()) >= rightBorder
                || (this.circleCenter.getX() + this.circleVelocity.getDx()) <= leftBorder) {
            this.setVelocity((this.circleVelocity.getDx() * (-1)), this.circleVelocity.getDy());
        }

        //arrived to upper or lower lane
        if ((this.circleCenter.getY() + this.circleVelocity.getDy()) <= upperBorder
                || (this.circleCenter.getY() + this.circleVelocity.getDy()) >= lowerBorder) {
            this.setVelocity(this.circleVelocity.getDx(), (this.circleVelocity.getDy() * (-1)));
        }
        //updating movement
        this.circleCenter = this.getVelocity().applyToPoint(this.circleCenter);
    }

    /**
     * adding ball to the sprite's list.
     *
     * @param gameLevel the game that is holding the lists
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addSprite(this);
    }

    /**
     * removing ball from the sprite's lists.
     *
     * @param gameLevel the game that is holding the lists
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeSprite(this);
    }
}
