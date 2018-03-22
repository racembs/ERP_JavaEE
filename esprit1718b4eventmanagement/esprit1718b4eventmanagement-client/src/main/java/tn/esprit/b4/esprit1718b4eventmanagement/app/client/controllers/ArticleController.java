/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.google.common.base.Optional;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXButton.ButtonType;
import com.jfoenix.controls.JFXComboBox;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

/**
 * FXML Controller class
 *
 * @author RBS
 */
public class ArticleController implements Initializable {

    @FXML
    private JFXTextField txtArticleCode;
    @FXML
    private JFXTextField txtUnitCode;
    @FXML
    private JFXTextField txtArticlePrice;
    @FXML
    private JFXTextField txtArticleQuantity;
    @FXML
    private JFXButton BtnAddArticle;
    @FXML
    private JFXComboBox<String> comboType;
    @FXML
    private JFXTextArea txtDescription;
    @FXML
    private TreeTableView<Article> ArticleTableView;
    @FXML
    private TreeTableColumn<Article,String> articleColumn;
    @FXML
    private TreeTableColumn<Article,String> quantityColumn;
    public static ArticleServiceRemote ArticleProxy ;
    @FXML
    private Tab tabArticleTree;
    @FXML
    private Tab tabAddNewRticle;
    @FXML
    private JFXTextField txtArticleSearch;
    @FXML
    private JFXTextField txtArticleChild;
    @FXML
    private JFXButton BtnAddArticleChild;
    @FXML
    private JFXTextField txtChildQuantity;
    @FXML
    private Tab tabArticleTree1;
    @FXML
    private TableView<Article> tableAllArticles;
    @FXML
    private TableColumn<Article,String> tArticlePrice;
    @FXML
    private TableColumn<Article,String> tArticleCode;
    @FXML
    private TableColumn<Article,String> tArticleDescription;
    @FXML
    private TableColumn<Article,String> tArticleQuantity;
    @FXML
    private TableColumn<Article,String> tArticleType;
    @FXML
    private TableColumn<Article,String> tUnitCode;
    @FXML
    private JFXTextField txtArticleSearchFromTable;
    @FXML
    private ContextMenu contextMenu;
    @FXML
    private MenuItem UpdateItem;
    @FXML
    private MenuItem deleteItem;
    @FXML
    private JFXButton BtnAddArticleUpdate;
    @FXML
    private JFXTextArea txtDescriptionUpdate;
    @FXML
    private JFXComboBox<String> comboTypeUpdate;
    @FXML
    private JFXTextField txtArticleQuantityUpdate;
    @FXML
    private JFXTextField txtArticlePriceUpdate;
    @FXML
    private JFXTextField txtArticleCodeUpdate;
    @FXML
    private JFXTextField txtUnitCodeUpdate;
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	try {
    		fillTreeTableView("all");
    	
    		
		} catch (NamingException e) {
			}
    	
 
          
comboType.getItems().addAll("Matiére-Premiére","Produit-Semi-Fini","Produit-Fini");
comboTypeUpdate.getItems().addAll("Matiére-Premiére","Produit-Semi-Fini","Produit-Fini");

    	Article article1=new Article();
    	article1.setDescription("article1");
    	article1.setQuantity(10);
    	Article article2=new Article();
    	article2.setDescription("article2");
    	article2.setQuantity(20);
    
    	try {
			fillTableView("all");
		} catch (NamingException e) {
			
		}
    	
    }    

    @FXML
    private void OnAddAction(ActionEvent event) throws NamingException {
    
        
    	
		Article article=new Article(txtArticleCode.getText(),txtDescription.getText(),
		txtUnitCode.getText(),comboType.getValue(),Float.valueOf(txtArticlePrice.getText()),Integer.valueOf(txtArticleQuantity.getText()));
	    ArticleProxy.addArticle(article);
	
    }

    @FXML
    private void OnTabArticleTreeSelected(Event event) throws NamingException {
    fillTreeTableView("all");
    	
     	
    }

    @FXML
    private void OntabAddNewRticleSelected(Event event) {
    	
    }

    @FXML
    private void OnAddChildAction(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	TreeItem<Article> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	
    	
    	aArticleProxy.addNomenclature(selectedItem.getValue().getId(),aArticleProxy.findArticleByCode(txtArticleChild.getText()).getId(),Integer.parseInt(txtChildQuantity.getText()));
    	
  
    }

    @FXML
    private void itemSelected(MouseEvent event) {
    	BtnAddArticleChild.setDisable(false);
    }
    
    
    
    
