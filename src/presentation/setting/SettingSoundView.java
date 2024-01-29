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
	
	public CheckBox bgs_01;
	public CheckBox bgs_02;
	public CheckBox bgs_03;
	public CheckBox bgs_04;
	public CheckBox bgs_05;
	public CheckBox bgs_06;
	
	public CheckBox ps_01;
	public CheckBox ps_02;
	public CheckBox ps_03;
	
	public CheckBox ns_01;
	public CheckBox ns_02;
	public CheckBox ns_03;
	
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
		bgs_01 = new CheckBox("bgs_01");
		bgs_02 = new CheckBox("bgs_02");
		bgs_03 = new CheckBox("bgs_03");
		bgs_04 = new CheckBox("bgs_04");
		bgs_05 = new CheckBox("bgs_05");
		bgs_06 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(bgs_01, bgs_02, bgs_03, bgs_04, bgs_05, bgs_06);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, chooseBox, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setPauseSoundBox(HBox box) {
		Label boxName = new Label("Pause Sound");
		
		HBox soundBox = new HBox();
		ps_01 = new CheckBox("ps_01");
		ps_02 = new CheckBox("ps_02");
		ps_03 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(ps_01, ps_02, ps_03);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setNotificationSoundBox(HBox box) {
		Label boxName = new Label("Nofitication Sound");
		
		HBox soundBox = new HBox();
		ns_01 = new CheckBox("ns_01");
		ns_02 = new CheckBox("ns_02");
		ns_03 = new CheckBox("No Sound");
		soundBox.getChildren().addAll(ns_01, ns_02, ns_03);
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
