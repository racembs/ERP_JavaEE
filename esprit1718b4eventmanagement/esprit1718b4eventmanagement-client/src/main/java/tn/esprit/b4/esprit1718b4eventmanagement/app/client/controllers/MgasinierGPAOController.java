/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDateTime;
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
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.util.Callback;
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
    private TableView<MvtApprov> tableAllOrders;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem UpdateItem;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private TableColumn<MvtApprov,String> tArticle;
    @FXML
    private TableColumn<MvtApprov,String> tQuantity;
    @FXML
    private TableColumn<MvtApprov,String> tAlarmDate;
    @FXML
    private TableColumn<MvtApprov,String> tRequestDate;
    @FXML
    private TableColumn<MvtApprov,String> tReceptionDate;
    @FXML
    private JFXTextField txtSearchOrder;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
		fillTableAllOrdres("all");
		AuoOrderCreation();
		
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
    }    
public void fillTableAllOrdres(String code) throws NamingException {

	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
	Context context1 = new InitialContext();
	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
	
	 //tArticle.setCellValueFactory(new PropertyValueFactory<>("article"));
	 tArticle.setCellValueFactory(new Callback<CellDataFeatures<MvtApprov,String>,ObservableValue<String>>(){

         @Override
         public ObservableValue<String> call(CellDataFeatures<MvtApprov, String> param) {
             return new SimpleStringProperty(param.getValue().getArticle().getArticleCode());
         }
     });
     tAlarmDate.setCellValueFactory(new PropertyValueFactory<>("alarmDate"));
     tQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
     tReceptionDate.setCellValueFactory(new PropertyValueFactory<>("receptionDate"));
     tRequestDate.setCellValueFactory(new PropertyValueFactory<>("requestDate"));

 	ObservableList<MvtApprov> ObsListOrders;
	if(code.equals("all")) {
		ObsListOrders = FXCollections.observableArrayList(OrdreProxy.getAllOrders());
	}
	else {
		ObsListOrders = FXCollections.observableArrayList(OrdreProxy.getAllOrders());
	}
	
	tableAllOrders.setItems(ObsListOrders);
	tableAllOrders.refresh();
     
     

	
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
    
    
    
    
    private void AuoOrderCreation() throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	
    	List<Article> articles=aArticleProxy.getAllArticles();
    	
    	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
    	Context context1 = new InitialContext();
    	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
    	
    	
    	for(int i=0;i<articles.size();i++) {
    	int joursRestant=articles.get(i).getQuantity()/articles.get(i).getDailyConsumption();
    	 
    	
    	Date dateT=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
    	Date currentDate =java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());


    	int day= dateT.getDate();

    	dateT.setDate(day+joursRestant-articles.get(i).getDeliveryTime());
    	if(dateT.compareTo(currentDate)<=0 && articles.get(i).getEtatOrdre()==0) {
    		MvtApprov mvtApprov=new MvtApprov(articles.get(i),null,articles.get(i).getPricipalQuantity()-articles.get(i).getQuantity(),null,dateT,null);
    		OrdreProxy.addMvtApprov(mvtApprov);
    		articles.get(i).setEtatOrdre(1);
    		aArticleProxy.updateArticle(articles.get(i));
    	}
    	
    	
    	
    	
    	}
    }
}
   