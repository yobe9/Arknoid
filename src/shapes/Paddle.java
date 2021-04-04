package shapes;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimitive.Line;
import geometryprimitive.Point;
import geometryprimitive.Rectangle;
import gamelogic.GameLevel;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class Paddle implements Sprite, Collidable {
    //fields
    private biuoop.KeyboardSensor keyboard;
    private Rectangle paddleRec;
    private Color paddleColor;

    /**
     * constructor.
     *
     * @param paddleRec   the rectangle of the padle
     * @param paddleColor the color of the padle
     * @param ks          keyboard of the game
     */
    public Paddle(Rectangle paddleRec, Color paddleColor, KeyboardSensor ks) {
        this.paddleRec = paddleRec;
        this.paddleColor = paddleColor;
        this.keyboard = ks;
    }

    /**
     * moving the paddle to the left.
     */
    public void moveLeft() {
        double basicMovement = 5.0;
        double leftBorder = 30.0;
        double movmentX = this.paddleRec.getUpperLeft().getX() - basicMovement;
        //without getting out the borders
        if (movmentX > leftBorder) {
            this.paddleRec.setUpperLeft(movmentX, this.paddleRec.getUpperLeft().getY());
        }
    }

    /**
     * moving the paddle to the right.
     */
    public void moveRight() {
        double basicMovement = 5.0;
        double rightBorder = 770.0;
        double rectWidth = this.paddleRec.getWidth();
        double movmentX = this.paddleRec.getUpperLeft().getX() + basicMovement;
        //without getting out the borders
        if (movmentX + rectWidth < rightBorder) {
            this.paddleRec.setUpperLeft(movmentX, this.paddleRec.getUpperLeft().getY());
        }
    }

    // shapes.Sprite

    /**
     * checking if an arrow key had been pressed and moving the paddle accordingly.
     */
    @Override
    public void timePassed() {
        //move to the left
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        //move to the right
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Drawing the paddle on the given DrawSurface.
     *
     * @param d drawing board from main
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(this.paddleColor);
        d.fillRectangle((int) this.paddleRec.getUpperLeft().getX(),
                (int) this.paddleRec.getUpperLeft().getY(), (int) this.paddleRec.getWidth(),
                (int) this.paddleRec.getHeight());
        d.setColor(Color.WHITE);
        d.drawRectangle((int) this.paddleRec.getUpperLeft().getX(),
                (int) this.paddleRec.getUpperLeft().getY(), (int) this.paddleRec.getWidth(),
                (int) this.paddleRec.getHeight());
    }

    /**
     * return the suitable rectangle.
     *
     * @return the suitable rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.paddleRec;
    }

    /**
     * checks on which line the point is on and update the velocity accordingly.
     *
     * @param hitter          the ball that hits the paddle
     * @param collisionPoint  the point that is on the paddle
     * @param currentVelocity of the hitting ball
     * @return updated velocity after hitting the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //need to relate to cases its hitting the right, left, bottom
        boolean isUpper = false, isBottom = false, isRight = false, isLeft = false;
        //keeping the old data
        double hitDx = currentVelocity.getDx();
        double hitDy = currentVelocity.getDy();
        //converting dx and dy into speed with pythagoras
        double ballSpeed = Math.sqrt(Math.pow(currentVelocity.getDx(), 2)
                + Math.pow(currentVelocity.getDy(), 2));
        //checking if the point is in the upper line
        if (this.paddleRec.getUpperLine().isPointOnRectangleLine(collisionPoint)) {
            isUpper = true;
            //dividing the paddle line into 5 areas
            Line[] linesArr = new Line[5];
            double regLength = this.paddleRec.getUpperLine().length() / 5;
            //every time the left upper point is moving right
            for (int i = 0; i < 5; i++) {
                Point regLeftPoint = new Point(this.paddleRec.getUpperLeft().getX() + (regLength * i),
                        this.paddleRec.getUpperLeft().getY());
                double regRightBorder = this.paddleRec.getUpperLeft().getX() + (regLength * (i + 1));
                Point regRightPoint = new Point(regRightBorder,
                        this.paddleRec.getUpperLeft().getY());
                Line reg = new Line(regLeftPoint, regRightPoint);
                linesArr[i] = reg;
            }
            //check if left reg was hit 330 equals 330 -90
            if (linesArr[0].isPointOnRectangleLine(collisionPoint)) {
                Velocity velLeft = Velocity.fromAngleAndSpeed(210.0, ballSpeed);
                return velLeft;
            }
            //check if left-mid reg was hit, 300 equals 300 - 90
            if (linesArr[1].isPointOnRectangleLine(collisionPoint)) {
                Velocity velMidLeft = Velocity.fromAngleAndSpeed(240.0, ballSpeed);
                return velMidLeft;
            }
            //check if mid reg was hit, 360 equals 360-90
            if (linesArr[2].isPointOnRectangleLine(collisionPoint)) {
                Velocity velMid = Velocity.fromAngleAndSpeed(180.0, ballSpeed);
                return velMid;
            }
            //check if right-mid reg was hit, 30 equals 30 - 90 + 360
            if (linesArr[3].isPointOnRectangleLine(collisionPoint)) {
                Velocity velMidRight = Velocity.fromAngleAndSpeed(120.0, ballSpeed);
                return velMidRight;
            }
            //check if right reg was hit, 60 equals 60 - 90 + 360
            if (linesArr[4].isPointOnRectangleLine(collisionPoint)) {
                Velocity velRight = Velocity.fromAngleAndSpeed(150.0, ballSpeed);
                return velRight;
            }
        }
        //checking if the point is on the bottom line
        if (this.paddleRec.getBottomLine().isPointOnRectangleLine(collisionPoint)) {
            isBottom = true;
        }
        //checking if the point is on the right line
        if (this.paddleRec.getRightLine().isPointOnRectangleLine(collisionPoint)) {
            isRight = true;
        }
        //checking if the point is on the left line
        if (this.paddleRec.getLeftLine().isPointOnRectangleLine(collisionPoint)) {
            isLeft = true;
        }
        //checking if we didn't hit the block
        if (!isBottom && !isUpper && !isRight && !isLeft) {
            return currentVelocity;
        }
        //changing velocity if we hit right or left
        if (isRight || isLeft) {
            hitDx = currentVelocity.getDx() * (-1);
        }
        //creating the new velocity
        Velocity afterHitVelocity = new Velocity(hitDx, hitDy);
        return afterHitVelocity;
    }

    /**
     * Add this paddle to the game.
     *
     * @param g the game
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
        g.addSprite(this);
    }
}