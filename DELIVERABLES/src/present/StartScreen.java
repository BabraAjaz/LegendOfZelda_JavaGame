package present;

import java.awt.*;
import javax.swing.*;

import javax.swing.ImageIcon;
import javax.swing.border.LineBorder;

public class StartScreen extends JPanel {
	
	private int count = 255;
	private boolean brandComplete = false;

	private JPanel scoreScreen = new JPanel();
	private JButton start = new JButton("Play");
	private JButton score = new JButton("Hi-Scores");
	private JButton exit = new JButton("Exit Game");
	private JLabel title = new JLabel("Before N After");
	private Font fontStyle = new Font("Arial", Font.BOLD, 30);
	private Font font = new Font("merlin", Font.ITALIC, 40);
	
	public StartScreen() {
		scoreScreen.setLayout(null);
		scoreScreen.setBounds(448, 252, 448, 252);
		scoreScreen.setBackground(new Color(0,0,0,180));
		setLayout(null);
		setBounds(0, 0, 1376, 864);
		setBackground(new Color(119, 221, 119));
		this.setDoubleBuffered(true);
		styleButtons();

	}
	
	public void buttonHandling(boolean operational) {
		if (operational == true) {
			this.getStart().setEnabled(true);
			this.getScore().setEnabled(true);
			this.getExit().setEnabled(true);
			
			
		} else if (operational == false) {
			this.getStart().setEnabled(false);
			this.getScore().setEnabled(false);
			this.getExit().setEnabled(false);
		}
	}

	// Adds buttons to panel
	public void addButtons() {
		this.add(getStart());
		//this.add(story);
		this.add(getScore());
		//this.add(credits);
		this.add(getExit());
		this.add(title);
	}

	// Sets button styles
	public void styleButtons() {

		getStart().setBounds(1366 / 3, 768 / 3, 500, 60);
		getStart().setFont(fontStyle);
		getStart().setBackground(new Color(229, 229, 229, 50));
		getStart().setBorder(BorderFactory.createLineBorder(new Color(37, 150, 37), 5));

		getScore().setBounds(1366 / 3, 768 / 3 + 80, 500, 60);
		getScore().setFont(fontStyle);
		getScore().setBackground(new Color(229, 229, 229, 50));
		getScore().setBorder(BorderFactory.createLineBorder(new Color(37, 150, 37), 5));
		
		getExit().setBounds(1366 / 3, 768 / 3 + 160, 500, 60);
		getExit().setFont(fontStyle);
		getExit().setBackground(new Color(229, 229, 229, 50));
		getExit().setBorder(BorderFactory.createLineBorder(new Color(37, 150, 37), 5));
		title.setBounds(425, 546 / 6 + 50, 1000, 100);
		title.setForeground(Color.black);
		title.setFont(new Font("merlin", Font.BOLD, 80));
	}

	public void paintComponent(Graphics g) {
		// Integrates Introscreen fade
		super.paintComponent(g);

		Image screen = new ImageIcon(this.getClass().getResource("/assets/StartScreen4.png")).getImage();
		g.drawImage(screen, 0, 0, 1376, 864, null);
		
		if (count > 0 && brandComplete == false) {
			g.setColor(new Color(0, 0, 0, count));// new Color(255,255,255,count));
			count -= 5;
		}

		if (count == 0) {
			brandComplete = true;
			g.setColor(new Color(255, 255, 255, 0));
			addButtons();
		}

		g.fillRect(0, 0, 1920, 864);

		String company = "Zeebra Games";
		String names = "Created by: Azizul & Babra";
		g.setColor(new Color(255, 255, 255, count));
		g.setFont(font);
		g.drawString(company, 550, 300);
		g.drawString(names, 550, 360);


	}

	public JButton getStart() {
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getScore() {
		return score;
	}

	public void setScore(JButton score) {
		this.score = score;
	}

	public JButton getExit() {
		return exit;
	}

	public void setExit(JButton exit) {
		this.exit = exit;
	}
	
	/*public void drawScore(Graphics g) {
		g.setColor(Color.white);
		data.score.fileReader();
		System.out.println(data.score.scoreList.size());
		for (int i = 0; i < data.score.scoreList.size(); i++) {
			g.drawString(data.score.scoreList.get(i), 200, (i+1)*100 );
		}
		data.score.scoreList.clear();
	}*/
}
