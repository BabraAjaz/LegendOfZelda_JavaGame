package present;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class Knight extends Enemy {

	static final int maxWalkImages = 9;
	static final int maxAtkImages = 4;
	static final int maxAttackImages = 4;
	private Image[] Up = new Image[maxWalkImages];
	private Image[] Down = new Image[maxWalkImages];
	private Image[] Left = new Image[maxWalkImages];
	private Image[] Right = new Image[maxWalkImages];
	private Image[] atkImg = new Image[maxAttackImages];
	private final int timeTillSlowDown = 600;
	private final int attack2Delay = 0;
	private int entryDirection;
	boolean completeEntry;
	
	private static final int attackCharge = 500;
	private static final int abilityCharge = 1000;
	private int timeTillAttack = attackCharge;
	private int timeTillAbility = abilityCharge;
	private boolean ability = false;
	
	int timeTillFight;
	int attackDelay = 0;
	private double energy;
	final int disableCharge = 500;
	int timeTillDisableBracelet = disableCharge;
	private boolean disableHero = false;

	public Knight(Hero target, int posX, int posY, int stage, int entry, int delaytillFight) {
		super(target);
		this.posX = posX;
		this.posY = posY;
		startX = posX;
		startY = posY;
		type = 3;
		direction = 0;
		speed = 1;
		health = 120;
		energy = 60;
		this.entryDirection = entry;
		timeTillFight = delaytillFight;

		loadIdleImages();
		loadAtkImages();
		width = 62;
		height = 62;


	}

	public void loadIdleImages() {
		for (int i = 0; i < maxWalkImages; i++) {
			Up[i] = new ImageIcon(this.getClass().getResource("/assets/KnightWalk/up" + i + ".png")).getImage();
			Down[i] = new ImageIcon(this.getClass().getResource("/assets/KnightWalk/down" + i + ".png")).getImage();
			Left[i] = new ImageIcon(this.getClass().getResource("/assets/KnightWalk/left" + i + ".png")).getImage();
			Right[i] = new ImageIcon(this.getClass().getResource("/assets/KnightWalk/right" + i + ".png")).getImage();
		}
	}

	public void loadAtkImages() {
		for (int i = 0; i < maxAtkImages; i++) {
			atkImg[i] = new ImageIcon(this.getClass().getResource("/assets/KnightAttack/atk" + i + ".png")).getImage();

		}
	}


	public void path() {

		if (!completeEntry) {
			enterScreen();
		} else if (alive) {
			speed = 1;
			updateTarget();
			attackDelay();
			updateTimeTillDisable();
			if (energy < 300) {
				energy += 1;
				//System.out.println(energy);
				if (energy == 300) {
				}
			} else {
				if (targetX <= posX + 40 && targetX >= posX - 40 && targetY < posY + 40 && targetY > posY - 40) {
					energy = 0;
				} else {
					if (timeTillDisableBracelet < 100) {
						setAbility(true);
					} else {
						setAbility(false);
					}

					if (targetX < posX - 20) {
						direction = 3;
						if (targetX < posX + 20) {
							moveLeft();
							if (targetY > posY) {
								direction = 0;
								if (targetY > posY) {
									moveDown();
								}
							} else if (targetY < posY) {
								direction = 1;
								if (targetY < posY) {
									moveUp();
								}
							}

						}
					}

					if (targetX > posX - 20) {
						direction = 2;
						if (targetX > posX + 20) {
							moveRight();
							if (targetY > posY) {
								direction = 0;
								if (targetY > posY) {
									moveDown();
								}
							} else if (targetY < posY) {
								direction = 1;
								if (targetY < posY) {
									moveUp();
								}
							}

						}
					}
					if (targetX == posX) {
						if (targetY < posY) {
							direction = 1;
							moveUp();
						}
						if (targetY > posY) {
							direction = 0;
							moveDown();
						}

					}
					if (targetY == posY) {
						if (targetX < posX) {
							direction = 3;
							moveLeft();
						}
						if (targetX > posX) {
							direction = 2;
							moveRight();
						}

					}
				}

			}
		}
	}


	public Rectangle[] getAttackBounds() {
		Rectangle[] attack = new Rectangle[1];
		attack[0] = new Rectangle((int)posX,(int)posY,atkImg[0].getWidth(null),(atkImg[0].getHeight(null)));
		return attack;
	}


	public void setAbility(boolean isSlow) {
		disableHero = isSlow;

	}

	public boolean getAbility() {
		return disableHero;
	}

	public void attackDelay() {
		if (attackDelay > 0 && attackDelay < 500) {
			attackDelay++;
			if (attackDelay == 500) {
				attackDelay = 0;
			}
		}
	}

	public void updateTimeTillDisable() {
		timeTillDisableBracelet--;
		if (timeTillDisableBracelet == 0) {
			timeTillDisableBracelet = abilityCharge;
		}

	}

	public void abilityCharge() {
		setTimeTillAbility(getTimeTillAbility() - 1);

	}

	public void delayTillFight() {
		timeTillFight--;

	}
	
	public void renderBounds2(Graphics g) {
		g.drawRect((int)posX, (int)posY, width,height);
	}



	public void enterScreen() {
		if (entryDirection == 0 && !completeEntry) {
			speed = 2;
			moveDown();
			if (this.posY == 100) {
				speed = 0;
				completeEntry = true;
			}
		} else if (entryDirection == 2 && !completeEntry) {
			speed = 2;
			moveRight();
			if (this.posX == 100) {
				speed = 0;
				completeEntry = true;
			}
		} else if (entryDirection == 3 && !completeEntry) {
			speed = 2;
			moveLeft();
			if (this.posX == 1200) {
				speed = 0;
				completeEntry = true;
			}
		} else if (entryDirection == 1 && !completeEntry) {
			speed = 2;
			moveUp();
			if (this.posY == 600) {
				speed = 0;
				completeEntry = true;

			}
		}
	}

	public void updateTarget() {
		this.targetX = this.hero.getPosX();
		this.targetY = this.hero.getPosY();
	}

	public Image[] getDown() {
		return Down;
	}

	public void setDown(Image[] down) {
		Down = down;
	}

	public boolean getAttack() {
		return false;
	}

	public Image[] getAtkImg() {
		return atkImg;
	}

	public void setAtkImg(Image[] atkImg) {
		this.atkImg = atkImg;
	}

	public int getTimeTillSlowDown() {
		return timeTillSlowDown;
	}

	public int getAttack2Delay() {
		return attack2Delay;
	}

	public int getTimeTillAttack() {
		return timeTillAttack;
	}

	public void setTimeTillAttack(int timeTillAttack) {
		this.timeTillAttack = timeTillAttack;
	}

	public int getTimeTillAbility() {
		return timeTillAbility;
	}

	public void setTimeTillAbility(int timeTillAbility) {
		this.timeTillAbility = timeTillAbility;
	}

}
