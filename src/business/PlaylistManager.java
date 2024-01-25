package business;

import java.util.HashMap;

public class PlaylistManager {
	
	private String playlistName;
	private String playlistFile;
	private Playlist playlist;
//	private HashMap <String, String> defaultSounds;
	
	/*
	 * Tao playlist trong, chon track luu vao tracks 
	 */
	public PlaylistManager() {
//		playlistName = "Default Playlist";
//		playlistFile = "";
		playlist = new Playlist(); //playlist has no name
	}
	
	public void setNewPlaylist() {
		
	}
	
	public Playlist getPlaylist() {
		return playlist;
	}
	
	public String getPlaylistName() {
		return playlistName;
	}
	
}
