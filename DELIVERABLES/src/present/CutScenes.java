package present;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class CutScenes extends JPanel{
	
	//initializing panel size 
	private int screenWidth = 1376;
	private int screenHeight = 864;
	
	//helper variables to aid the view and set flags
	private int stage;
	private int cut = 0;
	
	//image of the cat
	private Image catCutScene = new ImageIcon(this.getClass().getResource("/assets/cutscenecat.png")).getImage();
	
	////Game Start
	private Image speechBub0 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub0.png")).getImage();
	private Image speechBub1 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub1.png")).getImage();
	private Image speechBub2 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub2.png")).getImage();
	private Image speechBub3 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub3.png")).getImage();
	
	///Level 1 end
	private Image speechBub4 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub4.png")).getImage();
	private Image speechBub5 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub5.png")).getImage();
	private Image speechBub6 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub6.png")).getImage();
	private Image speechBub7 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub7.png")).getImage();
	private Image speechBub8 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub8.png")).getImage();
	private Image speechBub9 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub9.png")).getImage();
	private Image speechBub10 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub10.png")).getImage();
	private Image speechBub11 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub11.png")).getImage();
	private Image speechBub12 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub12.png")).getImage();
	private Image speechBub13 = new ImageIcon(this.getClass().getResource("/assets/speechBubble/bub13.png")).getImage();
	private Font font = new Font("Arial", Font.PLAIN, 20);
	
	public CutScenes() {
	}
	
	//layout gets set whenever the cutscene is supposed to be called
	public void Layout() {
		System.out.println("HERE");
		this.setLayout(null);
		this.setBounds(0, 0, screenWidth, screenHeight);
		this.setBackground(Color.black);
		this.setVisible(true);
	}
	
	//getters and setters
	public void setCut(int cut) {
		this.cut = cut;
	}
	
	public int getCut() {
		return cut;
	}
	
	public void Hide() {
		this.setVisible(false);
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	
	public int getStage() {
		return stage;
	}
	
	//paintting components on teh panel relating to which cut scene i
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		g.drawImage(catCutScene, 96, 216, catCutScene.getWidth(null), catCutScene.getHeight(null), null);
	
		g.setColor(new Color(255,255,255,100));
		g.setFont(font);
		g.drawString("PRESS SPACE TO CONTINUE", 1050,770);
		g.drawString("PRESS ENTER TO SKIP", 32, 770);
		
		if (stage == 1) {
			if (cut == 0) {
				g.drawImage(speechBub0, 512, 0, 768,490,null);
			} else if (cut == 1) {
				g.drawImage(speechBub1, 512, 0, 768, 490, null);
			} else if (cut == 2) {
				g.drawImage(speechBub2, 512, 0, 768, 490, null);
			} else if (cut == 3) {
				g.drawImage(speechBub3, 512, 0, 768, 490, null);
			} else if (cut == 4) {
				g.drawImage(speechBub12, 512, 0, 768, 490, null);
			}
		} else if (stage == 2) {
			if (cut == 0) {
				g.drawImage(speechBub4, 512, 0, 768,490,null);
			} else if (cut == 1) {
				g.drawImage(speechBub5, 512, 0, 768, 490, null);
			} else if (cut == 2) {
				g.drawImage(speechBub6, 512, 0, 768, 490, null);
			} else if (cut == 3) {
				g.drawImage(speechBub7, 512, 0, 768, 490, null);
			} else if (cut == 4) {
				g.drawImage(speechBub7, 512, 0, 768, 490, null);
			} else if (cut == 5) {
				g.drawImage(speechBub8, 512, 0, 768, 490, null);
			} else if (cut == 6) {
				g.drawImage(speechBub9, 512, 0, 768, 490, null);
			} else if (cut == 7) {
				g.drawImage(speechBub10, 512, 0, 768, 490, null);
			} else if (cut == 8) {
				g.drawImage(speechBub11, 512, 0, 768, 490, null);
			}
		} else if (stage == 3) {
			if (cut == 0) {
				g.drawImage(speechBub13, 512, 0, 768, 490, null);
			}
		}
		
	}
	
	//fucntion to read \ns in a string
	private void drawString(Graphics g, String text, int x, int y) {
        int lineHeight = g.getFontMetrics().getHeight();
        for (String line : text.split("\n"))
            g.drawString(line, x, y += lineHeight);
    }

	public Image getCatCutScene() {
		return catCutScene;
	}

	public void setCatCutScene(Image catCutScene) {
		this.catCutScene = catCutScene;
	}

}
