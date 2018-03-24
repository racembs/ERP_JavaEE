package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Separator;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ons
 */

public class MenuuGPAOController implements Initializable {
	private JFXHamburger hamburger;
    @FXML
    private Label txtCurrentWindow;
    private AnchorPane holderPane;
    private JFXDrawer drawer;
    @FXML
    private ImageView idCH;
    @FXML
    private ImageView idA;
    @FXML
    private ImageView idOR;
    @FXML
    private ImageView idM;
    @FXML
    private ImageView idO;
    @FXML
    private ImageView idC;
    @FXML
    private Separator id1;
    @FXML
    private Label id2;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	idA.setOnMouseClicked((MouseEvent e) -> {
    		  Parent parent= null;
  	    	try {
  				parent  =FXMLLoader.load(getClass().getResource("/views/Article.fxml"));
  				Scene scene=new Scene(parent);
  				Stage primaryStage= new Stage(); 
  				primaryStage.setScene(scene);
  				primaryStage.show();
  			} catch (Exception e1) {
  				// TODO Auto-generated catch block
  				e1.printStackTrace();
  			}
        });
    	
    	idCH.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Charging Station Clicked!"); // change functionality
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/ChargingStationMenu.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idOR.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        });
    	
    	idOR.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Operating Range Clicked!"); // change functionality
            
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/OperatingRange.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idOR.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        });
    	
    	idM.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Manufacturing Clicked!"); // change functionality
            
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/MenuGPAO.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idM.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
            
        });
    	
    	idO.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Ordres Clicked!"); // change functionality
            
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/Orders.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idO.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        });
    	
    	
	    	idC.setOnMouseClicked((MouseEvent e) -> {
	    		  Parent parent= null;
	  	    	try {
	  				parent  =FXMLLoader.load(getClass().getResource("/views/Client.fxml"));
	  				Scene scene=new Scene(parent);
	  				Stage primaryStage= new Stage(); 
	  				primaryStage.setScene(scene);
	  				primaryStage.show();
	  			} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
	        });
    	
    	
    	
    		
    	

    }

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }


}
