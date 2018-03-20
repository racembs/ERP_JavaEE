/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class VerifCompteController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXPasswordField txtcode;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView imgProgress;
    @FXML
    private JFXButton btncancel;
User user =LoginController.user;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnLoginVerifAction(ActionEvent event) throws IOException, NamingException {
        
        	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
Context context;

		context= new InitialContext();
        
          UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
          
          
        if ((txtcode.getText().equals("123"))&&(user.getRole().equals("GMAO")))
        {
        
        	Parent parent= null;
		    	parent  =FXMLLoader.load(getClass().getResource("/views/HomeView.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				 btnLogin.getScene().getWindow().hide();
        user.setStatut("valable");
        user.setNb("0");
        proxy.update(user);
        
        }
        else if ((txtcode.getText().equals("123"))&&(user.getRole().equals("GPAO")))
        {
        	Parent parent= null;
		    	parent  =FXMLLoader.load(getClass().getResource("/views/Dashboard.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				 btnLogin.getScene().getWindow().hide();
        
            user.setStatut("valable");
        proxy.update(user);
        
        }
        else 
        {Alert alert = new Alert(Alert.AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Votre Code est inncorrect veuillez verifier !");
				alert.showAndWait();
				}
        
    }

    @FXML
    private void OnCancelAction(ActionEvent event) throws IOException {
         	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					 btnLogin.getScene().getWindow().hide();
    }
    
}
