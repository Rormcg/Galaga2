import java.awt.Graphics;
import java.awt.Color;


public class Utility {
   
   public static int random(int low, int high) {
      return (int)(Math.random() * (high - low + 1) + low);
   }
   
   public static double random(double low, double high) {
      return (Math.random() * (high - low) + low);
   }
   
   public static int randomExcludeZero(int low, int high) {
      int num = 0;
      while(num == 0) {
         num = (int)(Math.random() * (high - low + 2) + low -1);
      }
      return num;
   }
   
   public static double vectorDirection(double x, double y) {
       double dir = Math.atan(y / x) * 180 / Math.PI;
      if((x < 0 && y > 0) || (x < 0 && y < 0)) {
         dir += 270;
      } else  {
         dir += 90;
      } 
      return dir;
   }
   
   public static double toRadians(double degrees) {
      return degrees * (Math.PI / 180);
   }
   
   /*
   w = white
   r = red
   g = green-blue
   b = blue
   y = yellow
   o = orange
   p = purple
   m = magenta
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
   private static String[] enemyBossArt1A = {
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
   private static String[] enemyBossArt2A = {
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
   private static String[] enemyBossArt1B = {
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
   private static String[] enemyBossArt2B = {
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
   private static String[] enemyExplosion1 = {
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
   private static String[] enemyExplosion2 = {
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
   private static String[] enemyExplosion3 = {
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
   private static String[] pixelArt;
   public static void drawPixelArt(double x, double y, String type, Graphics g, int pixelSize) {
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
      }
      
      for(int row = 0; row < pixelArt.length; row ++) {
         for(int col = 0; col < pixelArt[row].length(); col ++) {
            boolean drawPixel = true;
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
               case '-':
                  drawPixel = false;
                  break;
            }
            if(drawPixel) {
               g.fillRect((int)(x + (pixelSize * (col - pixelArt[row].length() * 0.5))), 
                          (int)(y + (pixelSize * (row - pixelArt.length * 0.5))), pixelSize, pixelSize);
            }
         }
      }
   }
}