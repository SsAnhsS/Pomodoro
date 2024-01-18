package presentation.main;

import java.util.ArrayList;

import business.Todo;
import javafx.scene.control.ListView;

public class TodoViewController {
	
	TodoView todoView;
	
	ListView <Todo> todoListView;
	
	private ArrayList <Todo> todoList;
	
	public TodoViewController() {
		todoView = new TodoView();
		
		todoListView = todoView.todoListView;
		
		initialize();
	}
	
	public void initialize() {
		
	}
}
