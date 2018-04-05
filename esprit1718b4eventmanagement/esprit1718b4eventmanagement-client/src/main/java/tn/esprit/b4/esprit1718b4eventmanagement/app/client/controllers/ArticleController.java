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
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayDeque;

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
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.util.converter.NumberStringConverter;
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
    private TableColumn<Article,Number> tArticlePrice;
    @FXML
    private TableColumn<Article,String> tArticleCode;
    @FXML
    private TableColumn<Article,String> tArticleDescription;
    @FXML
    private TableColumn<Article,Number> tArticleQuantity;
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
    @FXML
    private Tab tabProcuremrnt;
    @FXML
    private JFXButton BtnAddOrder;
    @FXML
    private DatePicker AlarmDate;
    @FXML
    private JFXTextField txtArticleCodeForOrder;
    @FXML
    private JFXTextField txtQuantityForOrder;
  
    @FXML
    private DatePicker RequestDate;
    @FXML
    private Tab tabAddNewOrder;
    @FXML
    private Tab tabOrdreSettings;
    @FXML
    private JFXTextField txtDailyConsuption;
    @FXML
    private JFXTextField txtSearchArticleAutoOrdre;
    @FXML
    private JFXTextField txtDeliveryTime;
    @FXML
    private JFXButton BtnAutoOrdreUpdate;
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    	
    	try {
    		fillTreeTableView("all");
    		fillTableView("all");
    		//AuoOrderCreation();
    		
		} catch (NamingException e) {
			}
    	tableAllArticles.setEditable(true);
    	tArticleDescription.setCellFactory(TextFieldTableCell.forTableColumn());
    	//tArticleCode.setCellFactory(TextFieldTableCell.forTableColumn());
    	tArticlePrice.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
    	tArticleQuantity.setCellFactory(TextFieldTableCell.forTableColumn(new NumberStringConverter()));
    	tArticleType.setCellFactory(TextFieldTableCell.forTableColumn());
    	tUnitCode.setCellFactory(TextFieldTableCell.forTableColumn());
 

comboType.getItems().addAll("Matiére-Premiére","Produit-Semi-Fini","Produit-Fini");
comboTypeUpdate.getItems().addAll("Matiére-Premiére","Produit-Semi-Fini","Produit-Fini");

    	
    }    

    @FXML
    private void OnAddAction(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
        
    	
		Article article=new Article(txtArticleCode.getText(),txtDescription.getText(),
		txtUnitCode.getText(),comboType.getValue(),Float.valueOf(txtArticlePrice.getText()),Integer.valueOf(txtArticleQuantity.getText()));
		aArticleProxy.addArticle(article);
	
    }

    @FXML
    private void OnTabArticleTreeSelected(Event event) throws NamingException {
  
    fillTreeTableView("all");	
    
    	
     	
    }
    @FXML
    private void OnTabAllArticleSelected(Event event) throws NamingException {
    	fillTableView("all");
    }


   

    @FXML
    private void OnAddChildAction(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	TreeItem<Article> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	
    	
    	
    	aArticleProxy.addNomenclature(selectedItem.getValue().getId(),aArticleProxy.getArticleListByCode(txtArticleChild.getText()).get(0).getId(),Integer.parseInt(txtChildQuantity.getText()));
    	fillTreeTableView("all");
    	
  
    }

    @FXML
    private void itemSelected(MouseEvent event) throws NamingException {
    	txtArticleChild.setText("");
    	TreeItem<Article> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	if(!selectedItem.getValue().getType().equals("Matiére-Premiére")) {
    		txtArticleChild.setDisable(false);
    		
    		
    	}else {
    		txtArticleChild.setDisable(true);
    		txtChildQuantity.setDisable(true);
    		BtnAddArticleChild.setDisable(true);
    		
    	}
   	   
    }
    
    
    
    
