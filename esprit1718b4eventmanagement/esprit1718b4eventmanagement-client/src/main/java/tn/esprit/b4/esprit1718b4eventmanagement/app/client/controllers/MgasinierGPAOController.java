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
import com.jfoenix.controls.JFXCheckBox;
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
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.mail.MessagingException;
import javax.management.Notification;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sound.midi.Soundbank;

import org.controlsfx.control.Notifications;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.util.Callback;
import javafx.util.Duration;
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
    private TableColumn<MvtApprov,Date> tAlarmDate;
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
    @FXML
    private JFXCheckBox checkBoxNotif;
    
    
    private static int number;
    private static boolean notif;
    
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       try {
    	number=0;
		AuoOrderCreation();
		fillTableAllOrdres();
		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
		Context context1 = new InitialContext();
		MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
		number=OrdreProxy.getAllOrders().size();
		Timeline timeLine = new Timeline(new KeyFrame(Duration.seconds(5), new EventHandler<ActionEvent>() {

		    @Override
		    public void handle(ActionEvent event) {
		        
		    	try {
		    		
		    		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
		    		Context context1 = new InitialContext();
		    		MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
					AuoOrderCreation();
					
					if(OrdreProxy.getAllOrders().size()>number) {
						number=OrdreProxy.getAllOrders().size();
						showNotification();
					}
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	
		    }
		}));
		
		timeLine.setCycleCount(timeLine.INDEFINITE);
		timeLine.play();
		

	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
    }   
    
    
   @FXML
   public void OnRefreshBtnAction(ActionEvent event) throws NamingException {
	   fillTableAllOrdres();
	   BtnConfirmOrder.setDisable(true);
	   receptionDate.setDisable(true);
   }
   
   
    public void showNotification() {
    	receptionDate.setDisable(false);
    	BtnConfirmOrder.setDisable(false);
    	Image image=new Image("views/image/verified1.png");
    	Notifications notification=Notifications.create().title("New GPAO Notification")
    			.text("You have receive new order")
    			.graphic(null)
    			.position(Pos.BOTTOM_RIGHT)
    			.hideAfter(Duration.seconds(10000))
    			.darkStyle()
    			.graphic(new ImageView(image));
    			
    	
	
    	notification.show();
   
    }
    
    
    
    
    
public void fillTableAllOrdres() throws NamingException {

	String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/MvtApprovService!tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovServiceRemote";
	Context context1 = new InitialContext();
	MvtApprovServiceRemote OrdreProxy = (MvtApprovServiceRemote) context1.lookup(jndiName);
	
	  /*tAlarmDate.setCellFactory(column -> {
  	    return new TableCell<MvtApprov, Date>() {
  	        @Override
  	        protected void updateItem(Date item, boolean empty) {
  	            super.updateItem(item, empty);

  	            if (item == null || empty) {
  	                setText(null);
  	                setStyle("");
  	            } else {
  	            	
  	          
  	          Date currentDate =java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
  	          if(item.compareTo(currentDate)<=0) {
  	        	setText(item.toString());
  	        	
  	          }else {
  	        	setText(item.toString());
  	          }
  	        
  	            	//setTextFill(Color.RED);
  	               //setStyle("-fx-font-weight: bold");
  	                    
  	               
  	            }
  	        }
  	    };
  	});*/
	
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
	
		ObsListOrders = FXCollections.observableArrayList(OrdreProxy.getAllOrders());
	
	
	tableAllOrders.setItems(ObsListOrders);
	tableAllOrders.refresh();
     
     

	
}

@FXML
private void OnCheckBoxNotifClicked(ActionEvent actionEvent) throws InterruptedException, NamingException {

		
		
		
		
		
	
	

}





    @FXML
    private void SearchOrdreFromTableAction(KeyEvent event) throws NamingException {
    
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
    		if(articles.get(i).getType().equals("Matiére-Premiére")) {
    		
    		
    	int joursRestant=articles.get(i).getQuantity()/articles.get(i).getDailyConsumption();
    	 

    	Date dateT=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
    	Date AlarmDate=java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());
    	
    	Date currentDate =java.sql.Date.valueOf(LocalDateTime.now().toLocalDate());


    	int day= dateT.getDate();
    	
    	
    	dateT.setDate(day+joursRestant-articles.get(i).getDeliveryTime());
    	AlarmDate.setDate(day+joursRestant+1);
    	
    	
    	if(dateT.compareTo(currentDate)<=0 && articles.get(i).getEtatOrdre()==0) {
    	
    		MvtApprov mvtApprov=new MvtApprov(articles.get(i),null,articles.get(i).getPricipalQuantity()-articles.get(i).getQuantity(),AlarmDate,dateT,null);
    		OrdreProxy.addMvtApprov(mvtApprov);
    		articles.get(i).setEtatOrdre(1);
    		aArticleProxy.updateArticle(articles.get(i));
    	}
    	
    	
    		}
    	
    	}
    }
    @FXML
    private void OnConfirmOrderClicked(ActionEvent event) throws InterruptedException {
    	
    	receptionDate.setDisable(false);
    	BtnConfirmOrder.setDisable(false);
    
  	
    	
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
    
	 if(CurrentDate.compareTo(RecptionTypeDate)>=0) {
		 alert.setTitle("Wrong Date");
         alert.setHeaderText("Reception Date is wrong");
        alert.showAndWait();
	 }else {
			mvtApprov.setReceptionDate(RecptionTypeDate);
	    	
	    	OrdreProxy.updateMvtApprov(mvtApprov);
	    	fillTableAllOrdres();
	    	receptionDate.setDisable(true);
	    	BtnConfirmOrder.setDisable(true);
	    	receptionDate.setValue(null);
	    	aArticleProxy.incrementArticleQuantity(mvtApprov.getArticle().getId(), mvtApprov.getQuantity());
	 }
    	}
    
    	
    }
    
    private void genereteReportPdf(MvtApprov order ,String path) throws IOException, DocumentException, MessagingException {
    	Document document = new Document();

PdfWriter.getInstance(document, new FileOutputStream(path));

document.open();

document.add(new Paragraph("\n--------------------  Product Request  --------------------------\n\n"
    + " Article Code :"+order.getArticle().getUnitCode()
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
   