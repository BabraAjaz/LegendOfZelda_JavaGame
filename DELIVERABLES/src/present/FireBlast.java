package present;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class FireBlast extends PowerUp {
	
	
	public FireBlast(double posX, double posY, int direction) {
		super(posX, posY, direction);
		blastImage = new ImageIcon(this.getClass().getResource("/assets/Shoot/ballFire.png")).getImage();
		
		speed = 4;
		toCenterX = 70;
		toCenterY = 40;
	}

}
