
import java.awt.Point;

public class Laser {
private Point pos;
private int speed = 8;
private int direction;
private String type;

Laser(int x, int y, String typ) {
   pos = new Point(x, y);
   type = typ;
   if(type == "enemy") {
      direction = 1;
   } else {
      direction = -1;
   }
}

}