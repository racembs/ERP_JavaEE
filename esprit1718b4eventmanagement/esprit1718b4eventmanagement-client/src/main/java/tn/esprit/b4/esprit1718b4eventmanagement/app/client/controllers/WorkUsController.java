package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

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

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;

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
    private JFXComboBox<String> comboDepartment;
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
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */

    @SuppressWarnings({ "finally", "null" })
	@Override
    public void initialize(URL url, ResourceBundle rb){
    	
   	 tableview.setEditable(true);
    	UserServiceRemote userService2;
    
		try {
		
			userService2 = (UserServiceRemote) new InitialContext()
					.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
			List<String>lun = new ArrayList<>();
			for(int i=0;i<userService2.findAll().size();i++)
			{
			lun.add(userService2.findAll().get(i).getFirstname()+" "+userService2.findAll().get(i).getLastname());}

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
			C8.setOnEditCommit((CellEditEvent<Works, String> event) -> {
String s =event.getNewValue(); //HERE
System.out.println(event.getNewValue());
/*User tech =userService2.userbyfstlstname(s);
System.out.println(tech.getRole());
showdetails(tech);*/
	        });
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

	
      
        // Populate Comboboxe with static options,
        comboSpecialization.getItems().addAll("Mechanical","Electrical ","hydraulic"
        ,"unspecified");
        comboSpecialization.getSelectionModel().selectLast();
        
        comboDepartment.getItems().addAll("eq1","eq2");
        comboDepartment.getSelectionModel().selectLast();
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
					C4.setOnEditCommit((CellEditEvent<Works, String> event) -> {
			            TablePosition<Works, String> pos = event.getTablePosition();
			            
			            String state = event.getNewValue();
			 System.out.println("combo"+state);
			 
			         int row = pos.getRow();
			            Works www = event.getTableView().getItems().get(row);
			 UsualWork xxx=(UsualWork)www;
			 xxx.setState(state);
			 xxx.setWODate(new Date());
			 proxy.updateWork(xxx);
			        });
	    	        List<Works> list = proxy.displayWRB();
	    	        ObservableList<Works> items = FXCollections.observableArrayList(list);
	    	        System.out.println(items.get(0).getDescription());
	    	       tableview.setItems(items);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		tableview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			//showdetails(newValue);
		}));
    }  
	private void showdetails(User trader) {

		// Level comblevel = trader.getLevel();
		f1.setText(trader.getFirstname());
		f2.setText(trader.getLastname());
		f3.setText(trader.getNumtel());
	f4.setText(trader.getRole());
		f5.setText(trader.getEmail());

	}
	
    @FXML
    private void onclickSave(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	/*UserServiceRemote userService = (UserServiceRemote) context
				.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    	User user = LoginController.user;
    	*/
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	WorksPK worksPK =new WorksPK();
    	worksPK.setIdUser(1);
    	worksPK.setIdEquipment(1);
    	
    	UsualWork uw =new UsualWork();
    	/*UsualWork w=new UsualWork ("text", "text", "comboSpecialization.getValue().toString()",
    			 worksPK,user ,
    			eq,"ok", "En cours",new Date(),
    			 Nature.WorkRequest);*/
    	uw.setWorksPK(worksPK);
    	//System.out.println(.getDescription());
    uw.setObjet(object.getText());
    uw.setDescription(adInfo.getText());
    uw.setTechnology(comboSpecialization.getValue().toString());
      
  
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

