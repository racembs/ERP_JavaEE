/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import com.jfoenix.controls.JFXSpinner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;

import org.controlsfx.control.Notifications;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.gui.Upload;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class attController implements Initializable {
	private String imageFile ;
	private Image image ;
	   private Stage dialogStage;
	   public void start(Stage dialogStage) {
	    	this.dialogStage = dialogStage;}
    @FXML
    private Label fileSelected;
	  
	    
    @FXML
    private AnchorPane ehoooo;
    @FXML
    private JFXSpinner loggingProgress;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  

    }    
    private void genereteReportPdf(String path,String image) throws IOException, DocumentException, MessagingException {
    	Document document = new Document();

PdfWriter.getInstance(document, new FileOutputStream(path));

document.open();
Image image1 = Image.getInstance(image);

image1.setAbsolutePosition(0, 0);
image1.setBorderWidth(0);
image1.scaleAbsoluteHeight(PageSize.A6.getHeight());
image1.scaleAbsoluteWidth(PageSize.A6.getWidth());
document.add(image1);
document.close();
Notifications.create()
.title("Equipment Add ")
.text(" PDF is create  with success").darkStyle()
.showWarning();

Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("information");
alert.setHeaderText(null);
alert.setContentText("The report is successfully generated");
alert.show();


} 

    @FXML
    private void pdf(MouseEvent event) throws FileNotFoundException, IOException {
    	
   	 String path_img;
	    FileChooser fileChooser1 = new FileChooser();
	    fileChooser1.setTitle("Select Image File to put in PDF");
	    fileChooser1.getExtensionFilters().addAll(
	            new FileChooser.ExtensionFilter("Image Files",
	                    "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
	    File selectedFile = fileChooser1.showOpenDialog(fileSelected.getScene().getWindow());

	    if (selectedFile != null) {
	        Upload u = new Upload();
	        u.upload(selectedFile);
	        imageFile = selectedFile.toURI().toURL().toString();
	        
	  
	    	path_img = selectedFile.getName();

	   

		
	   
	    }


 fileChooser1.setTitle("location of your pdf");
 fileChooser1.setInitialDirectory(
         new File(System.getProperty("user.home"))
 );
 fileChooser1.setInitialFileName("Report.pdf");
 FileChooser.ExtensionFilter pdfExtensionFilter =
         new FileChooser.ExtensionFilter(
                 "PDF - Portable Document Format (.pdf)", "*.pdf");
 fileChooser1.getExtensionFilters().add(pdfExtensionFilter);
 fileChooser1.setSelectedExtensionFilter(pdfExtensionFilter);
File file1 = fileChooser1.showSaveDialog(fileSelected.getContextMenu());

 if (file1 != null) {
     try {
   	  genereteReportPdf(file1.getPath(),imageFile);
     } catch (Exception e) {
         e.printStackTrace();
     }
 }

	ehoooo.getScene().getWindow().hide();
	Notifications.create()
	.title("Equipment Add ")
	.text(" PDF is create  with success").darkStyle()
	.showWarning();
	Parent parent= null;
	parent  =FXMLLoader.load(getClass().getResource("/views/Equipement.fxml"));
	Scene scene=new Scene(parent);
	Stage primaryStage= new Stage(); 
	primaryStage.setScene(scene);
	primaryStage.show();
	ehoooo.getScene().getWindow().hide();
    }

}
