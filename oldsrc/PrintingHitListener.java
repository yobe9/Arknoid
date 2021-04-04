package observers;

import shapes.Ball;
import shapes.Block;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class PrintingHitListener implements HitListener {
    // fields

    //constructor

    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit a block that was being hit
     * @param hitter   the shapes.Ball that's doing the hitting
     */
    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        System.out.println("A shapes.Block was hit.");
    }
}