private void fillTreeTableView(String code) throws NamingException {
	
	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
	Context context = new InitialContext();
	 ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
	 TreeItem<Article> root=new TreeItem<>();
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
	 
	
	
	 
	 ArrayDeque <TreeItem<Article>> queue=new ArrayDeque<>();
	 queue.add(newItemarticlePere);
	
	 while(!queue.isEmpty()) {
		 
	
		TreeItem<Article> TreeItemHead=queue.getFirst();
		 queue.removeFirst();
		
		 List<Nomenclature> listNomenclatureFils=ArticleProxy.getFilsArticles(TreeItemHead.getValue().getId());
		 for(int j=0;j<listNomenclatureFils.size();j++) {
			 newItemarticleFils=new TreeItem<>(listNomenclatureFils.get(j).getArticleFils());
			 TreeItemHead.getChildren().add(newItemarticleFils);
			 queue.addLast(newItemarticleFils);
			 
	
	
		 }
	 }
	 
	 
	
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
    	alert.setContentText("Are you sure want to delete the "+article.getDescription());
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

  
    
    @FXML
    private void OnSearchNewChildForTreeReleased(KeyEvent event) throws NamingException {
    	BtnAddArticleChild.setDisable(true);
		txtChildQuantity.setDisable(true);
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=aArticleProxy.findArticleByCode(txtArticleChild.getText()).get(0);
    	if(article!=null&&!article.getType().equals("Produit-Fini")) {
    		BtnAddArticleChild.setDisable(false);
    		txtChildQuantity.setDisable(false);
    	}
    	
    }
 
    
////////////////////////////////////////////////////////****Update From Table****/////////////////////////////////////
    
    
    @FXML
    private void ChangeDescriptionFromTable(TableColumn.CellEditEvent<Article,String> event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setDescription(event.getNewValue().toString());
    	System.out.println(event.getNewValue().toString());
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }

    @FXML
    private void ChangeArticleCodeFromTable(TableColumn.CellEditEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setArticleCode(event.getNewValue().toString());
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }

    @FXML
    private void ChangePriceFromTable(TableColumn.CellEditEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setPmp(Float.valueOf(event.getNewValue().toString()));
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }

    @FXML
    private void ChangeQuantityFromTable(TableColumn.CellEditEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setQuantity(Integer.valueOf(event.getNewValue().toString()));
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }

    @FXML
    private void ChangeTypeFromTable(TableColumn.CellEditEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setType(event.getNewValue().toString());
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }

    @FXML
    private void ChangeUnitCodeFromTable(TableColumn.CellEditEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	Article article=tableAllArticles.getSelectionModel().getSelectedItem();
    	article.setUnitCode(event.getNewValue().toString());
    	aArticleProxy.updateArticle(article);
    	fillTableView("all");
    }
    
////////////////////////////////////////////////////////****Procrutment****///////////////////////////////////////////
   

  

    @FXML
    private void OnAddOrdreClicked(ActionEvent event) throws NamingException, ParseException {
    	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
    	Context context1 = new InitialContext();
    	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
    	
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	
    	
    	 Article article=aArticleProxy.getArticleListByCode(txtArticleCodeForOrder.getText()).get(0);
    	 LocalDate  localDateAlarm = AlarmDate.getValue();
    	 Date AlarmTypeDate = java.sql.Date.valueOf(localDateAlarm);
    			 
    	
    			 
    	LocalDate  localDateRequest = RequestDate.getValue();
    	 Date RequestTypeDate = java.sql.Date.valueOf(localDateRequest);
    	 Alert alert = new Alert(Alert.AlertType.WARNING);
        java.util.Date CurrentDate=new java.util.Date();
    	 if(AlarmTypeDate.compareTo(CurrentDate)<=0) {
    		
             alert.setTitle("Wrong Date");
             alert.setHeaderText("Alarm Date is wrong");
            alert.showAndWait();
            
    	 }else if(RequestTypeDate.compareTo(CurrentDate)<=0){
    	
    		 alert.setTitle("Wrong Date");
             alert.setHeaderText("Request Date is wrong");
            alert.showAndWait();
    	 }else {
    		 MvtApprov mvtApprov=new MvtApprov(article,null,Integer.valueOf(txtQuantityForOrder.getText()),AlarmTypeDate,RequestTypeDate,null);
             OrdreProxy.addMvtApprov(mvtApprov);
    	 }
      
   
       
        
    }

    @FXML
    private void OnSearchArticleAutoOrderReleased(KeyEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	BtnAutoOrdreUpdate.setDisable(true);
		txtDailyConsuption.setDisable(true);
    	txtDeliveryTime.setDisable(true);
    	txtDailyConsuption.setText("");
    	txtDeliveryTime.setText("");
    	Article article=aArticleProxy.findArticleByCode(txtSearchArticleAutoOrdre.getText()).get(0);
    	
    	if(article!=null&&article.getType().equals("Matiére-Premiére")) {
    		BtnAutoOrdreUpdate.setDisable(false);
    		txtDailyConsuption.setDisable(false);
        	txtDeliveryTime.setDisable(false);
        	txtDailyConsuption.setText(String.valueOf(article.getDailyConsumption()));
        	txtDeliveryTime.setText(String.valueOf(article.getDeliveryTime()));
        	
    	}
    	
    	
    }
    

    @FXML
    private void OnSearchAddOrderReleased(KeyEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	BtnAddOrder.setDisable(true); 
    	Article article=aArticleProxy.findArticleByCode(txtArticleCodeForOrder.getText()).get(0);
    	
    	if(article!=null&&article.getType().equals("Matiére-Premiére")) {

    	
    		BtnAddOrder.setDisable(false);  
    	
    	}
    	
    	
    }

    @FXML
    private void OnChangeAutoSettingsClicked(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	Article article=aArticleProxy.getArticleListByCode(txtSearchArticleAutoOrdre.getText()).get(0);
    	article.setDeliveryTime(Integer.valueOf(txtDeliveryTime.getText()));
    	article.setDailyConsumption(Integer.valueOf(txtDailyConsuption.getText()));
    	aArticleProxy.updateArticle(article);
    	
    	
    }

   
//private void AuoOrderCreation() throws NamingException {
//	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
//	Context context2 = new InitialContext();
//	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
//	
//	List<Article> articles=aArticleProxy.getAllArticles();
//	
//	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
//	Context context1 = new InitialContext();
//	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
//	
//	
//	for(int i=0;i<articles.size();i++) {
//	int joursRestant=articles.get(i).getQuantity()/articles.get(i).getDailyConsumption();
//	 
//	
//	Date dateT=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
//	Date currentDate =java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
//
//
//	int day= dateT.getDate();
//
//	dateT.setDate(day+joursRestant-articles.get(i).getDeliveryTime());
//	if(dateT.compareTo(currentDate)<=0 && articles.get(i).getEtatOrdre()==0) {
//		MvtApprov mvtApprov=new MvtApprov(articles.get(i),null,articles.get(i).getPricipalQuantity()-articles.get(i).getQuantity(),null,dateT,null);
//		OrdreProxy.addMvtApprov(mvtApprov);
//		articles.get(i).setEtatOrdre(1);
//		aArticleProxy.updateArticle(articles.get(i));
//	}
//	
//	
//	
//	
//	}
//}



   
    
}