private void fillTreeTableView(String code) throws NamingException {
	
	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
	Context context = new InitialContext();
	 ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
	 List<Nomenclature> listNomenclature=ArticleProxy.getFilsArticles(1);
	 TreeItem<Article> root=new TreeItem<>();
	// System.out.println(listNomenclature.get(0).getArticleFils().getDescription());
	 List<Article> produitFini;
 if(code.equals("all")) {
	produitFini=ArticleProxy.getArticlesByType("Produit-Fini");
 }else {
	produitFini=ArticleProxy.findArticleByCodeAndType(code,"Produit-Fini");
 }
 
 	TreeItem<Article> newItemarticlePere;
 	TreeItem<Article> newItemarticleFils=null;
 for(int i=0;i<produitFini.size();i++) {
	 List<Article> articlePere=produitFini;
	 
	 newItemarticlePere=new TreeItem<>(articlePere.get(i));
	 root.getChildren().add(newItemarticlePere);
	 List<Nomenclature> filsList;
	 int size;
	 do{
	 filsList= ArticleProxy.getFilsArticles(articlePere.get(i).getId());
	 size=filsList.size();
	 
	 for(int j=0;j<filsList.size();j++) {
		 newItemarticleFils=new TreeItem<>(filsList.get(j).getArticleFils());
		 newItemarticlePere.getChildren().add(newItemarticleFils);
		 
	 }
	 
	 newItemarticlePere=newItemarticleFils;
	 size--;
	 } while(size>0);
	
 }
 	

     articleColumn.setCellValueFactory((CellDataFeatures<Article, String> param) -> new SimpleStringProperty(param.getValue().getValue().getDescription()));
 	quantityColumn.setCellValueFactory((CellDataFeatures<Article, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getQuantity())));
 	ArticleTableView.setRoot(root);
 	ArticleTableView.setShowRoot(false);
 
}

private void fillTableView(String code) throws NamingException {
	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
	Context context = new InitialContext();
	 ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
 	  tArticleCode.setCellValueFactory(new PropertyValueFactory<>("ArticleCode"));
      tArticleDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
      tArticlePrice.setCellValueFactory(new PropertyValueFactory<>("Pmp"));
      tArticleQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
      tArticleType.setCellValueFactory(new PropertyValueFactory<>("Type"));
      tUnitCode.setCellValueFactory(new PropertyValueFactory<>("UnitCode"));
	ObservableList<Article> ObsListArticles;
	if(code.equals("all")) {
		ObsListArticles = FXCollections.observableArrayList(ArticleProxy.getAllArticles());
	}
	else {
		ObsListArticles = FXCollections.observableArrayList(ArticleProxy.getArticleListByCode(code));
	}
	
	tableAllArticles.setItems(ObsListArticles);
	tableAllArticles.refresh();
      
      
    
}

    @FXML
    private void SearchArticleAction(KeyEvent event) throws NamingException {
    	fillTreeTableView(txtArticleSearch.getText());
    }

    @FXML
    private void SearchArticleFromTableAction(KeyEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	System.out.println(txtArticleSearchFromTable.getText());
    	ObservableList<Article> ObsListArticles;

    		ObsListArticles = FXCollections.observableArrayList(aArticleProxy.getArticleListByCode(txtArticleSearchFromTable.getText()));
    		tableAllArticles.setItems(ObsListArticles);
    	    tableAllArticles.refresh();
          
    	
    }

    @FXML
    private void OnDeleteArticleClicked(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	Alert alert=new Alert(AlertType.CONFIRMATION);
    	if(article == null) {
    		alert.setTitle("Erreur");
    		return;
    	}
    	
    	alert.setTitle("Deleting Article");
    	alert.setContentText("Are you sure want to delete the article:"+article.getDescription());
    	java.util.Optional<javafx.scene.control.ButtonType> answer=alert.showAndWait();
    	if(answer.get()==javafx.scene.control.ButtonType.OK) {
    		aArticleProxy.DeleteArticle(article.getId());
          fillTableView("all");
    	}
    	
    }

    @FXML
    private void OnMenuUpdateArticleClicked(ActionEvent event) {
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	txtArticleQuantityUpdate.setDisable(false);
    	txtArticleQuantityUpdate.setText(String.valueOf(article.getQuantity()));
    	txtArticleCodeUpdate.setDisable(false);
    	txtArticleCodeUpdate.setText(article.getArticleCode());
    	comboTypeUpdate.setDisable(false);
    	
    	comboTypeUpdate.setValue(article.getType());
    	txtArticlePriceUpdate.setDisable(false);
    	txtArticlePriceUpdate.setText(String.valueOf(article.getPmp()));
    	txtUnitCodeUpdate.setDisable(false);
    	txtUnitCodeUpdate.setText(article.getUnitCode());
    	txtDescriptionUpdate.setDisable(false);
    	txtDescriptionUpdate.setText(article.getDescription());
    	BtnAddArticleUpdate.setDisable(false);
   
   }

    @FXML
    private void OnButtonUpdateArticleClicked(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    			Article article=new Article(txtArticleCodeUpdate.getText(), 
    			txtDescriptionUpdate.getText(),txtUnitCodeUpdate.getText(),comboTypeUpdate.getValue().toString(),
    			Float.valueOf(txtArticlePriceUpdate.getText()),Integer.valueOf(txtArticleQuantityUpdate.getText()));
    			System.out.println(article.getArticleCode());
    			aArticleProxy.updateArticle(article);
    	fillTableView("all");
    
    	txtArticleQuantityUpdate.setDisable(true);
    	txtArticleQuantityUpdate.setText("");
    	txtArticleCodeUpdate.setDisable(true);
    	txtArticleCodeUpdate.setText("");
    	comboTypeUpdate.setDisable(true);
    	
    	comboTypeUpdate.setValue("");
    	txtArticlePriceUpdate.setDisable(true);
    	txtArticlePriceUpdate.setText("");
    	txtUnitCodeUpdate.setDisable(true);
    	txtUnitCodeUpdate.setText("");
    	txtDescriptionUpdate.setDisable(true);
    	txtDescriptionUpdate.setText("");
    	BtnAddArticleUpdate.setDisable(true);
    	
    	
    	
    }

  
    

  

    
}
