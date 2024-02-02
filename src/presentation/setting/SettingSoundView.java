package presentation.setting;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class SettingSoundView extends VBox{
	
	public final double DISTANCE = 10;
	
	public ToggleGroup playModeGroup;

	public CheckBox bgs_01;
	public CheckBox bgs_02;
	public CheckBox bgs_03;
	public CheckBox bgs_04;
	public CheckBox bgs_05;
	public CheckBox bgs_06;
	public CheckBox bgs_07;
	public CheckBox bgs_08;
	public CheckBox bgs_09;
	public CheckBox bgs_10;
	
	public ToggleGroup psGroup;
	public ToggleGroup nsGroup;
	
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
		
		playModeGroup = new ToggleGroup();
		
		TilePane choosePane = new TilePane();
		RadioButton r1 = new RadioButton("Single Play");
		RadioButton r2 = new RadioButton("Multi Play");
		r1.setToggleGroup(playModeGroup);
		r2.setToggleGroup(playModeGroup);
		
		r1.setSelected(true);
		
		choosePane.getChildren().addAll(r1, r2);
		
		HBox soundBox = new HBox();
		bgs_01 = new CheckBox("Jazz Piano");
		bgs_02 = new CheckBox("Relax Piano");
		bgs_03 = new CheckBox("Last Piano");
		bgs_04 = new CheckBox("Calm River");
		bgs_05 = new CheckBox("Calm Waves");
		bgs_06 = new CheckBox("Soft Rain");
		bgs_07 = new CheckBox("Rain and Thunder");
		bgs_08 = new CheckBox("Forest");
		bgs_09 = new CheckBox("Wind");
		bgs_10 = new CheckBox("Coffe Shop Chatter");
		
		soundBox.getChildren().addAll(bgs_01, bgs_02, bgs_03, bgs_04, bgs_05, bgs_06, bgs_07, bgs_08, bgs_09, bgs_10);
		soundBox.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, choosePane, soundBox);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setPauseSoundBox(HBox box) {
		Label boxName = new Label("Pause Sound");
		
		psGroup = new ToggleGroup();
		
		TilePane soundPane = new TilePane();
		
		RadioButton r1 = new RadioButton("Morning Funny Beat");
		RadioButton r2 = new RadioButton("Cat Purring");
		
		r1.setToggleGroup(psGroup);
		r2.setToggleGroup(psGroup);
		
		r1.setSelected(true);
		
		soundPane.getChildren().addAll(r1, r2);
		
		box.getChildren().addAll(boxName, soundPane);
		box.setSpacing(DISTANCE);
		box.setAlignment(Pos.CENTER_LEFT);
	}
	
	public void setNotificationSoundBox(HBox box) {
		Label boxName = new Label("Nofitication Sound");
		
		nsGroup = new ToggleGroup();
		
		TilePane soundPane = new TilePane();
		
		RadioButton r1 = new RadioButton("Bicycle Bell");
		RadioButton r2 = new RadioButton("Old Church Bell");
		RadioButton r3 = new RadioButton("Cat Meowing");
		RadioButton r4 = new RadioButton("Correct");
		
		r1.setToggleGroup(nsGroup);
		r2.setToggleGroup(nsGroup);
		r3.setToggleGroup(nsGroup);
		r4.setToggleGroup(nsGroup);
		
		r1.setSelected(true);
		
		soundPane.getChildren().addAll(r1, r2, r3, r4);
		
		box.getChildren().addAll(boxName, soundPane);
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
