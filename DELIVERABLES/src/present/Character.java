package present;

import java.awt.Rectangle;

public class Character {

	protected boolean alive = false;
	protected double posX, posY;
	protected int boundX, boundY, boundWidth, boundHeight;
	protected int speed;
	protected int width,height;
	protected int direction;
	private boolean blockRight = false;
	private boolean blockLeft = false;
	private boolean blockUp = false;
	private boolean blockDown = false;

	public Character() {
		alive = true;
	}

	public Character(double posX, double posY) {
		alive = true;
		this.posX = posX;
		this.posY = posY;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int) posX, (int) posY, width, height);

	}
	
	public Rectangle getPredictedBounds() {
		
		if (direction == 2) { // right 
			return new Rectangle((int) posX + 16, (int) posY, width, height);
		}
		else if (direction == 0) { // down 
			return new Rectangle((int) posX, (int) posY + 16, width, height);
		} 
		else if (direction == 1) { // up
			return new Rectangle((int) posX, (int) posY - 16, width, height);
		} 
		else  { // left
			return new Rectangle((int) posX - 16, (int) posY, width, height);
		}
	
	}

	public void move() {
		moveUp();
		moveDown();
		moveLeft();
		moveRight();

	}

	public void moveUp() {
		if(!blockUp) {
		posY -= speed;
		} 
		else {
			blockUp = false;
		}
	}

	public void moveDown() {
		if (!blockDown) {
		posY += speed;
		} 
		else { blockDown = false;
		}
		

	}

	public void moveLeft() {
		if(!blockLeft) {
			posX -= speed;
		} else {
			blockLeft = false;
		}
	}

	public void moveRight() {
		if(!blockRight) {
			posX += speed;
		}
		else {
			blockRight = false;
		}
	}
	
	public void staticCollisions() {

	}


	public boolean isAlive() {
		return alive;
	}

	public int getDirection() {
		return direction;
	}
	
	public int getSpeed() {
		return speed;
	}
	
	
	public void setAlive(boolean alive) {
		this.alive = alive;
	}

	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}
	public boolean isBlockRight() {
		return blockRight;
	}

	public void setBlockRight(boolean blockRight) {
		this.blockRight = blockRight;
	}

	public boolean isBlockLeft() {
		return blockLeft;
	}

	public void setBlockLeft(boolean blockLeft) {
		this.blockLeft = blockLeft;
	}

	public boolean isBlockUp() {
		return blockUp;
	}

	public void setBlockUp(boolean blockUp) {
		this.blockUp = blockUp;
	}

	public boolean isBlockDown() {
		return blockDown;
	}

	public void setBlockDown(boolean blockDown) {
		this.blockDown = blockDown;
	}
}
