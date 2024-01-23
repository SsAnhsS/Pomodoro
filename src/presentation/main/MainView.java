package presentation.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import business.Todo;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import mp3player.scene.layout.ImageViewPane;

public class MainView extends BorderPane {
	
	public final double DISTANCE = 10;
	public final double MAX_WIDTH = 200;
	
	public Insets sameInsets = new Insets(10);
	
	public Button addNew;
	public TextField textField;
	public ListView <Todo> todoListView;
	
	public ImageView imageView;
	public Text countdownTimeValue;
	
	public Button settingButton;
	
	public String file = "src/data/images/tomato.png";
	
	TodoView todoView;
	
	public MainView(){
		
		Label name = new Label("Main View");
		this.setTop(name);
		
		HBox centerBox = new HBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		todoView = new TodoView();
		addNew = todoView.addNew;
		textField = todoView.textField;
		todoListView = todoView.todoListView;
		this.setLeft(todoView);
		
		HBox rightBox = new HBox();
		setRightBox(rightBox);
		rightBox.setMinWidth(MAX_WIDTH);
		this.setRight(rightBox);
	}
	
	public void setCenterBox(HBox box){
		
		StackPane pane = new StackPane();
		
		imageView = new ImageView();
		
		try {
			imageView.setImage(new Image(new FileInputStream(file)));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMaxSize(500, 500);
		
		countdownTimeValue = new Text();
		countdownTimeValue.setId("countdown-time");
		countdownTimeValue.setText("25:00");
		
		pane.getChildren().addAll(imagePane, countdownTimeValue);
		pane.setAlignment(Pos.CENTER);
		
		box.getChildren().addAll(pane);
		box.setPadding(sameInsets);
		box.setAlignment(Pos.CENTER);
	}
	
	public void setRightBox(HBox box) {
		settingButton = new Button("Setting");
		box.getChildren().addAll(settingButton);
		box.setPadding(sameInsets);
		box.setAlignment(Pos.TOP_CENTER);
	}
}
