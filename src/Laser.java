import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics;

public class Laser {

private Point2D.Double pos;
private int speed = 10;
private Point2D.Double velocity;
private String type;
private boolean isDead;

Laser(double x, double y, String type, double velX, double velY) {
   pos = new Point2D.Double(x, y);
   this.type = type;
   velocity = new Point2D.Double(velX, velY);
}

public void draw(Graphics g) {
   if(!isDead) {
      if(type == "player") {
         g.setColor(Color.BLUE);
         g.fillRect((int)pos.x - 4, (int)pos.y - 2, 8, 2);
         g.fillRect((int)pos.x - 2, (int)pos.y - 5, 4, 4);
         g.setColor(Color.RED);
         g.fillRect((int)pos.x - 1, (int)pos.y, 2, 8);
      } else {
         g.setColor(Color.RED);
         g.fillRect((int)pos.x - 4, (int)pos.y , 8, 2);
         g.fillRect((int)pos.x - 2, (int)pos.y + 1, 4, 4);
         g.setColor(Color.WHITE);
         g.fillRect((int)pos.x - 1, (int)pos.y - 8, 2, 8);
      }
   }
   
}

public void update() {
   pos.x += velocity.x;
   pos.y += velocity.y;
   
   if(pos.y > 600) {
      isDead = true;
   } else if(pos.y < -10) {
      isDead= true;
   }
}

public boolean getIsDead() {
   return isDead;
}

public String getType() {
   return type;
}

public Point2D.Double getPos() {
   return pos;
}

public void setIsDead(boolean a) {
   isDead = a;
}

}