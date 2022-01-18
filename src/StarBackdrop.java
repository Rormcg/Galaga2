/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class is used as a model for the starBackdrop object that displays the stars in the background of the program
*/

import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class StarBackdrop {
   
   private int numStars; //the number of total stars that are displayed
   private Point2D.Double[] positions; //the positions of all of the stars
   private boolean[] isOn; //whether each of the stars is blinking
   private double speed; //how fast the stars scroll
   private Color[] colors; //the colors of each of the stars
   private Dimension size; //width/height of the screen
   private boolean isMoving; //whether the stars are scrolling
   
   StarBackdrop(int width, int height) {
      numStars = 250;
      positions = new Point2D.Double[numStars];
      isOn = new boolean[numStars];
      speed = 2;
      colors = new Color[numStars];
      isMoving = true;
      size = new Dimension(width, height);
      
      //iterate through each of the arrays and fill them with random values
      for(int i = 0; i < numStars; i ++) {
         positions[i] = new Point2D.Double(Utility.random(1, size.width), Utility.random(-50, size.height));
         colors[i] = new Color(Utility.random(0, 255), Utility.random(0, 255), Utility.random(0, 255));
         if(Utility.random(0, 1) == 1) {
            isOn[i] = true;
         } else {
            isOn[i] = false;
         }
      }
   }
   
   //draw the backdrop
   public void draw(Graphics g) {
      //if the star is on, draw a colored circle; otherwise draw a black one
      for(int i = 0; i < numStars; i ++) {
         if(isOn[i]) {
            g.setColor(colors[i]);
         } else {
            g.setColor(Color.BLACK);
         }
         g.fillOval((int)positions[i].x, (int)positions[i].y, 3, 3);
      }
   }
   
   //update the backdrop
   public void update() {
      //if isMoving, move each star down the screen by (speed)
      for(int i = 0; i < numStars; i ++) {
         if(isMoving) {
            positions[i].y += speed;
            if(positions[i].y >= size.height + 50) {
               positions[i].y = 0;
            }
         }
         //there is a random chance that each star will turn on/off
         if(Utility.random(0, 100) == 5) {
            isOn[i] = false;
         } else if (Utility.random(0, 100) == 5) {
            isOn[i] = true;
         }
      }
   }
   
   public void setIsMoving(boolean a) {
      isMoving = a;
   }
   
   public String toString() {
      return "IsMoving: " + isMoving;
   }

}