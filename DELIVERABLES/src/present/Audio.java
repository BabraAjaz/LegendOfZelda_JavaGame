package present;

import java.io.*;
import java.util.*;

import javax.sound.midi.*;
import javax.sound.sampled.*;
import java.applet.*;
import java.net.*;


// Audio class contains sound effects as feedback in the game
public class Audio { 

	private static ArrayList<String> musicList = new ArrayList<String>();
	private int audioSelect = 0;
	private int track;
	private boolean playing;
	@SuppressWarnings("deprecation")
	private AudioClip clickSound;
	private AudioClip startSound;
	private AudioClip level1WalkSound;
	private AudioClip swordSlashSound;
	private AudioClip fireSound;
	private AudioClip iceSound;
	private AudioClip healthLossSound;
	private AudioClip healthGainSound;
	private AudioClip attackEnemy;
	private AudioClip damageTaken;
	private AudioClip shield;
	private AudioClip explode;




	public Audio() {
		soundEffect();
	}

	
	// Function loads the sounds 
	@SuppressWarnings("deprecation")
	public void soundEffect() {

		try {
			clickSound = Applet.newAudioClip(this.getClass().getResource("/audio/click.wav"));
			level1WalkSound = Applet.newAudioClip(this.getClass().getResource("/audio/mud.wav"));
			setSwordSlashSound(Applet.newAudioClip(this.getClass().getResource("/audio/swing.wav")));
			setExplode(Applet.newAudioClip(this.getClass().getResource("/audio/explode.wav")));
			setHealthGainSound(Applet.newAudioClip(this.getClass().getResource("/audio/heal.wav")));
			healthLossSound = Applet.newAudioClip(this.getClass().getResource("/audio/damage.wav"));
			setShield(Applet.newAudioClip(this.getClass().getResource("/audio/shield.wav")));
			setAttackEnemy(Applet.newAudioClip(this.getClass().getResource("/audio/attack.wav")));
			setDamageTaken(Applet.newAudioClip(this.getClass().getResource("/audio/damage.wav")));
		} catch(Exception e) {
			e.printStackTrace();
			System.out.println("An error has occured");
		}
	}



	public void restartClip() {
		getClip().close();
	}

	public boolean isPlaying() {
		return playing;
	}

	public boolean setPlaying(boolean playing) {
		this.playing = playing;
		return playing;
	}

	public AudioClip getClickSound() {
		return clickSound;
	}

	public void setClickButton(AudioClip clickSound) {
		this.clickSound = clickSound;
	}

	public Clip getClip() {
		return null;
		//return clip;
	}

	public void setClip(Clip clip) {
		//this.clip = clip;
	}

	public AudioClip getSwordSlashSound() {
		return swordSlashSound;
	}

	public void setSwordSlashSound(AudioClip swordSlashSound) {
		this.swordSlashSound = swordSlashSound;
	}

	public AudioClip getHealthGainSound() {
		return healthGainSound;
	}

	public void setHealthGainSound(AudioClip healthGainSound) {
		this.healthGainSound = healthGainSound;
	}

	public AudioClip getShield() {
		return shield;
	}

	public void setShield(AudioClip shield) {
		this.shield = shield;
	}

	public AudioClip getDamageTaken() {
		return damageTaken;
	}

	public void setDamageTaken(AudioClip damageTaken) {
		this.damageTaken = damageTaken;
	}

	public AudioClip getAttackEnemy() {
		return attackEnemy;
	}

	public void setAttackEnemy(AudioClip attackEnemy) {
		this.attackEnemy = attackEnemy;
	}

	public AudioClip getExplode() {
		return explode;
	}

	public void setExplode(AudioClip explode) {
		this.explode = explode;
	}
}
