package present;

import java.awt.Image;
import java.io.*;
import javax.swing.ImageIcon;

public class Maze {

	
	///Change these arrays to be row by col rather than width by height
	private Image[][] Layer1 = new Image [87][24];
	private Image[][] Layer2 = new Image[87][24];
	private Image[][] Layer3 = new Image[87][24];
	private final int mapRows = 24;
	private final int mapCols = 87;
	static boolean read = false;
	
	private int mapHeight;
	private int mapWidth;
	private int[][] mapBlocks;
	private int tileSize = 32;
	
	private Image FLOOR = new ImageIcon(this.getClass().getResource("/assets/maze.png")).getImage();
	private Image BLOCKS = new ImageIcon(this.getClass().getResource("/assets/blockgrey.png")).getImage();
	private Image GOLDEN = new ImageIcon(this.getClass().getResource("/assets/gold.png")).getImage();

	public Maze() {
	}
 
	/******* getters *******/
	public String getName() {
		return "MAZE";
	}
	
	public int getLayers() {
		return 1;
	}
	
	public int getTileSize() {
		return tileSize;
	}
	
	public int[][] getMapBlocks(){
		return this.mapBlocks;
	}

	public int[][] readTextMap() {
		try {
			
			BufferedReader br = new BufferedReader(new InputStreamReader(
	                  this.getClass().getResourceAsStream("maze.txt")));
			mapWidth = Integer.parseInt(br.readLine());
			mapHeight = Integer.parseInt(br.readLine());
			mapBlocks = new int[mapHeight][mapWidth];
			
			String delimiters = ",";
			for (int row = 0; row < mapHeight; row++) {
				String line = br.readLine();
				String[] tokens = line.split(delimiters);
				for (int col = 0; col < mapWidth; col++) {
					mapBlocks[row][col] = Integer.parseInt(tokens[col]);
				}
			}
			br.close();
		} catch (IOException e) {
			System.out.println("not found");
		} catch (NumberFormatException ex) {
			
		}
		return mapBlocks;
	}


	/***BASE LAYER***/
	public Image[][] setLayer1(){
		for (int i = 0; i < 43; i++) {
			for (int j = 0; j < 24; j++) {
				Layer1[i][j] = FLOOR;
			}
		}
		return Layer1;
	}

	/***MAZE/BOUNDARY LAYER***/
	public Image[][] setLayer2(){
		if(read == false) {
			readTextMap();
			read = true;
		}
		for (int i = 0; i < 23; i++) {
			for (int j = 0; j < 43; j++) {
				
				if (mapBlocks[i][j] == 1) {
					Layer2[j][i] = BLOCKS;
				}
				if(mapBlocks[i][j] == 2) {
					Layer2[j][i] = GOLDEN;
				}
			}
		}
		return Layer2;
	}

	public Image[][] setLayer3(){
		
		return Layer3;
	}

	public int getMapRows() {
		return mapRows;
	}

	public int getMapCols() {
		return mapCols;
	}
}
