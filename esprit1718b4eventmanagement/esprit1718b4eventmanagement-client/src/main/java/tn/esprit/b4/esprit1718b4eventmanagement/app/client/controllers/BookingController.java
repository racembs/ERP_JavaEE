package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import com.jfoenix.controls.JFXDatePicker;
import java.io.IOException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Booking;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.BookingPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.BookingServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

/**
 * FXML Controller class
 *
 * @author eya
 */
public class BookingController implements Initializable {

	@FXML
	private TableColumn<Tool, String> reference;
	@FXML
	private TableColumn<Tool, String> descriptionA;
	@FXML
	private TableColumn<Tool, Integer> av_quent;
	@FXML
	private TableColumn<Tool, String> studentACourseColumnSec;
	@FXML
	public TableColumn<Tool, String> referenceB;
	@FXML
	public TableColumn<Tool, Integer> reserv_quent;
	@FXML
	public TableColumn<Tool, String> studentCCourseColumnSec;
	@FXML
	public TableColumn<Tool, String> descriptionC;
	@FXML
	private TextField registrationTFSearch;
	@FXML
	private TableView<Tool> stockAllToolsTableView;
	@FXML
	public TableView<Tool> CurrentBookingTableView;
    @FXML
    private JFXDatePicker EndDate;

	private static int ID;
	private static int spin;
	

	public static int getSpin() {
		return spin;
	}

	public static void setSpin(int spin) {
		BookingController.spin = spin;
	}
   
	public BookingController() {
		super();
	}

	public static int getID() {
		return ID;
	}

	public static void setID(int iD) {
		ID = iD;
	}

	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		Context context = null;
		try {
			context = new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String TOOLjndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedToolService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote";
		NeedToolServiceRemote proxyT = null;
		try {
			proxyT = (NeedToolServiceRemote) context.lookup(TOOLjndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reference.setCellValueFactory(new PropertyValueFactory<Tool, String>("Reference"));
		av_quent.setCellValueFactory(new PropertyValueFactory<Tool, Integer>("Quantity"));
		studentACourseColumnSec.setCellValueFactory(new PropertyValueFactory<Tool, String>("Tool_Family"));
		descriptionA.setCellValueFactory(new PropertyValueFactory<Tool, String>("Description"));

		List<Tool> list = proxyT.displayAll();
		ObservableList<Tool> items = FXCollections.observableArrayList(list);
		stockAllToolsTableView.setItems(items);
		

	}

	@FXML
	private void setCourseCloseButtonClick(ActionEvent event) {
	}

	@FXML
	private void setCourseAboutButtonClick(ActionEvent event) {
	}

	@FXML
	private void onToolsBDeleteClick(ActionEvent event) {
	}

	@FXML
	private void onToolsTakeClick(ActionEvent event) throws IOException {
		int id = stockAllToolsTableView.getSelectionModel().getSelectedItem().getId_Need();
		String p = stockAllToolsTableView.getSelectionModel().getSelectedItem().getReference();
		int p1 = stockAllToolsTableView.getSelectionModel().getSelectedItem().getQuantity();
		String p2 = stockAllToolsTableView.getSelectionModel().getSelectedItem().getDescription();
		String p4 = stockAllToolsTableView.getSelectionModel().getSelectedItem().getTool_Family();

		Tool newtool = new Tool();
		newtool.setId_Need(id);
		newtool.setId_Need(id);
		newtool.setReference(p);
		newtool.setDescription(p2);
		newtool.setTool_Family(p4);
		

		QuantityBookingController re = new QuantityBookingController();
		re.setNumberTool(p1);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/QuantityBooking.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.showAndWait();
		
		int quant =	QuantityBookingController.getNumberToolbooked();
		newtool.setQuantity(quant);
		
		referenceB.setCellValueFactory(new PropertyValueFactory<Tool, String>("Reference"));
		reserv_quent.setCellValueFactory(new PropertyValueFactory<Tool, Integer>("Quantity"));
		studentCCourseColumnSec.setCellValueFactory(new PropertyValueFactory<Tool, String>("Tool_Family"));
		descriptionC.setCellValueFactory(new PropertyValueFactory<Tool, String>("Description"));

		CurrentBookingTableView.getItems().add(newtool);
				

	}

	

	@FXML
	private void setBookingDoneClick(ActionEvent event) throws NamingException {
		//System.out.println(CurrentBookingTableView.getItems().size());
		
		Context context;
		context = new InitialContext();
		
		String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
		 WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
		 
		 String jndiBooking="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/BookingService!tn.esprit.b4.esprit1718b4eventmanagement.services.BookingServiceRemote";
		    BookingServiceRemote proxyB =(BookingServiceRemote) context.lookup(jndiBooking);
		    UsualWork wr = proxy.findById(6);
		    java.sql.Date gettedDatePickerDate = java.sql.Date.valueOf(EndDate.getValue());
		    if (gettedDatePickerDate.before(wr.getWODate()))
		    {
		    	Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Wrong Date");
				alert.setHeaderText("Plz make sure the date is valid");
				alert.showAndWait();
		    }
		    else
		    {
		    	
		for (int i=0 ; i<CurrentBookingTableView.getItems().size() ; i++)
		{
			Booking b = new Booking();
			BookingPK bpk = new BookingPK();
			Tool t = CurrentBookingTableView.getItems().get(i);
			//System.out.println(t.getQuantity());
			bpk.setId_Need(t.getId_Need());
			bpk.setId_works(ID);
			b.setBookingPK(bpk);
			b.setQuantity(t.getQuantity());
	
			 
			
			b.setBookDate(wr.getWODate());
			
			b.setReleaseDate(gettedDatePickerDate);
		
		    
		    proxyB.addBooking(b);
		    System.out.println("done with " + i);
			
		}

		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Tools Successfully added");
		alert.setHeaderText("Booking done!");
		alert.showAndWait();
		    }
		
	}

	@FXML
	private void onBookedToolsRefresh(ActionEvent event) {
	}

	@FXML
	private void onToolSearchClick(ActionEvent event) {
	}

}
