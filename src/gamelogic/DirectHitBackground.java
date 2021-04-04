package gamelogic;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class DirectHitBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        //background
        d.fillRectangle(31, 31, 738, 569);
        d.setColor(Color.BLUE);
        //three circles
        d.drawCircle(400, 160, 30);
        d.drawCircle(400, 160, 40);
        d.drawCircle(400, 160, 50);
        //top line
        d.drawLine(400, 100, 400, 145);
        //bottom line
        d.drawLine(400, 175, 400, 215);
        //left line
        d.drawLine(385, 160, 345, 160);
        //right line
        d.drawLine(415, 160, 455, 160);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
