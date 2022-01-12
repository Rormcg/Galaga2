import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Enemy {

private Point2D.Double pos;
private Point2D.Double startingPos;
private boolean isDead;
private String type;
private int timer = 0;
private int screenWidth = 550;
private int screenHeight = 650;
private double rotation = 0;
private Point2D.Double velocity = new Point2D.Double(0, 0);
private boolean isAttacking = false;
private boolean isShooting = false;
private Point2D.Double size;
private int health;
private double speed = 2.5;
private int attackFrequency = 10;
private int explosionTimer = 9;
private boolean canAttack = true;

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
      size = new Point2D.Double(28, 22);
      health = 1;
   } else {
      size = new Point2D.Double(32, 32);
      health = 2;
      attackFrequency += 30;
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
               //the green version (full health)
               Utility.drawPixelArt(0, 0, "enemy-boss1A", g, 2);
            } else {
               Utility.drawPixelArt(0, 0, "enemy-boss2A", g, 2);
            }
         } else {
            if((timer / 40) % 2 == 0) {
               //the blue version (hit once)
               Utility.drawPixelArt(0, 0, "enemy-boss1B", g, 2);
            } else {
               Utility.drawPixelArt(0, 0, "enemy-boss2B", g, 2);
            }

         }
      }
      if(velocity.x != 0) {
         g2D.rotate(Utility.toRadians(-rotation));
      }
      g2D.translate(-pos.x, -pos.y);
   } else if(explosionTimer > 0) {
      if(explosionTimer > 6) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion1", g, 2);
      } else if(explosionTimer > 6) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion2", g, 2);
      } else {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion3", g, 2);
      }
   }
}

public void update() {
   timer ++;
   
   if(!isDead) {
      if(health < 1) {
         isDead = true;
      }
      
      if(canAttack) {
         if(Utility.random(1, 100000 / attackFrequency) == 5 && !isAttacking) {
            isAttacking = true;
            velocity.y = Utility.random(0.7, 1.8);
            velocity.x = Utility.randomExcludeZero(-1, 1) * Math.sqrt(Math.pow(speed, 2) - Math.pow(velocity.y, 2));
         }
      }
      
      if(pos.y > screenHeight + 40) {
         isAttacking = false;
      }
      
      if(!isAttacking) {
         //when not attacking, move back and forth
         velocity.x = 0;
         velocity.y = 0;
         pos.x = startingPos.x + Math.sin(timer / 60.0) * 55;
         pos.y = startingPos.y;
      } else {
         //shoot once the enemy reaches a certain y-coordinate
         if(pos.y <= 250 && pos.y > 250 - velocity.y) {
            isShooting = true;
         }
         if(type == "enemy-bug") {
            
         } else if(type == "enemy-ship") {
            //the enemy ships and bosses shoot more 
            if(pos.y <= 300 && pos.y > 300 - velocity.y) {
               isShooting = true;
            }
         } else {
            if(pos.y <= 300 && pos.y > 300 - velocity.y) {
               isShooting = true;
            }
         }
         
         
         if(pos.x + 0.5 * size.x >= screenWidth || pos.x - 0.5 * size.x <= 0 || 
           (pos.x + 0.5 * size.x >= screenWidth * 0.5 && pos.x > 0 && pos.x - screenWidth * 0.5 < 0 && pos.y < 0.6 * screenHeight) || 
           (pos.x - 0.5 * size.x <= screenWidth * 0.5 && pos.x < 0 && pos.x - screenWidth * 0.5 > 0 && pos.y < 0.6 * screenHeight)) {
            velocity.x = -velocity.x;
         }
         
         pos.x += velocity.x;
         pos.y += velocity.y;
         rotation =  Utility.vectorDirection(velocity.x, velocity.y);
      }
   } else {
      explosionTimer --;
   }
   
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

public boolean getIsShooting() {
   return isShooting;
}

public int getAttackFrequency() {
   return attackFrequency;
}

public String getType() {
   return type;
}

public void setAttackFrequency(int a) {
   attackFrequency = a;
}

public void setHealth(int a) {
   health = a;
}

public void setIsShooting(boolean a) {
   isShooting = a;
}

public void setIsDead(boolean a) {
   isDead = a;
}

public void setCanAttack(boolean a) {
   canAttack = a;
}


}