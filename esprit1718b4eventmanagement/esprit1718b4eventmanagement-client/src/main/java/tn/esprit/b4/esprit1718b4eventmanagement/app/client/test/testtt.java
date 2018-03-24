package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class testtt  extends Application {
	
	
	

	@Override
	public void start(Stage primaryStage) throws Exception {
		Parent parent =FXMLLoader.load(getClass().getResource("/views/Equipement.fxml"));
		Scene scene=new Scene(parent);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}
}
