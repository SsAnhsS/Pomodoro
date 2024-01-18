package business;

import java.util.ArrayList;

public class TodoList {
	
	private Todo todo;
	
	private ArrayList <Todo> todoList; 
	
	public TodoList() {
		todo = new Todo("New Todo");
		todoList = new ArrayList<Todo>();
		addNew(todo);
	}
	
	public void addNew(Todo todo) {
		todoList.add(todo);
	}
}
