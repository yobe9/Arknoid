package gamelogic;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import geometryprimitive.Point;
import geometryprimitive.Rectangle;
import observers.BallRemover;
import observers.BlockRemover;
import observers.Counter;
import observers.HitListener;
import observers.ScoreIndicator;
import observers.ScoreTrackingListener;
import shapes.Ball;
import shapes.Block;
import shapes.Collidable;
import shapes.Paddle;
import shapes.Sprite;
import shapes.SpriteCollection;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 27/04/2020
 */
public class GameLevel implements Animation {
    //fields
    private SpriteCollection sprites;
    private GameEnvironment environment;
    //private GUI gui;
    private Counter blockCounter;
    private Counter ballCounter;
    private Counter scoreCounter;
    private AnimationRunner runner;
    private boolean running;
    private LevelInformation levelInfo;
    private KeyboardSensor keyboardSensor;

    /**
     * constructor.
     *
     * @param levelInfo information about the levels
     * @param ks        keyboard sensor
     * @param ar        animation runner
     * @param scr       the counter of the score of the game
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor ks, AnimationRunner ar, Counter scr) {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        //creating the score counter
        this.scoreCounter = scr;
        //creating runner and setting running
        this.runner = ar;
        this.running = true;
        this.levelInfo = levelInfo;
        this.keyboardSensor = ks;
    }

    /**
     * add the given collidable to the environment.
     *
     * @param c given collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * add the given sprite to the list.
     *
     * @param s given sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and shapes.Ball (and shapes.Paddle)
     * and add them to the game.
     */
    public void initialize() {
        //creating background with sprites
        this.sprites.addSprite(this.levelInfo.getBackground());
        //creating the balls
        //level one option
        if (this.levelInfo.numberOfBalls() == 1) {
            //creating one ball
            Ball ball = new Ball(400, 530, 5, Color.BLUE, this.environment);
            ball.setVelocity(this.levelInfo.initialBallVelocities().get(0));
            ball.addToGame(this);
        }
        //case of level 2
        if (this.levelInfo.numberOfBalls() == 10) {
            //creating 10 balls
            for (int i = 0; i < 10; i++) {
                Ball ball = new Ball(200 + i * 50, 530, 5, Color.BLUE, this.environment);
                ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
                ball.addToGame(this);
            }
        }
        //case of level 3
        if (this.levelInfo.numberOfBalls() == 2) {
            //creating 10 balls
            for (int i = 0; i < 2; i++) {
                Ball ball = new Ball(300 + i * 200, 530, 5, Color.BLUE, this.environment);
                ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
                ball.addToGame(this);
            }

        }
        //case of level 4
        if (this.levelInfo.numberOfBalls() == 3) {
            //creating 3 balls
            for (int i = 0; i < 3; i++) {
                Ball ball = new Ball(300 + i * 100, 530, 5, Color.BLUE, this.environment);
                ball.setVelocity(this.levelInfo.initialBallVelocities().get(i));
                ball.addToGame(this);
            }
        }
        //creating the counter according to level info
        this.blockCounter = new Counter(this.levelInfo.numberOfBlocksToRemove());
        //create a new listener that remove the hitting
        BlockRemover blRemove = new BlockRemover(this, this.blockCounter);
        //creating the list of listeners and adding the listener to the list
        List<HitListener> blockListeners = new ArrayList<HitListener>();
        blockListeners.add(blRemove);

        //creating the ball counter according to level info
        this.ballCounter = new Counter(this.levelInfo.numberOfBalls());
        //creating a new listener that remove the ball
        BallRemover balRemove = new BallRemover(this, this.ballCounter);
        //creating the list of the ball remove listeners
        List<HitListener> ballListeners = new ArrayList<HitListener>();
        ballListeners.add(balRemove);

        //creating a new listener for the score
        ScoreTrackingListener scrTrack = new ScoreTrackingListener(this.scoreCounter);
        //adding the listener to the list of the block hitting
        blockListeners.add(scrTrack);
        //creating boundaries
        //upper
        Point upperLeftPoint = new Point(0.0, 0.0);
        Rectangle upperRec = new Rectangle(upperLeftPoint, 800, 30);
        Block upperBlock = new Block(upperRec, Color.GRAY, blockListeners);
        upperBlock.addToGame(this);
        //death block
        Point deathBlockLeftPoint = new Point(0.0, 600.0);
        Rectangle deathRec = new Rectangle(deathBlockLeftPoint, 800, 30);
        Block deathBlock = new Block(deathRec, Color.GRAY, ballListeners);
        deathBlock.addToGame(this);
        //left
        Point upperLeftLinePoinr = new Point(0.0, 30.0);
        Rectangle leftRec = new Rectangle(upperLeftLinePoinr, 30, 570);
        Block leftBlock = new Block(leftRec, Color.GRAY, blockListeners);
        leftBlock.addToGame(this);
        //right
        Point upperRightLinePoint = new Point(770.0, 30.0);
        Rectangle rightRec = new Rectangle(upperRightLinePoint, 30, 570);
        Block rightBlock = new Block(rightRec, Color.GRAY, blockListeners);
        rightBlock.addToGame(this);

        //creating blocks
        //initializing basic block logic
        for (Block blk : this.levelInfo.blocks()) {
            blk.setHitListeners(blockListeners);
            blk.addToGame(this);
        }

        //creating the paddle
        Point upperLeftPaddle = new Point(400, 540.0);
        //in case we are in the Wide Easy level
        if (this.levelInfo.levelName() == "Wide Easy") {
            upperLeftPaddle = new Point(70, 540);
        }
        Rectangle paddleRec = new Rectangle(upperLeftPaddle, this.levelInfo.paddleWidth(), 30.0);
        Paddle paddle = new Paddle(paddleRec, Color.YELLOW, this.keyboardSensor);
        paddle.addToGame(this);
        //creating the score indicator
        ScoreIndicator scrIndicator = new ScoreIndicator(this.scoreCounter);
        this.sprites.addSprite(scrIndicator);
        //drawing level name and add to sprite
        LevelNames levelN = new LevelNames(this.levelInfo.levelName());
        this.sprites.addSprite(levelN);
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        this.running = true;
        this.runner.run(this);
    }

    /**
     * remove the given collidable from the environment.
     *
     * @param c given collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * remove the given sprite from the list.
     *
     * @param s given sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Runs one frame, draw objects and check the if.
     *
     * @param d draw surface
     */
    @Override
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();
        //checking if pause has been pressed
        if (this.keyboardSensor.isPressed("p")) {
            PauseScreen pause = new PauseScreen(this.keyboardSensor);
            KeyPressStoppableAnimation stopAnimation = new KeyPressStoppableAnimation(this.keyboardSensor,
                    this.keyboardSensor.SPACE_KEY.toString(), pause);
            this.runner.run(stopAnimation);
        }
        //closing the game if there are no more game blocks and adding 100 to the score
        if (this.blockCounter.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }
        //closing the game if there are no more balls
        if (this.ballCounter.getValue() == 0) {
            this.running = false;
        }
    }

    /**
     * Return true or false if the should stop.
     *
     * @return true or false if the should stop
     */
    @Override
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Returns the ball counter.
     *
     * @return the ball counter
     */
    public int getBallCounterValue() {
        return this.ballCounter.getValue();
    }
}