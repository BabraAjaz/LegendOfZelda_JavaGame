package present;

import javax.swing.*;
import javax.swing.Timer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class GameScreen extends JPanel{

	int cutscene = 0;
	Collisions bam = new Collisions();

	int debug = 0;
	
	private int camWidth = 1376;
	private int camHeight = 768;
	private int camX = 0;
	private int camY = 96;
	boolean moved = false;
	boolean collided = false;
	boolean load = false;
	boolean ret = false;
	boolean shotComplete = true;
	boolean fireComplete = true;
	boolean iceComplete = true;
	boolean transit = false;
	private boolean retrieved;

	boolean timeStart = false;

	Rectangle flowerBound;

	int place = 0;

	Model data = new Model();
	public Image[] heroSprite;
	public Image[] catSprite;
	public Image[] shot = new Image[2];
	private Image[] bombSprite;
	boolean skip = false;

	int stage = 0;
	int count = 0;
	int mapComplete = 0;
	ArrayList<Image[][]> map = new ArrayList<Image[][]>();

	public GameScreen(Model data) {
		this.data = data;
		setBackground(Color.black);
		setLayout(null);
		setBackground(new Color(134, 94, 64));
		this.setDoubleBuffered(true);
		setBounds(camX, camY, camWidth, camHeight);
		mapComplete = 0;
		repaint();
	}

	public Rectangle getBounds() {
		return new Rectangle(96, 0, 1825, 1080);
	}

	public void setstage(int stage) {
		this.stage = stage;
	}

	public void setCamX(int L) {
		if (L == 0) {
			if (this.camX > -1376) {
				transit = true;
				this.camX -= 50;
			}
			if (this.camX <= -1376) {
				transit = false;
			}
		} else if (L == 1) {
			if (this.camX < 0) {
				this.camX += 50;
			}
		}
		moved = true;
	}

	public void paintComponent(Graphics g) {
		super.paintComponents(g);

		/************ Make reusable *********/
		if (load == false && data.stage == 1) {
			map = data.getMap().loadArrays();
			load = true;
		}

		if (load == false && data.stage == 2) {
			map = data.getMap().loadArrays();
			load = true;
		}

		if (load == false && data.stage == 3) {
			map = data.getMap().loadArrays();
			load = true;
		}
		
		if (load == false && data.stage == 4) {
			map = data.getMap().loadArrays();
			load = true;
		}

		// all render functions render the specific images that the 
		// game needs in order for the graphical interface works the way
		// it does.
		returnSprites();
		
		renderMap(g);
		
		renderPickUps(g);

		//rendering enemies depending on the stage
		if (data.getStage() == 1) {
		renderDogs(g);
		}
		renderBombs(g);
		
		if (data.getStage() == 4) {
			renderBoss(g);
		}

		renderFireBlast(g);
		renderIceBlast(g);
		renderHero(g);
		renderSword(g);
		renderBracelet(g);
		renderCat(g);
		renderBoss(g);
		healthLossNotification(g);
		renderKnights(g);
	}

	public void returnSprites() {
		heroSprite = data.getHero().returnHero();
		catSprite = data.getCat().returnCat();
		bombSprite = data.getBomb0().getSprite(); 
		shot[0] = data.getHero().getFire();
		shot[1] = data.getHero().getIce();
	}

	public void renderPickUps(Graphics g) {
		for (int i = 0; i < data.getPickup().getMaxOrange(); i++) {
			if(data.getPickup().getBadIndex()[i] != 1) {
				if (data.getPickup().getCollide()[i] == 0) {
					g.drawImage(data.getPickup().Energy(), data.getPickup().getRandomCol()[i] * 32, 
							data.getPickup().getRandomRow()[i] * 32, 16, 16, null);
					data.getPickup().getPlaced()[i] = 1;
				}
			}
		}
		for (int i = 0; i < data.getPickup().getMaxBlack(); i++) {
				if (data.getPickup().getCollideB()[i] == 0) {
					g.drawImage(data.getPickup().minusHealth(), data.getPickup().getRandomColB()[i] * 32, 
							data.getPickup().getRandomRowB()[i] * 32, 16, 16, null);
					data.getPickup().getPlacedB()[i] = 1;
				}
		}

		if (data.getPickup().getCollideW() == 0) {
				g.drawImage(data.getPickup().FullEnergy(), data.getPickup().getRandomColW() * 32, 
						data.getPickup().getRandomRowW() * 32, 16, 16, null);
				data.getPickup().setPlacedW(1);
		}

		if(data.stage > 1) {

			for (int i = 0; i < data.getPickup().getMaxRed(); i++) {
					if (data.getPickup().getCollideR()[i] == 0) {
						g.drawImage(data.getPickup().Fire(), data.getPickup().getRandomColR()[i] * 32, 
								data.getPickup().getRandomRowR()[i] * 32, 16, 16, null);
						data.getPickup().getPlacedR()[i] = 1;
					}
			}
			for (int i = 0; i < data.getPickup().getMaxBlue(); i++) {
				if(data.getPickup().getBadIndexBl()[i] != 1) {
					if (data.getPickup().getCollideBl()[i] == 0) {
						g.drawImage(data.getPickup().Ice(), data.getPickup().getRandomColBl()[i] * 32, 
								data.getPickup().getRandomRowBl()[i] * 32, 16, 16, null);
						data.getPickup().getPlacedBl()[i] = 1;
					}
				}
			}
		}
	}

	public void renderMap(Graphics g) {
		for (int j = 0; j < 87; j++) {
			for (int k = 0; k < 23; k++) {
				g.drawImage((map.get(0)[j][k]), j * 32 + camX, k * 32, 32, 32, null);
			}
		}

		for (int j = 0; j < 87; j++) {
			for (int k = 0; k < 23; k++) {
				g.drawImage((map.get(1)[j][k]), j * 32 + camX, k * 32, 32, 32, null);
			}
		}
		for (int j = 0; j < 87; j++) {
			for (int k = 0; k < 23; k++) {
				g.drawImage((map.get(2)[j][k]), j * 32 + camX, k * 32, null);
			}
		}
	}

	public void renderDogs(Graphics g) {
		if (data.getStage() == 1) {
			
			for (int i = 0; i < data.getDogs().size(); i++ ) {
				if (data.getDogs().get(i).isAlive()) {
					g.drawImage(data.getDogs().get(i).getDogSprite()[data.getDogs().get(i).getDirection()],
							(int)data.getDogs().get(i).getPosX(),(int)data.getDogs().get(i).getPosY(),null);

				}
			}
		}
	}

	public void renderBombs(Graphics g) {
		if (data.getStage() == 2) {
			for (int i = 0; i < data.getBombs().size(); i++) {
				if (data.getBombs().get(i).isAlive()) {
					g.drawImage(bombSprite[data.getBombs().get(i).getDirection()], (int) data.getBombs().get(i).getPosX(), (int) data.getBombs().get(i).getPosY(), null);

				}
			}
			
		}
	}

	public void renderBracelet(Graphics g) {
		if (data.getHero().getKeepHealth() == true) {
			g.drawImage(data.getBrace().getShieldImage(), (int)data.getBrace().getShieldX(),(int)data.getBrace().getShieldY(),null);
		}
	}

	public void renderFireBlast(Graphics g) {
		for (int i = 0; i < data.getHero().getFireBlast().size(); i++) {
			data.setTempFireShot(data.getHero().getFireBlast().get(i));
			g.drawImage(data.getTempFireShot().blastImage,
					data.getTempFireShot().getRelativeX()
					,data.getTempFireShot().getRelativeY()
					,data.getTempFireShot().getWidth()
					,data.getTempFireShot().getHeight(),null);
		}
	}

	public void renderIceBlast(Graphics g) {
		for (int i = 0; i < data.getHero().getIceBlast().size(); i++) {
			data.setTempIceShot(data.getHero().getIceBlast().get(i));
			g.drawImage(data.getTempIceShot().blastImage,
					data.getTempIceShot().getRelativeX()
					,data.getTempIceShot().getRelativeY()
					,data.getTempIceShot().getWidth()
					,data.getTempIceShot().getHeight(),null);
		}
	}

	public void renderHero(Graphics g) {
		if (data.getHero().getDirection() == 0 && data.getHero().isMoved() == true && data.getHero().isAtk() == false) {
			data.getHero().getWalkFront().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);
			
		} else if (data.getHero().getDirection() == 1 && data.getHero().isMoved() == true
				&& data.getHero().isAtk() == false) {
			data.getHero().getWalkBack().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);

		} else if (data.getHero().getDirection() == 2 && data.getHero().isMoved() == true
				&& data.getHero().isAtk() == false) {
			data.getHero().getWalkRight().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);

		} else if (data.getHero().getDirection() == 3 && data.getHero().isMoved() == true
				&& data.getHero().isAtk() == false) {
			data.getHero().getWalkLeft().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);

		} else if (data.getHero().isAtk() == false) {
			g.drawImage(heroSprite[data.getHero().getDirection()], (int) data.getHero().posX, (int) data.getHero().posY,
					null);
		}

	}

	public void healthLossNotification(Graphics g) {
		if (data.getHero().isHealthLoss() == true) {
			g.setColor(new Color(255,105,97,50));
			g.fillRect(0, 0, 1376, 768);
		}
		if (data.getHero().isHealthGain() == true) {
			g.setColor(new Color(119,221,119,50));
			g.fillRect(0, 0, 1376, 768);
		}
	}

	public void renderSword(Graphics g) {
		if (data.getHero().getDirection() == 0 && data.getHero().getWeapon() == "Sword"
				&& data.getHero().isAtk() == true && data.getHero().isMoved() == false) {
			data.getHero().getAtkDown().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);

		} else if (data.getHero().getDirection() == 1 && data.getHero().getWeapon() == "Sword"
				&& data.getHero().isAtk() == true && data.getHero().isMoved() == false) {
			data.getHero().getAtkUp().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);
		} else if (data.getHero().getDirection() == 2 && data.getHero().getWeapon() == "Sword"
				&& data.getHero().isAtk() == true && data.getHero().isMoved() == false) {
			data.getHero().getAtkRight().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);
		} else if (data.getHero().getDirection() == 3 && data.getHero().getWeapon() == "Sword"
				&& data.getHero().isAtk() == true && data.getHero().isMoved() == false) {
			data.getHero().getAtkLeft().drawAnimation(g, (int) data.getHero().posX, (int) data.getHero().posY);
		}
	}

	public void renderCat(Graphics g) {
		if (data.getCat().getDirection() == 0 && data.getCat().moving == true) {
			data.getCat().getWalkDown().drawAnimation(g, (int) data.getCat().posX ,
					(int) data.getCat().posY);
			
		} else if (data.getCat().getDirection() == 1 &&  data.getCat().moving == true) {
			data.getCat().getWalkUp().drawAnimation(g, (int) data.getCat().posX ,
					(int) data.getCat().posY);
		} else if (data.getCat().getDirection() == 2 &&  data.getCat().moving == true) {
			data.getCat().getWalkRight().drawAnimation(g, (int) data.getCat().posX ,
					(int) data.getCat().posY);
		} else if (data.getCat().getDirection() == 3 &&  data.getCat().moving == true) {
			data.getCat().getWalkLeft().drawAnimation(g, (int) data.getCat().posX ,
					(int) data.getCat().posY);
		} else {

			// Draw idle cat
			if (data.getCat().getDirection() == 0) {
				g.drawImage(catSprite[data.getCat().getDirection()], (int) data.getCat().posX ,
						(int) data.getCat().posY, null);
				//if (cutscene == 1 && skip == false) {
				//	Image bubble = new ImageIcon("assets/speechBubble/bub.png").getImage();
				//	g.drawImage(bubble, (int) data.getHero().posX + 30, (int) data.getHero().posY + 70, null);

			}
			else {
				g.drawImage(catSprite[data.getHero().getDirection()], (int) data.getCat().posX ,
						(int) data.getCat().posY, null);
			}
		}
	}

	public void renderBoss(Graphics g) {
		if (data.getBoss().isAlive() == true) {
			if (data.getStage() == 4) {
				g.setColor(new Color(189,236,182));
				g.fillRect((int)data.getBoss().getPosX(), (int) data.getBoss().getPosY()-15, data.getBoss().getHealth()/2,10);
				g.setColor(new Color(177,156,217));
				g.fillRect((int)data.getBoss().getPosX(), (int) data.getBoss().getPosY(), data.getBoss().getEnergy()/2,10);

				g.drawImage(data.getBoss().getBossImg(),(int) data.getBoss().getPosX(), (int)data.getBoss().getPosY(),null);

				if (data.getBoss().getTimeTillFireBlast()<150 && data.getBoss().getTimeTillFireBlast()>100) {
					g.setColor(new Color(253, 165,15,50));
					g.fillRect((int)data.getBoss().getPosX()-150, (int) data.getBoss().getPosY(), 400, 40);
					g.fillRect((int)data.getBoss().getPosX()+50, (int) data.getBoss().getPosY()-150, 40, 300);
				}
				if (data.getBoss().isFireBlast()) {
					g.setColor(new Color(253, 165,15));
					g.fillRect(0, (int) data.getBoss().getPosY(), 1376, 40);
					g.fillRect((int)data.getBoss().getPosX()+60,0, 50,768);
				}

				if (data.getBoss().getTimeTillIceBlast()<150 && data.getBoss().getTimeTillIceBlast()>100) {
					g.setColor(new Color(165, 242,243,50));
					g.fillOval((int)data.getBoss().getPosX()-70,(int) data.getBoss().getPosY()-100, 250, 250);

				}
				else if (data.getBoss().isIceBlast()) {
					g.setColor(new Color(165, 242,243,120));
					g.fillOval((int)data.getBoss().getPosX()-400,(int) data.getBoss().getPosY()-500, 1000, 1000);
				}


			}
		}
	}

	public void renderKnights(Graphics g) {
		if (data.getStage() == 3) {
			for(int i = 0; i < data.getKnights().size(); i++) {
				g.drawImage(data.getKnights().get(i).getDown()[i],
						(int)data.getKnights().get(i).getPosX(),(int)data.getKnights().get(i).getPosY(),
						null);
			}
		}
	}
}
