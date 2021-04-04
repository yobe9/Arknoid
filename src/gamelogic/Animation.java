package gamelogic;

import biuoop.DrawSurface;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 05/06/2020
 */
public interface Animation {
    /**
     * Runs one frame, draw objects and check the if.
     *
     * @param d draw surface
     */
    void doOneFrame(DrawSurface d);

    /**
     * Return true or false if the should stop.
     *
     * @return true or false if the should stop
     */
    boolean shouldStop();
}
