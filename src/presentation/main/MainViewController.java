package presentation.main;

import java.util.ArrayList;

import application.App;
import application.ViewName;
import business.MP3Player;
import business.Pomodoro;
import business.Todo;
import business.TodoList;
import business.Track;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Main View Controller, um Main View zu kontrollieren
 */
public class MainViewController {
	
	private App app;
	private Pomodoro pomodoro;
	private MP3Player mp3Player;
	
	private ArrayList <Todo> todos;
	private TodoList todoList = new TodoList();
	
	public Button addNew;
	public TextField textField;
	public ListView <Todo> todoListView;
	
	
	public Label focusTime;
	public Label pauseTime;
	public HBox sessionBox;
	
	public PhotoView photoView;
	public Button countdownButton;
	public Label musicLabel;
	
	public Button settingButton;
	
	private MainView mainView;
	
	public MainViewController(App app, Pomodoro pomodoro, MP3Player mp3Player) {
		this.app = app;
		this.pomodoro = pomodoro;
		this.mp3Player = mp3Player;
		
		mainView = new MainView();
		
		addNew = mainView.addNew;
		textField = mainView.textField;
		todoListView = mainView.todoListView;
		
		focusTime = mainView.focusTime;
		pauseTime = mainView.pauseTime;
		sessionBox = mainView.sessionBox;
		
		photoView = mainView.photoView;
		countdownButton = mainView.countdownButton;
		musicLabel = mainView.musicLabel;
		
		settingButton = mainView.settingButton;
		
		countdownButton.setText(getTimeForm(pomodoro.concentrationTimeProperty().getValue()));
		
		initialize();
	}
	
	public void initialize() {
		
		/**
		 * Listener der concentrationTimeProperty
		 */
		pomodoro.concentrationTimeProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						String newTime = String.valueOf(newValue.intValue() / 60);
						focusTime.setText("Concentration Time: " + newTime + " minuten");
					}
				});
				
			}

		});
		
		/**
		 * Listener der pauseTimeProperty
		 */
		pomodoro.pauseTimeProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						String newTime = String.valueOf(newValue.intValue() / 60);
						pauseTime.setText("Pause Time: " + newTime + " minuten");
					}
				});
				
			}

		});
		
		/**
		 * Listener der numberSessionPropery
		 */
		pomodoro.numberSessionProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						sessionBox.getChildren().clear();
						for(int i = 0; i < newValue.intValue(); i++) {
							PhotoView newPhotoView = new PhotoView();
							newPhotoView.setMaxSize(20, 20);
							sessionBox.getChildren().add(newPhotoView);
						}
					}
				});
			}

		});
		
		/**
		 * ActionEvent fuer count down button
		 */
		countdownButton.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				
				if(pomodoro.countingProperty().getValue()) {
					pomodoro.pause();
				} else {

					pomodoro.play();
				}
			}
			
		});
		
		/**
		 * Listener der currentTrack, um Musikname in der Main View zu zeigen
		 */
		mp3Player.currentTrackProperty().addListener(new ChangeListener<Track>() {

			@Override
			public void changed(ObservableValue<? extends Track> observable, Track oldValue, Track newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						musicLabel.setText(mp3Player.getSoundManager().getSoundName(newValue.getSoundFile()));
					}
				});
				
			}
			
		});
		
		/**
		 * Lsitener der timeProperty
		 * Aktualisierungszeit pro Sekunde
		 */
		pomodoro.timeProperty().addListener(new ChangeListener <Number>() {
			
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						int timeValue = newValue.intValue();
						String timeText = getTimeForm(timeValue);
						countdownButton.setText(timeText);
					}
				});
			}
		});
		
//		todoListView.setCellFactory(new Callback <ListView<Todo>, ListCell<Todo>>(){
//			@Override
//			public ListCell<Todo> call(ListView<Todo> param) {
//				return new TodoCell();
//			}
//		});
		
		/**
		 * Listener der themeProperty
		 * Aktualisieren Theme
		 */
		pomodoro.themeProperty().addListener(new ChangeListener <String>() {

			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						photoView.updateImage(newValue);
					}
				});
				
			}
			
		});
		
		/**
		 * Addieren neu Text in TodoList
		 */
		addNew.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				if(textField.getText() != null) {
					Todo newTodo = new Todo(textField.getText());
					todoList.addNew(newTodo);
					for(Todo aktTodo : todoList.getTodos()) {
						System.out.println(aktTodo.getContent());
					}
					System.out.println();
					textField.clear();
				}
				
			}
			
		});
		
		/**
		 * Action in settingButton
		 */
		settingButton.setOnAction(event -> {
			app.switchView(ViewName.SETTINGVIEW);
			Platform.runLater(() -> {

				if(mp3Player.playingProperty().getValue()) {
					mp3Player.pause();
				}
				
				if(pomodoro.countingProperty().getValue()) {
					pomodoro.pause();
				}
				
				
			});
			
		});
	}
	
	/**
	 * Formatieren Zeit in String
	 * @param timeValue
	 * @return
	 */
	public String getTimeForm(int timeValue) {
		String timeText = "";
		int minutes = timeValue / 60;
		int seconds = timeValue % 60;
		if(minutes < 10) {
			timeText += "0" + Integer.toString(minutes);
		}
		else {
			timeText += Integer.toString(minutes);
		}
		timeText += ":";
		if(seconds < 10) {
			timeText += "0" + Integer.toString(seconds);
		}
		else {
			timeText += Integer.toString(seconds);
		}
		return timeText;
	}
	
	public Pane getRoot() {
		return mainView;
	}
}
