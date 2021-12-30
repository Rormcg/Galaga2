import java.awt.Graphics;
import java.awt.Color;


public class Utility {

   /*
   w = white
   r = red
   g = green
   b = blue
   y = yellow
   */
   private static String[] shipArt = {
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
         
   private static String[] enemyBugArt = {
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------",
         "---------------"};
   private static String[] pixelArt;
   public static void drawPixelArt(double x, double y, String type, Graphics g, int size) {
      if(type == "ship") {
         pixelArt = shipArt;
      } else if(type == "enemy-bug") {
         pixelArt = enemyBugArt;
      } else if(type == "enemy-boss") {
      
      }
      
      for(int row = 0; row < pixelArt.length; row ++) {
         for(int col = 0; col < pixelArt[row].length(); col ++) {
            boolean drawPixel = false;
            switch(pixelArt[row].charAt(col)) {
               case 'w':
                  g.setColor(Color.WHITE);
                  drawPixel = true;
                  break;
               case 'r':
                  g.setColor(Color.RED);
                  drawPixel = true;
                  break;
               case 'g':
                  g.setColor(Color.GREEN);
                  drawPixel = true;
                  break;
               case 'b':
                  g.setColor(Color.BLUE);
                  drawPixel = true;
                  break;
               case 'y':
                  g.setColor(Color.YELLOW);
                  drawPixel = true;
                  break;
            }
            if(drawPixel) {
               g.fillRect((int)(x + col * size), (int)(y + row * size), size, size);
            }
         }
      }
   }
}