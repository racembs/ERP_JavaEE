/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
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
import javafx.event.*;
import javafx.scene.control.cell.*;
import javafx.scene.text.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileOutputStream;
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

import javax.mail.MessagingException;
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
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
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
import com.jfoenix.controls.JFXProgressBar;
/**
 * FXML Controller class
 *
 * @author ADMIN
 */
public class WorkOsController implements Initializable {
	  @FXML
	    private JFXProgressBar allworksPG;
	    @FXML
	    private Label labAllworks;
	    @FXML
	    private JFXProgressBar doneworksPG;
	    @FXML
	    private Label donelab;
	    @FXML
	    private JFXProgressBar progworksPG;
	    @FXML
	    private Label progLab;
    @FXML
    private JFXTreeView<String> treeviewEq;
    @FXML
    private Tab tabD;
    @FXML
    private Tab prevtab;
    @FXML
    private TableView<UsualWork> table;
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
    private TableColumn<UsualWork, String> C6;
    @FXML
    private TableColumn<UsualWork, String> C7;
    @FXML
    private TableColumn<UsualWork, String> C8;
    @FXML
    private TableColumn<UsualWork, String> C9;
    @FXML
    private TableColumn<PreventiveWork, String> d1;
    @FXML
    private TableColumn<PreventiveWork, String> d2;
    @FXML
    private TableColumn<PreventiveWork, String> d3;
    @FXML
    private TableColumn<PreventiveWork, String> d4;
    @FXML
    private TableColumn<PreventiveWork, String> d5;
    @FXML
    private TableColumn<PreventiveWork, String> d6;
    @FXML
    private TableColumn<PreventiveWork, String> d7;
    @FXML
    private TableColumn<PreventiveWork, String> d8;
    @FXML
    private TableColumn<PreventiveWork, String> d9;
    @FXML
    private TableColumn<PreventiveWork, String> d10;
    @FXML
    private TableColumn<PreventiveWork, String> d11;
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
    private JFXButton start;
    @FXML
    private JFXButton delete;
    @FXML
    private JFXButton done;
    @FXML
    private Spinner<Integer> spinner;
    @FXML
    private Spinner<Integer> spinner1;
    @FXML
    private JFXTextField object;
    @FXML
    private JFXTextField search;
    @FXML
    private JFXTextArea info;
    @FXML
    private JFXComboBox<String> combotech;
    @FXML
    private JFXComboBox<String> techniciancombo;
    @FXML
    private JFXDatePicker startdate;
    @FXML
    private JFXDatePicker enddate;
    @FXML
    private Label l12;
    @FXML
    private TableView<?> tableviewsearch;
    @FXML
    private TableView<PreventiveWork> tableview1;
 
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton eya1;
    @FXML
    private JFXButton eya2;
    @FXML
    private TableColumn<PreventiveWork, String> p1;
    @FXML
    private TableColumn<PreventiveWork, String> p2;
    @FXML
    private TableColumn<PreventiveWork, String> p3;
    @FXML
    private TableColumn<PreventiveWork, String> p4;
    @FXML
    private TableColumn<PreventiveWork, String> p5;
    @FXML
    private TableColumn<PreventiveWork, String> p6;
    @FXML
    private TableColumn<PreventiveWork, String> p7;
    @FXML
    private TableColumn<PreventiveWork, String> p8;
    @FXML
    private TableColumn<PreventiveWork, String> p9;
    @FXML
    private TableColumn<PreventiveWork, String> p10;
    @FXML
    private TableColumn<PreventiveWork, String> p11;
    @FXML
    private TableColumn<PreventiveWork, String> p12;
    static PreventiveWork workp;
    static UsualWork work;
    static PreventiveWork xxx2=new PreventiveWork();
    static PreventiveWork newprev;
    @FXML
    private TableView<PreventiveWork> prevtable;
    @FXML
    private JFXButton ganttbtn;
    /**
     * Initializes the controller class.
     */
    @SuppressWarnings("restriction")
	@Override
    public void initialize(URL url, ResourceBundle rb) {
    	

    	User user = LoginController.user;
    	if (user.getPost().equals("technician"))
    	{
    		prevtab.setDisable(true);
    	}
    	else
    	{
    		tableview1.setVisible(false);
    	}

    	combotech.getItems().addAll("Mechanical","Electrical ","hydraulic"
    	        ,"unspecified");


    	 Image icon = new Image (
    			   getClass().getResourceAsStream("/views/imgs/equi.png"));
    		
  				TreeItem<String> equii =  new TreeItem<>("Entreprise", new ImageView(icon));
    			  equii.setExpanded(true);
    	delete.setVisible(false);
    	done.setVisible(false);
    	start.setVisible(false);
    	eya1.setVisible(false);
    	eya2.setVisible(false);
 	 Image icon2 = new Image (
			   getClass().getResourceAsStream("/views/imgs/rsz_1rsz_trash-bin-open.png"));
		delete.setGraphic(new ImageView(icon2));
		done.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 80px; " +
                "-fx-min-height: 80px; " +
                "-fx-max-width: 80px; " +
                "-fx-max-height: 80px;"
        );
		start.setStyle(
				
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 80px; " +
                "-fx-min-height: 80px; " +
                "-fx-max-width: 80px; " +
                "-fx-max-height: 80px;"
        );
		table.setEditable(true);
 	//UserServiceRemote userService2;
  	 // l1.setVisible(false);
		
