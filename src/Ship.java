import java.awt.Point;

public class Ship {

private Point pos;
private boolean isDead;

Ship() {
   pos.move(0, 0);
   isDead = false;
}
Ship(Point position) {
   pos = position;
   isDead = false;
}
}