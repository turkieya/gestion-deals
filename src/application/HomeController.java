package application;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;

import java.io.IOException;

import javafx.event.ActionEvent;

import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class HomeController {
	@FXML
	private AnchorPane home;
	@FXML
	private Button btnadmin;
	@FXML
	private Button btnclient;

	// Event Listener on Button[#btnadmin].onAction
	@FXML
	public void loginadmin(ActionEvent event) throws IOException {
		Stage logadm = new Stage();
        home.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("LoginAdmin.fxml"));
        Scene scene = new Scene(root);
        logadm.setScene(scene);
        logadm.show();
        logadm.setResizable(false); 
	}
	// Event Listener on Button[#btnclient].onAction
	@FXML
	public void loginclient(ActionEvent event) throws IOException {
		Stage logclt = new Stage();
        home.getScene().getWindow().hide();
        Parent root;
        root = FXMLLoader.load(getClass().getResource("LoginClient.fxml"));
        Scene scene = new Scene(root);
        logclt.setScene(scene);
        logclt.show();
        logclt.setResizable(false); 
	}
}
