package shapes;

import biuoop.DrawSurface;
import geometryprimitive.Point;
import geometryprimitive.Rectangle;
import gamelogic.GameLevel;
import observers.HitListener;
import observers.HitNotifier;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class Block implements Collidable, Sprite, HitNotifier {
    //fields
    private Rectangle blockRectangle;
    private java.awt.Color blockColor;
    private List<HitListener> hitListeners;

    /**
     * constructor.
     *
     * @param blockRectangle the specific rectangle
     * @param blockColor     the color of the block
     * @param hitListeners   list of listeners
     */
    public Block(Rectangle blockRectangle, java.awt.Color blockColor, List<HitListener> hitListeners) {
        this.blockRectangle = blockRectangle;
        this.blockColor = blockColor;
        this.hitListeners = hitListeners;
    }

    /**
     * return the suitable rectangle.
     *
     * @return the suitable rectangle
     */
    @Override
    public Rectangle getCollisionRectangle() {
        return this.blockRectangle;
    }

    /**
     * checks on which line the point is on and update the velocity accordingly.
     *
     * @param hitter          the ball that hits
     * @param collisionPoint  the point that is on the block
     * @param currentVelocity of the hitting ball
     * @return updated velocity after hitting the block
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        //getting out the old velocity's data
        double hitDx = currentVelocity.getDx();
        double hitDy = currentVelocity.getDy();
        boolean isUpper = false, isBottom = false, isRight = false, isLeft = false;
        //checking if the point is on the upper line
        if (this.blockRectangle.getUpperLine().isPointOnRectangleLine(collisionPoint)) {
            isUpper = true;
        }
        //checking if the point is on the bottom line
        if (this.blockRectangle.getBottomLine().isPointOnRectangleLine(collisionPoint)) {
            isBottom = true;
        }
        //checking if the point is on the right line
        if (this.blockRectangle.getRightLine().isPointOnRectangleLine(collisionPoint)) {
            isRight = true;
        }
        //checking if the point is on the left line
        if (this.blockRectangle.getLeftLine().isPointOnRectangleLine(collisionPoint)) {
            isLeft = true;
        }
        //checking if we didn't hit the block
        if (!isBottom && !isUpper && !isRight && !isLeft) {
            return currentVelocity;
        }
        //changing velocity if we hit upper or bottom
        if (isUpper || isBottom) {
            hitDy = currentVelocity.getDy() * (-1);
        }
        //changing velocity if we hit right or left
        if (isRight || isLeft) {
            hitDx = currentVelocity.getDx() * (-1);
        }
        //creating the new velocity
        Velocity afterHitVelocity = new Velocity(hitDx, hitDy);
        this.notifyHit(hitter);
        return afterHitVelocity;
    }

    /**
     * Drawing the ball on the given DrawSurface.
     *
     * @param surface drawing board from main
     */
    @Override
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.blockColor);
        surface.fillRectangle((int) blockRectangle.getUpperLeft().getX(),
                (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawRectangle((int) blockRectangle.getUpperLeft().getX(),
                (int) blockRectangle.getUpperLeft().getY(),
                (int) blockRectangle.getWidth(), (int) blockRectangle.getHeight());
    }

    /**
     * time Passed.
     */
    @Override
    public void timePassed() {

    }

    /**
     * adding block to the collidable and sprite's lists.
     *
     * @param gameLevel the game that is holding the lists
     */
    public void addToGame(GameLevel gameLevel) {
        gameLevel.addCollidable(this);
        gameLevel.addSprite(this);
    }

    /**
     * removing block from the collidable and sprite's lists.
     *
     * @param gameLevel the game that is holding the lists
     */
    public void removeFromGame(GameLevel gameLevel) {
        gameLevel.removeCollidable(this);
        gameLevel.removeSprite(this);
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a listener for the hitting
     */
    @Override
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a listener for the hitting
     */
    @Override
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Notify listeners about the hit events.
     *
     * @param hitter a ball that hits
     */
    private void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Set the list of the listeners of the block.
     *
     * @param updateHitListeners the new list the will be updated
     */
    public void setHitListeners(List<HitListener> updateHitListeners) {
        this.hitListeners = updateHitListeners;
    }
}
