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
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    	try {
    		fillTreeTableView("all");
    		fillTableView("all");
		} catch (NamingException e) {
			}
    	
      
          
comboType.getItems().addAll("Matiére-Premiére","Produit-Semi-Fini","Produit-Fini");
    	Article article1=new Article();
    	article1.setDescription("article1");
    	article1.setQuantity(10);
    	Article article2=new Article();
    	article2.setDescription("article2");
    	article2.setQuantity(20);
    
    	
    	
    }    

    @FXML
    private void OnAddAction(ActionEvent event) throws NamingException {
    
        
    	
		Article article=new Article(txtArticleCode.getText(),txtDescription.getText(),
		txtUnitCode.getText(),comboType.getValue(),Float.valueOf(txtArticlePrice.getText()),Integer.valueOf(txtArticleQuantity.getText()));
	    ArticleProxy.addArticle(article);
	
    }

    @FXML
    private void OnTabArticleTreeSelected(Event event) throws NamingException {
    
    	
     	
    }

    @FXML
    private void OntabAddNewRticleSelected(Event event) {
    	
    }

    @FXML
    private void OnAddChildAction(ActionEvent event) {
    	TreeItem<Article> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	
    	
    	ArticleProxy.addNomenclature(selectedItem.getValue().getId(),ArticleProxy.findArticleByCode(txtArticleChild.getText()).getId(),Integer.parseInt(txtChildQuantity.getText()));
    	
  
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

private void fillTableView(String code) {
	ObservableList<Article> ObsListArticles;
	if(code.equals("all")) {
		ObsListArticles = FXCollections.observableArrayList(ArticleProxy.getAllArticles());
	}
	else {
		ObsListArticles = FXCollections.observableArrayList(ArticleProxy.findArticleByCodeAndType(code,"Produit-Fini"));
	}
	
	tableAllArticles.setItems(ObsListArticles);
      
      
      tArticleCode.setCellValueFactory(new PropertyValueFactory<>("ArticleCode"));
      tArticleDescription.setCellValueFactory(new PropertyValueFactory<>("Description"));
      tArticlePrice.setCellValueFactory(new PropertyValueFactory<>("Pmp"));
      tArticleQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
      tArticleType.setCellValueFactory(new PropertyValueFactory<>("Type"));
      tUnitCode.setCellValueFactory(new PropertyValueFactory<>("UnitCode"));
}

    @FXML
    private void SearchArticleAction(KeyEvent event) throws NamingException {
    	fillTreeTableView(txtArticleSearch.getText());
    }

    @FXML
    private void SearchArticleFromTableAction(KeyEvent event) {
    	fillTableView(txtArticleSearchFromTable.getText());
    }

  
    

  

    
}
