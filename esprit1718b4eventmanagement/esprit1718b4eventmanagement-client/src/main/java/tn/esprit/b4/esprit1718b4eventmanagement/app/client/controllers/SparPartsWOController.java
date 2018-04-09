/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.jfoenix.controls.JFXDatePicker;

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
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.services.BookingServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

/**
 * FXML Controller class
 *
 * @author asus
 */
public class SparPartsWOController implements Initializable {

    @FXML
    private TableView<SpareParts> stockAllToolsTableView;
    @FXML
	private TableColumn<SpareParts, String> reference;
	@FXML
	private TableColumn<SpareParts, String> descriptionA;
	@FXML
	private TableColumn<SpareParts, Integer> av_quent;
	@FXML
	private TableColumn<SpareParts, String> studentACourseColumnSec;
	@FXML
	public TableColumn<SpareParts, String> referenceB;
	@FXML
	public TableColumn<SpareParts, Integer> reserv_quent;
	@FXML
	public TableColumn<SpareParts, String> studentCCourseColumnSec;
	@FXML
	public TableColumn<SpareParts, String> descriptionC;
	@FXML
	private TextField registrationTFSearch;
	@FXML
	public TableView<SpareParts> CurrentBookingTableView;
    @FXML
    private JFXDatePicker EndDate;

	private static int ID;
	private static int spin;

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
		String SparejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedSparePartsService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote";
		NeedSparePartsServiceRemote proxyT = null;
		try {
			proxyT = (NeedSparePartsServiceRemote) context.lookup(SparejndiName);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		reference.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Reference"));
		av_quent.setCellValueFactory(new PropertyValueFactory<SpareParts, Integer>("Quantity"));
		studentACourseColumnSec.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Tool_Family"));
		descriptionA.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Description"));

		List<SpareParts> list = proxyT.displayAll();
		ObservableList<SpareParts> items = FXCollections.observableArrayList(list);
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
		String p4 = stockAllToolsTableView.getSelectionModel().getSelectedItem().getFamily();

		SpareParts newtool = new SpareParts();
		newtool.setId_Need(id);
		newtool.setId_Need(id);
		newtool.setReference(p);
		newtool.setDescription(p2);
		newtool.setFamily(p4);
		

		QuantityBookingController re = new QuantityBookingController();
		re.setNumberTool(p1);
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/QuantityBooking.fxml"));
		Parent root1 = (Parent) fxmlLoader.load();
		Stage stage = new Stage();
		stage.setScene(new Scene(root1));
		stage.showAndWait();
		
		int quant =	QuantityBookingController.getNumberToolbooked();
		newtool.setQuantity(quant);
		
		referenceB.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Reference"));
		reserv_quent.setCellValueFactory(new PropertyValueFactory<SpareParts, Integer>("Quantity"));
		studentCCourseColumnSec.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Tool_Family"));
		descriptionC.setCellValueFactory(new PropertyValueFactory<SpareParts, String>("Description"));

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
    			  
    			    	
    			for (int i=0 ; i<CurrentBookingTableView.getItems().size() ; i++)
    			{
    				Booking b = new Booking();
    				BookingPK bpk = new BookingPK();
    				SpareParts t = CurrentBookingTableView.getItems().get(i);
    				//System.out.println(t.getQuantity());
    				bpk.setId_Need(t.getId_Need());
    				bpk.setId_works(5);
    				b.setBookingPK(bpk);
    				b.setQuantity(t.getQuantity());
    				
                  // to do ( ma3maltch tna9es fl quantité )
	
    				b.setBookDate(wr.getWODate());    			
    			    
    			    proxyB.addBooking(b);
    			    System.out.println("done with " + i);
    				
    			}

    			Alert alert = new Alert(AlertType.CONFIRMATION);
    			alert.setTitle("Tools Successfully added");
    			alert.setHeaderText("Booking done!");
    			alert.showAndWait();
    			    
    }

    @FXML
    private void onBookedToolsRefresh(ActionEvent event) {
    }

    @FXML
    private void onToolSearchClick(ActionEvent event) {
    }
    
}
