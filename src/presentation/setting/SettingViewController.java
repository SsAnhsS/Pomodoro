package presentation.setting;

import application.App;
import application.ViewName;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class SettingViewController {
	
	private App app;
	
	public Button startButton;
	public Slider volumeSlider;
	public Text volumeValue;
	
	SettingView settingView;
	
	public SettingViewController(App app) {
		this.app = app;
		
		settingView = new SettingView();
		
		startButton =  settingView.startButton;
		volumeSlider = settingView.volumeSlider;
		volumeValue = settingView.volumeValue;
		
		initialize();
	}
	
	public void initialize() {
		
		volumeSlider.valueProperty().addListener(new ChangeListener<Number>() {
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
