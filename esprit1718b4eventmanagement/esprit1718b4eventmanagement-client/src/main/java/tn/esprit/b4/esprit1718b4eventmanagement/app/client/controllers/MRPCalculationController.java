package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.textfield.TextFields;

import com.jfoenix.controls.JFXTimePicker;
import com.sun.javafx.fxml.LoadListener;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.ServiceLocator;
import javafx.fxml.Initializable;

public class MRPCalculationController implements Initializable {
	
	String jndiNameClient = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ClientService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote";
	String jndiNameNomenclature = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedNomenclatureService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote";
	String jndiNameNeededItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeededItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote";
	String jndiNameManufacturingPlan = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
	String jndiNameOrdredItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrderItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote";
	String jndiNameOrders= "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrdersService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote";
	String jndiNameArticle = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
	ServiceLocator s=ServiceLocator.getInstance(); 
	ClientServiceRemote proxyClientServiceRemote=(ClientServiceRemote) s.getProxy(jndiNameClient);
	NeedNomenclatureServiceRemote proxyNomenclature=(NeedNomenclatureServiceRemote) s.getProxy(jndiNameNomenclature);
	NeededItemServiceRemote proxyNeededItem=(NeededItemServiceRemote) s.getProxy(jndiNameNeededItem);
	ManufacturingPlanningServiceRemote proxyManufacturing=(ManufacturingPlanningServiceRemote) s.getProxy(jndiNameManufacturingPlan);
	OrderItemServiceRemote proxyOrdredItem=(OrderItemServiceRemote) s.getProxy(jndiNameOrdredItem);
	OrdersServiceRemote proxyOrders=(OrdersServiceRemote) s.getProxy(jndiNameOrders);
	ArticleServiceRemote proxyArticleServiceRemote=(ArticleServiceRemote) s.getProxy(jndiNameArticle);
	
	@FXML
    private AnchorPane aPane;
	
    @FXML
    private Label txtCurrentWindow;

    @FXML
    private AnchorPane holderPane;
    
    @FXML
    private VBox box;

    @FXML
    private DatePicker date;
    
    @FXML
    private TreeTableView<NeededItem> TreeTable;

    @FXML
    private TreeTableColumn<NeededItem, String> NeededColumn;

    @FXML
    private TreeTableColumn<NeededItem, String> QtyColumn;

    @FXML
    private TableView<NeededItem> TableNeededItem;

    @FXML
    private TableColumn<NeededItem, String> OrdredItemColumn;

    @FXML
    private TableColumn<NeededItem, String> ChildColumn;

    @FXML
    private TableColumn<NeededItem, String> GrossNeedColumn;

    @FXML
    private TableColumn<NeededItem, String> NetNeedColumn;

    @FXML
    private TableColumn<NeededItem, String> ReadyLotColumn;

    @FXML
    private TableColumn<NeededItem, String> ReqActionColumn;

    @FXML
    private TableColumn<NeededItem, String> LevelColumn;

    @FXML
    private TableColumn<NeededItem, String> StatusColumn;

    @FXML
    private Button Make;

    @FXML
    private Button Cancel;
    
    @FXML
    private Button ShowButton;
    
    @FXML
    private ComboBox<String> HourlyPostCombo;

    @FXML
    private Label StartLabel;

    @FXML
    private Label hourlyLabel;
    
    NeededItem AbsoluteParent = new NeededItem();
    List<NeededItem> neededItemList = new ArrayList<>();
    Set<NeedNomenclature> needNomenclatureList =new HashSet<>() ;
    Map<NeededItem, List<NeededItem>> map = new HashMap<>();
    
    void initData(NeededItem Parent) {
    	AbsoluteParent=Parent;
		map = proxyNeededItem.InitialiseASCMap();
		map= proxyNeededItem.CreateNeedItemTree(AbsoluteParent);
		List<NeedNomenclature> needNomenclatureList = proxyNomenclature.DisplayTreeNomenclatureFromMap(map);
    	neededItemList=proxyNeededItem.NeedItemList(map);
    	needNomenclatureList = proxyNomenclature.DisplayTreeNomenclatureFromMap(map);
      }
    
