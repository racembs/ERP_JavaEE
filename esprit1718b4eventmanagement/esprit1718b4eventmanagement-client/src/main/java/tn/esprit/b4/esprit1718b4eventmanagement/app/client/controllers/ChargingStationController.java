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
    private ComboBox<?> idUser;
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
    	
    }
    
    
    }    

    

