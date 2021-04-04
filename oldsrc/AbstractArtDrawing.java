import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * @author Yoav Berger <yoavbrgr@gmail.com> ID 313268393
 * @since 28/03/2020
 */
public class AbstractArtDrawing {
    /**
     * Returns random line.
     *
     * @return random line
     */
    public static Line generateRandomLine() {
        Random rand = new Random();
        int x1 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y1 = rand.nextInt(300) + 1; // get integer in range 1-300
        int x2 = rand.nextInt(400) + 1; // get integer in range 1-400
        int y2 = rand.nextInt(300) + 1; // get integer in range 1-300
        Point start = new Point(x1, y1);
        Point end = new Point(x2, y2);
        Line newLine = new Line(start, end);
        return newLine;
    }

    /**
     * Draw a line.
     *
     * @param l line from main
     * @param d parameter allowing to draw
     */
    public static void drawLineOnScreen(Line l, DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawLine((int) l.start().getX(), (int) l.start().getY(), (int) l.end().getX(), (int) l.end().getY());
    }

    /**
     * Draw a line.
     *
     * @param circlePoint point from created line
     * @param d           parameter allowing to draw
     */
    public static void drawRedCircle(Point circlePoint, DrawSurface d) {
        d.setColor(Color.RED);
        int radius = 3;
        d.fillCircle((int) circlePoint.getX(), (int) circlePoint.getY(), radius);
    }

    /**
     * Draw a line.
     *
     * @param circlePoint point from created line
     * @param d           parameter allowing to draw
     */
    public static void drawBlueCircle(Point circlePoint, DrawSurface d) {
        d.setColor(Color.BLUE);
        int radius = 3;
        d.fillCircle((int) circlePoint.getX(), (int) circlePoint.getY(), radius);
    }

    /**
     * Main function.
     * creating random lines and show them on the screen with middle points and intersecting points
     *
     * @param args main
     */
    public static void main(String[] args) {
        //creating array of lines, draw surface and a GUI
        Line[] lineArray = new Line[10];
        GUI gui = new GUI("Random Lines Tester", 400, 300);
        DrawSurface d = gui.getDrawSurface();
        //generating 10 lines and draw them and their middle points
        for (int i = 0; i < 10; i++) {
            Line rndLine = generateRandomLine();
            lineArray[i] = rndLine;
            drawLineOnScreen(rndLine, d);
            Point mid = rndLine.middle();
            drawBlueCircle(mid, d);
        }
        //drawing intersections
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (lineArray[i].isIntersecting(lineArray[j])) {
                    Point intersectPoint = lineArray[i].intersectionWith(lineArray[j]);
                    drawRedCircle(intersectPoint, d);
                }
            }
        }
        //showing the drawing
        gui.show(d);
    }
}
