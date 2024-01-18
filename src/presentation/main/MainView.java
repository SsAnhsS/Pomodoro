package presentation.main;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import mp3player.scene.layout.ImageViewPane;

public class MainView extends BorderPane {
	
	public Insets sameInsets = new Insets(10);
	
	public ImageView imageView;
	
	public Button settingButton;
	
	TodoView todoView;
	
	public MainView(){
		
		Label name = new Label("Main View");
		this.setTop(name);
		
		HBox centerBox = new HBox();
		setCenterBox(centerBox);
		this.setCenter(centerBox);
		
		todoView = new TodoView();
		this.setLeft(todoView);
		
		HBox rightBox = new HBox();
		setRightBox(rightBox);
		this.setRight(rightBox);
	}
	
	public void setCenterBox(HBox box){
		
		Label name = new Label("image-box");
		
		imageView = new ImageView();
		
		try {
			imageView.setImage(new Image(new FileInputStream("src/data/images/tomato.png")));
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		
		
		ImageViewPane imagePane = new ImageViewPane(imageView);
		imagePane.setMaxSize(400, 400);
		
		box.getChildren().addAll(imagePane);
		box.setPadding(sameInsets);
		box.setAlignment(Pos.CENTER);
		
		box.setStyle("-fx-background-color: white;");
		
	}
	
	public void setRightBox(HBox box) {
		settingButton = new Button("Setting");
		box.getChildren().addAll(settingButton);
		box.setPadding(sameInsets);
		box.setAlignment(Pos.TOP_CENTER);
	}
}
