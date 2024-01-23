package presentation.main;

import business.Todo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TodoView extends VBox{
	
	public final double DISTANCE = 10;
	public final double MAX_WIDTH = 200;
	
	public Button addNew;
	public TextField textField;
	
	public ListView <Todo> todoListView;
	
	
	public TodoView() {
		
		HBox topBox = new HBox();
		Label todoLabel = new Label ("To-Do List");
		todoLabel.setId("todo-label");
		topBox.getChildren().addAll(todoLabel);
		topBox.setAlignment(Pos.CENTER_LEFT);
		topBox.setMaxWidth(MAX_WIDTH);
		
		todoListView = new ListView <>();
		
		HBox bottomBox = new HBox();
		setBottomBox(bottomBox);
		
		this.getChildren().addAll(topBox, todoListView, bottomBox);
		this.setSpacing(DISTANCE);
		this.setMaxWidth(MAX_WIDTH);
	}
	
	public void setBottomBox(HBox box) {
		addNew = new Button("add");
		textField = new TextField();
		
		box.getChildren().addAll(addNew, textField);
		box.setAlignment(Pos.CENTER);
	}
}
