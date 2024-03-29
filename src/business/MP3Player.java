package business;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;

/**Mp3-Player Klasse
 * Funktionen von Mp3-Player einstellen
 */
public class MP3Player {
	
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	
	private SoundManager soundManager;
	
	private int indexTrack = 0;
	
	private Thread playingMusic;
	
	private long position = 0;
	
	private boolean isRepeated;
	private boolean autoSkip;
	
	private SimpleObjectProperty <Playlist> currentPlaylist;
	private SimpleObjectProperty <Track> currentTrack;
	private SimpleBooleanProperty isPlaying;
	
	/**
	 * Erstellen einen neuen MP3Player
	 */
	public MP3Player() {
		minim = new SimpleMinim();
		currentTrack = new SimpleObjectProperty<Track>();
		currentPlaylist = new SimpleObjectProperty<Playlist>();
		isPlaying = new SimpleBooleanProperty();
		isPlaying.set(false);
		soundManager = new SoundManager();
		isRepeated = false;
		autoSkip = false;
	}
	
	/**
	 * Thread, um Music abzuspielen
	 */
	public class PlayThread extends Thread{
		public void run() {
			audioPlayer.play((int) position);
			
			if(!isPlaying.getValue()) {
				return;
			}
			
			if(isRepeated) {
				repeat();
			}
			
			if(autoSkip) {
				skip();
			}
			
		}
	}
	
	/**
	 * Abspielen
	 */
	public void play() {
		isPlaying.set(true);
		if(currentTrack.getValue() != null) {
			audioPlayer = minim.loadMP3File(currentTrack.getValue().getSoundFile());
		}
		playingMusic = new PlayThread();
		playingMusic.start();
	}
	
	/**
	 * Abspielen mit abgegebener File-String
	 * @param fileName File-String
	 */
	public void play(String fileName) {
		if(isPlaying.getValue()) {
			pause();
		}
		
		indexTrack = currentPlaylist.getValue().getIndex(fileName);
		audioPlayer = minim.loadMP3File(fileName);
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
		this.position = 0;
		play();
	}
	
	/**
	 * Abspielen des gewählten Track
	 * @param track
	 */
	public void select(Track track) {
		if(isPlaying.getValue()) {
			pause();
		}
		currentTrack.set(track);
		this.position = 0;
		audioPlayer = minim.loadMP3File(track.getSoundFile());
		play();
	}
	
	/**
	 * Pause PlayingThread, um die Music zu pausieren
	 */
	public void pause() {
		isPlaying.set(false);
		
		playingMusic.interrupt();
		audioPlayer.pause();
		
		this.position = audioPlayer.position();
	}
	
	public void stop() {
		playingMusic.interrupt();
		audioPlayer.pause();
		isPlaying.set(false);
		this.position = 0;
		isRepeated = false;
		autoSkip = false;
	}
	/**
	 * Abspielen die nächste Sound oder Song
	 */
	public void skip() {
		
		if(isPlaying.getValue()) {
			pause();
		}
		if(indexTrack + 1 < currentPlaylist.getValue().getTotal()) {
			indexTrack ++;
		}
		else {
			indexTrack = 0;
		}
		this.position = 0;
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
		play();

	}
	
	/**
	 * Automatisch abspielen
	 */
	public void repeat() {
		if(isPlaying.getValue()) {
			pause();
		}
		this.position = 0;
		play();
		return;
	}
	
	/**
	 * Abspielen der ausgewaehlte Sound oder Song
	 * @param soundName
	 */
	public void select(String soundName) {
		if(isPlaying.getValue()) {
			pause();
		}
		this.position = 0;
		currentTrack.set(new Track(soundManager.getSoundFile(soundName)));
		play();
	}
	
	/**
	 * Set Volume fuer MP3Player
	 * @param value Volumevalue
	 */
	public void volume(int value) {
		//Weil Gain Value hat Range [-50;0]
		float gainValue = (float) (value-100)/2;
		if(gainValue == -50) {
			audioPlayer.mute();
		} else {
			if(audioPlayer.isMuted()) {
				audioPlayer.unmute();
			}
			audioPlayer.setGain(gainValue);
		}
	}
	
	/**
	 * Getter und Setter Methode
	 */
	
	public SimpleObjectProperty <Track> currentTrackProperty(){
		return currentTrack;
	}
	
	public SimpleObjectProperty <Playlist> currentPlaylistProperty(){
		return currentPlaylist;
	}
	
	public SimpleBooleanProperty playingProperty() {
		return isPlaying;
	}
	
	public SoundManager getSoundManager() {
		return soundManager;
	}
	
	public void setRepeat(boolean value) {
		isRepeated = value;
	}
	
	public void setAutoSkip(boolean value) {
		autoSkip = value;
	}
}
