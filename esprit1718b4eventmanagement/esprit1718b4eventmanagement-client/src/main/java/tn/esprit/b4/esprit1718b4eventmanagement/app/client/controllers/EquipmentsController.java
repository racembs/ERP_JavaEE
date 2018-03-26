/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;


/**
 * FXML Controller class
 *
 * @author Asus
 */
public class EquipmentsController implements Initializable {


    @FXML
    private AnchorPane arbo;
    @FXML
    private JFXTextField serialnum;
    @FXML
    private JFXTextField marque;
    @FXML
    private JFXTextField fabriquant;
    @FXML
    private JFXButton btncancel;
    @FXML
    private JFXButton btnadd;
    @FXML
    private JFXTextField State;
    @FXML
    private JFXTextArea descrsption;
    @FXML
    private AnchorPane arbo1;
    @FXML
    private JFXTextField name;
    @FXML
    private JFXButton btncancel1;
    @FXML
    private JFXButton btnadd1;
    @FXML
    private JFXComboBox<String> combarbotype;
    @FXML
    private Tab btndetail;
    @FXML
    private TableView<Equipment> tableau;
    @FXML
    private TableColumn<Equipment,String> colSerialNum;
    @FXML
    private TableColumn<Equipment,String> colFabriquant;
    @FXML
    private TableColumn<Equipment,String>  colMarque;
    @FXML
    private TableColumn<Equipment,String>  colState;
    @FXML
    private TableColumn<Equipment,String>  colDescription;
 
    @FXML
    private JFXComboBox<String> ComboEqui;
    @FXML
    private JFXComboBox<String> ComboEqui1;
    @FXML
    private JFXComboBox<String> ComboEqui2;
    @FXML
    private JFXComboBox<String> ComboEqui3;
    @FXML
    private JFXComboBox<String> ComboEqui4;
    @FXML
    private JFXTextArea arboarea;
    @FXML
    private Label arbofinal;
    @FXML
    private JFXTextArea arbo2;
    @FXML
    private JFXComboBox<String> ComboEquii;
    @FXML
    private JFXComboBox<String> ComboEqui11;
    @FXML
    private JFXComboBox<String> ComboEqui21;
    @FXML
    private JFXComboBox<String> ComboEqui31;
    @FXML
    private JFXComboBox<String> ComboEqui41;
    @FXML
    private Label adarb;
    @FXML
    private TextField searchtraining;
 
    @FXML
    private Label txtfabr;
    @FXML
    private Label txtserial;
    @FXML
    private Label txtdate;
    @FXML
    private Label txtmarque;
    @FXML
    private Label txtetat;
 
    @FXML
    private Label txtdesc1;
    @FXML
    private Label txtarbo12;
   
		
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {


	
  		
       
    	 ComboEqui11.setVisible(false);
    	 
  	   ComboEqui21.setVisible(false);
  	 

  	   ComboEqui31.setVisible(false);
  
  	   ComboEqui41.setVisible(false);
    	   ComboEqui1.setVisible(false);
    	 
    	   ComboEqui2.setVisible(false);
    	 

    	   ComboEqui3.setVisible(false);
    
    	   ComboEqui4.setVisible(false);
    	  // arbofinal.setVisible(false);
    	
    	
        // TODO
    	combarbotype.getItems().addAll("Principale","Secondaire");
    	combarbotype.getSelectionModel().selectLast();
    	
    	

     	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
     	Context context1;
		try {
			context1 = new InitialContext();

	     	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);

     	
  		 	List<Arboresence> list1 = Proxy.getPereArbo("Principale") ;
	          for (Arboresence u: list1)
	         {
	        	   
	        	  ComboEqui.getItems().addAll(u.getName());
	          	ComboEqui.getSelectionModel().selectLast();
	            
	        	  ComboEquii.getItems().addAll(u.getName());
	          	ComboEquii.getSelectionModel().selectLast();
	        	  
	        	  
	      }
	          arboarea.setText(ComboEqui.getValue());
  
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }    

    @FXML
    private void OnCancelClick(ActionEvent event) {
    	
    	 serialnum.setText("");
    		
    	fabriquant.setText("");
    		
    	    marque.setText("");
    		
    	descrsption.setText("");
    		
    	   State.setText("");
    	
    	
    }

    @FXML
    private void OnAddClick(ActionEvent event) throws NamingException {
    	

		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
		Context context = new InitialContext();

	

	EquipementServiceRemote Proxy = (EquipementServiceRemote) context.lookup(jndiName);
	
	

	//System.out.println(	Proxy1.findArboresence(4).getId());
		
		 
    String serie =serialnum.getText();
	
    String fab =fabriquant.getText();
	
    String marq =marque.getText();
	
    String desc =descrsption.getText();
	
    String stat =State.getText();
	
    String lieu=arboarea.getText();

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Calendar date = Calendar.getInstance();
      String dateF = df.format(date.getTime());
        String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
         Context context1 = new InitialContext();

ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);

Arboresence arbo1=Proxy1.getArbo(arbofinal.getText());

