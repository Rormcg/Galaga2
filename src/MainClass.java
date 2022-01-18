/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as the place where all the code is run.
*/

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

private JFrame frame; //the frame on which everything happens
private int screenWidth, screenHeight; //the width/height of that frame
private int timerValue; //a timer value that is used for reference
private int numHits; //the number of times the player has hit an enemy
private int shotsFired; //the number of lasers that the player fired
private int score; //the player's score
private int highScore; //the high score

private String scene; //the current scene of the program (is either "Start", "Game", or "End")
private int stage; //the stage(level) that the player is currently on
private int timeToNextStage; //the time left for displaying "Stage " + stage on the screen

//objects
private Ship ship; //the player/ship
private StarBackdrop backdrop; //the moving star background
private EndScreen endScreen; //the screen that displays at the end of the game
private StartScreen startScreen; //the screen that displays at the start of the game
private Enemy[] enemies; //the array of enemies
private Laser[] lasers; //the array of lasers

MainClass() {
   screenWidth = 550;
   screenHeight = 650;
   timerValue = 0;
   numHits = 0;
   shotsFired = 0;
   score = 0;
   highScore = 10000;
   scene = "Start";
   stage = 1;
   timeToNextStage = 300;
   
   ship = new Ship(275, 550, screenWidth);
   backdrop = new StarBackdrop(screenWidth, screenHeight);
   startScreen = new StartScreen();
   enemies = new Enemy[40];
   lasers = new Laser[100];
   
   frame = new JFrame("Galaga");
   Container content = frame.getContentPane();
   content.setBackground(Color.BLACK);
   content.add(this);
   setup();
   
   Timer t = new Timer(1, this);
   t.start();
}

