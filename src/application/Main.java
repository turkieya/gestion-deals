package application;
	

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) throws Exception {
		try {
			
			Parent root = FXMLLoader.load(getClass().getResource("Home.fxml"));
	            Scene scene = new Scene(root,600,400);
	            primaryStage.setTitle("Best Deal");
	            primaryStage.setScene(scene);
	            primaryStage.show();
	            primaryStage.setResizable(true);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
 