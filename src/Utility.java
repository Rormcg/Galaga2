/*
Author: Rory McGuire
Date: 11/19/2022
Purpose: This class contains methods that are used throughout 
many of the other classes that do not belong to a specific object/class.
*/

import java.awt.Graphics;
import java.awt.Color;


public class Utility {
   
   //returns a random int between the two parameters
   public static int random(int low, int high) {
      return (int)(Math.random() * (high - low + 1) + low);
   }
   
   //returns a random double between the two parameters
   public static double random(double low, double high) {
      return (Math.random() * (high - low) + low);
   }
   
   //returns a random int that isn't zero(0) between the two parameters 
   public static int randomExcludeZero(int low, int high) {
      //the program repeats finding a random number until it finds one that isn't 0
      int num = 0;
      while(num == 0) {
         num = (int)(Math.random() * (high - low + 2) + low -1);
      }
      return num;
   }
   
   //returns the direction of the vector in degrees
   public static double vectorDirection(double x, double y) {
      //first use the formula (theta = atan(y/x)) and convert that to degrees
      double dir = Math.atan(y / x) * 180 / Math.PI;
      /*since the above formula only returns values between -90 and 90, we then need to modify the result
      by adding 90 or 270 (depending on which way the vector is facing) to get the final value in degrees*/
      if((x < 0 && y > 0) || (x < 0 && y < 0)) {
         dir += 270;
      } else  {
         dir += 90;
      } 
      return dir;
   }
   
   //returns the value in radians
   public static double toRadians(double degrees) {
      return degrees * (Math.PI / 180);
   }
   
