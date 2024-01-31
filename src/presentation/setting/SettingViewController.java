package presentation.setting;

import application.App;
import application.ViewName;
import business.Pomodoro;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Toggle;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SettingViewController {
	
	private App app;
	private Pomodoro pomodoro;
	
	public Button startButton;
	
	public ToggleGroup themeGroup;
	
	public Slider volumeSlider;
	public Text volumeValue;
	
	SettingView settingView;
	
	public SettingViewController(App app, Pomodoro pomodoro) {
		this.app = app;
		this.pomodoro = pomodoro;
		
		settingView = new SettingView();
		
		startButton =  settingView.startButton;
		
		themeGroup = settingView.themeGroup;
		
		volumeSlider = settingView.volumeSlider;
		volumeValue = settingView.volumeValue;
		
		initialize();
	}
	
	public void initialize() {
		
		themeGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> object, Toggle oldO, Toggle newO) {
				// TODO Auto-generated method stub
				RadioButton rb = (RadioButton)themeGroup.getSelectedToggle();
				
				if(rb != null) {
					String s = rb.getText();
					
					switch(s) {
					case "Tomato":
					case "Apple":
					case "Blueberry":
					case "Carrot":
					case "Cherry":
					case "Lemon":
					case "Olive":
					case "Orange":
					
					}
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
		});
		
	}
	
	public Pane getRoot() {
		return settingView;
	}
}