    Equipment eq=new Equipment(serie ,desc,stat,dateF,fab,marq,lieu,arbo1);
    
    
    Proxy.addEquippement(eq);
    }
    
    

    @FXML
    private void OnCancelArboClick(ActionEvent event) {
    }

    @FXML
    private void OnAddArboClick(ActionEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();
    	
    	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
    	String namee=name.getText();
    	String type=combarbotype.getValue();
    	Arboresence arbo=new Arboresence(namee,type);
    	Proxy.addArbo(arbo);
    	
    
    	Proxy.addArbo(Proxy.getArbo(  adarb.getText()).getId(),	Proxy.getArbo(namee).getId());
    	
   
    	
    	
    	
    	
    }

    @FXML
    private void OnDetailAction(Event event) throws NamingException {
    	
    	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		EquipementServiceRemote proxy=(EquipementServiceRemote) context.lookup(jndiName);
    		List<Equipment> list2=new ArrayList<>();
    	    	
    		colSerialNum.setCellValueFactory(new PropertyValueFactory<Equipment, String>("SerialNum"));
    		colFabriquant.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Fabriquant"));
    		colMarque.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Marque"));
    		colState.setCellValueFactory(new PropertyValueFactory<Equipment, String>("State"));
    		 colDescription.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Description"));
        
    		
        List<Equipment> list = proxy.getAllEquipment();
    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    	        tableau.setItems(items);
    	        
    	        tableau.setRowFactory(new Callback<TableView<Equipment>, TableRow<Equipment>>() {
                    @Override
                    public TableRow<Equipment> call(TableView<Equipment> param) {
                        final TableRow<Equipment> row = new TableRow<>();
                        final ContextMenu contextMenu = new ContextMenu();
                        final MenuItem MenuItem = new MenuItem("Afficher");
                        final MenuItem removeMenuItem = new MenuItem("Supprimer");
                        MenuItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            
                          
                                 
                                 String serie=tableau.getSelectionModel().getSelectedItem().getSerialNum();
                                txtserial.setText(serie);
                                 String date=tableau.getSelectionModel().getSelectedItem().getEISDate();
                                 txtdate.setText(date);
                                 String fabriquant=tableau.getSelectionModel().getSelectedItem().getFabriquant();
                                 txtfabr.setText(fabriquant);
                                 String lieu=tableau.getSelectionModel().getSelectedItem().getLieu();
                                 String desc =tableau.getSelectionModel().getSelectedItem().getDescription();
                                 txtdesc1.setText(desc);
                                 String marque=tableau.getSelectionModel().getSelectedItem().getMarque();
                                 txtmarque.setText(marque);
                                 String state=tableau.getSelectionModel().getSelectedItem().getState();
                                 txtetat.setText(state);
                                 Integer idarbo=tableau.getSelectionModel().getSelectedItem().getArboresence().getId();
                                 
                                
                   txtarbo12.setText(lieu);
               
                              	
                            	
                            }
                            
                        });
                        ////////remove
                        removeMenuItem.setOnAction(new  EventHandler<ActionEvent>() {

    						@Override
    						public void handle(ActionEvent event) {
    							
    							   
    							
    					    	
    						
    							
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
    private void ComboEquiAction(ActionEvent event) throws NamingException {
    	

		

	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
	Context context1 = new InitialContext();

	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
   Arboresence arbo= Proxy1.getArbo(ComboEqui.getValue());
   arbofinal.setText(ComboEqui.getValue());
   arboarea.setText(ComboEqui.getValue());
   ComboEqui1.getItems().clear();
   ComboEqui1.setValue(null);
   ComboEqui2.getItems().clear();
   ComboEqui2.setValue(null);

   ComboEqui3.getItems().clear();
   ComboEqui3.setValue(null);
   ComboEqui4.getItems().clear();
   ComboEqui4.setValue(null);
   
 	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
    for (ArboPereFis u: list)
   {
  	   
  	  ComboEqui1.getItems().addAll(u.getArboFils().getName());
    	ComboEqui1.getSelectionModel().selectLast();
  	  
  	  
  	  
}
 
    ComboEqui1.setVisible(true);
    ComboEqui2.setVisible(false);
    ComboEqui3.setVisible(false);
    ComboEqui4.setVisible(false);
    	
    }

    @FXML
    private void Combo2Action(MouseEvent event) throws NamingException {

		

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui1.getValue());
       arboarea.setText(ComboEqui.getValue()+"/"+ComboEqui1.getValue());
       arbofinal.setText(ComboEqui1.getValue());
       ComboEqui2.getItems().clear();
       ComboEqui2.setValue(null);

       ComboEqui3.getItems().clear();
       ComboEqui3.setValue(null);
       ComboEqui4.getItems().clear();
       ComboEqui4.setValue(null);
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui2.getItems().addAll(u.getArboFils().getName());
        	ComboEqui2.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
    	
        ComboEqui2.setVisible(true);
        ComboEqui3.setVisible(false);
        ComboEqui4.setVisible(false);
    	
    	
    }

    @FXML
    private void Combo3Action(MouseEvent event) throws NamingException {
    	
    	
    	
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui2.getValue());
       arboarea.setText(ComboEqui.getValue()+"/"+ComboEqui1.getValue()+"/"+ComboEqui2.getValue());
       arbofinal.setText(ComboEqui2.getValue());
       ComboEqui3.getItems().clear();
       ComboEqui3.setValue(null);
       ComboEqui4.getItems().clear();
       ComboEqui4.setValue(null);
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui3.getItems().addAll(u.getArboFils().getName());
        	ComboEqui3.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
        ComboEqui3.setVisible(true);
        ComboEqui4.setVisible(false);
    }

    @FXML
    private void ComboEqui3Acyion(MouseEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui3.getValue());
       arboarea.setText(ComboEqui.getValue()+"/"+ComboEqui1.getValue()+"/"+ComboEqui2.getValue()+"/"+ComboEqui3.getValue());
       arbofinal.setText(ComboEqui3.getValue());
       ComboEqui4.getItems().clear();
       ComboEqui4.setValue(null);
    	
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
     	
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui4.getItems().addAll(u.getArboFils().getName());
        	ComboEqui4.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
        ComboEqui4.setVisible(true);
    	
    }

    @FXML
    private void ComboEquiiAction(ActionEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEquii.getValue());
       arbo2.setText(ComboEquii.getValue());
       adarb.setText(ComboEquii.getValue());
       ComboEqui11.getItems().clear();
       ComboEqui11.setValue(null);
       ComboEqui21.getItems().clear();
       ComboEqui21.setValue(null);

       ComboEqui31.getItems().clear();
       ComboEqui31.setValue(null);
       ComboEqui41.getItems().clear();
       ComboEqui41.setValue(null);
       
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui11.getItems().addAll(u.getArboFils().getName());
        	ComboEqui11.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
     
        ComboEqui11.setVisible(true);
        ComboEqui21.setVisible(false);
        ComboEqui31.setVisible(false);
        ComboEqui41.setVisible(false);
        	
    	
    }

    @FXML
    private void Combo11Action(MouseEvent event) throws NamingException {
    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui11.getValue());
       arbo2.setText(ComboEquii.getValue()+"/"+ComboEqui11.getValue());
       adarb.setText(ComboEqui11.getValue());
       ComboEqui21.getItems().clear();
       ComboEqui21.setValue(null);

       ComboEqui31.getItems().clear();
       ComboEqui31.setValue(null);
       ComboEqui41.getItems().clear();
       ComboEqui41.setValue(null);
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui21.getItems().addAll(u.getArboFils().getName());
        	ComboEqui21.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
    	
        ComboEqui21.setVisible(true);
        ComboEqui31.setVisible(false);
        ComboEqui41.setVisible(false);
    	
    	
    	
    }

    @FXML
    private void Combo21Action(MouseEvent event) throws NamingException {
    	


    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui21.getValue());
       arbo2.setText(ComboEquii.getValue()+"/"+ComboEqui11.getValue()+"/"+ComboEqui21.getValue());
       adarb.setText(ComboEqui21.getValue());
       ComboEqui31.getItems().clear();
       ComboEqui31.setValue(null);
       ComboEqui41.getItems().clear();
       ComboEqui41.setValue(null);
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui31.getItems().addAll(u.getArboFils().getName());
        	ComboEqui31.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
        ComboEqui31.setVisible(true);
        ComboEqui41.setVisible(false);
    }

    @FXML
    private void ComboEqui31Acyion(MouseEvent event) throws NamingException {
    	
    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEqui21.getValue());
       arbo2.setText(ComboEquii.getValue()+"/"+ComboEqui11.getValue()+"/"+ComboEqui21.getValue()+"/"+ComboEqui31.getValue());
      adarb.setText(ComboEqui31.getValue());
       ComboEqui31.getItems().clear();
       ComboEqui31.setValue(null);
       ComboEqui41.getItems().clear();
       ComboEqui41.setValue(null);
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEqui31.getItems().addAll(u.getArboFils().getName());
        	ComboEqui31.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
        ComboEqui31.setVisible(true);
        ComboEqui41.setVisible(false);
    }

    @FXML
    private void combarbotypeAction(MouseEvent event) {
        
        
        
        
        
    }

    @FXML
    private void OnSerarchReleased(KeyEvent event) throws NamingException {
    	
    	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		EquipementServiceRemote proxy=(EquipementServiceRemote) context.lookup(jndiName);
    		List<Equipment> list2=new ArrayList<>();
    	    	
    		colSerialNum.setCellValueFactory(new PropertyValueFactory<Equipment, String>("SerialNum"));
    		colFabriquant.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Fabriquant"));
    		colMarque.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Marque"));
    		colState.setCellValueFactory(new PropertyValueFactory<Equipment, String>("State"));
    		 colDescription.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Description"));
        
    		String input =searchtraining.getText();
        List<Equipment> list = proxy.searchEquipment(input);
    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    	        tableau.setItems(items);
    	
    }


    

 
}
