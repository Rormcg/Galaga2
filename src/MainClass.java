import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JComponent implements ActionListener {

private JFrame frame;
private int screenWidth = 550, screenHeight = 650;
private int timerValue = 0;
private int numHits = 0;
private int shotsFired = 0;
private int score = 0;
private int highScore = 20000;

private String scene = "Start";
private int stage = 1;
private int timeToNextStage = 300;

private Ship ship = new Ship(275, 550, screenWidth);
private StarBackdrop backdrop = new StarBackdrop(screenWidth, screenHeight);
private EndScreen endScreen;
private StartScreen startScreen = new StartScreen();
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
   frame.addKeyListener(startScreen);
}

//draw here
public void paintComponent(Graphics g) {
   backdrop.draw(g);
   
   //draw the score and high score
   g.setFont(new Font("Sans-serif", Font.BOLD, 20));
   if(timerValue % 70 > 35) {
      g.setColor(Color.BLACK);
   } else {
      g.setColor(Color.RED);
   }
   g.drawString("1UP", 35, 25);
   g.setColor(Color.WHITE);
   g.drawString("" + score, 25, 45);
      
   g.drawString("" + highScore, 255, 45);
   g.setColor(Color.RED);
   g.drawString("High Score", 230, 25);
   
   if(scene == "Start") {
      startScreen.draw(g);
   } else if(scene == "Game") {
      
      for(int i = 0; i < lasers.length; i++) {
         lasers[i].draw(g);
      }
      
      if(timeToNextStage < 1) {
         for(int i = 0; i < enemies.length; i ++) {
            enemies[i].draw(g);
         }
      }
      
      ship.draw(g);
      
      //display the ready, stage, and game over messages
      g.setColor(new Color(0, 193, 193));
      g.setFont(new Font("Sans-serif", Font.BOLD, 34));
      if(ship.getIsDead()) {
         if(ship.getLives() > 0 && timeToNextStage < 1) {
            g.drawString("Ready", 220, 350);
         } else if(ship.getLives() < 1) {
            g.drawString("Game Over", 185, 350);
         }
      } 
      
      if(timeToNextStage > 0 && ship.getLives() > 0) {
         g.drawString("Stage " + stage, 210, 350);
      }
      
      //draw the amount of lives remaining
      for(int i = 0; i < ship.getLives() - 1; i ++) {
         Utility.drawPixelArt(20 + i * 40, 620, "ship", g, 2);
      }
   } else if(scene == "End") {
      endScreen.draw(g);
   }
}

