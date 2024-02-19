package presentation.main;

import business.Todo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * Main View Klasse, im Main View zu gestalten
 */
public class MainView extends BorderPane {
	
	public final double DISTANCE = 10;
	public final double MAX_WIDTH = 200;
	
	public Label focusTime;
	public Label relaxTime;
	public HBox sessionBox;
	
	public Button addNew;
	public TextField textField;
	public ListView <Todo> todoListView;
	
	public Label countdownPhase;
	public PhotoView photoView;
	public Button countdownButton;
	public Label musicLabel;
	
	public Button settingButton;
	
	TodoView todoView;
	
	
	public MainView(){
		
		HBox topBox = new HBox();
		
		VBox leftBox = new VBox();
		focusTime = new Label();
		relaxTime = new Label();
		sessionBox = new HBox();
		for(int i = 0; i < 2; i++) {
			PhotoView newPhotoView = new PhotoView();
			newPhotoView.setMaxSize(20, 20);
			newPhotoView.setOpacity(0.5);
			sessionBox.getChildren().add(newPhotoView);
		}
		sessionBox.setSpacing(DISTANCE);
		
		leftBox.getChildren().addAll(focusTime, relaxTime, sessionBox);
		leftBox.setSpacing(DISTANCE);
		leftBox.setMinWidth(MAX_WIDTH*5);
		
		HBox rightBox = new HBox();
		setRightBox(rightBox);
		
		topBox.getChildren().addAll(leftBox, rightBox);
		topBox.setSpacing(150);
		topBox.setAlignment(Pos.CENTER_LEFT);
		
		this.setTop(topBox);
		
		VBox centerBox = new VBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		todoView = new TodoView();
		addNew = todoView.addNew;
		textField = todoView.textField;
		todoListView = todoView.todoListView;
		this.setLeft(todoView);
		
		HBox emptyBox = new HBox();
		emptyBox.setMinWidth(MAX_WIDTH);
		this.setRight(emptyBox);
		
		BorderPane.setMargin(topBox, new Insets(20, 20, 0, 30));
		BorderPane.setMargin(todoView, new Insets(20, 0, 0, 20));
		BorderPane.setMargin(emptyBox, new Insets(20, 20, 0, 0));
		
	}
	
	public void setCenterBox(VBox box){
		
		countdownPhase = new Label();
		countdownPhase.getStyleClass().add(".text-24px");
		
		StackPane pane = new StackPane();
		pane.setAlignment(Pos.CENTER);
		
		countdownButton = new Button();
		countdownButton.setText("Start!");
		countdownButton.setMinSize(450, 450);
		countdownButton.setStyle("-fx-background-color: transparent;");
		countdownButton.getStyleClass().add("text-60");
		
		
		photoView = new PhotoView();
		photoView.setMaxSize(500, 500);
		StackPane.setAlignment(photoView, Pos.CENTER);
		
		musicLabel = new Label("Music");
		
		pane.getChildren().addAll(photoView, countdownButton);
		
		
		box.getChildren().addAll(countdownPhase, pane, musicLabel);
		box.setAlignment(Pos.BASELINE_CENTER);
		VBox.setVgrow(box, Priority.ALWAYS);
	}
	
	public void setRightBox(HBox box) {
		settingButton = new Button();
		settingButton.getStyleClass().add("icon-button");
		settingButton.setId("setting-button");
		box.getChildren().addAll(settingButton);
		box.setAlignment(Pos.TOP_RIGHT);
		HBox.setHgrow(box, Priority.ALWAYS);
	}
}
