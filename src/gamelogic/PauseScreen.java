package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 05/06/2020
 */
public class PauseScreen implements Animation {
    //fields
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * constructor.
     *
     * @param k keyboard sensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Runs one frame, draw objects and checks the if.
     * pause the game if needed
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
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
