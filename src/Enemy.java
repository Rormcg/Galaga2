import java.awt.geom.Point2D;

public class Enemy {

private Point2D.Double pos;
private boolean isDead;

Enemy() {
   pos = new Point2D.Double(0, 0);
   isDead = false;
}
Enemy(Point2D.Double position) {
   pos = position;
}

}