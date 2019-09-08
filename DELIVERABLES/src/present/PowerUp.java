package present;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

public class PowerUp {

	protected double posX,posY;
	protected int direction;
	protected Image blastImage;
	protected int speed;
	protected int type;
	protected int toCenterX;
	protected int toCenterY;
	protected int initX;
	protected int initY;
	
	public PowerUp(double posX, double posY, int direction) {
		this.posX = posX;
		this.posY = posY;
		this.direction = direction;
		initX = (int)posX;
		initY = (int)posY;
		
	}
	
	public double getPosX() {
		return posX;
	}
	
	public double getPosY() {
		return posY;
	}
	
	public void moveUp() {
		posY-=speed;
	}
	
	public void moveDown() {
		posY+=speed;
	}
	
	public void moveRight() {
		posX+=speed;
	}
	
	public void moveLeft( ) {
		posX-=speed;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public void update() {
		if (direction == 0) {
			moveDown();
		} else if (direction == 1) {
			moveUp();
		} else if (direction == 2) {
			moveRight();
		} else if (direction == 3) {
			moveLeft();
		}
		
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)posX+toCenterX,(int)posY+toCenterY,blastImage.getWidth(null),blastImage.getHeight(null));
	}
	
	public int getRelativeX() {
		return (int)posX+toCenterX;
	}
	
	public int getRelativeY() {
		return (int) (posY + toCenterY);
	}
	
	public int getWidth() {
		return blastImage.getWidth(null);
	}
	
	public int getHeight() {
		return blastImage.getHeight(null);
	}
	
	public boolean checkPowerUpRange() {
		if (this.getPosY() <= this.initY-150 ||
				this.getPosY() >= this.initY+150 
				|| this.getPosX() <= this.initX-200 ||
				this.getPosX() >= this.initX+ 200) {
			return true;
		}
		else return false;
	}
	
	
}


