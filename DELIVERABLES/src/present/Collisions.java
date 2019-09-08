package present;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Collisions {

	public Collisions() {
	}
	
	public boolean checkCollision(Rectangle A, Rectangle B) {
		return A.intersects(B);
	}
}
