package presentation.setting;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SettingSoundView extends VBox{
	
	public final double DISTANCE = 10;
	
	public Button singlePlay;
	public Button multiPlay;
	
	public CheckBox bgSound_01;
	public CheckBox bgSound_02;
	public CheckBox bgSound_03;
	public CheckBox bgSound_04;
	public CheckBox bgSound_05;
	public CheckBox bgSound_06;
	
	public CheckBox pauseSound_01;
	public CheckBox pauseSound_02;
	public CheckBox pauseSound_03;
	
	public CheckBox notiSound_01;
	public CheckBox notiSound_02;
	public CheckBox notiSound_03;
	
	public Slider volumeSlider;
	public Text volumeValue; 
	
	public int defaultVolume = 100;
	
	public SettingSoundView() {
		
		HBox labelBox = new HBox();
		
		Label soundBoxName = new Label("Sound");
		labelBox.getChildren().addAll(soundBoxName);
		
		VBox bgSoundBox = new VBox();
		setBackgroundSoundBox(bgSoundBox);
		
		HBox pauseSoundBox = new HBox();
		setPauseSoundBox(pauseSoundBox);
		
		HBox notiSoundBox = new HBox();
		setNotificationSoundBox(notiSoundBox);
		
		HBox volumeBox = new HBox();
		setVolumeBox(volumeBox);
		
		this.getChildren().addAll(labelBox, bgSoundBox, pauseSoundBox, notiSoundBox, volumeBox);
		this.setSpacing(DISTANCE);
	}
	
	public void setBackgroundSoundBox(VBox box) {
		Label boxName = new Label("Background Sound");
		
		HBox chooseBox = new HBox();
		singlePlay = new Button("Single Play");
		multiPlay = new Button("Multi Play");
		chooseBox.getChildren().addAll(singlePlay, multiPlay);
		chooseBox.setSpacing(DISTANCE);
		
		HBox soundBox = new HBox();
		bgSound_01 = new CheckBox("bgSound_01");
		bgSound_02 = new CheckBox("bgSound_02");
		bgSound_03 = new CheckBox("bgSound_03");
		bgSound_04 = new CheckBox("bgSound_04");
		bgSound_05 = new CheckBox("bgSound_05");
		bgSound_06 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(bgSound_01, bgSound_02, bgSound_03, bgSound_04, bgSound_05, bgSound_06);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, chooseBox, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setPauseSoundBox(HBox box) {
		Label boxName = new Label("Pause Sound");
		
		HBox soundBox = new HBox();
		pauseSound_01 = new CheckBox("pauseSound_01");
		pauseSound_02 = new CheckBox("pauseSound_02");
		pauseSound_03 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(pauseSound_01, pauseSound_02, pauseSound_03);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setNotificationSoundBox(HBox box) {
		Label boxName = new Label("Nofitication Sound");
		
		HBox soundBox = new HBox();
		notiSound_01 = new CheckBox("notiSound_01");
		notiSound_02 = new CheckBox("notiSound_02");
		notiSound_03 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(notiSound_01, notiSound_02, notiSound_03);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setVolumeBox(HBox box) {
		Label volumeBox = new Label("Volume");
		Button volumeIcon = new Button();
		volumeIcon.setId("volume");
		volumeIcon.getStyleClass().add("volume-icon");
		volumeSlider = new Slider(0, 100, defaultVolume);
		volumeValue = new Text();
		volumeValue.setText(String.valueOf(defaultVolume));
		
		box.getChildren().addAll(volumeBox, volumeIcon, volumeSlider, volumeValue);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
}
