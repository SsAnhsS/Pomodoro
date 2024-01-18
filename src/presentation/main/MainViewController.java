package presentation.main;

import application.App;
import javafx.scene.layout.Pane;

public class MainViewController {
	
	private App app;
	
	MainView mainView;
	
	public MainViewController(App app) {
		this.app = app;
		
		mainView = new MainView();
	}
	
	public void initialize() {
		
	}
	
	public Pane getRoot() {
		return mainView;
	}
}