    public void DisplayAllNeededItem(){
    	ObservableList<NeededItem> items = FXCollections.observableArrayList(neededItemList);
        
    	TableNeededItem.setItems(items);
        
        
    	OrdredItemColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getOrderItem().getCode()));
            }
        });
    	ChildColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(param.getValue().getNeeded_article().getArticleCode());
            }
        });
    	GrossNeedColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getGrossNeed()));
            }
        });
    	NetNeedColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getNetNeed()));
            }
        });
    	ReadyLotColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getReadyLotNumber()));
            }
        });
    	ReqActionColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getActionNature()));
            }
        });
    	LevelColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getLevel()));
            }
        });
    	StatusColumn.setCellValueFactory(new Callback<CellDataFeatures<NeededItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<NeededItem, String> param) {
                return new SimpleStringProperty(String.valueOf(param.getValue().getStatus()));
            }
        });
    }
    
    private void showTreeView() throws NamingException {
    	
    	TreeItem<NeededItem> root=new TreeItem<>();
     
     	TreeItem<NeededItem> newItemarticlePere;
     	TreeItem<NeededItem> newItemarticleFils=null;
    	 
     	newItemarticlePere=new TreeItem<>(AbsoluteParent);
    	 root.getChildren().add(newItemarticlePere);

    	 ArrayDeque <TreeItem<NeededItem>> queue=new ArrayDeque<>();
    	 queue.add(newItemarticlePere);
    	
    	 while(!queue.isEmpty()) {
    		 
    		TreeItem<NeededItem> TreeItemHead=queue.getFirst();
    		queue.removeFirst();
    		List<NeededItem> listNomenclatureFils2 = map.get(TreeItemHead.getValue());
    		for (NeededItem neededItem : listNomenclatureFils2) {
    			newItemarticleFils=new TreeItem<>(neededItem);
    			TreeItemHead.getChildren().add(newItemarticleFils);
    			queue.addLast(newItemarticleFils);
			}
    	 }
    	 
    	 NeededColumn.setCellValueFactory(
    	            (TreeTableColumn.CellDataFeatures<NeededItem, String> param) -> 
    	            new ReadOnlyStringWrapper(param.getValue().getValue().getNeeded_article().getArticleCode())
    	        );
    	 QtyColumn.setCellValueFactory(
 	            (TreeTableColumn.CellDataFeatures<NeededItem, String> param) -> 
 	            new ReadOnlyStringWrapper(String.valueOf(param.getValue().getValue().getNetNeed()))
 	        );
     	TreeTable.setRoot(root);
     	TreeTable.setShowRoot(false);
     
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Make.setVisible(true);
		date.setVisible(true);
		Cancel.setText("Cancel");
		TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        List<String> list = new ArrayList<String>() ;
        list.add("One- From 7h to 15h");
        list.add("Two- From 7h to 23h");
        list.add("Three- 24h/24h");
        ObservableList<String> items = FXCollections.observableArrayList(list);
    	HourlyPostCombo.setItems(items);
	}
	
   @FXML
    void CancelAction(ActionEvent event) {
	   Stage stage = (Stage) Cancel.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void MakeAction(ActionEvent event) {
    	if(date.getValue()==null){
    		Alert alert1 = new Alert(AlertType.ERROR);
			alert1.setTitle("Error Dialog");
			alert1.setHeaderText("Look, an Error Dialog");
			alert1.setContentText("You have to select the starting date");
			alert1.showAndWait();
    	} else {
    		Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("Confirmation Dialog");
        	alert.setHeaderText("Look, a Confirmation Dialog");
        	alert.setContentText("Do you want to start the Manufacturing planning for this Item?");

        	Optional<ButtonType> result = alert.showAndWait();
        	if (result.get() == ButtonType.OK){
        		if(AbsoluteParent.getNetNeed()==0){
        			proxyManufacturing.updateIfOneNeededItem(AbsoluteParent);
        		} else {
//        			map=proxyNeededItem.SaveNeedItemTree(map);
//        			map = proxyNeededItem.InitialiseMap();
//        			AbsoluteParent.setId(proxyNeededItem.SaveParentNeedItemTree(AbsoluteParent));
//        			map=proxyNeededItem.CreateANDSaveNeedItemTree(AbsoluteParent);
        			map = proxyNeededItem.SaveNeedItemTree(map);
        			map=proxyNeededItem.SetPurchaseDeliveryDate(map);
            		needNomenclatureList = proxyNomenclature.SaveNeedItemTreeNomenclature(map);
            		LocalDate localDate = date.getValue();
            		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
            		Date startDate = Date.from(instant);
            		Calendar calendar = Calendar.getInstance();
            		calendar.setTime(startDate);
            		calendar.setTimeInMillis(calendar.getTimeInMillis()+25200000);
                    int hourlyPostNumber = 1;
            		if (HourlyPostCombo.getSelectionModel().getSelectedItem().equals("One- From 7h to 15h"))
            			hourlyPostNumber=1;
            		else if(HourlyPostCombo.getSelectionModel().getSelectedItem().equals("Two- From 7h to 23h"))
            			hourlyPostNumber=2;
            		else
            			hourlyPostNumber=3;
            		proxyManufacturing.ReadyManufacturingPlanning(map, calendar.getTime(),hourlyPostNumber);
            		proxyNeededItem.InitialiseDESCMap();
            		NeededItem paren = new NeededItem();
            		for (NeededItem needNomenclature : map.keySet()) {
						if(needNomenclature.getLevel()==0)
							paren=needNomenclature;
					}
            		Map<NeededItem, List<NeededItem>> map2 = proxyNeededItem.findNeededItemTreeByOrdredItem(proxyNeededItem.find(paren.getId()));
//            		for (Map.Entry<NeededItem, List<NeededItem>> e : map2.entrySet()) {
//						System.out.println(e.getKey().getNeeded_article().getArticleCode()+" "+e.getKey().getNetNeed());
//						System.out.println("valeur");
//						for (NeededItem neededItem : e.getValue()) {
//							System.out.println(neededItem.getNeeded_article().getArticleCode());
//						}
//						System.out.println("*************");
//					}
            		List<ManufacturingPlanning> llisst =proxyManufacturing.AfterDeliveryManufacturingPlanning(map2,hourlyPostNumber);
            		for (ManufacturingPlanning manufacturingPlanning : llisst) {
            			System.out.println(manufacturingPlanning.getNeededItem().getNeeded_article().getArticleCode());
					}
            		System.out.println();
        		}
        		
        		Make.setVisible(false);
        		date.setVisible(false);
        		Cancel.setText("Exit");
        	}
        	
    	}
    	
		//DeliveryDatePicker.setValue(null);
    }
    
    @FXML
    void ShowAction(ActionEvent event) throws NamingException {
    	DisplayAllNeededItem();
    	showTreeView();
    	ShowButton.setVisible(false);
    }

	
	}
