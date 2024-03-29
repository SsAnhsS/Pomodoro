package business;

import java.util.ArrayList;

/**
 * To Do List Klasse
 */
public class TodoList {
	
	private Todo todo;
	
	private ArrayList <Todo> todos; 
	
	public TodoList() {
		todos = new ArrayList<Todo>();
	}
	
	/**
	 * Addieren neu ToDo in To Do List
	 * @param todo
	 */
	public void addNew(Todo todo) {
		todos.add(todo);
	}
	
	/**
	 * Getter und Setter Methode
	 */
	public int getTotal() {
		return todos.size();
	}
	
	public ArrayList<Todo> getTodos(){
		return todos;
	}
}
