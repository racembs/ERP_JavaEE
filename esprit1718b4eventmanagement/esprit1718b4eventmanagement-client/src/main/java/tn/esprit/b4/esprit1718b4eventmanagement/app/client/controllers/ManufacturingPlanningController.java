package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import javax.naming.NamingException;

import org.controlsfx.control.textfield.TextFields;

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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.ServiceLocator;
import javafx.fxml.Initializable;

public class ManufacturingPlanningController implements Initializable {
	
	String jndiNameClient = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ClientService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote";
	String jndiNameNomenclature = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedNomenclatureService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote";
	String jndiNameNeededItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeededItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote";
	String jndiNameManufacturingPlan = "exported/esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
	String jndiNameOrdredItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrderItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote";
	String jndiNameOrders= "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrdersService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote";
	String jndiNameArticle = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
	ServiceLocator s=ServiceLocator.getInstance(); 
	ClientServiceRemote proxyClientServiceRemote=(ClientServiceRemote) s.getProxy(jndiNameClient);
	NeedNomenclatureServiceRemote proxyNomenclature=(NeedNomenclatureServiceRemote) s.getProxy(jndiNameNomenclature);
	NeededItemServiceRemote proxyNeededItem=(NeededItemServiceRemote) s.getProxy(jndiNameNeededItem);
	NeededItemServiceRemote proxyManufacturing=(NeededItemServiceRemote) s.getProxy(jndiNameManufacturingPlan);
	OrderItemServiceRemote proxyOrdredItem=(OrderItemServiceRemote) s.getProxy(jndiNameOrdredItem);
	OrdersServiceRemote proxyOrders=(OrdersServiceRemote) s.getProxy(jndiNameOrders);
	ArticleServiceRemote proxyArticleServiceRemote=(ArticleServiceRemote) s.getProxy(jndiNameArticle);

	@FXML
    private Label txtCurrentWindow;

    @FXML
    private AnchorPane holderPane;

    @FXML
    private Tab idMRP;

    @FXML
    private Label CompanyLabel;

    @FXML
    private Label ItemLabel;

    @FXML
    private Button Calculate;

    @FXML
    private TextField ClientText;

    @FXML
    private VBox vBox;

    @FXML
    private ListView<OrdredItem> ListShow;

    @FXML
    private ComboBox<Orders> ComboOrders;

    @FXML
    private Label ItemLabel1;

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
    private Button delete;

    @FXML
    private Label CompanyLabel1;

    @FXML
    private TextField ClientTextSearch;

    @FXML
    private ComboBox<Orders> ComboOrdersSearch;

    @FXML
    private Label ItemLabel11;

    @FXML
    private ComboBox<OrdredItem> ComboArticleSearch;

    @FXML
    private Label ItemLabel111;
    
    public void ComboBoxOrders(ComboBox<Orders> combo, TextField ClientTex){
    	Client client = proxyClientServiceRemote.findByCompany(ClientTex.getText());
    	List <Orders> list = proxyOrders.findOrdersByClient(client.getId());
    	ObservableList<Orders> items = FXCollections.observableArrayList(list);
    	combo.setConverter(new StringConverter<Orders>() {
    		@Override
    		public String toString(Orders object) {
    		    return String.valueOf(object.getReference());
    		}
    		@Override
    		public Orders fromString(String string) {
    		    return null;
    		}
    		});
    	combo.setItems(items); 

    	combo.valueProperty().addListener(new ChangeListener<Orders>() {
           
			@Override
			public void changed(ObservableValue<? extends Orders> observableValue, Orders oldValue, Orders newValue) {
				
			}    
        });
    }
    
    
    OrdredItem ArticleToAdd = null;
	    
		@Override
		public void initialize(URL location, ResourceBundle resources) {
			java.util.List<Client> ClientList = proxyClientServiceRemote.findAll();
			ObservableList<String> items = FXCollections.observableArrayList();
			for (Client client : ClientList) {
				items.add(client.getCompany());	
			}
			TextFields.bindAutoCompletion(ClientText, items);
			TextFields.bindAutoCompletion(ClientTextSearch, items);
			
		}
		
		@FXML
	    void CalculateAction(ActionEvent event) throws IOException {
			//Client client = proxyClientServiceRemote.findByCompany(ClientText.getText());
			if(ListShow.getItems().isEmpty()){
				Alert alert = new Alert(AlertType.ERROR);
		        alert.setTitle("Error Dialog");
		        alert.setHeaderText("Failed");
		        alert.setContentText("you have to select an item");
		        alert.showAndWait();
			}
			else{
				NeededItem Parent = new NeededItem();
				Parent.setActionNature("Manufacturing Order");
				Parent.setOrderItem(ListShow.getItems().get(0));
				Parent.setNeeded_article(ListShow.getItems().get(0).getArticle());
				Parent.setGrossNeed(Parent.getOrderItem().getQuantity());
				Parent.setNetNeed(Parent.getGrossNeed()-(Parent.getNeeded_article().getQuantity()-Parent.getNeeded_article().getReservedQuantity()));
				Parent.setStatus("Pending");
  				
  				FXMLLoader loader = new FXMLLoader(getClass().getResource("/views/MRPCalculation.fxml"));
  				Stage stage = new Stage();
  				Parent parent = (Parent)loader.load();
				Scene scene=new Scene(parent);
  				stage.setScene(scene);
				MRPCalculationController controller = loader.<MRPCalculationController>getController();
				controller.initData(Parent);
				stage.show();
			}
			
	    }

