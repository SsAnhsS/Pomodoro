package presentation.main;

import business.Todo;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextField;

public class TodoCell extends ListCell<Todo> {
	
	public CheckBox checkBox;
	
	public TodoCell() {
		checkBox = new CheckBox();
		
	}
}
