package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.net.URL;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import org.controlsfx.control.textfield.TextFields;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.util.Callback;
import javafx.util.StringConverter;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.ServiceLocator;
import javafx.fxml.Initializable;

public class OrdersController implements Initializable {
	
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
    private Tab idOrders;

    @FXML
    private Label CompanyLabel;

    @FXML
    private Label ItemLabel;

    @FXML
    private Button AddItem;

    @FXML
    private TextField CompanyText;

    @FXML
    private Button submit;

    @FXML
    private VBox vBox;

    @FXML
    private TextField ItemText;

    @FXML
    private ListView<Article> ListShow;

    @FXML
    private TableView<OrdredItem> Table_Added_Item;

    @FXML
    private TableColumn<OrdredItem, String> ItemColumn;

    @FXML
    private TableColumn<OrdredItem, Integer> QuantityColumn;

    @FXML
    private Label QuantityLabel;

    @FXML
    private TextField QuantityText;

    @FXML
    private Label DeliveryDateLabel;

    @FXML
    private DatePicker DeliveryDatePicker;

    @FXML
    private Button SubmitUpdate;
    
    @FXML
    private Button CancelUpdate;

    @FXML
    private TableView<Orders> TableOrders;

    @FXML
    private TableColumn<Orders, Long> ReferenceColumn;

    @FXML
    private TableColumn<Orders, String> CompanyColumn;

    @FXML
    private TableColumn<Orders, Date> OrderDateColumn;

    @FXML
    private TableColumn<Orders, Date> DeliveryDateColumn;
    
    @FXML
    private TableColumn<Orders, String> StatusColumn;

    @FXML
    private Button delete;

    @FXML
    private Button UpdateClick;

    @FXML
    private Button ShowItem;
    
    @FXML
    private Button Remove;

    @FXML
    private TableView<OrdredItem> TableItems;

    @FXML
    private TableColumn<OrdredItem, String> CodeColumn;

    @FXML
    private TableColumn<OrdredItem, String> ArticleColumn;

