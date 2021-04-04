package observers;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public interface HitNotifier {

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a listener for the hitting
     */
    void addHitListener(HitListener hl);

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a listener for the hitting
     */
    void removeHitListener(HitListener hl);
}
