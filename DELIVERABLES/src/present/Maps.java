package present;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

import javax.swing.*;

public class Maps {

	private Present present = new Present();
	private Maze maze = new Maze();
	private Future future = new Future();
	private int stage;

	private Rectangle labdoor = new Rectangle(1250, 595, 26, 45);
	private Rectangle goldoor = new Rectangle(1344, 320, 32, 96);
	private Rectangle bossdoor = new Rectangle(1344,608, 32, 96);
	
	private static int loaded = 0;
	private static int loadComplete;
	private boolean complete;
	
	private ArrayList<Image[][]> layeredMap = new ArrayList<Image[][]>();
	private ArrayList<Rectangle> bounds = new ArrayList<Rectangle>();//boundaries
	private ArrayList<Rectangle> objects = new ArrayList<Rectangle>();

	public Maps() {
		
	}
	
	public void setStage(int stage) {
		this.stage = stage;
	}
	//LOADING arrays with images of the maps so that the gamescreen class
	//can access it and print according to the level type
	public ArrayList<Image[][]> loadArrays() {
		if (stage == 1) {
			layeredMap.clear();
			layeredMap.add(present.setLayer1()); 
			layeredMap.add(present.setLayer2()); 
			layeredMap.add(present.setLayer3()); 
			//load completes are flags read by the model so that these arrayLists are
			//loaded once
			setLoadComplete(1);
			loaded = 1;
			if (loaded == 1 && getLoadComplete() == 1) {
				setLoadComplete(2);
			}
		} else if (stage == 2) {
			bounds.clear();
			objects.clear();
			layeredMap.clear();
			layeredMap.add(maze.setLayer1());
			layeredMap.add(maze.setLayer2());
			layeredMap.add(maze.setLayer3());
			setLoadComplete(1);
			loaded = 1;
			if (loaded == 1 && getLoadComplete() == 1) {
				setLoadComplete(2);
			}
		} else if (stage == 3 || stage == 4) {
			bounds.clear();
			objects.clear();
			layeredMap.clear();
			layeredMap.add(future.setLayer1());
			layeredMap.add(future.setLayer2());
			layeredMap.add(future.setLayer3());
			setLoadComplete(1);
			loaded = 1;
			if (loaded == 1 && getLoadComplete() == 1) {
				setLoadComplete(2);
			}
		}
		return layeredMap;
	}

	public void loadLevelCollisions(){
		for (int i = 0; i < 43; i++) {
			for (int j = 0; j < 23; j++) {
				
				if(stage == 1 && getLoadComplete() == 2) {
					if (present.setLayer2bounds()[i][j] == 1) {
						bounds.add(new Rectangle(i*32,j*32,32,32));
					}
					for (int k = 0; k < 2; k++) {
						objects.add(present.getObjectBounds()[k]);
					}
				} 
				if (stage == 2 && getLoadComplete() == 2) {
					if(maze.readTextMap()[j][i] == 1) {
						bounds.add(new Rectangle(i*maze.getTileSize(), j*maze.getTileSize(), maze.getTileSize(), maze.getTileSize()));
					}
				} 
				
				if (stage == 3 && getLoadComplete() == 2) {
					if (future.Layer2bounds[i][j] == 1) {
						bounds.add(new Rectangle(i*32,j*32,32,32));
					}
					for (int k = 0; k < future.numObjects; k++) {
						objects.add(future.getObjectBounds()[k]);
					}
				}
			}
		}

		setLoadComplete(3);
	}

	public static int getLoadComplete() {
		return loadComplete;
	}

	public static void setLoadComplete(int loadComplete) {
		Maps.loadComplete = loadComplete;
	}

	public Rectangle getLabdoor() {
		return labdoor;
	}

	public void setLabdoor(Rectangle labdoor) {
		this.labdoor = labdoor;
	}

	public Rectangle getGoldoor() {
		return goldoor;
	}

	public void setGoldoor(Rectangle goldoor) {
		this.goldoor = goldoor;
	}

	public Rectangle getBossdoor() {
		return bossdoor;
	}

	public void setBossdoor(Rectangle bossdoor) {
		this.bossdoor = bossdoor;
	}

	public ArrayList<Rectangle> getBounds() {
		return bounds;
	}

	public void setBounds(ArrayList<Rectangle> bounds) {
		this.bounds = bounds;
	}

	public ArrayList<Rectangle> getObjects() {
		return objects;
	}

	public void setObjects(ArrayList<Rectangle> objects) {
		this.objects = objects;
	}

}
