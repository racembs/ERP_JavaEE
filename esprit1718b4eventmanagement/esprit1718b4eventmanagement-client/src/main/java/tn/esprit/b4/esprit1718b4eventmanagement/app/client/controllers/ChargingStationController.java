package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import com.sun.javafx.css.converters.StringConverter;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
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
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ons
 */

public class ChargingStationController implements Initializable {
	@FXML  
    private Label label;
	@FXML 
    private Label idAddlb;
	@FXML 
    private Label idUplb;

    @FXML
    private Label txtCurrentWindow;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private TableView<ChargingStation> idTab;
    @FXML
    private TableColumn<ChargingStation, String> idUserTab;
    @FXML
    private TableColumn<ChargingStation, String> idEquipementTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idCodeTab;
    @FXML
    private TableColumn<ChargingStation, String> idDescriptionTab;
    @FXML
    private TableColumn<ChargingStation, String> idNaturePostTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idNbDaysTab;
    @FXML
    private TableColumn<ChargingStation, Integer> idNbHoursTab;
    @FXML
    private ImageView idAdd;
    @FXML
    private ImageView idBack;
 
    @FXML
    private ImageView idFind;
    @FXML
    private ImageView idicone;
    @FXML
    private JFXTextField idchs;
    @FXML
    private ComboBox<User> idUser;
    @FXML
    private ComboBox<Equipment> idEquipement;
    @FXML
    private TextField idCode;
    @FXML
    private TextField idNaturePost;
    @FXML
    private TextField idNbDays;
    @FXML
    private TextField idNbHours;
    @FXML
    private TextArea idDEscription;
    @FXML
    private ImageView idCalendar;
    @FXML
    private ImageView idStatistics;
    @FXML
    private ImageView idsubmit;
//    @FXML
//    private ImageView idcancel;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	
    	Context context;
    	
