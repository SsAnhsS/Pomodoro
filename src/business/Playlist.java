package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
	private String playlistName;
	private ArrayList<Track> tracks;
	
	private String file;
	
	public Playlist() {
		playlistName = "New Playlist";
		tracks = new ArrayList<Track>();
	}
	
	public Playlist(String name, String file) {
		this.playlistName = name;
		this.file = file;
		tracks = new ArrayList<Track>();
		savePlaylist();
	}
	
	/**
	 * Playlist speichern
	 */
	public void savePlaylist() {
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
	
	public void addTrack(Track track) {
		tracks.add(track);
	}
	
	public void setPlaylistName(String name) {
		playlistName = name;
	}
	
	public void setTracks(ArrayList <Track> tracks) {
		this.tracks = tracks;
	}
	
	public int getTotal() {
		return tracks.size();
	}
	
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
	
	public Track getTrack(int n) {
		for(Track aktTrack : tracks) {
			if(n == tracks.indexOf(aktTrack)) {
				return aktTrack;
			}
		}
		return null;
	}
}
