package presentation.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import mp3player.scene.layout.ImageViewPane;

public class MainView extends BorderPane {
	
	public Insets sameInsets = new Insets(10);
	
	public ImageView imageView;
	
	TodoView todoView;
	
	public MainView(){
		
		Label name = new Label("Main View");
		this.setTop(name);
		
		HBox centerBox = new HBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		todoView = new TodoView();
		this.setLeft(todoView);
	}
	
	public void setCenterBox(HBox box) {
		
		Label name = new Label("image-box");
		
		imageView = new ImageView();
		
		imageView.setImage(new Image("file:///D:/GitHub/Pomodoro/src/data/images/tomato.png"));
		
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMaxSize(400, 400);
		
		box.getChildren().addAll(imagePane);
		
		box.setStyle("-fx-background-color: red;");
		box.setPadding(sameInsets);
		
		box.setAlignment(Pos.CENTER);
		
	}
}
