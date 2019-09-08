package present;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class PickUps{

	private int pickUpPoints = 0;
	private Image BLUE = new ImageIcon(this.getClass().getResource("/assets/Flowers/flowerBlue.png")).getImage();
	private Image BLACK = new ImageIcon(this.getClass().getResource("/assets/Flowers/flowerBlack.png")).getImage();
	private Image WHITE = new ImageIcon(this.getClass().getResource("/assets/Flowers/flowerWhite.png")).getImage();
	private Image ORANGE = new ImageIcon(this.getClass().getResource("/assets/Flowers/ORANGE.png")).getImage();
	private Image RED = new ImageIcon(this.getClass().getResource("/assets/Flowers/flowerRed.png")).getImage();

	private int maxOrange = 40;
	private int maxBlack = 3;
	private int maxWhite = 1;
	private int maxRed = 7;
	private int maxBlue = 7;
	private int minCol = 2;
	private int minRow = 2;
	private int maxCol = 80;
	private int maxRow = 21;
	private static boolean loaded = false;

	private int [] randomRow = new int[maxOrange];
	private int[] randomCol = new int[maxOrange];
	private int[] collide = new int[maxOrange];
	public int[] retrieved = new int[maxOrange];
	private int[] placed = new int[maxOrange];
	private int[] badIndex = new int[maxOrange];

	private int [] randomRowB = new int[maxBlack];
	private int[] randomColB = new int[maxBlack];
	private int[] collideB = new int[maxBlack];
	private int[] retrievedB = new int[maxBlack];
	private int[] placedB = new int[maxBlack];
	private int[] badIndexB = new int[maxBlack];

	private int [] randomRowBl = new int[maxBlue];
	private int[] randomColBl = new int[maxBlue];
	private int[] collideBl = new int[maxBlue];
	private int[] retrievedBl = new int[maxBlue];
	private int[] placedBl = new int[maxBlue];
	private int[] badIndexBl = new int[maxBlue];

	private int[] randomRowR = new int[maxRed];
	private int[] randomColR = new int[maxRed];
	private int[] collideR = new int[maxRed];
	private int[] retrievedR = new int[maxRed];
	private int[] placedR = new int[maxRed];
	private int[] badIndexR = new int[maxRed];

	private int randomRowW;
	private int randomColW;
	private int collideW;
	private int retrievedW;
	private int placedW;
	private int badIndexW;

	public PickUps() {
	}

	public int getMaxOrange() {
		return maxOrange;
	}

	public int getPickUpPoints() {
		return pickUpPoints;
	}

	public void setPickUpPoints() {
		this.pickUpPoints++;
	}

	public int[] getRandomRow() {
		return this.randomRow;
	}

	public int[] getRandomCol() {
		return this.randomCol;
	}

	public int[] getRandomRowB() {
		return this.randomRowB;
	}

	public int[] getRandomColB() {
		return this.randomColB;
	}

	public int[] getRandomRowBl() {
		return this.randomRowBl;
	}

	public int[] getRandomColBl() {
		return this.randomColBl;
	}

	public int[] getRandomRowR() {
		return this.randomRowR;
	}

	public int[] getRandomColR() {
		return this.randomColR;
	}

	public void drawOrange() {
		reinitialize(getRandomCol());
		reinitialize(getRandomRow());
		reinitialize(getCollide());
		reinitialize(getPlaced());
		reinitialize(getPlaced());
		for(int i = 0; i < maxOrange; i++) {
			randomRow[i] = (int)(Math.random()*((maxRow - minRow) + 1) + minRow);
			randomCol[i] = (int)(Math.random()*((maxCol - minCol) + 1) + minCol);
		}
	}

	public void drawWhite() {
		setRandomRowW(0);
		setRandomColW(0);
		setCollideW(0);
		setRetrievedW(0);
		setPlacedW(0);
		randomRowW = (int)(Math.random()*((maxRow - minRow) + 1) + minRow);
		randomColW = (int)(Math.random()*((maxCol - minCol) + 1) + minCol);

	}

	public void drawBlack() {
		reinitialize(getRandomColB());
		reinitialize(getRandomRowB());
		reinitialize(getCollideB());
		reinitialize(getPlacedB());
		reinitialize(getPlacedB());
		for(int i = 0; i < maxBlack; i++) {
			randomRowB[i] = (int)(Math.random()*((maxRow - minRow) + 1) + minRow);
			randomColB[i] = (int)(Math.random()*((maxCol - minCol) + 1) + minCol);
		}

	}

	public void drawBlue() {
		reinitialize(getRandomColBl());
		reinitialize(getRandomRowBl());
		reinitialize(getCollideBl());
		reinitialize(getPlacedBl());
		reinitialize(getPlacedBl());

		randomRowBl[0] = 10;
		randomColBl[0] = 1;
		randomRowBl[6] = 10;
		randomColBl[6] = 41;
		for(int i = 1; i < maxBlue-1; i++) {
			randomRowBl[i] = (int)(Math.random()*((maxRow - minRow) + 1) + minRow);
			randomColBl[i] = (int)(Math.random()*((maxCol - minCol) + 1) + minCol);
		}

	}

	public void drawRed() {
		reinitialize(getRandomColR());
		reinitialize(getRandomRowR());
		reinitialize(getCollideR());
		reinitialize(getPlacedR());
		reinitialize(getPlacedR());
		
		randomRowR[0] = 12;
		randomColR[0] = 1;
		randomRowR[6] = 12;
		randomColR[6] = 41;
		for(int i = 1; i < maxRed-1; i++) {
			randomRowR[i] = (int)(Math.random()*((maxRow - minRow) + 1) + minRow);
			randomColR[i] = (int)(Math.random()*((maxCol - minCol) + 1) + minCol);
		}
	}

	public void reinitialize(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	public Rectangle getBounds() {
		return new Rectangle(650,300,28,28);
	}

	public Image Energy() {
		return ORANGE;
	}

	public Image Ice() {
		return BLUE;
	}

	public Image Fire() {
		return RED;
	}

	public Image FullEnergy() {
		return WHITE;
	}

	public Image minusHealth() {
		return BLACK;
	}

	public Rectangle getBounds(int x, int y) {
		return new Rectangle(x*32,y*32,32,32);
	}

	public static boolean isLoaded() {
		return loaded;
	}

	public static void setLoaded(boolean loaded) {
		PickUps.loaded = loaded;
	}

	public int getMaxBlack() {
		return maxBlack;
	}

	public void setMaxBlack(int maxBlack) {
		this.maxBlack = maxBlack;
	}

	public int getMaxWhite() {
		return maxWhite;
	}

	public void setMaxWhite(int maxWhite) {
		this.maxWhite = maxWhite;
	}

	public int getMaxRed() {
		return maxRed;
	}

	public void setMaxRed(int maxRed) {
		this.maxRed = maxRed;
	}

	public int getMaxBlue() {
		return maxBlue;
	}

	public void setMaxBlue(int maxBlue) {
		this.maxBlue = maxBlue;
	}

	public int[] getCollide() {
		return collide;
	}

	public void setCollide(int[] collide) {
		this.collide = collide;
	}

	public int[] getPlaced() {
		return placed;
	}

	public void setPlaced(int[] placed) {
		this.placed = placed;
	}

	public int[] getCollideB() {
		return collideB;
	}

	public void setCollideB(int[] collideB) {
		this.collideB = collideB;
	}

	public int[] getPlacedB() {
		return placedB;
	}

	public void setPlacedB(int[] placedB) {
		this.placedB = placedB;
	}

	public int[] getCollideR() {
		return collideR;
	}

	public void setCollideR(int[] collideR) {
		this.collideR = collideR;
	}

	public int[] getPlacedR() {
		return placedR;
	}

	public void setPlacedR(int[] placedR) {
		this.placedR = placedR;
	}

	public int[] getCollideBl() {
		return collideBl;
	}

	public void setCollideBl(int[] collideBl) {
		this.collideBl = collideBl;
	}

	public int[] getPlacedBl() {
		return placedBl;
	}

	public void setPlacedBl(int[] placedBl) {
		this.placedBl = placedBl;
	}

	public int[] getRetrieved() {
		return retrieved;
	}

	public void setRetrieved(int[] retrieved) {
		this.retrieved = retrieved;
	}

	public int[] getRetrievedB() {
		return retrievedB;
	}

	public void setRetrievedB(int[] retrievedB) {
		this.retrievedB = retrievedB;
	}

	public int[] getRetrievedBl() {
		return retrievedBl;
	}

	public void setRetrievedBl(int[] retrievedBl) {
		this.retrievedBl = retrievedBl;
	}

	public int[] getRetrievedR() {
		return retrievedR;
	}

	public void setRetrievedR(int[] retrievedR) {
		this.retrievedR = retrievedR;
	}

	public int getRandomRowW() {
		return randomRowW;
	}

	public void setRandomRowW(int randomRowW) {
		this.randomRowW = randomRowW;
	}

	public int getRandomColW() {
		return randomColW;
	}

	public void setRandomColW(int randomColW) {
		this.randomColW = randomColW;
	}

	public int getCollideW() {
		return collideW;
	}

	public void setCollideW(int collideW) {
		this.collideW = collideW;
	}

	public int getRetrievedW() {
		return retrievedW;
	}

	public void setRetrievedW(int retrievedW) {
		this.retrievedW = retrievedW;
	}

	public int getPlacedW() {
		return placedW;
	}

	public void setPlacedW(int placedW) {
		this.placedW = placedW;
	}

	public int[] getBadIndex() {
		return badIndex;
	}

	public void setBadIndex(int[] badIndex) {
		this.badIndex = badIndex;
	}

	public int[] getBadIndexB() {
		return badIndexB;
	}

	public void setBadIndexB(int[] badIndexB) {
		this.badIndexB = badIndexB;
	}

	public int[] getBadIndexBl() {
		return badIndexBl;
	}

	public void setBadIndexBl(int[] badIndexBl) {
		this.badIndexBl = badIndexBl;
	}

	public int[] getBadIndexR() {
		return badIndexR;
	}

	public void setBadIndexR(int[] badIndexR) {
		this.badIndexR = badIndexR;
	}

	public int getBadIndexW() {
		return badIndexW;
	}

	public void setBadIndexW(int badIndexW) {
		this.badIndexW = badIndexW;
	}

}