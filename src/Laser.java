/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for laser objects that can be fired by the player and enemies.
*/

import java.awt.Color;
import java.awt.geom.Point2D;
import java.awt.Graphics;

public class Laser {

private Point2D.Double pos; //the position of the laser
private Point2D.Double velocity; //the velocity of the laser(how far the laser travels each frame)
private String type; //either a "player" laser or an "enemy" laser (depending on who fired it)
private boolean isDead; //whether the laser is dead


Laser(double x, double y, String type, double velX, double velY) {
   pos = new Point2D.Double(x, y);
   this.type = type;
   velocity = new Point2D.Double(velX, velY);
   isDead = false;
}

//draws the laser on the Graphics that is sent into the method
public void draw(Graphics g) {
   //as long as the laser is not dead
   if(!isDead) {
      //either draw a player's laser
      if(type == "player") {
         g.setColor(Color.BLUE);
         g.fillRect((int)pos.x - 4, (int)pos.y - 2, 8, 2);
         g.fillRect((int)pos.x - 2, (int)pos.y - 5, 4, 4);
         g.setColor(Color.RED);
         g.fillRect((int)pos.x - 1, (int)pos.y, 2, 8);
      //or an enemy's laser
      } else {
         g.setColor(Color.RED);
         g.fillRect((int)pos.x - 4, (int)pos.y , 8, 2);
         g.fillRect((int)pos.x - 2, (int)pos.y + 1, 4, 4);
         g.setColor(Color.WHITE);
         g.fillRect((int)pos.x - 1, (int)pos.y - 8, 2, 8);
      }
   }
   
}

//updates the laser's position and isDead
public void update() {
   //change position based on velocity
   pos.x += velocity.x;
   pos.y += velocity.y;
   
   //die if past the top or bottom of the screen
   if(pos.y > 660) {
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

public Point2D.Double getVelocity() {
   return velocity;
}

public Point2D.Double getPos() {
   return pos;
}

public void setVelocity(double x, double y) {
   velocity = new Point2D.Double(x, y);
}

public void setIsDead(boolean a) {
   isDead = a;
}

public String toString() {
   return "Pos: " + pos.x + ", " + pos.y +
   "\nVelocity: " + velocity.x + ", " + velocity.y;
}

}