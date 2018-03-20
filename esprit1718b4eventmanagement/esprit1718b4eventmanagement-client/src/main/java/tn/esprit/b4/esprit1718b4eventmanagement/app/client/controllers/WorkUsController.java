package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;

import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.swing.event.ChangeListener;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ToggleGroup;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;


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

