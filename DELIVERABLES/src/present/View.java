package present;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class View extends JFrame implements ActionListener, KeyListener {

	public static int screenWidth = 1376;
	public static int screenHeight = 864;
	Model data = new Model();
	Score records = new Score();

	//IntroScreen intro = new IntroScreen();
	StartScreen start = new StartScreen();
	GameScreen gamePlay;
	CutScenes cutscene = new CutScenes();

	PopUp gamecomp = new PopUp(0);
	PopUp pause = new PopUp(1);
	boolean pauseScreen = false;
	PopUp popUp = new PopUp(2);
	PopUp confirm = new PopUp(3);
	boolean confirmScreen = false;
	PopUp score = new PopUp(5);
	PopUp gameover = new PopUp(4);
	PopUp name = new PopUp(6);
	PopUp guide = new PopUp(7);

	TopBar topBar;
	String weapon = new String();
	Collisions bam = new Collisions();

	/************* Flags ***********/

	Boolean protect = false;
	Boolean namePop = false;
	Boolean recover = false;
	Boolean moveUp = false;
	Boolean moveDown = false;
	Boolean moveRight = false;
	Boolean moveLeft = false;
	Boolean open = false;
	Boolean restart = false;
	Boolean P = false;
	Boolean confirmed = false;
	Boolean move = false;
	Boolean setLoad = false;
	Boolean gameComp = false;
	Boolean topBarflag = false;
	static int scoreUpdate = 0;
	static int scoreload = 0;
	int stage = 0;
	boolean instructions = false;
	boolean playButtonSound = false;
	boolean playStartSound = false;
	boolean playlevel1WalkSound = false;
	boolean playSwordSound = false;
	boolean damageTaken = false;
	boolean normalRun = false;
	boolean fastRun = false;
	boolean music = true;
	boolean sound = true;
	boolean shoot = false;
	public View() {

	}

	public View(Model gameData) {

		data = gameData;
		gamePlay = new GameScreen(data);
		topBar = new TopBar(data);
		cutscene = new CutScenes();
		/******* Frame Initialization **********/
		this.setSize(screenWidth, screenHeight);
		this.setTitle("Before & After");
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setLayout(null);
		this.setVisible(true);
		this.add(cutscene);

		/******* Implementing KeyListener ******/
		addKeyListener(this);
		setFocusable(true);
		requestFocusInWindow();
		gamePlay.setstage(stage);
		//start.start.setMnemonic(KeyEvent.VK_C);
		// time.start();
		ButtonListening();
	}

	/*********NAME******/
	public void addName() {
		name.Layout();
		this.add(name);
		namePop = true;

	}

	public void hideName() {
		name.Hide();
	}

	/*******START SCREEN******/
	public void startScreen() {
		if (scoreload == 0) {
			scoreUpdate = 2;
			scoreload = 1;
		}
		start.setVisible(true);
		this.add(start);
		gamePlay.cutscene = 1;
	}

	public void hideStartScreen() {
		start.setVisible(false);
	}

	/**********GAMEOVER*******/
	public void addGameover() {
		scoreUpdate = 1;
		gameover.Layout();
		this.add(gameover);
		P = true;
	}

	public void addGameComp() {
		scoreUpdate = 1;
		gamecomp.Layout();
		gamecomp.score.setText("Score: " + data.getHero().getPoints());
		gamecomp.add(score);
		this.add(gamecomp);
		P = true;
	}

	/************CONFIRM SCREEN*******/
	public void addConfirmScreen() {
		hidePause();
		confirm.Layout();
		this.add(confirm);
		confirmScreen = true;
	}

	/***GAMESCREEN****/
	public void gamePlay() {
		gamePlay.setVisible(true);
		this.add(gamePlay);
	}

	public void hideGameScreen() {
		gamePlay.setVisible(false);
	}

	/*****PAUSE********/
	public void addPause() {
		pauseScreen = true;
		this.add(pause);
	}

	public void hidePause() {
		pause.setVisible(false);
	}

	/******TOPBAR******/
	public void topBar() {
		this.add(topBar);
		topBar.setVisible(true);
	}

	public void hideTopBar() {
		topBar.setVisible(false);
	}

	/******POPUP*****/
	public void addPopUp() {
		popUp.Layout();
		this.add(popUp);
		P = true;
	}

	/******SCORE********/
	public void addScore() {
		score.Layout();
		this.add(score);
	}

	/******CUTSCENE******/

	public void addCutScene() {
		hideTopBar();
		cutscene.setStage(stage);
		cutscene.Layout();
		this.add(cutscene);
		P = true;
	}

	public void hideCutScene() {
		topBar();
		cutscene.Hide();
		cutscene.setCut(0);
		P = false;
		if(stage == 1 & instructions == false) {
			addInstructions();
			instructions = true;

		}
	}

	/*******INSTRUCTIONS****/
	public void addInstructions() {
		P = true;
		guide.Layout();
		this.add(guide);
	}

	public void hideInstructions() {
		P = false;
		guide.Hide();

	}
	/*********GETTERS AND SETTERS********/
	public int returnStage() {
		return stage;
	}

	public void setStage(int stage) {
		this.stage = stage;
	}

	public boolean returnP() {
		return P;
	}

	/******BUTTON LISTENING*****/

	public void ButtonListening() {
		// Button Listeners for start screen
		start.getStart().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addName();
				start.buttonHandling(false); // ADDED FOR TOPBAR - AZIZUL
				playButtonSound = true;
			}
		});

		start.getScore().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addScore();
				start.buttonHandling(false);
				playButtonSound = true;
			}
		});

		start.getExit().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				playButtonSound = true;
			}
		});

		// Button listeners for pause screen
		pause.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//stage = 0;
				P = false;
				addConfirmScreen();

				playButtonSound = true;
			}
		});

		pause.Resume.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pause.Hide();
				P = false;
				playButtonSound = true;
			}
		});

		pause.Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart = true;
				pause.Hide();
				P = false;
				playButtonSound = true;
			}
		});

		// Button LIsteners for transition screen
		popUp.Continue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P = false;
				if(stage == 1) {
					stage = 2;
					popUp.Hide();	
					data.getHero().setPosition(2);
					data.getCat().updatePosition();
					addCutScene();
				}
				else if (stage == 2) {
					stage = 3;
					setLoad = false;
					popUp.Hide();
					data.getHero().setPosition(3);
					data.getCat().updatePosition();
					addCutScene();
					playButtonSound = true;
				}
			}
		});

		popUp.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				popUp.Hide();
				addConfirmScreen();
				playButtonSound = true;
			}
		});

		// button listeners for continue screen
		confirm.Yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
				playButtonSound = true;

			}
		});
		confirm.No.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				P=false;
				confirm.Hide();
				playButtonSound = true;
			}
		});

		// button listeners game over
		gameover.Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// restart = true;
				P = false;
				data.getHero().restart(stage);
				gameover.Hide();
				restart = true;
				playButtonSound = true;

			}
		});
		gameover.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// restart = true;
				gameover.Hide();
				addConfirmScreen();
				playButtonSound = true;

			}
		});
		score.exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				start.buttonHandling(true);
				score.setVisible(false);
				playButtonSound = true;
			}

		});
		name.Continue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name.holder = name.Name.getText();

				data.getHero().setName(name.Name.getText());
				hideName();
				stage = 1;
				data.getHero().setShotType(0);
				topBar.stage = 1;
				addCutScene();

			}
		});

		gamecomp.Restart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				restart = true;
				pause.Hide();
				gamecomp.Hide();
				P = false;
				playButtonSound = true;
			}
		});
		gamecomp.Quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gamecomp.Hide();	 
				addConfirmScreen();
				playButtonSound = true;


			}
		});

	}

	// eventHandlers
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_S:
			if (data.getHero().getIceBlast().size() == 0) {
				data.ready = true; // make this a flag
			}

			if (data.getHero().getFireBlast().size() == 0) {
				data.ready = true; // make this a flag
			}

			break;
		case KeyEvent.VK_P:
			if (stage > 0) {
				P = true;
				pause.Layout();
			} else {
				P = false;
				pause.Hide();
			}
			break;
		case KeyEvent.VK_ESCAPE:
			if (!instructions) {
				P = false;
			}
			else {
				hideInstructions();
			}
			name.Hide();
			start.buttonHandling(true);
			pause.Hide();
			popUp.Hide();
			if (pauseScreen == true) {
				addConfirmScreen();
			}

			break;
		case KeyEvent.VK_UP:
			if (!data.getHero().isAtk()) {
				moveUp = true;
				playlevel1WalkSound= true;
			}

			break;
		case KeyEvent.VK_DOWN:
			if (!data.getHero().isAtk())
				// gamePlay.setCamY(0);
				moveDown = true;
			playlevel1WalkSound= true;
			// moveUp = false;
			break;
		case KeyEvent.VK_LEFT:
			if (!data.getHero().isAtk())
				moveLeft = true;
			playlevel1WalkSound= true;
			// gamePlay.setCamX(0);
			// moveRight = false;
			break;
		case KeyEvent.VK_RIGHT:
			if (!data.getHero().isAtk())
				moveRight = true;
			playlevel1WalkSound= true;
			// gamePlay.setCamX(1);
			// moveLeft = false;
			break;
		case KeyEvent.VK_SHIFT:
			fastRun = true; // set run flag for update
			normalRun = false;
			break;
		case KeyEvent.VK_1:
			data.getHero().setWeapon("Sword");
			break;
		case KeyEvent.VK_2:
			data.getHero().setWeapon("Gun");
			break;
		case KeyEvent.VK_Z:
			recover = true;
			break;
		case KeyEvent.VK_I:
			addInstructions();
			break;
		case KeyEvent.VK_U:
			hideInstructions();
			break;
		case KeyEvent.VK_X:
			protect = true;
			break;
		case KeyEvent.VK_SPACE:
			if(stage == 1) {
				if(cutscene.getCut() == 0) {
					cutscene.setCut(1);
				} 
				else if(cutscene.getCut() == 1) {
					cutscene.setCut(2);
				}
				else if(cutscene.getCut() == 2) {
					cutscene.setCut(3);
				} else if(cutscene.getCut() == 3) {
					cutscene.setCut(4);
				}else if(cutscene.getCut() == 4) {
					hideCutScene();
					topBar.setTimeStart(1);
					topBarflag = true;
				}
			}
			if (stage == 2) {
				if(cutscene.getCut() == 0) {
					cutscene.setCut(1);
				} 
				else if(cutscene.getCut() == 1) {
					cutscene.setCut(2);
				}
				else if(cutscene.getCut() == 2) {
					cutscene.setCut(3);
				} else if(cutscene.getCut() == 3) {
					cutscene.setCut(4);
				} else if(cutscene.getCut() == 4) {
					cutscene.setCut(5);

				}else if(cutscene.getCut() == 5) {
					cutscene.setCut(6);

				}else if(cutscene.getCut() == 6) {
					cutscene.setCut(7);

				}else if(cutscene.getCut() == 7) {
					cutscene.setCut(8);

				}else if(cutscene.getCut() == 8) {
					hideCutScene();
				}

			}

			if (stage == 3) {
				hideCutScene();
			}
			if (data.getHero().getWeapon() == "Sword" && data.getHero().isMoved() == false) {
				data.getHero().setAtk(true);
				playSwordSound = true;

				//data.audio.swordSlashSound.play();
			}
			break;
		case KeyEvent.VK_ENTER: // Opens Lab
			if (stage == 0) {
				if (namePop) {
					data.getHero().setName(name.Name.getText());
					hideName();
					stage = 1;
					data.getHero().setShotType(0);
					topBar.stage = 1;
					addCutScene();
				}
				//stage = 1;
			} else if (stage == 1 && !instructions) {// add flags for this and put logic in controller
				hideCutScene();
			}
			else {
				if (bam.checkCollision(data.getMap().getLabdoor(), data.getHero().getBounds())) {
					addPopUp();
				}
			
		   else if (stage == 2) {
			hideCutScene();
			if (bam.checkCollision(data.getMap().getGoldoor(), data.getHero().getBounds())) {
				addPopUp();
			}
		} else if (stage == 3) {
			hideCutScene();
			if(bam.checkCollision(data.getMap().getBossdoor(), data.getHero().getBounds())) {
				move = true;
			}
		}
		if (confirmScreen == true) {
			System.exit(0);
		}
			}

		break;
	case KeyEvent.VK_PAGE_DOWN:
		if (stage > 0) {
			hideCutScene();
			gamePlay.load = false;
			move = true;
			stage = 4;
		}
		break;

	default:
		key = e.getKeyCode();
	}
}

