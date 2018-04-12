/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.pdf.PdfImage;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.image.WritableImage;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.gui.Upload;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.PdfWriter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Asus
 */
public class PdfController implements Initializable {
	private String imageFile ;
		private Image image ;
    @FXML
    private Button pdf;
    @FXML
    private Label fileSelected;
    @FXML
    private AnchorPane ehoooo;
    private Stage dialogStage;
   
    @FXML
    private Label txtserial;
    @FXML
    private Label txtdate;
    @FXML
    private Label txtetat;
    @FXML
    private Label txtdesc1;
    @FXML
    private Label txtmarque;
    @FXML
    private Label txtfabr;
    @FXML
    private ImageView imageview;
    @FXML
    private AnchorPane print;
    @FXML
    private Label dd;
    public void start(Stage dialogStage) {
    	this.dialogStage = dialogStage;}
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
    	Context context;

    		
    			try {
					context= new InitialContext();
			

    		EquipementServiceRemote proxy=(EquipementServiceRemote) context.lookup(jndiName);
    		Equipment eq=proxy.findEquipementBySerie(EquipmentsController.serieid);
        String serie=eq.getSerialNum();
       txtserial.setText(serie);
        String date=eq.getEISDate();
        txtdate.setText(date);
        String fabriquant=eq.getFabriquant();
        txtfabr.setText(fabriquant);
        String lieu=eq.getLieu();
        String desc =eq.getDescription();
        txtdesc1.setText(desc);
        String marque=eq.getMarque();
        txtmarque.setText(marque);
        String state=eq.getState();
        txtetat.setText(state);
        
        
        
        Image image = new Image("http://localhost/image/"+eq.getImage());
        imageview.setImage(image);
        
        
    			} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

    }    



    @FXML
    private void pdf(ActionEvent event) throws FileNotFoundException, IOException {
    	WritableImage image =print.snapshot(new SnapshotParameters(), null);
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Image");
    	// Set extension filter
    	FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
    	"PNG files (*.png)", "*.png");
    	fileChooser.getExtensionFilters().add(extFilter);
    	// Show save file dialog
    	File file = fileChooser.showSaveDialog(this.dialogStage);
    	if (file != null) {
    	// Make sure it has the correct extension
    	if (!file.getPath().endsWith(".png")) {
    	file = new File(file.getPath() + ".png");
    	}
    	ImageIO.write(SwingFXUtils.fromFXImage(image, null), "png", file);
	
    	Parent parent= null;
    	parent  =FXMLLoader.load(getClass().getResource("/views/attent.fxml"));
		Scene scene=new Scene(parent);
		Stage primaryStage= new Stage(); 
		primaryStage.setScene(scene);
		primaryStage.show();
		pdf.getScene().getWindow().hide();
		
	
	}
    	   
    	
    	
    
    }
 

    
}
