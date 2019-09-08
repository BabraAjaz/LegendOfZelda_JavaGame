package present;

import java.awt.Rectangle;
import java.util.ArrayList;

public class Enemy extends Character {

	
	protected int type = 0;
	private Collisions attack = new Collisions();
	private Model data;
	protected Hero hero;
	protected double targetX;
	protected double targetY;
	protected int health;
	protected int startX;
	protected int startY;

	// 1: Dog
	// 2: Bomb
	// 3: Drone
	// 4: Gold Knights
	// 5: Boss
	public Enemy(Hero target) {
		this.hero = target;
		
		// data = model;
	}

	public void death() {
		if (!alive) {
			posX = 0;
			posY = 0;
		}
	}
	
	public void restart() {
		alive = true;
		posX = startX;
		posY = startY;

	}

	public void enemyCollisions(ArrayList<Enemy> enemy, int stage) {
		for (int i = 0; i < enemy.size(); i++) {
			if (enemy.get(i).type == 2 && stage == 2) {
				if (attack.checkCollision(hero.getBounds(), enemy.get(i).getBounds()) && hero.getAtkDelay() == 0) {
					hero.explode();
					hero.setHealthLoss(true);
					hero.setKeepHealth(false);
					enemy.get(i).setAlive(false);
					enemy.get(i).death();
					//System.out.println("EXPLODE");
					hero.setAtkDelay(hero.getAtkDelay() + 1);
					enemy.remove(i);
					return;
				}
				for (int j = 0; j < hero.getIceBlast().size(); j++) {
					if (attack.checkCollision(hero.getIceBlast().get(j).getBounds()
							, enemy.get(i).getBounds())) {
						enemy.get(i).setAlive(false);
						enemy.get(i).death();
						hero.getIceBlast().clear();
						hero.setAtkDelay(hero.getAtkDelay() + 1);
						enemy.remove(i);
						return;
					}
				}
				for (int j = 0; j < hero.getFireBlast().size(); j++) {
					if (attack.checkCollision(hero.getFireBlast().get(j).getBounds()
							, enemy.get(i).getBounds())) {
						hero.updateHealth(-100);
						hero.setHealthLoss(true);
						enemy.get(i).setAlive(false);
						enemy.get(i).death();
						hero.getFireBlast().clear();
						hero.setAtkDelay(hero.getAtkDelay() + 1);
						enemy.remove(i);
						return;
					}
				}
				if (attack.checkCollision(hero.swordBounds(), enemy.get(i).getBounds())
						&& hero.getAtkDelay() == 0) {
					hero.updateHealth(-50);
					hero.setHealthLoss(true);
					hero.setKeepHealth(false);
					enemy.get(i).setAlive(false);
					enemy.get(i).death();
					hero.setAtkDelay(hero.getAtkDelay() + 1);
				}
			}
			if (enemy.get(i).type == 1 && stage == 1) {
				if (attack.checkCollision(hero.swordBounds(), enemy.get(i).getBounds())
						&& hero.getAtkDelay() == 0) {
					enemy.get(i).setAlive(false);
					enemy.get(i).death();
					hero.setAtkDelay(hero.getAtkDelay() + 1);
					enemy.remove(i);
					return;

				}
				else if (attack.checkCollision(hero.getBounds(), enemy.get(i).getBounds())
							&& hero.getAtkDelay() == 0)  {
					if (!hero.getKeepHealth()) {
					hero.updateHealth(-40);
					hero.setHealthLoss(true);
					}
					hero.setKeepHealth(false);
					hero.setAtkDelay(hero.getAtkDelay() + 1);
					}
				}
			if (enemy.get(i).type == 5 && stage == 4) {
				if (attack.checkCollision(hero.swordBounds(), enemy.get(i).getBounds()) 
						&& hero.getAtkDelay() == 0) {
					//System.out.println("slashed");
					enemy.get(i).health-=40;
					hero.setAtkDelay(hero.getAtkDelay() + 1);
					if (enemy.get(i).health <= 0 ) {
						enemy.get(i).setAlive(false);
						enemy.get(i).death();
						enemy.remove(i);
						return;
					}
				}
					for (int j = 0; j < hero.getFireBlast().size(); j++) {
						if (attack.checkCollision(hero.getFireBlast().get(j).getBounds(), enemy.get(i).getBounds())
								&& hero.getAtkDelay() == 0) {
							enemy.get(i).health-=30;
							hero.getFireBlast().clear();
							hero.setAtkDelay(hero.getAtkDelay() + 1);
							if (enemy.get(i).health <= 0 ) {
								enemy.get(i).setAlive(false);
								enemy.get(i).death();
								enemy.remove(i);
								return;
							}
						}
					}
					for (int j = 0; j < hero.getIceBlast().size(); j++) {
						if (attack.checkCollision(hero.getIceBlast().get(j).getBounds(), enemy.get(i).getBounds())
								&& hero.getAtkDelay() == 0) {
							enemy.get(i).health-=20;
							hero.getIceBlast().clear();
							hero.setAtkDelay(hero.getAtkDelay() + 1);
							if (enemy.get(i).health <= 0 ) {
								enemy.get(i).setAlive(false);
								enemy.get(i).death();
								enemy.remove(i);
								return;
							}
						}
					}
					
					if (attack.checkCollision(hero.getBounds(), enemy.get(i).getBounds())
							&& hero.getAtkDelay() == 0) {
						if (!hero.getKeepHealth()) {
						hero.updateHealth(-20);
						hero.setHealthLoss(true);
						}
						hero.setKeepHealth(false);
						//System.out.println("EXPLODE");
						hero.setAtkDelay(hero.getAtkDelay() + 1);
					}
					if (attack.checkCollision(hero.getBounds(), enemy.get(i).getAttackBounds()[0])
							&& hero.getAtkDelay() == 0) {
						if (!hero.getKeepHealth()) {
						hero.updateHealth(-40);
						hero.setHealthLoss(true);
						}
						
						hero.setKeepHealth(false);
						//System.out.println("EXPLODE");
						hero.setAtkDelay(hero.getAtkDelay() + 1);
					}
					if (attack.checkCollision(hero.getBounds(), enemy.get(i).getAttackBounds()[1])
							&& hero.getAtkDelay() == 0) {
						if (!hero.getKeepHealth()) {
						hero.updateHealth(-40);
						hero.setHealthLoss(true);
						}
						
						hero.setKeepHealth(false);
						//System.out.println("EXPLODE");
						hero.setAtkDelay(hero.getAtkDelay() + 1);
					}
					

			}
		}
	}
	
	
	public Rectangle[] getAttackBounds() {
		Rectangle[] atk = new Rectangle[2];
		atk[0] = new Rectangle(0,0,0,0);
		atk[1] = new Rectangle(0,0,0,0);
		return atk;
		
	}
	
}

