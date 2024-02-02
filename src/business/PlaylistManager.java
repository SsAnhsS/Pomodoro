package business;

/**
 * Playlist Manager Klasse
 */
public class PlaylistManager {
	
	private String playlistName;
	private String playlistFile;
	private Playlist playlist;
	
	public PlaylistManager() {
		Playlist defaultPlaylist = new Playlist("Background Sound", "src\\data\\playlists\\default-background-sound.m3u");
		Playlist testPlaylist = new Playlist("Test", "src\\data\\playlists\\test.m3u");
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
