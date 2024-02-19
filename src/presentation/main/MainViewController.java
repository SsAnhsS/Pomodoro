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
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Callback;

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
	public Label relaxTime;
	public HBox sessionBox;
	
	public Label countdownPhase;
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
		relaxTime = mainView.relaxTime;
		sessionBox = mainView.sessionBox;
		
		countdownPhase = mainView.countdownPhase;
		photoView = mainView.photoView;
		countdownButton = mainView.countdownButton;
		musicLabel = mainView.musicLabel;
		
		settingButton = mainView.settingButton;
		
		float focusTimeValue = (float) pomodoro.focusTimeProperty().getValue()/60;
		if(focusTimeValue < 1) {
			focusTime.setText("Focus Time: " + focusTimeValue + " Minuten");
		}
		else {
			focusTime.setText("Focus Time: " + (int)focusTimeValue + " Minuten");
		}
		
		float relaxTimeValue = (float)pomodoro.relaxTimeProperty().getValue() / 60;
		if(relaxTimeValue < 1) {
			relaxTime.setText("Telax Time: " + relaxTimeValue + " Minuten");
		}
		else {
			relaxTime.setText("Telax Time: " + (int)relaxTimeValue + " Minuten");
		}
		
		updateCountDownPhaseLabel(pomodoro.isFocusTimeProperty().getValue());
		updateMusicLabel();
		initialize();
	}
	
	public void initialize() {
		
		/**
		 * Listener der concentrationTimeProperty
		 */
		pomodoro.focusTimeProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						String newTime = String.valueOf(newValue.intValue() / 60);
						focusTime.setText("Focus Time: " + newTime + " minuten");
					}
				});
				
			}

		});
		
		/**
		 * Listener der pauseTimeProperty
		 */
		pomodoro.relaxTimeProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						String newTime = String.valueOf(newValue.intValue() / 60);
						relaxTime.setText("Relax Time: " + newTime + " minuten");
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
							newPhotoView.setOpacity(0.5);
							sessionBox.getChildren().add(newPhotoView);
						}
					}
				});
			}

		});
		
		/**
		 * Listener der turnPropery
		 */
		pomodoro.turnProperty().addListener(new ChangeListener<Number>(){

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				Platform.runLater(new Runnable() {
					public void run() {
						sessionBox.getChildren().clear();
						for(int i = 0; i < pomodoro.numberSessionProperty().getValue(); i++) {
							PhotoView newPhotoView = new PhotoView();
							newPhotoView.setMaxSize(20, 20);
							if(i >= newValue.intValue()) {
								newPhotoView.setOpacity(0.5);
							}
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
				if(newValue != null) {
					Platform.runLater(new Runnable() {
						public void run() {
							updateMusicLabel();
						}
					});
				}
			}
			
		});
		
		/**
		 * Listener der current count down phase
		 */
		pomodoro.isFocusTimeProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> value, Boolean oldValue, Boolean newValue) {
				updateCountDownPhaseLabel(newValue);
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
		 * Set TodoCell fuer List View
		 */
		todoListView.setCellFactory(new Callback <ListView<Todo>, ListCell<Todo>>(){
			@Override
			public ListCell<Todo> call(ListView<Todo> param) {
				return new TodoCell();
			}
		});
		
		/**
		 * selecktiert einem Todo, um fertig zu makieren
		 */
		todoListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Todo>() {

			@Override
			public void changed(ObservableValue<? extends Todo> observable, Todo oldTodo, Todo newTodo) {
				if(newTodo != null) {
					Platform.runLater(() -> {
						newTodo.setDone();
						updateTodoList(todoList);
					});
				}
			}
			
		});
		
		/**
		 * Addieren neu Text in TodoList
		 */
		addNew.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				String newText = textField.getText();
				
				if(newText != null && !newText.isEmpty()) {
					Todo newTodo = new Todo(newText);
					todoList.addNew(newTodo);
					textField.clear();
					Platform.runLater(() -> updateTodoList(todoList));
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
	
	/**
	 * Aktualisieren Todo-List
	 * @param todoList
	 */
	public void updateTodoList(TodoList todoList) {
		todos = todoList.getTodos();
		ObservableList <Todo> todolistModel = todoListView.getItems();
		todolistModel.clear();
		todolistModel.addAll(todos);
		todoListView.setItems(todolistModel);
	}
	
	/**
	 * Aktualisieren Song-Name
	 */
	public void updateMusicLabel() {
		String songName = "";
		if(pomodoro.singlePlayProperty().getValue()) {
			if(mp3Player.currentTrackProperty().getValue()!= null) {
				songName = mp3Player.getSoundManager().getSoundName(mp3Player.currentTrackProperty().getValue().getSoundFile());
			}
		}
		else {
			if(mp3Player.currentPlaylistProperty().getValue() != null) {
				for(Track aktTrack : mp3Player.currentPlaylistProperty().getValue().getTracks()) {
					if (!songName.isEmpty()) {
						songName += " - ";
					}
					
					songName += mp3Player.getSoundManager().getSoundName(aktTrack.getSoundFile());
					
				}
			}
		}
		musicLabel.setText(songName);
	}
	
	/**
	 * Aktualisieren Label von Count Down Phase 
	 * @param value
	 */
	public void updateCountDownPhaseLabel(boolean value) {
		Platform.runLater(new Runnable() {
			public void run() {
				if(value) {
					countdownPhase.setText("Focus Time");
				}
				else {
					countdownPhase.setText("Relax Time");
				}
			}
		});
	}
	
	public Pane getRoot() {
		return mainView;
	}
}
