package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import observers.Counter;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class YouWinScreen implements Animation {
    //fields
    private KeyboardSensor keyboard;
    private boolean stop;
    private Counter scrCounter;

    /**
     * constructor.
     *
     * @param k   keyboard sensor
     * @param scr score of the game
     */
    public YouWinScreen(KeyboardSensor k, Counter scr) {
        this.keyboard = k;
        this.stop = false;
        this.scrCounter = scr;
    }

    /**
     * Runs one frame, draw objects and check the if.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + this.scrCounter.getValue(), 32);
    }

    /**
     * Return true or false if the should stop.
     *
     * @return true or false if the should stop
     */
    @Override
    public boolean shouldStop() {
        return this.stop;
    }
}
