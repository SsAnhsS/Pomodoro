package business;

import java.util.ArrayList;

public class TodoList {
	
	private Todo todo;
	
	private ArrayList <Todo> todoList; 
	
	public TodoList() {
		todoList = new ArrayList<Todo>();
	}
	
	public void addNew(Todo todo) {
		todoList.add(todo);
	}
}
