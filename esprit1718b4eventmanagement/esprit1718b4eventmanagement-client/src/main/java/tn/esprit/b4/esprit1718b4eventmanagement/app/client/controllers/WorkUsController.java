package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.ResourceBundle;


import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;


/**
 * FXML Controller class
 *
 * @author danml
 */
public class WorkUsController implements Initializable {

    @FXML
    private JFXComboBox<String> comboSpecialization;

    @FXML
    private ToggleGroup q;
    @FXML
    private JFXButton saveWR;
    @FXML
    private JFXTextField object;
    @FXML
    private JFXTextArea adInfo;
    @FXML
    private TableColumn<Works, String> C1;
    @FXML
    private TableColumn<Works, String> C2;
    @FXML
    private TableColumn<Works, String> C3;
    @FXML
    private TableColumn<Works, String> C4;
    @FXML
    private TableColumn<Works, String> C5;
    @FXML
    private TableColumn<Works, String> C6;
    @FXML
    private TableColumn<Works, String> C7;
    @FXML
    private TableColumn<Works, String> C8;
    @FXML
    private TableView<Works> tableview;
    @FXML
    private Tab tabD;
    @FXML
    private JFXTextField f1;
    @FXML
    private JFXTextField f2;
    @FXML
    private JFXTextField f3;
    @FXML
    private JFXTextField f4;
    @FXML
    private JFXTextField f5;
    @FXML
    private JFXTextField f6;
    @FXML
    private JFXTextField f7;
    @FXML
    private JFXTreeView<String> treeviewEq;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

