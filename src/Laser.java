
import java.awt.geom.Point2D;

public class Laser {
private Point2D.Double pos;
private int speed = 8;
private Point2D.Double velocity;
private String type;

Laser(int x, int y, String typ) {
   pos = new Point2D.Double(x, y);
   type = typ;
}

}