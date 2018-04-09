package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.collections.ListChangeListener;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TreeItem.TreeModificationEvent;
import javafx.scene.control.TreeView;
import javafx.scene.control.cell.CheckBoxTreeCell;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.controlsfx.control.CheckTreeView;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

import java.net.URL;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow.Builder;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.gdata.client.calendar.*;
import com.google.gdata.data.calendar.*;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.Calendar.CalendarList;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.api.services.calendar.model.Events;

public class ChargingStationMenuController implements Initializable {
    
    private Label label;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private ImageView idO;
    @FXML
    private Separator id1;
    @FXML
    private Label id2;
    @FXML
    private Label idreflb;
    @FXML
    private ImageView idU;
    @FXML
    private ImageView idEq;
    @FXML
    private ImageView idAll;
    @FXML
    private ImageView idCal;
    @FXML
    private ImageView idStat;
    @FXML
    private ImageView idA12;
    @FXML
    private ImageView idA111;
    @FXML
    private ImageView idref;
    @FXML
    private ImageView idcancel;
    @FXML
    private TreeView<String> idtreeU;
    @FXML
    private TreeView<String> idtreeE;
    
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        label.setText("Hello World!");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     
    	idtreeU.setVisible(false);
		idtreeE.setVisible(false);
		
	

 
		
		
    	idAll.setOnMouseClicked((MouseEvent e) -> {
            System.out.println("Charging Station Clicked!"); // change functionality
            Parent parent= null;
	    	try {
				parent  =FXMLLoader.load(getClass().getResource("/views/ChargingStation.fxml"));
				Scene scene=new Scene(parent);
				Stage primaryStage= new Stage(); 
				primaryStage.setScene(scene);
				primaryStage.show();
				idAll.getScene().getWindow().hide();

			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
        });
    	
    	idU.setOnMouseClicked((MouseEvent e) -> {
    		idEq.setVisible(false);
    		idAll.setVisible(false);
    		idCal.setVisible(false);
    		idA111.setVisible(false);
    		idA12.setVisible(false);
    		idStat.setVisible(false);
    		idtreeU.setVisible(true);
    		idtreeE.setVisible(false);
    		
    		try {
    			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
    			Context contextChargingStation;
    			contextChargingStation = new InitialContext();
    			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
    			ChargingStation ch = new ChargingStation();
    			
    			
    			TreeItem<String> userss = new TreeItem<String>("Users");
    			Context contextUser;
    			contextUser = new InitialContext();
    		   	UserServiceRemote proxyuser = (UserServiceRemote) contextUser.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    	    	User user = new User();
    	    	
    			List<ChargingStation>listu = proxyChargingStation.DisplayChargingStation();
    			
    	 		for (int i = 0; i < listu.size(); i++) {
    	 			ObservableList<ChargingStation> itemss = FXCollections.observableArrayList(listu);
    	 			TreeItem<String> Usr = new TreeItem<String>(itemss.get(i).getUser().getFirstname()+"-"+itemss.get(i).getUser().getLastname());
    	 			Usr.setValue(" Code : "+itemss.get(i).getCode()+" First Name : "+itemss.get(i).getUser().getFirstname()+" Last Name : "+itemss.get(i).getUser().getLastname()+" Department : "+itemss.get(i).getUser().getRole());
    	 			userss.getChildren().addAll(Usr);
    			}
    	 		idtreeU.setRoot(userss);	 		
    	 		
    	 		
    	 		TreeItem<String> selectedItem = idtreeU.getSelectionModel().getSelectedItem();

    	        
     
    	 		} catch (NamingException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}
   
    		
    		
        });
    	

    	idEq.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(false);
    		idAll.setVisible(false);
    		idCal.setVisible(false);
    		idA111.setVisible(false);
    		idA12.setVisible(false);
    		idStat.setVisible(false);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(true);
    		
    		try {
    			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
    			Context contextChargingStation;
    			contextChargingStation = new InitialContext();
    			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
    			ChargingStation ch = new ChargingStation();
    			
    			
    			
    			TreeItem<String> equipments = new TreeItem<String>("Equipments");
    			Context contextEquipment;
     			contextEquipment = new InitialContext();
     			EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
     	    	Equipment equipment = new Equipment();
     	    	//List<Equipment> listE = proxyEquipment.DisplayEquipment();
     	    	List<ChargingStation> listE = proxyChargingStation.DisplayChargingStation();
    			
    	 		for (int i = 0; i < listE.size(); i++) {
    	 			ObservableList<ChargingStation> itemss = FXCollections.observableArrayList(listE);
    	 			TreeItem<String> Eq = new TreeItem<String>(itemss.get(i).getEquipement().getSerialNum()+"-"+itemss.get(i).getEquipement().getMarque());
    	 			Eq.setValue(" Code : "+itemss.get(i).getCode()+" Serial Number : "+itemss.get(i).getEquipement().getSerialNum()+" Marque : "+itemss.get(i).getEquipement().getMarque()+" Manufacturer : "+itemss.get(i).getEquipement().getFabriquant());
    	 			equipments.getChildren().addAll(Eq);
    			}
    	 		
    	 		idtreeE.setRoot(equipments);
    	 		//idtreeE.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    	 		TreeItem<String> selectedItem = idtreeU.getSelectionModel().getSelectedItem();
    		} catch (NamingException e2) {
    			// TODO Auto-generated catch block
    			e2.printStackTrace();
    		}

			
        });
    	
    	
    	idref.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(true);
    		idAll.setVisible(true);
    		idCal.setVisible(true);
    		idA111.setVisible(true);
    		idA12.setVisible(true);
    		idStat.setVisible(true);
    		idEq.setVisible(true);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(false);
    	  });
    	
    	idreflb.setOnMouseClicked((MouseEvent e) -> {
    		idU.setVisible(true);
    		idAll.setVisible(true);
    		idCal.setVisible(true);
    		idA111.setVisible(true);
    		idA12.setVisible(true);
    		idStat.setVisible(true);
    		idEq.setVisible(true);
    		idtreeU.setVisible(false);
    		idtreeE.setVisible(false);
    	  });
    	
    	
		  
    	idCal.setOnMouseClicked((MouseEvent e) -> { 
			  
			  System.out.println("Calendar Clicked!"); // change functionality
			  
			  Parent parent= null;
			  	try {
			  		
			  		VBox vb = new VBox();
	  				parent  =FXMLLoader.load(getClass().getResource("/views/Stat.fxml"));
	  				
	  				Stage primaryStage= new Stage(); 
	  			
	  				idcancel.getScene().getWindow().hide();
	  				
	  			    vb.setId("root");

	  			    WebView  browser = new WebView();
	  			    WebEngine engine = browser.getEngine();
	  			    String url1 = "https://calendar.google.com/calendar/embed?src=gpaoons%40gmail.com&ctz=Africa%2FTunis";
	  			    engine.load(url1);
	  			    
	  			    vb.setPadding(new Insets(30, 50, 50, 50));
	  			    vb.setSpacing(10);
	  			    vb.setAlignment(Pos.CENTER);
	  			    vb.getChildren().addAll(browser);

	  			    Scene scene = new Scene(vb);
	  			    primaryStage.setScene(scene);
	  			    primaryStage.show();
			  	} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
			  	
			  	
//	            Parent parent= null;
//	            Stage stage = null;
//	            stage.setTitle("HTML");
//	            stage.setWidth(500);
//	            stage.setHeight(500);
//	            Scene scene = new Scene(new Group());
//
//	            VBox root = new VBox();     
//
//	            final WebView browser = new WebView();
//	            final WebEngine webEngine = browser.getEngine();
//
//	            ScrollPane scrollPane = new ScrollPane();
//	            scrollPane.setContent(browser);
//	            webEngine.loadContent("https://calendar.google.com/calendar/embed?src=gpaoons%40gmail.com&ctz=Africa%2FTunis");
//
//	            root.getChildren().addAll(scrollPane);
//	            scene.setRoot(root);
//
//	            stage.setScene(scene);
//	            stage.show();      
	        
	                  
	            /////////////////////////////////////////////////
//	            CalendarController quickstart =new CalendarController();
//	            
//				
//		
//	            try {
//	            	 // Build a new authorized API client service.
//	                // Note: Do not confuse this class with the
//	                //   com.google.api.services.calendar.model.Calendar class.
//	                Calendar service;
//					try {
//						service = quickstart.getCalendarService();
//						 // List the next 10 events from the primary calendar.
//		                DateTime now = new DateTime(System.currentTimeMillis());
//		                Events events;
//						
//							events = service.events().list("primary")
//							    .setMaxResults(10)
//							    .setTimeMin(now)
//							    .setOrderBy("startTime")
//							    .setSingleEvents(true)
//							    .execute();
//							
//			                List<Event> items = events.getItems();
//			                if (items.size() == 0) {
//			                    System.out.println("No upcoming events found.");
//			                } else {
//			                    System.out.println("Upcoming events");
//			                    for (Event event : items) {
//			                        DateTime start = event.getStart().getDateTime();
//			                        if (start == null) {
//			                            start = event.getStart().getDate();
//			                        }
//			                        System.out.printf("%s (%s)\n", event.getSummary(), start);
//			                        System.out.printf("%s (%s)\n", event.getId(), start);
//			                     
//			                     
//			                     /////////////////////////////////////
//			                     // Refer to the Java quickstart on how to setup the environment:
//			                     // https://developers.google.com/calendar/quickstart/java
//			                     // Change the scope to CalendarScopes.CALENDAR and delete any stored
//			                     // credentials.
//			                    	quickstart.authorize();
//			                     Event event1 = new Event()
//			                         .setSummary("Google I/O 2015")
//			                         .setLocation("800 Howard St., San Francisco, CA 94103")
//			                         .setDescription("A chance to hear more about Google's developer products.");
//
//			                     DateTime startDateTime = new DateTime("2018-05-28T09:00:00-07:00");
//			                     EventDateTime start1 = new EventDateTime()
//			                         .setDateTime(startDateTime)
//			                         .setTimeZone("America/Los_Angeles");
//			                     event1.setStart(start1);
//
//			                     DateTime endDateTime = new DateTime("2018-05-28T17:00:00-07:00");
//			                     EventDateTime end = new EventDateTime()
//			                         .setDateTime(endDateTime)
//			                         .setTimeZone("America/Los_Angeles");
//			                     event1.setEnd(end);
//
//			                     String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
//			                     event1.setRecurrence(Arrays.asList(recurrence));
//
//			                     EventAttendee[] attendees = new EventAttendee[] {
//			                         new EventAttendee().setEmail("gpaoons@gmail.com"),
//			                         new EventAttendee().setEmail("gpaoons@gmail.com"),
//			                     };
//			                     event1.setAttendees(Arrays.asList(attendees));
//
//			                     EventReminder[] reminderOverrides = new EventReminder[] {
//			                         new EventReminder().setMethod("email").setMinutes(24 * 60),
//			                         new EventReminder().setMethod("popup").setMinutes(10),
//			                     };
//			                     Event.Reminders reminders = new Event.Reminders()
//			                         .setUseDefault(false)
//			                         .setOverrides(Arrays.asList(reminderOverrides));
//			                     event1.setReminders(reminders);
//
//			                     String calendarId = "primary";
//			                     event1 = service.events().insert(calendarId, event1).execute();
//			                     System.out.printf("Event created: %s\n", event1.getHtmlLink());
//			                     ////////////////////////////////////////   
//			                     // Delete an event
//			                     //add the id
//			                        service.events().delete("primary", event.getId()).execute();
//			                     
//			                        
//			                     // Retrieve the event from the API
//			                        Event event2 = service.events().get("primary", event.getId()).execute();
//
//			                        // Make a change
//			                        event2.setSummary("Appointment at Somewhere");
//			                        
//			                     // Update the event
//			                        Event updatedEvent = service.events().update("primary", event2.getId(), event2).execute();
//			                        System.out.println(updatedEvent.getUpdated());
//			                    }
//			                }
//					
//
//	               
//					} catch (Exception e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//	
//	           
//	            }finally {
//	            	 System.out.printf("i'm very happy 😉  ");
//	    		}
	            ////////////////////////////////////////////////////
	            
	            
//		    	try {
//					parent  =FXMLLoader.load(getClass().getResource("/views/Calendar.fxml"));
//					Scene scene=new Scene(parent);
//					Stage primaryStage= new Stage(); 
//					primaryStage.setScene(scene);
//					primaryStage.show();
//					idCal.getScene().getWindow().hide();
//
//				} catch (Exception e1) {
//					// TODO Auto-generated catch block
//					e1.printStackTrace();
//				}

	            
	            
	            
//	            try{
//	                CalendarService myService = new CalendarService("ons");
//	                myService.setUserCredentials("gpaoons@gmail.com", "gpaoons123");
//	                URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/allcalendars/full");
//	                CalendarFeed resultFeed = myService.getFeed(feedUrl, CalendarFeed.class);
//	                System.out.println("Your calendars:");
//	                System.out.println();
//	                for (int i = 0; i < resultFeed.getEntries().size(); i++) {
//	                    CalendarEntry entry = resultFeed.getEntries().get(i);
//	                    System.out.println("\t" + entry.getTitle().getPlainText());
//	                }
//	            }catch(Exception p){
//	                p.printStackTrace();
//	            }
	            
	            

	            


   
	            
		  });
    	
    	idcancel.setOnMouseClicked((MouseEvent a) -> { 
			  Parent parent= null;
			  	try {
	  				parent  =FXMLLoader.load(getClass().getResource("/views/MenuuGPAO.fxml"));
	  				Scene scene=new Scene(parent);
	  				Stage primaryStage= new Stage(); 
	  				primaryStage.setScene(scene);
	  				primaryStage.show();
	  				idcancel.getScene().getWindow().hide();
			  	} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
		  });
    	
    	
     	idStat.setOnMouseClicked((MouseEvent a) -> { 
     		System.out.println("Stat Clicked!");
			  Parent parent= null;
			  	try {
	  				parent  =FXMLLoader.load(getClass().getResource("/views/Stat.fxml"));
	  				Scene scene=new Scene(parent);
	  				Stage primaryStage= new Stage(); 
	  				primaryStage.setScene(scene);
	  				primaryStage.show();
	  				idcancel.getScene().getWindow().hide();
			  	} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
		  });
    	
    	}
    }    
    

