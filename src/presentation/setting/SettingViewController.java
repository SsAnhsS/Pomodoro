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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Setting View Controller Klasse, um Setting View zu kontrollieren
 */
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
	
	public ComboBox <Integer> focusTime;
	public ComboBox <Integer> relaxTime;
	public ComboBox <Integer> numberSession;
	
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
		
//		updateCheckBoxSelection(bgs_01);
//		updateCheckBoxSelection(bgs_02);
//		updateCheckBoxSelection(bgs_03);
//		updateCheckBoxSelection(bgs_04);
//		updateCheckBoxSelection(bgs_05);
//		updateCheckBoxSelection(bgs_06);
//		updateCheckBoxSelection(bgs_07);
//		updateCheckBoxSelection(bgs_08);
//		updateCheckBoxSelection(bgs_09);
//		updateCheckBoxSelection(bgs_10);
		
		bgs_03.setSelected(true);
		bgs_06.setSelected(true);
		
		psGroup = settingView.psGroup;
		nsGroup = settingView.nsGroup;
		
		volumeSlider = settingView.volumeSlider;
		volumeValue = settingView.volumeValue;
		
		focusTime = settingView.focusTime;
		relaxTime = settingView.relaxTime;
		numberSession = settingView.numberSession;
		
		initialize();
	}
	
	/***
	 * Check Box Listener
	 * CheckBox ist ausgrgaehlt, entsprechende Sound in Playlist zu speichern
	 * Ansonsten, entsprechende Sound in Playlist zu entfernen
	 * @param checkBox
	 */
	private void addCheckBoxListener(CheckBox checkBox) {
		checkBox.selectedProperty().addListener((observable, oldValue, newValue) -> {
			String soundName = checkBox.getText();
			
			
			if(newValue) {
				Track newTrack = new Track(mp3Player.getSoundManager().getSoundFile(soundName));
				if(pomodoro.bgSoundPlaylistProperty().getValue() == null) {
					pomodoro.bgSoundPlaylistProperty().set(new Playlist("New Playlist"));
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
	
//	private void updateCheckBoxSelection(CheckBox checkBox) {
//		Playlist playlist = pomodoro.bgSoundPlaylistProperty().getValue();
//		String checkBoxText = checkBox.getText();
//		if (playlist != null && playlist.getTotal() > 0) {
//			for(Track aktTrack : playlist.getTracks()) {
//				String soundName = mp3Player.getSoundManager().getSoundName(aktTrack.getSoundFile());
//				if (soundName != null && soundName.equals(checkBoxText)) {
//		            checkBox.setSelected(true);
//		        } else {
//		            checkBox.setSelected(false);
//		        }
//			}
//		}
//		else {
//			checkBox.setSelected(false);
//		}
//	}
	
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
		
		/**
		 * Listener der Play Mode, neue Value fuer singlePlayProperty zu nehmen
		 * 
		 */
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
		
		/**
		 * Listener der Pause Sound ToggleGroup, neue Value fuer relaxSoundProperty zu nehmen
		 */
		psGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				RadioButton rb = (RadioButton) psGroup.getSelectedToggle();
				
				if(rb != null) {
					String selectedSound = rb.getText();
					mp3Player.select(selectedSound);
					pomodoro.relaxSoundProperty().set(mp3Player.currentTrackProperty().getValue());
				}
			}
			
		});
		
		/**
		 * Listener der Notification Sound ToggleGroup, neue Value fuer notiSoundProperty zu nehmen
		 */
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
		
		
		/**
		 * Listener der Theme ToggleGroup, um neue Theme zu veraendern
		 */
		themeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton)themeGroup.getSelectedToggle();
				
				if(rb != null) {
					String selectedTheme = rb.getText();
					setStartButtonStyle(selectedTheme);
					Platform.runLater(() -> pomodoro.setThemeProperty(selectedTheme));
					
				}
			}

		});
		
		/**
		 * Listener der volume von mp3Player
		 */
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>(){
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				mp3Player.volume(newValue.intValue());
				volumeValue.setText(newValue.intValue() + "");
			}
		});
		
		/**
		 * Listener der concentrationTime, um die conzentratierte Zeit zu aktualisieren
		 */
		focusTime.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				Platform.runLater(() -> pomodoro.focusTimeProperty().set(newValue * 60));
			}
			
		});
		
		/**
		 * Listener der pauseTime, um die pause Zeit zu aktualisieren
		 */
		relaxTime.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				
				Platform.runLater(() -> pomodoro.relaxTimeProperty().set(newValue * 60));
			}
			
		});
		
		/**
		 * Listener der Anzahl der Session, um die Anzahl der Session zu aktualisieren
		 */
		numberSession.valueProperty().addListener(new ChangeListener<Integer>() {

			@Override
			public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
				
				Platform.runLater(() -> pomodoro.numberSessionProperty().set(newValue));
			}
			
		});
		
		/**
		 * Action in Start Button
		 */
		startButton.setOnAction(event -> {
			app.switchView(ViewName.MAINVIEW);
			Platform.runLater(() ->{
				if(mp3Player.playingProperty().getValue()) {
					mp3Player.pause();
				}
				pomodoro.play();
			});
			
		});
	}
	
	/**
	 * Einstellung der neuen Theme in Start Button
	 * @param themeName
	 */
	public void setStartButtonStyle(String themeName) {
		startButton.getStyleClass().clear();
		startButton.getStyleClass().add("icon-button");
		switch(themeName) {
		case "Tomato":
			startButton.getStyleClass().add("theme-tomato");
			break;
		case "Apple":
			startButton.getStyleClass().add("theme-apple");
			break;
		case "Blueberry":
			startButton.getStyleClass().add("theme-blueberry");
			break;
		case "Carrot":
			startButton.getStyleClass().add("theme-carrot");
			break;
		case "Cherry":
			startButton.getStyleClass().add("theme-cherry");
			break;
		case "Lemon":
			startButton.getStyleClass().add("theme-lemon");
			break;
		case "Olive":
			startButton.getStyleClass().add("theme-olive");
			break;
		case "Orange":
			startButton.getStyleClass().add("theme-orange");
			break;
		}
	}
	
	public Pane getRoot() {
		return settingView;
	}
}
