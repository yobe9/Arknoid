package gamelogic;

import geometryprimitive.Point;
import geometryprimitive.Rectangle;
import observers.HitListener;
import shapes.Block;
import shapes.Sprite;
import shapes.Velocity;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 06/06/2020
 */
public class DirectHitLevel implements LevelInformation {
    //fields
    private int numOfBalls;
    private List<Velocity> initialBallVelocity;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private List<Block> blocks;
    private int numOfBlocks;

    /**
     * constructor.
     */
    public DirectHitLevel() {
        this.numOfBalls = 1;
        this.initialBallVelocity = new ArrayList<Velocity>();
        this.paddleSpeed = 5;
        this.paddleWidth = 50;
        this.levelName = "Direct Hit";
        this.blocks = new ArrayList<Block>();
        this.numOfBlocks = 1;
    }

    /**
     * Return the number of balls needed in the level.
     *
     * @return the number of balls needed in the level
     */
    @Override
    public int numberOfBalls() {
        return this.numOfBalls;
    }

    /**
     * The initial velocity of each ball.
     * Note that initialBallVelocities().size() == numberOfBalls()
     *
     * @return a list with the initial velocity of each ball
     */
    @Override
    public List<Velocity> initialBallVelocities() {
        Velocity vel = new Velocity(0.0, -2.0);
        this.initialBallVelocity.add(vel);
        return this.initialBallVelocity;
    }

    /**
     * Return the speed of the paddle.
     *
     * @return the speed of the paddle
     */
    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Return the width of the paddle.
     *
     * @return the speed of the paddle
     */
    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Returns the level name that will be displayed at the top of the screen.
     *
     * @return the level name that will be displayed at the top of the screen
     */
    @Override
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return a sprite with the background of the level
     */
    @Override
    public Sprite getBackground() {
        DirectHitBackground background = new DirectHitBackground();
        return background;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list with the Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {
        //creating a fake list for the blocks
        List<HitListener> defaultListenerList = new ArrayList<HitListener>();
        //creating the lonely block
        Point upperLeftPoint = new Point(390.0, 150.0);
        Rectangle loneRec = new Rectangle(upperLeftPoint, 20.0, 20.0);
        Block loneBlock = new Block(loneRec, Color.RED, defaultListenerList);
        //adding the block to the list
        this.blocks.add(loneBlock);
        return blocks;
    }

    /**
     * Number of blocks that should be removed
     * before the level is considered to be "cleared".
     * This number should be <= blocks.size();
     *
     * @return the number of blocks that should be removed
     */
    @Override
    public int numberOfBlocksToRemove() {
        return this.numOfBlocks;
    }
}
