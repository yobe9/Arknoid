import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 29/03/2020
 */
public class MultipleFramesBouncingBallsAnimation {
    /**
     * Creating velocity for the shapes.Ball according to it's size.
     *
     * @param radius of the ball from the user
     * @return velocity of the ball
     */
    public static Velocity velocityPoll(int radius) {
        int newSpeed;
        //balls above 50 radius are moving at the same speed
        if (radius >= 50) {
            newSpeed = 1;
        } else { //as the ball smaller the speed is higher
            newSpeed = 50 - radius;
        }
        //randomizing angle for the direction of movement of the ball
        Random rand = new Random();
        int randAngle = rand.nextInt(359);
        Velocity newVelocity;
        newVelocity = Velocity.fromAngleAndSpeed((double) randAngle, (double) newSpeed);
        return newVelocity;
    }

    /**
     * Polling random point for the ball inside given limits.
     *
     * @param leftTop     the left border of the pixels of given limits
     * @param rightBottom the right border of the pixels of given limits
     * @param ballRadius  radius of the ball from user
     * @return random point for the ball
     */
    public static Point randPointFrame(int leftTop, int rightBottom, int ballRadius) {
        //limiting the random to be between the limits, considering his radius
        int pixelsLimitTop = leftTop + ballRadius;
        int pixelsLimitBottom = rightBottom - ballRadius;
        Random rand = new Random();
        int x = rand.nextInt(pixelsLimitBottom);
        //if the random is below the lower limit change it to the first optional point
        if (x <= pixelsLimitTop) { //checks they are not outside
            x = pixelsLimitTop;
        }
        int y = rand.nextInt(pixelsLimitBottom);
        //if the random is below the lower limit change it to the first optional point
        if (y <= pixelsLimitTop) {
            y = pixelsLimitTop;
        }

        Point newPoint = new Point((double) x, (double) y);
        return newPoint;
    }

    /**
     * Main function.
     * creating balls array, and balls for each argument from the user
     * also drawing the balls,their movement and the frames
     *
     * @param args main
     */
    public static void main(String[] args) {
        int width = 700;
        int height = 700;
        GUI gui = new GUI("Multiple Frames Bouncing balls Animation", width, height);
        Sleeper sleeper = new Sleeper();
        Ball[] ballArray = new Ball[args.length];
        //creating balls with user's inputs starting in a random point
        //creating the first half inside the first frame
        for (int i = 0; i < args.length / 2; i++) {
            int radiusFromUser = Integer.parseInt(args[i]);
            //checking if the user's input is in range of the GUI and not negative
            if (radiusFromUser <= 0 || radiusFromUser > 225) {
                System.out.println("Radius number " + (i + 1) + " has invalid radius size");
                radiusFromUser = 0;
            }
            //polling starting point inside first frame and creating ball and his velocity
            Point newPoint = randPointFrame(50, 500, radiusFromUser);
            Ball newBall = new Ball(newPoint, radiusFromUser, Color.BLUE);
            Velocity randVelocity;
            randVelocity = velocityPoll(radiusFromUser);
            newBall.setVelocity(randVelocity);
            ballArray[i] = newBall;
        }
        //creating the second half inside the second frame
        for (int i = args.length / 2; i < args.length; i++) {
            int radiusFromUser = Integer.parseInt(args[i]);
            //checking if the user's input is in range of the GUI and not negative
            if (radiusFromUser <= 0 || radiusFromUser > 75) {
                System.out.println("Radius number " + (i + 1) + " has invalid radius size");
                radiusFromUser = 0;
            }
            //polling starting point inside first frame and creating ball and his velocity
            Point newPoint = randPointFrame(450, 600, radiusFromUser);
            Ball newBall = new Ball(newPoint, radiusFromUser, Color.BLUE);
            Velocity randVelocity;
            randVelocity = velocityPoll(radiusFromUser);
            newBall.setVelocity(randVelocity);
            ballArray[i] = newBall;
        }
        //drawing the balls inside the frames on the screen and moving them
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            //drawing the frames
            d.setColor(Color.GRAY);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.YELLOW);
            d.fillRectangle(450, 450, 150, 150);
            //drawing the balls inside their frames
            for (int j = 0; j < ballArray.length / 2; j++) {
                ballArray[j].moveOneStepInsideFrames(50, 500);
                ballArray[j].drawOn(d);
            }
            for (int j = ballArray.length / 2; j < ballArray.length; j++) {
                ballArray[j].moveOneStepInsideFrames(450, 600);
                ballArray[j].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