   /*this method will draw a pixel image:
   it will take in an x and y for where to draw it, a string for which image to draw,
   a Graphics to draw it on, and an int to determine the size of each pixel in the image*/
   public static void drawPixelArt(double x, double y, String type, Graphics g, int pixelSize) {
      /*
      The colors that the letters in the following string arrays represent:
      w = white
      r = red
      g = green-blue
      b = blue
      y = yellow
      o = orange
      p = purple
      m = magenta
      l = light blue
      */
      //the following string arrays each represent one of the images that can be drawn in this method:
      //the player's ship
      String[] shipArt = {
            "-------w-------",
            "-------w-------",
            "-------w-------",
            "------www------",
            "------www------",
            "---r--www--r---",
            "---r--www--r---",
            "r--w-wwwww-w--r",
            "r--wbwwrwwbw--r",
            "w--bwwrrrwwb--w",
            "w--wwwrwrwww--w",
            "w-wwwwwwwwwww-w",
            "wwwwwrwwwrwwwww",
            "www-rrwwwrr-www",
            "ww--rr-w-rr--ww",
            "w------w------w"};
      //the bug enemies: frame 1
      String[] enemyBugArt1 = {
            "-b----y----b-",
            "--b-yryry-b--",
            "---brryrrb---",
            "----yyyyy----",
            "---bbyyybb---",
            "--bbbrrrbbb--",
            "-bbb-rrr-bbb-",
            "bbbb-yyy-bbbb",
            "bbb--rrr--bbb",
            "bbb---r---bbb"};
      //the bug enemies: frame 2
      String[] enemyBugArt2 = {
            "--b---y---b--",
            "--b-yryry-b--",
            "---brryrrb---",
            "----yyyyy----",
            "---bbyyybb---",
            "--bbbrrrbbb--",
            "--bb-rrr-bb--",
            "--bb-yyy-bb--",
            "--bb-rrr-bb--",
            "--bb--r--bb--"};
      //the ship/butterfly enemies: frame 1
      String[] enemyShipArt1 = {
            "--r--b-b--r--",
            "rrr--b-b--rrr",
            "rrr-wrwrw-rrr",
            "rrr-wwwww-rrr",
            "-rrrrwwwrrrr-",
            "--rrrbbbrrr--",
            "-rrrrbbbrrrr-",
            "rrrrrwwwrrrrr",
            "-rrr-bbb-rrr-",
            "---r-----r---"};
      //the ship/butterfly enemies: frame 2
      String[] enemyShipArt2 = {
            "--r--b-b--r--",
            "--r--b-b--r--",
            "--r-wrwrw-r--",
            "--r-wwwww-r--",
            "--rrrwwwrrr--",
            "----rbbbr----",
            "--rrrbbbrrr--",
            "--rrrwwwrrr--",
            "--rr-bbb-rr--",
            "--rr--b--rr--"};
      //the boss enemies: full health frame 1
      String[] enemyBossArt1A = {
            "------g-g------",
            "------g-g------",
            "---ggoogoogg---",
            "----googoog----",
            "-----ggggg-----",
            "----gyygyyg-----",
            "--gggyyyyyggg--",
            "gggggyyyyyggggg",
            "-ggggyyyyygggg-",
            "-gogg-o-o-ggog-",
            "ggog--o-o--gogg",
            "gogg-------ggog",
            "goog-------goog",
            "goog-------goog",
            "gggg-------gggg",
            "-gg---------gg-"};
      //the boss enemies: full health frame 2
      String[] enemyBossArt2A = {
            "---------------",
            "------g-g------",
            "---ggoogoogg---",
            "----googoog----",
            "-----ggggg-----",
            "---ggyygyygg----",
            "--gggyyyyyggg--",
            "gggggyyyyyggggg",
            "-ggg-yyyyy-ggg-",
            "--gg--o-o--gg--",
            "--gg--o-o--gg--",
            "--ggg-----ggg--",
            "---gg-----gg---",
            "----gg---gg----",
            "-----gg-gg-----",
            "------g-g------"};
      //the boss enemies: half health frame 1
      String[] enemyBossArt1B = {
            "------b-b------",
            "------b-b------",
            "---bbmmbmmbb---",
            "----bmmbmmb----",
            "-----bbbbb-----",
            "----bppbppb-----",
            "--bbbpppppbbb--",
            "bbbbbpppppbbbbb",
            "-bbbbpppppbbbb-",
            "-bmbb-m-m-bbmb-",
            "bbmb--m-m--bmbb",
            "bmbb-------bbmb",
            "bmmb-------bmmb",
            "bmmb-------bmmb",
            "bbbb-------bbbb",
            "-bb---------bb-"};
      //the boss enemies: half health frame 2
      String[] enemyBossArt2B = {
            "---------------",
            "------b-b------",
            "---bbmmbmmbb---",
            "----bmmbmmb----",
            "-----bbbbb-----",
            "---bbppbppbb----",
            "--bbbpppppbbb--",
            "bbbbbpppppbbbbb",
            "-bbb-ppppp-bbb-",
            "--bb--m-m--bb--",
            "--bb--m-m--bb--",
            "--bbb-----bbb--",
            "---bb-----bb---",
            "----bb---bb----",
            "-----bb-bb-----",
            "------b-b------"};
      //the explosion when enemies die: frame 1
      String[] enemyExplosion1 = {
            "-w---o-----",
            "--r--ro-r--",
            "---w-o--o--",
            "-ro-rr-rr--",
            "-o-r-r-wo--",
            "r--r-r-r---",
            "---or---o--",
            "or-r-r-r-r-",
            "rwo---r-o--",
            "-o---r-----",
            "--r----o---"};
      //the explosion when enemies die: frame 2
      String[] enemyExplosion2 = {
            "-----o-------",
            "--r-----r-o--",
            "---w-o-------",
            "----r--o-r-r-",
            "---o-----r-w-",
            "r-r---ro---r-",
            "----r----o---",
            "---wr-----w--",
            "r---r---r----",
            "--r---o--r---",
            "---r-----o---",
            "---o---wr----",
            "---r------r--"};
      //the explosion when enemies die: frame 3
      String[] enemyExplosion3 = {
            "-w---o-w-------",
            "--r--ro-r-ow---",
            "---w-o---o--o--",
            "-r--r--o-r-rr--",
            "-o-o--o--r-wo--",
            "r-r----w---r-w-",
            "-ow--o--o---o--",
            "or--o--------r-",
            "rwo---------o--",
            "-o----o--r-w-o-",
            "--rr-----o-o---",
            "---o-w-wr-o-w--",
            "---r-o----ro---",
            "----o----o---o-",
            "---------------"};
      //the explosion when the player gets hit: frame 1
      String[] playerExplosion1 = {
            "---------r---b-",
            "w--r-------w---",
            "---w---r----r--",
            "--r----rr--r---",
            "-w--r-rr-----w-",
            "--r----r-------",
            "r--b-rrr-rr-w-b",
            "r-w--r-r-rr---r",
            "r--rr-rrr-r---r",
            "----r-rrrr---rr",
            "r--rrrrr--r--r-",
            "-rrrr-rrrrrrr-r-",
            "b--rr-rrrrrrrr-",
            "-rrrrr-w-rr-rrw"};
      //the explosion when the player gets hit: frame 2
      String[] playerExplosion2 = {
            "-r----bbb---r---r",
            "--r-b-wwwwwb--rr-",
            "--rrww-wwwwwb-rb-",
            "---w-rww-w-bwrw--",
            "b-wwwwwwwwwwr-wb-",
            "-wwwwrwww-wwwbw--",
            "-b-ww-wrwwwbb-b--",
            "--wwwwrwrrwrwwb--",
            "b-ww-wrrwrrrwwww-",
            "-bw--wwrwwwrw--wb",
            "--wwrrwr-wrwww-b-",
            "bwwrwrww--wrw-ww-",
            "--b-wwwwrrwrww-b-",
            "-bwwr---wwwwrwb--",
            "---brwwww-wwbrr--",
            "--rrb-ww-www--rr-",
            "-r-b-b-wwwwb----r"};
      //the explosion when the player gets hit: frame 3
      String[] playerExplosion3 = {
            "-r----lll---r---r",
            "--r-l-lllwwl--rr-",
            "--rrww-wll--l-rl-",
            "-l-w-lwl-wllwrl--",
            "l-lwwwwrlwwwr-wl-",
            "-wlwwrwlrrl-wlw--",
            "-l-ww-wrwwwll-ll-",
            "-rlwwwrwrrwrwwl--",
            "l-lrlwrrwwlrwllw-",
            "-lw--wwwwwwrw-lwl",
            "-lwwrrlwwwrllw-l-",
            "lwwwwrww--lrw-ww-",
            "--l-wwlrwwlrwr-l-",
            "-llwwr-llwwwrwl--",
            "-l-lrwwwllwwwwll-",
            "--rrl-ww-wll--rr-",
            "-r-l-l-lllwl----r"};
      
      String[] pixelArt;//this will be changed to whichever string array is being used
      //based on what type of image was sent into this method, make pixelArt equal to the corresponding string array
      if(type == "ship") {
         pixelArt = shipArt;
      } else if(type == "enemy-bug1") {
         pixelArt = enemyBugArt1;
      } else if(type == "enemy-bug2") {
         pixelArt = enemyBugArt2;
      } else if(type == "enemy-ship1") {
         pixelArt = enemyShipArt1;
      } else if(type == "enemy-ship2") {
         pixelArt = enemyShipArt2;
      } else if(type == "enemy-boss1A") {
         pixelArt = enemyBossArt1A;
      } else if(type == "enemy-boss2A") {
         pixelArt = enemyBossArt2A;
      } else if(type == "enemy-boss1B") {
         pixelArt = enemyBossArt1B;
      } else if(type == "enemy-boss2B") {
         pixelArt = enemyBossArt2B;
      } else if(type == "enemy-explosion1") {
         pixelArt = enemyExplosion1;
      } else if(type == "enemy-explosion2") {
         pixelArt = enemyExplosion2;
      } else if(type == "enemy-explosion3") {
         pixelArt = enemyExplosion3;
      } else if(type == "player-explosion1") {
         pixelArt = playerExplosion1;
      } else if(type == "player-explosion2") {
         pixelArt = playerExplosion2;
      } else if(type == "player-explosion3") {
         pixelArt = playerExplosion3;
      } else {
         pixelArt = enemyBugArt1;
      }
      
      /*now, loop through each character in the chosen string array, and for each one 
      change the color to correspond to that character, and draw a square with that color
      */
      for(int row = 0; row < pixelArt.length; row ++) {
         for(int col = 0; col < pixelArt[row].length(); col ++) {
            boolean drawPixel = true;//will become false if that character is '-' (that character represents a blank space)
            switch(pixelArt[row].charAt(col)) {
               case 'w':
                  g.setColor(Color.WHITE);
                  break;
               case 'r':
                  g.setColor(Color.RED);
                  break;
               case 'g':
                  g.setColor(new Color(55, 140, 130));
                  break;
               case 'b':
                  g.setColor(Color.BLUE);
                  break;
               case 'y':
                  g.setColor(Color.YELLOW);
                  break;
               case 'o':
                  g.setColor(new Color(255, 90, 13));
                  break;
               case 'p':
                  g.setColor(new Color(186, 62, 217));
                  break;
               case 'm':
                  g.setColor(new Color(255, 0, 113));
                  break;
               case 'l':
                  g.setColor(new Color(0, 128, 255));
                  break;
               case '-':
                  drawPixel = false;
                  break;
            }
            if(drawPixel) {
               /*draw a square that has the dimensions: pixelSize X pixelSize
               and is drawn at a position that corresponds to where the current character is in the string array*/
               g.fillRect((int)(x + (pixelSize * (col - pixelArt[row].length() * 0.5))), 
                          (int)(y + (pixelSize * (row - pixelArt.length * 0.5))), pixelSize, pixelSize);
            }
         }
      }
   }
}