	    @FXML
	    void TableClicked(MouseEvent event) {

	    }

	    @FXML
	    void TableClickedWithKey(KeyEvent event) {

	    }

	    @FXML
	    void deleteOrdredItemNeed(ActionEvent event) {
	    	
	    }

	    @FXML
	    void getItem(MouseEvent event) {
	    	ArticleToAdd = ListShow.getSelectionModel().getSelectedItem();
	    }
	    
	    void displaySearchedItem(){
	    	
	    	Client client = proxyClientServiceRemote.findByCompany(ClientText.getText());
	    	if(client==null ||ComboOrders.getItems().isEmpty()){
	    		Alert alert2 = new Alert(AlertType.ERROR);
		        alert2.setTitle("Error Dialog");
		        alert2.setHeaderText("Failed");
		        alert2.setContentText("you have to select the client and the order first");
		        alert2.showAndWait();
	    	}
	    	else{
	    		List<OrdredItem> list ;
		        list = proxyOrdredItem.findPendingItemsOfAnOrder(ComboOrders.getSelectionModel().getSelectedItem().getId());
		        ObservableList<OrdredItem> items = FXCollections.observableArrayList(list);
		        ListShow.setItems(items);
		        ListShow.setCellFactory(param -> new ListCell<OrdredItem>() {
		            @Override
		            protected void updateItem(OrdredItem item, boolean empty) {
		                super.updateItem(item, empty);

		                if (empty || item == null || item.getArticle().getArticleCode() == null) {
		                    setText(null);
		                } else {
		                    setText(item.getArticle().getArticleCode());
		                }
		            }
		        });
	    	}
	    	
	    }
	    
	    Map<NeededItem, List<NeededItem>> map = new HashMap<>();
	    NeededItem ParentneededItem = new NeededItem();
	    public void DisplayNeededItem(){
 
	    		ParentneededItem = proxyNeededItem.getNeededItemParentOfOrdredItem
		    			(ComboArticleSearch.getSelectionModel().getSelectedItem().getOrdredItemPk().getId_Order(), 
		    					ComboArticleSearch.getSelectionModel().getSelectedItem().getOrdredItemPk().getId_Article());  
  
			map=proxyNeededItem.InitialiseMap();
	    	map = proxyNeededItem.findNeededItemTreeByOrdredItem(ParentneededItem);
	    	List<NeededItem> neededItemList =proxyNeededItem.NeedItemList(map);
	    	
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
	    
	    private void showTreeView(NeededItem AbsoluteParent, Map<NeededItem, List<NeededItem>> map ) throws NamingException {
	    	
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
	    
	    @FXML
	    void OrderdisplayAction(MouseEvent event) {
	    	ComboBoxOrders(ComboOrders,ClientText);
	    }
	    
	    @FXML
	    void OrderSerachDisplayAction(MouseEvent event) {
	    	ComboBoxOrders(ComboOrdersSearch,ClientTextSearch);
	    }
	    
	    @FXML
	    void SelectComboAction(ActionEvent event) {
	    	displaySearchedItem();
	    }
	    
	    @FXML
	    void SelectComboSearchAction(ActionEvent event) {
	    	List<OrdredItem> list ;
	        list = proxyOrdredItem.findItemsOfAnOrder(ComboOrdersSearch.getSelectionModel().getSelectedItem().getId());
	        ObservableList<OrdredItem> items = FXCollections.observableArrayList(list);
	    	ComboArticleSearch.setConverter(new StringConverter<OrdredItem>() {
	    		@Override
	    		public String toString(OrdredItem object) {
	    		    return String.valueOf(object.getArticle().getArticleCode());
	    		}
	    		@Override
	    		public OrdredItem fromString(String string) {
	    		    return null;
	    		}
	    		});
	    	ComboArticleSearch.setItems(items); 

	    	ComboArticleSearch.valueProperty().addListener(new ChangeListener<OrdredItem>() {
	           
				@Override
				public void changed(ObservableValue<? extends OrdredItem> observableValue, OrdredItem oldValue, OrdredItem newValue) {
					
				}    
	        });
	    }
	    
	    @FXML
	    void forTableAction(ActionEvent event) throws NamingException {
	    	TableNeededItem.getItems().clear();
	    	DisplayNeededItem();
	    	showTreeView(ParentneededItem,map);
	    }
	}
