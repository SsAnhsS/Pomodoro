package presentation.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import mp3player.scene.layout.ImageViewPane;
import presentation.setting.ThemeName;

/**
 * Photo View Klasse
 */
public class PhotoView extends ImageViewPane{
	
	public String fileString;
	public ImageView imageView;
	
	public PhotoView(String fileString) {
		this.fileString = fileString;
		loadImage();
	}
	
	public PhotoView() {
		fileString = ThemeName.TOMATO.getFile();
		loadImage();
	}
	
	/**
	 * aktualisieren Image
	 */
	private void loadImage() {
		try {
			FileInputStream file = new FileInputStream(fileString);
			Image image = new Image(file);
			if(imageView == null) {
				imageView = new ImageView(image);
				this.setImageView(imageView);
			} else {
				imageView.setImage(image);
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * aktualisieren Image aus File-String
	 * @param newFileString
	 */
	public void updateImage(String newFileString) {
		if(imageView != null && imageView.getImage() != null) {
			imageView.getImage().cancel();
		}
		
		this.fileString = newFileString;
		loadImage();
	}
	
}
