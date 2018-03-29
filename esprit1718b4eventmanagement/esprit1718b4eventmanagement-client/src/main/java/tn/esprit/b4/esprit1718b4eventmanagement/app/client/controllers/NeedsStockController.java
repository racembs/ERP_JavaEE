package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;
import com.jfoenix.controls.JFXButton;


import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import java.awt.Label;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.CustomTextField;

public class NeedsStockController implements Initializable{
	

	    @FXML
	    private ToggleGroup q;
    @FXML
    private JFXTextField reference;
    @FXML
    private JFXTextArea description;
    @FXML
    private JFXTextField brand;
    @FXML
    private JFXTextField supplier;
    @FXML
    private JFXTextField code_supp;
    @FXML
    private JFXRadioButton tool;
    @FXML
    private JFXRadioButton spareparts;
    @FXML
    private CustomTextField quantity;
    @FXML
    private CustomTextField price;
    @FXML
    private JFXComboBox<String> FamiliCombo;
    @FXML
    private JFXComboBox<String> MethodCombo;
    @FXML
    private JFXComboBox<String> NatureCombo;
    @FXML
    private JFXButton addstock;
    @FXML
    private TableView<Tool> ToolsTab;
    @FXML
    private TableView<SpareParts> SparePartsTab;
    @FXML
    private JFXButton FindSP;
    @FXML
    private javafx.scene.control.Label labMethod ;
    @FXML
    private javafx.scene.control.Label labNature ;
    
    static int x ;
    @FXML
    private TableColumn<Tool,Integer> Tquant;
    @FXML
    private TableColumn<Tool,String> Tref;
    @FXML
    private TableColumn<Tool,String> Tdesc;
    @FXML
    private TableColumn<Tool,String> Tbrand;
    @FXML
    private TableColumn<Tool,String> Tfam;
    @FXML
    private TableColumn<Tool,String> Tnature;
    @FXML
    private TableColumn<Tool,String> Tsupp;
    @FXML
    private TableColumn<Tool,String> TcodeS;
    @FXML
    private TableColumn<Tool,Number> Tprix;
    @FXML
    private TableColumn<SpareParts,Integer > Squent;
    @FXML
    private TableColumn<SpareParts, String> Sref;
    @FXML
    private TableColumn<SpareParts, String> Sdesc;
    @FXML
    private TableColumn<SpareParts, String> Sbrand;
    @FXML
    private TableColumn<SpareParts, String> Sfam;
    @FXML
    private TableColumn<SpareParts, String> Smethod;
    @FXML
    private TableColumn<SpareParts, String> Ssup;
    @FXML
    private TableColumn<SpareParts, String> ScodSup;
    @FXML
    private TableColumn<SpareParts, Number> Sprix;

