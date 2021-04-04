package observers;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class ScoreIndicator implements Sprite {
    //fields
    private Counter scoreIndicate;

    /**
     * Constructor.
     *
     * @param scoreCounter the score of the game
     */
    public ScoreIndicator(Counter scoreCounter) {
        this.scoreIndicate = scoreCounter;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(0, 2, 800, 15);
        d.setColor(Color.BLACK);
        d.drawText(400, 10, "Score: " + scoreIndicate.getValue(), 10);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
