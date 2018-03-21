/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;
import java.net.URL;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.util.Callback;
import javassist.expr.NewArray;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
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
    private JFXTreeView<Article> ArticleTreeView;
    @FXML
    private TreeTableView<Article> ArticleTableView;
    @FXML
    private TreeTableColumn<Article,String> articleColumn;
    @FXML
    private TreeTableColumn<Article,String> quantityColumn;
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	comboType.getItems().addAll("Matiére Premiére","Produit Semi Fini","Produit Fini");
    	Article article1=new Article();
    	article1.setDescription("article1");
    	article1.setQuantity(10);
    	Article article2=new Article();
    	article2.setDescription("article2");
    	article2.setQuantity(20);
    	TreeItem<Article> Itemarticle1=new TreeItem<>(article1);
    	TreeItem<Article> Itemarticle2=new TreeItem<>(article2);
    	TreeItem<Article> root=new TreeItem<>(article1);
    
    	root.getChildren().setAll(Itemarticle1,Itemarticle2);
    	Itemarticle1.getChildren().addAll(Itemarticle2);
    	
 
        articleColumn.setCellValueFactory((CellDataFeatures<Article, String> param) -> new SimpleStringProperty(param.getValue().getValue().getDescription()));
    	quantityColumn.setCellValueFactory((CellDataFeatures<Article, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getQuantity())));
    	ArticleTableView.setRoot(root);
    	ArticleTableView.setShowRoot(false);
    	
    	
    	/*TreeItem<String>articlePere2,articleFils1,articleFils2;
    	TreeItem<Article> root,articlePere1;
        
   
    	articlePere2=new TreeItem<String>("articlePere2");
    	articleFils1=new TreeItem<String>("articleFils1");
    	articleFils2=new TreeItem<String>("articleFils2");
    	root=new TreeItem<Article>();
    	articlePere1=new TreeItem<Article>();
        ArticleTreeView.setRoot(root);
    	root.setExpanded(true);
    	root.getChildren().add(articlePere1);
    	articlePere1.getChildren().add(articleFils1);
    	articlePere2.getChildren().add(articleFils2);
    	
    	ArticleTreeView=new JFXTreeView<>(root);
    	ArticleTreeView.setShowRoot(false);*/
    	
    	
    }    

    @FXML
    private void OnAddAction(ActionEvent event) throws NamingException {
    	
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		Article article=new Article(txtArticleCode.getText(),txtDescription.getText(),
				txtUnitCode.getText(),comboType.getValue(),Float.valueOf(txtArticlePrice.getText()),Integer.valueOf(txtArticleQuantity.getText()));
	    ArticleProxy.addArticle(article);
	
    }

    
}
