package gamelogic;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class FinalFourBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.CYAN);
        //background
        d.fillRectangle(31, 31, 738, 569);
        d.setColor(Color.LIGHT_GRAY);
        //first cloud
        d.fillCircle(115, 380, 30);
        d.fillCircle(145, 380, 30);
        d.fillCircle(175, 380, 30);
        for (int i = 0; i < 4; i++) {
            d.fillCircle(100 + i * 30, 400, 30);
            d.drawLine(85 + i * 30, 400, (85 + i * 30) - 10, 600);
            d.drawLine(95 + i * 30, 400, (95 + i * 30) - 10, 600);
            d.drawLine(105 + i * 30, 400, (105 + i * 30) - 10, 600);
            d.drawLine(115 + i * 30, 400, (115 + i * 30) - 10, 600);
        }

        //second cloud
        d.fillCircle(615, 480, 30);
        d.fillCircle(645, 480, 30);
        d.fillCircle(675, 480, 30);
        for (int j = 0; j < 4; j++) {
            d.fillCircle(600 + j * 30, 500, 30);
            d.drawLine(585 + j * 30, 500, (585 + j * 30) - 10, 600);
            d.drawLine(595 + j * 30, 500, (595 + j * 30) - 10, 600);
            d.drawLine(605 + j * 30, 500, (605 + j * 30) - 10, 600);
            d.drawLine(615 + j * 30, 500, (615 + j * 30) - 10, 600);
        }
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
