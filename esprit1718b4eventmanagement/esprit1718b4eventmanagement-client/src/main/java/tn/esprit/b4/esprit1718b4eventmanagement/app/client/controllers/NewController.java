package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;

import com.jfoenix.controls.JFXButton;
import javafx.event.ActionEvent;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableRow;
import javafx.scene.control.Spinner;
import javafx.scene.control.TreeItem;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeView;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.cell.*;
import javafx.scene.text.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javafx.scene.control.TreeItem;

import java.util.List;
import java.util.ResourceBundle;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Cell;
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

import javax.naming.Context;
import javax.naming.InitialContext;

import javax.naming.NamingException;
import javax.script.Bindings;

import org.controlsfx.control.PopOver;
import org.jfree.ui.RefineryUtilities;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.Shape;
import javafx.scene.control.Tooltip;
import java.util.Arrays;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers.DateAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers.GanttChart.ExtraData;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.gui.GanttChart;

import tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote;
import java.sql.*;
/**
 * FXML Controller class
 *
 * @author ismail
 */
public class NewController implements Initializable {

    @FXML
    private Label l12;
    @FXML
    private JFXTextField fx4;
    @FXML
    private JFXTextField fx1;
    @FXML
    private JFXTextField fx2;
    @FXML
    private JFXTextField fx3;
    @FXML
    private JFXDatePicker fx5;
    @FXML
    private Spinner<Integer> fx6;
    @FXML
    private JFXButton savepop;

    /**
     * Initializes the controller class.
     */
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    	//WorkOsController.newprev
    	fx1.setText(WorkOsController.newprev.getObjet());
    	fx2.setText(WorkOsController.newprev.getDescription());
    	fx3.setText(WorkOsController.newprev.getFrequency());
    	fx4.setText(WorkOsController.newprev.getTechnology());
    	//LocalDate date = WorkOsController.newprev.getEndDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    	LocalDate ld =  new java.sql.Date( WorkOsController.newprev.getEndDate().getTime() ).toLocalDate();
    	fx5.setValue(ld);
    	long diff2=WorkOsController.newprev.getStartDate().getTime()-WorkOsController.newprev.getTriggerD().getTime();
		long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
		String s=String.valueOf(diffDays2);
    	fx6.getValueFactory().setValue(Integer.parseInt(s));
    	  Tooltip tooltip1=new Tooltip("Object");
		    
			 // Set tooltip
			 fx1.setTooltip(tooltip1); 
			 Tooltip.install(fx1, tooltip1);
			 ////////////////////////////
			  Tooltip tooltip2=new Tooltip("Add.Info");
			    
				 // Set tooltip
				 fx2.setTooltip(tooltip2); 
				 Tooltip.install(fx2, tooltip2);
				 ////////////////////////////
				  Tooltip tooltip3=new Tooltip("Frequency");
				    
					 // Set tooltip
					 fx3.setTooltip(tooltip3); 
					 Tooltip.install(fx3, tooltip3);
					 ////////////////////////////
					  Tooltip tooltip4=new Tooltip("Technology");
					    
						 // Set tooltip
						 fx4.setTooltip(tooltip4); 
						 Tooltip.install(fx4, tooltip4);
						 ////////////////////////////
						  Tooltip tooltip5=new Tooltip("Ending date");
						    
							 // Set tooltip
							 fx5.setTooltip(tooltip5); 
							 Tooltip.install(fx5, tooltip5);
							 ////////////////////////////
							  Tooltip tooltip6=new Tooltip("Warning days");
							    
								 // Set tooltip
								 fx6.setTooltip(tooltip6); 
								 Tooltip.install(fx6, tooltip6);
								 
    }    

   
	@FXML
    private void onclickSave(ActionEvent event) throws NamingException, IOException {

		 Context context;
		context = new InitialContext();
	   	
	     String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote";
	     	
	     	WorkPrevServiceRemote proxy=(WorkPrevServiceRemote) context.lookup(jndiName);
    	WorkOsController.newprev.setObjet(fx1.getText());	
    	WorkOsController.newprev.setDescription(fx2.getText());
    	WorkOsController.newprev.setFrequency(fx3.getText());
    	WorkOsController.newprev.setTechnology(fx4.getText());
    	 LocalDate localDate = fx5.getValue();

	        //Convert LocalDate to Date
	        Date dateFromPicker = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
	        System.out.println("Hello 1");
    	WorkOsController.newprev.setEndDate(dateFromPicker);
    	LocalDate ld =  new java.sql.Date( WorkOsController.newprev.getStartDate().getTime() ).toLocalDate();
    	
    	int day= ld.getDayOfMonth();
	     Calendar calendar = Calendar.getInstance();
	 	calendar.setTime(dateFromPicker);
	 	calendar.set(Calendar.DAY_OF_MONTH,day-fx6.getValue());
	 	Date dt=calendar.getTime();
    	WorkOsController.newprev.setTrigger(dt);
    	proxy.update(WorkOsController.newprev);
    	
        
    	System.out.println("Hello 2");
    }
    
}
