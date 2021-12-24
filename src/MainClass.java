import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JComponent;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Color;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class MainClass extends JComponent implements ActionListener {
private JFrame frame;
private int screenWidth = 400, screenHeight = 500;


Ship ship = new Ship(200, 430);

MainClass() {
   frame = new JFrame("Galaga");
   Container content = frame.getContentPane();
   content.setBackground(Color.BLACK);
   content.add(this);
   setup();
   
   Timer t = new Timer(1, this);
   t.start();
}

public void setup() {
   frame.setSize(screenWidth+17, screenHeight+40);
   frame.setLocationRelativeTo(null);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
   frame.addKeyListener(ship);
}

public void paintComponent(Graphics g) {
   ship.draw(g);
}

public void actionPerformed(ActionEvent e) {
   ship.update();
   repaint();
}

}

class RunMain {
   public static void main(String[] args) {
      MainClass mainClass = new MainClass();
   }
}