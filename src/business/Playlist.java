package business;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Playlist {
	private String playlistName;
	private ArrayList<Track> trackList;
	
	private String file;
	
	public Playlist() {
		playlistName = "";
		trackList = new ArrayList<Track>();
	}
	
	public Playlist(String name, String file) {
		this.playlistName = name;
		this.file = file;
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
				trackList.add(newTrack);
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
		trackList.add(track);
	}
	
}
