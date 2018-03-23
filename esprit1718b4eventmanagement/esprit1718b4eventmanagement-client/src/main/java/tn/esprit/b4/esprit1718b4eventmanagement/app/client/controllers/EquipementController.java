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

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
public class EquipementController implements Initializable {

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
    private JFXComboBox<?> Combo1;
    @FXML
    private JFXComboBox<?> Combo2;
    @FXML
    private JFXComboBox<?> Combo3;
    @FXML
    private JFXComboBox<?> Combo4;
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
    private JFXComboBox<?> Combo11;
    @FXML
    private JFXComboBox<?> Combo21;
    @FXML
    private JFXComboBox<?> Combo31;
    @FXML
    private JFXComboBox<?> Combo41;
    @FXML
    private JFXComboBox<?> combarbotype;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
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
	


    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
Calendar date = Calendar.getInstance();
String dateF = df.format(date.getTime());

    Equipment eq=new Equipment(serie ,desc,stat,dateF,fab,marq);
    
    
    Proxy.addEquippement(eq);
    }
    
    

    @FXML
    private void OnCancelArboClick(ActionEvent event) {
    }

    @FXML
    private void OnAddArboClick(ActionEvent event) {
    	
    	
    	
    	
    	
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
    	
    	
    	
    	
    	
    	
    }
    
}
