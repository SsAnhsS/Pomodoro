package business;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import presentation.setting.ThemeName;

public class Pomodoro {
	
	public static final int second = 60;
	
	private MP3Player mp3Player;
	private SoundManager soundManager;
	
	private Thread countdownThread;
	
	private int timePosition;
	
	private SimpleIntegerProperty time;
	private SimpleIntegerProperty concentrationTime;
	private SimpleIntegerProperty pauseTime;
	private SimpleIntegerProperty sessionNumber;
	private SimpleBooleanProperty isCounting;
	private SimpleStringProperty theme;
	
	private SimpleObjectProperty<Track> pauseSound;
	private SimpleObjectProperty<Track> notiSound;
	private SimpleObjectProperty<Playlist> bgSoundPlaylist;
	private SimpleBooleanProperty singlePlay;
	
	public Pomodoro() {
		soundManager = new SoundManager();
		
		time = new SimpleIntegerProperty();
		concentrationTime = new SimpleIntegerProperty();
		pauseTime = new SimpleIntegerProperty();
		sessionNumber = new SimpleIntegerProperty();
		isCounting = new SimpleBooleanProperty();
		theme = new SimpleStringProperty();
		
		concentrationTime.set(25*second);
		pauseTime.set(5*second);
		sessionNumber.set(2);
		isCounting.set(false);
		theme.set(ThemeName.TOMATO.getFile());
		
		pauseSound = new SimpleObjectProperty<Track>();
		notiSound = new SimpleObjectProperty<Track>();
		bgSoundPlaylist = new SimpleObjectProperty<Playlist>();
		singlePlay = new SimpleBooleanProperty();
		
		pauseSound.set(new Track(soundManager.getSoundFile("Morning Funny Beat")));
		notiSound.set(new Track(soundManager.getSoundFile("Bicycle Bell")));
		
		singlePlay.set(true);
		
		time.set(concentrationTime.getValue());
		this.timePosition = time.getValue();
	}
	
	public void setMP3Player(MP3Player mp3Player) {
		this.mp3Player = mp3Player;
		soundManager = mp3Player.getSoundManager();
		mp3Player.currentPlaylistProperty().set(bgSoundPlaylist.getValue());
	}
	
	public class CountDownThread extends Thread{
		
		public void run() {
			countdownTime(timePosition);
		}
		
		public void countdownTime(int countdownTime) {
			for (int i = countdownTime; i >= 0 && isCounting.getValue(); i--) {
				time.set(i);
				System.out.println(time.getValue());
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
			
//			// Check if it's time for a pause
//            if (isCounting.getValue()) {
//                // Decrease sessionNumber
//                sessionNumber.set(sessionNumber.getValue() - 1);
//
//                // If sessionNumber is still greater than 0, start a pause
//                if (sessionNumber.getValue() > 0) {
//                    startPauseTime();
//                }
//            }
		}
		
	}
	
//	// Method to start the countdown for the pause
//    private void startPauseTime() {
//        time.set(pauseTime.getValue());
//        this.timePosition = time.getValue();
//        countdownThread = new CountDownThread();
//        countdownThread.start();
//    }
	
//	// Add this method to start a new concentration session
//  public void startNewSession() {
//      time.set(concentrationTime.getValue());
//      this.timePosition = time.getValue();
//      play(); // Start the countdown for the new session
//  }
	
	public void play() {
		isCounting.set(true);
		countdownThread = new CountDownThread();
		countdownThread.start();
		mp3Player.select(notiSound.getValue());
	}
	
	public void pause() {
		isCounting.set(false);
		mp3Player.select(notiSound.getValue());
		if(countdownThread != null) {
			countdownThread.interrupt();
		}
		this.timePosition = time.getValue();
		System.out.println("Pause");
		System.out.println(time.getValue());
		
	}
	
	public SimpleIntegerProperty timeProperty() {
		return time;
	}
	
	public SimpleIntegerProperty conzentrationTimeProperty() {
		return concentrationTime;
	}
	
	public SimpleIntegerProperty pauseTimeProperty() {
		return pauseTime;
	}
	
	public SimpleIntegerProperty sessionNumberProperty() {
		return sessionNumber;
	}
	
	public SimpleStringProperty themeProperty() {
		return theme;
	}
	
	public SimpleBooleanProperty countingProperty() {
		return isCounting;
	}
	
	public void setThemeProperty(String themeName) {
		switch(themeName) {
		case "Tomato":
			theme.set(ThemeName.TOMATO.getFile());
			break;
		case "Apple":
			theme.set(ThemeName.APPLE.getFile());
			break;
		case "Blueberry":
			theme.set(ThemeName.BLUEBERRY.getFile());
			break;
		case "Carrot":
			theme.set(ThemeName.CARROT.getFile());
			break;
		case "Cherry":
			theme.set(ThemeName.CHERRY.getFile());
			break;
		case "Lemon":
			theme.set(ThemeName.LEMON.getFile());
			break;
		case "Olive":
			theme.set(ThemeName.OLIVE.getFile());
			break;
		case "Orange":
			theme.set(ThemeName.ORANGE.getFile());
			break;
		}
	}
	
	public SimpleObjectProperty <Track> pauseSoundProperty(){
		return pauseSound;
	}
	
	public SimpleObjectProperty <Track> notiSoundProperty(){
		return notiSound;
	}
	
	public SimpleObjectProperty <Playlist> bgSoundPlaylistProperty(){
		return bgSoundPlaylist;
	}
	
	public SimpleBooleanProperty singlePlayProperty() {
		return singlePlay;
	}
	
}
