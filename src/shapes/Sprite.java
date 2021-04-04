package shapes;

import biuoop.DrawSurface;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
