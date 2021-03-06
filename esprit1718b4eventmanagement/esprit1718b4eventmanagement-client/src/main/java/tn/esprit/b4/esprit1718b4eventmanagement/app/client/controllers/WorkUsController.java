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
import javax.swing.text.TabableView;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
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
import javafx.scene.Parent;
import javafx.scene.PointLight;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.paint.*;
import javafx.stage.Stage;


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
    private TableColumn<UsualWork, String> C1;
    @FXML
    private TableColumn<UsualWork, String> C2;
    @FXML
    private TableColumn<UsualWork, String> C3;
    @FXML
    private TableColumn<UsualWork, String> C4;
    @FXML
    private TableColumn<UsualWork, String> C5;
    @FXML
    private TableColumn<UsualWork, Date> C6;
    @FXML
    private TableColumn<UsualWork, String> C7;
    @FXML
    private TableColumn<UsualWork, String> C8;
    @FXML
    private TableColumn<UsualWork, String> C9;
    @FXML
    private TableView<UsualWork> tableview;
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
    URL url1;
    ResourceBundle rb1;

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    static UsualWork xxx;
    static UsualWork xxx2=new UsualWork();
    @SuppressWarnings({ "finally", "null", "unchecked" })
	@Override
    public void initialize(URL url, ResourceBundle rb){
    	 Image icon = new Image (
  			   getClass().getResourceAsStream("/views/imgs/equi.png"));
  		
				TreeItem<String> equii =  new TreeItem<>("Entreprise", new ImageView(icon));
  			  equii.setExpanded(true);

  			/*  TreeItem<String> nodeA = new TreeItem<>("arbo 1", new ImageView(icon));
  			  TreeItem<String> nodeB = new TreeItem<>("arbo 2", new ImageView(icon));
  			  TreeItem<String> nodeC = new TreeItem<>("arbo 3", new ImageView(icon));
  			  equii.getChildren().addAll(nodeA,nodeB,nodeC);
  			  nodeA.setExpanded(true);*/
  			  
  			  TreeItem<String> nodeA1 = new TreeItem<>("arbo1:room 1", new ImageView(icon));
  			  TreeItem<String> nodeB1 = new TreeItem<>("arbo1:room 2", new ImageView(icon));
  			  TreeItem<String> nodeC1 = new TreeItem<>("arbo1:room 3", new ImageView(icon));
  			 // nodeA.getChildren().addAll(nodeA1,nodeB1,nodeC1);
  			 // treeviewEq.setRoot(equii);
 	 tableview.setEditable(true);
  	//UserServiceRemote userService2;
  

    
		try {
			String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
	     	Context context1;
		
				context1 = new InitialContext();

		     	ArboresenceServiceRemote Proxy = (ArboresenceServiceRemote) context1.lookup(jndiName2);
		    	
	  		 	List<Arboresence> list1 = Proxy.getPereArbo("Principale") ;
	  		 //	for(int i=0;i<list1.size();i++)
	  		 		for (Arboresence l1: list1)
	  		 	{
	  		 	 TreeItem<String> node = new TreeItem<>(l1.getName(), new ImageView(icon));
	  		 	 equii.getChildren().add(node);
	  		 	List<ArboPereFis> list= Proxy.getFilsArbo(l1.getId());
	  	
	  		 		for (ArboPereFis l: list)
	  		 	{
	  		 	 TreeItem<String> nodej = new TreeItem<>(l.getArboFils().getName(), new ImageView(icon));
	  		 	 node.setExpanded(true);
	  		 	 node.getChildren().add(nodej);
	  		  
	  		 	List<ArboPereFis> listj= Proxy.getFilsArbo(l.getArboFils().getId());
	  			for (ArboPereFis uj: listj)
	  		 	{
	  		 	TreeItem<String> nodek = new TreeItem<>(uj.getArboFils().getName(), new ImageView(icon));
	  		 	 nodej.setExpanded(true);
	  		 	 nodej.getChildren().add(nodek);
	  			List<ArboPereFis> listk= Proxy.getFilsArbo(uj.getArboFils().getId());
	  			for (ArboPereFis u: listk)
	  		 	{
	  		 	TreeItem<String> nodem= new TreeItem<>(u.getArboFils().getName(), new ImageView(icon));
	  		 	 nodek.setExpanded(true);
	  		 	 nodek.getChildren().add(nodem);
	  		 	List<ArboPereFis> listeq= Proxy.getFilsArbo(u.getArboFils().getId());
	  			for (ArboPereFis ueq: listeq)
	  		 	{
	  		 	TreeItem<String> nodeq= new TreeItem<>(ueq.getArboFils().getName(), new ImageView(icon));
	  		 	 nodem.setExpanded(true);
	  		 	 nodem.getChildren().add(nodeq);
	  		 	List<Equipment> eq= Proxy.DisplayEquipmentbyarbo(ueq.getArboFils());
	  			for (Equipment q: eq)
	  		 	{
	  			 	System.out.println("q.getSerialNum()");
	  		 	TreeItem<String> nodeequi= new TreeItem<>(q.getSerialNum(), new ImageView(icon));
	  		 	System.out.println(q.getSerialNum());
	  		 	nodeq.setExpanded(true);
	  		 	nodeq.getChildren().add(nodeequi);
	  		 	}
	  		 	}
	  		 	}
	  		 	
	  		 	}
	  		 	
   			 
   			// node.getChildren().addAll(nodeA1,nodeB1,nodeC1);
	  		 	}
	  		 	}
  			  treeviewEq.setRoot(equii);
  	    	
  
  			treeviewEq.getSelectionModel().selectedItemProperty()
            .addListener(new ChangeListener<TreeItem<String>>() {

                @Override
                public void changed(
                	
    	     	
                        ObservableValue<? extends TreeItem<String>> observable,
                        TreeItem<String> old_val, TreeItem<String> new_val) {
                	
					try {
						String jndiNameEq= "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
	        	  	    

		  	    		EquipementServiceRemote proxyeq;
						proxyeq = (EquipementServiceRemote) context1.lookup(jndiNameEq);
						 TreeItem<String> selectedItem = new_val;
		                    System.out.println("Selected Text : " + selectedItem.getValue());
		                    // do what ever you want
		                    System.out.println(proxyeq.findEquipementBySerie(selectedItem.getValue()));
		                    xxx2.setEquipement(proxyeq.findEquipementBySerie(selectedItem.getValue()));
					} catch (NamingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   
                }

            });
			Context context;
			context = new InitialContext();
	    	UserServiceRemote userService2 = (UserServiceRemote) context
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");

			List<String>lun = new ArrayList<>();
			for(int i=0;i<userService2.findAll().size();i++)
			{
			lun.add(userService2.findAll().get(i).getFirstname()+" "+userService2.findAll().get(i).getLastname());
			}
			C4.setOnEditCommit((CellEditEvent<UsualWork, String> event) -> {
				try {
					 
					 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
					 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
					
					  	TablePosition<UsualWork, String> pos = event.getTablePosition();
			            
			            String state = event.getNewValue();
			            System.out.println("combo"+state);
			 
			            int row = pos.getRow();
			            xxx= event.getTableView().getItems().get(row);
			            if(state.equals("approuved"))
			            {xxx.setNature(Nature.WorkOrder);
			           
			           
			            System.out.println( "wo.init(url, rb)");
			            }
			            else
			            	{xxx.setNature(Nature.WorkRequest);}
			            xxx.setState(state);
			    	    xxx.setWODate(new Date());
			    	 //   xxx.setWorksPK(www.getWorksPK()); 

			    	

		
			    	
			    		
			    		 proxy.updateWork(xxx);
			    	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

	        });
       		C8.setOnEditCommit((CellEditEvent<UsualWork, String> eventk) -> {
       			UserServiceRemote userServicej;
				try {
					userServicej = (UserServiceRemote) context
							.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
					 String jndiNamec8="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
					 WorksUsServiceRemote proxyc8=(WorksUsServiceRemote) context.lookup(jndiNamec8);
					
					  	TablePosition<UsualWork, String> pos = eventk.getTablePosition();
				
					User tech =userServicej.userbyfstlstname(eventk.getNewValue());
				       int row = pos.getRow();
				  
				       xxx= eventk.getTableView().getItems().get(row);
				
					System.out.println(eventk.getNewValue());
		    	   	showdetails(tech);
		    	
		    	
		    	   // xxx.setWorksPK(www.getWorksPK()); 
		    	   	xxx.setTechnicianId(tech.getId());
		    		proxyc8.updateWork(xxx);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
	    	   
    });
       		C3.setOnEditCommit((CellEditEvent<UsualWork, String> eventb) -> {
       		 String jndiNamec3="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxyc3;
			try {
				proxyc3 = (WorksUsServiceRemote) context.lookup(jndiNamec3);
				TablePosition<UsualWork, String> pos = eventb.getTablePosition();
				System.out.println(eventb.getNewValue());
			       int row = pos.getRow();
			    xxx= eventb.getTableView().getItems().get(row);
					
			  //  xxx.setWorksPK(www.getWorksPK()); 
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
            	ComboBoxTableCell<UsualWork, String> ct2 = new ComboBoxTableCell<>();
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
	    	if (user.getPost().equals("technician"))
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
			 C9.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

	              @Override
	              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
	                  return new SimpleStringProperty(param.getValue().getUser().getFirstname()+"--"+param.getValue().getUser().getLastname());
	              }
	          }); 
		    		C1.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("objet"));
		    		C2.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("description"));
		    		C3.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("technology"));
		    		C3.setCellValueFactory(cellData
		                    -> {
		                        return new SimpleStringProperty(cellData.getValue().getTechnology());
		                    }
		            );
		            C3.setCellFactory(tableCol -> {
		                ComboBoxTableCell<UsualWork, String> ct = new ComboBoxTableCell<>();
		                ct.getItems().addAll("Mechanical","Electrical ","hydraulic"
		                        ,"unspecified");
		                ct.setComboBoxEditable(true);

		                return ct;
		            });
		            C4.setEditable(true);
		    		C4.setCellValueFactory(new Callback<CellDataFeatures<UsualWork, String>, ObservableValue<String>>() {
		    			 
		                @Override
		                public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
		                    Works person = param.getValue();
		                    // F,M
		                    String state = person.getState();
		                   // Gender gender = Gender.getByCode(genderCode);
		                    return new SimpleObjectProperty<String>(state);
		                }
		            });
		    		C5.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("emmergency"));
				    C6.setCellValueFactory(new PropertyValueFactory<UsualWork, Date>("WRDate"));
				    C7.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
		    		ObservableList<String> genderList = FXCollections.observableArrayList("approuved","not approuved");
			        // Populate Comboboxe with static options,
		    		C4.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
				
	    	        List<UsualWork> list = proxy.displayWRB();
	    	        ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
	    	      //  System.out.println(items.get(0).getDescription());
	    	       tableview.setItems(items);
	    	 
	    	 // 	WorkOsController.init(url1, rb1);
	    	   
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
    	
    	
    	//UsualWork uw =xxx2;
    System.out.println(LoginController.user.getLogin());
  
    	xxx2.setUser(LoginController.user);
    	xxx2.setObjet(object.getText());
    	xxx2.setDescription(adInfo.getText());
    	xxx2.setTechnology(comboSpecialization.getValue().toString());
    	xxx2.setWRDate(new Date());
  
        RadioButton selectedRadioButton = (RadioButton) q.getSelectedToggle();
        String toogleGroupValue = selectedRadioButton.getText();
        xxx2.setEmmergency(toogleGroupValue);
        Equipment e=new Equipment();
        e.setId(2);
        //uw.setEquipement(e);
        proxy.addWR(xxx2);
        Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Work Request Adding");
		alert.setHeaderText("Succesful :) ");
		alert.showAndWait();
		DETAILS();
    }
    

  
    private void DETAILS() throws NamingException {
   
			  
	 tableview.setEditable(true);
	//UserServiceRemote userService2;

	try {
		
		Context context;
		context = new InitialContext();
  	UserServiceRemote userService2 = (UserServiceRemote) context
				.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");

		List<String>lun = new ArrayList<>();
		for(int i=0;i<userService2.findAll().size();i++)
		{
		lun.add(userService2.findAll().get(i).getFirstname()+" "+userService2.findAll().get(i).getLastname());
		}
		C4.setOnEditCommit((CellEditEvent<UsualWork, String> event) -> {
			try {
			
				 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
				 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
				
				  	TablePosition<UsualWork, String> pos = event.getTablePosition();
		            
		            String state = event.getNewValue();
		            System.out.println("combo"+state);
		 
		            int row = pos.getRow();
		            xxx= event.getTableView().getItems().get(row);
		            if(state.equals("approuved"))
		            {xxx.setNature(Nature.WorkOrder);
		         /*   WorkOsController wo=new WorkOsController();
		            wo.init(url, rb);*/
		            }
		            else
		            	{xxx.setNature(Nature.WorkRequest);}
		            xxx.setState(state);
		    	    xxx.setWODate(new Date());
		    	 //   xxx.setWorksPK(www.getWorksPK()); 

		    	

	
		    	
		    		
		    		 proxy.updateWork(xxx);

		
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

      });
 		C8.setOnEditCommit((CellEditEvent<UsualWork, String> eventk) -> {
 			UserServiceRemote userServicej;
			try {
				userServicej = (UserServiceRemote) context
						.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
				 String jndiNamec8="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
				 WorksUsServiceRemote proxyc8=(WorksUsServiceRemote) context.lookup(jndiNamec8);
				
				  	TablePosition<UsualWork, String> pos = eventk.getTablePosition();
			
				User tech =userServicej.userbyfstlstname(eventk.getNewValue());
				
			       int row = pos.getRow();
			  
			       xxx= eventk.getTableView().getItems().get(row);
			
				System.out.println(eventk.getNewValue());
	    	   	showdetails(tech);
	    	
	    	
	    	   // xxx.setWorksPK(www.getWorksPK()); 
	    	   	xxx.setTechnicianId(tech.getId());
	    		proxyc8.updateWork(xxx);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
  	   
});
 		C3.setOnEditCommit((CellEditEvent<UsualWork, String> eventb) -> {
 		 String jndiNamec3="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
		 WorksUsServiceRemote proxyc3;
		try {
			proxyc3 = (WorksUsServiceRemote) context.lookup(jndiNamec3);
			TablePosition<UsualWork, String> pos = eventb.getTablePosition();
			System.out.println(eventb.getNewValue());
		       int row = pos.getRow();
		    xxx= eventb.getTableView().getItems().get(row);
				
		  //  xxx.setWorksPK(www.getWorksPK()); 
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
      	ComboBoxTableCell<UsualWork, String> ct2 = new ComboBoxTableCell<>();
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
  	if (user.getPost().equals("technician"))
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
	

	    		C1.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("objet"));
	    		C2.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("description"));
	    		C3.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("technology"));
	    		C3.setCellValueFactory(cellData
	                    -> {
	                        return new SimpleStringProperty(cellData.getValue().getTechnology());
	                    }
	            );
	            C3.setCellFactory(tableCol -> {
	                ComboBoxTableCell<UsualWork, String> ct = new ComboBoxTableCell<>();
	                ct.getItems().addAll("Mechanical","Electrical ","hydraulic"
	                        ,"unspecified");
	                ct.setComboBoxEditable(true);

	                return ct;
	            });
	            C4.setEditable(true);
	    		C4.setCellValueFactory(new Callback<CellDataFeatures<UsualWork, String>, ObservableValue<String>>() {
	    			 
	                @Override
	                public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
	                    Works person = param.getValue();
	                    // F,M
	                    String state = person.getState();
	                   // Gender gender = Gender.getByCode(genderCode);
	                    return new SimpleObjectProperty<String>(state);
	                }
	            });
	    		C5.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("emmergency"));
			    C6.setCellValueFactory(new PropertyValueFactory<UsualWork, Date>("WRDate"));
			    C7.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

		              @Override
		              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
		                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
		              }
		          }); 
	    		ObservableList<String> genderList = FXCollections.observableArrayList("approuved","not approuved");
		        // Populate Comboboxe with static options,
	    		C4.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
			
  	        List<UsualWork> list = proxy.displayWRB();
  	        ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
  	      //  System.out.println(items.get(0).getDescription());
  	       tableview.setItems(items);
  	   
	} catch (NamingException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	tableview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
		
	}));
    }
}

