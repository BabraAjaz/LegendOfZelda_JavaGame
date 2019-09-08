package present;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class PopUp extends JPanel{

	int popUp;
	Model data = new Model();
	String[] scores;
	int[] integerScores;
	
	//Name
	JLabel Request = new JLabel("What's your name?");
	JTextField Name = new JTextField(10);
	String holder;
	
	// Pause
	JButton Resume = new JButton("Resume");
	JButton Restart = new JButton("Restart");
	JLabel Title = new JLabel("PAUSE");
	
	// Transition
	JButton Continue = new JButton("Continue");
	JButton Quit = new JButton("Quit");
	JLabel Confirm = new JLabel("<html>Are you sure you want to quit?");
	JLabel Transition = new JLabel("<html>You have reached the end of " + "this level! Choose one of the below");
	
	// Confirm
	JButton No = new JButton("No");
	JButton Yes = new JButton("Yes");

	// GameOver
	JLabel GameOver = new JLabel("GAMEOVER");
	
	//GameComplete
	JLabel GameComp = new JLabel("YAY!");
	JLabel message = new JLabel("<html>You've successfully completed the game. Select one of the options below:");
	JButton save = new JButton("Save HighScore");
	JLabel score = new JLabel();
	JLabel name = new JLabel("Name: ");
	
	//Scores
	JLabel scoring = new JLabel("Previous High Scores");
	JButton exit = new JButton("Return");
	JLabel token;
	Image GOLD = new ImageIcon(this.getClass().getResource("/assets/goldstar.png")).getImage();
	Image SILVER = new ImageIcon(this.getClass().getResource("/assets/silverstar.png")).getImage();
	Image BRONZE = new ImageIcon(this.getClass().getResource("/assets/bronzestar.png")).getImage();
	private int text;
	private int max;
	
	//Instructions 
	Image UP = new ImageIcon(this.getClass().getResource("/assets/up.png")).getImage();
	Image DOWN = new ImageIcon(this.getClass().getResource("/assets/down.png")).getImage();
	Image LEFT = new ImageIcon(this.getClass().getResource("/assets/left.png")).getImage();
	Image RIGHT = new ImageIcon(this.getClass().getResource("/assets/right.png")).getImage();
	Image S = new ImageIcon(this.getClass().getResource("/assets/s.png")).getImage();
	Image P = new ImageIcon(this.getClass().getResource("/assets/p.png")).getImage();
	Image I = new ImageIcon(this.getClass().getResource("/assets/i.png")).getImage();
	Image Z = new ImageIcon(this.getClass().getResource("/assets/z.png")).getImage();
	Image X = new ImageIcon(this.getClass().getResource("/assets/x.png")).getImage();
	Image U = new ImageIcon(this.getClass().getResource("/assets/u.png")).getImage();
	Image M = new ImageIcon(this.getClass().getResource("/assets/m.png")).getImage();
	
	boolean open = false;
		
	// FontStyles
	Font fontStyle1 = new Font("Arial", Font.BOLD, 55);
	Font fontStyle2 = new Font("Arial", Font.BOLD, 25);
	Font fontStyle4 = new Font("Arial", Font.PLAIN, 25);
	Font fontStyle3 = new Font("Arial", Font.PLAIN, 20);
	Font fontStyle5 = new Font("Arial", Font.ITALIC, 25);

	public PopUp(int popUp) {
		this.popUp = popUp;
		addButtons();
	}
	
	///if we don't do scores through popups we don't need this constructor 
	/*public PopUp(int popUp, Model data) {
		this.data = data;
		this.popUp = popUp;
		addButtons();
	}*/

	public void Layout() {
		
		if (popUp == 1 || popUp == 2 || popUp == 3) {
			setLayout(null);
			setBackground(new Color(255,255,255,30));
			setBounds(448, 252, 448, 252);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
		} else if (popUp == 4 ) { //GameOver
			setLayout(null);
			setBackground(new Color(255,255,255,30));
			setBounds(344, 252, 688, 252);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
		} else if (popUp == 0) { //GameComp
			setLayout(null);
			setBackground(new Color(0,0,0,100));
			setBounds(344, 252, 688, 300);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
		} else if (popUp == 5 ) { // Score
			setLayout(null);
			setBackground(new Color(0,0,0,180));
			setBounds(0,0,1376,864);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
			//this.add(token);
		} else if (popUp == 6) {
			setLayout(null);
			setBackground(Color.white);
			setBounds(448, 252, 448, 252);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
		} else if (popUp == 7) {
			setLayout(null);
			setBackground(new Color(0,0,0,180));
			setBounds(343,108,687,579);
			setBorder(BorderFactory.createLineBorder(new Color(255, 255, 255, 80), 2));
			this.setVisible(true);
			styleComponents();
		}
		
	}

	public void Hide() {
		this.setVisible(false);
	}

	public void addButtons() {
		if (popUp == 0) {
			this.add(GameComp);
			this.add(message);
			this.add(score);
			//this.add(Restart);
			this.add(Quit);
		}
		if (popUp == 1) {
			this.add(Title);
			this.add(Resume);
			this.add(Restart);
			this.add(Quit);
		}

		if (popUp == 2) {
			this.add(Transition);
			this.add(Continue);
			this.add(Quit);
		}

		if (popUp == 3) {
			this.add(Confirm);
			this.add(No);
			this.add(Yes);
		}

		if (popUp == 4) {
			this.add(GameOver);
			this.add(Restart);
			this.add(Quit);
		}
		
		if (popUp == 5) {
			this.add(scoring);
			this.add(exit);
		}
		
		if(popUp == 6) {
			this.add(Name);
			this.add(Request);
			this.add(Continue);
		}
	}

	public void styleComponents() {
		if (popUp == 0) { //GameComplete
			GameComp.setBounds(290, 25, 400, 45);
			GameComp.setFont(fontStyle1);
			GameComp.setForeground(Color.black);
			message.setBounds(50,100,500,45);
			message.setFont(fontStyle3);
			message.setForeground(Color.black);
			score.setBounds(450,130,400,100);
			score.setFont(fontStyle2);
			score.setForeground(Color.black);
			Restart.setBounds(64, 170, 157, 34);
			Restart.setFont(fontStyle2);
			Restart.setBackground(new Color(255, 255, 255, 70));
			Restart.setBorder(BorderFactory.createLineBorder(null, 0));
			Quit.setBounds(64, 220, 200, 34);
			Quit.setFont(fontStyle2);
			Quit.setBackground(new Color(255, 255, 255, 70));
			Quit.setBorder(BorderFactory.createLineBorder(null, 0));
		}
		else if (popUp == 1) { //Pause
			Title.setBounds(155, 22, 350, 45);
			Title.setFont(fontStyle1);
			Title.setForeground(Color.white);
			// Resume.setBounds(216,128,34,34);
			Resume.setBounds(151, 90, 157, 34);
			Resume.setFont(fontStyle2);
			Resume.setBackground(new Color(255, 255, 255, 70));
			Resume.setBorder(BorderFactory.createLineBorder(null, 0));
			Restart.setBounds(151, 146, 157, 34);
			Restart.setFont(fontStyle2);
			Restart.setBackground(new Color(255, 255, 255, 70));
			Restart.setBorder(BorderFactory.createLineBorder(null, 0));
			Quit.setBounds(151, 202, 157, 34);
			Quit.setFont(fontStyle2);
			Quit.setBackground(new Color(255, 255, 255, 70));
			Quit.setBorder(BorderFactory.createLineBorder(null, 0));
		}
		else if (popUp == 2) { //Transition from level
			Transition.setBounds(32, 22, 350, 128);
			Transition.setFont(fontStyle3);
			Transition.setForeground(Color.white);
			Continue.setBounds(32, 170, 157, 34);// 259,170,157,34
			Continue.setFont(fontStyle4);
			Continue.setBackground(new Color(255, 255, 255, 70));
			Continue.setBorder(BorderFactory.createLineBorder(null, 0));
			Quit.setBounds(259, 170, 157, 34);
			Quit.setFont(fontStyle4);
			Quit.setBackground(new Color(255, 255, 255, 70));
			Quit.setBorder(BorderFactory.createLineBorder(null, 0));
		}
		else if (popUp == 3) { // Quit confirmation
			
			Confirm.setBounds(32, 22, 350, 128);
			Confirm.setFont(fontStyle3);
			Confirm.setForeground(Color.white);
			Yes.setBounds(32, 170, 157, 34);// 259,170,157,34
			Yes.setFont(fontStyle4);
			Yes.setBackground(new Color(255, 255, 255, 70));
			Yes.setBorder(BorderFactory.createLineBorder(null, 0));
			No.setBounds(259, 170, 157, 34);
			No.setFont(fontStyle4);
			No.setBackground(new Color(255, 255, 255, 70));
			No.setBorder(BorderFactory.createLineBorder(null, 0));
		}
		else if (popUp == 4) { // GameOver
			GameOver.setBounds(155, 25, 400, 45);
			GameOver.setFont(fontStyle1);
			GameOver.setForeground(Color.white);
			Restart.setBounds(151, 146, 157, 34);
			Restart.setFont(fontStyle2);
			Restart.setBackground(new Color(255, 255, 255, 70));
			Restart.setBorder(BorderFactory.createLineBorder(null, 0));
			Quit.setBounds(340, 146, 157, 34);
			Quit.setFont(fontStyle2);
			Quit.setBackground(new Color(255, 255, 255, 70));
			Quit.setBorder(BorderFactory.createLineBorder(null, 0));
		} else if (popUp == 5) {
			scoring.setBounds(500, 25, 400, 45);
			scoring.setFont(fontStyle2);
			scoring.setForeground(Color.white);
			exit.setBounds(500, 600, 400, 45);
			exit.setFont(fontStyle2);
			exit.setBackground(new Color(0,0,0,70));
			exit.setForeground(Color.white);
			text = 64;
			for (int i = 0; i < scores.length; i++) {
				token = new JLabel(String.valueOf(scores[i]));
				token.setFont(fontStyle2);
				token.setBounds(624, text+=64, 128, 96);
				token.setForeground(Color.white);
			}
			
		} else if(popUp == 6) {
			Request.setBounds(50,22,350,40);
			Request.setBackground(Color.white);
			Request.setFont(fontStyle3);
			Name.setBounds(32,94,350,40);
			Name.setBackground(new Color(0,0,0,70));
			Name.setFont(fontStyle2);
			Continue.setBounds(32, 170, 157, 34);// 259,170,157,34
			Continue.setFont(fontStyle4);
			Continue.setBackground(new Color(255, 255, 255, 70));
			Continue.setBorder(BorderFactory.createLineBorder(null, 0));
		}
	}
	
	public void sortScore() {
		for (int i = 0; i < scores.length; i++) {
			integerScores[i] = Integer.parseInt(scores[i]);
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		if(popUp == 5) {
			text = 312;
			if(scores.length > 3) {
				max = 3;
			}
			else {
				max = scores.length;
			}
			
			for (int i = 0; i < max; i++) {
				g.setColor(Color.white);
				g.setFont(fontStyle1);
				g.drawString(String.valueOf(scores[i]), text, 388);
				if (text < 1000) {
					text += 344;
				}
			}
			g.drawImage(GOLD, 216, 100, 256, 256, null);
			g.drawImage(SILVER, 560, 100, 256, 256, null);
			g.drawImage(BRONZE, 904, 100, 256, 256, null);
		}
		
		if(popUp == 7) {
			g.setColor(Color.white);
			
			//LEFT
			g.drawImage(UP, 32, 32, null);
			g.drawImage(DOWN, 32, 96, null);
			g.drawImage(LEFT, 32, 160, null);
			g.drawImage(RIGHT, 32, 224, null);
			g.drawImage(I, 32, 288, null);
			g.drawImage(U, 32, 456, null);
			
			g.setFont(fontStyle5); //ITALICS
			g.drawString("SPACE", 32, 368);
			g.drawString("SHIFT", 32, 424);
			
			
			//RIGHT
			g.drawImage(S, 375, 32, null);
			g.drawImage(P, 375, 96, null);
			g.drawImage(Z, 375, 160, null);
			g.drawImage(X, 375, 224, null);
			g.drawImage(M, 375, 288, null);
			
			
			g.setFont(fontStyle2); //BOLD
			//LEFT
			g.drawString("UP", 128, 56);
			g.drawString("DOWN", 128, 120);
			g.drawString("LEFT", 128, 184);
			g.drawString("RIGHT", 128, 248);
			g.drawString("INSTRUCTIONS", 128, 312);
			g.drawString("SWORD", 128, 368);
			
			g.drawString("Shift + Arrow to run", 128, 424);
			g.drawString("Escape out of instructions", 128, 480);
			
			//RIGHT
			g.drawString("SHOOT", 471, 56);
			g.drawString("PAUSE", 471, 120);
			
			g.drawString("HEAL", 471, 184);
			g.drawString("SHIELD", 471, 248);
			
			g.drawString("MUTE", 471, 312);
			
			
			g.setFont(fontStyle3);
			g.drawString("Every orange flower increases your energy by 10", 32, 550);
		}
	}

}
