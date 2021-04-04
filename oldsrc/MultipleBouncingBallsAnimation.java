import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 29/03/2020
 */
public class MultipleBouncingBallsAnimation {

    /**
     * Polling random point for the ball inside 200*200 pixels.
     *
     * @param ballRadius radius of the ball from user
     * @return random point for the ball
     */
    public static Point randPoint(int ballRadius) {
        int pixelsLimit = 200 - ballRadius;
        Random rand = new Random();
        //polling x location
        int x = rand.nextInt(pixelsLimit);
        if (x <= ballRadius) { //checks they are not outside
            x = ballRadius;
        }
        //poling y location
        int y = rand.nextInt(pixelsLimit);
        if (y <= ballRadius) {
            y = ballRadius;
        }
        //crating random point with the randomize values
        Point newPoint = new Point((double) x, (double) y);
        return newPoint;
    }

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
        } else {
            //as the ball smaller the speed is higher
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
     * Main function.
     * creating balls array, and balls for each argument from the user
     * also drawing the balls and their movement
     *
     * @param args main
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Multiple Bouncing balls Animation", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball[] ballArray = new Ball[args.length];
        //creating a ball with user's inputs starting in random point
        for (int i = 0; i < args.length; i++) {
            int radiusFromUser = Integer.parseInt(args[i]);
            //checking if the user's input is in range of the GUI and not negative
            if (radiusFromUser <= 0 || radiusFromUser > 100) {
                System.out.println("radius number " + (i + 1) + " has invalid radius size");
                radiusFromUser = 0;
            }
            //polling starting point and creating ball and his velocity
            Point newPoint = randPoint(radiusFromUser);
            Ball newBall = new Ball(newPoint, radiusFromUser, Color.YELLOW);
            Velocity randVelocity = velocityPoll(radiusFromUser);
            newBall.setVelocity(randVelocity);
            ballArray[i] = newBall;
        }

        //drawing the balls on the screen and moving them
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int j = 0; j < ballArray.length; j++) {
                ballArray[j].drawOn(d);
                ballArray[j].moveOneStep();
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

}
