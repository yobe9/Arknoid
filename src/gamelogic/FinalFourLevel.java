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
public class FinalFourLevel implements LevelInformation {
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
    public FinalFourLevel() {
        this.numOfBalls = 3;
        this.initialBallVelocity = new ArrayList<Velocity>();
        this.paddleSpeed = 5;
        this.paddleWidth = 50;
        this.levelName = "Final Four";
        this.blocks = new ArrayList<Block>();
        this.numOfBlocks = 105;
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
        for (int i = 0; i < 3; i++) {
            Velocity vel = new Velocity(0, -2 - i);
            this.initialBallVelocity.add(vel);
        }
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
        FinalFourBackground back = new FinalFourBackground();
        return back;
    }

    /**
     * The Blocks that make up this level, each block contains
     * its size, color and location.
     *
     * @return a list with the Blocks that make up this level
     */
    @Override
    public List<Block> blocks() {
        //creating the colors for the blocks
        Color[] arrColor = new Color[7];
        arrColor[0] = Color.LIGHT_GRAY;
        arrColor[1] = Color.RED;
        arrColor[2] = Color.YELLOW;
        arrColor[3] = Color.GREEN;
        arrColor[4] = Color.WHITE;
        arrColor[5] = Color.PINK;
        arrColor[6] = Color.BLUE;

        //creating a fake list for the blocks
        List<HitListener> defaultListenerList = new ArrayList<HitListener>();

        Point firstUpperLeftPoint = new Point(720.0, 150.0);
        double blockWidth = 49.0;
        double blockHeight = 30.0;
        //creating the rows of the block
        int maxBlocksInRows = 15;
        for (int i = 1; i <= 5; i++) {
            for (int j = 1; j <= maxBlocksInRows; j++) {
                //adding one after one in the row
                Rectangle rec = new Rectangle(firstUpperLeftPoint, blockWidth, blockHeight);
                Block blk = new Block(rec, arrColor[i - 1], defaultListenerList);
                //adding block to the list
                this.blocks.add(blk);
                Point iterationPoint = new Point(firstUpperLeftPoint.getX() - blockWidth,
                        firstUpperLeftPoint.getY());
                firstUpperLeftPoint = iterationPoint;
            }
            //every new line
            Point tmpPoint = new Point(720, firstUpperLeftPoint.getY() + blockHeight);
            firstUpperLeftPoint = tmpPoint;
            //maxBlocksInRows--;
        }
        return this.blocks;
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
