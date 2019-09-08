package present;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Future {

	int[][] Layer2bounds = new int[43][24];
	private Image[][] Layer1 = new Image[87][24];
	private Image[][] Layer2 = new Image[87][24];
	private Image[][] Layer3 = new Image[87][24];
	
	int numObjects = 2;

	private Rectangle [] objectBounds = new Rectangle[numObjects];
	private Rectangle house1Bounds = new Rectangle(32,96,100,115);
	private Rectangle house2Bounds = new Rectangle(32,288,100,115);
	

	private Image COURT = new ImageIcon(this.getClass().getResource("/assets/court2.png")).getImage();
	private Image GRASS = new ImageIcon(this.getClass().getResource("/assets/grass0.png")).getImage();
	private Image BAR = new ImageIcon(this.getClass().getResource("/assets/gold.png")).getImage();
	private Image BRICK = new ImageIcon(this.getClass().getResource("/assets/bricks3.png")).getImage();
	private Image WATER5 = new ImageIcon(this.getClass().getResource("/assets/water5.png")).getImage();
	private Image WATER2 = new ImageIcon(this.getClass().getResource("/assets/water2.png")).getImage();
	private Image WATER4 = new ImageIcon(this.getClass().getResource("/assets/water4.png")).getImage();
	private Image WATER7 = new ImageIcon(this.getClass().getResource("/assets/water7.png")).getImage();
	private Image WATER8 = new ImageIcon(this.getClass().getResource("/assets/water8.png")).getImage();
	private Image HOUSE = new ImageIcon(this.getClass().getResource("/assets/house5.png")).getImage();

	public Future() {
	}

	public String getName() {
		return "FUTURE";
	}

	public int getLayers() {
		return 2;
	}

	/*** TERRAIN LAYER ***/
	public Image[][] setLayer1() {
		// Level 3
		for (int i = 0; i < 42; i++) {
			for (int j = 0; j < 17; j++) {
				Layer1[i][j] = GRASS;
			}
			for (int j = 17; j < 23; j++) {
				Layer1[i][j] = WATER5;
			}
		}
		for (int j = 0; j < 23; j++) {
			Layer1[42][j] = BAR;
		}
		
		for (int i = 0; i < 10; i++) {
			for (int j = 19; j < 22; j++) {
				Layer1[i][j] = BRICK;
			}
		}
		
		for (int i = 6; i < 10; i++) {
			for (int j = 16; j < 19; j++) {
				Layer1[i][j] = BRICK;
			}
		}
		for (int i = 36; i < 42; i++) {
			for (int j = 19; j < 22; j++) {
				Layer1[i][j] = BRICK;
			}
		}
		for (int i = 36; i < 40; i++) {
			for (int j = 16; j < 19; j++) {
				Layer1[i][j] = BRICK;
			}
		}
		
		//Boss layer [43-87][24-48]
		for(int i = 44; i < 87; i++) {
			for( int j = 0; j < 24; j++) {
				Layer1[43][j] = BAR;
				Layer1[i][j] = COURT;
			}
		}
		
		return Layer1;
	}

	//setting layer 2 and at the same time setting bounds for areas that aren't
	// meant to be accessed
	public Image[][] setLayer2() {
		

		for (int i = 0; i < 6; i++) {
			Layer2bounds[i][16] = 1;
			Layer2[i][16] = WATER2;
		}
		for (int i = 10; i < 36; i++) {
			Layer2bounds[i][16] = 1;
			Layer2[i][16] = WATER2;
		}
		for (int i = 40; i < 42; i++) {
			Layer2bounds[i][16] = 1;
			Layer2[i][16] = WATER2;
		}
		
		//brick walk boundaries
		for (int i = 0; i < 6; i++) {
			Layer2bounds[i][18] = 1;
		}
		
		
		
		for (int i = 16; i < 23; i++) {
			Layer2bounds[10][i] = 1;
			Layer2bounds[35][i] = 1;
		}
		
		for ( int i = 0; i < 10; i++) {
			Layer2bounds[i][22] = 1;
		}
		
		for (int i = 36; i < 43; i++) {
			Layer2bounds[i][22] = 1;
		}
		Layer2bounds[5][17] = 1;
		Layer2bounds[5][16] = 1;
		
		Layer2bounds[40][16] = 1;
		Layer2bounds[40][17] = 1;
		Layer2bounds[40][18] = 1;
		Layer2bounds[41][18] = 1;
		Layer2bounds[42][18] = 1;

		Layer2[24][5] = WATER7;
		Layer2bounds[24][5] = 1;
		for (int i = 25; i < 43; i++) {
			for (int j = 0; j < 5; j++) {	
				Layer2[24][j] = WATER4;
				Layer2[i][5] = WATER8;
				Layer2[i][j] = WATER5;
				Layer2bounds[24][j] = 1;
				Layer2bounds[i][5] = 1;
				Layer2bounds[i][j] = 1;
			}
		}
		return Layer2;
	}

	public Image[][] setLayer3() {

		Layer3[1][3] = HOUSE;
		Layer3[1][9] = HOUSE;

		return Layer3;
	}
	
	public Rectangle[] getObjectBounds() {

		for (int i = 0; i < numObjects; i++) {
			objectBounds[0] = house1Bounds;
			objectBounds[1] = house2Bounds;
		}

		return objectBounds;
	}
	
	
}
