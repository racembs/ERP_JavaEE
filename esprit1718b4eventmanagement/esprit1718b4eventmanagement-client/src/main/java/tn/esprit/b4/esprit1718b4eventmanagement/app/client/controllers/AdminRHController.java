/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class AdminRHController implements Initializable {

    @FXML
    private JFXTextField txtfisrtname;
    @FXML
    private JFXTextField txtlastname;
    @FXML
    private JFXTextField txtemail;
    @FXML
    private JFXTextField txtphonenumber;
    @FXML
    private JFXTextField txtpassword;
    @FXML
    private ToggleGroup q;
    @FXML
    private JFXTextField txtlogin;
    @FXML
    private JFXRadioButton GPAO;
    @FXML
    private JFXRadioButton GMAO;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void OnCancelClick(ActionEvent event) {
    	txtfisrtname.setText("");
    	txtlastname.setText("");
    	txtemail.setText("");
    	txtphonenumber.setText("");
    	txtpassword.setText("");
    	 txtlogin.setText("");
    	
    }

    @FXML
    private void OnEditClick(ActionEvent event) throws NamingException {
		String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
Context context;

	
		context= new InitialContext();

	UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
    	String mail= txtemail.getText();
    	String firstname=txtfisrtname.getText();
    	String login=txtlogin.getText();
    	String number=txtphonenumber.getText();
    	String password=txtpassword.getText();
    	String lastname=txtlastname.getText();
    	String role;
    if(	GPAO.isSelected())
    {role="GPAO";}else
    {role="GMAO";}
    	User user=new User(firstname,lastname,login,password,mail,role,number,"valable","0");
    	proxy.save(user);
    	
    	
    	
    	
    }
    
}
