package presentation.setting;

import application.App;
import application.ViewName;
import business.MP3Player;
import business.Playlist;
import business.Pomodoro;
import business.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import presentation.main.MainViewController;

public class SettingViewController {
	
	private App app;
	private Pomodoro pomodoro;
	private MP3Player mp3Player;
	
	public Button startButton;
	
	public ToggleGroup themeGroup;
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
	
	public Slider volumeSlider;
	public Text volumeValue;
	
	private SettingView settingView;
	
	public SettingViewController(App app, Pomodoro pomodoro, MP3Player mp3Player) {
		this.app = app;
		this.pomodoro = pomodoro;
		this.mp3Player = mp3Player;
		
		settingView = new SettingView();
		
		startButton =  settingView.startButton;
		
		themeGroup = settingView.themeGroup;
		
		playModeGroup = settingView.playModeGroup;
		
		bgs_01 = settingView.bgs_01;
		bgs_02 = settingView.bgs_02;
		bgs_03 = settingView.bgs_03;
		bgs_04 = settingView.bgs_04;
		bgs_05 = settingView.bgs_05;
		bgs_06 = settingView.bgs_06;
		bgs_07 = settingView.bgs_07;
		bgs_08 = settingView.bgs_08;
		bgs_09 = settingView.bgs_09;
		bgs_10 = settingView.bgs_10;
		
		psGroup = settingView.psGroup;
		nsGroup = settingView.nsGroup;
		
		volumeSlider = settingView.volumeSlider;
		volumeValue = settingView.volumeValue;
		
		initialize();
	}
	
	private void addCheckBoxListener(CheckBox checkBox) {
		checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			String soundName = checkBox.getText();
			
			
			if(newValue) {
				Track newTrack = new Track(mp3Player.getSoundManager().getSoundFile(soundName));
				if(pomodoro.bgSoundPlaylistProperty().getValue() == null) {
					pomodoro.bgSoundPlaylistProperty().set(new Playlist());
				}
				pomodoro.bgSoundPlaylistProperty().getValue().addTrack(newTrack);
				mp3Player.select(soundName);
			}
			else {
				pomodoro.bgSoundPlaylistProperty().getValue().removeTrackWithSoundFile(mp3Player.getSoundManager().getSoundFile(soundName));
				mp3Player.pause();
			}
			
			if(pomodoro.bgSoundPlaylistProperty().getValue() != null) {
				System.out.println(pomodoro.bgSoundPlaylistProperty().getValue().getPlaylistName());
				for(Track aktTrack : pomodoro.bgSoundPlaylistProperty().getValue().getTracks()) {
					System.out.println(mp3Player.getSoundManager().getSoundName(aktTrack.getSoundFile()));
				}
				System.out.println();
			}
		});
		
	}
	
	public void initialize() {
		
		addCheckBoxListener(bgs_01);
		addCheckBoxListener(bgs_02);
		addCheckBoxListener(bgs_03);
		addCheckBoxListener(bgs_04);
		addCheckBoxListener(bgs_05);
		addCheckBoxListener(bgs_06);
		addCheckBoxListener(bgs_07);
		addCheckBoxListener(bgs_08);
		addCheckBoxListener(bgs_09);
		addCheckBoxListener(bgs_10);
		
		playModeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				RadioButton rb = (RadioButton) playModeGroup.getSelectedToggle();
				
				if(rb != null) {
					if(rb.getText().equals("Single Play")) {
						pomodoro.singlePlayProperty().set(true);
					}
					else {
						pomodoro.singlePlayProperty().set(false);
					}
				}
			}
			
		});
		
		psGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				RadioButton rb = (RadioButton) psGroup.getSelectedToggle();
				
				if(rb != null) {
					String selectedSound = rb.getText();
					mp3Player.select(selectedSound);
					pomodoro.pauseSoundProperty().set(mp3Player.currentTrackProperty().getValue());
				}
			}
			
		});
		
		nsGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				RadioButton rb = (RadioButton) nsGroup.getSelectedToggle();
				
				if(rb != null) {
					String selectedSound = rb.getText();
					mp3Player.select(selectedSound);
					pomodoro.notiSoundProperty().set(mp3Player.currentTrackProperty().getValue());
				}
			}
			
		});
		
		themeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton)themeGroup.getSelectedToggle();
				
				if(rb != null) {
					String selectedTheme = rb.getText();
					pomodoro.setThemeProperty(selectedTheme);
				}
			}

		});
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
//				player.volume(newValue.intValue());
				volumeValue.setText(newValue.intValue() + "");
			}
		});
		
		startButton.setOnAction(event -> {
			app.switchView(ViewName.MAINVIEW);
			Platform.runLater(() -> pomodoro.play());
		});
	}
	
	public Pane getRoot() {
		return settingView;
	}
}
