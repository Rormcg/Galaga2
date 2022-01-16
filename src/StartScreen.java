import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;


public class StartScreen implements KeyListener {
   private boolean spacePressed = false;
   
   StartScreen() {}
   
   public void draw(Graphics g) {
      g.setColor(new Color(0, 193, 193));
      g.setFont(new Font("sans-serif", Font.BOLD, 25));
      g.drawString("Push space to start", 110, 300);
      g.setColor(Color.YELLOW);
      g.drawString("1ST BONUS FOR 10000 PTS", 90, 340);
      g.drawString("2ND BONUS FOR 25000 PTS", 90, 380);
      g.drawString("AND FOR EVERY 25000 PTS", 90, 420);
   }
   
   public boolean getSpacePressed() {
      return spacePressed;
   }
   
   public void setSpacePressed(boolean a) {
      spacePressed = a;
   }
   
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == 32) {
         //space
         spacePressed = true;
      }
   }
   public void keyReleased(KeyEvent e) {}
   public void keyTyped(KeyEvent e) {}
}