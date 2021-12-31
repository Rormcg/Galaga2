import java.awt.geom.Point2D;
import java.awt.Graphics;

public class Enemy {

private Point2D.Double pos;
private boolean isDead;
private String type;

Enemy() {
   pos = new Point2D.Double(0, 0);
   isDead = false;
}
Enemy(int x, int y, String type) {
   pos = new Point2D.Double(x, y);
   this.type = type;
}

public void draw(Graphics g) {
   if(type == "enemy-bug") {
      Utility.drawPixelArt(pos.x, pos.y, "enemy-bug", g, 3);
   } else if(type == "enemy-ship") {
      Utility.drawPixelArt(pos.x, pos.y, "enemy-ship", g, 3);
   } else {
      Utility.drawPixelArt(pos.x, pos.y, "enemy-boss", g, 3);
   }
}

public void update() {
   if(type == "enemy-bug") {
      
   } else if(type == "enemy-ship") {
   
   } else {
   
   }
}

}