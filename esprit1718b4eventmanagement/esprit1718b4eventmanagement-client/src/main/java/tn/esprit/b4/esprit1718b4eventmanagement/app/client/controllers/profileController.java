/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.io.File;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;



import javafx.scene.image.Image;



/**
 * FXML Controller class
 *
 * @author Asus
 */
public class profileController implements Initializable {

   
    @FXML
    private Label txtlastname;
    @FXML
    private Label txtlogin;
    @FXML
    private Label txtpassword;
    @FXML
    private Label txtemail;
    @FXML
    private Label txtfirstname;
    @FXML
    private Label txtphonenumber;
    @FXML
    private JFXTextField txtmodFn;
    @FXML
    private JFXTextField txtmodLn;
    @FXML
    private JFXTextField txtmodEmail;
    @FXML
    private JFXTextField txtmodnumber;
    @FXML
    private JFXPasswordField txtpwd;
    @FXML
    private JFXPasswordField txtpwd1;
    @FXML
    private JFXTextField txtmodlogin;
User user = LoginController.user;
    @FXML
    private JFXButton txtback;
    @FXML
    private Label ss;
    @FXML
    private Label fileSelected;
    @FXML
    private ImageView imageView;
    
    private String imageFile;
    @FXML
    private ImageView imageviewprofil;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    	 Image image = new Image("http://localhost/image/" +user.getImage());
        imageviewprofil.setImage(image);
        
        
        txtphonenumber.setText(user.getNumtel());
             txtlastname.setText(user.getLastname());
             txtfirstname.setText(user.getFirstname());
            txtemail.setText(user.getEmail()); 
            txtlogin.setText(user.getLogin());
            txtpassword.setText(user.getPassword());
                        
                txtmodnumber.setText(user.getNumtel());
             txtmodLn.setText(user.getLastname());
             txtmodFn.setText(user.getFirstname());
            txtmodEmail.setText(user.getEmail()); 
            txtmodlogin.setText(user.getLogin());
            txtpwd.setText(user.getPassword());        
                        
                txtpwd1.setText(user.getPassword());   
                
                
                fileSelected.setVisible(false);
    }    

    @FXML
    private void OnBrowserAction(ActionEvent event) throws IOException {
    	  
    	String path_img;
          FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Select Image File");
          fileChooser.getExtensionFilters().addAll(
                  new FileChooser.ExtensionFilter("Image Files",
                          "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
          File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

          if (selectedFile != null) {

              imageFile = selectedFile.toURI().toURL().toString();
          	path_img = selectedFile.getPath();
			ss.setText(path_img);
              Image image = new Image(imageFile);
              imageView.setImage(image);
          } else {
              fileSelected.setText("Image file selection cancelled.");
          }
       /*
    	File selectedfile;
		String path_img;
		Upload up = new Upload();

		FileChooser fc = new FileChooser();
		// fc.setInitialDirectory(new File ("C:\\Users\\user\\Desktop\\img"));

		fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Image", ".jpg", ".png", ".PNG", ".JPG"));

		selectedfile = fc.showOpenDialog(null);

		if (selectedfile != null) {

			BufferedImage bufferedImage = ImageIO.read(selectedfile);
			path_img = selectedfile.getName();
			ss.setText(path_img);
			WritableImage image = SwingFXUtils.toFXImage(bufferedImage, null);
			// imageIMGV.setImage(image);

			up.upload(selectedfile);
		} else {
			System.out.println("FICHIER erroné");
		}*/
    }

    @FXML
    private void OnChangeAction(ActionEvent event) {
    	
    	
		String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
Context context;

	try {
		context= new InitialContext();

	UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
   
        	user.setEmail( txtmodEmail.getText());
        	user.setFirstname(txtmodFn.getText());
        	user.setLastname(txtmodLn.getText());
        	user.setLogin(txtmodlogin.getText());
        	user.setPassword(txtpwd.getText());
        	user.setNumtel( txtmodnumber.getText());
        
        	if (txtpwd.getText().equals(txtpwd1.getText()))
        	{
        		Alert alert = new Alert(AlertType.CONFIRMATION);
        		alert.setTitle("WARNING");
        		alert.setHeaderText("Are You Sure to Update   ?");
        		
        		if (alert.showAndWait().get () == ButtonType.OK)
        		{ 		
proxy.update1(user);
	 
	 
	System.out.println(user.getLastname());
        		}
	
		
        	}else
        	{
        		Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Error");
				alert.setHeaderText(null);
				alert.setContentText("veuiller verifier le pwd!");
				alert.showAndWait();
        	}
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	

    	
    }

    @FXML
    private void OnCleanAction(ActionEvent event) {
        
        
        txtmodnumber.setText("");
             txtmodLn.setText("");
             txtmodFn.setText("");
            txtmodEmail.setText(""); 
            txtmodlogin.setText("");
            txtpwd.setText("");        
                        
                txtpwd1.setText("");   
    }

    @FXML
    private void OnCancelAction(ActionEvent event) {
         txtmodnumber.setText(user.getNumtel());
             txtmodLn.setText(user.getFirstname());
             txtmodFn.setText(user.getLastname());
            txtmodEmail.setText(user.getEmail()); 
            txtmodlogin.setText(user.getLogin());
            txtpwd.setText(user.getPassword());        
                        
                txtpwd1.setText(user.getPassword());     
    }

    @FXML
    private void OnBackAction(ActionEvent event) throws IOException {
        Parent parent= null;
		    	parent  =FXMLLoader.load(getClass().getResource("/views/HomeView.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				 txtback.getScene().getWindow().hide();
    }
    
}
