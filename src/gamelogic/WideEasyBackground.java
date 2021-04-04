package gamelogic;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class WideEasyBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        //drawing the sun
        d.setColor(Color.YELLOW);
        d.fillCircle(75, 100, 35);
        //drawing the lines
        for (int i = 1; i <= 50; i++) {
            d.drawLine(40, 100, 50 + i * 10, 150 + i);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
