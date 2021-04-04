package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class KeyPressStoppableAnimation implements Animation {
    //field
    private KeyboardSensor keyboard;
    private String key;
    private Animation animation;
    private Boolean stop;
    private Boolean isAlreadyPressed;

    /**
     * constructor.
     *
     * @param sensor    keyboard sensor
     * @param key       string
     * @param animation of the game
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.keyboard = sensor;
        this.key = key;
        this.animation = animation;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Runs one frame, draw objects and check the if.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);
        if (this.keyboard.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }
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
