import java.awt.Graphics;
import java.awt.Color;
import java.awt.Font;
import java.awt.geom.Point2D;
import java.text.DecimalFormat;

public class EndScreen {
   private Point2D.Double pos;
   private double numHits;
   private double shotsFired;
   private int timer = 500;
   
   private DecimalFormat percent = new DecimalFormat("#0.0%");
   private DecimalFormat integer = new DecimalFormat("#");
   
   EndScreen(double x, double y, int hits, int shots) {
      pos = new Point2D.Double(x, y);
      numHits = hits;
      shotsFired = shots;
   }
   
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
}