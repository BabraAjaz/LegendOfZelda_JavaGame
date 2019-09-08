package present;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Present {// extends Maps{

	private int numObjects = 9;
	private Image[][] Layer1 = new Image[87][24];
	private Image[][] Layer2 = new Image[87][24];
	private Image[][] Layer3 = new Image[87][24];
	private int[][] Layer2bounds = new int[43][24];
	
	private Rectangle [] objectBounds = new Rectangle[2];
	private Rectangle houseBounds = new Rectangle(76,64,137,165);
	private Rectangle labBounds = new Rectangle(1195,416,137,165);

	private Image TERRAIN = new ImageIcon(this.getClass().getResource("/assets/terrain.png")).getImage();
	private Image BLOCK = new ImageIcon(this.getClass().getResource("/assets/block.png")).getImage();
	private Image HOUSE = new ImageIcon(this.getClass().getResource("/assets/house.png")).getImage();
	private Image LAB = new ImageIcon(this.getClass().getResource("/assets/lab.png")).getImage();
	private Image HOLE5 = new ImageIcon(this.getClass().getResource("/assets/hole5.png")).getImage();
	private Image HOLE8 = new ImageIcon(this.getClass().getResource("/assets/hole8.png")).getImage();
	private Image HOLE4 = new ImageIcon(this.getClass().getResource("/assets/hole4.png")).getImage();
	private Image HOLE6 = new ImageIcon(this.getClass().getResource("/assets/hole6.png")).getImage();
	private Image HOLE2 = new ImageIcon(this.getClass().getResource("/assets/hole2.png")).getImage();
	private Image HOLE3 = new ImageIcon(this.getClass().getResource("/assets/hole3.png")).getImage();
	private Image HOLE7 = new ImageIcon(this.getClass().getResource("/assets/hole7.png")).getImage();
	private Image TREE = new ImageIcon(this.getClass().getResource("/assets/tree.png")).getImage();
	private Image TREE2 = new ImageIcon(this.getClass().getResource("/assets/tree2.png")).getImage();

	public Present() {
		
	}

	public String getName() {
		return "PRESENT";
	}

	public int getLayers() {
		return 2;
	}

	/*** TERRAIN Layer ***/
	public Image[][] setLayer1() {
		for (int i = 0; i < 43; i++) {
			for (int j = 0; j < 24; j++) {
				Layer1[i][j] = TERRAIN;
			}
		} // add blops and speckles to the terrain later
		return Layer1;
	}

	/*** HOLES Layer ***/
	public Image[][] setLayer2() {

		// Top right Holes
		for(int i = 0; i < 24; i++) {
			Layer2[i][0] = BLOCK;
			Layer2[0][i] = BLOCK;
			Layer2[42][i] = BLOCK;
		}
		
		Layer2[24][7] = HOLE7;
		for (int i = 25; i < 43; i++) {
			for (int j = 0; j < 7; j++) {
				Layer2[24][j] = HOLE4;
				Layer2[i][7] = HOLE8;
				Layer2[i][j] = HOLE5;
			}
		}
		for (int i = 5; i < 43; i++) {
			Layer2[i][22]=BLOCK;
		}
		Layer2[5][13] = HOLE3;
		for (int i = 0; i < 5; i++) {
			for (int j = 14; j < 24; j++) {
				Layer2[i][13] = HOLE2;
				Layer2[5][j] = HOLE6;
				Layer2[i][j] = HOLE5;
			}
		}
		return Layer2;
	}
	public int[][] setLayer2bounds(){
		
		for(int i = 0; i < 24; i++) {
			Layer2bounds[i][0] = 1;
			Layer2bounds[0][i] = 1;
			Layer2bounds[42][i] = 1;
		}
		
		for (int i = 5; i < 43; i++) {
			Layer2bounds[i][22]=1;
		}

		Layer2bounds[24][7] = 1;
		for (int i = 25; i < 43; i++) {
			for (int j = 0; j < 7; j++) {
				Layer2bounds[24][j] = 1;
				Layer2bounds[i][7] = 1;
				Layer2bounds[i][j] = 1;
			}
		}

		Layer2bounds[5][13] = 1;
		for (int i = 0; i < 5; i++) {
			for (int j = 14; j < 24; j++) {	
				Layer2bounds[i][13] = 1;
				Layer2bounds[5][j] = 1;
				Layer2bounds[i][j] = 1;
			}
		}
		return Layer2bounds;
	}

	/** HOUSES **/
	public Image[][] setLayer3() {

		Layer3[2][2] = HOUSE; // 196 x 274
		Layer3[37][13] = LAB; // 196 x 274
		
		Layer3[25][20] = TREE;
		Layer3[4][14] = TREE2;
		Layer3[7][14] = TREE2;
		Layer3[10][14] = TREE2;
		Layer3[36][3] = TREE2;
		Layer3[35][7] = TREE2;
		Layer3[1][5] = TREE;
		return Layer3;
	}
	
	public Rectangle[] getObjectBounds() {
		
		for (int i = 0; i < numObjects; i++) {
			objectBounds[0] = houseBounds;
			objectBounds[1] = labBounds;
		}
		
		return objectBounds;
	}
}
