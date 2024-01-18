package presentation.main;

import business.Todo;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

public class TodoView extends BorderPane{
	
	public Button addNew;
	public Button toggle; 
	public TextField textField;
	
	public ListView <Todo> todoListView;
	
	
	public TodoView() {
		
		HBox topBox = new HBox();
		setTopBox(topBox);
		this.setTop(topBox);
		
		todoListView = new ListView <>();
		this.setCenter(todoListView);
		
		HBox bottomBox = new HBox();
		setBottomBox(bottomBox);
		this.setBottom(bottomBox);
		
	}
	
	public void setTopBox(HBox box) {
		
		Label todoLabel = new Label ("To-Do List");
		todoLabel.setId("todo-label");
		
		toggle = new Button("toggle");
		
		box.getChildren().addAll(todoLabel, toggle);
		box.setAlignment(Pos.CENTER);
	}
	
	public void setBottomBox(HBox box) {
		addNew = new Button("add");
		textField = new TextField();
		
		box.getChildren().addAll(addNew, textField);
		box.setAlignment(Pos.CENTER);
	}
}
