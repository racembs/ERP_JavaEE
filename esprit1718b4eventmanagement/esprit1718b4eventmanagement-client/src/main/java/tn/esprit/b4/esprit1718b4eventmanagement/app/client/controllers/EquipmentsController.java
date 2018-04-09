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
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.validation.Severity;
import org.controlsfx.validation.ValidationSupport;
import org.controlsfx.validation.Validator;

import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import sun.invoke.empty.Empty;

import org.controlsfx.control.CheckTreeView;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
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
    private JFXComboBox<String> combarbotype;
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
    private Label arbofinal;
  
   
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
    private AnchorPane arbo3;
    @FXML
    private JFXTextField serialnumModif;
    @FXML
    private JFXTextField marqueModif;
    @FXML
    private JFXTextField fabriquantModif;
    @FXML
    private JFXButton btncancel2;
    @FXML
    private JFXButton btnUpdate2;
    @FXML
    private JFXTextField StateModif;
    @FXML
    private JFXTextArea descrsptionModif;
    @FXML
    private JFXTextArea arboareaModif;
    @FXML
    private JFXComboBox<String> ComboEquiModif;
    @FXML
    private JFXComboBox<String> ComboEquiModif1;
    @FXML
    private JFXComboBox<String> ComboEquiModif2;
    @FXML
    private JFXComboBox<String> ComboEquiModif3;
    @FXML
    private JFXComboBox<String> ComboEquiModif4;
    @FXML
    private Tab btndetail1;
    @FXML
    private JFXButton BtnNewArbo;
    @FXML
    private TableView<Equipment> tableau1;
    @FXML
    private TableColumn<Equipment,String> colSerialNum1;
    @FXML
    private TableColumn<Equipment,String> colFabriquant1;
    @FXML
    private TableColumn<Equipment,String> colMarque1;
    @FXML
    private TableColumn<Equipment,String> colState1;
    @FXML
    private TableColumn<Equipment,String> colDescription1;
    @FXML
    private TextField searchtraining1;
    @FXML
    private JFXTextField LieuModif;
 
    ValidationSupport v7= new ValidationSupport();
    ValidationSupport v6= new ValidationSupport();
  		ValidationSupport v5= new ValidationSupport();
  		   ValidationSupport v1= new ValidationSupport();
  			ValidationSupport v2= new ValidationSupport();
  			ValidationSupport v3= new ValidationSupport();
  			ValidationSupport v4= new ValidationSupport();
  			ValidationSupport v11= new ValidationSupport();
  			
  		   ValidationSupport v17= new ValidationSupport();
  		    ValidationSupport v16= new ValidationSupport();
  		  		ValidationSupport v15= new ValidationSupport();
  		  		   ValidationSupport v14= new ValidationSupport();
  		  			ValidationSupport v13= new ValidationSupport();
  		  			ValidationSupport v18= new ValidationSupport();
  		  			ValidationSupport v19= new ValidationSupport();
  		  			ValidationSupport v12= new ValidationSupport();
  			
  			
  			
  			
    @FXML
    private CheckTreeView<String> treeviewarbo1;
 
    @FXML
    private JFXButton btnmodifarbo;
    @FXML
    private Label labelarbotype;
    @FXML
    private JFXButton btnadd1;
    @FXML
    private JFXButton btnadd11;
    @FXML
    private JFXButton btnupdatee;
    @FXML
    private JFXButton btndelete1;
    @FXML
    private CheckTreeView<String> treeviewarbo11;
    
    @FXML
    private ImageView idCal;
    @FXML
    private Label nbeqpanne;
    @FXML
    private ImageView idCal1;
    @FXML
    private Label nbbonne;
    @FXML
    private ImageView idCal11;
    @FXML
    private Label totalequipment;
		
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	try {
			refrech();
	
			refrech1();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	arbofinal.setVisible(false);
    	//////Modif Equipment Combo
ComboEquiModif1.setVisible(false);
ComboEquiModif2.setVisible(false);
ComboEquiModif3.setVisible(false);
ComboEquiModif4.setVisible(false);
ComboEquiModif.setVisible(false);

  		/////combo ;
btnadd11.setVisible(false);
btnmodifarbo.setVisible(false);
  	   ////combo de l interface equipment add 
    	
    	  // arbofinal.setVisible(false);
    	
    	
        // TODO
    	combarbotype.getItems().addAll("Principale","Secondaire");
    	combarbotype.getSelectionModel().selectLast();
    	
    	

     	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
     	Context context1;
		try {
			context1 = new InitialContext();

	     	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);

     	
	     
  
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
	
    String lieu=arbofinal.getText();

    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
      Calendar date = Calendar.getInstance();
      String dateF = df.format(date.getTime());
        String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
         Context context1 = new InitialContext();

ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);

