package present;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class Hero extends Character {
	
	private String name;
	
	private Animation walkFront;
	private Animation walkLeft;
	private Animation walkRight;
	private Animation walkBack;
	private Animation atkUp;
	private Animation atkDown;
	private Animation atkLeft;
	private Animation atkRight;
	private ArrayList<FireBlast> fireBlast = new ArrayList<FireBlast>();
	private FireBlast fireAmmo;
	private ArrayList<IceBlast> iceBlast = new ArrayList<IceBlast>();
	private IceBlast iceAmmo;

	private boolean moved = false; // flags
	private boolean atk = false; // flags
	
	private boolean keepHealth = false;
	private int atkDelay = 0;
	private int braceDelay = 0;
	private String weapon = new String("Sword"); // Updates weapon of use
	private int startX = 120; // stage 1 X resets
	private int startY = 349;// stage 1 Y
	private int points;
	private boolean boundary = false;
	private boolean healthLoss = false;
	private boolean healthGain = false;
	private double health = 200;
	private double energy = 200;
	private int posXMax = 1276;
	private int posXMin = 0;
	private int posYMax = 668;
	private int posYMin = 0;
	private int shotType = 0; // 0 is Nothing // 1 is Fire // 2 is Ice
	
	

	// Images for Sword + Walking Animation
	private Image[] walkF = new Image[8];
	private Image[] walkB = new Image[8];
	private Image[] walkL = new Image[8];
	private Image[] walkR = new Image[8];
	private Image[] SwordU = new Image[6];
	private Image[] SwordD = new Image[6];
	private Image[] SwordL = new Image[6];
	private Image[] SwordR = new Image[6];

	// Images for shooting
	Image[] fire = new Image[3];
	Image fireAtk;
	int fireDirection;
	double fireX = 0;
	double fireY = 0;
	double initFireX = 0;
	double initFireY = 0;
	boolean shotF = false;
	boolean shotI = false;
	boolean shotComplete = false;
	boolean iceComplete = false;
	boolean fireComplete = false;
	private boolean shot = false;
	Image[] ice = new Image[3];
	Image iceAtk;
	int iceDirection;
	double iceX = 0;
	double iceY = 0;
	double initIceX;
	double initIceY;

	// double speed = 2;
	double posXlim = 1276;

	 // 0 = Down, 1 = Up, 2 = Right, 3 = Left
	private Image spriteheroes[] = new Image[4];

	public Hero() {
		weapon = "Sword";
		direction = 0;
		posX = startX;
		posY = startY;
		speed = 2;
		width = 32;
		height = 32;
		////////////////// Update Walking Animation
		boundX = getBounds().x;
		boundY = getBounds().y;
		boundWidth = getBounds().width;
		boundHeight = getBounds().height;
		
		updateWalkAnimation();

		///////////////// Update Sword Animation

		updateSwordAnimation();

		/////////// Stationary Sprite

		updateStationarySprite();

		// Update projectile atks
		updateShot();

	}

	/********** Getters & Setters *****************/
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}

	public int getPoints() {
		return this.points;
	}

	public void setPoints() {
		this.points++;
		updateEnergy(10);
	}

	public int getRow() {
		return (int) posY / 32;
	}

	public int getCol() {
		return (int) posX / 32;
	}

	public Image getIce() {
		return iceAtk;
	}

	public Image getFire() {
		return fireAtk;
	}

	public void setPosition(int l) {
		if (l == 1) {
			posX = 120;
			posY = 300;
		} else if (l == 2) {
			posX = -54;
			posY = 316;
		} else if (l == 3) {
			posX = 64;
			posY = 592;
		} else if (l == 4) {
			posX = 64;
			posY = 560;
		}
	}

	public void changeDirection(int direction) {
		this.direction = direction;
	}

	public void explode() {/////////// Added
		if (!keepHealth) {
			health -= 100;
		} else if (keepHealth) {
			health -= 20;
			keepHealth = false;
		}
	}

	public int getDirection() {
		return direction;
	}

	public Image[] returnHero() {
		return spriteheroes;
	}

	public void setNormalLimits() {
		posXMax = 1276;
		posXMin = -64;
		posYMin = 0;
		posYMax = 668;
	}

	public double getPosX() {
		return posX;
	}
	
	public Rectangle swordBounds() {
		if (direction == 0 && atk) {
			return new Rectangle((int)posX,(int)posY+50,160,40);
		}
		else if (direction == 1 && atk) {
			return new Rectangle((int)posX,(int)posY,160,40);
		}
		else if (direction == 2 && atk) {
			return new Rectangle((int)posX+80,(int)posY+20,70,100);
		}
		else if (direction == 3 && atk) {
			return new Rectangle((int)posX,(int)posY,70,100);
		}
		else {
			return new Rectangle(0,0,0,0);
		}
	}

	public double getEnergy() {
		return energy;
	}

	public void updateEnergy(int update) {
		energy += update;
		if (energy > 200) {
			energy = 200;
		}
	}

	public void updateHealth(int update) {
		health += update;
		if (health > 200) {
			health = 200;
		}
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public boolean getKeepHealth() {
		return keepHealth;
	}

	public void setKeepHealth(boolean keepHealth) {
		this.keepHealth = keepHealth;
	}

	public double getPosY() {
		return posY;
	}

	public Rectangle getBounds() {
		//return new Rectangle((int) posX+60, (int) posY, 40, 80);
		return new Rectangle((int)posX+65, (int)posY+40, 32,32);
	}
	
	public Rectangle getPredictedBounds() {
		
		if (direction == 2) { // right 
			return new Rectangle((int)posX+65+8, (int)posY+40, 32,32);
		}
		if (direction == 0) { // down 
			return new Rectangle((int)posX+65, (int)posY+40+8, 32,32);
		} 
		if (direction == 1) { // up
			return new Rectangle((int)posX+65, (int)posY+40-8, 32,32);
		} 
		else{ // left
			return new Rectangle((int)posX+65-8, (int)posY+40, 32,32);
		}
	
	}

	/********** Changing animation speed for run ********/
	public void run() {
		this.speed = 4;
		getWalkFront().changeAnimationSpeed(1); // 0
		getWalkBack().changeAnimationSpeed(1); // 1
		getWalkLeft().changeAnimationSpeed(1); // 2
		getWalkRight().changeAnimationSpeed(1); // 3
	}

	public void normal() {
		this.speed = 2;
		getWalkFront().changeAnimationSpeed(4); // 0
		getWalkBack().changeAnimationSpeed(4); // 1
		getWalkLeft().changeAnimationSpeed(4); // 2
		getWalkRight().changeAnimationSpeed(4); // 3
	}
	
	public void slow() {
		this.speed = 1;
		getWalkFront().changeAnimationSpeed(4); // 0
		getWalkBack().changeAnimationSpeed(4); // 1
		getWalkLeft().changeAnimationSpeed(4); // 2
		getWalkRight().changeAnimationSpeed(4); // 3
	}

	/********* Moves Character based on flags ******/
	public void updatePosRight() {
		
		if (posX < posXMax) {
			moveRight();
		}
	}

	public void updatePosLeft() {
		if (posX > posXMin) {
			moveLeft();
		}
	}

	public void updatePosUp() {
		if (this.posY > posYMin) {
			moveUp();
		}
	}

	public void updatePosDown() {
		if (this.posY < posYMax) {
			moveDown();
		}
	}

	/******* Call collision class (Not complete) ***********/

	/**************** Shooting fire/Ice functions ************/
	public void updateFireShot() {
		fireX = (int) posX;
		fireY = (int) posY;

		fireDirection = direction;

	}
	
	public void initFireShot() {
		initFireX = posX;
		initFireY = posY;
	}

	public void initIceShot() {
		initIceX = posX;
		initIceY = posY;
	}
	public void updateIceShot() {
		iceX = (int) posX;
		iceY = (int) posY;

		iceDirection = direction;
	}

	public Rectangle getFireBounds() {
		return new Rectangle((int) fireX + 70, (int) fireY + 20, fireAtk.getWidth(null), fireAtk.getHeight(null));
	}

	public Rectangle getIceBounds() {
		return new Rectangle((int) iceX + 70, (int) iceY + 20, iceAtk.getWidth(null), fireAtk.getHeight(null));
	}

	public void restart(int L) {
		if (L == 1) {
			direction = 0;
			this.setPosition(L);
			this.health = 200;
			this.energy = 200;
			this.points = 0;
		} else if (L == 2) {
			direction = 2;
			this.health = 200;
			this.energy = 200;
			this.setPosition(L);
		} else if (L == 3) {
			direction = 2;
			this.setPosition(L);
			this.health = 200;
			this.energy = 200;
		}	else if (L == 4) {
			direction = 2;
			this.setPosition(L);
		}

	}

	/********** Animation ****************/
	public void swordAnimation() {
		getAtkUp().runAnimation();
		getAtkDown().runAnimation();
		getAtkLeft().runAnimation();
		getAtkRight().runAnimation();
	}

	public void walkAnimation() {
		getWalkFront().runAnimation();
		getWalkBack().runAnimation();
		getWalkRight().runAnimation();
		getWalkLeft().runAnimation();

	}

	public void updateWalkAnimation() {
		int j = 0;
		for (int i = 1; i < 9; i++) {
			walkF[j] = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroD" + i + ".png")).getImage();
			walkB[j] = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroU" + i + ".png")).getImage();
			walkL[j] = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroL" + i + ".png")).getImage();
			walkR[j] = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroR" + i + ".png")).getImage();
			j++;
		}

		setWalkFront(new Animation(4, walkF)); // 0
		setWalkBack(new Animation(4, walkB)); // 1
		setWalkLeft(new Animation(4, walkL)); // 2
		setWalkRight(new Animation(4, walkR)); // 3
	}

	public void updateSwordAnimation() {
		for (int i = 0; i < 6; i++) {
			SwordU[i] = new ImageIcon(this.getClass().getResource("/assets/HeroSword/swordU" + i + ".png")).getImage();
			SwordD[i] = new ImageIcon(this.getClass().getResource("/assets/HeroSword/swordD" + i + ".png")).getImage();
			SwordL[i] = new ImageIcon(this.getClass().getResource("/assets/HeroSword/swordL" + i + ".png")).getImage();
			SwordR[i] = new ImageIcon(this.getClass().getResource("/assets/HeroSword/swordR" + i + ".png")).getImage();
		}

		setAtkUp(new Animation(1, SwordU));
		setAtkDown(new Animation(1, SwordD));
		setAtkLeft(new Animation(1, SwordL));
		setAtkRight(new Animation(1, SwordR));
	}

	public void updateStationarySprite() {
		Image heroFront = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroD0.png")).getImage();
		Image heroBack = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroU0.png")).getImage();
		Image heroRight = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroR0.png")).getImage();
		Image heroLeft = new ImageIcon(this.getClass().getResource("/assets/HeroWalk/heroL0.png")).getImage();
		this.spriteheroes[0] = heroFront;
		this.spriteheroes[1] = heroBack;
		this.spriteheroes[2] = heroRight;
		this.spriteheroes[3] = heroLeft;
	}

	public void updateShot() {
		fireAtk = new ImageIcon(this.getClass().getResource("/assets/Shoot/ballFire.png")).getImage();
		iceAtk = new ImageIcon(this.getClass().getResource("/assets/Shoot/ballIce.png")).getImage();
	
	}

	public Animation getWalkFront() {
		return walkFront;
	}

	public void setWalkFront(Animation walkFront) {
		this.walkFront = walkFront;
	}

	public Animation getWalkBack() {
		return walkBack;
	}

	public void setWalkBack(Animation walkBack) {
		this.walkBack = walkBack;
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

	public boolean isAtk() {
		return atk;
	}

	public void setAtk(boolean atk) {
		this.atk = atk;
	}

	public boolean isMoved() {
		return moved;
	}

	public void setMoved(boolean moved) {
		this.moved = moved;
	}

	public String getWeapon() {
		return weapon;
	}

	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}

	public Animation getAtkDown() {
		return atkDown;
	}

	public void setAtkDown(Animation atkDown) {
		this.atkDown = atkDown;
	}

	public Animation getAtkUp() {
		return atkUp;
	}

	public void setAtkUp(Animation atkUp) {
		this.atkUp = atkUp;
	}

	public Animation getAtkRight() {
		return atkRight;
	}

	public void setAtkRight(Animation atkRight) {
		this.atkRight = atkRight;
	}

	public Animation getAtkLeft() {
		return atkLeft;
	}

	public void setAtkLeft(Animation atkLeft) {
		this.atkLeft = atkLeft;
	}

	public int getBraceDelay() {
		return braceDelay;
	}

	public void setBraceDelay(int braceDelay) {
		this.braceDelay = braceDelay;
	}

	public int getAtkDelay() {
		return atkDelay;
	}

	public void setAtkDelay(int atkDelay) {
		this.atkDelay = atkDelay;
	}
	
	public void iceShot() {
		
	}
	
	public void updateShotPos() {
		if (fireDirection == 0) {
			fireY++;
		} else if (fireDirection == 1) {
			fireY--;
		} else if (fireDirection == 2) {
			fireX++;
		} else if (fireDirection == 3) {
			fireX--;
		}
		if (iceDirection == 0) {
			iceY++;
		} else if (iceDirection == 1) {
			iceY--;
		} else if (iceDirection == 2) {
			iceX++;
		} else if (iceDirection == 3) {
			iceX--;
		}
		
	}
	
	public Boolean checkFireLimits() {
		// Shot of fire should last about 200 metres
		if (fireX >= initFireX + 200
				|| fireX <= initFireX - 200
				|| fireY <= initFireY - 150
				|| fireY >= initFireY + 150) {
			// if 200 m, shot should not be drawn - flag
			shotF = false;
			return shotF;
		}
		return true;
	}
	
	public void checkIceLimits() {
		
	}
	
	public void updateIcePos() {
		
	}
	
	public void initShots() {
		if(shotType == 1) {
			if (!shotF) { // If it hasnt been shot, set initial position
				updateFireShot(); 
			}
		}
		if(shotType == 2) {
			if (!shotI) {
				updateIceShot();
			}
		}
	}


	public int getShotType() {
		return shotType;
	}

	public void setShotType(int shotType) {
		this.shotType = shotType;
	}

	public boolean isShot() {
		return shot;
	}

	public void setShot(boolean shot) {
		this.shot = shot;
	}

	public ArrayList<FireBlast> getFireBlast() {
		return fireBlast;
	}

	public void setFireBlast(ArrayList<FireBlast> fireBlast) {
		this.fireBlast = fireBlast;
	}
	

	public ArrayList<IceBlast> getIceBlast() {
		return iceBlast;
	}

	public void setIceBlast(ArrayList<IceBlast> iceBlast) {
		this.iceBlast = iceBlast;
	}

	public boolean isHealthLoss() {
		return healthLoss;
	}

	public void setHealthLoss(boolean healthLoss) {
		this.healthLoss = healthLoss;
	}

	public boolean isHealthGain() {
		return healthGain;
	}

	public void setHealthGain(boolean healthGain) {
		this.healthGain = healthGain;
	}

	public void setEnergy(int energy) {
		this.energy = energy;
		// TODO Auto-generated method stub
		
	}

	public IceBlast getIceAmmo() {
		return iceAmmo;
	}

	public void setIceAmmo(IceBlast iceAmmo) {
		this.iceAmmo = iceAmmo;
	}

	public FireBlast getFireAmmo() {
		return fireAmmo;
	}

	public void setFireAmmo(FireBlast fireAmmo) {
		this.fireAmmo = fireAmmo;
	}
	
	

}
