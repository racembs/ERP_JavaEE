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
    private JFXTextField idoptrange;
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
	    	List<User> listU = proxyuser.DisplayUser();
			List<String>listuser = new ArrayList<>();
			
			
			for(int i=0;i<listU.size();i++)
			{
				listuser.add(listU.get(i).getFirstname()+" "+listU.get(i).getLastname());
			}

             ObservableList obList = FXCollections.observableList(listuser);
    		//idUser.getItems().clear();
             idUser.setItems(obList);
             idUser.getSelectionModel().selectLast();
			
			
             
 			Context contextEquipment;
 			contextEquipment = new InitialContext();
 			EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
 	    	Equipment equipment = new Equipment();
 	    	List<Equipment> listE = proxyEquipment.DisplayEquipment();
 			List<String>listeq = new ArrayList<>();
 			
 			
 			for(int i=0;i<listE.size();i++)
 			{
 				listeq.add(listE.get(i).getDescription()+" "+listE.get(i).getSerialNum());
 			}

              ObservableList obListeq = FXCollections.observableList(listeq);
     		//idUser.getItems().clear();
              idEquipement.setItems(obListeq);
              idEquipement.getSelectionModel().selectLast();
			
			
			idUserTab.setCellValueFactory(new Callback<CellDataFeatures<ChargingStation,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<ChargingStation, String> param) {
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
    } catch (NamingException e1) {
		// TODO Auto-generated catch block
		
	}
		}
    
  
    
    
    }    

    

