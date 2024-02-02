package business;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Playlist Klasse
 */
public class Playlist {
	private String playlistName;
	private ArrayList<Track> tracks;
	
	private String file;
	
	public Playlist(String playlistName) {
		this.playlistName = playlistName;
		tracks = new ArrayList<Track>();
	}
	
	public Playlist(String name, String file) {
		this.playlistName = name;
		this.file = file;
		tracks = new ArrayList<Track>();
		getPlaylistFromFile();
	}
	
	/**
	 * Playlist speichern
	 */
	public void getPlaylistFromFile() {
		BufferedReader reader;

		try {
			
			reader = new BufferedReader(new FileReader(file));
			String line = reader.readLine();
			
			while(line != null) {
				String trackFile = line;
				Track newTrack = new Track(trackFile);
				tracks.add(newTrack);
				line = reader.readLine();
			}
			
			reader.close();
		} catch (FileNotFoundException e) {
			System.err.println("File not found: " + file);
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Addieren neue Track in Playlist
	 * @param track
	 */
	public void addTrack(Track track) {
		tracks.add(track);
	}
	
	/**
	 * Entfernen Track mit seiner SoundFile
	 * @param soundFile
	 */
	public void removeTrackWithSoundFile(String soundFile) {
		Iterator <Track> iterator = tracks.iterator();
		while(iterator.hasNext()) {
			Track aktTrack = iterator.next();
			if(aktTrack.getSoundFile().equals(soundFile)) {
				iterator.remove();
			}
		}
	}
	
	/**
	 * Speichern alle Tracks in Playlist in einem File
	 * @param fileName
	 */
	public void saveTrackInFile(String fileName) {
		File file = new File("src/data/playlist/" + fileName);
		try {
			if(!file.exists()) {
				file.createNewFile();
			}
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			Iterator <Track> iterator = tracks.iterator();
			while(iterator.hasNext()){
				Track aktTrack = iterator.next();
				writer.write(aktTrack.getSoundFile());
				writer.newLine();
			}
			
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Nehme der Anzahl des Tracks in Playlist
	 * @return
	 */
	public int getTotal() {
		return tracks.size();
	}
	
	/**
	 * Nehmen Index von Track in ArrayList tracks
	 * @param track
	 * @return index
	 */
	public int getIndex(Track currentTrack) {
		int index = 0;
		for(Track aktTrack : tracks) {
			if(aktTrack.equals(currentTrack)) {
				index = tracks.indexOf(aktTrack);
				break;
			}
		}
		return index;
	}
	
	/**
	 * Nehmen Index von Track-Name in ArrayList tracks
	 * @param trackName
	 * @return
	 */
	public int getIndex(String trackName) {
		int index = 0;
		for(Track aktTrack : tracks) {
			if(aktTrack.getSoundFile().equals(trackName)) {
				index = tracks.indexOf(aktTrack);
				break;
			}
		}
		return index;
	}
	
	/**
	 * Nehmen Track bei Index in ArrayList tracks
	 * @param index
	 * @return Track
	 */
	public Track getTrack(int n) {
		for(Track aktTrack : tracks) {
			if(n == tracks.indexOf(aktTrack)) {
				return aktTrack;
			}
		}
		return null;
	}
	
	/**
	 * Getter und Setter Methode
	 */
	public ArrayList<Track> getTracks(){
		return tracks;
	}
	
	public void setTracks(ArrayList <Track> tracks) {
		this.tracks = tracks;
	}
	
	public void setPlaylistName(String name) {
		playlistName = name;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
}
