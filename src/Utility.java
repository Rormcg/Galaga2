import java.awt.Graphics;
import java.awt.Color;


public class Utility {
   
   public static int random(int low, int high) {
      return (int)(Math.random() * (high - low) + low);
   }
   
   /*
   w = white
   r = red
   g = green-blue
   b = blue
   y = yellow
   o = orange
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
         
   private static String[] enemyBugArt1 = {
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
   private static String[] enemyBugArt2 = {
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
   private static String[] enemyShipArt1 = {
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
   private static String[] enemyShipArt2 = {
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
   private static String[] enemyBossArt1 = {
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
   private static String[] enemyBossArt2 = {
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
   private static String[] pixelArt;
   public static void drawPixelArt(double x, double y, String type, Graphics g, int size) {
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
      } else if(type == "enemy-boss1") {
         pixelArt = enemyBossArt1;
      } else if(type == "enemy-boss2") {
         pixelArt = enemyBossArt2;
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
                  g.setColor(new Color(55, 140, 130));
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
               case 'o':
                  g.setColor(new Color(255, 123, 4));
                  drawPixel = true;
                  break;
            }
            if(drawPixel) {
               g.fillRect((int)(x + col * (size) - (0.5 * pixelArt[row].length() - 2)), (int)(y + row * (size) - (0.5 * pixelArt.length - 2)), size, size);
            }
         }
      }
   }
}