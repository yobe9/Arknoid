package gamelogic;

import geometryprimitive.Line;
import geometryprimitive.Point;
import shapes.Collidable;
import shapes.CollisionInfo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 26/03/2020
 */
public class GameEnvironment {
    //fields
    private List<Collidable> collidables;

    /**
     * constructor.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c given collidable
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param c given collidable
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory line that represent the ball movement
     * @return the collision info of the closest collision
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point nearestPoint = null;
        Point tmpPoint = null;
        Collidable currentColide = null;
        // initializing the minimal distance with the longest distance + constant of my choice
        double constantDistanceAdding = 100.0;
        double minimalDistance = trajectory.length() + constantDistanceAdding;
        //iterating threw the collidable objects and checking if they are collides with the line
        for (Collidable c : collidables) {
            //getting the collision point
            tmpPoint = trajectory.closestIntersectionToStartOfLine(c.getCollisionRectangle());
            //in case there is a collision
            if (tmpPoint != null) {
                //checking if the distance is smaller than other hitting points
                if (tmpPoint.distance(trajectory.getOldStart()) < minimalDistance) {
                    minimalDistance = tmpPoint.distance(trajectory.getOldStart());
                    nearestPoint = tmpPoint;
                    currentColide = c;
                }
            }
        }
        //in case there was no collision
        if (minimalDistance == trajectory.length() + constantDistanceAdding) {
            return null;
        }
        //creating the collision info
        CollisionInfo newCollisionInfo = new CollisionInfo(nearestPoint, currentColide);
        return newCollisionInfo;
    }

    /**
     * returning the game enviorment list.
     *
     * @return the game enviorment list
     */
    //
    //
    public List<Collidable> getGameList() {
        return this.collidables;
    }

}