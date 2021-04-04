package shapes;

import geometryprimitive.Point;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class CollisionInfo {
    //fields
    private Point collisPoint;
    private Collidable colideObject;

    /**
     * constructor.
     *
     * @param newCollisionPoint the point that the collision occur
     * @param newColideObject   the collision object
     */
    public CollisionInfo(Point newCollisionPoint, Collidable newColideObject) {
        this.collisPoint = newCollisionPoint;
        this.colideObject = newColideObject;
    }

    /**
     * return the point at which the collision occurs.
     *
     * @return the point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisPoint;
    }

    /**
     * return the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.colideObject;
    }
}