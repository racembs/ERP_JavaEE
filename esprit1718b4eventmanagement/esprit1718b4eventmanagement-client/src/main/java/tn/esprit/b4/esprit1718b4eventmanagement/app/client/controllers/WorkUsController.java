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
import java.util.Hashtable;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.Name;
import javax.naming.NameClassPair;
import javax.naming.NameParser;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import java.net.URL;
import java.util.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
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
	    		//C3.setCellValueFactory(new PropertyValueFactory<Works, String>("emmergency"));
	    	//	C4.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("statut"));
	    	
	    	        List<Works> list = proxy.displayWRB();
	    	        ObservableList<Works> items = FXCollections.observableArrayList(list);
	    	        System.out.println(items.get(0).getDescription());
	    	       tableview.setItems(items);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		tableview.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
			showdetails(newValue);
		}));
    }  
	private void showdetails(Works trader) {

		// Level comblevel = trader.getLevel();
		f1.setText(trader.getDescription());
		f2.setText(trader.getObjet());
		f3.setText(trader.getTechnology());
		//f4.setText(trader.getPassword());
		//f5.setValue(trader.getLevel());

	}
    @FXML
    private void onclickSave(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	UserServiceRemote userService = (UserServiceRemote) context
				.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    	User user = LoginController.user;
    	
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