		try {
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation;
			contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();
		
				
           
 			Context contextEquipment;
 			contextEquipment = new InitialContext();
 			EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
 	    	Equipment equipment = new Equipment();
 	    	List<Equipment> listE;// = proxyEquipment.DisplayEquipment();
 	    	listE = proxyEquipment.DisplayEquipment();
 	    	//ComboBox Equipment
              ObservableList<Equipment> obListeq = FXCollections.observableArrayList(listE);
              
              idEquipement.setConverter(new javafx.util.StringConverter<Equipment>(
            		  ) {
				
				@Override
				public String toString(Equipment object) {
					 return String.valueOf(object.getSerialNum()+"-"+object.getDescription());
				}
				
				@Override
				public Equipment fromString(String string) {
					// TODO Auto-generated method stub
					return null;
				}
			});
              
            
              idEquipement.setItems(obListeq);

              idEquipement.getSelectionModel().selectLast();
			
 			 
              

          	
  			Context contextUser;
  			contextUser = new InitialContext();
  	    	UserServiceRemote proxyuser = (UserServiceRemote) contextUser.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
  	    	User user = new User();
  			List<User>listu = proxyuser.DisplayUser();
  			
  		    //ComboBox User
  		    ObservableList<User> obListu = FXCollections.observableList(listu);
		    
  		  idUser.setConverter(new javafx.util.StringConverter<User>(
        		  ) {
			
			@Override
			public String toString(User object) {
				return String.valueOf(object.getFirstname()+"-"+object.getLastname());
			}
			
			@Override
			public User fromString(String string) {
				// TODO Auto-generated method stub
				return null;
			}
		});
  		    
          
             idUser.setItems(obListu);  
           idUser.getSelectionModel().selectLast();	
              
			idUserTab.setCellValueFactory(new Callback<CellDataFeatures<ChargingStation,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<ChargingStation, String> param) {
	                  return new SimpleStringProperty(param.getValue().getUser().getFirstname()+"-"+param.getValue().getUser().getLastname());
	              }
	          }); 
			idEquipementTab.setCellValueFactory(new Callback<CellDataFeatures<ChargingStation,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<ChargingStation, String> param) {
	                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"-"+param.getValue().getEquipement().getDescription());
	              }
	          }); 
			idCodeTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("code"));
			idDescriptionTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, String>("description"));
			idNaturePostTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, String>("naturepost"));
			idNbDaysTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("nbday"));
			idNbHoursTab.setCellValueFactory(new PropertyValueFactory<ChargingStation, Integer>("nbhours"));
			
			
			   List<ChargingStation> list = proxyChargingStation.DisplayChargingStation();
		        ObservableList<ChargingStation> items = FXCollections.observableArrayList(list);
		        idTab.setItems(items);
	

		  
		  idAdd.setOnMouseClicked((MouseEvent e) -> { 
		  
			  ChargingStation chargingstation = new ChargingStation();
			  int code=Integer.parseInt(idCode.getText());
			  chargingstation.setCode(code);	  
			  chargingstation.setDescription(idDEscription.getText());
			  chargingstation.setNaturepost(idNaturePost.getText());
			  int nbd=Integer.parseInt(idNbDays.getText());
			  int nbh=Integer.parseInt(idNbHours.getText());		  
			  chargingstation.setNbday(nbd);
			  chargingstation.setNbhours(nbh);
			
			  int idE = idEquipement.getSelectionModel().getSelectedItem().getId();
			  int idU = idUser.getSelectionModel().getSelectedItem().getId();
			  System.out.println(idE);
			  System.out.println(idU);

				
			  proxyChargingStation.addChargingStation(idE, idU, chargingstation);
				

    		    List<ChargingStation> listch = proxyChargingStation.DisplayChargingStation();
    	        ObservableList<ChargingStation> itemsch = FXCollections.observableArrayList(listch);
    	        idTab.setItems(itemsch);
			        
				    Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ChargingStation Added");
					alert.setHeaderText("Succesful");
					alert.showAndWait();

		  });
		  
		  
		  
		  idFind.setOnMouseClicked((MouseEvent e) -> { 
				       
//			  int c=Integer.parseInt(idchs.getText());	  
//			  System.out.println(idchs.getText());
			  List<ChargingStation> listch22 =proxyChargingStation.findd(idchs.getText());
				        
			  ObservableList<ChargingStation> itemsch22 = FXCollections.observableArrayList(listch22);
			 idTab.setItems(itemsch22);
			  
				  
		});
		  
		  

		  
		  
		  idBack.setOnMouseClicked((MouseEvent a) -> { 
			  Parent parent= null;
			  	try {
	  				parent  =FXMLLoader.load(getClass().getResource("/views/ChargingStationMenu.fxml"));
	  				Scene scene=new Scene(parent);
	  				Stage primaryStage= new Stage(); 
	  				primaryStage.setScene(scene);
	  				primaryStage.show();
	  				idBack.getScene().getWindow().hide();
			  	} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
		  });
		  
		  
