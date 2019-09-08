package present;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class Boss extends Enemy {

	private int energy = 400;
	private Image bossImg = new ImageIcon(this.getClass().getResource("/assets/BossWalk/BossDown0.png")).getImage();
	private ArrayList<String> powers = new ArrayList<String>
	(Arrays.asList("blockAtk","recover","blockBracelet"));
	static final int atkCharge = 1250;
	static final int boostCharge = 500;
	static final int fireCharge = 750;
	static final int iceCharge = 1250;
	static final int disableCharge = 3000;
	static final int relativeToHeroX = 300;
	static final int relativeToHeroY = 300;
	private int count = 0;
	private int direction = 0;
	private int tempIceXLocation;
	private int tempIceYLocation;
	private int tempIceWidth = 200;
	private int tempIceHeight = 200;
	private int maxIceWidth = 400;
	private int maxIceHeight = 400;

	private boolean attack = false;
	private boolean fireBlast = false;
	private boolean iceBlast = false;
	private boolean disableHero = false;
	private boolean boostSpeed = false;


	// Boss Abilities - Once the boss is ready, one of these abilities activate
	private int timeTillFireBlast = fireCharge;
	private int timeTillIceBlast = iceCharge;
	private int timeTillDisableBracelet = disableCharge;
	private int timeTillPhysicalAttack = atkCharge;
	private int timeTillBoostSpeed = boostCharge;


	public Boss(Hero hero, int startX, int startY) {
		super(hero);
		type = 5;
		posX = startX;
		posY = startY;
		this.startX = startX;
		this.startY = startY;
		targetX =  hero.getPosX();
		targetY =  hero.getPosY();
		speed = 1;
		health = 400;
		width = bossImg.getWidth(null);
		height = bossImg.getHeight(null);

	}


	// The Boss AI works by moving towards the target at a speed less than the hero, 
	// However, with the boss's special abilities, it can be difficult to beat.
	// Boss can speed up, when boost speed is activated
	// Boss can shoot fire when fire blast is activated
	// Boss can slow down hero when ice blast is activated
	// timeTill functions simply count down till the boss is able to 
	// use them. The boss utilizes energy to carry out these actions too,
	// when energy drops the boss is unable to move
	// and utilizes 
	public void path() {
		if (alive) {
			updateTarget();
			updateTimeTillAtk();
			if (energy < 0) {
				setFireBlast(false);
				setIceBlast(false);
				setBoostSpeed(false);
				energy--;
				speed = 0;
				if (energy == -100) {
					energy = 400;
				}
			}
			else {
				if (timeTillBoostSpeed <50) {
					setBoostSpeed(true);
					energy-=1;
					speed = 6;
				}
				else {
					speed = 1;
					setBoostSpeed(false);
				}

				if (getTimeTillFireBlast() < 100) {
					energy -=1;
					speed = 0;
					setFireBlast(true);

				}
				else {
					setFireBlast(false);
				}

				if (getTimeTillIceBlast() < 100) {
					speed = 2;
					energy-=1;
					setIceBlast(true);
				}
				else {
					setIceBlast(false);
				}

				if (timeTillDisableBracelet <50) {
					// play sound
					setDisableHero(true);
					energy-=1;
					speed = 0;
				}
				else {
					setDisableHero(false);
				}


				if (targetX < posX) {
					if (targetX < posX-100) {
						moveLeft();
						attack = false;
					}
					else {
						moveLeft();
						attack = true;

					}
				}
				if (targetX > posX) {
					if (targetX > posX +100) {

						attack = false;
						moveRight();
					}
					else {
						moveRight();
						attack = true;

					}
				}
				if (targetY > posY) {
					if (targetY > posY + 100) {
						attack = false;
						moveDown();
					}
					else {
						moveDown();
						attack = true;
					}

				}
				if (targetY < posY) {
					if (targetY <posY - 100) {
						attack = false;
						moveUp();
					} else {
						moveUp();
						attack = true;
					}

				}
			}
		}






	}
	
	// helper function for collision of fire attack
	public Rectangle[] getAttackBounds() {
		Rectangle[] fire = new Rectangle[2];
		if (fireBlast) {
		fire[0] = new Rectangle(0, (int)posY, 1376, 40);
		fire[1] = new Rectangle((int)posX+60,0, 50,768);
		System.out.println("fireblast");
		} else {
			fire[0] = new Rectangle(0,0,0,0);
			fire[1] = new Rectangle(0,0,0,0);
		}
		return fire;
		
	}

	// helper function for collision of boss
	public Rectangle getBounds() {
		return new Rectangle((int)posX+60, (int)posY+25, 50,50);
	}


	public void randomizeLocation() {
		setTempIceXLocation(new Random().nextInt(1377));
		setTempIceYLocation(new Random().nextInt(769));

	}


	public void updateTarget() {
		targetX = hero.getPosX();
		targetY = hero.getPosY();

	}


	public Image getBossImg() {
		return bossImg;
	}


	public void setBossImg(Image bossImg) {
		this.bossImg = bossImg;
	}

	// updates the delay between the boss's attacks
	public void updateTimeTillAtk() {
		setTimeTillFireBlast(getTimeTillFireBlast() - 1);
		setTimeTillIceBlast(getTimeTillIceBlast() - 1);
		timeTillDisableBracelet-=1;
		timeTillPhysicalAttack-=1;
		timeTillBoostSpeed-=1;
		if (getTimeTillFireBlast() == 0) {
			setTimeTillFireBlast(fireCharge);
		}
		if (getTimeTillIceBlast() == 0) {
			setTimeTillIceBlast(iceCharge);
		}
		if (timeTillDisableBracelet == 0) {
			timeTillDisableBracelet = disableCharge;
		}
		if (timeTillPhysicalAttack == 0) {
			timeTillPhysicalAttack = atkCharge;
		}
		if (timeTillBoostSpeed == 0) {
			timeTillBoostSpeed = boostCharge;
		}
	}


	public int getHealth() {
		return health;
	}


	public void setHealth(int health) {
		this.health = health;
	}


	public int getEnergy() {
		return energy;
	}


	public boolean isDisableHero() {
		return disableHero;
	}


	public void setDisableHero(boolean disableHero) {
		this.disableHero = disableHero;
	}


	public boolean isBoostSpeed() {
		return boostSpeed;
	}


	public void setBoostSpeed(boolean boostSpeed) {
		this.boostSpeed = boostSpeed;
	}


	public boolean isIceBlast() {
		return iceBlast;
	}


	public void setIceBlast(boolean iceBlast) {
		this.iceBlast = iceBlast;
	}


	public boolean isFireBlast() {
		return fireBlast;
	}


	public void setFireBlast(boolean fireBlast) {
		this.fireBlast = fireBlast;
	}


	public boolean isAttack() {
		return attack;
	}


	public void setAttack(boolean attack) {
		this.attack = attack;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public int getDirection() {
		return direction;
	}


	public void setDirection(int direction) {
		this.direction = direction;
	}


	public int getTempIceXLocation() {
		return tempIceXLocation;
	}


	public void setTempIceXLocation(int tempIceXLocation) {
		this.tempIceXLocation = tempIceXLocation;
	}


	public int getTempIceYLocation() {
		return tempIceYLocation;
	}


	public void setTempIceYLocation(int tempIceYLocation) {
		this.tempIceYLocation = tempIceYLocation;
	}


	public int getTempIceWidth() {
		return tempIceWidth;
	}


	public void setTempIceWidth(int tempIceWidth) {
		this.tempIceWidth = tempIceWidth;
	}


	public int getTempIceHeight() {
		return tempIceHeight;
	}


	public void setTempIceHeight(int tempIceHeight) {
		this.tempIceHeight = tempIceHeight;
	}


	public int getMaxIceWidth() {
		return maxIceWidth;
	}


	public void setMaxIceWidth(int maxIceWidth) {
		this.maxIceWidth = maxIceWidth;
	}


	public int getMaxIceHeight() {
		return maxIceHeight;
	}


	public void setMaxIceHeight(int maxIceHeight) {
		this.maxIceHeight = maxIceHeight;
	}


	public int getTimeTillFireBlast() {
		return timeTillFireBlast;
	}


	public void setTimeTillFireBlast(int timeTillFireBlast) {
		this.timeTillFireBlast = timeTillFireBlast;
	}


	public int getTimeTillIceBlast() {
		return timeTillIceBlast;
	}


	public void setTimeTillIceBlast(int timeTillIceBlast) {
		this.timeTillIceBlast = timeTillIceBlast;
	}


}