		try {
			DETAILSPrev();
			DETAILSP();
			Context contextkk;
				 contextkk = new InitialContext();
			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxykk=(WorksUsServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote");
		
			 		///////////////////////////
		    		C1.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("objet"));
		    		C2.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("description"));
		    		C3.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("technology"));
		    		C4.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("orderstate"));
		    		C5.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("emmergency"));
				    C6.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("WODate"));
				    C8.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("endDate"));
				    C9.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("startDate"));
				    C7.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
				    
				      
			
				        table.setRowFactory(tv -> new TableRow<UsualWork>() {
				            @Override
				            public void updateItem(UsualWork item, boolean empty) {
				                super.updateItem(item, empty) ;
				                if (item == null) {
				                    setStyle("");
				                } else if (item.getId()==1) {
				                    setStyle("");
				                } else {
				                    setStyle("");
				                }
				            }
				        });

Tooltip tooltip_userName=new Tooltip("Work orders table");
 
// Set tooltip
table.setTooltip(tooltip_userName);
 
// Or using Tooltip.install
Tooltip.install(table, tooltip_userName);

		    		
if(LoginController.user.getPost().equals("technician"))
		    		{
		    			 List<UsualWork> list = proxykk.displayWObyTech(LoginController.user.getId());
		    			 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    		
		    			 
		    			 table.setItems(items);
		    		//     System.out.println(items.get(0).getDescription()+"tableauuuuuu1");
		    		}
		    		else
		    		{ List<UsualWork> list = proxykk.displayWO();
		    		 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    		 table.setItems(items);
		    	     System.out.println(items.get(0).getDescription()+"tableauuuuuu");
		    	    
		    	     }
