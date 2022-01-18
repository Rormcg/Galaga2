/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for the ship object that is controlled by the player
*/

import java.awt.geom.Point2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Ship implements KeyListener {

private Point2D.Double pos; //the position of the ship
private int speed; //how fast the ship moves left/right
private boolean isDead; //whether the ship is dead
private boolean leftPressed, rightPressed; //whether the left/right arrow keys are pressed
private Point2D.Double size; //the width/height of the ship
private int xRestriction; //how wide the screen is
private boolean isShooting; //whether the ship is shooting
private int lives; //the number of lives remaining
private int extraLives; //how many extra lives the player has received
private int explosionTimer; //the timer used for reference for which frame of the explosion to show
private int resetTimer; //the timer for how much longer the ship is suspended (when the "ready" or "game over" messages are displayed)

Ship(int x, int y, int xRestriction) {
   pos = new Point2D.Double(x, y);
   this.xRestriction = xRestriction;
   speed = 3;
   isDead = false;
   leftPressed = false;
   rightPressed = false;
   size = new Point2D.Double(45, 28);
   lives = 3;
   extraLives = 0;
   explosionTimer = 18;
   resetTimer = 200;
}

//draw the ship
public void draw(Graphics g) {
   if(!isDead) {
      Utility.drawPixelArt(pos.x, pos.y, "ship", g, 3);
      //g.setColor(Color.YELLOW);
      //g.fillOval((int)pos.x - 5, (int)pos.y - 5, 10, 10);
   } else {
      //if the ship is dead, draw the explosion - the frame# depends on the value of explosionTimer
      if(explosionTimer > 12) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion1", g, 3);
      } else if(explosionTimer > 6) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion2", g, 3);
      } else if(explosionTimer > 0) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion3", g, 3);
      }
   }
}

//update the ship
public void update() {
   if(!isDead) {
      //if the ship isn't dead, move it right or left if the right or left arrow keys are pressed
      if(rightPressed && pos.x < xRestriction - 0.6 * size.x) {
         pos.x += speed;
      } else if(leftPressed && pos.x > 0 + 0.6 * size.x) {
         pos.x -= speed;
      }
   } else if(explosionTimer > 0) {
      //decrement explosionTimer until explosionTimer = 0
      explosionTimer --;
   } else {
      //decrement resetTimer until it reaches 0, then if the ship still has lives, isDead = false again
      resetTimer --;
      if(resetTimer < 0 && lives > 0) {
            isDead = false;
            pos.x = 275;
            pos.y = 550;
      }
   }
}

public boolean getIsShooting() {
   return isShooting;
}

public Point2D.Double getPos() {
   return pos;
}

public Point2D.Double getSize() {
   return size;
}

public int getLives() {
   return lives;
}

public boolean getIsDead() {
   return isDead;
}

public int getResetTimer() {
   return resetTimer;
}

public int getExplosionTimer() {
   return explosionTimer;
}

public int getExtraLives() {
   return extraLives;
}

public void setExtraLives(int a) {
   extraLives = a;
}

public void setIsDead(boolean a) {
   isDead = a;
}

public void setResetTimer(int a) {
   resetTimer = a;
}

public void setExplosionTimer(int a) {
   explosionTimer = a;
}

public void setLives(int a) {
   lives = a;
}

public void setIsShooting(boolean h) {
   isShooting = h;
}

public void setPos(double x, double y) {
   pos = new Point2D.Double(x, y);
}

public String toString() {
   return "Pos: " + pos.x + ", " + pos.y +
   "\nLives " + lives;
}

//check for the keys that are pressed, and change their corresponding variables to represent that
public void keyPressed(KeyEvent e) {
   if(e.getKeyCode() == 32) {
      //space
      isShooting = true;
      //System.out.println("H");
   } else if(e.getKeyCode() == 39) {
      //right arrow key
      rightPressed = true;
   } else if(e.getKeyCode() == 37) {
      //left arrow key
      leftPressed = true;
   } 
}
public void keyReleased(KeyEvent e) {
   if(e.getKeyCode() == 32) {
      //space
      isShooting = false;
   } else if(e.getKeyCode() == 39) {
      //right arrow key
      rightPressed = false;
   } else if(e.getKeyCode() == 37) {
      //left arrow key
      leftPressed = false;
   }
}
public void keyTyped(KeyEvent e) {}

}