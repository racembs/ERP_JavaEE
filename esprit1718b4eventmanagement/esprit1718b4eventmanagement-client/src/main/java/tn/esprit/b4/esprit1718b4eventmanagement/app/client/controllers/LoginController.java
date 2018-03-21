/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;

import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class LoginController implements Initializable {

    @FXML
    private StackPane rootPane;
    @FXML
    private JFXTextField txtUsername;
    @FXML
    private JFXPasswordField txtPassword;
    @FXML
    private JFXButton btnLogin;
    @FXML
    private ImageView imgProgress;
    
    
public static User user = new User();
    @FXML
    private Hyperlink lienpwdforgot;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void login(ActionEvent event) throws IOException {
    	
    	
    	Context context;
		try {
			context = new InitialContext();
			UserServiceRemote userService = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
		
		
		
		
		System.out.println("JNDI OK");

		 user = userService.findByLogin(txtUsername.getText());
    
     	
    	
    	
			 

			if (user == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username !");
				alert.showAndWait();
			} 
			else if ((user.getStatut().equals("Bloquer"))&&(user.getNb().equals("3")))
			{Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("Votre Compte est Bloquer veuiller saisir le code envoie sur votre mail !");
			alert.showAndWait();
			
			Parent parent= null;
	    	parent  =FXMLLoader.load(getClass().getResource("/views/VerifCompte.fxml"));
			Scene scene=new Scene(parent);
			Stage primaryStage= new Stage(); 
			primaryStage.setScene(scene);
			primaryStage.show();
			 btnLogin.getScene().getWindow().hide();
			
			
			}
			else if ( user.getNb().equals("3"))
			{
				user.setStatut("Bloquer");
				userService.update(user);
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Votre Compte est Bloquer mtn!");
				alert.showAndWait();
			}
			else
			
			{
				
				
				
				
				 if (txtPassword.getText().equals(user.getPassword())&&(user.getRole().equals("GMAO"))&&(user.getStatut().equals("valable"))) {
					System.out.println("3okkkeeyy");
					
user.setNb("0");
					
					userService.update(user);
			    	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/HomeView.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					 btnLogin.getScene().getWindow().hide();
				
				} 
				else if (txtPassword.getText().equals(user.getPassword())&&(user.getRole().equals("GPAO"))&&(user.getStatut().equals("valable"))) {
					System.out.println("3okkkeeyy");
					
user.setNb("0");
					
					userService.update(user);
			    	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/MenuGPAO.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					  btnLogin.getScene().getWindow().hide();
				} 
				else if (txtPassword.getText().equals(user.getPassword())&&(user.getRole().equals("ResponsableRH"))&&(user.getStatut().equals("valable"))) {
					System.out.println("3okkkeeyy");
					

					
				
			    	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/InterfaceAdminRH.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					  btnLogin.getScene().getWindow().hide();
				} 
				else {
					Alert alert = new Alert(AlertType.ERROR);
					alert.setTitle("Error");
					alert.setHeaderText(null);
					alert.setContentText("Invalid username or password !");
					alert.showAndWait();
					
					
				int i=Integer.valueOf(user.getNb())+1;
					user.setNb(Integer.toString(i));
					
					userService.update(user);
					
				}

			}
	
	
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }

    @FXML
    private void OnForgotPassWord(ActionEvent event) throws NamingException, IOException {
        Context context;
		
			context = new InitialContext();
			UserServiceRemote userService = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
		
		
		
		
		System.out.println("JNDI OK");

		 user = userService.findByLogin(txtUsername.getText());
                 
                 if (txtUsername.getText().equals(""))
                 {Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Veuillez remplir le username !");
				alert.showAndWait();}
                 else if (user == null) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("Invalid username !");
				alert.showAndWait();
			} 
                 else 
                 {
                 
                 
                 		userService.update(user);
			    	Parent parent= null;
			    	parent  =FXMLLoader.load(getClass().getResource("/views/PassWordForgot.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					  lienpwdforgot.getScene().getWindow().hide();
                 
                 }
        
        
        
    }
    
}
