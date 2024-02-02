package application;

import java.util.HashMap;

import business.MP3Player;
import business.Pomodoro;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import presentation.main.MainViewController;
import presentation.setting.SettingViewController;

/** Application Pomodoro
 * 
 * @author Thi Hai Anh Luong
 */
public class App extends Application{
	
	private Stage primaryStage;
	private HashMap<ViewName, Pane> views;
	
	private Pane mainView;
	private Pane settingView;
	
	MainViewController mainViewController;
	SettingViewController settingViewController;
	
	Pomodoro pomodoro;
	MP3Player mp3Player;
	
	/**
	 * Initialisierung des Programm
	 */
	public void init() {
		views = new HashMap<>();
		pomodoro = new Pomodoro();
		mp3Player = new MP3Player();
		pomodoro.setMP3Player(mp3Player);
		
		mainViewController = new MainViewController(this, pomodoro, mp3Player);
		mainView = mainViewController.getRoot();
		views.put(ViewName.MAINVIEW, mainView);
		
		settingViewController = new SettingViewController(this, pomodoro, mp3Player);
		settingView = settingViewController.getRoot();
		views.put(ViewName.SETTINGVIEW, settingView);
		
	}
	
	@Override
	public void start(Stage primaryStage){
		try {
			this.primaryStage = primaryStage;
			Pane root = new Pane();
			
			Scene scene = new Scene(root, 1280, 800);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			
			switchView(ViewName.MAINVIEW);
			
			primaryStage.setTitle("Pomodoro");
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}	
	}
	
	/**
	 * Scene auswählen
	 * @param ViewName
	 */
	public void switchView(ViewName name) {
		Scene currentScene = primaryStage.getScene();
		
		Pane nextView = views.get(name);
		
		if(nextView != null) {
			currentScene.setRoot(nextView);
		}
	}
	
	/**
	 * Stoppen des laufende Programm
	 */
	public void stop() {
		System.exit(0);
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
