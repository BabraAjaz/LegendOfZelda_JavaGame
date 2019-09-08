package present;

import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;

public class Animation {
	// Inspired by RealTutsGML Java Game Programming #13 - Animations
	// https://www.youtube.com/watch?v=kzgNCEWUqUs&t=8s
	
	private int speed = 1;
	private int scene;
	private int index = 0;
	private int step = 0;
	private boolean complete = false;
	private Image[] imageCycle;
	private Image currentImage;

	// Updates an array with animation set and speed of process
	public Animation (int rate, Image[] animationSheet) {
		speed = rate;
		int i = 0;
		scene = animationSheet.length;
		imageCycle = new Image[scene];
		while (i < scene) {
			imageCycle[i] = animationSheet[i];
			i++;
		}
	}

	// Cycles through images to create animation
	public void runAnimation() {
		index++;
		if (index > speed) {
			index = 0;
			updateScene();
		}
	}
	
	// Waits for a delay before changing image
	public void updateScene() {

		if (step < scene) {
			currentImage = imageCycle[step];
			step++;
			complete = false;
		}
		else {
			complete = true;
			step = 0;
		}	

	}
	
	public boolean getComplete() {
		return complete;
	}
	public void changeAnimationSpeed(int rate) {
		speed = rate;
	}
	

	
	// Render functions (Meant to be transferred to the view)
	public void drawAnimation(Graphics g, int x, int y) {
		g.drawImage(currentImage,x,y,null);
	}

	public void drawAnimation(Graphics g, int x, int y, int scaleX, int scaleY) {
		g.drawImage(currentImage,x,y, scaleX, scaleY, null);
	}



}
