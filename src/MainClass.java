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
   
   for(int i = 0; i < enemies.length; i ++) {
      enemies[i].draw(g);
   }
   
   ship.draw(g);
}

//update here
public void actionPerformed(ActionEvent e) {
   ship.update();
   if(ship.getIsMoving()) {
      backdrop.update();
   }
   boolean addToAttackFrequency = false;
   for(int i = 0; i < enemies.length; i ++) {
      Enemy a = enemies[i];
      a.update();
      
      if(a.getIsDead() && a.getAttackFrequency() > 0) {
         addToAttackFrequency = true;
         a.setAttackFrequency(0);
      }
      for(int j = 0; j < lasers.length; j ++) {
         Laser b = lasers[j];
         if(b.getType() == "player"  && !a.getIsDead() && !b.getIsDead() && b.getPos().x >= a.getPos().x - a.getSize().x && b.getPos().x <= a.getPos().x + a.getSize().x &&
         b.getPos().y >= a.getPos().y - a.getSize().y && b.getPos().y <= a.getPos().y + a.getSize().y) {
            b.setIsDead(true);
            a.setHealth(a.getHealth() - 1);
         }
      }
   }
   if(addToAttackFrequency) {
      for(int i = 0; i < enemies.length; i ++) {
         if(!enemies[i].getIsDead()) {
            enemies[i].setAttackFrequency(enemies[i].getAttackFrequency() + 1);
         }
      }
   }
   
   if(ship.getIsShooting()) {
      int numPlayerBullets = 0;
      for(int i = 0; i < lasers.length; i ++) {
         if(lasers[i].getType() == "player" && lasers[i].getIsDead() != true) {
            numPlayerBullets ++;
         }
      }
      if(numPlayerBullets < 2) {
         for(int i = 0; i < lasers.length; i ++) {
            if(lasers[i].getIsDead() || lasers[i].getType() == "null") {
               lasers[i] = new Laser(ship.getPos().x, ship.getPos().y - 24, "player", 0, -8);
               ship.setIsShooting(false);
               break;
            }
         }
      }
   }
   for(int i = 0; i < enemies.length; i ++) {
      if(enemies[i].getIsShooting()) {
         for(int j = 0; j < lasers.length; j ++) {
            if(lasers[j].getIsDead() || lasers[j].getType() == "null") {
               //create a new laser with a velocity pointing at the player
               lasers[j] = new Laser(enemies[i].getPos().x, enemies[i].getPos().y, 
               "enemy", ship.getPos().x - enemies[i].getPos().x, ship.getPos().y - enemies[i].getPos().y);
               
               //scale the new laser's velocity down to a magnitude of 5
               lasers[j].setVelocity(
               lasers[j].getVelocity().x * (5 / Math.sqrt(Math.pow(lasers[j].getVelocity().x, 2) + Math.pow(lasers[j].getVelocity().y, 2))), 
               lasers[j].getVelocity().y * (5 / Math.sqrt(Math.pow(lasers[j].getVelocity().x, 2) + Math.pow(lasers[j].getVelocity().y, 2))));
               
               //System.out.println(lasers[i].getVelocity.x);
               //System.out.println(Utility.vectorDirection(lasers[j].getVelocity().x, lasers[j].getVelocity().y));
               enemies[i].setIsShooting(false);
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


/*
Websites Used:
   For how to change the magnitude of a vector:
   https://stackoverflow.com/questions/41317291/setting-the-magnitude-of-a-2d-vector#:~:text=Let%27s%20vector%20components%20are%20vx%2C%20vy.%20It%27s%20current,magnitude%2C%20just%20multiply%20components%20by%20ratio%20of%20magnitudes%3A
   
   
*/