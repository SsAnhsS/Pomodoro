package presentation.main;

import java.util.ArrayList;

import application.App;
import application.ViewName;
import business.MP3Player;
import business.Pomodoro;
import business.Todo;
import business.TodoList;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import presentation.setting.SettingViewController;
import presentation.setting.ThemeName;

public class MainViewController {
	
	private App app;
	private Pomodoro pomodoro;
	private MP3Player mp3Player;
	
	private ArrayList <Todo> todos;
	private TodoList todoList = new TodoList();
	
	public Button addNew;
	public TextField textField;
	public ListView <Todo> todoListView;
	
	public PhotoView photoView;
	public Button countdownButton;
	
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
		
		photoView = mainView.photoView;
		countdownButton = mainView.countdownButton;
		
		settingButton = mainView.settingButton;
		
		int timeValue = pomodoro.timeProperty().getValue();
		countdownButton.setText(getTimeForm(timeValue));
		initialize();
	}
	
	public void initialize() {
		
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
		
		settingButton.setOnAction(event -> {
			app.switchView(ViewName.SETTINGVIEW);
			Platform.runLater(() -> {
				if(pomodoro.countingProperty().getValue()) {
					pomodoro.pause();
				}
			});
			
		});
	}
	
	/**
	 * Zeit in String
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
	
	public void updatePhotoView(String themeName) {
		
	}
	
	public Pane getRoot() {
		return mainView;
	}
}
