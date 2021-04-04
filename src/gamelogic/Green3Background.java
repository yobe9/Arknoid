package gamelogic;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class Green3Background implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.GREEN);
        //background
        d.fillRectangle(31, 31, 738, 569);
        //tower
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 400, 100, 200);
        d.fillRectangle(135, 370, 30, 30);
        //antenna
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(143, 270, 15, 100);
        d.setColor(Color.RED);
        d.fillCircle(150, 265, 5);
        //windows
        d.setColor(Color.WHITE);
        //rows
        for (int i = 0; i < 5; i++) {
            //columns
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(110 + j * 17, 405 + i * 35, 10, 25);
            }
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
