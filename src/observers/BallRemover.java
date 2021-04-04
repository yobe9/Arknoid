package observers;

import geometryprimitive.Point;
import shapes.Ball;
import shapes.Block;
import gamelogic.GameLevel;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class BallRemover implements HitListener {
    //fields
    private GameLevel gameLevel;
    private Counter remainingBalls;

    /**
     * Constructor.
     *
     * @param gameLevel           the game with the lists of sprites and collidable
     * @param remainingBalls number of remaining balls
     */
    public BallRemover(GameLevel gameLevel, Counter remainingBalls) {
        this.gameLevel = gameLevel;
        this.remainingBalls = remainingBalls;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit a block that was being hit
     * @param hitter   the shapes.Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //checking if we hit the death block
        //creating the upper left point of the death block
        Point upLeftDeathBlock = new Point(0.0, 600.0);
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(upLeftDeathBlock)) {
            hitter.removeFromGame(this.gameLevel);
            this.remainingBalls.decrease(1);
            return;
        }
        //in regular cases
        return;

    }
}
