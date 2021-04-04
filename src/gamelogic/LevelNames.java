package gamelogic;

import biuoop.DrawSurface;
import shapes.Sprite;

import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class LevelNames implements Sprite {
    //fields
    private String levelName;

    /**
     * Constructor.
     *
     * @param lName the name of the level
     */
    public LevelNames(String lName) {
        this.levelName = lName;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d drawing surface
     */
    @Override
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(600, 10, "Level Name: " + this.levelName, 10);
    }

    /**
     * notify the sprite that time has passed.
     */
    @Override
    public void timePassed() {

    }
}
