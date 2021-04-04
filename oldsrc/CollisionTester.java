import biuoop.GUI;
import biuoop.DrawSurface;

import java.awt.Color;

import biuoop.Sleeper;

public class CollisionTester {
    public static void main(String[] args) {
        GUI gui = new GUI("title", 800, 600);
        Sleeper sleeper = new Sleeper();
        //game Enviroment
        GameEnvironment colidabels = new GameEnvironment();
        //List<shapes.Collidable> collidables = new ArrayList<shapes.Collidable>();
        //upper
        Point upperLeftPoint = new Point(0.0, 0.0);
        Rectangle upperRec = new Rectangle(upperLeftPoint, 800, 50);
        Block upperBlock = new Block(upperRec, Color.GRAY);
        colidabels.addCollidable(upperBlock);
        //bottom
        Point bottomLeftPoint = new Point(0.0, 550);
        Rectangle bottomRec = new Rectangle(bottomLeftPoint, 800, 50);
        Block bottomBlock = new Block(bottomRec, Color.GRAY);
        colidabels.addCollidable(bottomBlock);
        //left
        Point upperLeftLinePoinr = new Point(0.0, 10.0);
        Rectangle leftRec = new Rectangle(upperLeftLinePoinr, 50, 580);
        Block leftBlock = new Block(leftRec, Color.GRAY);
        colidabels.addCollidable(leftBlock);
        //right
        Point upperRightLinePoint = new Point(750.0, 10.0);
        Rectangle rightRec = new Rectangle(upperRightLinePoint, 50, 580);
        Block rightBlock = new Block(rightRec, Color.GRAY);
        colidabels.addCollidable(rightBlock);
        //first shapes.Block
        Rectangle fRec = new Rectangle(new Point(300, 400), 100, 50);
        Block fBlock = new Block(fRec, Color.BLUE);
        colidabels.addCollidable(fBlock);
        //second
        Rectangle sRec = new Rectangle(new Point(50, 500), 600, 20);
        Block sBlock = new Block(sRec, Color.BLUE);
        colidabels.addCollidable(sBlock);
        //ball
        Ball ball = new Ball(100, 100, 5, Color.ORANGE, colidabels);
        ball.setVelocity(10.0, -15.0);

        //drawing the balls on the screen and moving them
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (Collidable c : colidabels.getGameList()) {
                ((Block) c).drawOn(d);//can fall on runtime
            }
            ball.drawOn(d);
            ball.moveOneStep();
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