    @SuppressWarnings({ "finally", "null", "unchecked" })
	@Override
    public void initialize(URL url, ResourceBundle rb){
    	 Image icon = new Image (
    			   getClass().getResourceAsStream("/views/image/chs.png"));
    			
    			  TreeItem<String> equii =  new TreeItem<>("Entreprise", new ImageView(icon));
    			  equii.setExpanded(true);

    			  TreeItem<String> nodeA = new TreeItem<>("arbo 1", new ImageView(icon));
    			  TreeItem<String> nodeB = new TreeItem<>("arbo 2", new ImageView(icon));
    			  TreeItem<String> nodeC = new TreeItem<>("arbo 3", new ImageView(icon));
    			  equii.getChildren().addAll(nodeA,nodeB,nodeC);
    			  nodeA.setExpanded(true);
    			  
    			  TreeItem<String> nodeA1 = new TreeItem<>("arbo1:room 1", new ImageView(icon));
    			  TreeItem<String> nodeB1 = new TreeItem<>("arbo1:room 2", new ImageView(icon));
    			  TreeItem<String> nodeC1 = new TreeItem<>("arbo1:room 3", new ImageView(icon));
    			  nodeA.getChildren().addAll(nodeA1,nodeB1,nodeC1);
    			  treeviewEq.setRoot(equii);
   	 tableview.setEditable(true);
    	//UserServiceRemote userService2;
    
		try {
			UsualWork xxx=new UsualWork();
			Context context;
			context = new InitialContext();
	    	UserServiceRemote userService2 = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");

			List<String>lun = new ArrayList<>();
			for(int i=0;i<userService2.findAll().size();i++)
			{
			lun.add(userService2.findAll().get(i).getFirstname()+" "+userService2.findAll().get(i).getLastname());
			}
			C4.setOnEditCommit((CellEditEvent<Works, String> event) -> {
				try {
				
					 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
					 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
					
					  	TablePosition<Works, String> pos = event.getTablePosition();
			            
			            String state = event.getNewValue();
			            System.out.println("combo"+state);
			 
			            int row = pos.getRow();
			            UsualWork www=(UsualWork) event.getTableView().getItems().get(row);
			            xxx.setNature(Nature.WorkOrder);
			            xxx.setDescription(www.getDescription());
			            xxx.setEmmergency(www.getEmmergency());
			            xxx.setObjet(www.getObjet());
			            xxx.setState(state);
			    	    xxx.setWODate(new Date());
			    	    xxx.setWorksPK(www.getWorksPK()); 

			    	

		
			    	
			    		
			    		 proxy.updateWork(xxx);

			
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        });
       		C8.setOnEditCommit((CellEditEvent<Works, String> eventk) -> {
       			UserServiceRemote userServicej;
				try {
					userServicej = (UserServiceRemote) context
							.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
					 String jndiNamec8="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
					 WorksUsServiceRemote proxyc8=(WorksUsServiceRemote) context.lookup(jndiNamec8);
					
					  	TablePosition<Works, String> pos = eventk.getTablePosition();
				
					User tech =userServicej.userbyfstlstname(eventk.getNewValue());
				       int row = pos.getRow();
				  
				    UsualWork www=(UsualWork) eventk.getTableView().getItems().get(row);
				
					System.out.println(eventk.getNewValue());
		    	   	showdetails(tech);
		    	
		    	
		    	    xxx.setWorksPK(www.getWorksPK()); 
		    	   	xxx.setTechnicianId(tech.getId());
		    		proxyc8.updateWork(xxx);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	   
    });
       		C3.setOnEditCommit((CellEditEvent<Works, String> eventb) -> {
       		 String jndiNamec3="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxyc3;
			try {
				proxyc3 = (WorksUsServiceRemote) context.lookup(jndiNamec3);
				TablePosition<Works, String> pos = eventb.getTablePosition();
				System.out.println(eventb.getNewValue());
			       int row = pos.getRow();
			    UsualWork www=(UsualWork) eventb.getTableView().getItems().get(row);
					
			    xxx.setWorksPK(www.getWorksPK()); 
							System.out.println(eventb.getNewValue());
							xxx.setTechnology(eventb.getNewValue());
							proxyc3.updateWork(xxx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			  
					


		        });
    		C8.setCellValueFactory(cellData
                    -> {
                        return new SimpleStringProperty(cellData.getValue().getUser().getFirstname()+" "+cellData.getValue().getUser().getLastname());
                    }
            );
            C8.setCellFactory(tableCol -> {
            	ComboBoxTableCell<Works, String> ct2 = new ComboBoxTableCell<>();
    			ObservableList<String> obList = FXCollections.observableList(lun);
    		
                ct2.getItems().addAll(obList);
                ct2.setComboBoxEditable(true);
            	C8.setEditable(true);
                return ct2;
            });

		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	
      
        // Populate Comboboxe with static options,
        comboSpecialization.getItems().addAll("Mechanical","Electrical ","hydraulic"
        ,"unspecified");
        comboSpecialization.getSelectionModel().selectLast();
        
      //  comboDepartment.getItems().addAll("eq1","eq2");
     //   comboDepartment.getSelectionModel().selectLast();
    	Context context;
    	try {
			context = new InitialContext();
			UserServiceRemote userService = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
	    	User user = LoginController.user;
	    	if (user.getId()==1)
	    	{
	    		tabD.setDisable(true);
	    	}
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    

    	
		try {
			

				context= new InitialContext();
			 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
		
	
		    		C1.setCellValueFactory(new PropertyValueFactory<Works, String>("objet"));
		    		C2.setCellValueFactory(new PropertyValueFactory<Works, String>("description"));
		    		C3.setCellValueFactory(new PropertyValueFactory<Works, String>("technology"));
		    		C3.setCellValueFactory(cellData
		                    -> {
		                        return new SimpleStringProperty(cellData.getValue().getTechnology());
		                    }
		            );
		            C3.setCellFactory(tableCol -> {
		                ComboBoxTableCell<Works, String> ct = new ComboBoxTableCell<>();
		                ct.getItems().addAll("Mechanical","Electrical ","hydraulic"
		                        ,"unspecified");
		                ct.setComboBoxEditable(true);

		                return ct;
		            });
		            C4.setEditable(true);
		    		C4.setCellValueFactory(new Callback<CellDataFeatures<Works, String>, ObservableValue<String>>() {
		    			 
		                @Override
		                public ObservableValue<String> call(CellDataFeatures<Works, String> param) {
		                    Works person = param.getValue();
		                    // F,M
		                    String state = person.getState();
		                   // Gender gender = Gender.getByCode(genderCode);
		                    return new SimpleObjectProperty<String>(state);
		                }
		            });
		    		C5.setCellValueFactory(new PropertyValueFactory<Works, String>("emmergency"));
				    C6.setCellValueFactory(new PropertyValueFactory<Works, String>("WRDate"));
				    C7.setCellValueFactory(new Callback<CellDataFeatures<Works,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<Works, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
		    		ObservableList<String> genderList = FXCollections.observableArrayList("approuved","not approuved");
			        // Populate Comboboxe with static options,
		    		C4.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
				
	    	        List<Works> list = proxy.displayWRB();
	    	        ObservableList<Works> items = FXCollections.observableArrayList(list);
	    	      //  System.out.println(items.get(0).getDescription());
	    	       tableview.setItems(items);
	    	   
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		tableview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			
		}));
    }  
	private void showdetails(User trader) {

	
		f1.setText("Firstname: "+trader.getFirstname());
		f2.setText("Lastname: "+trader.getLastname());
		f3.setText("Tel: "+trader.getNumtel());
		f4.setText("Departement: "+trader.getRole());
		f5.setText("Email: "+trader.getEmail());

	}
	
    @FXML
    private void onclickSave(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	WorksPK worksPK =new WorksPK();
    	worksPK.setIdUser(LoginController.user.getId());
    	worksPK.setIdEquipment(1);
    	
    	UsualWork uw =new UsualWork();
    
    	uw.setWorksPK(worksPK);

    	uw.setObjet(object.getText());
    	uw.setDescription(adInfo.getText());
    	uw.setTechnology(comboSpecialization.getValue().toString());
      uw.setWRDate(new Date());
  
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        uw.setEmmergency(toogleGroupValue);
        proxy.addWR(uw);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Work Request Adding");
		alert.setHeaderText("Succesful :) ");
		alert.showAndWait();
    }
       
}

