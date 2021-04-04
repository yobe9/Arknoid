import shapes.Velocity;

public class VelocityTest {
    public static void main(String[] args) {
        Velocity vel = Velocity.fromAngleAndSpeed(0, 2);
        //System.out.println("Dx " + vel.getDx() + " Dy " + vel.getDy());
        Velocity vel1 = Velocity.fromAngleAndSpeed(90, 5);
        //System.out.println("Dx " + vel1.getDx() + " Dy " + vel1.getDy());
        Velocity vel2 = Velocity.fromAngleAndSpeed(180, 3);
        //System.out.println("Dx " + vel2.getDx() + " Dy " + vel2.getDy());
        Velocity vel3 = Velocity.fromAngleAndSpeed(270, 2);
        //System.out.println("Dx " + vel3.getDx() + " Dy " + vel3.getDy());
        Velocity vel4 = Velocity.fromAngleAndSpeed(360, 1);
        //System.out.println("Dx " + vel4.getDx() + " Dy " + vel4.getDy());
        Velocity vel5 = Velocity.fromAngleAndSpeed(120, 1);
        Velocity vel6 = Velocity.fromAngleAndSpeed(480, 1);
        Velocity vel7 = Velocity.fromAngleAndSpeed(60, 1);
        //System.out.println("Dx " + vel5.getDx() + " Dy " + vel5.getDy());
    }
}
