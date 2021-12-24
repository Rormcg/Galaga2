import java.awt.Point;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.Graphics;

public class Ship implements KeyListener {

private Point pos;
private int speed = 5;
private boolean isDead = false;
private boolean leftPressed = false, rightPressed = false;

Ship() {
   pos = new Point(0, 0);
}
Ship(int x, int y) {
   pos = new Point(x, y);
}

public void draw(Graphics g) {
   //g.setColor(Color.RED);
   //g.fillOval(pos.x, pos.y, 50, 50);
   Utility.drawPixelArt(pos.x, pos.y, "ship", g);
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