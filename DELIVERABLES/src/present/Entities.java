package present;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Entities {
	
	//Entity collision interaction and performs functionality based on which entity it is

	Collisions bam = new Collisions();

	private Rectangle flowerBounds;
	private Rectangle flowerBoundsB;
	private Rectangle flowerBoundsBl;
	private Rectangle flowerBoundsR;
	private Rectangle flowerBoundsW;
	private int count = 0;
	
	private boolean load = false;
	private int actionDelay = 0;
	private boolean update = false;

	public Entities() {

	}

	public Rectangle getBounds() {
		return new Rectangle();
	}

	//a delay to delay the result of collisions
	public void entityDelay() {
		if (actionDelay > 0 && actionDelay < 100) {
			actionDelay++;
			if (actionDelay == 15) {
				update = true;
			}
			if (actionDelay == 100) {
				actionDelay = 0;
			}
		}
	}

	//checks collisions between the map and the player, if they walk into bounds they're not supposed to walk into
	//they lose health or can't move etc
	public void checkCollisions(Hero hero, Maps map, int stage, int direction) {
		entityDelay();
		
		if (stage == 1) {
			for (int i = 0; i < map.getBounds().size(); i++) { // should damage health
				if (bam.checkCollision(hero.getPredictedBounds(), map.getBounds().get(i))) {
					if (hero.direction == 0 && actionDelay == 0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

					else if (hero.direction == 2 && actionDelay == 0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

					else if (hero.direction == 1 && actionDelay == 0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

					else if (hero.direction == 3 && actionDelay == 0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

				}

				if (update == true) {
					hero.setHealthLoss(false);
					update = false;
				}
			}
			for (int i = 0; i < map.getObjects().size(); i++) {
				if (bam.checkCollision(hero.getPredictedBounds(), map.getObjects().get(i))) {
					if (hero.direction == 0) {
						hero.setBlockDown(true);
						// hero.updateHealth(-5);
					}

					else if (hero.direction == 2) {
						hero.setBlockRight(true);
						// hero.updateHealth(-5);
					}

					else if (hero.direction == 1) {
						hero.setBlockUp(true);
						// hero.updateHealth(-5);
					}

					else if (hero.direction == 3) {
						hero.setBlockLeft(true);
						// hero.updateHealth(-5);
					}
				}
			}
		}

		if (stage == 2) { // should block hero;
			for (int i = 0; i < map.getBounds().size(); i++) {
				if (bam.checkCollision(hero.getPredictedBounds(), map.getBounds().get(i))) {
					if (hero.direction == 0) {
						hero.setBlockDown(true);
					}

					else if (hero.direction == 2) {
						hero.setBlockRight(true);
					}

					else if (hero.direction == 1) {
						hero.setBlockUp(true);
					}

					else if (hero.direction == 3) {
						hero.setBlockLeft(true);
					}
				}
			}
		}

		if (stage == 3) {
			for (int i = 0; i < map.getBounds().size(); i++) { // should damage health
				if (bam.checkCollision(hero.getPredictedBounds(), map.getBounds().get(i))) {
					if(hero.direction == 0 && actionDelay ==0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

					else if(hero.direction == 2 && actionDelay ==0) {

						hero.updateHealth(-5);
						hero.setHealthLoss(true);

						actionDelay++;
					}

					else if(hero.direction == 1 && actionDelay ==0) {
						hero.updateHealth(-5);
						hero.setHealthLoss(true);
						actionDelay++;
					}

					else if(hero.direction == 3 && actionDelay ==0) {
						hero.updateHealth(-5);
						hero.setHealthLoss(true);
						actionDelay++;
					}
				}

				if (update == true) {
					hero.setHealthLoss(false);
					update = false;
				}
			}
			for (int i = 0; i < map.getObjects().size(); i++) {
				if (bam.checkCollision(hero.getPredictedBounds(), map.getObjects().get(i))) {
					if (hero.direction == 0) {
						hero.setBlockDown(true);
					}

					else if (hero.direction == 2) {
						hero.setBlockRight(true);
					}

					else if (hero.direction == 1) {
						hero.setBlockUp(true);
					}

					else if (hero.direction == 3) {
						hero.setBlockLeft(true);
					}
				}
			}
		}

	}

	//interaction between the orange pickup flowers and the map
	// doesn't interact with flowers that aren't printed on the 
	//accessible part of the map
	public void checkCollisions(PickUps pickup, Maps map) {

		if (load == false) {
			/************** ORANGE **********************/
			for (int i = 0; i < pickup.getMaxOrange(); i++) {
				flowerBounds = new Rectangle(pickup.getRandomCol()[i] * 32, pickup.getRandomRow()[i] * 32, 16, 16);

				for (int j = 0; j < map.getBounds().size(); j++) {
					if (bam.checkCollision(flowerBounds, map.getBounds().get(j))) {
						pickup.getBadIndex()[i] = 1;

					}
				}

				for (int j = 0; j < map.getObjects().size(); j++) {
					if (bam.checkCollision(flowerBounds, map.getObjects().get(j))) {
						pickup.getBadIndex()[i] = 1;
					}
				}

			}

		}

		load = true;
	}

	//interaction between the hero and the pickups
	//setting flags for the view to read so the pickups 
	//have been collected by the hero disappear and 
	//the score increases
	public void checkCollisions(Hero hero, PickUps pickUp) {

		/**************** point flowers *************/
		for (int i = 0; i < pickUp.getMaxOrange(); i++) {
			if (pickUp.getBadIndex()[i] != 1) {
				flowerBounds = new Rectangle(pickUp.getRandomCol()[i] * 32, pickUp.getRandomRow()[i] * 32, 16, 16);

				if (pickUp.getCollide()[i] == 0) {
					if (bam.checkCollision(hero.getBounds(), flowerBounds)) {
						pickUp.getCollide()[i] = 1;
						pickUp.getRetrieved()[i] = 1;
					}
				}

				if (pickUp.getRetrieved()[i] == 1) {
					hero.setPoints();
					pickUp.getRetrieved()[i] = 0;
				}
			}
		}

		/********** depleting energy by 50 **********/
		for (int i = 0; i < pickUp.getMaxBlack(); i++) {
			flowerBoundsB = new Rectangle(pickUp.getRandomColB()[i] * 32, pickUp.getRandomRowB()[i] * 32, 16, 16);

			if (pickUp.getCollideB()[i] == 0) {
				if (bam.checkCollision(hero.getBounds(), flowerBoundsB)) {
					pickUp.getCollideB()[i] = 1;
					pickUp.getRetrievedB()[i] = 1;
				}
			}

			if (pickUp.getRetrievedB()[i] == 1) {
				hero.updateEnergy(-50);
				pickUp.getRetrievedB()[i] = 0;
			}
		}

		/************ increasing energy by 100 ************/
		flowerBoundsW = new Rectangle(pickUp.getRandomColW() * 32, pickUp.getRandomRowW() * 32, 16, 16);
		if (pickUp.getCollideW() == 0) {
			if (bam.checkCollision(hero.getBounds(), flowerBoundsW)) {
				pickUp.setCollideW(1);
				pickUp.setRetrievedW(1);
			}
		}

		if (pickUp.getRetrievedW() == 1) {
			hero.updateEnergy(100);
			pickUp.setRetrievedW(0);
		}

		/******** blue and red flowers ********/
		// ShotType 0 is nothing // 1 is Fire // 2 is Ice
		for (int i = 0; i < pickUp.getMaxBlue(); i++) {
			flowerBoundsBl = new Rectangle(pickUp.getRandomColBl()[i] * 32, pickUp.getRandomRowBl()[i] * 32, 16, 16);
			if (pickUp.getCollideBl()[i] == 0) {
				if (bam.checkCollision(hero.getBounds(), flowerBoundsBl)) {
					pickUp.getCollideBl()[i] = 1;
					pickUp.getRetrievedBl()[i] = 1;
				}
			}

			if (pickUp.getRetrievedBl()[i] == 1) {
				hero.setShotType(2);
				pickUp.getRetrievedBl()[i] = 0;
			}
		}

		for (int i = 0; i < pickUp.getMaxRed(); i++) {
			flowerBoundsR = new Rectangle(pickUp.getRandomColR()[i] * 32, pickUp.getRandomRowR()[i] * 32, 16, 16);
			if (pickUp.getCollideR()[i] == 0) {
				if (bam.checkCollision(hero.getBounds(), flowerBoundsR)) {
					pickUp.getCollideR()[i] = 1;
					pickUp.getRetrievedR()[i] = 1;
				}
			}

			if (pickUp.getRetrievedR()[i] == 1) {
				hero.setShotType(1);
				pickUp.getRetrievedR()[i] = 0;
			}
		}
	}

	//getters and setters to aid with
	//the privacy
	public boolean isLoad() {
		return load;
	}

	public void setLoad(boolean load) {
		this.load = load;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
