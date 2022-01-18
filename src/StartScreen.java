/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for startingScreen object that displays the starting screen
*/


import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Color;


public class StartScreen implements KeyListener {
   private boolean spacePressed; //whether space has been pressed yet(once this is true, the scene will move on)
   
   StartScreen() {
      spacePressed = false;
   }
   
   //draw the starting screen
   public void draw(Graphics g) {
      g.setColor(new Color(0, 193, 193));
      g.setFont(new Font("sans-serif", Font.BOLD, 30));
      g.drawString("Push space to start", 135, 270);
      g.setColor(Color.YELLOW);
      g.drawString("1st bonus for 10000 pts", 140, 330);
      g.drawString("2nd bonus for 25000 pts", 140, 380);
      g.drawString("And for every 25000 pts", 140, 430);
      for(int i = 0; i < 3; i ++) {
         Utility.drawPixelArt(100, 320 + (i * 50), "ship", g, 2);
      }
   }
   
   public boolean getSpacePressed() {
      return spacePressed;
   }
   
   public void setSpacePressed(boolean a) {
      spacePressed = a;
   }
   
   public String toString() {
      return "Space is Pressed: " + spacePressed;
   }
   
   //if space is pressed, spacePressed = true
   public void keyPressed(KeyEvent e) {
      if(e.getKeyCode() == 32) {
         //space
         spacePressed = true;
      }
   }
   public void keyReleased(KeyEvent e) {}
   public void keyTyped(KeyEvent e) {}
}