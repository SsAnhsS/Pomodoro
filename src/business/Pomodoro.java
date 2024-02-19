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
	
	public static final int DEFAULT_FOCUS_TIME = 60; //25*60
	public static final int DEFAULT_RELAX_TIME = 30; //5*60
	public static final int DEFAULT_NUMBER_SESSION = 2;
	
	private MP3Player mp3Player;
	private SoundManager soundManager;
	
	private Thread countdownThread;
	
	private SimpleBooleanProperty isFocusTime;
	
	private SimpleIntegerProperty time;
	private SimpleIntegerProperty focusTime;
	private SimpleIntegerProperty relaxTime;
	private SimpleIntegerProperty numberSession;
	private SimpleIntegerProperty turn;
	private SimpleBooleanProperty isCounting;
	private SimpleStringProperty theme;
	
	private SimpleObjectProperty<Track> relaxSound;
	private SimpleObjectProperty<Track> notiSound;
	private SimpleObjectProperty<Playlist> bgSoundPlaylist;
	private SimpleBooleanProperty singlePlay;
	
	public Pomodoro() {
		soundManager = new SoundManager();
		
		isFocusTime = new SimpleBooleanProperty();
		
		time = new SimpleIntegerProperty();
		focusTime = new SimpleIntegerProperty();
		relaxTime = new SimpleIntegerProperty();
		numberSession = new SimpleIntegerProperty();
		turn = new SimpleIntegerProperty();
		isCounting = new SimpleBooleanProperty();
		theme = new SimpleStringProperty();
		
		isFocusTime.set(true);
		focusTime.set(DEFAULT_FOCUS_TIME);
		relaxTime.set(DEFAULT_RELAX_TIME);
		numberSession.set(DEFAULT_NUMBER_SESSION);
		turn.set(0);
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
		
	}
	
	public void setMP3Player(MP3Player mp3Player) {
		this.mp3Player = mp3Player;
		soundManager = mp3Player.getSoundManager();
	}
	
	/**
	 * Count Down Thread
	 */
	public class CountDownThread extends Thread{
		
		/**
		 * run-Methode
		 */
		public void run() {
			turn.set(0);
			int totalSessions = numberSession.getValue();
	        for (int i = 1; i <= totalSessions && isCounting.getValue(); i++) {
	            countdownPhase(true);
	            if (isCounting.getValue()) {
	                countdownPhase(false);
	                turn.set(i);
	            }
	        }
		}
		
		/**
		 * Countdown-Phase
		 * count down focus time when isFocusTime is true
		 * else count down relax time
		 * @param focus isFocusTime
		 */
		private void countdownPhase(boolean focus) {
	        isFocusTime.set(focus);
	        int countdownTime = isFocusTime.getValue() ? focusTimeProperty().getValue() : relaxTimeProperty().getValue();
	        mp3Player.select(notiSound.getValue());
	        playCountdownSound();
	        for (int i = countdownTime; i >= 0 && isCounting.getValue(); i--) {
				if(i == 0) {
					mp3Player.stop();
				}
	            time.set(i);
	            sleepInSeconds(1);
	        }

	    }
		
		/**
		 * Musik abspielt während Countdown-Funktion läuft 
		 */
		private void playCountdownSound() {
	        if (isFocusTime.getValue()) {
	        	mp3Player.stop();
	            playBackgroundMusic();
	            mp3Player.play();
	  
	        } else {
	        	mp3Player.stop();
	        	mp3Player.setRepeat(true);
	            mp3Player.select(relaxSound.getValue());
	            
	        }
	    }
		
		/**
		 * Thread schlafen in x seconds
		 * @param seconds
		 */
		private void sleepInSeconds(int seconds) {
	        try {
	            Thread.sleep(seconds * 1000);
	        } catch (InterruptedException e) {
	            interrupt();
	        }
	    }

	}

	/**
	 * Anfangen Count Down Thread
	 */
	public void play() {
		isCounting.set(true);
		countdownThread = new CountDownThread();
		countdownThread.start();
	}
	
	/**
	 * Stopen Couwn Down Thread
	 */
	public void pause() {
		isCounting.set(false);
		countdownThread.interrupt();
		mp3Player.stop();
		turn.set(0);
	}

	/**
	 * Abspielen Musik in der conzentratierte Zeit
	 */
	public void playBackgroundMusic() {
		mp3Player.currentPlaylistProperty().set(bgSoundPlaylist.getValue());
		
		if(singlePlay.getValue()) {
			mp3Player.stop();
			mp3Player.setAutoSkip(true);
			mp3Player.setRepeat(false);
			if (mp3Player.currentPlaylistProperty().getValue().getTotal() > 0) {
	            Track firstTrack = mp3Player.currentPlaylistProperty().getValue().getTrack(0);
	            if (firstTrack != null) {
	                mp3Player.currentTrackProperty().set(firstTrack);
	            }
	        }
		}
		else {
			mp3Player.stop();
			mp3Player.setAutoSkip(true);
			mp3Player.setRepeat(false);
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
	 * Getter und Setter Methode
	 */
	
	public SimpleBooleanProperty isFocusTimeProperty() {
		return isFocusTime;
	}
	
	public SimpleIntegerProperty timeProperty() {
		return time;
	}
	
	public SimpleIntegerProperty focusTimeProperty() {
		return focusTime;
	}
	
	public SimpleIntegerProperty relaxTimeProperty() {
		return relaxTime;
	}
	
	public SimpleIntegerProperty numberSessionProperty() {
		return numberSession;
	}
	
	public SimpleIntegerProperty turnProperty() {
		return turn;
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
