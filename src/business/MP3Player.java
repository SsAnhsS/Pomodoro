package business;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MP3Player {
	
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	
	private PlaylistManager playlistManager;
	private SoundManager soundManager;
	
	//quan ly playlist, tao ra mot playlist moi, xoa playlist cu
	
	//private int indexTrack = 0; //-> co can khong khi chay nhieu track cung 1 luc -> mix background music
	
	private int indexTrack = 0;
	
	private Thread playingMusic;
	
	private long position = 0;
	
	private boolean repeat = true;
	
	private SimpleObjectProperty <Playlist> currentPlaylist;
	private SimpleObjectProperty <Track> currentTrack;
	private SimpleBooleanProperty isPlaying;
	
	public MP3Player() {
		minim = new SimpleMinim();
		
		currentTrack = new SimpleObjectProperty<Track>();
		currentPlaylist = new SimpleObjectProperty<Playlist>();
		isPlaying = new SimpleBooleanProperty();
		
		playlistManager = new PlaylistManager();
		soundManager = new SoundManager();
		
		currentPlaylist.set(playlistManager.getPlaylist());
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
	}
	
	public class PlayThread extends Thread{
		public void run() {
			audioPlayer.play((int) position);
		
//			skip();
		}
	}
	
	public void play() {
		isPlaying.set(true);
		audioPlayer = minim.loadMP3File(currentTrack.getValue().getSoundFile());
		
//		if(currentPlaylist.getValue() != null) {
//			
//			isPlaying.set(true);
////			if(singlePlay.getValue())
//			
//			
//			
////			this.position = 0;
//			
//		}
		
//		audioPlayer = minim.loadMP3File(track.getSoundFile());
		playingMusic = new PlayThread();
		playingMusic.start();
	}
	
	public void pause() {
		audioPlayer.pause();
		playingMusic.interrupt();
		isPlaying.set(false);
		this.position = audioPlayer.position();
		
//		if(singlePlay.getValue()){
//			audioPlayer.pause();
//		}
//		else {
//			for(int i = 0; i < currentPlaylist.getValue().getTotal(); i++) {
//				audioPlayer.pause();
//			}
//		}
	}
	
	public void skip() {
		
		if(repeat) {
			if(isPlaying.getValue()) {
				pause();
			}
			this.position = 0;
			play();
			return;
		}
		else {
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
		
	}
	
	public void select(Track track) {
		if(isPlaying.getValue()) {
			pause();
		}
		currentTrack.set(track);
		this.position = 0;
		play();
	}
	
	public void select(String soundName) {
		if(isPlaying.getValue()) {
			pause();
		}
		this.position = 0;
		currentTrack.set(new Track(soundManager.getSoundFile(soundName)));
		play();
		
	}
	
	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
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
	
}
