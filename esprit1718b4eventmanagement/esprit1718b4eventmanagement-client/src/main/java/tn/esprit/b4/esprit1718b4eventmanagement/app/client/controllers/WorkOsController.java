/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;

import javafx.scene.control.cell.*;
import javafx.scene.text.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;

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
import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class WorkOsController implements Initializable {

    @FXML
    private JFXComboBox<?> comboSpecialization;
    @FXML
    private ToggleGroup q;
    @FXML
    private JFXButton saveWR;
    @FXML
    private JFXTextField object;
    @FXML
    private JFXTextArea adInfo;
    @FXML
    private JFXTreeView<?> treeviewEq;
    @FXML
    private Tab tabD;
    @FXML
    private TableView<UsualWork> tableview;
    @FXML
    private TableColumn<UsualWork, String> C1;
    @FXML
    private TableColumn<UsualWork, String> C2;
    @FXML
    private TableColumn<UsualWork, String> C3;
    @FXML
    private TableColumn<UsualWork, String> C4;
    @FXML
    private TableColumn<UsualWork, String> C5;
    @FXML
    private TableColumn<UsualWork, String> C6;
    @FXML
    private TableColumn<UsualWork, String> C7;
    @FXML
    private TableColumn<UsualWork, String> C8;
    @FXML
    private JFXTextField f1;
    @FXML
    private JFXTextField f2;
    @FXML
    private JFXTextField f3;
    @FXML
    private JFXTextField f4;
    @FXML
    private JFXTextField f5;
    @FXML
    private JFXTextField f6;
    @FXML
    private Label l1;
    @FXML
    private JFXButton start;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton done;
    @FXML
    private GridPane pane;
    @FXML
    private JFXRadioButton book;

    static UsualWork work;
    /**
     * Initializes the controller class.
     */
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	delete.setVisible(false);
    	done.setVisible(false);
   	 Image icon = new Image (
			   getClass().getResourceAsStream("/views/imgs/play.png"));
		start.setGraphic(new ImageView(icon));
		
		start.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 50px; " +
                "-fx-min-height: 50px; " +
                "-fx-max-width: 50px; " +
                "-fx-max-height: 50px;"
        );
  	 tableview.setEditable(true);
 	//UserServiceRemote userService2;
  	 // l1.setVisible(false);
		try {
			
			Context context;
			context = new InitialContext();
	    	UserServiceRemote userService2 = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");

			List<String>lun = new ArrayList<>();
			for(int i=0;i<userService2.findAll().size();i++)
			{
			lun.add(userService2.findAll().get(i).getFirstname()+" "+userService2.findAll().get(i).getLastname());
			}

    		
    		


		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

 	Context context;


		try {
			

				context= new InitialContext();
			 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
		
	
		    		C1.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("objet"));
		    		C2.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("description"));
		    		C3.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("technology"));
		  
		    		C5.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("emmergency"));
				    C6.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("WRDate"));
				    C7.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
		    		if(LoginController.user.getPost().equals("technician"))
		    		{
		    			 List<UsualWork> list = proxy.displayWObyTech(LoginController.user.getId());
		    			 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    			/* if(items.isEmpty())
		    			 {
		    				 Alert alert = new Alert(AlertType.INFORMATION);
		    					alert.setTitle("You have no work orders !");
		    					alert.setHeaderText("work orders");
		    					alert.showAndWait(); 
		    			 }
		    			 else*/
		    		     tableview.setItems(items);
		    		}
		    		else
		    		{ List<UsualWork> list = proxy.displayWO();
		    		 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    	     tableview.setItems(items);}
	    	       
	    	      //  System.out.println(items.get(0).getDescription());
	    	  
	    	   
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		tableview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			
			pane.setVisible(true);
			if(newValue.getOrderstate().equals("start"))
			{
				start.setVisible(false);
				done.setVisible(true);
			}
			if(newValue.getOrderstate().equals("done"))
			{
				start.setVisible(false);
				done.setVisible(false);
				delete.setVisible(true);
			}
			work=newValue;
		}));
		
		tableview.getSelectionModel().setCellSelectionEnabled(true);
		ObservableList selectedCells = tableview.getSelectionModel().getSelectedCells();
		selectedCells.addListener(new ListChangeListener() {
		    @Override
		    public void onChanged(Change c) {
		    	TablePosition<UsualWork, String> tablePosition = (TablePosition) selectedCells.get(0);
		    	int row = tablePosition.getRow();
		    	work=tableview.getItems().get(row);
		    	 if(tablePosition.getTableColumn().getText().equals("Equipment(Ref--Info)"))
		        {showdetails(tableview.getItems().get(row).getEquipement());
		    		 Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
		      l1.setVisible(true);
		        System.out.println("Selected Value" + val);
		        
		        }

		    }
		});
		
    	
    }   
	private void showdetails(Equipment trader) {

		
		f1.setText("description: "+trader.getDescription());
	f2.setText("EIS date: "+trader.getEISDate());
		f3.setText("fabriquant: "+trader.getFabriquant());
		f4.setText("marque: "+trader.getMarque());
		f5.setText("lieu: "+trader.getLieu());

	}

    @FXML
    private void onclickSave(ActionEvent event) {
    }
    @FXML
    private void onclickStart(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	 System.out.println(work.getDescription());
    	work.setOrderstate("start");
    	work.setStartDate(new Date());
    	proxy.updateWork(work);
    	//l'affichage de l interface booking
    	
    }
    @FXML
    private void onclickDone(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	 System.out.println(work.getDescription());
    	work.setOrderstate("done");
    	work.setEndDate(new Date());
    	proxy.updateWork(work);
    	//l'affichage de l interface booking
    	
    }
    @FXML
    private void onclickDelete(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	 System.out.println(work.getDescription());
    	
    	proxy.deleteWork(work.getId());
    	//l'affichage de l interface booking
    	
    }
    
    @FXML
    private void onBookTool(ActionEvent event) {
    }
}
