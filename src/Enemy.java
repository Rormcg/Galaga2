/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for the enemy objects.
*/

import java.awt.geom.Point2D;
import java.awt.Graphics2D;
import java.awt.Graphics;

public class Enemy {

private Point2D.Double pos; //the position of the enemy
private Point2D.Double startingPos; //the original position of the enemy (used for a point of reference)
private boolean isDead; //whether the enemy is dead
private String type; //either the "bug" enemies or the "ship" enemies or the "boss" enemies
private int timer; //gradually increments over time - used as reference for when to change the image to frame 1/frame 2
private int screenWidth; //the width of the screen
private int screenHeight; //the height of the screen
private double rotation; //the direction the enemy is rotated (in degrees)
private Point2D.Double velocity; //the velocity of the enemy (used when the enemy is attacking only)
private boolean isAttacking; //whether the enemy is attacking (or hovering back and forth)
private boolean isShooting; //whether the enemy is shooting a laser
private Point2D.Double size; //the width/height of the enemy
private int health; //how many hits the enemy has left before it dies
private double speed; //how fast the enemy moves when attacking (the magnitude of velocity)
private int attackFrequency; //as this number gets higher, the higher the chances are of an enemy deciding start attacking
private int explosionTimer; //used as reference for which explosion frame(1, 2, or 3) to use when the enemy is exploding
private boolean canAttack; //whether the enemy can begin an attack

Enemy(int x, int y, String type) {
   startingPos = new Point2D.Double(x, y);
   pos = new Point2D.Double(x, y);
   this.type = type;
   timer = 0;
   screenWidth = 550;
   screenHeight = 650;
   rotation = 0;
   velocity = new Point2D.Double(0, 0);
   isAttacking = false;
   isShooting = false;
   speed = 2.7;
   attackFrequency = 10;
   explosionTimer = 9;
   canAttack = true;
   //set the enemy's health to 1 if it is a bug or ship, otherwise set it to 2 if it is a boss
   if(type == "enemy-bug" || type == "enemy-ship") {
      size = new Point2D.Double(30, 30);
      health = 1;
   } else {
      size = new Point2D.Double(35, 35);
      health = 2;
      attackFrequency += 30;
   }
}

//draw the enemy
public void draw(Graphics g) {
   //if the enemy still has some health:
   if(health > 0) {
      Graphics2D g2D = (Graphics2D) g;
      g2D.translate(pos.x, pos.y);
      //rotate the enemy as long as velocity.x != 0 (this prevents an error message)
      if(velocity.x != 0) {
         g2D.rotate(Utility.toRadians(rotation));
      }
      //draw the enemy based on its type, and draw frame 1 or 2 depending on the current value of timer
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
         //for the bosses, draw the green version if they are at full health, otherwise drae the blue version
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
   //if the enemy's health is <= 0, draw an explosion as long as explosionTimer is greater than 0
   } else if(explosionTimer > 0) {
      //based on the value explosionTimer, draw a certain frame of the explosion
      if(explosionTimer > 6) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion1", g, 2);
      } else if(explosionTimer > 3) {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion2", g, 2);
      } else {
         Utility.drawPixelArt(pos.x, pos.y, "enemy-explosion3", g, 2);
      }
   }
}

//update the enemy
public void update() {
   timer ++; //increment timer
   
   //as long as the enemy is not dead
   if(!isDead) {
      //die if health is less than 1
      if(health < 1) {
         isDead = true;
      }
      
      //as long as the enemy can attack currently, there is a random chance (that is greater if attackFrequency is greater) to start attacking
      if(canAttack) {
         if(Utility.random(1, 100000 / attackFrequency) == 5 && !isAttacking) {
            isAttacking = true;
            //get a random y velocity
            velocity.y = Utility.random(1.0, 1.9);
            //and get an x velocity that makes sure that the magnitude of velocity is equal to speed
            velocity.x = Utility.randomExcludeZero(-1, 1) * Math.sqrt(Math.pow(speed, 2) - Math.pow(velocity.y, 2));
         }
      }
      
      //once the enemy goes past the bottom of the screen, start hovering back and forth again
      if(pos.y > screenHeight + 40) {
         isAttacking = false;
      }
      
      if(!isAttacking) {
         //when not attacking, move back and forth
         velocity.x = 0;
         velocity.y = 0;
         //use startingPos as a reference point and add/subtract to that using sin
         pos.x = startingPos.x + Math.sin(timer / 60.0) * 55;
         pos.y = startingPos.y;
      //if the enemy is attacking:
      } else {
         //shoot once the enemy reaches a certain y-coordinate
         if(pos.y <= 250 && pos.y > 250 - velocity.y) {
            isShooting = true;
         }
         if(type == "enemy-bug") {
            
         } else if(type == "enemy-ship") {
            //the enemy ships and bosses shoot once more 
            if(pos.y <= 300 && pos.y > 300 - velocity.y) {
               isShooting = true;
            }
         } else {
            if(pos.y <= 300 && pos.y > 300 - velocity.y) {
               isShooting = true;
            }
         }
         
         /*reverse velocity.x if the enemy
            -hits the right/left edge of the screen
            -hits the middle of the screen above y-(0.6 * screenHeight)*/
         if(pos.x + 0.5 * size.x >= screenWidth || pos.x - 0.5 * size.x <= 0 || 
           (pos.x + 0.5 * size.x >= screenWidth * 0.5 && pos.x > 0 && pos.x - screenWidth * 0.5 < 0 && pos.y < 0.6 * screenHeight) || 
           (pos.x - 0.5 * size.x <= screenWidth * 0.5 && pos.x < 0 && pos.x - screenWidth * 0.5 > 0 && pos.y < 0.6 * screenHeight)) {
            velocity.x = -velocity.x;
         }
         
         //add velocity to the enemy's position
         pos.x += velocity.x;
         pos.y += velocity.y;
         //make the enemy's rotation equal the direction that velocity is pointing toward
         rotation =  Utility.vectorDirection(velocity.x, velocity.y);
      }
   } else {
      //if the enemy's is dead, subtract one from explosion timer
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

public Point2D.Double getStartingPos() {
   return startingPos;
}

public void setAttackFrequency(int a) {
   attackFrequency = a;
}

public void setHealth(int a) {
   health = a;
}

public void setIsAttacking(boolean a) {
   isAttacking = a;
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


public void setPos(double x, double y) {
   pos = new Point2D.Double(x, y);
}

public void setExplosionTimer(int a) {
   explosionTimer = a;
}

public String toString() {
   return "Pos: " + pos.x + ", " + pos.y +
   "\nVelocity: " + velocity.x + ", " + velocity.y +
   "\nDead: " + isDead;
}

}