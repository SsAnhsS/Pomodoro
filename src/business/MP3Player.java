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
	//quan ly playlist, tao ra mot playlist moi, xoa playlist cu
	
	//private int indexTrack = 0; //-> co can khong khi chay nhieu track cung 1 luc -> mix background music
	
	private int indexTrack = 0;
	
	private Thread playingMusic;
	
	private long position = 0;
	
	private SimpleObjectProperty <Playlist> currentPlaylist;
	private SimpleObjectProperty <Track> currentTrack;
	private SimpleBooleanProperty isPlaying;
	private SimpleBooleanProperty singlePlay;
	
	public MP3Player() {
		minim = new SimpleMinim();
		
		currentTrack = new SimpleObjectProperty<Track>();
		currentPlaylist = new SimpleObjectProperty<Playlist>();
		isPlaying = new SimpleBooleanProperty();
		singlePlay = new SimpleBooleanProperty();
		singlePlay.set(true);
		
		playlistManager = new PlaylistManager();
		
		currentPlaylist.set(playlistManager.getPlaylist());
		currentTrack.set(currentPlaylist.getValue().getTrack(indexTrack));
	}
	
	public class PlayThread extends Thread{
		public void run() {
			audioPlayer.play((int) position);
		
			skip();
		}
	}
	
	public void play() {
		if(currentPlaylist.getValue() != null) {
			
//			if(singlePlay.getValue())
			
			audioPlayer = minim.loadMP3File(currentTrack.getValue().getSoundFile());
			this.position = 0;
			
			playingMusic = new PlayThread();
			playingMusic.start();
			
		}
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
		
		if(singlePlay.getValue()) {
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
	
	public SimpleObjectProperty <Track> trackProperty(){
		return currentTrack;
	}
	
	public SimpleObjectProperty <Playlist> playlistProperty(){
		return currentPlaylist;
	}
	
	public SimpleBooleanProperty singlePlayProperty() {
		return singlePlay;
	}
	
	
	
	
}
