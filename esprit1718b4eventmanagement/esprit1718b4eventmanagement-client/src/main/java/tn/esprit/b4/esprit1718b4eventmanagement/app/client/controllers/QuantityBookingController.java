/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.awt.Window;
import java.net.URL;


import java.util.ResourceBundle;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class QuantityBookingController implements Initializable {

    @FXML
    private Spinner<Integer> QuantitySpinner;
    @FXML
    private Button closeButton;
    
    private static int numberTool;
    private static int numberToolbooked;
    private static boolean done ;

    public static int getNumberToolbooked() {
		return numberToolbooked;
	}

	public static void setNumberToolbooked(int numberToolbooked) {
		QuantityBookingController.numberToolbooked = numberToolbooked;
	}

	/**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	 SpinnerValueFactory<Integer> valueFactory = //
                 new SpinnerValueFactory.IntegerSpinnerValueFactory(1, numberTool ,1);
  
    	 QuantitySpinner.setValueFactory(valueFactory);
    	 
    	 done = false ;
    	
    }    

    public QuantityBookingController() {
		super();
	}



	public static int getNumberTool() {
		return numberTool;
	}

	public static void setNumberTool(int numberTool) {
		QuantityBookingController.numberTool = numberTool;
	}

	@FXML
    private void onQuentityTake(ActionEvent event) {
		
		numberToolbooked = QuantitySpinner.getValue();
		 Stage stage = (Stage) closeButton.getScene().getWindow();
		    stage.close();
		
    }

	public static boolean isDone() {
		return done;
	}

	public static void setDone(boolean done) {
		QuantityBookingController.done = done;
	}
    
}
