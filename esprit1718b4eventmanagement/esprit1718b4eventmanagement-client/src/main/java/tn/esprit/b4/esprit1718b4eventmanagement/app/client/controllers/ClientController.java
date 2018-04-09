package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
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

public class ClientController implements Initializable {
	
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
    private Tab idClient;

    @FXML
    private Label CompanyLabel;

    @FXML
    private Label EmailLabel;
    
    @FXML
    private Label PhoneLabel1;
    
    @FXML
    private Label PhoneLabel11;

    @FXML
    private Label CompanyLabel1;

    @FXML
    private Label EmailLabel1;

    @FXML
    private Button Addclient;

    @FXML
    private TextField EmailText;

    @FXML
    private TextField CompanyText;

    @FXML
    private TextField PhoneText;
    
    @FXML
    private TextField EmailText1;

    @FXML
    private TextField CompanyText1;
    
    @FXML
    private TextField PhoneText1;

    @FXML
    private TableColumn<Client, String> CodeColumn;

    @FXML
    private TableColumn<Client, String> CompanyColumn;

    @FXML
    private TableColumn<Client, String> EmailColumn;

    @FXML
    private TableColumn<Client, Long> PhoneNumberColumn;
    
    @FXML
    private TableView<Client> Table;

    @FXML
    private Button SearchButton;

    @FXML
    private TextField SearchText;
    
    @FXML
    private Button update;
    
    @FXML
    private Button delete;
    
    @FXML
    private Button submit;
    
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		displayAllClient();
		submit.setVisible(false);
		PhoneLabel11.setVisible(false);
		EmailLabel1.setVisible(false);
		CompanyLabel1.setVisible(false);
		CompanyText1.setVisible(false);
		PhoneText1.setVisible(false);
		EmailText1.setVisible(false);
	}
	
	@FXML
    void addCompany(ActionEvent event) {
		Client client = new Client();
		client.setEmail(EmailText.getText());
		client.setCompany(CompanyText.getText());
		client.setPhoneNumber((Long.parseLong(PhoneText.getText()))) ;
		client.setId(proxyClientServiceRemote.addClient(client));
		String codess="";
		for (int i = 0; i < 3; i++) {
			codess=codess+client.getCompany().charAt(i);
		}
		client.setCode(codess+(client.getId()*12+7));
		proxyClientServiceRemote.update(client);
		displayAllClient();
		CompanyText.clear();
		PhoneText.clear();
		EmailText.clear();
		
    }
	
	private void displayAllClient(){
        List<Client> list;
        list = proxyClientServiceRemote.findAll();
        ObservableList<Client> items = FXCollections.observableArrayList(list);
        Table.setItems(items);
        
		CodeColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("code"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		CompanyColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("company"));
		PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, Long>("phoneNumber"));
		
	}
	
    

    @FXML
    void SearchAction(KeyEvent event) {
    	
    	List<Client> list ;
        list = proxyClientServiceRemote.searchClient(SearchText.getText());
        ObservableList<Client> items = FXCollections.observableArrayList(list);
        Table.setItems(items);
        
		CodeColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("code"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		CompanyColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("company"));
		PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, Long>("phoneNumber"));
    }

    @FXML
    void SearchCompany(ActionEvent event) {
    	List<Client> list ;
        list = proxyClientServiceRemote.searchClient(SearchText.getText());
        ObservableList<Client> items = FXCollections.observableArrayList(list);
        Table.setItems(items);
        
		CodeColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("code"));
		EmailColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("email"));
		CompanyColumn.setCellValueFactory(new PropertyValueFactory<Client, String>("company"));
		PhoneNumberColumn.setCellValueFactory(new PropertyValueFactory<Client, Long>("phoneNumber"));

    }


    @FXML
    void deleteCompany(ActionEvent event) {
    	Client client = Table.getSelectionModel().getSelectedItem();
    	List<Client> list ;
        list = proxyClientServiceRemote.searchClient(SearchText.getText());
        ObservableList<Client> items = FXCollections.observableArrayList(list);
        items.remove(Table.getSelectionModel().getSelectedIndex());
    	Table.getSelectionModel().clearSelection();
    	Table.getItems().clear();
    	Table.getItems().addAll(items);
    	proxyClientServiceRemote.delete(client);
    	
    }

    @FXML
    void updateCompany(ActionEvent event) {
    	Client client = Table.getSelectionModel().getSelectedItem();
    	client.setCompany(CompanyText1.getText());
    	client.setEmail(EmailText1.getText());
    	client.setPhoneNumber(Long.parseLong(PhoneText1.getText()));
    	proxyClientServiceRemote.update(client);
    	submit.setVisible(false);
		PhoneLabel11.setVisible(false);
		EmailLabel1.setVisible(false);
		CompanyLabel1.setVisible(false);
		CompanyText1.setVisible(false);
		PhoneText1.setVisible(false);
		EmailText1.setVisible(false);
		displayAllClient();

    }
    
    @FXML
    void SubmitVisible(ActionEvent event) {
    	CompanyText1.setText(Table.getSelectionModel().getSelectedItem().getCompany());
    	PhoneText1.setText(Long.toString(Table.getSelectionModel().getSelectedItem().getPhoneNumber()));
    	EmailText1.setText(Table.getSelectionModel().getSelectedItem().getEmail());
		if(submit.isVisible()==false){
    		submit.setVisible(true);
    		PhoneLabel11.setVisible(true);
    		EmailLabel1.setVisible(true);
    		CompanyLabel1.setVisible(true);
    		CompanyText1.setVisible(true);
    		PhoneText1.setVisible(true);
    		EmailText1.setVisible(true);
    		update.setText("Cancel");
    	}
    	else{
    		submit.setVisible(false);
    		PhoneLabel11.setVisible(false);
    		EmailLabel1.setVisible(false);
    		CompanyLabel1.setVisible(false);
    		CompanyText1.setVisible(false);
    		PhoneText1.setVisible(false);
    		EmailText1.setVisible(false);
    		update.setText("Update");
    	}
    }
    
    @FXML
    void TableClicked(MouseEvent event) {
    	CompanyText1.setText(Table.getSelectionModel().getSelectedItem().getCompany());
    	PhoneText1.setText(Long.toString(Table.getSelectionModel().getSelectedItem().getPhoneNumber()));
    	EmailText1.setText(Table.getSelectionModel().getSelectedItem().getEmail());
    }

}