//		  idcancel.setOnMouseClicked((MouseEvent a) -> { 
//				idCode.clear();
//				idDEscription.clear();
//				idNaturePost.clear();
//				idNbDays.clear();
//				idNbHours.clear();
//				idEquipement.getSelectionModel().selectLast();
//				idUser.getSelectionModel().selectLast();
//		   	    
//			
//		  });
    } catch (NamingException e1) {
		// TODO Auto-generated catch block
		
	}
		}
    
  
    @FXML
    public void OnKeyReleased2(KeyEvent event)
    {
  	 System.out.print(idchs.getText());
    	
		
Context context;
    	
		try {
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation;
			contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();

			  List<ChargingStation> listch22 =proxyChargingStation.findd(idchs.getText());
				        
			  ObservableList<ChargingStation> itemsch22 = FXCollections.observableArrayList(listch22);
			 idTab.setItems(itemsch22);
			  
  	  
  		} catch (NamingException x) {
  			
  			
  		}
        
    }
    
    
    @FXML
    public void OnUpdate(ActionEvent event)
    {	Context context;
	
	try {
		String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
		Context contextChargingStation;
		contextChargingStation = new InitialContext();
		ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
		ChargingStation ch = new ChargingStation();
		  
			if(idTab.getSelectionModel().getSelectedItem()!=null){

				ChargingStation chs = idTab.getSelectionModel().getSelectedItem();
				String c= String.valueOf(chs.getCode());
				String d= String.valueOf(chs.getNbday());
				String h= String.valueOf(chs.getNbhours());
	    		idCode.setText(c);    
	    		idDEscription.setText(chs.getDescription());
	    		idNaturePost.setText(chs.getNaturepost());
	    		idNbDays.setText(d);
	    		idNbHours.setText(h);
	    		idEquipement.getSelectionModel().select(chs.getEquipement().getId());
	    		idUser.getSelectionModel().select(chs.getEquipement().getId());
	  idsubmit.setOnMouseClicked((MouseEvent a) -> { 

				int code=Integer.parseInt(idCode.getText());
				chs.setCode(code);	  
				chs.setDescription(idDEscription.getText());
				chs.setNaturepost(idNaturePost.getText());
				int nbd=Integer.parseInt(idNbDays.getText());
				int nbh=Integer.parseInt(idNbHours.getText());		  
				chs.setNbday(nbd);
				chs.setNbhours(nbh);
						
				int idE = idEquipement.getSelectionModel().getSelectedItem().getId();
				int idU = idUser.getSelectionModel().getSelectedItem().getId();
				System.out.println(idE);
				System.out.println(idU);
						  
				ChargingStationPK charginstationpk = new ChargingStationPK();
				charginstationpk.setId_equipment(idE);
				charginstationpk.setIdUser(idU);
				chs.setChargingstationPK(charginstationpk);
				//proxyChargingStation.deleteChargingStation(idE,idU);
				//proxyChargingStation.updateChargingStation(idE,idU,chs);
				proxyChargingStation.update(idTab.getSelectionModel().getSelectedItem());
	    	

  		    List<ChargingStation> listchu = proxyChargingStation.DisplayChargingStation();
  	        ObservableList<ChargingStation> itemschu = FXCollections.observableArrayList(listchu);
  	        idTab.setItems(itemschu);
			        
				    Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("ChargingStation Updated");
					alert.setHeaderText("Succesful");
					alert.showAndWait();
	    			

	    		 });
	    	}
	} catch (Exception e1) {
		
		e1.printStackTrace();
	}
    }
    
    @FXML
    public void OnDelete(ActionEvent event)
    {
	Context context;
    	
		try {
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation;
			contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();
			
			  	Alert alert = new Alert(AlertType.CONFIRMATION);
		    	alert.setTitle("WARNING");
				alert.setHeaderText("Are You Sure?");
		    	
		    	if (alert.showAndWait().get () == ButtonType.OK)
		    	{
//		        	int idE= idTab.getSelectionModel().getSelectedItem().getEquipement().getId();
//		        	int idU= idTab.getSelectionModel().getSelectedItem().getUser().getId();
//		        	System.out.println(idE);
//		        	System.out.println(idU);
		        	proxyChargingStation.delete(idTab.getSelectionModel().getSelectedItem());
		        	//proxyChargingStation.deleteChargingStation(idE, idU);
		        	
		    		    List<ChargingStation> listch = proxyChargingStation.DisplayChargingStation();
		    	        ObservableList<ChargingStation> itemsch = FXCollections.observableArrayList(listch);
		    	        idTab.setItems(itemsch);
		    	        
		    	        
		    		    Alert alert1 = new Alert(AlertType.INFORMATION);
		    			alert1.setTitle("ChargingStation Deleted");
		    			alert1.setHeaderText("Succesful");
		    			alert1.showAndWait();
		    		
		    	}
			} catch (Exception e1) {
				
				e1.printStackTrace();
			}
    }
    }    

    

