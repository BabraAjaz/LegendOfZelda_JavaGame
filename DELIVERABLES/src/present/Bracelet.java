package present;
import java.util.*;

import javax.swing.ImageIcon;

import java.awt.*;

public class Bracelet {
	private int energy;
	private int health;
	private boolean keepHealth = false;
	private Hero hero;
	private Image shieldImage = new ImageIcon(this.getClass().getResource("/assets/Shoot/shieldImg.png")).getImage();
	private int shieldXRelativeToChar = 40;
	private int shieldYRelativeToChar = 0;
	private int shieldX;
	private int shieldY;
	
	public Bracelet(Hero hero) {
		this.hero = hero; 
	}
	
	
	
	// Increases health if heal ability is activated
	public void recoverHP() {
		if (hero.getEnergy() >= 100) {
			hero.updateEnergy(-100);
			hero.updateHealth(100);
			hero.setHealthGain(true);
			if (hero.getHealth() > 200) {
				hero.setHealth(200);
			}
		}
	}
	
	// Increases protection against attacks if ability is activated
	public void shield() {
		if (hero.getEnergy() >= 40) {
			hero.updateEnergy(-40);
			hero.setKeepHealth(true);
		}
	}
	

	public void update() {
		
	}
	
	// Updates position based on character location
	public void updateShield(Character hero) {
		this.setShieldX((int) hero.getPosX() + shieldXRelativeToChar);
		this.setShieldY((int) hero.getPosY() + shieldYRelativeToChar);
	}


	public Image getShieldImage() {
		return shieldImage;
	}


	public void setShieldImage(Image shieldImage) {
		this.shieldImage = shieldImage;
	}


	public int getShieldX() {
		return shieldX;
	}


	public void setShieldX(int shieldX) {
		this.shieldX = shieldX;
	}


	public int getShieldY() {
		return shieldY;
	}


	public void setShieldY(int shieldY) {
		this.shieldY = shieldY;
	}



	
}
