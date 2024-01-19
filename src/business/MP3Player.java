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
	
	private Thread coundTime; //-> count down time
	
	private SimpleIntegerProperty time;
//	private SimpleObjectProperty <Playlist> currentPlaylist;
//	private SimpleObjectProperty <Track> currentTrack;
//	private SimpleBooleanProperty isPlaying;
	
	public MP3Player() {
		minim = new SimpleMinim();
		
		time = new SimpleIntegerProperty();
		
//		currentTrack = new SimpleObjectProperty<Track>();
//		currentPlaylist = new SimpleObjectProperty<Playlist>();
//		isPlaying = new SimpleBooleanProperty();
//		isPlaying.set(false);
	}
	
	
	
	
	
	
}
