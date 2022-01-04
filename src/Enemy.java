import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Enemy {

private Point2D.Double pos;
private Point2D.Double startingPos;
private boolean isDead;
private String type;
private int timer = 0;
private double rotation = 0;
private Point2D.Double velocity = new Point2D.Double(0, 0);
private boolean isAttacking = false;
private boolean isShooting = false;
private Point2D.Double size;
private int health;

Enemy() {
   startingPos = new Point2D.Double(0, 0);
   pos = new Point2D.Double(0, 0);
   isDead = false;
}
Enemy(int x, int y, String type) {
   startingPos = new Point2D.Double(x, y);
   pos = new Point2D.Double(x, y);
   this.type = type;
   if(type == "enemy-bug" || type == "enemy-ship") {
      size = new Point2D.Double(20, 15);
      health = 1;
   } else {
      size = new Point2D.Double(25, 25);
      health = 2;
   }
}

public void draw(Graphics g) {
   if(health > 0) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.translate(pos.x, pos.y);
      if(velocity.x != 0) {
         g2D.rotate(Utility.toRadians(rotation));
      }
      if(type == "enemy-bug") {
         if((timer / 40) % 2 == 0) {
            Utility.drawPixelArt(0, 0, "enemy-bug1", g, 2);
         } else {
            Utility.drawPixelArt(0, 0, "enemy-bug2", g, 2);
         }
         
      } else if(type == "enemy-ship") {
         if((timer / 40) % 2 == 0) {
            Utility.drawPixelArt(0, 0, "enemy-ship1", g, 2);
         } else {
            Utility.drawPixelArt(0, 0, "enemy-ship2", g, 2);
         }
      } else {
         if(health > 1) {
            if((timer / 40) % 2 == 0) {
               Utility.drawPixelArt(0, 0, "enemy-boss1", g, 2);
            } else {
               Utility.drawPixelArt(0, 0, "enemy-boss2", g, 2);
            }
         } else {
            if((timer / 40) % 2 == 0) {
               Utility.drawPixelArt(0, 0, "enemy-boss1", g, 2);
            } else {
               Utility.drawPixelArt(0, 0, "enemy-boss2", g, 2);
            }

         }
      }
      if(velocity.x != 0) {
         g2D.rotate(Utility.toRadians(-rotation));
      }
      g2D.translate(-pos.x, -pos.y);
   }
}

public void update() {
   if(health < 1) {
      isDead = true;
   }
   timer ++;
   if(!isAttacking) {
      pos.x = startingPos.x + Math.sin(timer / 60.0) * 65;
   } else {
      if(type == "enemy-bug") {
         
      } else if(type == "enemy-ship") {
      
      } else {
      
      }
   }
   
   pos.x += velocity.x;
   pos.y += velocity.y;
   rotation =  Utility.vectorDirection(velocity.x, velocity.y);
}

public Point2D.Double getPos() {
   return pos;
}

public Point2D.Double getSize() {
   return size;
}

public boolean getIsDead() {
   return isDead;
}

public int getHealth() {
   return health;
}

public void setHealth(int a) {
   health = a;
}

public void setIsDead(boolean a) {
   isDead = a;
}

}