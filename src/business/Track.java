package business;

import java.io.IOException;

import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * Track Klasse
 */
public class Track {
	
	private Mp3File mp3File;
	private String title;
	private int length;
	private String artist;
	private String albumTitle;
	private String soundFile;
	
	public Track(String soundFile) {
		this.soundFile = soundFile;
		
		try {
			mp3File = new Mp3File(soundFile);
			length = (int) mp3File.getLengthInSeconds();
			if(mp3File.hasId3v2Tag()) {
				ID3v2 id3v2Tag = mp3File.getId3v2Tag();
				title = id3v2Tag.getTitle();
				artist = id3v2Tag.getArtist();
				albumTitle = id3v2Tag.getAlbum();
			}
			
		} catch (UnsupportedTagException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidDataException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
//	
//	public static Track getTrackFromFile(String file) {
//		try {
//			
//			BufferedReader reader = new BufferedReader(new FileReader(file));
//			String line = reader.readLine();
//			Track newTrack = null;
//			while(line != null) {
//				String trackFile = line;
//				newTrack = new Track(trackFile);
//				line = reader.readLine();
//			}
//			reader.close();
//			return newTrack;
//		} catch (FileNotFoundException e) {
//			System.err.println("File not found: " + file);
//			e.printStackTrace();
//		}catch (IOException e) {
//			e.printStackTrace();
//		}
//		return null;
//	}
//	
//	public void saveTrackInFile(String fileName) {
//		File file = new File("src/data/default setting/" + fileName);
//		try {
//			if(!file.exists()) {
//				file.createNewFile();
//			}
//			
//			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
//			writer.write(getSoundFile());
//			writer.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
	
	
	/**
	 * Getter und Setter Methode
	 */
	
	public String getSoundFile() {
		return soundFile;
	}
	
	public String getTitle() {
		return title;
	}
	
	public int getLength() {
		return length;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public String getAlbumTitle() {
		return albumTitle;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
}
