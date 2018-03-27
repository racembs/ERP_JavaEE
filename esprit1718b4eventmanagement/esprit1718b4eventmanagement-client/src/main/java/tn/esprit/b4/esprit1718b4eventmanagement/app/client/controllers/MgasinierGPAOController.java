/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote;

/**
 * FXML Controller class
 *
 * @author RBS
 */
public class MgasinierGPAOController implements Initializable {

    @FXML
    private Tab tabArticleTree1;
    @FXML
    private TableView<?> tableAllOrders;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem UpdateItem;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private TableColumn<Nomenclature,String> tArticle;
    @FXML
    private TableColumn<Nomenclature,String> tQuantity;
    @FXML
    private TableColumn<Nomenclature,String> tAlarmDate;
    @FXML
    private TableColumn<Nomenclature,String> tArticleQuantity;
    @FXML
    private TableColumn<Nomenclature,String> tArticleType;
    @FXML
    private JFXTextField txtSearchOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
		fillTableAllOrdres();
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }    
public void fillTableAllOrdres() throws NamingException {

	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
	Context context1 = new InitialContext();
	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
	//List<MvtApprov> listOrdre=OrdreProxy.getAllOrders();
	 tArticle.setCellValueFactory(new PropertyValueFactory<>("Article"));
     tAlarmDate.setCellValueFactory(new PropertyValueFactory<>("AlarmDate"));
     tQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
     
     

	
}
    @FXML
    private void OnMenuUpdateArticleClicked(ActionEvent event) {
    }

    @FXML
    private void OnDeleteArticleClicked(ActionEvent event) {
    }


    @FXML
    private void SearchArticleFromTableAction(KeyEvent event) {
    }

    @FXML
    private void OnTabAllArticleSelected(Event event) {
    }
}
   