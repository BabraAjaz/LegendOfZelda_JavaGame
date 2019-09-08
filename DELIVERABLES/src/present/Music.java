package present;

import java.io.BufferedInputStream;
import java.io.File;
import java.net.URL;
import java.util.*;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Music implements Runnable {

	private boolean loop = false;
	private Model data;
	private View view;
	private Clip clip;
	private int prevIndex = 0;
	private static ArrayList<String> trackFiles = new ArrayList<String>();
	private static ArrayList<String> musicList = new ArrayList<String>();
	private int audioSelect = 0;
	private int track;
	private Clip soundEffects;

	private int stage = 0;
	private int trackIndex = 0;
	private boolean prevMusic = false;
	private boolean currentMusic = false;

	public Music(Model data, View view) {
		this.data = data;
		trackIndex = this.data.getStage(); 
		this.view = view;
		currentMusic = this.view.music;
		start();
		

	}
	@Override
	public void run() {
		{
			addTrackFiles("start","Level1","Level2","Level3","Boss");
			playMusic(trackIndex);
			while (loop) {
				updateMusic();
				//System.out.println(trackIndex);
				if (!view.music) {
					clip.stop();
					
				}
				else {
					
					if (trackIndex == 0 && stage == 0) {
						
						stopClip();
						playMusic(trackIndex); 
						stage = 1;
					}
					else if (trackIndex == 1 && stage == 1) {
						
						stopClip();
						playMusic(trackIndex);
						stage = 2;
					}
					else if (trackIndex == 2 && stage == 2) {
						
						stopClip();
						playMusic(trackIndex);
						stage = 3;
					}

					else if (trackIndex == 3 && stage == 3) {
						
						stopClip();
						playMusic(trackIndex);
						stage = 4;
					}

					else if (trackIndex == 4 && stage == 4) {
						
						stopClip();
						playMusic(trackIndex);
						stage = 5;
					}
				}
					try {
						Thread.sleep(3);
					} catch (InterruptedException e) {
						e.printStackTrace();
						loop = false;
					}
				}
			}

		}

	

	public void updateMusic() {
		prevMusic = currentMusic;
		prevIndex = trackIndex;
		trackIndex = this.data.getStage();
		currentMusic = this.view.music;
		if (prevIndex != trackIndex) {
			stage = trackIndex;
		}
		if (prevMusic != currentMusic) {
			stage = trackIndex;
		}


	}

	public void start() {
		loop = true;
		Thread musicPlayer = new Thread(this);
		musicPlayer.run();
	}

	public void playMusic(int trackIndex) {
		try {
			BufferedInputStream stream = new BufferedInputStream(this.getClass().getResourceAsStream(trackFiles.get(trackIndex)));
			AudioInputStream sound = 
					AudioSystem.getAudioInputStream(stream);
			clip = AudioSystem.getClip();
			clip.open(sound);
			
			
			clip.start();
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			//clip.loop(Clip.LOOP_CONTINUOUSLY);

			//Thread.sleep(clip.getMicrosecondLength()/1000);
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error");
			System.out.println("Working Directory = " +
					System.getProperty("user.dir"));
		}

	}

	public void stopClip() {
		clip.close();
	}

	public static void addTrackFiles(String... files) {
		for (int i = 0; i < files.length; i ++) {
			String temp = "/music/" + files[i] + ".wav";
			
			trackFiles.add(temp);
		}

	} 

	public static void addMusicFiles(String... files) {
		for (int i = 0; i < files.length; i ++) {
			String temp = "/audio/" + files[i] + ".wav";
			musicList.add(temp);
		}

	} 

	public void playMusic(String fileName) {
		try {
			BufferedInputStream stream = new BufferedInputStream(this.getClass().getResourceAsStream("/audio/" + fileName + ".wav"));
			AudioInputStream sound = AudioSystem.getAudioInputStream(stream);
			soundEffects = (AudioSystem.getClip());
			soundEffects.open(sound);
			
			soundEffects.start();
			//clip.loop(Clip.LOOP_CONTINUOUSLY);

			//clip.close();
		} catch (Exception e) {

			e.printStackTrace();
			System.out.println("Error");
			System.out.println("Working Directory = " +
					System.getProperty("user.dir"));
		}

	}


	public void addMusicFile(String fileName) {
		musicList.add(fileName);
	}

}


