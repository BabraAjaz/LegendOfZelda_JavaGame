package present;

import java.awt.*;
import java.util.*;
import javax.swing.*;

public class Dog extends Enemy {


	Hero hero = new Hero();
	private int initX = 1125;
	private int initY = 384;
	private final int directionLimit = 3;
	private final int WIDTH = 1360;
	private final int HEIGHT = 700;
	private boolean boundary;
	private int randomSpeedChange = 0;
	private double posR;
	private double posC;
	private int currentR;
	private int currentC;
	private Image[] dogSprite = new Image[4];


	// with 60 cols and 34 rows;
	public Dog(Hero hero, int posX, int posY, int direction) {
		super(hero);
		this.direction = direction;
		startX = posX;
		startY = posY;
		targetY = hero.getPosY();
		targetX = hero.getPosX();
		this.posY = posY;
		this.posX = posX;
		speed = 1;
		alive = true;
		type = 1;
		loadDogSprite();

	}

	public Rectangle getBounds() {
		return new Rectangle((int) posX, (int) posY, 32, 32);
	}

	public void setTargetC(int posX) {
		this.targetX = posX;
	}

	public void setTargetR(int posY) {
		this.targetY = posY;
	}

	public void path() {
		if (alive) {
			if (direction == 1) {
				moveUp();
				if (posY < 0) {
					direction = 0;
				}
			}
			else if (direction == 0) {
				moveDown();
				if (posY > HEIGHT) {
					direction = 1;
				}

			}
			if (direction == 2) {
				moveRight();
				if (posX > WIDTH) {
					direction = 3;
				}
			}
			else if (direction == 3) {
				moveLeft();
				if (posX < 0) {
					direction = 2;
				}
			}
		}
	}

	public Image[] getDogSprite() {
		return dogSprite;

	}

	public void loadDogSprite() {
		dogSprite[0] = new ImageIcon(this.getClass().getResource("/assets/Dog/down0.png")).getImage();
		dogSprite[1] = new ImageIcon(this.getClass().getResource("/assets/Dog/up0.png")).getImage();
		dogSprite[2] = new ImageIcon(this.getClass().getResource("/assets/Dog/right0.png")).getImage();
		dogSprite[3] = new ImageIcon(this.getClass().getResource("/assets/Dog/left0.png")).getImage();


	}

	public int getInitX() {
		return initX;
	}

	public void setInitX(int initX) {
		this.initX = initX;
	}

	public int getInitY() {
		return initY;
	}

	public void setInitY(int initY) {
		this.initY = initY;
	}

	public int getDirectionLimit() {
		return directionLimit;
	}

	public boolean isBoundary() {
		return boundary;
	}

	public void setBoundary(boolean boundary) {
		this.boundary = boundary;
	}

	public int getRandomSpeedChange() {
		return randomSpeedChange;
	}

	public void setRandomSpeedChange(int randomSpeedChange) {
		this.randomSpeedChange = randomSpeedChange;
	}

	public double getPosR() {
		return posR;
	}

	public void setPosR(double posR) {
		this.posR = posR;
	}

	public double getPosC() {
		return posC;
	}

	public void setPosC(double posC) {
		this.posC = posC;
	}

	public int getCurrentR() {
		return currentR;
	}

	public void setCurrentR(int currentR) {
		this.currentR = currentR;
	}

	public int getCurrentC() {
		return currentC;
	}

	public void setCurrentC(int currentC) {
		this.currentC = currentC;
	}

}