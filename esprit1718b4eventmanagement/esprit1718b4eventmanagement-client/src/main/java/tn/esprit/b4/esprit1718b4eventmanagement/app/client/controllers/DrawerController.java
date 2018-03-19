/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class DrawerController implements Initializable {

    @FXML
    private JFXButton homeBtn;
    @FXML
    private JFXButton appointmentBtn;
    @FXML
    private JFXButton paymentBtn;
    @FXML
    private JFXButton logoutBtn;
    @FXML
    private JFXButton exitBtn;
    @FXML
    private JFXButton ProfileBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     @FXML
    private void logOut(ActionEvent event) throws IOException {
        Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					 ProfileBtn.getScene().getWindow().hide();
      
        
    }

    @FXML
    private void exit(ActionEvent event) {
        Platform.exit();
    
    }

    @FXML
    private void onProfileClik(ActionEvent event) throws IOException {
          	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/Profile.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					 ProfileBtn.getScene().getWindow().hide();
    }
    
}
