/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for the endScreen object that displays the game end screen
*/

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class EndScreen {

   private Point2D.Double pos;//the position on the screen where this is displayed
   private double numHits; //the number of times the player hit an enemy
   private double shotsFired; //the number of times that the player shot a laser
   private int timer; //how long the endScreen will continue to be displayed
   
   private DecimalFormat percent; //a DecimalFormat to format integers
   private DecimalFormat integer; //a DecimalFormat to format percentages with a single digit after the decimal
   
   EndScreen(double x, double y, int hits, int shots) {
      pos = new Point2D.Double(x, y);
      numHits = hits;
      shotsFired = shots;
      timer = 500;
      
      percent = new DecimalFormat("#0.0%");
      integer = new DecimalFormat("#");
   }
   
   //draw the endScreen
   public void draw(Graphics g) {
      timer --;
      
      g.setColor(Color.RED);
      g.setFont(new Font("Sans-serif", Font.BOLD, 34));
      g.drawString("--Results--", (int)pos.x, (int)pos.y);
      g.setColor(Color.YELLOW);
      g.setFont(new Font("Sans-serif", Font.BOLD, 27));
      g.drawString("Shots Fired: " + integer.format(shotsFired), (int)pos.x - 20, (int)pos.y + 40);
      g.drawString("Number of Hits: " + integer.format(numHits), (int)pos.x - 30, (int)pos.y + 80);
      g.setColor(Color.WHITE);
      //to prevent an error, only divide numHits by shotsFired if shotsFired > 0
      if(shotsFired > 0) {
         g.drawString("Hit-to-miss ratio: " + percent.format(numHits / shotsFired), (int)pos.x - 60, (int)pos.y + 120);
      } else {
         g.drawString("Hit-to-miss ratio: 0%", (int)pos.x - 60, (int)pos.y + 120);
      }
   }
   
   public int getTimer() {
      return timer;
   }
   
   public void setTimer(int a) {
      timer = a;
   }
   
   public String toString() {
      return "Timer: " + timer;
   }
}