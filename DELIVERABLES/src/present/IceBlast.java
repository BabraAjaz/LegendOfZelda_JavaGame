package present;

import javax.swing.ImageIcon;

public class IceBlast extends PowerUp {
	


	public IceBlast(double posX, double posY, int direction) {
		super(posX, posY, direction);
		blastImage = new ImageIcon(this.getClass().getResource("/assets/Shoot/ballIce.png")).getImage();
		// TODO Auto-generated constructor stub
		speed = 4;
		toCenterX = 70;
		toCenterY = 40;
	}


}
