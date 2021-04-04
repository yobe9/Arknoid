import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 28/03/2020
 */
public class BouncingBallAnimation {

    /**
     * Creating GUI and drawing the moving ball.
     *
     * @param start the starting point of the ball
     * @param dx    vertical advance
     * @param dy    horizontal advance
     */
    static void drawAnimation(Point start, double dx, double dy) {
        GUI gui = new GUI("title", 200, 200);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball((int) start.getX(), (int) start.getY(), 5, java.awt.Color.BLACK);
        ball.setVelocity(dx, dy);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }

    /**
     * Main function.
     * creating point and forwarding data to the animation method according to user's inputs
     *
     * @param args main
     */
    public static void main(String[] args) {
        if (args.length != 4 || Double.parseDouble(args[0]) < 0 || Double.parseDouble(args[1]) < 0
                || Double.parseDouble(args[0]) > 200 || Double.parseDouble(args[1]) > 200) {
            System.out.println("Invalid input amount, or out of range");
        } else {
            double xFromUser = Double.parseDouble(args[0]);
            double yFromUser = Double.parseDouble(args[1]);
            Point newPoint = new Point(xFromUser, yFromUser);
            double dxFromUser = Double.parseDouble(args[2]);
            double dyFromUser = Double.parseDouble(args[3]);
            drawAnimation(newPoint, dxFromUser, dyFromUser);
        }

    }
}
