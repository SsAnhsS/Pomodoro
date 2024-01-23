package business;

import java.util.ArrayList;

public class TodoList {
	
	private Todo todo;
	
	private ArrayList <Todo> todos; 
	
	public TodoList() {
		todo = new Todo("New Todo-List");
		todos = new ArrayList<Todo>();
		addNew(todo);
	}
	
	public void addNew(Todo todo) {
		todos.add(todo);
	}
	
	public int getTotal() {
		return todos.size();
	}
	
	public ArrayList<Todo> getTodos(){
		return todos;
	}
}