Arboresence arbo1=Proxy1.getArbo(arbofinal.getText());

    Equipment eq=new Equipment(serie ,desc,stat,dateF,fab,marq,lieu,arbo1);
    

    Proxy.addEquippement(eq);
	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText("the equipment : " +serialnum.getText()+ "is add in :"+lieu+" with success");
	alert.showAndWait();
	  
    	
	
    }
    
    

    @FXML
    private void OnCancelArboClick(ActionEvent event) {
    	
    	btnupdatee.setVisible(true);
    	btnadd11.setVisible(false);
    	btndelete1.setVisible(true);
    	btnmodifarbo.setVisible(false);
    	btnadd1.setVisible(true);
   
    	combarbotype.setVisible(true);
    	labelarbotype.setVisible(true);
    	name.setVisible(true);
    
    }

    @FXML
    private void OnAddArboClick(ActionEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();
    	
    	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
    	String namee=name.getText();
    	String type=combarbotype.getValue();
    	Arboresence arbo=new Arboresence(namee,type);
   
    	
    	
    	if(name.getText().equals(""))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Erreur ");
	    	alert.setHeaderText(" please complete all fields .!!!!");
	    	alert.showAndWait();
	    	
    	}
    	else
    	{
    		if(!Proxy.verifAllArboresence(namee).isEmpty())
    		{
    			
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Erreur name exist");
    	    	alert.setHeaderText("the name exist .!!!!");
    	    	alert.showAndWait();
    	    	
    		}else
    		{
    
    	if(type.equals("Principale"))
    	{	Proxy.addArbo(arbo);
    	
    	
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information");
    	alert.setHeaderText("the arbo    : "+name.getText()+ "is add  with success");
    	alert.showAndWait();
    	refrech();
    	}else{
    		Proxy.addArbo(arbo);
    	
    	Proxy.addArbo(Proxy.getArbo(  adarb.getText()).getId(),	Proxy.getArbo(namee).getId());
    	Alert alert = new Alert(AlertType.INFORMATION);
    	alert.setTitle("Information");
    	alert.setHeaderText("the arbo    : " +arbofinal.getText()+"/"+name.getText()+ "is add  with success");
    	alert.showAndWait();
    	refrech();
    		}
    		
    	}}
    
    	
    	  
    	
    	
    	
    	
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
    		 tableau.setTableMenuButtonVisible(true);
    		 
    		 DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    		    Calendar date = Calendar.getInstance();
    		    String dateF = df.format(date.getTime());
    		    Long x=proxy.Date(dateF, dateF);
    	nbeqpanne.setText( Long.toString(x));
    	Long y=proxy.countequi();
    	
    	nbbonne.setText( Long.toString(y-x));
        totalequipment.setText( Long.toString(y));
    		//////////////////////////////
    		
    		
    		 //////////////////////////
    		 
    		 
    		 
    		 
    		 
    		 
    		 
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
                                 
                                
                
               
                              	
                            	
                            }
                            
                        });
                        ////////remove
                        removeMenuItem.setOnAction(new  EventHandler<ActionEvent>() {

    						@Override
    						public void handle(ActionEvent event) {
    							
    							   
    							Integer id  =tableau.getSelectionModel().getSelectedItem().getId();
    							Alert alert = new Alert(AlertType.CONFIRMATION);
    					    	alert.setTitle("WARNING");
    							alert.setHeaderText("Are You Sure to remove"+proxy.findEqupment(id).getSerialNum()+"?");
    					    	
    					    	if (alert.showAndWait().get () == ButtonType.OK)
    					    	{    
                                	
    					    	
    						proxy.DeleteEqupment(id);
    					    	}
    						
    						
    						
    				    	
    			    		colSerialNum.setCellValueFactory(new PropertyValueFactory<Equipment, String>("SerialNum"));
    			    		colFabriquant.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Fabriquant"));
    			    		colMarque.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Marque"));
    			    		colState.setCellValueFactory(new PropertyValueFactory<Equipment, String>("State"));
    			    		 colDescription.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Description"));
    			        
    			    		
    			        List<Equipment> list = proxy.getAllEquipment();
    			    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    			    	        tableau.setItems(items);
    						
    						
    							
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

    @FXML
    private void combarbotypeAction(ActionEvent event) {
   
    	
    	
    }
    
    
    
    
    ////////////////////////////////update equipment ////////////////////////////////////////

    @FXML
    private void OnCancelModifClick(ActionEvent event) {
    	
        String serie=tableau1.getSelectionModel().getSelectedItem().getSerialNum();
        serialnumModif.setText(serie);
         String date=tableau1.getSelectionModel().getSelectedItem().getEISDate();
         txtdate.setText(date);
         String fabriquant=tableau1.getSelectionModel().getSelectedItem().getFabriquant();
         fabriquantModif.setText(fabriquant);
         String lieu=tableau1.getSelectionModel().getSelectedItem().getLieu();
         String desc =tableau1.getSelectionModel().getSelectedItem().getDescription();
         descrsptionModif.setText(desc);
         String marque=tableau1.getSelectionModel().getSelectedItem().getMarque();
         marqueModif.setText(marque);
         String state=tableau1.getSelectionModel().getSelectedItem().getState();
         StateModif.setText(state);
         Integer idarbo=tableau1.getSelectionModel().getSelectedItem().getArboresence().getId();
         
        
    }

    @FXML
    private void OnUpdateClick(ActionEvent event) throws NamingException {
    	
    	

    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		EquipementServiceRemote proxy=(EquipementServiceRemote) context.lookup(jndiName);
    		
    		Integer id=tableau1.getSelectionModel().getSelectedItem().getId();
    		Equipment equi=proxy.findEqupment(id);
    	equi.setDescription(descrsptionModif.getText());
    	equi.setFabriquant(fabriquantModif.getText());
    	equi.setMarque(marqueModif.getText());
    	equi.setLieu(arboareaModif.getText());
    	equi.setSerialNum(serialnumModif.getText());
    	equi.setState(StateModif.getText());
    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
     	Context context1;
		
			context1 = new InitialContext();

	     	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
    	
    equi.setArboresence(Proxy1.getArbo(LieuModif.getText()));
    
    
	Alert alert = new Alert(AlertType.CONFIRMATION);
	alert.setTitle("WARNING");
	alert.setHeaderText("Are You Sure to Update   : "+proxy.findEqupment(id).getSerialNum()+"?");
	
	if (alert.showAndWait().get () == ButtonType.OK)
	{    
    	
	
    	proxy.updateEquipment(equi);
    	
	}

    		
    		List<Equipment> list2=new ArrayList<>();
    	    	
    		colSerialNum1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("SerialNum"));
    		colFabriquant1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Fabriquant"));
    		colMarque1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Marque"));
    		colState1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("State"));
    		 colDescription1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Description"));
        
    		
        List<Equipment> list = proxy.getAllEquipment();
    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    	        tableau1.setItems(items);
    	
    	
    	
    	
    	
    	
    	
    }

    @FXML
    private void ComboEquimodifAction(ActionEvent event) throws NamingException {
    	
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEquiModif.getValue());
       LieuModif.setText(ComboEquiModif.getValue());
       arboareaModif.setText(ComboEquiModif.getValue());
       ComboEquiModif1.getItems().clear();
       ComboEquiModif1.setValue(null);
       ComboEquiModif2.getItems().clear();
       ComboEquiModif2.setValue(null);

       ComboEquiModif3.getItems().clear();
       ComboEquiModif3.setValue(null);
       ComboEquiModif4.getItems().clear();
       ComboEquiModif4.setValue(null);
       
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEquiModif1.getItems().addAll(u.getArboFils().getName());
        	ComboEquiModif1.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
     
        ComboEquiModif1.setVisible(true);
        ComboEquiModif2.setVisible(false);
        ComboEquiModif3.setVisible(false);
        ComboEquiModif4.setVisible(false);
    	

    }



    @FXML
    private void OnModifEquipmentActionPage(Event event) throws NamingException {
    	
    	
    	   
v12.registerValidator(fabriquantModif,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	
    	v14.registerValidator(marqueModif, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	v15.registerValidator(descrsptionModif,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	
    
    	v13.registerValidator(StateModif, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	v16.registerValidator(serialnumModif, Validator.createRegexValidator("", "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*).{8,25})", Severity.ERROR));
    	btnUpdate2.disableProperty().bind(v12.invalidProperty());
    	btnUpdate2.disableProperty().bind(v13.invalidProperty());
    	btnUpdate2.disableProperty().bind(v14.invalidProperty());
    	btnUpdate2.disableProperty().bind(v16.invalidProperty());
    	btnUpdate2.disableProperty().bind(v15.invalidProperty());
    	
    	
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
    	Context context;

    		
    			context= new InitialContext();

    		EquipementServiceRemote proxy=(EquipementServiceRemote) context.lookup(jndiName);
    		List<Equipment> list2=new ArrayList<>();
    	    	
    		colSerialNum1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("SerialNum"));
    		colFabriquant1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Fabriquant"));
    		colMarque1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Marque"));
    		colState1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("State"));
    		 colDescription1.setCellValueFactory(new PropertyValueFactory<Equipment, String>("Description"));
        
    		
        List<Equipment> list = proxy.getAllEquipment();
    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    	        tableau1.setItems(items);
    	        
    	        tableau1.setRowFactory(new Callback<TableView<Equipment>, TableRow<Equipment>>() {
                    @Override
                    public TableRow<Equipment> call(TableView<Equipment> param) {
                        final TableRow<Equipment> row = new TableRow<>();
                        final ContextMenu contextMenu = new ContextMenu();
                        final MenuItem MenuItem = new MenuItem("Update");
                      
                        MenuItem.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            	 v2.registerValidator(descrsptionModif,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
                             	
                             	v4.registerValidator(marqueModif, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
                             	v5.registerValidator(fabriquantModif,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));

                             	
                             	v3.registerValidator(StateModif, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
                             	v6.registerValidator(serialnumModif, Validator.createRegexValidator("", "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*).{8,25})", Severity.ERROR));

                             	LieuModif.setDisable(true);
                          
                                 
                                 String serie=tableau1.getSelectionModel().getSelectedItem().getSerialNum();
                                serialnumModif.setText(serie);
                                 String date=tableau1.getSelectionModel().getSelectedItem().getEISDate();
                                 txtdate.setText(date);
                                 String fabriquant=tableau1.getSelectionModel().getSelectedItem().getFabriquant();
                                 fabriquantModif.setText(fabriquant);
                                 String lieu=tableau1.getSelectionModel().getSelectedItem().getLieu();
                                 String desc =tableau1.getSelectionModel().getSelectedItem().getDescription();
                                 descrsptionModif.setText(desc);
                                 String marque=tableau1.getSelectionModel().getSelectedItem().getMarque();
                                 marqueModif.setText(marque);
                                 String state=tableau1.getSelectionModel().getSelectedItem().getState();
                                 StateModif.setText(state);
                                 Integer idarbo=tableau1.getSelectionModel().getSelectedItem().getArboresence().getId();
                              
                                 arboareaModif.setText(lieu);
               
                                 arboareaModif.setDisable(true);
                            }
                            
                        });
                        ////////remove
                     
               
                        
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
    private void OnNewArboAction(ActionEvent event) {
    	
    	ComboEquiModif.setVisible(true);
    	
     	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
     	Context context1;
		try {
			context1 = new InitialContext();

	     	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);

     	
  		 	List<Arboresence> list1 = Proxy.getPereArbo("Principale") ;
	          for (Arboresence u: list1)
	         {
	        	   
	        	  ComboEquiModif.getItems().addAll(u.getName());
	        	  ComboEquiModif.getSelectionModel().selectLast();
	            
	        	
	        	  
	        	  
	      }
	          arboareaModif.setText(ComboEquiModif.getValue());
	          LieuModif.setText(ComboEquiModif.getValue());
  
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }

    @FXML
    private void ComboEquiModif1Action(MouseEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEquiModif1.getValue());
      LieuModif.setText(ComboEquiModif1.getValue());
       arboareaModif.setText(ComboEquiModif.getValue()+"/"+ComboEquiModif1.getValue());
        
       ComboEquiModif2.getItems().clear();
       ComboEquiModif2.setValue(null);

       ComboEquiModif3.getItems().clear();
       ComboEquiModif3.setValue(null);
       ComboEquiModif4.getItems().clear();
       ComboEquiModif4.setValue(null);
       
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEquiModif2.getItems().addAll(u.getArboFils().getName());
        	ComboEquiModif2.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
     
        ComboEquiModif2.setVisible(true);
        ComboEquiModif3.setVisible(false);
        ComboEquiModif4.setVisible(false);
    }

    @FXML
    private void ComboEquiModif2Action(MouseEvent event) throws NamingException {

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEquiModif2.getValue());
       LieuModif.setText(ComboEquiModif2.getValue());
       arboareaModif.setText(ComboEquiModif.getValue()+"/"+ComboEquiModif1.getValue()+"/"+ComboEquiModif2.getValue());
       
      
       ComboEquiModif3.getItems().clear();
       ComboEquiModif3.setValue(null);
       ComboEquiModif4.getItems().clear();
       ComboEquiModif4.setValue(null);
       
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEquiModif3.getItems().addAll(u.getArboFils().getName());
        	ComboEquiModif3.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
     
  
        ComboEquiModif3.setVisible(true);
        ComboEquiModif4.setVisible(false);
    	
    	
    }

    @FXML
    private void ComboEquiModif3Action(MouseEvent event) throws NamingException {
    	

    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();

    	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
       Arboresence arbo= Proxy1.getArbo(ComboEquiModif3.getValue());
       LieuModif.setText(ComboEquiModif3.getValue());
      
       arboareaModif.setText(ComboEquiModif.getValue()+"/"+ComboEquiModif1.getValue()+"/"+ComboEquiModif2.getValue()+"/"+ComboEquiModif3.getValue());
      
       
       ComboEquiModif4.getItems().clear();
       ComboEquiModif4.setValue(null);
       
     	List<ArboPereFis> list= Proxy1.getFilsArbo(arbo.getId());
        for (ArboPereFis u: list)
       {
      	   
      	  ComboEquiModif4.getItems().addAll(u.getArboFils().getName());
        	ComboEquiModif4.getSelectionModel().selectLast();
      	  
      	  
      	  
    }
     
  
       
        ComboEquiModif4.setVisible(true);
    	
    	
    	
    	
    }

    @FXML
    private void OnSerarchModifReleased(KeyEvent event) throws NamingException {
    	
    	
    	
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
        
    		String input =searchtraining1.getText();
        List<Equipment> list = proxy.searchEquipment(input);
    	        ObservableList<Equipment> items = FXCollections.observableArrayList(list);
    	        tableau1.setItems(items);
    }



   
    @FXML
    private void onUpdateArboSaveAction(ActionEvent event) throws NamingException {
    
    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();
    	
    	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
    	 String Select=adarb.getText();
    	String namee=name.getText();
    	
    	
    	if(name.getText().equals(""))
    	{
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Erreur ");
	    	alert.setHeaderText(" please complete all fields .!!!!");
	    	alert.showAndWait();
	    	
    	}
    	else
    	{
    		if(!Proxy.verifAllArboresence(namee).isEmpty())
    		{
    			
    			Alert alert = new Alert(AlertType.ERROR);
    	    	alert.setTitle("Erreur name exist");
    	    	alert.setHeaderText("the name exist .!!!!");
    	    	alert.showAndWait();
    	    	
    		}else
    		{
    
    	
    Arboresence arbo =Proxy.getArbo(Select);
    arbo.setName(namee);
    Proxy.updateArbo(arbo);
   	Alert alert = new Alert(AlertType.INFORMATION);
	alert.setTitle("Information");
	alert.setHeaderText("the arbo    : " +name.getText()+ "is update  with success");
	alert.showAndWait();
	refrech();



    		}}
    }

    
    
    @FXML
    private void ControledesaiseAddEqui(Event event) {
v2.registerValidator(fabriquant,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	
    	v4.registerValidator(marque, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	v5.registerValidator(descrsption,Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	
    	v11.registerValidator(arbofinal, Validator.createEmptyValidator("Text is Required"));
    	v3.registerValidator(State, Validator.createRegexValidator("", "^[A-Za-z, ]++$", Severity.ERROR));
    	v6.registerValidator(serialnum, Validator.createRegexValidator("", "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z])(?=.*).{8,25})", Severity.ERROR));
    	btnadd.disableProperty().bind(v2.invalidProperty());
    	btnadd.disableProperty().bind(v3.invalidProperty());
    	btnadd.disableProperty().bind(v4.invalidProperty());
    	btnadd.disableProperty().bind(v6.invalidProperty());
    	btnadd.disableProperty().bind(v5.invalidProperty());
    	
    }

    @FXML
    private void onaddarboclick(Event event) throws NamingException {
    	refrech();

    	
    }
void refrech() throws NamingException
{   	 Image icon = new Image (
		   getClass().getResourceAsStream("/views/imgs/equi.png"));



treeviewarbo1.setEditable(true);




	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
	Context context1;

		context1 = new InitialContext();

  	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
 	List<Arboresence> Arbolist = Proxy.getPereArbo("Principale") ;
	 TreeItem<String> Arbo = new TreeItem<>("BlocPrincipal", new ImageView(icon));

for (Arboresence x:  Arbolist)
	 	{
	 TreeItem<String> Arbo1 = new TreeItem<>(x.getName(), new ImageView(icon));
	 Arbo.setExpanded(true);
  Arbo.getChildren().add(Arbo1);
	 	 List<ArboPereFis> Arbolist1= Proxy.getFilsArbo(x.getId());
	
for (ArboPereFis x1: Arbolist1)
	 	{
	 	 TreeItem<String> Arbo2 = new TreeItem<>(x1.getArboFils().getName(), new ImageView(icon));

Arbo1.getChildren().add(Arbo2);
	  List<ArboPereFis> Arbolist2= Proxy.getFilsArbo(x1.getArboFils().getId());
		
for (ArboPereFis x2: Arbolist2)
	 	{
	      TreeItem<String> Arbo3 = new TreeItem<>( x2.getArboFils().getName(), new ImageView(icon));
Arbo2.setExpanded(true);
Arbo2.getChildren().add(Arbo3);

		List<ArboPereFis> Arbolist3= Proxy.getFilsArbo( x2.getArboFils().getId());
for (ArboPereFis x3: Arbolist3)
	 	{
	 	TreeItem<String> arbo4= new TreeItem<>(x3.getArboFils().getName(), new ImageView(icon));
Arbo3.setExpanded(true);
Arbo3.getChildren().add(arbo4);
List<ArboPereFis> Arbolist4= Proxy.getFilsArbo( x3.getArboFils().getId());
for (ArboPereFis x5: Arbolist4)
	 	{
	 	TreeItem<String> arbo5= new TreeItem<>(x5.getArboFils().getName(), new ImageView(icon));
arbo4.setExpanded(true);
arbo4.getChildren().add(arbo5);
	 	}
		
		
	 	}
	 	
	 	}
	 	

		// node.getChildren().addAll(nodeA1,nodeB1,nodeC1);
	 	}

	 	}
treeviewarbo1.setRoot(Arbo); 


treeviewarbo1.getSelectionModel().selectedItemProperty()
 .addListener(new ChangeListener<TreeItem<String>>()
		 {
			
			@Override
			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
					TreeItem<String> newValue) {
				 TreeItem<String> selectedItem = newValue;
				adarb.setText(selectedItem.getValue());
			}
		});

}

  
   
    @FXML
    private void DeleteClick(ActionEvent event) throws NamingException {
    	
       	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1 = new InitialContext();
    	
    	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
    	 String Select=adarb.getText();
    	String namee=name.getText();
    	
    Arboresence arbo =Proxy.getArbo(Select);
   
    Proxy.DeleteArbo(arbo.getId());
refrech();

    }

    @FXML
    private void onUpdate1Action(ActionEvent event) {
    	btndelete1.setVisible(false);
    	btnadd11.setVisible(false);
    	btnmodifarbo.setVisible(true);
    	btnadd1.setVisible(false);
    	
    	combarbotype.setVisible(false);
    	labelarbotype.setVisible(false);
    	
    }

    @FXML
    private void onDelete1Action(ActionEvent event) {
    	btnupdatee.setVisible(false);
    	btnadd11.setVisible(true);
    	btnmodifarbo.setVisible(false);
    	btnadd1.setVisible(false);
    
    	combarbotype.setVisible(false);
    	labelarbotype.setVisible(false);
    	name.setVisible(false);
    	
    }
    void refrech1() throws NamingException
    {   	 Image icon = new Image (
    		   getClass().getResourceAsStream("/views/imgs/equi.png"));



    treeviewarbo11.setEditable(true);




    	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
    	Context context1;

    		context1 = new InitialContext();

      	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
     	List<Arboresence> Arbolist = Proxy.getPereArbo("Principale") ;
    	 TreeItem<String> Arbo = new TreeItem<>("BlocPrincipal", new ImageView(icon));

    for (Arboresence x:  Arbolist)
    	 	{
    	 TreeItem<String> Arbo1 = new TreeItem<>(x.getName(), new ImageView(icon));
    	 Arbo.setExpanded(true);
      Arbo.getChildren().add(Arbo1);
    	 	 List<ArboPereFis> Arbolist1= Proxy.getFilsArbo(x.getId());
    	
    for (ArboPereFis x1: Arbolist1)
    	 	{
    	 	 TreeItem<String> Arbo2 = new TreeItem<>(x1.getArboFils().getName(), new ImageView(icon));

    Arbo1.getChildren().add(Arbo2);
    	  List<ArboPereFis> Arbolist2= Proxy.getFilsArbo(x1.getArboFils().getId());
    		
    for (ArboPereFis x2: Arbolist2)
    	 	{
    	      TreeItem<String> Arbo3 = new TreeItem<>( x2.getArboFils().getName(), new ImageView(icon));
    Arbo2.setExpanded(true);
    Arbo2.getChildren().add(Arbo3);

    		List<ArboPereFis> Arbolist3= Proxy.getFilsArbo( x2.getArboFils().getId());
    for (ArboPereFis x3: Arbolist3)
    	 	{
    	 	TreeItem<String> arbo4= new TreeItem<>(x3.getArboFils().getName(), new ImageView(icon));
    Arbo3.setExpanded(true);
    Arbo3.getChildren().add(arbo4);
	List<ArboPereFis> Arbolist4= Proxy.getFilsArbo( x3.getArboFils().getId());
    for (ArboPereFis x5: Arbolist4)
    	 	{
    	 	TreeItem<String> arbo5= new TreeItem<>(x5.getArboFils().getName(), new ImageView(icon));
    arbo4.setExpanded(true);
    arbo4.getChildren().add(arbo5);
    	 	}
    		
    	 	}
    	 	
    	 	}
    	 	

    		// node.getChildren().addAll(nodeA1,nodeB1,nodeC1);
    	 	}

    	 	}
    treeviewarbo11.setRoot(Arbo); 


    treeviewarbo11.getSelectionModel().selectedItemProperty()
     .addListener(new ChangeListener<TreeItem<String>>()
    		 {
    			
    			@Override
    			public void changed(ObservableValue<? extends TreeItem<String>> observable, TreeItem<String> oldValue,
    					TreeItem<String> newValue) {
    				 TreeItem<String> selectedItem = newValue;
    				 arbofinal.setText(selectedItem.getValue());
    			}
    		});

    }

    

 

    @FXML
    private void ONADDEQUI22(Event event) throws NamingException {
    	refrech1();
    }
}

