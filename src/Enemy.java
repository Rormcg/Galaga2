import java.awt.Point;

public class Enemy {

private Point pos;
private boolean isDead;

Enemy() {
   pos.move(0, 0);
   isDead = false;
}
Enemy(Point position) {
   pos = position;
}

}