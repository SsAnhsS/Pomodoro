package presentation.main;

import business.Todo;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;

/**
 * ToDoCell Klasse
 */
public class TodoCell extends ListCell<Todo> {
	
	private HBox todoBox;
	
	public CheckBox checkBox;
	public Text text;
	
	public TodoCell() {
		todoBox = new HBox();
		
		checkBox = new CheckBox();
		text = new Text();
		
		todoBox.getChildren().addAll(checkBox, text);
		todoBox.setSpacing(10);
	}
	
	/**
	 * Informationen von Todo update in Cell
	 */
	protected void updateItem(Todo item, boolean empty) {
		super.updateItem(item, empty);
		
		if (!empty) {
			//checkBox.setText(item.getDone());
			text.setText(item.getContent());
			
			this.setGraphic(todoBox);
		} else {
			this.setGraphic(null);
		}
		
	}
}
