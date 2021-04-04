package observers;

import geometryprimitive.Point;
import shapes.Ball;
import shapes.Block;
import gamelogic.GameLevel;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class BlockRemover implements HitListener {
    //fields
    private GameLevel gameLevel;
    private Counter remainingBlocks;

    /**
     * Constructor.
     *
     * @param gameLevel            the game with the lists of sprites and collidable
     * @param remainingBlocks number of remaining blocks
     */
    public BlockRemover(GameLevel gameLevel, Counter remainingBlocks) {
        this.gameLevel = gameLevel;
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit a block that was being hit
     * @param hitter   the shapes.Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //checking if the block that fot hit is one of the borders
        //first creating the upper left points of the borders
        Point upLeftTop = new Point(0.0, 0.0);
        Point upLeftLeft = new Point(0.0, 30.0);
        Point upLeftRight = new Point(770.0, 30.0);
        //comparing upper left points
        Boolean isTop = false;
        Boolean isLeft = false;
        Boolean isRight = false;
        //check if the hitted block is the top border
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(upLeftTop)) {
            isTop = true;
        }
        //check if the hitted block is the left border
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(upLeftLeft)) {
            isLeft = true;
        }
        //check if the hitted block is the left border
        if (beingHit.getCollisionRectangle().getUpperLeft().equals(upLeftRight)) {
            isRight = true;
        }
        //if we hit a border we dont want to remove it
        if (isTop || isLeft || isRight) {
            return;
        } else { //in regular cases
            beingHit.removeFromGame(this.gameLevel);
            this.remainingBlocks.decrease(1);
            return;
        }

    }
}