//set up the things that weren't in the constructor
public void setup() {
   //set up the array of enemies
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
   
   //set up the array of lasers
   for(int i = 0; i < lasers.length; i ++) {
      //fill the lasers array with placeholder objects
      lasers[i] = new Laser(-400, -400, "null", 0, 0);
   }
   
   //set up the frame
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
   
   //draw different things based on the scene
   if(scene == "Start") {
      startScreen.draw(g);
   } else if(scene == "Game") {
      //draw the lasers
      for(int i = 0; i < lasers.length; i++) {
         lasers[i].draw(g);
      }
      
      //draw the enemies as long as the stage is not still transitioning
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
      
      //display the stage # as long as timeToNextStage > 0 and the ship isn't dead
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
   
   //make the backdrop of stars move only if the player is not dead
   if(ship.getIsDead()) {
      backdrop.setIsMoving(false);
   } else {
      backdrop.setIsMoving(true);
   }
   backdrop.update();
   
   //update different things based on the scene
   if(scene == "Start") {
      //change scenes to the game if the player pressed space
      if(startScreen.getSpacePressed()) {
         //set up all necessary variables
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
         ship.setExtraLives(0);
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
      
      //change the scene to "End" if the player is out of lives and the "Game Over" message is finished displaying
      if(ship.getLives() <= 0 && ship.getResetTimer() < 200) {
         scene = "End";
         endScreen = new EndScreen(180, 300, numHits, shotsFired);
      }
      
      //give the player an extra life every time they reach a score of 10000 or if they reach a multiple of 25000
      if((score > 10000 && ship.getExtraLives() < 1) || (ship.getExtraLives() > 0 && score / 25000 > ship.getExtraLives() - 1)) {
         ship.setLives(ship.getLives() + 1);
         ship.setExtraLives(ship.getExtraLives() + 1);
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
            //make the laser and ship die
            b.setIsDead(true);
            a.setIsDead(true);
            //subtract a life
            a.setLives(a.getLives() - 1);
            //start the ship's explosionTimer and reset timer
            a.setExplosionTimer(18);
            a.setResetTimer(400);
         }
      }
      
      int numDead = 0; //used to check how many enemies are dead
      
      //check if the enemies are hit
      boolean addToAttackFrequency = false;
      for(int i = 0; i < enemies.length; i ++) {
         Enemy a = enemies[i];
         
         //the enemy can't start attacking if the player is dead
         if(ship.getIsDead()) {
            a.setCanAttack(false);
         } else {
            a.setCanAttack(true);
         }
         
         if(timeToNextStage < 1) {
            a.update();
         }
         
         //if this enemy is dead, add to numDead
         if(a.getIsDead()) {
            numDead ++;
         }
         
         //every time an enemy dies, addToAttackFrequency = true (all other enemies will add 1 to their attackFrequency)
         if(a.getIsDead() && a.getAttackFrequency() > 0) {
            addToAttackFrequency = true;
            a.setAttackFrequency(0);
         }
         
         //check if hitting the player
         if(!a.getIsDead() && !ship.getIsDead() && ship.getPos().x + ship.getSize().x * 0.5 >= a.getPos().x - a.getSize().x * 0.5
         && ship.getPos().x - ship.getSize().x * 0.5 <= a.getPos().x + a.getSize().x * 0.5 && ship.getPos().y + ship.getSize().y * 0.5 >= a.getPos().y - a.getSize().y * 0.5 
         && ship.getPos().y - ship.getSize().y * 0.5 <= a.getPos().y + a.getSize().y * 0.5) {
            //make the ship and the enemy die
            ship.setIsDead(true);
            a.setHealth(a.getHealth() - 2);
            //subtract one from lives
            ship.setLives(ship.getLives() - 1);
            //start the ship's explosionTimer and resetTimer
            ship.setExplosionTimer(18);
            ship.setResetTimer(400);
         }
         
         //check if any enemy was hit by the player's lasers
         for(int j = 0; j < lasers.length; j ++) {
            Laser b = lasers[j];
            if(b.getType() == "player"  && !a.getIsDead() && !b.getIsDead() && b.getPos().x >= a.getPos().x - a.getSize().x * 0.5 
            && b.getPos().x <= a.getPos().x + a.getSize().x * 0.5 && b.getPos().y >= a.getPos().y - a.getSize().y * 0.5
            && b.getPos().y <= a.getPos().y + a.getSize().y * 0.5) {
               b.setIsDead(true);
               a.setHealth(a.getHealth() - 1);
               numHits ++;
               //add to the score depending on which enemy type it was
               if(a.getType() == "enemy-bug") {
                  score += 75;
               } else if(a.getType() == "enemy-ship") {
                  score += 125;
               } else {
                  score += 175;
               }
            }
         }
      }
      //if an enemy just died, add 1 to each enemy's attackFrequency
      if(addToAttackFrequency) {
         for(int i = 0; i < enemies.length; i ++) {
            if(!enemies[i].getIsDead()) {
               enemies[i].setAttackFrequency(enemies[i].getAttackFrequency() + 1);
            }
         }
      }
      //check if all of the enemies are dead; if so, start next stage
      if(numDead >= enemies.length) {
         //reset all of the necessary variables
         stage ++;
         timeToNextStage = 300;
         ship.setIsDead(true);
         ship.setResetTimer(200);
         for(int i = 0; i < enemies.length; i ++) {
            enemies[i].setIsDead(false);
            if(enemies[i].getType() == "enemy-boss") {
               enemies[i].setHealth(2);
               enemies[i].setAttackFrequency(40 + (stage - 1) * 3);
            } else {
               enemies[i].setHealth(1);
               enemies[i].setAttackFrequency(10 + (stage - 1) * 3);
            }
            enemies[i].setPos(enemies[i].getStartingPos().x, enemies[i].getStartingPos().y);
            enemies[i].setIsAttacking(false);
            enemies[i].setExplosionTimer(9);
         }
         for(int i = 0; i < lasers.length; i ++) {
            lasers[i].setIsDead(true);
         }
      }
      
      //have the ship shoot
      if(ship.getIsShooting() && !ship.getIsDead()) {
         
         int numPlayerBullets = 0; //however many bullets the player has shot
         for(int i = 0; i < lasers.length; i ++) {
            if(lasers[i].getType() == "player" && lasers[i].getIsDead() != true) {
               //add one to the # of player's bullets if this laser's type = "player" and this laser isn't dead
               numPlayerBullets ++;
            }
         }
         //as long as the player does not already have two lasers, shoot a laser
         if(numPlayerBullets < 2) {
            for(int i = 0; i < lasers.length; i ++) {
               //go through the lasers and replace the first one that is a dead laser or a placeholder laser with the new laser
               if(lasers[i].getIsDead() || lasers[i].getType() == "null") {
                  lasers[i] = new Laser(ship.getPos().x, ship.getPos().y - 24, "player", 0, -8);
                  ship.setIsShooting(false);
                  shotsFired ++;
                  break;
               }
            }
         }
      }
      //have the enemies shoot
      for(int i = 0; i < enemies.length; i ++) {
         if(enemies[i].getIsShooting()) {
            for(int j = 0; j < lasers.length; j ++) {
               //go through the lasers and replace the first one that is a dead laser or a placeholder laser with the new laser
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
      
      //update the high score if score is greater than the high score
      if(score > highScore) {
         highScore = score;
      }
   } else if(scene == "End") {
      //go back to the starting screen once the endScreen's timer is up
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