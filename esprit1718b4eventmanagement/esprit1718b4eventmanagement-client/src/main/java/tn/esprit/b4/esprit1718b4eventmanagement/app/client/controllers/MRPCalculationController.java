package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.textfield.TextFields;

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
    
    NeededItem AbsoluteParent = new NeededItem();
    List<NeededItem> neededItemList = new ArrayList<>();
    List<NeedNomenclature> needNomenclatureList =new ArrayList<>();
    Map<NeededItem, List<NeededItem>> map = new HashMap<>();
    
    void initData(NeededItem Parent) {
    	AbsoluteParent=Parent;
		map = proxyNeededItem.InitialiseMap();
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
		Cancel.setText("Cancel");
		TreeItem<String> rootItem = new TreeItem<String> ("Inbox");
        rootItem.setExpanded(true);
        for (int i = 1; i < 6; i++) {
            TreeItem<String> item = new TreeItem<String> ("Message" + i);            
            rootItem.getChildren().add(item);
        }        
        //Tree.setRoot(rootItem);
	}
	
   @FXML
    void CancelAction(ActionEvent event) {
	   Stage stage = (Stage) Cancel.getScene().getWindow();
	    stage.close();
    }

    @FXML
    void MakeAction(ActionEvent event) {
    	Alert alert = new Alert(AlertType.CONFIRMATION);
    	alert.setTitle("Confirmation Dialog");
    	alert.setHeaderText("Look, a Confirmation Dialog");
    	alert.setContentText("Do you want to start the Manufacturing planning for this Item?");

    	Optional<ButtonType> result = alert.showAndWait();
    	if (result.get() == ButtonType.OK){
    		map=proxyNeededItem.SaveNeedItemTree(map);
    		needNomenclatureList = proxyNomenclature.SaveNeedItemTreeNomenclature(map);
    		Make.setVisible(false);
    		Cancel.setText("Exit");
    	}
    	
    }
    
    @FXML
    void ShowAction(ActionEvent event) throws NamingException {
    	DisplayAllNeededItem();
    	showTreeView();
    	ShowButton.setVisible(false);

    }

	
	}
