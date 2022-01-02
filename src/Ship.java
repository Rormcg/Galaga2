import java.awt.geom.Point2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Ship implements KeyListener {

private Point2D.Double pos;
private int speed = 3;
private boolean isMoving = true;
private boolean isDead = false;
private boolean leftPressed = false, rightPressed = false;
private Point2D.Double size = new Point2D.Double(45, 24);
private int xRestriction;
private boolean isShooting;

Ship(int x, int y, int xRestriction) {
   pos = new Point2D.Double(x, y);
   this.xRestriction = xRestriction;
}

public boolean getIsMoving() {
   return isMoving;
}

public void draw(Graphics g) {
   Utility.drawPixelArt(pos.x, pos.y, "ship", g, 3);
   //g.setColor(Color.YELLOW);
   //g.fillOval((int)pos.x - 5, (int)pos.y - 5, 10, 10);
}

public void update() {
   if(rightPressed && pos.x < xRestriction - 0.6 * size.x) {
      pos.x += speed;
   } else if(leftPressed && pos.x > 0 + 0.6 * size.x) {
      pos.x -= speed;
   }
}

public boolean getIsShooting() {
   return isShooting;
}

public Point2D.Double getPos() {
   return pos;
}

public void setIsShooting(boolean h) {
   isShooting = h;
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