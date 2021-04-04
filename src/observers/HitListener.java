package observers;

import shapes.Ball;
import shapes.Block;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit a block that was being hit
     * @param hitter   the shapes.Ball that's doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}
