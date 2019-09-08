package present;

import java.awt.*;
//import java.awt.image.BufferStrategy;

import javax.swing.*;
import java.util.*;

public class Controller implements Runnable {

	boolean loop = true;
	Collisions bam = new Collisions();

	// Make Instance of View and Model
	public Model model;
	public View UI;
	public Audio audio = new Audio();
	private Thread thread;
	private int delay = 0;
	private int timer = 0;
	int count = 0;
	int counter = 0;
	int soundDelay = 0;
	boolean pickUpsCheck = false;
	@Override
	public void run() {

		long prevTime = System.nanoTime();
		double targetTime = 1000000000 / 60;
		double delta = 0;
		long timer = System.currentTimeMillis(); // debugger
		int updates = 0; // debugger
		int frames = 0; // debugger

		// Main Game Loop
		while (loop) {

			long currentTime = System.nanoTime();
			delta += (currentTime - prevTime) / targetTime;
			prevTime = currentTime;
			while (delta >= 1) { // If the time difference is at least 16.6ms

				// Update Model
				Updates();
				Render();
				updates++;
				delta--;
			}

			// Minimize CPU power
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}



		}

	}

	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
	}

	// Constructor
	public Controller(Model model, View view) {
		this.model = model;
		this.UI = view;
		start();
	}

	/******* Updates Model **********/
	public void Updates() {
		updateView();
		updateScore();
		updatePickUps();
		checkCollisions();

		if (!(UI.returnP())) {
			updateCharacter();
			updateEnemy();
			updateSound();
			//model.audio.playSound();
			model.updateFireBlast();
			model.updateIceBlast();
		}
		else {
			updateNotifications();
		}

	}

	/******* RePaints after updating model *****/
	public void Render() {
		UI.repaint();
	}

	/******* Updates View ********/
	public void updateView() {

		if (UI.returnStage() == 0) {
			counter++;
			model.setStage(UI.stage);
			UI.hideGameScreen();
			UI.hidePause();
			UI.hideTopBar();
			UI.startScreen();
			model.getHero().restart(1);
			model.getCat().updatePosition();
		} 
		if (UI.returnStage() == 1) {
			model.setStage(UI.stage);
			UI.hideStartScreen();
			if (UI.topBarflag) {
				UI.topBar();
			}
			UI.addPause();
			UI.gamePlay();
			UI.topBar.setStage(1);
			UI.gamePlay.setCamX(1);

			// UI.repaint();
		} 
		if (UI.returnStage() == 2) {
			model.setStage(UI.stage);
			if (UI.setLoad == false) {
				UI.setLoad = true;
				UI.gamePlay.load = false;
			}
			if (UI.topBarflag) {
				UI.topBar.setStage(2);
			}
			UI.addPause();
			UI.gamePlay();
			UI.gamePlay.setCamX(1);
		} 
		if (UI.returnStage() == 3) {
			model.setStage(UI.stage);
			UI.addPause();
			UI.gamePlay();
			UI.topBar.setStage(3);
			if (UI.setLoad == false) {
				UI.setLoad = true;
				UI.gamePlay.load = false;
			}
			if (UI.move == true) {
				if (UI.gamePlay.load == true) {
					UI.gamePlay.setCamX(0);
					if (UI.gamePlay.transit) {
						model.getHero().restart(UI.stage+1);
						model.getCat().updatePosition();
					}
					UI.setStage(4);
				}
			}

		} 
		if (UI.returnStage() == 4) {
			model.setStage(UI.stage);
			UI.addPause();
			UI.gamePlay();
			UI.gamePlay.setCamX(0);
			UI.topBar.setStage(3);
			if (UI.setLoad == false) {
				UI.setLoad = true;
				UI.gamePlay.load = false;
			}
			if (UI.gameComp == true) {
				UI.addGameComp();
				UI.gameComp = false;
			}
		}

		//Whenever P == true
		if(UI.returnStage() > 0) {
			if (UI.returnP() == false) {
				UI.topBar.setTimeStart(1);
			} else if(UI.returnP() == true) {
				UI.topBar.setTimeStart(2);
			}
		}
	}

	public void updatePickUps() {
		model.loadPickUps();
		if(UI.stage == 4) {
			model.loadPickUps();
		}
		pickUpsCheck = true;
	}

	@SuppressWarnings("static-access")
	public void updateScore() {
		if (UI.scoreUpdate == 1) {
			model.getRecords().writeToFile(model.getHero().getPoints(),model.getHero().getName());
			UI.scoreUpdate = 0;
		}
		if (UI.scoreUpdate == 2) {
			model.getRecords().fileReader();
			count++;
			System.out.println(count);
			UI.scoreUpdate = 0;
			UI.score.scores = new String[model.getRecords().getScore().size()];
			for (int i = 0; i < model.getRecords().getScore().size(); i++) {				
				UI.score.scores[i] = model.getRecords().getScore().get(i); 
			}

			UI.scoreUpdate = 0;
		}
	}

	///shoots fire or ice only after stage 2 and depending on the type of flower the player has collected
	public void addShot() {
		if (UI.stage >= 2) {
			if (UI.shoot == true) {

				if(model.getHero().getShotType()==2) {
					if (model.ready == true) {

						model.addIce(new IceBlast(model.getHero().getPosX(),model.getHero().getPosY(),
								model.getHero().getDirection()));
					}
					model.ready = false;
				}

				if (model.getHero().getShotType() == 1) {
					if (model.ready == true) {
						model.getHero().getFireBlast().clear();
						model.addFire(new FireBlast(model.getHero().getPosX(),model.getHero().getPosY(),
								model.getHero().getDirection()));
					}
					model.ready = false;
				}
				UI.shoot = false;
			}
		}

	}

	public void updateSound() {
		if (UI.sound) {
			if (UI.playButtonSound) {
				audio.getClickSound().play();
				UI.playButtonSound = false;
			}
			if (UI.playStartSound) {
			}
			if (UI.playlevel1WalkSound && soundDelay == 0) {
				UI.playlevel1WalkSound = false;
				soundDelay++;
			}


			if (UI.playSwordSound && soundDelay == 0) {
				audio.getSwordSlashSound().play();

				soundDelay++;
				UI.playSwordSound = false;
			}
			soundDelay();
		}
	}

	public void soundDelay() {
		if (soundDelay > 0 && soundDelay <=20) {
			soundDelay++;
			if (soundDelay == 20) {
				soundDelay = 0;
			}
		}
	}

	/********** Updates Character Logic ***********/
	public void updateEnemy() {
		if (UI.stage == 1) {
			for (int i = 0; i < model.getDogs().size();i++) {
				model.getDogs().get(i).path();
			}
		}
		if (UI.stage == 2) {
			for (int i = 0; i < model.getBombs().size(); i++) {
				model.getBombs().get(i).path();
				model.getBombs().get(i).updateTarget(model.getHero());

			}
		}
		if (UI.stage == 3) {
			for (int i = 0; i < model.getKnights().size(); i++) {
				model.getKnights().get(i).path();
			}
		}

		if (UI.stage == 4) {
			model.getBoss().path();
		}

		if (model.getBoss().getHealth() <= 0) {
			UI.addGameComp();
		}
	}

	public void updateCharacter() {
		model.getHero().setNormalLimits();
		// Update Walking Animation
		model.getHero().walkAnimation();

		// Update Sword Attacking animation
		model.getHero().swordAnimation();

		// Updates cat Walking animation
		model.getCat().runWalkAnimation();
		model.getCat().path();

		///// There is movement bug - pressing all 3 arrows make animation run!
		//// Fix after finishing game skeletal
		////// Moving up
		if (UI.moveUp == true && UI.moveRight == false && UI.moveLeft == false && UI.moveDown == false) {
			model.getHero().setMoved(true); // moved setter
			model.getHero().changeDirection(1); //
			model.getHero().updatePosUp();
		}

		// Moving down
		if (UI.moveUp == false && UI.moveRight == false && UI.moveLeft == false && UI.moveDown == true) {
			model.getHero().setMoved(true); // moved setter
			model.getHero().changeDirection(0);
			model.getHero().updatePosDown();
		}

		/////// Moving left
		if (UI.moveUp == false && UI.moveRight == false && UI.moveLeft == true && UI.moveDown == false) {
			model.getHero().setMoved(true); // moved setter
			model.getHero().changeDirection(3);
			model.getHero().updatePosLeft();
		}

		////// Moving Right
		if (UI.moveUp == false && UI.moveRight == true && UI.moveLeft == false && UI.moveDown == false) {
			model.getHero().setMoved(true);
			model.getHero().changeDirection(2);
			model.getHero().updatePosRight();
		}

		///// Moving up and Right
		if (UI.moveUp == true && UI.moveRight == true && UI.moveLeft == false && UI.moveDown == false) {
			model.getHero().setMoved(true);
			model.getHero().changeDirection(1);
			model.getHero().updatePosUp();
			model.getHero().updatePosRight();
		}

		/////// Moving up and left
		if (UI.moveUp == true && UI.moveRight == false && UI.moveLeft == true && UI.moveDown == false) {
			model.getHero().setMoved(true);
			model.getHero().changeDirection(1);
			model.getHero().updatePosUp();
			model.getHero().updatePosLeft();
		}

		////// Moving down and right
		if (UI.moveUp == false && UI.moveRight == true && UI.moveLeft == false && UI.moveDown == true) {
			model.getHero().setMoved(true);
			model.getHero().changeDirection(0);
			model.getHero().updatePosDown();
			model.getHero().updatePosRight();

		}

		/////// Moving down and left
		if (UI.moveUp == false && UI.moveRight == false && UI.moveLeft == true && UI.moveDown == true) {
			model.getHero().setMoved(true);
			model.getHero().changeDirection(0);
			model.getHero().updatePosDown();
			model.getHero().updatePosLeft();
		}

		if (UI.moveUp == false && UI.moveRight == false && UI.moveLeft == false && UI.moveDown == false) {
			model.getHero().setMoved(false); // Moved setter
		}

		if (UI.recover && model.getHero().getBraceDelay() == 0) {
			model.getBrace().recoverHP();
			if (UI.sound) {
				audio.getHealthGainSound().play();
			}
			UI.recover = false;
			model.getHero().setBraceDelay(model.getHero().getBraceDelay() + 1);
		}
		if (UI.protect && model.getHero().getBraceDelay() == 0) {
			model.getBrace().shield();
			if (UI.sound) {
				audio.getShield().play();
			}
			//audio.playMusic("shield");
			UI.protect = false;
			model.getHero().setBraceDelay(model.getHero().getBraceDelay() + 1);
		}

		if (model.getHero().getHealth() <= 0) {
			UI.addGameover();
		}

		if (model.getHero().getHealth() <= 0) {
			UI.addGameover();
		}

		addShot();
		model.getBrace().updateShield(model.getHero()); // Less coupling with just x and y?
		attackDelay();
		braceletDelay();
		restart();

		if (model.getBoss().isIceBlast()) {
			model.getHero().slow();
		}
		else if (UI.fastRun) {
			model.getHero().run();
		}
		else if (UI.normalRun){

			model.getHero().normal();
		}

		if (model.getBoss().isDisableHero() == true) {
			model.getHero().updateEnergy(-1);
		}

	}

	public void checkCollisions() {

		if (pickUpsCheck == true && UI.stage > 0 && model.getMap().getLoadComplete() == 3) {

			//only needs to happen once 
			model.entity.checkCollisions(model.getPickup(), model.getMap());

			//keep checking while game runs as characters and hero is dynamic
			model.entity.checkCollisions(model.getHero(), model.getPickup());
			model.entity.checkCollisions(model.getHero(), model.getMap(), UI.stage, model.getCharacter().getDirection());
		}

		if (UI.stage == 1) {
			int tempHealth = (int) model.getHero().getHealth();
			int tempEnemies = (int)model.enemyArmyList.size();
			model.enemy.enemyCollisions(model.enemyArmyList,1);
			if (tempHealth < model.getHero().getHealth()) {
				if (UI.sound)  {
					audio.getDamageTaken().play();
				}

			}
			else if(tempEnemies > model.enemyArmyList.size()) {
				if (UI.sound)  {
					audio.getAttackEnemy().play();
				}
			}
		}
		
		if (UI.stage == 2) {
			int tempHealth = (int) model.getHero().getHealth();
			int tempEnemies = model.enemyArmyList.size();
			model.enemy.enemyCollisions(model.enemyArmyList, 2);
			if (tempEnemies>model.enemyArmyList.size() ) {
				audio.getExplode().play();
			}
		}
		if(UI.stage == 4) {
			int tempHeroHealth = (int) model.getHero().getHealth();
			int tempBossHealth = (int) model.getBoss().getHealth();
			model.enemy.enemyCollisions(model.enemyArmyList, 4);
			if (tempHeroHealth < model.getHero().getHealth()) {
				if (UI.sound)  {
					audio.getDamageTaken().play();
				}
			}
			if (tempBossHealth < model.getBoss().getHealth()) {
				if (UI.sound)  {
					audio.getAttackEnemy().play();
				}
				
			}
		}
		attackDelay();


	}

	public void updateNotifications() {
		model.getHero().setHealthLoss(false);
	}

	/********* Restart inputs ****************/
	public void restart() {


		if (UI.restart) {
			model.reloadEnemies();
			for (int i = 0; i < model.enemyArmyList.size(); i++) {
				model.enemyArmyList.get(i).restart();
			}
			if (UI.stage == 1) {
				model.getHero().restart(UI.stage);
				model.getCat().updatePosition();
				UI.restart = false;
			} else if (UI.stage == 2) {
				model.getHero().restart(UI.stage);
				model.getCat().updatePosition();
				UI.restart = false;
			} else if (UI.stage == 3) {
				model.getHero().restart(UI.stage);
				model.getCat().updatePosition();
				UI.restart = false;
			} else if(UI.stage == 4) {
				model.getHero().setHealth(200);
				model.getHero().setEnergy(100);
				model.getHero().restart(UI.stage);
				model.getCat().updatePosition();
				UI.restart = false;
			}

		}

	}

	/*******************************************************************************/

	/**********************************
	 * Delays
	 ****************************************/

	public void braceletDelay() {
		if (model.getHero().getBraceDelay() <= 100 && model.getHero().getBraceDelay() > 0) {
			model.getHero().setBraceDelay(model.getHero().getBraceDelay() + 1);
			if (model.getHero().getBraceDelay() == 15) {
				model.getHero().setHealthGain(false);
			}
			if (model.getHero().getBraceDelay() == 100) {
				model.getHero().setBraceDelay(0);
			}
		}

	}

	public void attackDelay() {
		if (model.getHero().getAtkDelay() <= 50 && model.getHero().getAtkDelay() > 0) {
			model.getHero().setAtkDelay(model.getHero().getAtkDelay() + 1);
			if (model.getHero().getAtkDelay() == 15) {
				model.getHero().setHealthLoss(false);
			}
			if (model.getHero().getAtkDelay() == 50) {
				model.getHero().setAtkDelay(0);
			}
		}

	}

}
