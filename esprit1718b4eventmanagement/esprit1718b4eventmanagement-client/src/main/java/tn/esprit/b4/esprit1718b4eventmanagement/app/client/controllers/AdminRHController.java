/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;



import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
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
    @FXML
    private Label labnum;
    @FXML
    private Label labfirstname;
    @FXML
    private Label lablastname;
    @FXML
    private Label labmail;
    @FXML
    private Label lablogin;
    @FXML
    private Label labpassword;
    @FXML
    private Label labrole;
    @FXML
    private Label labstatut;
    @FXML
    private CheckBox chekVal;
    @FXML
    private CheckBox CheckBloc;
    @FXML
    private JFXButton BtnConfirme;
    @FXML
    private TableView<User> tableau;
    @FXML
    private TableColumn<User, String> ColFirstName;
    @FXML
    private TableColumn<User, String> ColLastName;
    @FXML
    private TableColumn<User, String> ColEmail;
    @FXML
    private TableColumn<User, String> ColStatut;
    @FXML
    private JFXButton BtnConfirme1;
    @FXML
    private CheckBox chekGmao;
    @FXML
    private CheckBox chekGPAO;
   String login ;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	BtnConfirme.setVisible(false);
    	chekVal.setVisible(false);
    	CheckBloc.setVisible(false);
    	
    	BtnConfirme1.setVisible(false);
    	chekGPAO.setVisible(false);
    	chekGmao.setVisible(false);
	


    	
    	
    	
    	
    	
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

    @FXML
    private void OnChangeAction(ActionEvent event) {
    	BtnConfirme.setVisible(true);
    	chekVal.setVisible(true);
    	CheckBloc.setVisible(true);
        BtnConfirme1.setVisible(false);
    	chekGPAO.setVisible(false);
    	chekGmao.setVisible(false);
    }

    @FXML
    private void OnConfirmeAction(ActionEvent event) throws NamingException {
        
        
        
        
        
    	
      	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
    		String select="";
    		User	user=proxy.findByLogin(login);
    	if ((CheckBloc.isSelected())&&(chekVal.isSelected()))
    		{
                    
                    	Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("veuillez choisir un seul choix !");
			alert.showAndWait();
                        
                    select=user.getStatut();
                
                
                }
    		else if(CheckBloc.isSelected())
    		{ select ="Bloquer";
               
                }
    		else
    		{ select ="valable";
                CheckBloc.setSelected(false);}
    	
    		user.setStatut(select);
    		proxy.update(user);
    		afficher();
                 labstatut.setText(select);
    	
    }

    @FXML
    private void OnUserDetail(Event event) throws NamingException {
    	
    	
    afficher();
                
                
                
                tableau.setRowFactory(new Callback<TableView<User>, TableRow<User>>() {
                @Override
                public TableRow<User> call(TableView<User> param) {
                    final TableRow<User> row = new TableRow<>();
                    final ContextMenu contextMenu = new ContextMenu();
                    final MenuItem removeMenuItem = new MenuItem("Afficher");
                    
                    removeMenuItem.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                           
                                 String p=tableau.getSelectionModel().getSelectedItem().getEmail();
 labmail.setText(p);
 
                                  String p1=tableau.getSelectionModel().getSelectedItem().getFirstname();
 labfirstname.setText(p1);
  
                            String p2=tableau.getSelectionModel().getSelectedItem().getLastname();
 lablastname.setText(p2);
              String p3=tableau.getSelectionModel().getSelectedItem().getStatut();
labstatut.setText(p3);
 String p4=tableau.getSelectionModel().getSelectedItem().getLogin();
lablogin.setText(p4);
            String p6=tableau.getSelectionModel().getSelectedItem().getNumtel();
labnum.setText(p6);


String p5=tableau.getSelectionModel().getSelectedItem().getPassword();
labpassword.setText(p5);
String p7=tableau.getSelectionModel().getSelectedItem().getRole();
labrole.setText(p7);

 login=tableau.getSelectionModel().getSelectedItem().getLogin();
                            }
                        
                    });
                    contextMenu.getItems().add(removeMenuItem);
                    
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu)
                    );
                    
                    return row;

                }
                
                
                
                
                
     
                
            
                    
                    
                    
                    
        
                    
            } );
                    
     
         
}

    @FXML
    private void OnChangeRoleAction(ActionEvent event) {
        
        
        BtnConfirme.setVisible(false);
    	chekVal.setVisible(false);
    	CheckBloc.setVisible(false);
        BtnConfirme1.setVisible(true);
    	chekGPAO.setVisible(true);
    	chekGmao.setVisible(true);
    }

    @FXML
    private void OnConfirmeRoleAction(ActionEvent event) throws NamingException {
    	
    	
    	
      	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
    		String select="";
    		 	User	user=proxy.findByLogin(login);
    	if ((chekGmao.isSelected())&&(chekGPAO.isSelected()))
    		{Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText(null);
			alert.setContentText("veuillez choisir un seul choix !");
			alert.showAndWait();
                        
                        select=user.getRole();
			}
    		else if(chekGmao.isSelected())
    		{ select ="GMAO";}
    		else
    		{ select ="GPAO";}
   
    		user.setRole(select);
    		proxy.update(user);
    		afficher();
                labrole.setText(select);
    	
    	
    }
    
    
    public void afficher() throws NamingException
    {
    	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
    		List<User> list2=new ArrayList<>();
    	    	
    		ColFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
    		ColLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
    		ColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
    		ColStatut.setCellValueFactory(new PropertyValueFactory<User, String>("statut"));
    	
    		
    	        List<User> list = proxy.findAll();
    	        ObservableList<User> items = FXCollections.observableArrayList(list);
    	        tableau.setItems(items);}
}
    

