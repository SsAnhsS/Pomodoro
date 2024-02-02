package business;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import presentation.setting.ThemeName;

/*
 * Pomodoro Klasse, um pomodoro application zu kontrollieren
 */
public class Pomodoro {
	
	public static final int DEFAULT_CONCENTATION_TIME = 60; //*25
	public static final int DEFAULT_PAUSE_TIME = 10; //5*60
	public static final int DEFAULT_NUMBER_SESSION = 2;
	
	private MP3Player mp3Player;
	private SoundManager soundManager;
	
	private Thread countdownThread;
	
	private int timePosition;
	
	private boolean isNewSession;
	private boolean isFocusTime;
	
	private SimpleIntegerProperty time;
	private SimpleIntegerProperty concentrationTime;
	private SimpleIntegerProperty pauseTime;
	private SimpleIntegerProperty numberSession;
	private SimpleBooleanProperty isCounting;
	private SimpleStringProperty theme;
	
	private SimpleObjectProperty<Track> relaxSound;
	private SimpleObjectProperty<Track> notiSound;
	private SimpleObjectProperty<Playlist> bgSoundPlaylist;
	private SimpleBooleanProperty singlePlay;
	
	public Pomodoro() {
		soundManager = new SoundManager();
		
		time = new SimpleIntegerProperty();
		concentrationTime = new SimpleIntegerProperty();
		pauseTime = new SimpleIntegerProperty();
		numberSession = new SimpleIntegerProperty();
		isCounting = new SimpleBooleanProperty();
		theme = new SimpleStringProperty();
		
		concentrationTime.set(DEFAULT_CONCENTATION_TIME);
		pauseTime.set(DEFAULT_PAUSE_TIME);
		numberSession.set(DEFAULT_NUMBER_SESSION);
		isCounting.set(false);
		theme.set(ThemeName.TOMATO.getFile());
		
		relaxSound = new SimpleObjectProperty<Track>();
		notiSound = new SimpleObjectProperty<Track>();
		bgSoundPlaylist = new SimpleObjectProperty<Playlist>();
		singlePlay = new SimpleBooleanProperty();
		
		relaxSound.set(new Track(soundManager.getSoundFile("Morning Funny Beat")));
		notiSound.set(new Track(soundManager.getSoundFile("Bicycle Bell")));
		
		Playlist defaultPlaylist = new Playlist("Background Sound", "src\\data\\playlists\\default-background-sound.m3u");
		bgSoundPlaylist.set(defaultPlaylist);
		
		singlePlay.set(true);
		
		time.set(concentrationTime.getValue());
		this.timePosition = time.getValue();
	}
	
	public void setMP3Player(MP3Player mp3Player) {
		this.mp3Player = mp3Player;
		soundManager = mp3Player.getSoundManager();
	}
	
	/**
	 * Count Down Thread
	 */
	public class CountDownThread extends Thread{

		public void run() {
			countdownTime(timePosition);
		}
		
		public void countdownTime(int countdownTime) {
			for (int i = countdownTime; i >= 0 && isCounting.getValue(); i--) {
				time.set(i);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					interrupt();
				}
			}
			
		}
		
	}
	
//	public void startNewSession() {
//        isNewSession = true;
//
//        while (numberSession.getValue() > 0) {
//            isFocusTime = true;
//            play();
//            try {
//                countdownThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            isFocusTime = false;
//            play();
//            try {
//                countdownThread.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//
//            numberSession.set(numberSession.getValue() - 1);
//        }
//	}
	
	
	/**
	 * Anfangen Count Down Thread
	 */
	public void play() {
		isCounting.set(true);
		
		//Notification sound absplien wenn startet cout down Thread
        mp3Player.playNotiSound(notiSound.getValue());
		playBackgroundMusic();
		
        mp3Player.play();
        
		countdownThread = new CountDownThread();
		countdownThread.start();

	}
	
	/**
	 * Schalten Zeit, ob in der conzentration Zeit oder Pause Zeit
	 */
	public void switchTime() {
		if(isFocusTime) {
        	time.set(concentrationTime.getValue());
            mp3Player.playNotiSound(notiSound.getValue());
    		playBackgroundMusic();
        }
        else {
        	time.set(pauseTime.getValue());
            mp3Player.playNotiSound(notiSound.getValue());
            mp3Player.play(relaxSound.getValue().getSoundFile());
        }
	}
	
	/**
	 * Abspielen Musik in der conzentratierte Zeit
	 */
	public void playBackgroundMusic() {
		mp3Player.currentPlaylistProperty().set(bgSoundPlaylist.getValue());
		
		if(singlePlay.getValue()) {
			if (mp3Player.currentPlaylistProperty().getValue().getTotal() > 0) {
	            Track firstTrack = mp3Player.currentPlaylistProperty().getValue().getTrack(0);
	            if (firstTrack != null) {
	                mp3Player.currentTrackProperty().set(firstTrack);
	            }
	        }
		}
		else {
			Playlist currentPlaylist = mp3Player.currentPlaylistProperty().getValue();
	        if (currentPlaylist != null) {
	            for (int i = 0; i < currentPlaylist.getTotal(); i++) {
	                Track currentTrack = currentPlaylist.getTrack(i);
	                if (currentTrack != null) {
	                    mp3Player.play(currentTrack.getSoundFile());
	                }
	            }
	        }
		}
	}
	
	
	/**
	 * Stopen Couwn Down Thread
	 */
	public void pause() {
		isCounting.set(false);
		countdownThread.interrupt();
		mp3Player.pause();
		this.timePosition = time.getValue();
	}
	
	/**
	 * Getter und Setter Methode
	 */
	public boolean getIsNewSession() {
		return isNewSession;
	}
	
	public boolean getIsFocusTime() {
		return isFocusTime;
	}
	
	public SimpleIntegerProperty timeProperty() {
		return time;
	}
	
	public SimpleIntegerProperty concentrationTimeProperty() {
		return concentrationTime;
	}
	
	public SimpleIntegerProperty pauseTimeProperty() {
		return pauseTime;
	}
	
	public SimpleIntegerProperty numberSessionProperty() {
		return numberSession;
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
	
	public SimpleObjectProperty <Track> relaxSoundProperty(){
		return relaxSound;
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
