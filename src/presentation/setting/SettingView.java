package presentation.setting;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import presentation.main.PhotoView;

/**
 * Setting View Klasse, um Setting View zu gestalten
 */
public class SettingView extends VBox{
	
	public final double DISTANCE = 10;

	public Insets sameInsets = new Insets(10, 50, 20, 50);
	
	public PhotoView smalIcon;
	public Button startButton;
	
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
	
	public ToggleGroup themeGroup;
	
	public Slider volumeSlider;
	public Text volumeValue;
	
	SettingSoundView settingSoundView;
	
	public ComboBox <Integer> concentrationTime;
	public ComboBox <Integer> pauseTime;
	public ComboBox <Integer> numberSession;
	
	public SettingView() {
		HBox labelBox = new HBox();
		Label name = new Label ("Setting View");
		name.getStyleClass().add("label-32");
		startButton = new Button();
		startButton.getStyleClass().add("icon-button");
		startButton.getStyleClass().add("theme-tomato");
		
		labelBox.getChildren().addAll(name, startButton);
		labelBox.setSpacing(DISTANCE*95);
		labelBox.setAlignment(Pos.CENTER_LEFT);
		
		settingSoundView = new SettingSoundView();
		setSoundBox();
		
		VBox themeSettingBox = new VBox();
		setThemeBox(themeSettingBox);
		
		VBox timeSettingBox = new VBox();
		setTimeBox(timeSettingBox);
		
		this.getChildren().addAll(labelBox, settingSoundView, themeSettingBox, timeSettingBox);
		this.setSpacing(DISTANCE);
		
		VBox.setMargin(labelBox, sameInsets);
		VBox.setMargin(settingSoundView, sameInsets);
		VBox.setMargin(themeSettingBox, sameInsets);
		VBox.setMargin(timeSettingBox, sameInsets);
	}
	
	public void setSoundBox() {
		playModeGroup = settingSoundView.playModeGroup;
		
		bgs_01 = settingSoundView.bgs_01;
		bgs_02 = settingSoundView.bgs_02;
		bgs_03 = settingSoundView.bgs_03;
		bgs_04 = settingSoundView.bgs_04;
		bgs_05 = settingSoundView.bgs_05;
		bgs_06 = settingSoundView.bgs_06;
		bgs_07 = settingSoundView.bgs_07;
		bgs_08 = settingSoundView.bgs_08;
		bgs_09 = settingSoundView.bgs_09;
		bgs_10 = settingSoundView.bgs_10;
		
		psGroup = settingSoundView.psGroup;
		nsGroup = settingSoundView.nsGroup;
		
		volumeSlider = settingSoundView.volumeSlider;
		volumeValue = settingSoundView.volumeValue;
		
	}
	
	public void setThemeBox(VBox box) {
		Label boxName = new Label("Theme");
		boxName.getStyleClass().add("label-28");
		
		themeGroup = new ToggleGroup();
		
		TilePane buttonPane = new TilePane();
		
		RadioButton r1 = new RadioButton("Tomato");
		RadioButton r2 = new RadioButton("Apple");
		RadioButton r3 = new RadioButton("Blueberry");
		RadioButton r4 = new RadioButton("Carrot");
		RadioButton r5 = new RadioButton("Cherry");
		RadioButton r6 = new RadioButton("Lemon");
		RadioButton r7 = new RadioButton("Olive");
		RadioButton r8 = new RadioButton("Orange");
		
		r1.setToggleGroup(themeGroup);
		r2.setToggleGroup(themeGroup);
		r3.setToggleGroup(themeGroup);
		r4.setToggleGroup(themeGroup);
		r5.setToggleGroup(themeGroup);
		r6.setToggleGroup(themeGroup);
		r7.setToggleGroup(themeGroup);
		r8.setToggleGroup(themeGroup);
		
		r1.setSelected(true);
		
		buttonPane.getChildren().addAll(r1, r2, r3, r4, r5, r6, r7, r8);
		
		box.getChildren().addAll(boxName, buttonPane);
		box.setSpacing(DISTANCE);
		
	}
	
	public void setTimeBox(VBox box) {
		Label boxName = new Label("Time");
		boxName.getStyleClass().add("label-28");
		
		HBox box_01 = new HBox();
		Label comboBox_01 = new Label("Concentration Time");
		comboBox_01.getStyleClass().add("font-size-16");
		concentrationTime = new ComboBox<Integer>();
		concentrationTime.getItems().addAll(25, 30, 35, 40, 45);
		concentrationTime.setValue(25);
		Text minuten_01 = new Text("Minuten");
		minuten_01.getStyleClass().add("text-16px");
		
		box_01.getChildren().addAll(comboBox_01, concentrationTime, minuten_01);
		box_01.setSpacing(DISTANCE);
		
		HBox box_02 = new HBox();
		Label comboBox_02 = new Label("Pause Time");
		comboBox_02.getStyleClass().add("font-size-16");
		pauseTime = new ComboBox<Integer>();
		pauseTime.getItems().addAll(5, 10, 15);
		pauseTime.setValue(5);
		Text minuten_02 = new Text("Minuten");
		minuten_02.getStyleClass().add("text-16px");
		
		box_02.getChildren().addAll(comboBox_02, pauseTime, minuten_02);
		box_02.setSpacing(DISTANCE);
		
		HBox box_03 = new HBox();
		Label comboBox_03 = new Label("Number of Session");
		comboBox_03.getStyleClass().add("font-size-16");
		numberSession = new ComboBox<Integer>();
		numberSession.getItems().addAll(1, 2, 3, 4);
		numberSession.setValue(2);
		Text set = new Text("Sets");
		set.getStyleClass().add("text-16px");
		
		box_03.getChildren().addAll(comboBox_03, numberSession, set);
		box_03.setSpacing(DISTANCE);
		
		box.getChildren().addAll(boxName, box_01, box_02, box_03);
		box.setSpacing(DISTANCE);
	}
}