List<UsualWork> listprog = proxykk.displayWO();
List<UsualWork> listdone = proxykk.displayDone();
List<UsualWork> liststart = proxykk.displayStart();
allworksPG.setProgress(listprog.size());
String str = Integer.toString(listprog.size());
labAllworks.setText(str);
String str1 = Integer.toString(listdone.size());
String str2 = Integer.toString(liststart.size());
doneworksPG.setProgress(listdone.size()/listprog.size());
donelab.setText(str1);
progworksPG.setProgress(liststart.size()/listprog.size());
progLab.setText(str2);

		    		table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
		    			if (user.getPost().equals("technician"))
		    	    	{
		    			//pane.setVisible(true);
		    			if(newValue.getOrderstate().equals("start"))
		    			{
		    				start.setVisible(false);
		    				done.setVisible(true);
		    				eya1.setVisible(true);
		    		    	eya2.setVisible(true);
		    			}
		    			else {
		    			if(newValue.getOrderstate().equals("done"))
		    			{
		    				start.setVisible(false);
		    				done.setVisible(false);
		    				delete.setVisible(true);
		    				eya1.setVisible(false);
		    		    	eya2.setVisible(false);
		    			}
		    			else{
		    				start.setVisible(true);
		    				done.setVisible(false);
		    				delete.setVisible(false);
		    				eya1.setVisible(true);
		    				eya2.setVisible(true);
		    				
		    			}
		    			}}
		    			work=newValue;
		    		}));
		    		
		    		table.getSelectionModel().setCellSelectionEnabled(true);
		    		ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
		    		selectedCells.addListener(new ListChangeListener() {
		    		    @Override
		    		    public void onChanged(Change c) {
		    		    	TablePosition<UsualWork, String> tablePosition = (TablePosition) selectedCells.get(0);
		    		    	int row = tablePosition.getRow();
		    		    	work=table.getItems().get(row);
		    		    	 if(tablePosition.getTableColumn().getText().equals("Equipment(Ref--Info)"))
		    		        {showdetails(table.getItems().get(row).getEquipement());
		    		    		 Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
		    		      l12.setVisible(true);
		    		        System.out.println("Selected Value" + val);
		    		        
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
			ObservableList<String> obList = FXCollections.observableList(lun);
			
			techniciancombo.getItems().addAll(obList);
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
	  		
	  		 	}
	  		 	}
				  treeviewEq.setRoot(equii);
		    	

				treeviewEq.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

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
			

	    	   
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
tabD.setOnSelectionChanged(new EventHandler<Event>() {
			
			@Override
			public void handle(Event event) {
				// TODO Auto-generated method stub
				if(tabD.isSelected())
				{
					inittt();
					System.out.println("taaaaaaaaaab" );	
					
				}
				
			}
		});

	
    }   
    private void inittt() {
    	User user = LoginController.user;
    	if (user.getPost().equals("technician"))
    	{
    		prevtab.setDisable(true);
    	}
    	else
    	{
    		tableview1.setVisible(false);
    	}

    	combotech.getItems().addAll("Mechanical","Electrical ","hydraulic"
    	        ,"unspecified");


    	 Image icon = new Image (
    			   getClass().getResourceAsStream("/views/imgs/equi.png"));
    		
  				TreeItem<String> equii =  new TreeItem<>("Entreprise", new ImageView(icon));
    			  equii.setExpanded(true);
    	delete.setVisible(false);
    	done.setVisible(false);
    	start.setVisible(false);
    	eya1.setVisible(false);
    	eya2.setVisible(false);
 	 Image icon2 = new Image (
			   getClass().getResourceAsStream("/views/imgs/rsz_1rsz_trash-bin-open.png"));
		delete.setGraphic(new ImageView(icon2));
		done.setStyle(
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 80px; " +
                "-fx-min-height: 80px; " +
                "-fx-max-width: 80px; " +
                "-fx-max-height: 80px;"
        );
		start.setStyle(
				
                "-fx-background-radius: 5em; " +
                "-fx-min-width: 80px; " +
                "-fx-min-height: 80px; " +
                "-fx-max-width: 80px; " +
                "-fx-max-height: 80px;"
        );
		table.setEditable(true);
 	//UserServiceRemote userService2;
  	 // l1.setVisible(false);
		
		try {
			DETAILSPrev();
			DETAILSP();
			Context contextkk;
				 contextkk = new InitialContext();
			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
			 WorksUsServiceRemote proxykk=(WorksUsServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote");
		
	
		    		C1.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("objet"));
		    		C2.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("description"));
		    		C3.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("technology"));
		    		C4.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("orderstate"));
		    		C5.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("emmergency"));
				    C6.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("WODate"));
				    C8.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("endDate"));
				    C9.setCellValueFactory(new PropertyValueFactory<UsualWork, String>("startDate"));
				    C7.setCellValueFactory(new Callback<CellDataFeatures<UsualWork,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<UsualWork, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
				    
				      
			
				        table.setRowFactory(tv -> new TableRow<UsualWork>() {
				            @Override
				            public void updateItem(UsualWork item, boolean empty) {
				                super.updateItem(item, empty) ;
				                if (item == null) {
				                    setStyle("");
				                } else if (item.getId()==1) {
				                    setStyle("");
				                } else {
				                    setStyle("");
				                }
				            }
				        });

Tooltip tooltip_userName=new Tooltip("Work orders table");
 
// Set tooltip
table.setTooltip(tooltip_userName);
 
// Or using Tooltip.install
Tooltip.install(table, tooltip_userName);

		    		
if(LoginController.user.getPost().equals("technician"))
		    		{
		    			 List<UsualWork> list = proxykk.displayWObyTech(LoginController.user.getId());
		    			 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    		
		    			 
		    			 table.setItems(items);
		    		     System.out.println(items.get(0).getDescription()+"tableauuuuuu1");
		    		}
		    		else
		    		{ List<UsualWork> list = proxykk.displayWO();
		    		 ObservableList<UsualWork> items = FXCollections.observableArrayList(list);
		    		 table.setItems(items);
		    	     System.out.println(items.get(0).getDescription()+"tableauuuuuu");
		    	    
		    	     }
	    	       
	    	
		    		table.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
		    			if (user.getPost().equals("technician"))
		    	    	{
		    			//pane.setVisible(true);
		    			if(newValue.getOrderstate().equals("start"))
		    			{
		    				start.setVisible(false);
		    				done.setVisible(true);
		    			}
		    			else {
		    			if(newValue.getOrderstate().equals("done"))
		    			{
		    				start.setVisible(false);
		    				done.setVisible(false);
		    				delete.setVisible(true);
		    			}
		    			else{
		    				start.setVisible(true);
		    				done.setVisible(false);
		    				delete.setVisible(false);
		    				eya1.setVisible(true);
		    				eya2.setVisible(true);
		    				
		    			}
		    			}}
		    			work=newValue;
		    		}));
		    		
		    		table.getSelectionModel().setCellSelectionEnabled(true);
		    		ObservableList selectedCells = table.getSelectionModel().getSelectedCells();
		    		selectedCells.addListener(new ListChangeListener() {
		    		    @Override
		    		    public void onChanged(Change c) {
		    		    	TablePosition<UsualWork, String> tablePosition = (TablePosition) selectedCells.get(0);
		    		    	int row = tablePosition.getRow();
		    		    	work=table.getItems().get(row);
		    		    	 if(tablePosition.getTableColumn().getText().equals("Equipment(Ref--Info)"))
		    		        {showdetails(table.getItems().get(row).getEquipement());
		    		    		 Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
		    		      l12.setVisible(true);
		    		        System.out.println("Selected Value" + val);
		    		        
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
			ObservableList<String> obList = FXCollections.observableList(lun);
			
			techniciancombo.getItems().addAll(obList);
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
	  		
	  		 	}
	  		 	}
				  treeviewEq.setRoot(equii);
		    	

				treeviewEq.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<TreeItem<String>>() {

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
			

	    	   
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
    }
	private void showdetails(Equipment trader) {

		
		f1.setText("description: "+trader.getDescription());
	f2.setText("EIS date: "+trader.getEISDate());
		f3.setText("fabriquant: "+trader.getFabriquant());
		f4.setText("marque: "+trader.getMarque());
		f5.setText("lieu: "+trader.getLieu());

	}

    @FXML
    private void onclickSave(ActionEvent event) {
    	 System.out.println("spinneeeeeer"+spinner.getValue());
    	
     	try {
     		 Context context;
			context = new InitialContext();
		   	
		     String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote";
		     	
		     	WorkPrevServiceRemote proxy=(WorkPrevServiceRemote) context.lookup(jndiName);
		     	UserServiceRemote userServicej;
		    	
					userServicej = (UserServiceRemote) context
							.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
					
					 User tech =userServicej.userbyfstlstname(techniciancombo.getSelectionModel().getSelectedItem());
		     	
		     	//UsualWork uw =xxx2;
		 //    System.out.println(LoginController.user.getLogin());
		
		     	xxx2.setUser(LoginController.user);
		     	xxx2.setObjet(object.getText());
		     	xxx2.setDescription(info.getText());
		     	xxx2.setTechnology(combotech.getValue().toString());
		        LocalDate localDate = startdate.getValue();

		        //Convert LocalDate to Date
		        Date dateFromPicker = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

		     	 xxx2.setStartDate(dateFromPicker);
		     	 LocalDate localDate2 = enddate.getValue();

			        //Convert LocalDate to Date
			     Date dateFromPicker2 = Date.from(localDate2.atStartOfDay(ZoneId.systemDefault()).toInstant());
			     
			     
			     xxx2.setEndDate(dateFromPicker2);
				 LocalDate localDate3 = startdate.getValue();
				
				 int day= localDate.getDayOfMonth();
			     Calendar calendar = Calendar.getInstance();
			 	calendar.setTime(dateFromPicker);
			 	calendar.set(Calendar.DAY_OF_MONTH,day-spinner1.getValue());
			 	Date dt=calendar.getTime();
			 	
			        //Convert LocalDate to Date
			   //  Date dateFromPicker3 = Date.from(localDate3.atStartOfDay(ZoneId.systemDefault()).toInstant());
			     xxx2.setTrigger(dt);
		         xxx2.setFrequency(spinner.getValue().toString());
		         if(dateFromPicker.after(dateFromPicker2)){
		        	 Alert alert = new Alert(AlertType.WARNING);
				 		alert.setTitle("Date error");
				 		alert.setHeaderText("starting date need to be inferior to the ending date");
				 		alert.showAndWait(); 
		         }
		         else {
		         xxx2.setState("approuved");

		         xxx2.setCreatDate(new Date());
		         System.out.println(tech.getId());
		         xxx2.setTechnicianId(tech.getId());
		         System.out.println(xxx2.getTechnicianId()+"beghhhhhhhh");
		         proxy.addWP(xxx2);
		         Alert alert = new Alert(AlertType.INFORMATION);
		 		alert.setTitle("Work Request Adding");
		 		alert.setHeaderText("Succesful :) ");
		 		alert.showAndWait();}
		         DETAILSPrev();
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
  
    }
    @FXML
    private void onclickStart(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	 System.out.println(work.getDescription());
    	work.setOrderstate("start");
    	work.setStartDate(new Date());
    	proxy.updateWork(work);
    	 Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("start");
 		alert.setHeaderText("Selected work order state set to Start");
 		alert.showAndWait();
    	//l'affichage de l interface booking
 		start.setVisible(false);
    	
    }
    @FXML
    private void onclickDone(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();

    	Context contexthhh;
    	contexthhh = new InitialContext();
    	UserServiceRemote userService222;
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);

		userService222 = (UserServiceRemote) contexthhh.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
 	  	 User u=userService222.find(work.getTechnicianId());
    	 System.out.println(work.getDescription());
    	work.setOrderstate("done");
    	work.setEndDate(new Date());
    	proxy.updateWork(work);
    	 Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("done");
 		alert.setHeaderText("Selected work order state set to done! keep the good work ;) ");
 		alert.showAndWait();
    	//l'affichage de l interface booking
 		done.setVisible(false);	
 		  FileChooser fileChooser = new FileChooser();
          fileChooser.setTitle("Save Copy of Report");
          fileChooser.setInitialDirectory(
                  new File(System.getProperty("user.home"))
          );
          fileChooser.setInitialFileName("Report"+work.getObjet()+new Date()+".pdf");
          FileChooser.ExtensionFilter pdfExtensionFilter =
                  new FileChooser.ExtensionFilter(
                          "PDF - Portable Document Format (.pdf)", "*.pdf");
          fileChooser.getExtensionFilters().add(pdfExtensionFilter);
          fileChooser.setSelectedExtensionFilter(pdfExtensionFilter);
          File file = fileChooser.showSaveDialog(done.getContextMenu());

          if (file != null) {
              try {
            	  genereteReportPdf(work,u,file.getPath());
              } catch (Exception e) {
                  e.printStackTrace();
              }
          }
    }
    @FXML
    private void onclickDelete(ActionEvent event) throws NamingException {
    	
    	Context context;
    	context = new InitialContext();
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	 System.out.println(work.getDescription());
    	
    	proxy.deleteWork(work.getId());
    	 Alert alert = new Alert(AlertType.INFORMATION);
 		alert.setTitle("Delete");
 		alert.setHeaderText("Selected work order deleted");
 		alert.showAndWait();
    	//l'affichage de l interface booking
    	
    }
    private void DETAILSP() throws NamingException {
    	tableview1.setEditable(true);
     	//UserServiceRemote userService2;
      	 // l1.setVisible(false);
    		try {
    			

    			Context contextkk;
    				 contextkk = new InitialContext();
    			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    			 WorkPrevServiceRemote proxypp=(WorkPrevServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote");
 		     	
    	
    			 d7.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("frequency"));
		    		d3.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("objet"));
		    		d4.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("description"));
		    		d10.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("technology"));
		    		d8.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("state"));
		    	
				    d1.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("CreatDate"));
				    d6.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("endDate"));
				    d5.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("startDate"));
				    d11.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

			              @Override
			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
			              }
			          }); 
				    d2.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

		              @Override
		              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
		            	long diff2=param.getValue().getStartDate().getTime()-param.getValue().getTriggerD().getTime();
		      		long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
		                  return new SimpleStringProperty(diffDays2+"-days");
		              }
		          }); 
					Context contexthhh;
		  			contexthhh = new InitialContext();
		  	   
			   d9.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

		              @Override
		              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
		             	UserServiceRemote userService222;
						try {
							
							userService222 = (UserServiceRemote) contexthhh.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
					 	  	 User u=userService222.find(param.getValue().getTechnicianId());
			                  return new SimpleStringProperty(u.getFirstname()+"--"+u.getLastname());
						} catch (NamingException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
		      		
		            	  	
		                  return new SimpleStringProperty("-_-");
		              }
		          }); 
				    


 
