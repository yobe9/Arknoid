package gamelogic;

import shapes.Block;
import shapes.Sprite;
import shapes.Velocity;

import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 05/06/2020
 */
public interface LevelInformation {
    /**
     * Return the number of balls needed in the level.
     *
     * @return the number of balls needed in the level
     */
    int numberOfBalls();

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return a list with the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * Return the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    int paddleSpeed();

    /**
     * Return the width of the paddle.
     *
     * @return the speed of the paddle
     */
    int paddleWidth();

    /**
     * Returns the level name that will be displayed at the top of the screen.
     *
     * @return the level name that will be displayed at the top of the screen
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list with the Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number of blocks that should be removed
     */
    int numberOfBlocksToRemove();
}
