package presentation.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mp3player.scene.layout.ImageViewPane;
import presentation.setting.ThemeName;

public class PhotoView extends ImageViewPane{
	
	public String fileString = ThemeName.TOMATO.getFile();
	public ImageView imageView;
	
	public PhotoView() {
		FileInputStream file = null;
		
		try {
			file = new FileInputStream(fileString);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Image image = new Image(file);
		imageView = new ImageView(image);
		this.setImageView(imageView);
	}
	
	public void setFile(String fileString) {
		this.fileString = fileString;
	}
	
	public void setNewPhoto(String fileString) {
		
	}
	
}
