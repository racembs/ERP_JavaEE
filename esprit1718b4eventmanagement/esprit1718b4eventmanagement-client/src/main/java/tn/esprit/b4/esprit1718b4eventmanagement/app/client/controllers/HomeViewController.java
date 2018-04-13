/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXPopup;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

/**
 * FXML Controller class
 *
 * @author danml
 */
public class HomeViewController implements Initializable {

    @FXML
    private JFXHamburger hamburger;
    @FXML
    private AnchorPane holderPane;
    @FXML
    private JFXDrawer drawer;
    @FXML
    private Label txtCurrentWindow;
    @FXML
  private Label count;
	/**  static 
	 * Initializes the controller class.
	 *
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
//count.setVisible(false);

		Context contextkk;
			 try {
				
				 contextkk = new InitialContext();
			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxykk=(WorksUsServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote");
						 if(LoginController.user.getPost().equals("technician"))
						 {	count.setVisible(true);
							 List<UsualWork> list = proxykk.displayWObyTechStart(LoginController.user.getId());
						 String str = Integer.toString(list.size());
						 System.out.println("notifffffff"+list.size());
						 
				    count.setText(str);
				    Tooltip tooltip_userName=new Tooltip("Work Orders Not Started");
				    
				 // Set tooltip
				 count.setTooltip(tooltip_userName);
				 Tooltip.install(count, tooltip_userName);
				 }
						 else
						 {
							 if(LoginController.user.getPost().equals("engineer"))
							 {count.setVisible(true);
								 List<UsualWork> list = proxykk.displayWRB();
								 String str = Integer.toString(list.size());
								 System.out.println(list.size());
								 
						    count.setText(str);
						    Tooltip tooltip_userName=new Tooltip("Work Requests Not approuved");
						    
						 // Set tooltip
						 count.setTooltip(tooltip_userName); 
						 Tooltip.install(count, tooltip_userName);
						 
							 }
						 }
				  
				 // Or using Tooltip.install
				 
			} catch (NamingException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	
		HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
		transition.setRate(-1);
		hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent e) -> {
			transition.setRate(transition.getRate() * -1);
			transition.play();

			if (drawer.isShown()) {
				drawer.close();
			} else {
				drawer.open();
			}

		});
		try {
			VBox sidePane = FXMLLoader.load(getClass().getResource("/views/Drawer.fxml"));
			AnchorPane wrPane = FXMLLoader.load(getClass().getResource("/views/WorkUs.fxml"));
			AnchorPane ndPane = FXMLLoader.load(getClass().getResource("/views/NeedsStock.fxml"));
			AnchorPane woPane = FXMLLoader.load(getClass().getResource("/views/WorkOs.fxml"));
			AnchorPane Profile = FXMLLoader.load(getClass().getResource("/views/Profile.fxml"));
			AnchorPane Equipment = FXMLLoader.load(getClass().getResource("/views/Equipement.fxml"));
			drawer.setSidePane(sidePane);

			for (Node node : sidePane.getChildren()) {
				if (node.getAccessibleText() != null) {
					node.addEventHandler(MouseEvent.MOUSE_PRESSED, (MouseEvent ev) -> {
						switch (node.getAccessibleText()) {

						case "WorkRequest":
							drawer.close();
							setNode(wrPane);
							break;
						case "doctorMenu":
							drawer.close();
							setNode(Profile);
							break;
						case "WorkOrder":
							drawer.close();
							setNode(woPane);
							break;
						case "appointmentMenu":
							drawer.close();
							setNode(Profile);
							break;
							
						case "Equipment":
							drawer.close();
							setNode(Equipment);
							break;


						case "NeedsStock":
							drawer.close();
							setNode(ndPane);

						}
					});
				}

			}

		} catch (IOException ex) {
			Logger.getLogger(HomeViewController.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

    private void setNode(Node node) {
        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);
    }


}
