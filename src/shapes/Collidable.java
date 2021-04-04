package shapes;

import geometryprimitive.Point;
import geometryprimitive.Rectangle;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return the "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with
     * a given velocity.
     * The return is the new velocity expected after the hit (based on
     * the force the object inflicted on us).
     *
     * @param hitter          that hits the collidable object
     * @param collisionPoint  with the collidable object
     * @param currentVelocity the current velocity of the ball
     * @return is the new velocity expected after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}