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
	
	public void addTrack(Track track) {
		tracks.add(track);
	}
	
	public void removeTrackWithSoundFile(String soundFile) {
		Iterator <Track> iterator = tracks.iterator();
		while(iterator.hasNext()) {
			Track aktTrack = iterator.next();
			if(aktTrack.getSoundFile().equals(soundFile)) {
				iterator.remove();
			}
		}
	}
	
	public void saveTrackInFile(String fileName) {
		File file = new File("src/data/default setting/" + fileName);
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
	
	public void setPlaylistName(String name) {
		playlistName = name;
	}
	
	public ArrayList<Track> getTracks(){
		return tracks;
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
	
	public String getPlaylistName() {
		return playlistName;
	}
}
