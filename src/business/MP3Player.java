package business;

import de.hsrm.mi.eibo.simpleplayer.SimpleAudioPlayer;
import de.hsrm.mi.eibo.simpleplayer.SimpleMinim;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;

public class MP3Player {
	
	private SimpleMinim minim;
	private SimpleAudioPlayer audioPlayer;
	
	//private PlaylistMangeger playlistManager;
	//quan ly playlist, tao ra mot playlist moi, xoa playlist cu
	
	//private int indexTrack = 0; //-> co can khong khi chay nhieu track cung 1 luc -> mix background music
	
	private Thread playingMusic;
	
	private Thread countTime; //-> count down time
	
	private SimpleIntegerProperty time;
	private SimpleObjectProperty <Playlist> currentPlaylist;
	private SimpleObjectProperty <Track> currentTrack;
	private SimpleBooleanProperty singlePlay;
	
	public MP3Player() {
		minim = new SimpleMinim();
		
		time = new SimpleIntegerProperty();
		
		currentTrack = new SimpleObjectProperty<Track>();
		currentPlaylist = new SimpleObjectProperty<Playlist>();
		singlePlay = new SimpleBooleanProperty();
		singlePlay.set(true);
	}
	
	public class PlayThread extends Thread{
		public void run() {
			audioPlayer.play();
		}
	}
	
	public void play() {
		if(currentPlaylist.getValue() != null) {
			
			playingMusic = new PlayThread();
			playingMusic.start();
		}
	}
	
	public void pause() {
		audioPlayer.pause();
		
//		if(singlePlay.getValue()){
//			audioPlayer.pause();
//		}
//		else {
//			for(int i = 0; i < currentPlaylist.getValue().getTotal(); i++) {
//				audioPlayer.pause();
//			}
//		}
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