// Set tooltip

    				    final ContextMenu contextMenu = new ContextMenu();
    				    MenuItem cut = new MenuItem("warning days!");
    				    MenuItem copy = new MenuItem("", new ImageView("/views/imgs/Ambox_warning_psycho.gif"));
    				  
    				    cut.setStyle("-fx-text-fill:black ;");
    				    contextMenu.setStyle("-fx-max-width: 110px;-fx-max-hight: 110px;");
        				
    				    contextMenu.getItems().addAll(copy);
    				    cut.setOnAction(new EventHandler<ActionEvent>() {
    				        @Override
    				        public void handle(ActionEvent event) {
    				            System.out.println("Cut...");
    				        }
    				    });
    			
    				        tableview1.setRowFactory(tv -> new TableRow<PreventiveWork>() {
    				        	
    				            @Override
    				            public void updateItem(PreventiveWork item, boolean empty) {
    				                super.updateItem(item, empty) ;
    				                if (item == null) {
    				                    setStyle("");
    				                } else if (VerifierWarn(item)) {
    				                    setStyle("-fx-background-color: tomato;");
    				                    setContextMenu(contextMenu);
    				                } else {
    				                    setStyle("");
    				                }
    				            }
    				        });

    Tooltip tooltip_userName=new Tooltip("Preventive Works  table");
     
    // Set tooltip
    tableview1.setTooltip(tooltip_userName);
     
    // Or using Tooltip.install
    Tooltip.install(tableview1, tooltip_userName);

    		    		if(LoginController.user.getPost().equals("technician"))
    		    		{
    		    			 List<PreventiveWork> list = proxypp.displayWPbyTech(LoginController.user.getId());
    		    			 ObservableList<PreventiveWork> items = FXCollections.observableArrayList(list);
    		    		
    		    			 
    		    			 tableview1.setItems(items);

    		    		}
    		    		else
    		    		{ List<PreventiveWork> list = proxypp.DisplayPWorks();
    		    		 ObservableList<PreventiveWork> items = FXCollections.observableArrayList(list);
    		    		 tableview1.setItems(items);
    		    	  //   System.out.println(items.get(0).getDescription()+"tableauuuuuu");
    		    	    
    		    	     }
    	    	       
    	    	
    		    		tableview1.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
    		    			
    		    			//pane.setVisible(true);
    		    			if(newValue.getState().equals("start"))
    		    			{
    		    				start.setVisible(false);
    		    				done.setVisible(true);
    		    			}
    		    			if(newValue.getState().equals("done"))
    		    			{
    		    				start.setVisible(false);
    		    				done.setVisible(false);
    		    				delete.setVisible(true);
    		    			}
    		    			workp=newValue;
    		    		}));
    		    		
    		    		tableview1.getSelectionModel().setCellSelectionEnabled(true);
    		    		ObservableList selectedCells = tableview1.getSelectionModel().getSelectedCells();
    		    		selectedCells.addListener(new ListChangeListener() {
    		    		    @Override
    		    		    public void onChanged(Change c) {
    		    		    	TablePosition<PreventiveWork, String> tablePosition = (TablePosition) selectedCells.get(0);
    		    		    	int row = tablePosition.getRow();
    		    		    	workp=tableview1.getItems().get(row);
    		    		    	 if(tablePosition.getTableColumn().getText().equals("Equipment(Ref--Info)"))
    		    		        {showdetails(tableview1.getItems().get(row).getEquipement());
    		    		    		 Object val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
    		    		      l12.setVisible(true);
    		    		        System.out.println("Selected Value" + val);
    		    		        
    		    		        }

    		    		    }
    		    		});  
    		} catch (NamingException e1) {
    			
    			
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	
    }
	private Boolean VerifierWarn(PreventiveWork wp) {
		// calculer today-startingday/ freq // nombre d'intervention effectués
		long diff= new Date().getTime()-wp.getStartDate().getTime();
		long diffDays = diff / (24 * 60 * 60 * 1000);
		long nb= diffDays/(Integer.parseInt(wp.getFrequency()));
		   System.out.println(" Value" + nb);
		///calcule de nbr de warning days depuis first trigger date
		long diff2=wp.getStartDate().getTime()-wp.getTriggerD().getTime();
		long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
		System.out.println(" nbr de warning days" + diffDays2);
		//get the warning day for next intervention
	     Calendar calendar = Calendar.getInstance();
	 	calendar.setTime(wp.getStartDate());
	 	long days=(nb*Integer.parseInt(wp.getFrequency()))+(Integer.parseInt(wp.getFrequency())-diffDays2);
	 	calendar.add(Calendar.DAY_OF_MONTH,Math.toIntExact(days));
	 	Date dt=calendar.getTime();
		System.out.println(" warning day" + dt);
		if(new Date().after(dt)||new Date().equals(dt))
		{
			return true;
		}
		else return false;
	}

    @FXML
    private void onBookTools(ActionEvent event) throws IOException {
    	BookingController re = new BookingController();
        re.setID(5); 
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/Booking.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }

    @FXML
    private void onAddSpares(ActionEvent event) throws IOException {
    	//SparPartsWOController
    	SparPartsWOController re = new SparPartsWOController();
        re.setID(5);
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SparPartsWO.fxml"));
        Parent root1 = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root1));  
        stage.show();
    }
    public void DETAILSPrev() throws NamingException {
    
     	//UserServiceRemote userService2;
      	 // l1.setVisible(false);
    		try {
    			

    			Context contextkk;
    				 contextkk = new InitialContext();
    			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    			 WorkPrevServiceRemote proxypp=(WorkPrevServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote");
    			
    			 p7.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("frequency"));
    		    		p3.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("objet"));
    		    		p4.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("description"));
    		    		p10.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("technology"));
    		    		p8.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("state"));
    		    	
    				    p1.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("CreatDate"));
    				    p6.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("endDate"));
    				    p5.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("startDate"));
    				    p11.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

    			              @Override
    			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
    			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
    			              }
    			          }); 
    				    p2.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

  			              @Override
  			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
  			            	long diff2=param.getValue().getStartDate().getTime()-param.getValue().getTriggerD().getTime();
  			      		long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
  			                  return new SimpleStringProperty(diffDays2+"-days");
  			              }
  			          }); 
    					Context contexthhh;
			  			contexthhh = new InitialContext();
			  	   
    			   p9.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

  			              @Override
  			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
  			             	UserServiceRemote userService222;
							try {
								
								userService222 = (UserServiceRemote) contexthhh.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
						 	  	 User u=userService222.find(param.getValue().getTechnicianId());
	  			                  return new SimpleStringProperty(u.getFirstname()+"--"+u.getLastname());
							} catch (NamingException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
  			      		
  			            	  	
  			                  return new SimpleStringProperty("-_-");
  			              }
  			          }); 
    				    
 
