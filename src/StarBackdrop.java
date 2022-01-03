import java.awt.geom.Point2D;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Dimension;

public class StarBackdrop {
private int numStars = 250;
private Point2D.Double[] positions = new Point2D.Double[numStars];
private boolean[] isOn = new boolean[numStars];
private double speed = 2;
private Color[] colors = new Color[numStars];
private Dimension size; //overall size

StarBackdrop(int width, int height) {
   size = new Dimension(width, height);
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

public void draw(Graphics g) {
   for(int i = 0; i < numStars; i ++) {
      if(isOn[i]) {
         g.setColor(colors[i]);
      } else {
         g.setColor(Color.BLACK);
      }
      g.fillOval((int)positions[i].x, (int)positions[i].y, 3, 3);
   }
}

public void update() {
   for(int i = 0; i < numStars; i ++) {
      positions[i].y += speed;
      if(positions[i].y >= size.height + 50) {
         positions[i].y = 0;
      }
      if(Utility.random(0, 120) == 5) {
         isOn[i] = false;
      } else if (Utility.random(0, 120) == 5) {
         isOn[i] = true;
      }
   }
}
}