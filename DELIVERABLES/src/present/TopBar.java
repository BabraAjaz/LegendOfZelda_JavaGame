package present;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TopBar extends JPanel implements ActionListener {

	Model data = new Model();
	private Image LOCKEDBLUE = new ImageIcon(this.getClass().getResource("/assets/Flowers/bluelock.png")).getImage();
	private int health = 200;
	private int energy = 200;
	private int healthR = 200;
	private int energyR = 200;
	int stage = 0;
	private int timeStart = 0;
	private boolean set = false;
	private int time = 300; // 2:30 mins
	private int points;
	Timer seconds = new Timer(1000, this); // 1 second timer
	JLabel HP = new JLabel("HEALTH");
	JLabel MP = new JLabel("ENERGY");
	String NAME;
	Font font = new Font("Arial", Font.PLAIN, 40);
	Font font2 = new Font("Arial", Font.PLAIN, 25);

	public TopBar(Model data) {
		this.data = data;
		setLayout(null);
		setBounds(0, 0, 1376, 96);
		setLabels();
	}
	
	private void setLabels() {
		HP.setBounds(1071, 10, 60, 20);
		HP.setBackground(new Color(229, 229, 229, 0));
		HP.setForeground(Color.white);
		HP.setOpaque(true);
		this.add(HP);
		MP.setBounds(1071, 50, 60, 20);
		MP.setBackground(new Color(229, 229, 229, 0));
		MP.setForeground(Color.white);
		MP.setOpaque(true);
		this.add(MP);
	}
	
	public void UpdateHealth() {
		this.health = (int)data.getHero().getHealth();
		
	}
	
	public void UpdateEnergy() {
		this.energy = (int)data.getHero().getEnergy();
	}

	public void setStage(int stage) {
		this.stage = stage;
	}
	
	protected void paintComponent(Graphics g) {

		super.paintComponent(g);
		UpdateHealth();
		UpdateEnergy();
		// make hourglass have different coloured sand for different levels mettalic for
		// level 2 gold for level 3
		if (stage == 0) {
			
		} else if (stage == 1) {
			Image hourglass = new ImageIcon(this.getClass().getResource("/assets/timers/brownglass.png")).getImage();
			g.drawImage(hourglass, 10, -15, null);
		} else if (stage == 2) {
			Image hourglass = new ImageIcon(this.getClass().getResource("/assets/timers/greyglass.png")).getImage();
			g.drawImage(hourglass, 0, -15, null); 
		} else if (stage == 3) {
			Image hourglass = new ImageIcon(this.getClass().getResource("/assets/timers/goldglass.png")).getImage();
			g.drawImage(hourglass, 0, -15, null);
			HP.setForeground(Color.BLACK);
			MP.setForeground(Color.black);
		}
		
		
		g.setColor(new Color(3, 192, 60));
		g.drawRoundRect(1130, 10, 200, 20, 10, 10);
		if (health > 120) {
			if (healthR > health) {
			healthR-=2;
			}
			else if (health > healthR) {
				healthR+=2;
			}
			g.setColor(new Color(119, 221, 119)); // Pastel Green
			g.fillRoundRect(1130, 10, healthR, 20, 10, 10);
		} else if (health > 80) {
			if (healthR > health) {
				healthR-=2;
				}
			else if (health > healthR) {
				healthR+=2;
			}
			g.setColor(new Color(255, 179, 71)); // Pastel Orange
			g.fillRoundRect(1130, 10, healthR, 20, 10, 10);
		} else {
			if (healthR > health) {
				healthR-=2;
				}
			else if (health > healthR) {
				healthR+=2;
			}
			g.setColor(new Color(255, 105, 97)); // Pastel Red
			g.fillRoundRect(1130, 10, healthR, 20, 10, 10);
		}

		if (energy < energyR) {
			energyR-=2;
		}
		if (energy > energyR) {
			energyR+=2;
		}
		
		g.setColor(new Color(68, 119, 178));
		g.drawRoundRect(1130, 50, 200, 20, 10, 10);
		g.setColor(new Color(119, 158, 203)); // Pastel Blue
		g.fillRoundRect(1130, 50, energyR, 20, 10, 10);

		/******FLOWER POINTS*****/
		g.setColor(Color.white);
		g.setFont(font2);
		g.drawImage(data.getPickup().Energy(), 208, 48, 32,32, null);
		g.drawString(String.valueOf(data.getHero().getPoints()), 240, 72);
		
		if (data.stage > 0) {		
			if (data.getHero().getName() == null) {
				NAME = "Ruby";	
			} else if (data.getHero().getName() != null) {
				NAME = data.getHero().getName();
			}
			g.setFont(font2);
			g.drawString(NAME, 216, 32);
		}
		
		if (data.getHero().getShotType() == 0) {
			g.drawImage(LOCKEDBLUE, 288, 48, 32,32,null);

		}else if (data.getHero().getShotType() == 1) {//FIRE
			g.drawImage(data.getPickup().Fire(), 288, 48, 32,32,null);

		} else if (data.getHero().getShotType() == 2) {//ICE
			g.drawImage(data.getPickup().Ice(), 288, 48, 32,32,null);
		}
	
		//2min30sec
		if (getTimeStart() == 0) {
			g.drawString(String.valueOf(300), 136, 55);
		}
		setBarBackground();

		// Counts down from 2:30 // Press S to start timer
		if (getTimeStart() == 1) {
			seconds.start();
			g.setFont(font2);
			g.drawString(String.valueOf(this.time), 136, 55);
		}
		if (getTimeStart() == 2) {
			g.setFont(font2);
			g.drawString(String.valueOf(this.time), 136,55);
			seconds.stop();
		}
		
		if(data.stage == 1) {
			g.drawString("Prese ENTER at the door of the lab!",340,32);
		} 
		if(data.stage == 2) {
			g.drawString("Press ENTER at the gold door!", 340, 32);
		}
		if(data.stage == 3) {
			g.drawString("Press ENTER at the other bridge to get to the boss", 340, 32);
		}
	}

	public void setBarBackground() {
		if (stage == 1) { // Brownish Background
			this.setBackground(new Color(114, 64, 42, 170));
		}

		else if (stage == 2) { // Blueish Background // Can be changed
			setBackground(new Color(3, 63, 99, 170));
		}

		else if (stage == 3) { // Goldish Background
			setBackground(new Color(53,98,68, 170));
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		time--;

	}

	public int getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(int timeStart) {
		this.timeStart = timeStart;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public boolean isSet() {
		return set;
	}

	public void setSet(boolean set) {
		this.set = set;
	}

}
