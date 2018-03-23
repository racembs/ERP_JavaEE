package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
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
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;

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
    private ComboBox<?> idEquipement;
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
		    	
				Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("WARNING");
				alert.setHeaderText("Are You Sure?");
		    	
		    	if (alert.showAndWait().get () == ButtonType.OK)
		    	{

	        	Integer idE= idTab.getSelectionModel().getSelectedItem().getChargingstationPK().getId_equipment();
	        	Integer idU= idTab.getSelectionModel().getSelectedItem().getChargingstationPK().getIdUser();
	        	proxyChargingStation.deleteChargingStation(idE, idU);
	        	System.out.println("deleted");
	    		   List<ChargingStation> list1 = proxyChargingStation.DisplayChargingStation();
	    	        ObservableList<ChargingStation> items1 = FXCollections.observableArrayList(list1);
	    	        
	    	        idTab.setItems(items1);
	    	        
	    	        
	    		 Alert alert1 = new Alert(AlertType.INFORMATION);
	    			alert1.setTitle("Charging Station Deleted");
	    			alert1.setHeaderText("Succesful");
	    			alert1.showAndWait();
	    		//System.out.println("deleted");
	        	
	        
	    		
	    	}
		    	
		    });
    } catch (NamingException e1) {
		// TODO Auto-generated catch block
		
	}
		}
    
  
    
    
    }    

    

