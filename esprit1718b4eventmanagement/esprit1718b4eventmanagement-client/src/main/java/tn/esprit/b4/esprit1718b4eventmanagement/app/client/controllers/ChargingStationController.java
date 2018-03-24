package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ons
 */

public class ChargingStationController implements Initializable {
	  
    private Label label;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private TableView<ChargingStation> idTab;
    @FXML
    private TableColumn<ChargingStation, String> idUserTab;
    @FXML
    private TableColumn<ChargingStation, String> idEquipementTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idCodeTab;
    @FXML
    private TableColumn<ChargingStation, String> idDescriptionTab;
    @FXML
    private TableColumn<ChargingStation, String> idNaturePostTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idNbDaysTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idNbHoursTab;
    @FXML
    private ImageView idAdd;
    @FXML
    private ImageView idUpdate;
    @FXML
    private ImageView idDelete;
    @FXML
    private ImageView idFind;
    @FXML
    private JFXTextField idchs;
    @FXML
    private ComboBox<User> idUser;
    @FXML
    private ComboBox<Equipment> idEquipement;
    @FXML
    private TextField idCode;
    @FXML
    private TextField idNaturePost;
    @FXML
    private TextField idNbDays;
    @FXML
    private TextField idNbHours;
    @FXML
    private TextArea idDEscription;
    @FXML
    private ImageView idCalendar;
    @FXML
    private ImageView idStatistics;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	Context context;
    	
