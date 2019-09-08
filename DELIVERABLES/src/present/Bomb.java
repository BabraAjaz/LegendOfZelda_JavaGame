package present;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Bomb extends Enemy {

	private Image[] sprite = new Image[4];
	private int direction = 0;
	private boolean BmoveUp = false;
	private boolean BmoveDown = false;
	private boolean move = true;
	private Hero target = new Hero();
	private int maxX = 0;
	private int maxY = 0;
	private int axis;
	private boolean bombExplode = false;


	// Bomb class can move in straight lines, explode and target the hero
	public Bomb(Hero hero, int bPosX, int bPosY, int axis, int maxX, int maxY, int direction) { // CHANGES
		super(hero);
		this.direction = direction;
		speed = 1;
		posX = bPosX;
		posY = bPosY;
		startX = bPosX;
		startY = bPosY;
		this.axis = axis;
		this.maxX = maxX;
		this.maxY = maxY;
		targetX = (int) hero.getPosX();
		targetY = (int) hero.getPosY();
		this.alive = true;
		this.type = 2;
		updateImages();
		width = sprite[0].getWidth(null);
		height = sprite[0].getHeight(null);
		// path();
	}

	// Helper function for collisions
	public Rectangle getBounds() {
		return new Rectangle((int)posX, (int)posY, sprite[0].getWidth(null), sprite[0].getHeight(null));
	}


	// Updates target position for AI
	public void updateTarget(Hero hero) {
		targetX = (int) hero.getPosX();
		targetY = (int) hero.getPosY();
	}


	// Calculates Path
	public void path() {

		// If the target comes in the bombs range of detection, increase speed
		// and move towards character (axis = 0 is moving the Y direction and
		// axis = 1 is moving the X direction)
		if (axis == 0) {
			if ((targetY + 20 > posY) && (targetX > (posX - 200)) && (targetX < (posX + 50))) {
				direction = 0;
				speed = 2;
				moveDown();

			} else if ((targetY + 20 < posY) && (targetX > posX - 200 && targetX < posX + 50)) {
				direction = 1;
				speed = 2;
				moveUp();
			} else if (posY < maxY && direction == 0) {
				speed = 1;
				moveDown();
			} else if (posY > maxY && direction == 1) {
				speed = 1;
				moveUp();
			} else if (posY == maxY && direction == 0) {
				speed = 1;
				direction = 1;
				moveUp();
			} else if (posY == maxY && direction == 1) {
				speed = 1;
				direction = 0;
				moveDown();
			} else if (direction == 1) {
				speed = 1;
				moveUp();
				if (posY <= startY) {
					direction = 0;
				}
			} else if (direction == 0) {
				speed = 1;
				moveDown();
				if (posY >= startY) {
					direction = 1;
				}
			}
		}

		if (axis == 1)
		{
			if ((posX - 50 < targetX) && (targetY > (posY - 100)) && (targetY < (posY + 50))) {
				direction = 2;
				speed = 2;;
				moveRight();

			} else if ((posX - 50 > targetX) && (targetY > (posY - 100)) && (targetY < (posY + 50))) {
				direction = 3;
				speed = 2;
				moveLeft();
			} else if (posX < maxX && direction == 2) {
				speed = 1;
				moveRight();
			} else if (posX > maxX && direction == 3) {
				speed = 1;
				moveLeft();
			} else if (posX == maxX && direction == 3) {
				speed = 1;
				direction = 2;
				moveRight();
			} else if (posX == maxX && direction == 2) {
				speed = 1;
				direction = 3;
				moveLeft();
			} else if (direction == 3) {
				speed = 1;
				moveLeft();
				if (posX <= startX) {
					direction = 2;
				}
			} else if (direction == 2) {
				speed = 1;
				moveRight();
				if (posX >= startX ) {
					direction = 3;
				}
			}
		}
	}


	public double getPosX() {
		return posX;
	}

	public double getPosY() {
		return posY;
	}

	public int getDirection() {
		return direction;
	}

	public Image[] getSprite() {
		return sprite;
	}

	public void dead() { 
		if (!alive) {
			posX = 0;
			posY = 0;
		}
	}

	public void updateImages() {
		for (int i = 0; i < 4; i++) {
			// 0 - Down, 1 - Up , 2 - Right, 3 - Left
			sprite[i] = new ImageIcon(this.getClass().getResource("/assets/Bomb/bombs" + i + ".png")).getImage(); // 0

		}
	}



	public boolean isBmoveUp() {
		return BmoveUp;
	}

	public void setBmoveUp(boolean bmoveUp) {
		BmoveUp = bmoveUp;
	}

	public boolean isBmoveDown() {
		return BmoveDown;
	}

	public void setBmoveDown(boolean bmoveDown) {
		BmoveDown = bmoveDown;
	}

	public boolean isMove() {
		return move;
	}

	public void setMove(boolean move) {
		this.move = move;
	}


	public void setPosX(int posX) {
		this.posX = posX;
	}


	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getAxis() {
		return axis;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public boolean isBombExplode() {
		return bombExplode;
	}

	public void setBombExplode(boolean bombExplode) {
		this.bombExplode = bombExplode;
	}
}
