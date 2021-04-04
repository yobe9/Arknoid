public class RectangleTester {
    public static void main(String[] args) {
        Point upperLeft = new Point(10.0, 10.0);
        Rectangle rec = new Rectangle(upperLeft, 10, 10);
        Line[] lineArrayT = new Line[5];
        //exactly in corner
        Line l1 = new Line(9.0, 9.0, 10, 10);
        lineArrayT[0] = l1;
        //on one line and intersect with another
        Line l2 = new Line(28.0, 28.0, 12.0, 8.0);
        lineArrayT[1] = l2;
        // top to bottom
        Line l3 = new Line(14.0, 28.0, 12.0, 8.0);
        lineArrayT[2] = l3;
        //not touching
        Line l4 = new Line(4.0, 4.0, 4.0, 4.0);
        lineArrayT[3] = l4;
        //exactly in 2 corners
        Line l5 = new Line(9.0, 9.0, 21.0, 21.0);
        lineArrayT[4] = l5;
        for (int i = 0; i <= 4; i++) {
            Point interPoint = lineArrayT[i].closestIntersectionToStartOfLine(rec);
            if (interPoint != null) {
                double x = interPoint.getX();
                double y = interPoint.getY();
                System.out.println("intersection point x is" + x + " intersection y is " + y);
                System.out.println("iteration number " + i);
            } else {
                System.out.println("null intersect");
            }
        }
    }
}