// Set tooltip

    				    final ContextMenu contextMenu = new ContextMenu();
    				    MenuItem cut = new MenuItem("!");
    				    MenuItem copy = new MenuItem("", new ImageView("/views/imgs/Ambox_warning_psycho.gif"));
    				  
    				    cut.setStyle("-fx-text-fill:white ;");
    				    contextMenu.setStyle("-fx-background-color: black;-fx-text-fill:white ;-fx-max-width: 100px;");
    				
    				    contextMenu.getItems().addAll(cut, copy);
    				    cut.setOnAction(new EventHandler<ActionEvent>() {
    				        @Override
    				        public void handle(ActionEvent event) {
    				            System.out.println("Cut...");
    				        }
    				    });
    			
    				        tableview1.setRowFactory(tv -> new TableRow<PreventiveWork>() {
    				        	
    				            @Override
    				            public void updateItem(PreventiveWork item, boolean empty) {
    				                super.updateItem(item, empty) ;
    				                if (item == null) {
    				                    setStyle("");
    				                } else if (VerifierWarn(item)) {
    				                    setStyle("-fx-background-color: tomato;");
    				                    setContextMenu(contextMenu);
    				                } else {
    				                    setStyle("");
    				                }
    				            }
    				        });

    Tooltip tooltip_userName=new Tooltip("Preventive Works table");
     
    // Set tooltip
    prevtable.setTooltip(tooltip_userName);
     
    // Or using Tooltip.install
    Tooltip.install(prevtable, tooltip_userName);

    		    		 List<PreventiveWork> list = proxypp.DisplayPWorks();
    		    		 ObservableList<PreventiveWork> items = FXCollections.observableArrayList(list);
    		    		 prevtable.setItems(items);
    		    	  //   System.out.println(items.get(0).getDescription()+"tableauuuuuu");
    		    	    
    		    	     
    	    	       
    	    	
    		    		prevtable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
    		    			
    		    		
    		    			
    		    		}));
    		    		MenuItem mi2 = new MenuItem("update");
    		    		MenuItem mi1 = new MenuItem("delete");
    		    		mi1.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                            	Context contextkk;
               				 try {
								contextkk = new InitialContext();
								WorkPrevServiceRemote proxypp=(WorkPrevServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote");
		               			
		                          
	    		    		    System.out.println("Menu item 1");
	    		    		    PreventiveWork item = prevtable.getSelectionModel().getSelectedItem();
	    		    		   
	    		    		    Alert alert = new Alert(AlertType.CONFIRMATION);
						    	alert.setTitle("WARNING");
								alert.setHeaderText("Are You Sure to remove this work?");
						    	
						    	if (alert.showAndWait().get () == ButtonType.OK)
						    	{    
	                            	
						    	System.out.println("remooooooove"+item.getId());
						    		 proxypp.remove(item.getId());
						    		 
										DETAILSPrev();
									
						    	}
							} catch (NamingException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
               			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
               			 
  }
                            
                        });
    		    		mi2.setOnAction((ActionEvent event) -> {
    		    		    System.out.println("Menu item 2");
    		    		    PreventiveWork item = prevtable.getSelectionModel().getSelectedItem();
    		    		    PopOver popOver = new PopOver();
    		    		    popOver.setArrowLocation(PopOver.ArrowLocation.RIGHT_CENTER);
    		    		    popOver.setContentNode(new Label("Test"));
    		    		    popOver.setAutoFix(true);
    		    		    popOver.setAutoHide(true);
    		    		    popOver.setHideOnEscape(true);
    		    		    popOver.setDetachable(false);
    		    		    newprev= prevtable.getSelectionModel().getSelectedItem();
    		    		    Node content = null;

    		    		    FXMLLoader fxmlLoader = new FXMLLoader();
    		    		    try {
    		    		    	//FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/SparPartsWO.fxml"));

								content = (Parent) fxmlLoader.load(getClass().getResourceAsStream("/views/new.fxml"));
								  popOver.setContentNode(content);

		    		    		    popOver.show(prevtable.getScene().getWindow());
		    		    		    popOver.focusedProperty().addListener((observable, oldValue, newValue) -> {
		    		    		        if (oldValue){    
		    		    		            // oldValue is true -> current value is false-> no focus               
		    		    		            // TODO your code here
		    		    		        	try {
												DETAILSPrev();
											} catch (Exception e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
		    		    		        }
		    		    		    });
    		    		    } catch (Exception e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
    		    		  
    		    		});

    		    		ContextMenu menu = new ContextMenu();
    		    		menu.getItems().add(mi1);
    		    		menu.getItems().add(mi2);
    		    		prevtable.setContextMenu(menu);	
    		    		
    		} catch (NamingException e1) {
    			
    			
    			// TODO Auto-generated catch block
    			e1.printStackTrace();
    		}
    	
    }
    @FXML
    private void recherchePrev(KeyEvent event) {
             String mot= search.getText();
    
         	//UserServiceRemote userService2;
          	 // l1.setVisible(false);
        		try {
        			

        			Context contextkk;
        				 contextkk = new InitialContext();
        			 //String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
        			 WorkPrevServiceRemote proxypp=(WorkPrevServiceRemote) contextkk.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorkPrevService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorkPrevServiceRemote");
        			
        			 p7.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("frequency"));
        		    		p3.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("objet"));
        		    		p4.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("description"));
        		    		p10.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("technology"));
        		    		p8.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("state"));
        		    	
        				    p1.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("CreatDate"));
        				    p6.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("endDate"));
        				    p5.setCellValueFactory(new PropertyValueFactory<PreventiveWork, String>("startDate"));
        				    p11.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

        			              @Override
        			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
        			                  return new SimpleStringProperty(param.getValue().getEquipement().getSerialNum()+"--"+param.getValue().getEquipement().getDescription());
        			              }
        			          }); 
        				    p2.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

      			              @Override
      			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
      			            	long diff2=param.getValue().getStartDate().getTime()-param.getValue().getTriggerD().getTime();
      			      		long diffDays2 = diff2 / (24 * 60 * 60 * 1000);
      			                  return new SimpleStringProperty(diffDays2+"-days");
      			              }
      			          }); 
        					Context contexthhh;
    			  			contexthhh = new InitialContext();
    			  	   
        			   p9.setCellValueFactory(new Callback<CellDataFeatures<PreventiveWork,String>,ObservableValue<String>>(){

      			              @Override
      			              public ObservableValue<String> call(CellDataFeatures<PreventiveWork, String> param) {
      			             	UserServiceRemote userService222;
    							try {
    								
    								userService222 = (UserServiceRemote) contexthhh.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    						 	  	 User u=userService222.find(param.getValue().getTechnicianId());
    	  			                  return new SimpleStringProperty(u.getFirstname()+"--"+u.getLastname());
    							} catch (NamingException e) {
    								// TODO Auto-generated catch block
    								e.printStackTrace();
    							}
      			      		
      			            	  	
      			                  return new SimpleStringProperty("-_-");
      			              }
      			          }); 
        				    
     
    // Set tooltip

        				    final ContextMenu contextMenu = new ContextMenu();
        				    MenuItem cut = new MenuItem("warning days!");
        				    MenuItem copy = new MenuItem("", new ImageView("/views/imgs/Ambox_warning_psycho.gif"));
        				 cut.setStyle("-fx-text-fill:black ;");
        				    contextMenu.setStyle("-fx-max-width: 110px;-fx-max-hight: 110px;");
            				
        				    contextMenu.getItems().addAll( copy);
        				    cut.setOnAction(new EventHandler<ActionEvent>() {
        				        @Override
        				        public void handle(ActionEvent event) {
        				            System.out.println("Cut...");
        				        }
        				    });
        			
        				        tableview1.setRowFactory(tv -> new TableRow<PreventiveWork>() {
        				        	
        				            @Override
        				            public void updateItem(PreventiveWork item, boolean empty) {
        				                super.updateItem(item, empty) ;
        				                if (item == null) {
        				                    setStyle("");
        				                } else if (VerifierWarn(item)) {
        				                    setStyle("-fx-background-color: tomato;");
        				                    setContextMenu(contextMenu);
        				                } else {
        				                    setStyle("");
        				                }
        				            }
        				        });

        Tooltip tooltip_userName=new Tooltip("Preventive Works  table");
         
        // Set tooltip
        prevtable.setTooltip(tooltip_userName);
         
        // Or using Tooltip.install
        Tooltip.install(prevtable, tooltip_userName);

        		    		 List<PreventiveWork> list = proxypp.searchPreventiveWork(search.getText());
        		    		 ObservableList<PreventiveWork> items = FXCollections.observableArrayList(list);
        		    		 prevtable.setItems(items);
        		    	  //   System.out.println(items.get(0).getDescription()+"tableauuuuuu");
        		    	    
        		    	     
        	    	       
        	    	
        		    		prevtable.getSelectionModel().selectedItemProperty().addListener(((observable, oldValue, newValue) -> {
        		    			
        		    		
        		    			
        		    		}));
        		    		
        		    	
        		    		
        		} catch (NamingException e1) {
        			
        			
        			// TODO Auto-generated catch block
        			e1.printStackTrace();
        		}}
    @FXML
    private void onGantt(ActionEvent event) throws IOException, NamingException {
        final GanttChart demo = new GanttChart("Gantt Chart Preventive works");
        demo.pack();
        RefineryUtilities.centerFrameOnScreen(demo);
        demo.setVisible(true);
    }  
    private void genereteReportPdf(UsualWork order,User u ,String path) throws IOException, DocumentException, MessagingException {
    	Document document = new Document();

PdfWriter.getInstance(document, new FileOutputStream(path));

document.open();

document.add(new Paragraph("\n--------------------  work rapport  --------------------------\n\n"
    + " object :"+order.getObjet()
    + "\n Add.Informations  : "+order.getDescription()
    + "\n equipment  : "+order.getEquipement().getSerialNum()+"--"+order.getEquipement().getFabriquant()
    +"\n Company : SpotLight CMMS"
    + "\n\n\n"
    + "\n Date :"+new java.util.Date()+"\n\n"
    + " Cordially "
));

document.addTitle("title");
document.close();



Alert alert = new Alert(Alert.AlertType.INFORMATION);
alert.setTitle("information");
alert.setHeaderText(null);
alert.setContentText("The report is successfully generated");
alert.show();


}
   

}
