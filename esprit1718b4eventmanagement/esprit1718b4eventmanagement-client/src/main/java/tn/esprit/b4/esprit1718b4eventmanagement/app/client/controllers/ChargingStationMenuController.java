package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;


public class ChargingStationMenuController implements Initializable {
    
    private Label label;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private ImageView idO;
    @FXML
    private Separator id1;
    @FXML
    private Label id2;
    @FXML
    private Label idreflb;
    @FXML
    private ImageView idU;
    @FXML
    private ImageView idEq;
    @FXML
    private ImageView idAll;
    @FXML
    private ImageView idCal;
    @FXML
    private ImageView idStat;
    @FXML
    private ImageView idA12;
    @FXML
    private ImageView idA111;
    @FXML
    private ImageView idref;
    @FXML
    private TreeView<String> idtreeU;
    @FXML
    private TreeView<String> idtreeE;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    	idtreeU.setVisible(false);
		idtreeE.setVisible(false);
		
	

 
		
		
    	idAll.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Charging Station Clicked!"); // change functionality
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/ChargingStation.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idAll.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        });
    	
    	idU.setOnMouseClicked((MouseEvent e) -> {
    		idEq.setVisible(false);
    		idAll.setVisible(false);
    		idCal.setVisible(false);
    		idA111.setVisible(false);
    		idA12.setVisible(false);
    		idStat.setVisible(false);
    		idtreeU.setVisible(true);
    		idtreeE.setVisible(false);
    		
    		try {
    			
    			CheckBoxTreeItem<String> userss = new CheckBoxTreeItem<String>("Users");
    			Context contextUser;
    			contextUser = new InitialContext();
    		   	UserServiceRemote proxyuser = (UserServiceRemote) contextUser.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    	    	User user = new User();
    	    	
    			List<User>listu = proxyuser.DisplayUser();
    			
    	 		for (int i = 0; i < listu.size(); i++) {
    	 			ObservableList<User> itemss = FXCollections.observableArrayList(listu);
    	 			CheckBoxTreeItem<String> Usr = new CheckBoxTreeItem<String>(itemss.get(i).getFirstname()+"-"+itemss.get(i).getLastname());
    	 			Usr.setValue(itemss.get(i).getFirstname()+"-"+itemss.get(i).getLastname());
    	 			userss.getChildren().addAll(Usr);
    			}
    	 		
    	 		idtreeU.setRoot(userss);
    	 		idtreeU.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    	 		
    		} catch (NamingException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
   
    		
    		
        });
    	

    	idEq.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(false);
    		idAll.setVisible(false);
    		idCal.setVisible(false);
    		idA111.setVisible(false);
    		idA12.setVisible(false);
    		idStat.setVisible(false);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(true);
    		
    		try {
    			
    			CheckBoxTreeItem<String> equipments = new CheckBoxTreeItem<String>("Equipments");
    			Context contextEquipment;
     			contextEquipment = new InitialContext();
     			EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
     	    	Equipment equipment = new Equipment();
     	    	List<Equipment> listE = proxyEquipment.DisplayEquipment();
    			
    			
    	 		for (int i = 0; i < listE.size(); i++) {
    	 			ObservableList<Equipment> itemss = FXCollections.observableArrayList(listE);
    	 			CheckBoxTreeItem<String> Eq = new CheckBoxTreeItem<String>(itemss.get(i).getSerialNum()+"-"+itemss.get(i).getMarque());
    	 			Eq.setValue(itemss.get(i).getSerialNum()+"-"+itemss.get(i).getMarque());
    	 			equipments.getChildren().addAll(Eq);
    			}
    	 		
    	 		idtreeE.setRoot(equipments);
    	 		idtreeE.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    	 		
    		} catch (NamingException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}

			
        });
    	
    	
    	idref.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(true);
    		idAll.setVisible(true);
    		idCal.setVisible(true);
    		idA111.setVisible(true);
    		idA12.setVisible(true);
    		idStat.setVisible(true);
    		idEq.setVisible(true);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(false);
    	  });
    	
    	idreflb.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(true);
    		idAll.setVisible(true);
    		idCal.setVisible(true);
    		idA111.setVisible(true);
    		idA12.setVisible(true);
    		idStat.setVisible(true);
    		idEq.setVisible(true);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(false);
    	  });
    	
    	
		  
    	idCal.setOnMouseClicked((MouseEvent e) -> { 
			  
			  System.out.println("Calendar Clicked!"); // change functionality
	            Parent parent= null;
		    	try {
					parent  =FXMLLoader.load(getClass().getResource("/views/Calendar.fxml"));
					Scene scene=new Scene(parent);
					Stage primaryStage= new Stage(); 
					primaryStage.setScene(scene);
					primaryStage.show();
					idCal.getScene().getWindow().hide();

				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		  });
    	}
    }    
    

