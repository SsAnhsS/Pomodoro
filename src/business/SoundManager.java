package business;

import java.util.HashMap;
import java.util.Map.Entry;

/**
 * Sound Manager Klasse
 */
public class SoundManager {
	
	private HashMap<String, String> soundMap;
	
	public SoundManager() {
		soundMap = new HashMap<>();
		saveSoundMap();
	}
	
	/**
	 * Speichern Sound in HashMap
	 */
	private void saveSoundMap() {
		//Background Sound
		soundMap.put("Jazz Piano", "src//data//sounds//BGS-a-jazz-piano.mp3");
		soundMap.put("Relax Piano", "src//data//sounds//BGS-relax-piano.mp3");
		soundMap.put("Last Piano", "src//data//sounds//BGS-the-last-piano.mp3");
		soundMap.put("Calm River", "src//data//sounds//BGS-calm-river.mp3");
		soundMap.put("Calm Waves", "src//data//sounds//BGS-calm-waves.mp3");
		soundMap.put("Soft Rain", "src//data//sounds//BGS-soft-rain.mp3");
		soundMap.put("Rain and Thunder", "src//data//sounds//BGS-rain-and-thunder.mp3");
		soundMap.put("Forest", "src//data//sounds//BGS-forest.mp3");
		soundMap.put("Wind", "src//data//sounds//BGS-wind.mp3");
		soundMap.put("Coffe Shop Chatter", "src//data//sounds//BGS-coffee-shop-chatter.mp3");
		
		//Pause Sound
		soundMap.put("Cat Purring", "src//data//sounds//PS-cat-purring.mp3");
		soundMap.put("Morning Funny Beat", "src//data//sounds//PS-morning-funny-beat.mp3");
		
		//Notification Sound
		soundMap.put("Bicycle Bell", "src//data//sounds//NS-bicycle-bell.mp3");
		soundMap.put("Old Church Bell", "src//data//sounds//NS-old-church-bell.mp3");
		soundMap.put("Cat Meowing", "src//data//sounds//NS-cat-meowing.mp3");
		soundMap.put("Correct", "src//data//sounds//NS-correct.mp3");
	}
	
	/**
	 * Nehmen Sound File aus Sound Name
	 * @param soundName
	 * @return
	 */
	public String getSoundFile(String soundName) {
		return soundMap.get(soundName);
	}
	
	/**
	 * Nehmen Sound Name aus Sound File
	 * @param soundFile
	 * @return
	 */
	public String getSoundName(String soundFile) {
		for(Entry <String, String> entry : soundMap.entrySet()) {
			if(entry.getValue().equals(soundFile)) {
				return entry.getKey();
			}
		}
		return null;
	}
}
