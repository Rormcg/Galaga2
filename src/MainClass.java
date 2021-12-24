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

public class MainClass extends JComponent implements KeyListener, ActionListener {
private JFrame frame;
private int screenWidth = 400, screenHeight = 500;

MainClass() {
   frame = new JFrame("Challenges One");
   Container content = frame.getContentPane();
   content.setBackground(Color.BLACK);
   content.add(this);
   setUp();
   
   Timer t = new Timer(5, this);
   t.start();
}

public void setUp() {
   frame.setSize(screenWidth+17, screenHeight+40);
   frame.setLocationRelativeTo(null);
   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
   frame.setVisible(true);
}


public void actionPerformed(ActionEvent e) {

}

public void keyPressed(KeyEvent e) {
   if(e.getKeyCode() == 32) {
      //space
   } else if(e.getKeyCode() == 39) {
      //right arrow key
   } else if(e.getKeyCode() == 37) {
      //left arrow key
   } 
}
public void keyReleased(KeyEvent e) {
   if(e.getKeyCode() == 32) {
      //space
   } else if(e.getKeyCode() == 39) {
      //right arrow key
   } else if(e.getKeyCode() == 37) {
      //left arrow key
   }
}
public void keyTyped(KeyEvent e) {}
}

class RunMain {
   public static void main(String[] args) {
      MainClass mainClass = new MainClass();
   }
}