		try {
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation;
			contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();
		
		
	
			Context contextUser;
			contextUser = new InitialContext();
	    	UserServiceRemote proxyuser = (UserServiceRemote) contextUser.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
	    	User user = new User();
	    	//List<User> listU = proxyuser.DisplayUser();
			List<User>listu = proxyuser.DisplayUser();
			
//			
//			for(int i=0;i<listU.size();i++)
//			{
//				listuser.add(listU.get(i).getFirstname()+" "+listU.get(i).getLastname());
//			}
//
//             ObservableList obList = FXCollections.observableList(listuser);
//    		//idUser.getItems().clear();
//             idUser.setItems(obList);
//             idUser.getSelectionModel().selectLast();
 	
             
 			Context contextEquipment;
 			contextEquipment = new InitialContext();
 			EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
 	    	Equipment equipment = new Equipment();
 	    	List<Equipment> listE = proxyEquipment.DisplayEquipment();
// 			List<String>listeq = new ArrayList<>();
// 			
// 			
// 			for(int i=0;i<listE.size();i++)
// 			{
// 				listeq.add(listE.get(i).getDescription()+" "+listE.get(i).getSerialNum());
// 			}

              ObservableList<Equipment> obListeq = FXCollections.observableList(listE);
              ObservableList<User> obListu = FXCollections.observableList(listu);
     		//idUser.getItems().clear();
              idEquipement.setItems(obListeq);
              idEquipement.valueProperty().addListener((obs, oldVal, newVal) -> {
                  String selectionText =  newVal.getSerialNum() + "-" + newVal.getDescription();

                  System.out.println(selectionText);
                 // textNamePrice.setText(selectionText);
              });
              idEquipement.getSelectionModel().selectLast();
			
 			 
             idUser.setItems(obListu);
             idUser.valueProperty().addListener((obs, oldVal, newVal) -> {
                 String selectionText =  newVal.getFirstname() + "-" + newVal.getLastname();

                 System.out.println(selectionText);
                // textNamePrice.setText(selectionText);
             });
           
             idUser.getSelectionModel().selectLast();	
              
			idUserTab.setCellValueFactory(new Callback<CellDataFeatures<ChargingStation,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<ChargingStation, String> param) {
//	            	  String f= param.getValue().getUser().getFirstname();
//	            	  String l= param.getValue().getUser().getLastname();
	                  return new SimpleStringProperty(param.getValue().getUser().getFirstname()+"-"+param.getValue().getUser().getLastname());
	              }
	          }); 
			idEquipementTab.setCellValueFactory(new Callback<CellDataFeatures<ChargingStation,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<ChargingStation, String> param) {
	                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"-"+param.getValue().getEquipement().getDescription());
	              }
	          }); 
			idCodeTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("code"));
			idDescriptionTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, String>("description"));
			idNaturePostTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, String>("naturepost"));
			idNbDaysTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("nbday"));
			idNbHoursTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("nbhours"));
			
			
			   List<ChargingStation> list = proxyChargingStation.DisplayChargingStation();
		        ObservableList<ChargingStation> items = FXCollections.observableArrayList(list);
		        idTab.setItems(items);
	
		
		  idDelete.setOnMouseClicked((MouseEvent e) -> { 
		    	
			  
				try {

					String jndiNameChargingStation1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
					Context contextChargingStation1;
					contextChargingStation1 = new InitialContext();
					
					ChargingStationServiceRemote proxyChargingStation1 = (ChargingStationServiceRemote) contextChargingStation1.lookup(jndiNameChargingStation1);
					ChargingStation ch1 = new ChargingStation(); 
					
				  	Alert alert = new Alert(AlertType.CONFIRMATION);
			    	alert.setTitle("WARNING");
					alert.setHeaderText("Are You Sure?");
			    	
			    	if (alert.showAndWait().get () == ButtonType.OK)
			    	{
			    		
			    	
			    		
			        	Integer idE= idTab.getSelectionModel().getSelectedItem().getEquipement().getId();
			        	Integer idU= idTab.getSelectionModel().getSelectedItem().getUser().getId();
			        	System.out.println(idE);
			        	System.out.println(idU);
			        	proxyChargingStation1.deleteChargingStation(idE,idU);
			        	
			    		   List<ChargingStation> listch = proxyChargingStation1.DisplayChargingStation();
			    	        ObservableList<ChargingStation> itemsch = FXCollections.observableArrayList(listch);
			    	        System.out.println(itemsch.get(0).getDescription());
			    	        idTab.setItems(itemsch);
			    	        
			    	        
			    		 Alert alert1 = new Alert(AlertType.INFORMATION);
			    			alert1.setTitle("ChargingStation Deleted");
			    			alert1.setHeaderText("Succesful");
			    			alert1.showAndWait();
			    		//System.out.println("deleted");
			        	
			     
			    		
			    	}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	

		    });
		  
		  
		  idAdd.setOnMouseClicked((MouseEvent e) -> { 
			  
			  
			  
			  ChargingStation charginstation = new ChargingStation();
			  int code=Integer.parseInt(idCode.getText());
			  charginstation.setCode(code);	  
			  charginstation.setDescription(idDEscription.getText());
			  charginstation.setNaturepost(idNaturePost.getText());
			  int nbd=Integer.parseInt(idNbDays.getText());
			  int nbh=Integer.parseInt(idNbHours.getText());		  
			  charginstation.setNbday(nbd);
			  charginstation.setNbhours(nbh);
			
			  int idE = idEquipement.getSelectionModel().getSelectedItem().getId();
			  int idU = idUser.getSelectionModel().getSelectedItem().getId();
			  System.out.println(idE);
			  System.out.println(idU);
			//  System.out.println(idU);
			  //int idU = Integer.parseInt(idUser.getValue().toString());
			  
			  
			 // idE=proxyEquipment.findEquipementByNumDesc("bb","aaa").getId();
		    	
//			  System.out.println("hello");
//		    	String a= String.valueOf(idE);
//		    	String b= String.valueOf(idU);
//			  System.out.println(a+""+b);
			  try {
				String jndiNameChargingStation22 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
				Context contextChargingStation22;
				contextChargingStation22 = new InitialContext();
				ChargingStationServiceRemote proxyChargingStation22 = (ChargingStationServiceRemote) contextChargingStation22.lookup(jndiNameChargingStation22);
				
			  proxyChargingStation22.addChargingStation(idE, idU, charginstation);
				System.out.println("created");
				
				
//				   List<OperatingRange> list = proxy.DisplayOperatingRange();
//			        ObservableList<OperatingRange> items = FXCollections.observableArrayList(list);
//			        System.out.println(items.get(0).getDesignation());
//			        idTab.setItems(items);
			        
			        
				 Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ChargingStation Added");
					alert.setHeaderText("Succesful");
					alert.showAndWait();
		} catch (NamingException n) {
					
					n.printStackTrace();
				}
		  });
		  
		  
		  
		  idFind.setOnMouseClicked((MouseEvent e) -> { 
			
		  		Context context55;
				try {
					context55 = new InitialContext();
					String ChargingStationjndiName55 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
					ChargingStationServiceRemote proxyChargingStation55 =  (ChargingStationServiceRemote) context55.lookup(ChargingStationjndiName55);
			       
			  int c=Integer.parseInt(idchs.getText());	  
			  System.out.println(idchs.getText());
			  List<ChargingStation> listch22 =proxyChargingStation55.find(c);
				        
			    	        ObservableList<ChargingStation> itemsch22 = FXCollections.observableArrayList(listch22);
			    	        System.out.println(itemsch22.get(0).getDescription());
			    	        idTab.setItems(itemsch22);
			    			  
				} catch (NamingException n) {
					
					n.printStackTrace();
				}
				
				  
				  
				  
				  
		});
		  
		  
		  
			
		  idUpdate.setOnMouseClicked((MouseEvent e) -> { 
		
		  });
		  
    } catch (NamingException e1) {
		// TODO Auto-generated catch block
		
	}
		}
    
  
    
    
    }    

    