//update here
public void actionPerformed(ActionEvent e) {
   timerValue ++;
   
   if(ship.getIsDead()) {
      backdrop.setIsMoving(false);
   } else {
      backdrop.setIsMoving(true);
   }
   backdrop.update();
   if(scene == "Start") {
      if(startScreen.getSpacePressed()) {
         scene = "Game";
         stage = 1;
         timeToNextStage = 300;
         numHits = 0;
         shotsFired = 0;
         score = 0;
         ship.setLives(3);
         ship.setPos(275, 550);
         ship.setIsDead(true);
         ship.setExplosionTimer(0);
         for(int i = 0; i < enemies.length; i ++) {
            enemies[i].setIsDead(false);
            if(enemies[i].getType() == "enemy-boss") {
               enemies[i].setHealth(2);
               enemies[i].setAttackFrequency(40);
            } else {
               enemies[i].setHealth(1);
               enemies[i].setAttackFrequency(10);
            }
            enemies[i].setPos(enemies[i].getStartingPos().x, enemies[i].getStartingPos().y);
            enemies[i].setIsAttacking(false);
            enemies[i].setExplosionTimer(9);
         }
      }
   } else if(scene == "Game") {
      timeToNextStage --;
      if(ship.getLives() <= 0 && ship.getResetTimer() < 200) {
         scene = "End";
         endScreen = new EndScreen(180, 300, numHits, shotsFired);
      }
      
      if(timeToNextStage < 1) {
         ship.update();
      }
      //check if the ship is hit
      for(int j = 0; j < lasers.length; j ++) {
         Laser b = lasers[j];
         Ship a = ship;
         //if a laser hits the ship
         if(b.getType() == "enemy"  && !a.getIsDead() && !b.getIsDead() && b.getPos().x >= a.getPos().x - a.getSize().x * 0.5
         && b.getPos().x <= a.getPos().x + a.getSize().x * 0.5 && b.getPos().y >= a.getPos().y - a.getSize().y * 0.5 
         && b.getPos().y <= a.getPos().y + a.getSize().y * 0.5) {
            b.setIsDead(true);
            a.setIsDead(true);
            a.setLives(a.getLives() - 1);
            a.setExplosionTimer(18);
            a.setResetTimer(400);
         }
      }
      
      //check how many enemies are dead
      int numDead = 0;
      
      //check if the enemies are hit
      boolean addToAttackFrequency = false;
      for(int i = 0; i < enemies.length; i ++) {
         Enemy a = enemies[i];
         if(ship.getIsDead()) {
            a.setCanAttack(false);
         } else {
            a.setCanAttack(true);
         }
         if(timeToNextStage < 1) {
            a.update();
         }
         
         if(a.getIsDead()) {
            numDead ++;
         }
         
         if(a.getIsDead() && a.getAttackFrequency() > 0) {
            addToAttackFrequency = true;
            a.setAttackFrequency(0);
         }
         
         //check if hitting the player
         if(!a.getIsDead() && !ship.getIsDead() && ship.getPos().x + ship.getSize().x * 0.5 >= a.getPos().x - a.getSize().x * 0.5
         && ship.getPos().x - ship.getSize().x * 0.5 <= a.getPos().x + a.getSize().x * 0.5 && ship.getPos().y + ship.getSize().y * 0.5 >= a.getPos().y - a.getSize().y * 0.5 
         && ship.getPos().y - ship.getSize().y * 0.5 <= a.getPos().y + a.getSize().y * 0.5) {
            ship.setIsDead(true);
            a.setHealth(a.getHealth() - 5);
            ship.setLives(ship.getLives() - 1);
            ship.setExplosionTimer(18);
            ship.setResetTimer(500);
         }
         
         for(int j = 0; j < lasers.length; j ++) {
            Laser b = lasers[j];
            if(b.getType() == "player"  && !a.getIsDead() && !b.getIsDead() && b.getPos().x >= a.getPos().x - a.getSize().x * 0.5 
            && b.getPos().x <= a.getPos().x + a.getSize().x * 0.5 && b.getPos().y >= a.getPos().y - a.getSize().y * 0.5
            && b.getPos().y <= a.getPos().y + a.getSize().y * 0.5) {
               b.setIsDead(true);
               a.setHealth(a.getHealth() - 1);
               numHits ++;
               if(a.getType() == "enemy-bug") {
                  score += 100;
               } else if(a.getType() == "enemy-ship") {
                  score += 175;
               } else {
                  score += 200;
               }
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
      //check if all of the enemies are dead; if so, start next stage
      if(numDead >= enemies.length) {
         stage ++;
         timeToNextStage = 300;
         for(int i = 0; i < enemies.length; i ++) {
            enemies[i].setIsDead(false);
            if(enemies[i].getType() == "enemy-boss") {
               enemies[i].setHealth(2);
               enemies[i].setAttackFrequency(40 + stage * 2);
            } else {
               enemies[i].setHealth(1);
               enemies[i].setAttackFrequency(10 + stage * 2);
            }
            enemies[i].setPos(enemies[i].getStartingPos().x, enemies[i].getStartingPos().y);
            enemies[i].setIsAttacking(false);
            enemies[i].setExplosionTimer(9);
         }
      }
      
      //have the ship shoot
      if(ship.getIsShooting() && !ship.getIsDead()) {
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
                  shotsFired ++;
                  break;
               }
            }
         }
      }
      //hae the enemies shoot
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
      
      if(score > highScore) {
         highScore = score;
      }
   } else if(scene == "End") {
      if(endScreen.getTimer() <= 0) {
         scene = "Start";
         startScreen.setSpacePressed(false);
      }
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
   For the referencing Galaga itself:
      https://galaga.cc/
   For how to change the magnitude of a vector:
      https://stackoverflow.com/questions/41317291/setting-the-magnitude-of-a-2d-vector#:~:text=Let%27s%20vector%20components%20are%20vx%2C%20vy.%20It%27s%20current,magnitude%2C%20just%20multiply%20components%20by%20ratio%20of%20magnitudes%3A
   For how to implement key listeners:
      https://www.educba.com/java-keylistener/
   For how to get a Point class that accepts double parameters:
      https://stackoverflow.com/questions/40066333/how-to-store-doubles-in-a-point#:~:text=For%20the%20double%20precision%20you%20need%20to%20use,%3D%20new%20Point2D.Double%20%28x%2C%20y%29%3B%20System.out.println%20%28pointDouble%29%3B%20%7D
   For how to calculate the angle of a vector:
      https://byjus.com/direction-of-a-vector-formula/
   For the strings-to-pixel-art concept:
      this is a little different- I saw this concept about a year ago on the khan academy programming community. When I started this project,
      I decided to use this concept in my project. I would give credit to whomever came up with this idea, but so many people were using this
      that I couldn't pinpoint who first did it. I did not copy this code, but used the concept that I learned here.
*/