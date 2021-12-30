import java.awt.geom.Point2D;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Ship implements KeyListener {

private Point2D.Double pos;
private int speed = 5;
private boolean isDead = false;
private boolean leftPressed = false, rightPressed = false;
private Point2D.Double size = new Point2D.Double(60, 32);

Ship() {
   pos = new Point2D.Double(0, 0);
}
Ship(int x, int y) {
   pos = new Point2D.Double(x, y);
}

public void draw(Graphics g) {
   //g.setColor(Color.RED);
   //g.fillOval(pos.x, pos.y, 50, 50);
   Utility.drawPixelArt(pos.x, pos.y, "ship", g, 4);
}

public void update() {
   if(rightPressed) {
      pos.x += speed;
   } else if(leftPressed) {
      pos.x -= speed;
   }
}

public void keyPressed(KeyEvent e) {
   if(e.getKeyCode() == 32) {
      //space
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