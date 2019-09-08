package present;

import java.awt.Image;

import javax.swing.ImageIcon;

// Cat class is an ally to the hero, and also tells the story
public class Cat extends Character {
	
	private double startX = 120;
	private double startY = 349;
	private double targetX;
	private double targetY;
	private Animation walkLeft;
	private Animation walkRight;
	private Animation walkUp;
	private Animation walkDown;
	private Image catCutScene = new ImageIcon(this.getClass().getResource("/assets/cutscenecat.png")).getImage();
	private Image[] Up = new Image[3];
	private Image[] Down = new Image[3];
	private Image[] Left = new Image[3];
	private Image[] Right = new Image[3];
	private Image[] still = new Image[4];
	private Image[] catguide = new Image[10];
	private Hero hero;
	boolean moving = false;

	public Cat(Hero hero) {
		posX = hero.getPosX();
		posY = hero.getPosY();
		this.hero = hero;
		targetX = hero.getPosX();
		targetY = hero.getPosY();
		speed = 2;
		direction = hero.getDirection();

		updateWalkImages();
		stillImages();
		updateWalkAnimation();
	}

	public void updateWalkImages() {
		for (int i = 0; i < 3; i++) {
			Up[i] = new ImageIcon(this.getClass().getResource("/assets/CatGrey/catU" + i + ".png")).getImage();
			Down[i] = new ImageIcon(this.getClass().getResource("/assets/CatGrey/catD" + i + ".png")).getImage();
			Left[i] = new ImageIcon(this.getClass().getResource("/assets/CatGrey/catL" + i + ".png")).getImage();
			Right[i] = new ImageIcon(this.getClass().getResource("/assets/CatGrey/catR" + i + ".png")).getImage();
		}
	}

	public void stillImages() {
		still[0] = Down[1];
		still[1] = Up[1];
		still[2] = Right[1];
		still[3] = Left[1];
		catguide[0] = new ImageIcon(this.getClass().getResource("/assets/CatGrey/screen.png")).getImage();
	}

	public Image[] getScene() {
		return catguide;
	}
	
	public Image getCutScene() {
		return catCutScene;
	}

	public void updateWalkAnimation() {
		walkUp = new Animation(4, Up);
		walkDown = new Animation(4, Down);
		walkLeft = new Animation(4, Left);
		walkRight = new Animation(4, Right);

	}

	public void runWalkAnimation() {
		walkUp.runAnimation();
		walkDown.runAnimation();
		walkLeft.runAnimation();
		walkRight.runAnimation();
	}

	public void updatePosition() {
		this.posX = hero.posX;
		this.posY = hero.posY;
	}

	public Image[] returnCat() {
		return still;
	}
	
	public void updateTarget() {
		this.targetX = hero.getPosX();
		this.targetY = hero.getPosY();
	}
	
	
	// AI follows hero, by always being at the hero's side
	public void path() {
		updateTarget();
		if (hero.getSpeed() == 4) {
			speed = 4;
		}
		else {
			speed = 2;
		}
		if (targetX < posX - 85 && hero.getDirection() == 3) {
			moving = true;
			direction = 3;
			moveLeft();
		}
		else if (targetX > posX - 40 && hero.getDirection() == 2) {
			moving = true;
			direction = 2;
			moveRight();
		}
		else if (targetY < (posY-60) && hero.getDirection() == 1) {
			moving = true;
			direction = 1;
			if (targetX > posX- 40) {
				moveRight();
			}
			else if (targetX < posX - 85) {
				moveLeft();
			}
			
			moveUp();
		}
		else if (targetY > posY-10 && hero.getDirection() == 0) {
			moving = true;
			direction = 0;
			if (targetX > posX - 40) {
				moveRight();
			}
			else if (targetX < posX - 85) {
				moveLeft();
			}

			moveDown();
		}
		else if (hero.getDirection() == 0) {
			moving = true;
			if (targetX > posX - 40) {
				direction = 2;
				moveRight();
			}
			else if (targetX < posX - 85) {
				direction = 4;
				moveLeft();
			}
			else {
				moving = false;
			}
		}
		else if (hero.getDirection() == 1) {
			moving = true;
			if (targetX > posX - 40) {
				direction = 2;
				moveRight();
			}
			else if (targetX < posX - 85) {
				direction = 3;
				moveLeft();
			}
			else {
				moving = false;
			}
		}
		else {
			moving = false;
			
		}
	}
	
	
	public Animation getWalkDown() {
		return walkDown;
	}

	public void setWalkDown(Animation walkDown) {
		this.walkDown = walkDown;
	}

	public Animation getWalkUp() {
		return walkUp;
	}

	public void setWalkUp(Animation walkUp) {
		this.walkUp = walkUp;
	}

	public Animation getWalkRight() {
		return walkRight;
	}

	public void setWalkRight(Animation walkRight) {
		this.walkRight = walkRight;
	}

	public Animation getWalkLeft() {
		return walkLeft;
	}

	public void setWalkLeft(Animation walkLeft) {
		this.walkLeft = walkLeft;
	}
}
