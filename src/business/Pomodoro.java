package business;

import javafx.beans.property.SimpleIntegerProperty;

public class Pomodoro {
	
	private MP3Player mp3Player;
	
	private Thread countdownThread;
	
	private SimpleIntegerProperty time;
	
	public Pomodoro() {
		mp3Player = new MP3Player();
		
		time = new SimpleIntegerProperty();
		
	}
	
	public class CountDownThread extends Thread{
		public void run() {
			while(!isInterrupted()) {
				
			}
		}
	}
	
	public void play() {
		mp3Player.play();
	}
	
	public void pause() {
		mp3Player.pause();
	}
	
	/*
	 * set repeat with number of set 
	 * concentiert and pause with the time setting
	 * 
	 */
}
