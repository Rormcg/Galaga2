import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MainClass extends JComponent implements ActionListener {

private JFrame frame;
private int screenWidth = 550, screenHeight = 650;

private Ship ship = new Ship(275, 550, screenWidth);
private StarBackdrop backdrop = new StarBackdrop(screenWidth, screenHeight);
private Enemy[] enemies = new Enemy[40];
private Laser[] lasers = new Laser[100];

MainClass() {
   frame = new JFrame("Galaga");
   Container content = frame.getContentPane();
   content.setBackground(Color.BLACK);
   content.add(this);
   setup();
   
   Timer t = new Timer(1, this);
   t.start();
}

public void setup() {
   for(int i = 0; i < 4; i ++) {
      enemies[i] = new Enemy(210 + 40 * i, 90, "enemy-boss");
   }
   for(int i = 4; i < 12; i ++) {
      enemies[i] = new Enemy(115 + 45 * (i - 4), 130, "enemy-ship");
   }
   for(int i = 12; i < 20; i ++) {
      enemies[i] = new Enemy(115 + 45 * (i - 12), 160, "enemy-ship");
   }
   for(int i = 20; i < 30; i ++) {
      enemies[i] = new Enemy(70 + 45 * (i - 20), 190, "enemy-bug");
   }
   for(int i = 30; i < 40; i ++) {
      enemies[i] = new Enemy(70 + 45 * (i - 30), 220, "enemy-bug");
   }
   
   for(int i = 0; i < lasers.length; i ++) {
      //fill the lasers array with placeholder objects
      lasers[i] = new Laser(-400, -400, "null", 0, 0);
   }
   
   frame.setSize(screenWidth+17, screenHeight+40);
   frame.setLocationRelativeTo(null);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
   frame.addKeyListener(ship);
}

//draw here
public void paintComponent(Graphics g) {
   backdrop.draw(g);
   
   for(int i = 0; i < lasers.length; i++) {
      lasers[i].draw(g);
   }
   
   ship.draw(g);
   for(int i = 0; i < enemies.length; i ++) {
      enemies[i].draw(g);
   }
}

//update here
public void actionPerformed(ActionEvent e) {
   ship.update();
   if(ship.getIsMoving()) {
      backdrop.update();
   }
   for(int i = 0; i < enemies.length; i ++) {
      enemies[i].update();
   }
   
   if(ship.getIsShooting()) {
      int numPlayerBullets = 0;
      for(int i = 0; i < lasers.length; i ++) {
         if(lasers[i].getType() == "player" && lasers[i].getIsDead() != true) {
            numPlayerBullets ++;
         }
      }
      if(numPlayerBullets < 3) {
         for(int i = 0; i < lasers.length; i ++) {
            if(lasers[i].getIsDead() || lasers[i].getType() == "null") {
               lasers[i] = new Laser(ship.getPos().x, ship.getPos().y - 24, "player", 0, -6);
               ship.setIsShooting(false);
               break;
            }
         }
      }
   }
   
   for(int i = 0; i < lasers.length; i++) {
      lasers[i].update();
   }
   
   repaint();
}

}

class RunMain {
   public static void main(String[] args) {
      MainClass mainClass = new MainClass();
   }
}