	    /**
	     * Initializes the controller class.
	     */
	    @Override
	    public void initialize(URL url, ResourceBundle rb) {
	    	
	   /*********************** adding needs part *************************************************/
	    	
	    	FamiliCombo.getItems().addAll("Mechanical","Electrical ","hydraulic"
	    	        ,"unspecified");
	    	
	    	MethodCombo.getItems().addAll("quantité fixe date variable","quantite variable dt fixe","nothing");
	    	
	    	NatureCombo.getItems().addAll("measuring tool","regular tool");
	    	
	    	MethodCombo.setVisible(false);
	    	NatureCombo.setVisible(false);
	    	labNature.setVisible(false);
	    	labMethod.setVisible(false);
	    	
	   /*************************** SpareParts table ******************************************/
	    	
	    	
			try {

		    	Context context;
		    	context = new InitialContext();
				String SparejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedSparePartsService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote";    	
				NeedSparePartsServiceRemote proxyS =  (NeedSparePartsServiceRemote) context.lookup(SparejndiName);

		        Ssup.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Supplier"));
		        Sref.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Reference"));
		        Squent.setCellValueFactory(new PropertyValueFactory<SpareParts, Integer>("Quantity"));
		        Sprix.setCellValueFactory(new PropertyValueFactory<SpareParts, Number>("Price"));
		        Smethod.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Method"));
		        Sfam.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Family"));
		        Sdesc.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Description"));
		        ScodSup.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Code_Supplier"));
		        Sbrand.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Brand"));
		        
		        
		        List<SpareParts> lists = proxyS.displayAll();
		        ObservableList<SpareParts> itemss = FXCollections.observableArrayList(lists);
		        SparePartsTab.setItems(itemss);

			} catch (NamingException e) {
				// TODO Auto-generated catch block
			}
	    
	    	
	    	
	    	
	   /*******************************************************************/
			
 /*************************** Tool table ******************************************/
	    	
	    	
			try {

		    	Context context;
		    	context = new InitialContext();
		    	String TOOLjndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedToolService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote";
		    	NeedToolServiceRemote proxyT =  (NeedToolServiceRemote) context.lookup(TOOLjndiName);

		        Tsupp.setCellValueFactory(new PropertyValueFactory<Tool, String>("Supplier"));
		        Tref.setCellValueFactory(new PropertyValueFactory<Tool, String>("Reference"));
		        Tquant.setCellValueFactory(new PropertyValueFactory<Tool, Integer>("Quantity"));
		        Tprix.setCellValueFactory(new PropertyValueFactory<Tool, Number>("Price"));
		        Tnature.setCellValueFactory(new PropertyValueFactory<Tool, String>("Nature"));
		        Tfam.setCellValueFactory(new PropertyValueFactory<Tool, String>("Tool_Family"));
		        Tdesc.setCellValueFactory(new PropertyValueFactory<Tool, String>("Description"));
		        TcodeS.setCellValueFactory(new PropertyValueFactory<Tool, String>("Code_Supplier"));
		        Tbrand.setCellValueFactory(new PropertyValueFactory<Tool, String>("Brand"));
		        
		        
		        List<Tool> list = proxyT.displayAll();
		        ObservableList<Tool> items = FXCollections.observableArrayList(list);
		        ToolsTab.setItems(items);

			} catch (NamingException e) {
				// TODO Auto-generated catch block
			}
	    
	    	
	    	
	    	
	   /*******************************************************************/
	    	
	    	
	    	
	        
	    }    

    @FXML
    private void OnTool(ActionEvent event) {
    	labMethod.setVisible(false);
    	MethodCombo.setVisible(false);
    	labNature.setVisible(true);
    	NatureCombo.setVisible(true);
    	x = 1;
    }

    @FXML
    private void OnSP(ActionEvent event) {
    	labNature.setVisible(false);
    	NatureCombo.setVisible(false);
    	labMethod.setVisible(true);
    	MethodCombo.setVisible(true);
    	x = 2 ;
    }

    @FXML
    private void OnAddStock(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	String TOOLjndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedToolService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote";
		String SparejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedSparePartsService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote";    	
		NeedToolServiceRemote proxy =  (NeedToolServiceRemote) context.lookup(TOOLjndiName);
		NeedSparePartsServiceRemote proxy2 =  (NeedSparePartsServiceRemote) context.lookup(SparejndiName);

		Tool newTool = new Tool();
		SpareParts newSpare = new SpareParts();
    	UsualWork uw =new UsualWork();
    
  if (x == 1)
	  
  {
	  newTool.setAvailability("Available");
	  newTool.setReference(reference.getText());
	  newTool.setTool_Family(FamiliCombo.getValue().toString());
	  newTool.setNature(NatureCombo.getValue().toString());
	  newTool.setSupplier(supplier.getText());
	  newTool.setCode_Supplier(code_supp.getText());
	  newTool.setDescription(description.getText());
	  float f = Float.parseFloat(price.getText());
	  newTool.setPrice(f);
	  newTool.setBrand(brand.getText());
	  int q = Integer.parseInt(quantity.getText());
	  newTool.setQuantity(q);
	  
	    proxy.addTool(newTool);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Spare Parts Adding");
		alert.setHeaderText("Succesful ");
		alert.showAndWait();
	  
	  
  }
  
  if (x == 2)
  {
	 
	  newSpare.setReference(reference.getText());
	  newSpare.setFamily(FamiliCombo.getValue().toString());
	  newSpare.setMethod(MethodCombo.getValue().toString());
	  newSpare.setSupplier(supplier.getText());
	  newSpare.setCode_Supplier(code_supp.getText());
	  newSpare.setDescription(description.getText());
	  float f = Float.parseFloat(price.getText());
	  newSpare.setPrice(f);
	  newSpare.setBrand(brand.getText());
	  int q = Integer.parseInt(quantity.getText());
	  newSpare.setQuantity(q);
	  
	    proxy2.addSpareParts(newSpare);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Tool Adding");
		alert.setHeaderText("Succesful ");
		alert.showAndWait();
	  
  }

        
    
    }

    @FXML
    private void ClickOnFindSP(ActionEvent event) {
    }
	    



}
