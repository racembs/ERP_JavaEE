/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.google.common.base.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
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
    @FXML
    private TableColumn <User, String> ColLogin;
    @FXML
    private TableColumn <User, String>ColNum;
    @FXML
    private JFXButton btndeconnexion;
    @FXML
    private JFXButton btndeconnexion1;
    @FXML
    private TextField txtsearch;
    @FXML
    private JFXComboBox<String> Combocheck;
    @FXML
    private ImageView imageView;
    @FXML
    private JFXButton Browser;
    @FXML
    private Label ss;
    @FXML
    private Label fileSelected;
	  ValidationSupport v6= new ValidationSupport();
		ValidationSupport v5= new ValidationSupport();
		   ValidationSupport v1= new ValidationSupport();
			ValidationSupport v2= new ValidationSupport();
			ValidationSupport v3= new ValidationSupport();
			ValidationSupport v4= new ValidationSupport();
    private String imageFile;
    @FXML
    private JFXRadioButton technician;
    @FXML
    private ToggleGroup q1;
    @FXML
    private JFXRadioButton dev;
    @FXML
    private JFXButton edibtn;
    @FXML
    private Tab addduser;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	   technician.setVisible(false);
    	    dev.setVisible(false);
        // TODO
    	Combocheck.getItems().addAll("Mail","Statut","Name","Login");
    	Combocheck.getSelectionModel().selectLast();
    	BtnConfirme.setVisible(false);
    	chekVal.setVisible(false);
    	CheckBloc.setVisible(false);
    	
    	BtnConfirme1.setVisible(false);
    	chekGPAO.setVisible(false);
    	chekGmao.setVisible(false);
	

    
    		
    			
    		v1.registerValidator(txtfisrtname,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    		v2.registerValidator(txtlastname,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    		
    		v4.registerValidator(txtemail, Validator.createRegexValidator("", "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$", Severity.ERROR));
    		v5.registerValidator(txtphonenumber, Validator.createRegexValidator("must be digits only!", "^[0-9]{8,8}$", Severity.ERROR));

    		
    		v3.registerValidator(txtlogin, Validator.createEmptyValidator("Text is Required"));
    		v6.registerValidator(txtpassword, Validator.createRegexValidator("", "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*).{8,25})", Severity.ERROR));

    	
    		edibtn.disableProperty().bind(v5.invalidProperty());

    		edibtn.disableProperty().bind(v6.invalidProperty());

    		edibtn.disableProperty().bind(v2.invalidProperty());

    		edibtn.disableProperty().bind(v1.invalidProperty());

    		edibtn.disableProperty().bind(v3.invalidProperty());

    		edibtn.disableProperty().bind(v4.invalidProperty());
    		
    	
    		
    	
    	
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
    	String post=""; 
    if(	GPAO.isSelected())
    {role="GPAO";
    technician.setVisible(false);
    dev.setVisible(false);
    }else
    {role="GMAO";
    
    technician.setVisible(true);
    dev.setVisible(true);
    if(technician.isSelected())
    {
    	post="technician";
    }
    else
    { post="dev";
    }
    
   
    	
    }
 
    	User user=new User(firstname,lastname,login,password,mail,role,number,"valable","0",ss.getText(),post );

    	
    	proxy.save(user);
    	

    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information");
    	alert.setHeaderText("the User   : " +firstname+"/"+lastname+ "is add  with success");
    	alert.showAndWait();
    	
    	
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
                    final MenuItem MenuItem = new MenuItem("Afficher");
                    final MenuItem removeMenuItem = new MenuItem("Supprimer");
                    MenuItem.setOnAction(new EventHandler<ActionEvent>() {
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
                    ////////remove
                    removeMenuItem.setOnAction(new  EventHandler<ActionEvent>() {

						@Override
						public void handle(ActionEvent event) {
							
							
					    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
					    	Context context;

					    		
					    			try {
										context= new InitialContext();
								

					    		UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
					    		   
					    		
					    		

					              
					    		
					    	User user=	proxy.find(tableau.getSelectionModel().getSelectedItem().getId())
;
					    	user.setStatut("baned");
							proxy.update(user);
							
							afficher();
	                            
							
					    			} 
	                            catch (NamingException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							
							
						
							
						}
                    	
                   
					});
                    
                    
                    
  contextMenu.getItems().add(removeMenuItem);
                    
                    row.contextMenuProperty().bind(
                            Bindings.when(row.emptyProperty())
                                    .then((ContextMenu) null)
                                    .otherwise(contextMenu));
                    
                    
                    
                    contextMenu.getItems().add(MenuItem);
                    
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

            	Alert alert = new Alert(AlertType.INFORMATION);
            	alert.setTitle("Information");
            	alert.setHeaderText("the User Role  is updated  with success");
            	alert.showAndWait();    	
    	
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
    	ColLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        ColNum.setCellValueFactory(new PropertyValueFactory<User, String>("numtel"));
    		
    	        List<User> list = proxy.findAll();
    	        ObservableList<User> items = FXCollections.observableArrayList(list);
    	        tableau.setItems(items);}

   

    @FXML
    private void OnDeconnexionAction(Event event) throws IOException {

    	Parent parent= null;
    	parent  =FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
		Scene scene=new Scene(parent);
		Stage primaryStage= new Stage(); 
		primaryStage.setScene(scene);
		primaryStage.show();
		 btndeconnexion.getScene().getWindow().hide();
    }

    @FXML
    private void OnDeconnexion1Action(ActionEvent event) throws IOException {
    	
    	
    	Parent parent= null;
    	parent  =FXMLLoader.load(getClass().getResource("/views/Login.fxml"));
		Scene scene=new Scene(parent);
		Stage primaryStage= new Stage(); 
		primaryStage.setScene(scene);
		primaryStage.show();
		btndeconnexion1.getScene().getWindow().hide();
		
		
		
		
		
    }

    @FXML
    private void OnSearchAction(ActionEvent event) throws NamingException {
    	
    	
    	

    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		UserServiceRemote proxy=(UserServiceRemote) context.lookup(jndiName);
    		
    	    	
    		ColFirstName.setCellValueFactory(new PropertyValueFactory<User, String>("firstname"));
    		ColLastName.setCellValueFactory(new PropertyValueFactory<User, String>("lastname"));
    		ColEmail.setCellValueFactory(new PropertyValueFactory<User, String>("email"));
    		ColStatut.setCellValueFactory(new PropertyValueFactory<User, String>("statut"));
    	ColLogin.setCellValueFactory(new PropertyValueFactory<User, String>("login"));
        ColNum.setCellValueFactory(new PropertyValueFactory<User, String>("numtel"));
        
        
        List<User> list=new ArrayList<>();
      String val=  Combocheck.getValue();
    	String login=	txtsearch.getText();
    	if (txtsearch.getText().equals(""))
    	{
    		List<User> list1=proxy.findAll();
    		
    		 ObservableList<User> items = FXCollections.observableArrayList(list1);
    		 tableau.setItems(items);
 	       
    	}
    	else if (val.equals("Login")) 
    	{
    	User user1 = proxy.findByLogin(login);
      
	        list.add(user1);
	        ObservableList<User> items = FXCollections.observableArrayList(list);
	        tableau.setItems(items);
	        }
    	else if (val.equals("Mail")) 
    	{
    		List<User> list1=proxy.SearchMail(login);
    		
   		 ObservableList<User> items = FXCollections.observableArrayList(list1);
   		 tableau.setItems(items);
	        }
    	else if (val.equals("Name")) 
	    	{
    		List<User> list12=proxy.SearchFirstName(login);
    		
   		 ObservableList<User> items = FXCollections.observableArrayList(list12);
   		 tableau.setItems(items);
	    	        }
    	
    	else if (val.equals("Statut")) 
	    	    	{
    		List<User> list12=proxy.SearchStatut(login);
    		
   		 ObservableList<User> items = FXCollections.observableArrayList(list12);
   		 tableau.setItems(items);
	    	    	        }
    	//     List<User> list=proxy.SearchLogin(login);
    	        
    	    
    	
    	
    }

    @FXML
    private void searchreleased(KeyEvent event) {
    	
    	
    }

    @FXML
    private void OnBrowserAction(ActionEvent event) throws MalformedURLException {
    	
    	String path_img;
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
        	path_img = selectedFile.getName();
			ss.setText(path_img);
            Image image = new Image(imageFile);
            imageView.setImage(image);
        } else {
            fileSelected.setText("Image file selection cancelled.");
        }
    	
    	
    }

    @FXML
    private void GPAO(ActionEvent event) {
    	 technician.setVisible(false);
 	    dev.setVisible(false);
    }

    @FXML
    private void GMAO(ActionEvent event) {
    	 technician.setVisible(true);
 	    dev.setVisible(true);
    	
    }
    
    
   
}
    

