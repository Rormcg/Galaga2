import java.awt.geom.Point2D;
import java.awt.Graphics;

public class Enemy {

private Point2D.Double pos;
private boolean isDead;
private String type;
private int timer = 0;

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
      if((timer / 40) % 2 == 0) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-bug1", g, 2);
      } else {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-bug2", g, 2);
      }
      
   } else if(type == "enemy-ship") {
      if((timer / 40) % 2 == 0) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-ship1", g, 2);
      } else {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-ship2", g, 2);
      }
   } else {
      if((timer / 40) % 2 == 0) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-boss1", g, 2);
      } else {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-boss2", g, 2);
      }
   }
}

public void update() {
   timer ++;
   if(type == "enemy-bug") {
      
   } else if(type == "enemy-ship") {
   
   } else {
   
   }
}

}