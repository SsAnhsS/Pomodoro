package business;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pomodoro {
	
	private MP3Player mp3Player;
	
	private Thread countdownThread;
	
	private long position;
	
	private SimpleIntegerProperty time;
	private SimpleIntegerProperty conzentrationTime;
	private SimpleIntegerProperty pauseTime;
	private SimpleIntegerProperty set;
	private SimpleBooleanProperty isConzentration;
	
	public Pomodoro() {
		mp3Player = new MP3Player();
		
		time = new SimpleIntegerProperty();
		conzentrationTime = new SimpleIntegerProperty();
		pauseTime = new SimpleIntegerProperty();
		set = new SimpleIntegerProperty();
		isConzentration = new SimpleBooleanProperty();
		
		conzentrationTime.set(25);
		pauseTime.set(5);
		set.set(2);
		isConzentration.set(true);
		
		time.set(conzentrationTime.getValue());
		this.position = time.getValue();
	}
	
	public class CountDownThread extends Thread{
		public void run() {
			
			while(!isInterrupted()) {
				int pos = (int) (position / 1000);
				
				for(int i = pos; i >= 0; i--) {
					time.set(i);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						interrupt();
					}
				}
			}
		}
	}
	
	public void play() {
		mp3Player.play();
		countdownThread = new CountDownThread();
		countdownThread.start();
	}
	
	public void pause() {
		mp3Player.pause();
		countdownThread.interrupt();
		this.position = time.getValue();
	}
	
	/*
	 * set repeat with number of set 
	 * concentiert and pause with the time setting
	 * 
	 */
}