    @FXML
    private TableColumn<OrdredItem, Integer> QtyColumn;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		SubmitUpdate.setVisible(false);
		CancelUpdate.setVisible(false);
		TableItems.setVisible(false);
		ShowItem.setVisible(false);
		displayAllOrdres();
		java.util.List<Client> ClientList = proxyClientServiceRemote.findAll();
		ObservableList<String> items = FXCollections.observableArrayList();
		for (Client client : ClientList) {
			items.add(client.getCompany());	
		}
		TextFields.bindAutoCompletion(CompanyText, items);

	}
	
	private void displayAllOrdres(){
        List<Orders> list;
        list = proxyOrders.findAll();
        ObservableList<Orders> items = FXCollections.observableArrayList(list);
        TableOrders.setItems(items);
        
		ReferenceColumn.setCellValueFactory(new PropertyValueFactory<Orders, Long>("reference"));
		CompanyColumn.setCellValueFactory(new Callback<CellDataFeatures<Orders, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<Orders, String> param) {
                return new SimpleStringProperty(param.getValue().getClient().getCompany());
            }
        });
		OrderDateColumn.setCellValueFactory(new PropertyValueFactory<Orders, Date>("order_date"));
		DeliveryDateColumn.setCellValueFactory(new PropertyValueFactory<Orders, Date>("delivery_date"));
		StatusColumn.setCellValueFactory(new PropertyValueFactory<Orders, String>("statut"));
	}
	
	private void displayItemOfAnOrder(){
		List<OrdredItem> list;
        list = proxyOrdredItem.findItemsOfAnOrder(TableOrders.getSelectionModel().getSelectedItem().getId());
        ObservableList<OrdredItem> items = FXCollections.observableArrayList(list);
        TableItems.setItems(items);
        
		QtyColumn.setCellValueFactory(new PropertyValueFactory<OrdredItem, Integer>("quantity"));
		ArticleColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                return new SimpleStringProperty(param.getValue().getArticle().getDescription());
            }
        });
		CodeColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                return new SimpleStringProperty(param.getValue().getArticle().getArticleCode());
            }
        });
	}
	

    @FXML
    void ShowTableItem(ActionEvent event) {
    	TableItems.setVisible(true);
    	List<OrdredItem> list;
        list = proxyOrdredItem.findItemsOfAnOrder(TableOrders.getSelectionModel().getSelectedItem().getId());
        ObservableList<OrdredItem> items = FXCollections.observableArrayList(list);
        TableItems.setItems(items);
        
		QtyColumn.setCellValueFactory(new PropertyValueFactory<OrdredItem, Integer>("quantity"));
		ArticleColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                return new SimpleStringProperty(param.getValue().getArticle().getDescription());
            }
        });
		CodeColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                return new SimpleStringProperty(param.getValue().getArticle().getArticleCode());
            }
        });

    }

    @FXML
    void SubmitOrder(ActionEvent event) {
    	if(proxyClientServiceRemote.findByCompany(CompanyText.getText())==null||DeliveryDatePicker==null){
    		Alert alert2 = new Alert(AlertType.ERROR);
            alert2.setTitle("Error Dialog");
            alert2.setHeaderText("Look, an Error Dialog");
            alert2.setContentText("You have to select a valide client and delivery date");
            alert2.showAndWait();
    	}
    	else{
    		if(!Table_Added_Item.getItems().isEmpty()){
    			LocalDate localDate = DeliveryDatePicker.getValue();
        		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
        		Date Deliverydate = Date.from(instant);
        		Client client = proxyClientServiceRemote.findByCompany(CompanyText.getText());
        		Orders order = new Orders(0, Calendar.getInstance().getTime(), Deliverydate, "pending");
        		order.setClient(client);
        		int id = proxyOrders.addOrders(order);
        		order.setId(id);
        		order.setReference((id*17+47)*10);
        		proxyOrders.update(order);
        		List<OrdredItem> listOrderItem = Table_Added_Item.getItems();
        		for (OrdredItem ordredItem : listOrderItem) {
        			OrdredItem newOrdredItem = new OrdredItem();
        			newOrdredItem.setQuantity(ordredItem.getQuantity());
        			newOrdredItem.setStatus("pending");
        			System.out.println();
    				proxyOrdredItem.addOrdredItem(order.getId(), ordredItem.getArticle().getId(), newOrdredItem);
    			}
        		Table_Added_Item.getItems().clear();
        		CompanyText.clear();
        		DeliveryDatePicker.setValue(null);
        		TableOrders.setDisable(false);
        		TableItems.setDisable(false);
        		displayAllOrdres();
        		Alert alert4 = new Alert(AlertType.INFORMATION);
                alert4.setTitle("Info Dialog");
                alert4.setHeaderText("Success");
                alert4.setContentText("Adding successful");
                alert4.showAndWait();
    		}
    		else{
    			Alert alert3 = new Alert(AlertType.ERROR);
    			alert3.setTitle("Error Dialog");
    			alert3.setHeaderText("Look, an Error Dialog");
    			alert3.setContentText("You have to order at least an item");
    			alert3.showAndWait();
    		}
    	}

    }

    @FXML
    void TableClicked(MouseEvent event) {
    	if(TableOrders.getSelectionModel().getSelectedItem()!=null){
    		TableItems.setVisible(true);
    		displayItemOfAnOrder();
    	}
    }
    
    @FXML
    void TableClickedWithKey(KeyEvent event) {
    	displayItemOfAnOrder();
    }

    @FXML
    void UpdateClick(ActionEvent event) {
    	if(TableOrders.getSelectionModel().getSelectedItem()!=null){
    		submit.setVisible(false);
    		SubmitUpdate.setVisible(true);
    		CancelUpdate.setVisible(true);
    		Orders order = TableOrders.getSelectionModel().getSelectedItem();
    		CompanyText.setText(order.getClient().getCompany());
    		
    		Table_Added_Item.setItems(TableItems.getItems());
    		listAdedItem = new ArrayList<>();
        	listAdedItem = Table_Added_Item.getItems();
        	
            QuantityColumn.setCellValueFactory(new PropertyValueFactory<OrdredItem, Integer>("quantity"));
            ItemColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                    return new SimpleStringProperty(param.getValue().getArticle().getArticleCode());
                }
            });
            Instant instant = Instant.ofEpochMilli(order.getDelivery_date().getTime());
            LocalDate res = LocalDateTime.ofInstant(instant, ZoneId.systemDefault()).toLocalDate();
            DeliveryDatePicker.setValue(res);
            TableOrders.setDisable(true);
            TableItems.setDisable(true);
    	}
    }
    
    public static boolean isNumeric(String str)  
    {  
      try  
      {  
        double d = Double.parseDouble(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }

    List<OrdredItem> listAdedItem = new ArrayList<>();
    Article ArticleToAdd = null;
    
    @FXML
    void addCompany(ActionEvent event) {
    	if (QuantityText.getText().length()==0 || isNumeric(QuantityText.getText())==false || ArticleToAdd==null){
    		Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Look, an Error Dialog");
            alert.setContentText("You have to enter a valide quantity and select an item");
            alert.showAndWait();
    	}
    	else{
    		OrdredItem nouveau = new OrdredItem();
    		nouveau.setArticle(ArticleToAdd);
    		nouveau.setQuantity(Integer.parseInt(QuantityText.getText()));
    		nouveau.setStatus("en attente");
    		listAdedItem.add(nouveau);
            ObservableList<OrdredItem> items = FXCollections.observableArrayList(listAdedItem);
            Table_Added_Item.setItems(items);
            
            QuantityColumn.setCellValueFactory(new PropertyValueFactory<OrdredItem, Integer>("quantity"));
            ItemColumn.setCellValueFactory(new Callback<CellDataFeatures<OrdredItem, String>, ObservableValue<String>>() {
                @Override
                public ObservableValue<String> call(CellDataFeatures<OrdredItem, String> param) {
                    return new SimpleStringProperty(param.getValue().getArticle().getArticleCode());
                }
            });
            ItemText.clear();
            ListShow.getItems().clear();
            QuantityText.clear();
             
    	}

    }

    @FXML
    void deleteOrder(ActionEvent event) {
    	
    	Alert alert5 = new Alert(AlertType.CONFIRMATION);
    	alert5.setTitle("Confirmation Dialog");
    	alert5.setHeaderText("Look, a Confirmation Dialog");
    	alert5.setContentText("Are you ok with this?");

    	Optional<ButtonType> result = alert5.showAndWait();
    	if (result.get() == ButtonType.OK){
    		Orders order = TableOrders.getSelectionModel().getSelectedItem();
        	proxyOrders.delete(order);
        	displayAllOrdres();
    	}
    	

    }

    @FXML
    void searchArcticle(KeyEvent event) {
    	displaySearchedItem();
    }
    
    void displaySearchedItem(){
    	List<Article> list ;
        list = proxyArticleServiceRemote.findArticleByCodeORDescription(ItemText.getText());
        ObservableList<Article> items = FXCollections.observableArrayList(list);
        ListShow.setItems(items);
        ListShow.setCellFactory(param -> new ListCell<Article>() {
            @Override
            protected void updateItem(Article item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null || item.getArticleCode() == null) {
                    setText(null);
                } else {
                    setText(item.getArticleCode());
                }
            }
        });
    	
    }
   
    @FXML
    void getItem(MouseEvent event) {
    	ArticleToAdd = ListShow.getSelectionModel().getSelectedItem();
    	ItemText.setText(ArticleToAdd.getArticleCode());
    	displaySearchedItem();
    }
    
    @FXML
    void CancelUpdateAction(ActionEvent event) {
    	Table_Added_Item.getItems().clear();
		CompanyText.clear();
		DeliveryDatePicker.setValue(null);
		SubmitUpdate.setVisible(false);
		CancelUpdate.setVisible(false);
		submit.setVisible(true);
		TableOrders.setDisable(false);
        TableItems.setDisable(false);
        displayItemOfAnOrder();

    }
    
    @FXML
    void RemoveItem(ActionEvent event) {
        ObservableList<OrdredItem> items = Table_Added_Item.getItems();
        items.remove(Table_Added_Item.getSelectionModel().getSelectedIndex());
    }
    
    @FXML
    void SubmitUpdate(ActionEvent event) {
    	Orders order = TableOrders.getSelectionModel().getSelectedItem();
    	listAdedItem = Table_Added_Item.getItems();
    	LocalDate localDate = DeliveryDatePicker.getValue();
		Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.systemDefault()));
		Date Deliverydate = Date.from(instant);
		order.setDelivery_date(Deliverydate);
		Client client = proxyClientServiceRemote.findByCompany(CompanyText.getText());
		order.setClient(client);
		proxyOrdredItem.deleteAllByOrder(order.getId());
		proxyOrders.update(order);
		for (OrdredItem ordredItem : listAdedItem) {
			proxyOrdredItem.addOrdredItem(order.getId(), ordredItem.getArticle().getId(), ordredItem);
		}
		CompanyText.clear();
		DeliveryDatePicker.setValue(null);
		Table_Added_Item.getItems().clear();
		SubmitUpdate.setVisible(false);
		CancelUpdate.setVisible(false);
		submit.setVisible(true);
		TableOrders.setDisable(false);
		TableItems.setDisable(false);
		
		Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("Info Dialog");
        alert.setHeaderText("Success");
        alert.setContentText("update successful");
        alert.showAndWait();
        displayItemOfAnOrder();
    }

}