public void keyReleased(KeyEvent e) {
	int key = e.getKeyCode();
	if (key == KeyEvent.VK_UP) {
		moveUp = false;
	}
	if (key == KeyEvent.VK_DOWN) {

		moveDown = false;
	}
	if (key == KeyEvent.VK_RIGHT) {

		moveRight = false;
	}
	if (key == KeyEvent.VK_LEFT) {

		moveLeft = false;
	}

	if (key == KeyEvent.VK_SHIFT) {
		normalRun = true;
		fastRun = false;
	}

	if (key == KeyEvent.VK_SPACE) {
		open = false;
	}

	if (key == KeyEvent.VK_Z) {
		recover = false;
	}
	if (key == KeyEvent.VK_X) {
		protect = false;
	}
	if (key == KeyEvent.VK_S) {
		shoot = true;
	}
	if (key == KeyEvent.VK_M) {
		if (music) {
			music = false;
		}
		else if (!music) {
			music = true;
		}
		if (sound) {
			sound = false;
		}
		else if (!sound) {
			sound = true;
		}
	}
	if (key == KeyEvent.VK_SPACE) {
		data.getHero().setAtk(false);
	}

}

public void keyTyped(KeyEvent e) {
}

public void actionPerformed(ActionEvent e) {
}

public void actionReleased(ActionEvent e) {
}
}
