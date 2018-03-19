package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;

import javafx.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleGroup;

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
    private void onclickSave(ActionEvent event) {
    	System.out.println("button save ok");
    }
       
}

