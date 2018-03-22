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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ons
 */

public class OperatingRangeController implements Initializable {
	private JFXHamburger hamburger;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private AnchorPane holderPane;
    private JFXDrawer drawer;
    @FXML
    private Tab idArticle;
    @FXML
    private Tab idChargingStation;
    @FXML
    private Tab idOperatingRange;
    @FXML
    private Tab idManufacturing;
    @FXML
    private Tab idClient;
    @FXML
    private Tab idChargingStation12;
    @FXML
    private MenuBar idMenuArticle;
    @FXML
    private MenuBar idMenuManufacturing;
    @FXML
    private MenuBar idMenuOrders;
    @FXML
    private MenuBar idMenuClient;
    @FXML
    private TextField idCode;
    @FXML
    private TextArea idDesignation;
    @FXML
    private TextField idDeadline;
    @FXML
    private ComboBox<String> idStakingCond;
    @FXML
    private TableView<OperatingRange> idTab;
    @FXML
    private TableColumn<OperatingRange, String> idCodeTab;
    @FXML
    private TableColumn<OperatingRange, String> idDesignationTab;
    @FXML
    private TableColumn<OperatingRange, Integer> idDeadlineTab;
    @FXML
    private TableColumn<OperatingRange, String> idStakingCondTab;
    @FXML
    private Button idAdd;
    @FXML
    private Button idUpdate;
    @FXML
    private Button idDelete;
    @FXML
    private Button idFind;
    @FXML
    private JFXTextField idoptfind;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	idStakingCond.getItems().addAll("Consecutive","Overlap","With staking delay","Parallel");
    	idStakingCond.getSelectionModel().selectLast();
    	
    	Context context;
		try {
			context = new InitialContext();
			String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	        OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);

	        idCodeTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("code"));
	        idDesignationTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("designation"));
	        idDeadlineTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, Integer>("deadline"));
	        idStakingCondTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("stakingcondition"));
	        
	        List<OperatingRange> list = proxy.DisplayOperatingRange();
	        ObservableList<OperatingRange> items = FXCollections.observableArrayList(list);
	        System.out.println(items.get(0).getDesignation());
	        idTab.setItems(items);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
		}
    

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }

    @FXML
    private void AddOperatingRange(ActionEvent event) {
    	
    	
		String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";

		Context context;
		try {
			context = new InitialContext();
			OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);
			//OperatingRange optrange1 = new OperatingRange("AR","Table mount","Series",30);
			OperatingRange optrange = new OperatingRange();
			optrange.setCode(idCode.getText());
			optrange.setDesignation(idDesignation.getText());
			int Deadline = Integer.parseInt(idDeadline.getText());
			optrange.setDeadline(Deadline);
			optrange.setStakingcondition(idStakingCond.getValue().toString());
						
			proxy.addOperatingRange(optrange);
			//System.out.println("created");
			
			   List<OperatingRange> list = proxy.DisplayOperatingRange();
		        ObservableList<OperatingRange> items = FXCollections.observableArrayList(list);
		        System.out.println(items.get(0).getDesignation());
		        idTab.setItems(items);
		        
		        
			 Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Operating Range Added");
				alert.setHeaderText("Succesful");
				alert.showAndWait();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			
		}
		
    }

    @FXML
    private void UpdateOperatingRange(ActionEvent event) {
    }

    @FXML
    private void DeleteOperatingRange(ActionEvent event) {
    	
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("WARNING");
		alert.setHeaderText("Are You Sure?");
    	
    	if (alert.showAndWait().get () == ButtonType.OK)
    	{
    		
    		try {
        		Context context;
    			context = new InitialContext();
    			String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
    	        OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);

        	Integer id= idTab.getSelectionModel().getSelectedItem().getIdoptrange();
        	proxy.deleteOperatingRange(id);
        	
    		   List<OperatingRange> list = proxy.DisplayOperatingRange();
    	        ObservableList<OperatingRange> items = FXCollections.observableArrayList(list);
    	        System.out.println(items.get(0).getDesignation());
    	        idTab.setItems(items);
    	        
    	        
    		 Alert alert1 = new Alert(AlertType.INFORMATION);
    			alert1.setTitle("Operating Range Deleted");
    			alert1.setHeaderText("Succesful");
    			alert1.showAndWait();
    		//System.out.println("deleted");
        	
        	} catch (NamingException e) {
    			// TODO Auto-generated catch block
    			
    		}
    		
    	}
    
    }

    @FXML
    private void FindOperatingRange(ActionEvent event) {
    }
}
