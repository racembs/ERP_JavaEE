/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.itextpdf.text.Document;


import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import javax.mail.MessagingException;
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
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
    @FXML
    private JFXButton BtnConfirmOrder;
    @FXML
    private DatePicker receptionDate;
    @FXML
    private MenuItem generatePdf;
    
    

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
	
	  tArticle.setCellFactory(column -> {
  	    return new TableCell<MvtApprov, String>() {
  	        @Override
  	        protected void updateItem(String item, boolean empty) {
  	            super.updateItem(item, empty);

  	            if (item == null || empty) {
  	                setText(null);
  	                setStyle("");
  	            } else {
  	            
  	            	setText(item);
  	            	//setTextFill(Color.WHITE);
  	               // setStyle("-fx-font-weight: bold");
  	                    
  	               
  	            }
  	        }
  	    };
  	});
	
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
		//ObsListOrders = FXCollections.observableArrayList(OrdreProxy.getOrderByArticle(Integer.valueOf(code)));
		System.out.println(OrdreProxy.getOrderByArticle(Integer.valueOf(code)).getQuantity());
	}
	
	tableAllOrders.setItems(ObsListOrders);
	tableAllOrders.refresh();
     
     

	
}
 


    @FXML
    private void SearchOrdreFromTableAction(KeyEvent event) throws NamingException {
    //txtSearchOrder.getText());
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		Article article =ArticleProxy.findArticleByCode(txtSearchOrder.getText()).get(0);
		fillTableAllOrdres(String.valueOf(article.getId()));
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
    @FXML
    private void OnConfirmOrderClicked(ActionEvent event) {
    	receptionDate.setDisable(false);
    	BtnConfirmOrder.setDisable(false);
    	receptionDate.setOpacity(1);
    	BtnConfirmOrder.setOpacity(1);
    }
    
    @FXML
    private void OnGeneratePdfClicked(ActionEvent event) {
    	MvtApprov mvtApprov=tableAllOrders.getSelectionModel().getSelectedItem();
    	  FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Save Copy of Report");
          fileChooser.setInitialDirectory(
                  new File(System.getProperty("user.home"))
          );
          fileChooser.setInitialFileName("Report"+mvtApprov.getArticle().getArticleCode()+"_"+mvtApprov.getRequestDate()+".pdf");
          FileChooser.ExtensionFilter pdfExtensionFilter =
                  new FileChooser.ExtensionFilter(
                          "PDF - Portable Document Format (.pdf)", "*.pdf");
          fileChooser.getExtensionFilters().add(pdfExtensionFilter);
          fileChooser.setSelectedExtensionFilter(pdfExtensionFilter);
          File file = fileChooser.showSaveDialog(txtSearchOrder.getContextMenu());

          if (file != null) {
              try {
            	  genereteReportPdf(mvtApprov,file.getPath());
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
      }
    
    @FXML
    private void OnConfirmBtnAction(ActionEvent event) throws NamingException {

    	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
    	Context context1 = new InitialContext();
    	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
    	
    	String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    	Context context2 = new InitialContext();
    	ArticleServiceRemote aArticleProxy = (ArticleServiceRemote) context2.lookup(ArticlejndiName);
    	
    	
    	MvtApprov mvtApprov=tableAllOrders.getSelectionModel().getSelectedItem();
    	LocalDate  localDateRequest = receptionDate.getValue();
    	Alert alert = new Alert(Alert.AlertType.WARNING);
    	if(localDateRequest==null) {
    		 alert.setTitle("Wrong Date");
             alert.setHeaderText("You should choose a date");
            alert.showAndWait();
    	}else {
   	 Date RecptionTypeDate = java.sql.Date.valueOf(localDateRequest);
   	 
    java.util.Date CurrentDate=new java.util.Date();
    
	 if(RecptionTypeDate.compareTo(CurrentDate)>=0) {
		 alert.setTitle("Wrong Date");
         alert.setHeaderText("Reception Date is wrong");
        alert.showAndWait();
	 }else {
			mvtApprov.setReceptionDate(RecptionTypeDate);
	    	
	    	OrdreProxy.updateMvtApprov(mvtApprov);
	    	fillTableAllOrdres("all");
	    	receptionDate.setDisable(true);
	    	BtnConfirmOrder.setDisable(true);
	    	receptionDate.setValue(null);
	    	receptionDate.setOpacity(0.5);
	    	BtnConfirmOrder.setOpacity(0.5);
	    	aArticleProxy.incrementArticleQuantity(mvtApprov.getArticle().getId(), mvtApprov.getQuantity());
	 }
    	}
    
    	
    }
    
    private void genereteReportPdf(MvtApprov order ,String path) throws IOException, DocumentException, MessagingException {
    	Document document = new Document();

PdfWriter.getInstance(document, new FileOutputStream(path));

document.open();

document.add(new Paragraph("\n--------------------  Product Request  --------------------------\n\n"
    + " Article Code :"+order.getArticle().getArticleCode()
    + "\n Quantity needed  : "+order.getQuantity()
    +"\n Company : SpotLight GPAO"
    + "\n\n\n"
    + "\n Date :"+new java.util.Date()+"\n\n"
    + " Cordially "
));

document.addTitle("title");
document.close();



Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("information");
alert.setHeaderText(null);
alert.setContentText("The report is successfully generated");
alert.show();


}

    
}
   