package observers;

import geometryprimitive.Point;
import shapes.Ball;
import shapes.Block;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class ScoreTrackingListener implements HitListener {
    //fields
    private Counter currentScore;

    /**
     * Constructor.
     *
     * @param scoreCounter the score of the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit a block that was being hit
     * @param hitter   the shapes.Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        //checking if the block that got hit is one of the borders
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
        } else { //when hits a game block
            this.currentScore.increase(5);
            return;
        }
    }
}
