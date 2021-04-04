package shapes;

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class SpriteCollection {
    //fields
    private List<Sprite> spriteList;

    /**
     * constructor.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }

    /**
     * add the given sprite to the list.
     *
     * @param s given sprite
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }

    /**
     * remove the given sprite from the list.
     *
     * @param s given sprite
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }

    /**
     * call timePassed() on all sprites.
     */
    public void notifyAllTimePassed() {
        //make a copy of the list before iterating
        List<Sprite> spriteListCopy = new ArrayList<Sprite>(this.spriteList);
        for (Sprite s : spriteListCopy) {
            s.timePassed();
        }
    }

    /**
     * draw all of the sprites to the screen.
     *
     * @param d drawing surface
     */
    public void drawAllOn(DrawSurface d) {
        for (Sprite s : spriteList) {
            s.drawOn(d);
        }
    }
}