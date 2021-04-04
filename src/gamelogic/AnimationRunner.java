package gamelogic;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 05/06/2020
 */
public class AnimationRunner {
    //fields
    private GUI gui;
    private int framesPerSecond;

    /**
     * constructor.
     *
     * @param gui of the game
     */
    public AnimationRunner(GUI gui) {
        this.gui = gui;
        //default 60 frames per seconds
        this.framesPerSecond = 60;
    }

    /**
     * Gets animation of the game and runs it.
     *
     * @param animation to run the game
     */
    public void run(Animation animation) {
        Sleeper sleeper = new Sleeper();
        int millisecondsPerFrame = 1000 / this.framesPerSecond;
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis(); // timing
            //game logic
            DrawSurface d = this.gui.getDrawSurface();

            animation.doOneFrame(d);

            this.gui.show(d);

            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}
