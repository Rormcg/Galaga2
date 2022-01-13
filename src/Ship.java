import java.awt.geom.Point2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;

public class Ship implements KeyListener {

private Point2D.Double pos;
private int speed = 3;
private boolean isDead = false;
private boolean leftPressed = false, rightPressed = false;
private Point2D.Double size = new Point2D.Double(45, 28);
private int xRestriction;
private boolean isShooting;
private int lives = 3;
private int explosionTimer = 18;
private int resetTimer = 400;

Ship(int x, int y, int xRestriction) {
   pos = new Point2D.Double(x, y);
   this.xRestriction = xRestriction;
}

public void draw(Graphics g) {
   if(!isDead) {
      Utility.drawPixelArt(pos.x, pos.y, "ship", g, 3);
      //g.setColor(Color.YELLOW);
      //g.fillOval((int)pos.x - 5, (int)pos.y - 5, 10, 10);
   } else {
      if(explosionTimer > 12) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion1", g, 3);
      } else if(explosionTimer > 6) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion2", g, 3);
      } else if(explosionTimer > 0) {
         Utility.drawPixelArt(pos.x, pos.y, "player-explosion3", g, 3);
      }
   }
}

public void update() {
   if(!isDead) {
      if(rightPressed && pos.x < xRestriction - 0.6 * size.x) {
         pos.x += speed;
      } else if(leftPressed && pos.x > 0 + 0.6 * size.x) {
         pos.x -= speed;
      }
   } else if(explosionTimer > 0) {
      explosionTimer --;
   } else {
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