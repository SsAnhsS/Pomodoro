package presentation.main;

import java.util.ArrayList;

import application.App;
import application.ViewName;
import business.Todo;
import business.TodoList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MainViewController {
	
	private App app;
	private ArrayList <Todo> todos;
	private TodoList todoList = new TodoList();
	
	public Button addNew;
	public TextField textField;
	public ListView <Todo> todoListView;
	
	public ImageView imageView;
	public Text countdownTimeValue;
	
	public Button settingButton;
	
	public String file;
	
	MainView mainView;
	
	public MainViewController(App app) {
		this.app = app;
		
		mainView = new MainView();
		
		addNew = mainView.addNew;
		textField = mainView.textField;
		todoListView = mainView.todoListView;
		
		imageView = mainView.imageView;
		countdownTimeValue = mainView.countdownTimeValue;
		
		settingButton = mainView.settingButton;
		
		
		initialize();
	}
	
	public void initialize() {
		
//		todoListView.setCellFactory(new Callback <ListView<Todo>, ListCell<Todo>>(){
//			@Override
//			public ListCell<Todo> call(ListView<Todo> param) {
//				return new TodoCell();
//			}
//		});
		
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
		});
	}
	
	
	public Pane getRoot() {
		return mainView;
	}
}
