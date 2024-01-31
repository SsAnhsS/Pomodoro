package business;

import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Pomodoro {
	
	public static final int second = 60;
	private MP3Player mp3Player;
	
	private Thread countdownThread;
	
	private long position;
	
	private SimpleIntegerProperty time;
	private SimpleIntegerProperty conzentrationTime;
	private SimpleIntegerProperty pauseTime;
	private SimpleIntegerProperty set;
	private SimpleBooleanProperty isPlaying;
	
	public Pomodoro() {
		mp3Player = new MP3Player();
		
		time = new SimpleIntegerProperty();
		conzentrationTime = new SimpleIntegerProperty();
		pauseTime = new SimpleIntegerProperty();
		set = new SimpleIntegerProperty();
		isPlaying = new SimpleBooleanProperty();
		
		conzentrationTime.set(25*second);
		pauseTime.set(5*second);
		set.set(2);
		isPlaying.set(false);
		
		time.set(conzentrationTime.getValue());
		this.position = time.getValue();
	}
	
	public class CountDownThread extends Thread{
		public void run() {
			
			while(!isInterrupted()) {
				int pos = (int) (position);
				
				for(int i = pos; i >= 0; i--) {
					time.set(i);
					System.out.println(time.getValue());
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
		//mp3Player.play();
		countdownThread = new CountDownThread();
		countdownThread.start();
		isPlaying.set(true);
	}
	
	public void pause() {
		//mp3Player.pause();
		countdownThread.interrupt();
		this.position = time.getValue();
		System.out.println(time.getValue());
		isPlaying.set(false);
	}
	
	/*
	 * set repeat with number of set 
	 * concentiert and pause with the time setting
	 * 
	 */
	
	public SimpleIntegerProperty timeProperty() {
		return time;
	}
	
	public SimpleBooleanProperty playingProperty() {
		return isPlaying;
	}
}
