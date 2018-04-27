/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.ImageView;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
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
    private TreeTableView<Nomenclature> ArticleTableView;
    @FXML
    private TreeTableColumn<Nomenclature,String> articleColumn;
    @FXML
    private TreeTableColumn<Nomenclature,String> quantityColumn;
    
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
    @FXML
    private StackPane stackPaneADD;
    @FXML
    private StackPane stackPaneAddChild;
    @FXML
    private AreaChart<Integer,String> ArticleChart;
    @FXML
    private CategoryAxis axeX;
    @FXML
    private NumberAxis axeY;
    @FXML
    private JFXTextField txtSearchArticleForChart;
    @FXML
    private Label requestDate;  
    
    @FXML
    private ImageView reloadImg;
    
    
    
    
  
    
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    
    	
    	try {
    		
    		fillTableView("all");
    		
            
    		
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
    private void OnSearchArticleForChart(KeyEvent event) throws NamingException {
    	ArticleChart.setTitle("Order Procurement");
   
     
    	
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	List<Article> listArticle=aArticleProxy.findArticleByCodeAndType(txtSearchArticleForChart.getText(), "Matiére-Premiére");
    	if(listArticle.size()==1){
    		int joursRestant=listArticle.get(0).getQuantity()/listArticle.get(0).getDailyConsumption();
    		Date dateT=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
        	Date AlarmDate=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
        	
        	Date currentDate =java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());


        	int day= dateT.getDate();
        	dateT.setDate(day+joursRestant-listArticle.get(0).getDeliveryTime());
        	int diff=currentDate.compareTo(dateT);
       
        	 
        	XYChart.Series series = new XYChart.Series();
        	series.getData().add(new XYChart.Data(currentDate.toString(),listArticle.get(0).getQuantity()));
      
        	int Quantity=listArticle.get(0).getQuantity();
        	
        	for(int i=0;i<joursRestant;i++) {
        		
            	
        		int days=currentDate.getDate();
        		Quantity=Quantity-listArticle.get(0).getDailyConsumption();
        		currentDate.setDate(days+1);
        		
                series.getData().add(new XYChart.Data(currentDate.toString(),Quantity));
                
       
              
                
                
        	}
        	series.setName(listArticle.get(0).getArticleCode());
        	
        	  ArticleChart.getData().add(series);
        	  
        	
         
    		
    	}
    	   
    	
    }
    
    @FXML
    private void OnAddAction(ActionEvent event) throws NamingException {
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	JFXDialogLayout content=new JFXDialogLayout();
    	aArticleProxy.findArticleByCode(txtArticleCode.getText());
             
              if(txtArticleCode.getText().equals("")){
    		content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("wrong Article Code"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
    	}
              else if(aArticleProxy.findArticleByCode(txtArticleCode.getText()).size()!=0){
          		content.setHeading(new Text("                                              Error"));
          		content.setBody(new Text("Article already existed"));
          		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
          		jfxDialog.show();
          	}
    	else if(txtUnitCode.getText().equals("")){
    		content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("wrong Unit Code"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
    	}
    	else if(txtDescription.getText().equals("")){
    		content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("wrong Description"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
    	}
    	
    	else if(!comboType.isManaged()){
    		content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("wrong Type"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
    	}
    	else  if(!txtArticleQuantity.getText().matches("[\\p{Digit}&&[123456789]]+")) {
       	 content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("Please enter a number in Quantity field"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
        }
        else if(!txtArticlePrice.getText().matches("[-+]?[0-9]*\\.?[0-9]+")) {
       	 content.setHeading(new Text("                                              Error"));
    		content.setBody(new Text("Please enter a float in Price field"));
    		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
    		jfxDialog.show();
        }
    	else {
    		
    	
		Article article=new Article(txtArticleCode.getText(),txtDescription.getText(),
		txtUnitCode.getText(),comboType.getValue(),Float.valueOf(txtArticlePrice.getText()),Integer.valueOf(txtArticleQuantity.getText()));
		int idFils=aArticleProxy.addArticle(article);
		article.setType("Produit-Pere");
		int idPere=aArticleProxy.addArticle(article);
		aArticleProxy.addNomenclature(idPere, idFils, 1);
		
		
		
		
		content.setHeading(new Text("                                       Well Done \n"));
		content.setBody(new Text("This article has been successfully added"));
		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
		jfxDialog.show();
    	}
	 
    }
    @FXML
    private void OnDeleteFromTreeTableView() throws NamingException {
    	TreeItem<Nomenclature> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
     	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
    	aArticleProxy.DeleteNomenclature(selectedItem.getValue());
    	fillTreeTableView("all");
    	
    	
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
    	TreeItem<Nomenclature> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	JFXDialogLayout content=new JFXDialogLayout();
    	if(!txtChildQuantity.getText().matches("[\\p{Digit}&&[123456789]]+")) {
          	 content.setHeading(new Text("                                              Error"));
       		content.setBody(new Text("Please enter a number in Quantity field"));
       		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
       		jfxDialog.show();
           }
    	else {
    		
    	
    	aArticleProxy.addNomenclature(selectedItem.getValue().getArticleFils().getId(),aArticleProxy.getArticleListByCode(txtArticleChild.getText()).get(0).getId(),Integer.parseInt(txtChildQuantity.getText()));

		
		content.setHeading(new Text("                                       Well Done \n"));
		
		content.setBody(new Text("This Child has been successfully added"));
		
		JFXButton button=new JFXButton("close");
		JFXDialog jfxDialog=new JFXDialog(stackPaneAddChild,content,JFXDialog.DialogTransition.CENTER);
		button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				jfxDialog.close();
				
			}
		});
    	fillTreeTableView("all");
    	txtChildQuantity.setDisable(true);
    	txtArticleChild.setDisable(true);
    	BtnAddArticleChild.setDisable(true);
		jfxDialog.show();
    	
    	}
    }

    @FXML
    private void itemSelected(MouseEvent event) throws NamingException {
    	txtArticleChild.setText("");
    TreeItem<Nomenclature> selectedItem = ArticleTableView.getSelectionModel().getSelectedItem();
    	if(!selectedItem.getValue().getArticleFils().getType().equals("Matiére-Premiére")) {
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
	 TreeItem<Nomenclature> root=new TreeItem<>();
	 List<Nomenclature> listNomenclature;
	 List<Nomenclature> produitFini =  new ArrayList<Nomenclature>();
	 listNomenclature=ArticleProxy.getAllFinalArticleNomenclature();
 if(code.equals("all")) {
	
	 
for(int i=0;i<listNomenclature.size();i++) {
	if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")) {
		produitFini.add(listNomenclature.get(i));
		
	}
}

 }else {
	 for(int i=0;i<listNomenclature.size();i++) {
			if(listNomenclature.get(i).getArticlePere().getType().equals("Produit-Pere")&&listNomenclature.get(i).getArticleFils().getType().equals("Produit-Fini")&&listNomenclature.get(i).getArticleFils().getArticleCode().contains(code)) {
				produitFini.add(listNomenclature.get(i));
				
			}
		}
	//produitFini=ArticleProxy.findArticleByCodeAndType(code,"Produit-Fini");
 }
 
 	TreeItem<Nomenclature> newItemarticlePere;
 	TreeItem<Nomenclature> newItemarticleFils=null;
 for(int i=0;i<produitFini.size();i++) {
	 List<Nomenclature> articlePere=produitFini;
	 
	 newItemarticlePere=new TreeItem<>(articlePere.get(i));
	 root.getChildren().add(newItemarticlePere);
	 
	
	
	 
	 ArrayDeque <TreeItem<Nomenclature>> queue=new ArrayDeque<>();
	 queue.add(newItemarticlePere);
	 
	
	 while(!queue.isEmpty()) {
		 
	
		TreeItem<Nomenclature> TreeItemHead=queue.getFirst();
		 queue.removeFirst();
		
		 List<Nomenclature> listNomenclatureFils=ArticleProxy.getFilsArticles(TreeItemHead.getValue().getArticleFils().getId());
		 for(int j=0;j<listNomenclatureFils.size();j++) {
			 newItemarticleFils=new TreeItem<>(listNomenclatureFils.get(j));
			 TreeItemHead.getChildren().add(newItemarticleFils);
			 queue.addLast(newItemarticleFils);
			 
	
	
		 }
	 }
	 
	 
	
 }
 	

    articleColumn.setCellValueFactory((CellDataFeatures<Nomenclature, String> param) -> new SimpleStringProperty(param.getValue().getValue().getArticleFils().getArticleCode()));
 	quantityColumn.setCellValueFactory((CellDataFeatures<Nomenclature, String> param) -> new SimpleStringProperty(String.valueOf(param.getValue().getValue().getQuantity())));
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
		List<Article> list = ArticleProxy.getAllArticles();
		for(int i=0;i<list.size()-1;i++) {
			for(int j=0;j<list.size();j++) {
				if(i!=j&&list.get(i).getArticleCode().equals(list.get(j).getArticleCode())) {
					list.remove(list.get(j));
				}
			}
		}
		
		ObsListArticles = FXCollections.observableArrayList(list);
	}
	else {
		List<Article> listSearch = ArticleProxy.getArticleListByCode(code);
		for(int i=0;i<listSearch.size()-1;i++) {
			for(int j=0;j<listSearch.size();j++) {
				if(i!=j&&listSearch.get(i).getArticleCode().equals(listSearch.get(j).getArticleCode())) {
					listSearch.remove(listSearch.get(j));
				}
			}
		}
		ObsListArticles = FXCollections.observableArrayList(listSearch);
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
    	
    	ObservableList<Article> ObsListArticles;

    		//ObsListArticles = FXCollections.observableArrayList(aArticleProxy.getArticleListByCode(txtArticleSearchFromTable.getText()));
    		fillTableView(txtArticleSearchFromTable.getText());
    		//tableAllArticles.setItems(ObsListArticles);
    	    //tableAllArticles.refresh();
          
    	
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
    	// LocalDate  localDateAlarm = AlarmDate.getValue();
    	//Date AlarmTypeDate = java.sql.Date.valueOf(localDateAlarm);
    			 
    	
    			 
    	LocalDate  localDateRequest = RequestDate.getValue();
    	 Date RequestTypeDate = java.sql.Date.valueOf(localDateRequest);
    	 Alert alert = new Alert(Alert.AlertType.WARNING);
        java.util.Date CurrentDate=new java.util.Date();
    	/* if(AlarmTypeDate.compareTo(CurrentDate)<=0) {
    		
             alert.setTitle("Wrong Date");
             alert.setHeaderText("Alarm Date is wrong");
            alert.showAndWait();
            
    	 }else*/ if(RequestTypeDate.compareTo(CurrentDate)<=0){
    	
    		 alert.setTitle("Wrong Date");
             alert.setHeaderText("Request Date is wrong");
            alert.showAndWait();
    	 }else {
    		 Date AlarmTypeDate=java.sql.Date.valueOf(localDateRequest);
    		
    		 AlarmTypeDate.setDate(RequestTypeDate.getDate()+article.getDeliveryTime()+1);
    		 AlarmTypeDate.setMonth(RequestTypeDate.getMonth());
    		 AlarmTypeDate.setYear(RequestTypeDate.getYear());
    		 article.setEtatOrdre(1);
    		 aArticleProxy.updateArticle(article);
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
		txtQuantityForOrder.setDisable(true);
		RequestDate.setDisable(true);
		//requestDate.setDisable(true);
    	
    	if(aArticleProxy.findArticleByCodeAndType(txtArticleCodeForOrder.getText(),"Matiére-Premiére").size()==1) {

    	
    		BtnAddOrder.setDisable(false); 
    		txtQuantityForOrder.setDisable(false);
    		RequestDate.setDisable(false);
    		//requestDate.setDisable(false);
    	
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
