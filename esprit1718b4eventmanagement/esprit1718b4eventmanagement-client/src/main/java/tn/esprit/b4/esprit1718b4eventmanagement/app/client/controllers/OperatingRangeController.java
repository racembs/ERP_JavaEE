package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDialog;
import com.jfoenix.controls.JFXDialogLayout;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.controlsfx.control.CheckComboBox;
import org.controlsfx.control.CheckTreeView;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBoxTreeItem;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;

/**
 * FXML Controller class
 *
 * @author Ons
 */

public class OperatingRangeController implements Initializable {
	private JFXHamburger hamburger;
    @FXML
    private Label txtCurrentWindow;
    @FXML
    private AnchorPane holderPane;
    private JFXDrawer drawer;
    @FXML
    private Tab idArticle;
    @FXML
    private Tab idChargingStation;
    @FXML
    private Tab idOperatingRange;
    @FXML
    private Tab idManufacturing;
    @FXML
    private Tab idClient;
    @FXML
    private Tab idChargingStation12;
    @FXML
    private MenuBar idMenuArticle;
    @FXML
    private MenuBar idMenuManufacturing;
    @FXML
    private MenuBar idMenuOrders;
    @FXML
    private MenuBar idMenuClient;
    @FXML
    private TextField idCode;
    @FXML
    private TextArea idDesignation;
    @FXML
    private TextField idDeadline;
    @FXML
    private ComboBox<String> idStakingCond;
    @FXML
    private TableView<OperatingRange> idTab;
    @FXML
    private TableColumn<OperatingRange, String> idCodeTab;
    @FXML
    private TableColumn<OperatingRange, String> idDesignationTab;
    @FXML
    private TableColumn<OperatingRange, Integer> idDeadlineTab;
    @FXML
    private TableColumn<OperatingRange, String> idStakingCondTab;
    @FXML
    private Button show;
    @FXML
    private Button idUpdate;
    @FXML
    private Button idDelete;
    @FXML
    private Button idFind;
    @FXML
    private JFXTextField idoptrange;
    @FXML
    private TreeView<String> idTree;
    @FXML
    private ImageView idupimg;

    @FXML
    private StackPane stackPaneADD;
    @FXML
    private AnchorPane idsubmit;
    @FXML
    private AnchorPane idoptA;
    @FXML
    private TableView<Operation> opt;
    @FXML
    private TableColumn<Operation, String> ORtab;
    @FXML
    private TableColumn<Operation, String> Utab;
    @FXML
    private TableColumn<Operation, String> Etab;
    @FXML
    private TableColumn<Operation, String> Dtab;
    @FXML
    private TableColumn<Operation, Integer> PNtab;
    @FXML
    private TableColumn<Operation, Integer> UPToptab;
    @FXML
    private ComboBox<OperatingRange> ORop;
//    @FXML
//    private ComboBox<Article>idArticleCombo;
    @FXML
    private ComboBox<User> Uop;
    @FXML
    private ComboBox<ChargingStation> Eop;
    @FXML
    private CheckComboBox<String> idArticleComboo;
    @FXML
    private TextArea Dop;
    @FXML
    private TextField PNop;
    @FXML
    private TextField UPTop;
    @FXML
    private ImageView addop;

    
    @FXML
    private ImageView ok;
    @FXML
    private ImageView next;
 
    @FXML
    private JFXTextField find;
    @FXML
    private ImageView findbt;
    @FXML
    private ImageView addop1;
 
 
    @FXML
    private ImageView ok1;
  
    @FXML
    private JFXTextField find1;
    @FXML
    private ImageView findbt1;
    @FXML
    private ImageView back;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    			try {
    				String ArticlejndiName1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
    		    	Context contextArticle1;

			contextArticle1 = new InitialContext();
			
		  	ArticleServiceRemote ArticleProxy1 = (ArticleServiceRemote) contextArticle1.lookup(ArticlejndiName1);
	    	 Article article = new Article();
		     List<Article> listA1 = ArticleProxy1.DisplayArticle();
		     
	          ObservableList<Article> obListA1 = FXCollections.observableList(listA1);
//	          idArticleComboo.setAccessibleText("aa");
	         
	          ObservableList<String> strings = FXCollections.observableArrayList();
	          for (int i = 0; i <listA1.size() ; i++) {
	              strings.add(listA1.get(i).getArticleCode());
	          }
	          idArticleComboo.getItems().addAll(strings);

    	         
	             idArticleComboo.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
	        	     public void onChanged(ListChangeListener.Change<? extends String> c) {
	        	    	// System.out.println(idArticleComboo.getCheckModel().getCheckedItems().get(0));
	        	    	// System.out.println(idArticleComboo.getCheckModel().getItemCount());
	         	         Integer count =idArticleComboo.getCheckModel().getItemCount();
	         	        
	         	        	 
	         	        	 System.out.println(idArticleComboo.getCheckModel().getCheckedItems().get(0));
	         	        	// System.out.println("onss"+m);
	         	        	//System.out.println(idArticleComboo.getCheckModel().getCheckedItems().get(0));
	         	        	 
	         	        	 
						
	        	    	
	        	    	 
	        	         
	        	     }
	        	 });
	          //idArticleComboo.getAccessibleText().compareTo(null);
//	          idArticleComboo.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
//	              public void onChanged(ListChangeListener.Change<? extends String> c) {
//	                  System.out.println(idArticleComboo.getCheckModel().getCheckedItems().toString());
//	              }
//	          });
//	          
	          idArticleComboo.getCheckModel().getCheckedItems().addListener(new ListChangeListener<String>() {
	              public void onChanged(ListChangeListener.Change<? extends String> c) {

	            	  System.out.println(idArticleComboo.getAccessibleText());
	              }
	          });
	          
//	          idArticleComboo.setConverter(new javafx.util.StringConverter<Article>(
//            		  ) {
//	          System.out.println(idArticleComboo.getCheckModel().getCheckedItems());
	          
//	          idArticleCombo.setConverter(new javafx.util.StringConverter<Article>(
//            		  ) {
//				
//				@Override
//				public String toString(Article object) {
//					 return String.valueOf(object.getArticleCode()+ "-" +object.getDescription());
//				}
//				
//				@Override
//				public Article fromString(String string) {
//					// TODO Auto-generated method stub
//					return null;
//				}
//			});      
//	          
//	          idArticleCombo.setItems(obListA1);
//	          idArticleCombo.valueProperty().addListener((obs, oldVal, newVal) -> {
//	              String selectionText =  newVal.getArticleCode()+ "-" + newVal.getDescription();
//	              System.out.println(selectionText);
//	            
//	          });
//	          idArticleCombo.getSelectionModel().selectLast();
		} catch (NamingException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
  
    	
    	
    	idoptA.setVisible(false);
    	idStakingCond.getItems().addAll("Consecutive","Overlap","With staking delay","Parallel");
    	idStakingCond.getSelectionModel().selectLast();
    	
    	Context context;
		try {
			context = new InitialContext();
			String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	        OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);
	        OperatingRange op = new OperatingRange();
	        
			String jndiNameOperation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
			Context contextOperation = new InitialContext();
			OperationServiceRemote proxyOperation = (OperationServiceRemote) contextOperation.lookup(jndiNameOperation);
			//Operation opt = new Operation();
			
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();
			
			String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
			Context contextArticle = new InitialContext();
			ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) contextArticle.lookup(ArticlejndiName);
			
			
			Tree();
//	        idCodeTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("code"));
//	        idDesignationTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("designation"));
//	        idDeadlineTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, Integer>("deadline"));
//	        idStakingCondTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("stakingcondition"));
//	        
//	        List<OperatingRange> list = proxy.DisplayOperatingRange();
//	        List<Operation> listOpt = proxyOperation.DisplayOperation();
//	        List<Article> listA = ArticleProxy.DisplayArticle();
//
//	      
//	        
//	        ObservableList<Operation> items4 = FXCollections.observableArrayList(listOpt);
//	        //Long N = proxy.CountOperatingRange();
//	        ObservableList<OperatingRange> items22 = FXCollections.observableArrayList(list);
//	        System.out.println(items22.get(0).getDesignation());
//	        idTab.setItems(items22);
//	        
//	        TreeItem<String> GPAO = new TreeItem<String>("GPAO");
//	        
//	        for (int a = 0; a < listA.size(); a++) {
//	        	ObservableList<Article> itemsA = FXCollections.observableArrayList(listA);
//		        System.out.println(itemsA.get(a).getArticleCode());
//		 
//		        //String A=("Article"+a);
//
//		        TreeItem<String> Article = new TreeItem<String>(itemsA.get(a).getArticleCode());
//		        Article.setValue(itemsA.get(a).getArticleCode());
//		        
//		        ObservableList<OperatingRange> items = FXCollections.observableArrayList(itemsA.get(a).getOperatingranges());
//		        for (OperatingRange operatingRange : items) {
//		        	TreeItem<String> Gammes = new TreeItem<String>(operatingRange.getCode());
//		    		Gammes.setValue(operatingRange.getCode());
//		    		Article.getChildren().addAll(Gammes);
//		    		
//		    	
//				}
//
//	         
//		        
//	         GPAO.getChildren().addAll(Article);
//	         idTree.setRoot(GPAO);
////	         idTree.setCellFactory(TreeView.<String>forTreeView());
//
//	 		 
//	 		    	 
//	        }
//	        
//  
//	        final TreeView<String> treeView = new TreeView<>(GPAO);  
//	        
//	        treeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<String>>(){
//	             public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c){
//	            	 
//	                 System.out.println("onsss"+treeView.getSelectionModel().getSelectedItems());
//	                 
//	                 String ch=treeView.getSelectionModel().getSelectedItems().toString();
//	                 
//	                 JFXDialogLayout content=new JFXDialogLayout();
//	                 content.setHeading(new Text("Operating Range"));
//	           		content.setBody(new Text(ch));
//	           		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
//	           		jfxDialog.show();
//	             }
//	        
//	        
//	    }); 
//	        
	         
	 //*************************************ADD****************************************//        
	        addop1.setOnMouseClicked((MouseEvent e) -> {
	
				try {
					String OperatingRangejndiName42 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";

					Context context42;
					context42 = new InitialContext();
					OperatingRangeServiceRemote proxy42 =  (OperatingRangeServiceRemote) context42.lookup(OperatingRangejndiName42);
					//OperatingRange optrange1 = new OperatingRange("AR","Table mount","Series",30);
					OperatingRange optrange = new OperatingRange();
					Article a = new Article();
				    optrange.setCode(idCode.getText());
					optrange.setDesignation(idDesignation.getText());
					int Deadline = Integer.parseInt(idDeadline.getText());
					optrange.setDeadline(Deadline);
					
					optrange.setStakingcondition(idStakingCond.getValue().toString());
			        
					
//					int idA = idArticleCombo.getSelectionModel().getSelectedItem().getId();
					//System.out.println("onssssss"+idArticleComboo.getCheckModel());
					
					//List<Article> listA1= ArticleProxy.findArticleByCode(idArticleComboo.getCheckModel().toString());
//					List<Article> listA1= ArticleProxy.findArticleByCode("1");
//
					int idOptR=proxy42.addOperatingRange(optrange);
    	        	 for(int i=0;i<idArticleComboo.getCheckModel().getCheckedItems().size();i++){
    	        		 System.out.println("here :"+idArticleComboo.getCheckModel().getCheckedItems().get(i)); 
    	        		 
    	        		 proxy42.assignOperatingRangeToArticle (idOptR,Integer.valueOf(idArticleComboo.getCheckModel().getCheckedItems().get(i)));
    	        	 }
				
//			        for (int b = 0; b < listA1.size(); b++) {
//			        	
//			        	ObservableList<Article> itemsA1 = FXCollections.observableArrayList(listA1);
//			        	itemsA1.get(b).getId();
//	        proxy42.assignOperatingRangeToArticle (idOptR,itemsA1.get(b).getId());
//			        	
//			        	}
				      
				      
			        
					
				//	proxy42.addOperatingRange(optrange);
					
				
					
					//proxy42.assignOperatingRangeToArticle (idOptR,idA);
					//System.out.println("created");
					
					   List<OperatingRange> list12 = proxy42.DisplayOperatingRange();
				        ObservableList<OperatingRange> items12 = FXCollections.observableArrayList(list12);
				        System.out.println(items12.get(0).getDesignation());
				        idTab.setItems(items12);
				        
	
				        
					    Alert alert = new Alert(AlertType.INFORMATION);
						alert.setTitle("Operating Range Added");
						alert.setHeaderText("Succesful");
						alert.showAndWait();
				} catch (NamingException p) {
					// TODO Auto-generated catch block
					
				}
    	Tree();
			 });
	        

	        
	        
	        findbt1.setOnMouseClicked((MouseEvent e) -> {
	       		Context context1;
	    		try {
	    			
	    			context1 = new InitialContext();
	    			String OperatingRangejndiName1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	    	        OperatingRangeServiceRemote proxy1 =  (OperatingRangeServiceRemote) context1.lookup(OperatingRangejndiName1);
	    	        List<OperatingRange> list1 = proxy1.find(idoptrange.getText());
	    	        
	    	        ObservableList<OperatingRange> items1 = FXCollections.observableArrayList(list1);
	    	       // System.out.println(items1.get(0).getDesignation());
	    	        idTab.setItems(items1);
	    	        System.out.println(idoptrange.getText());
	    		 
	    		  
	    		} catch (NamingException x) {
	    			
	    			
	    		}
	    	  });
	        
	        idupimg.setOnMouseClicked((MouseEvent e) -> {
	        	
	        	
	        	if(idTab.getSelectionModel().getSelectedItem()==null){
	        		 Alert alert1 = new Alert(AlertType.INFORMATION);
	        			alert1.setTitle("Please Select an Operating Range");
	        			alert1.setHeaderText("Please Select");
	        			alert1.showAndWait();
	        	}
	        	else if(idTab.getSelectionModel().getSelectedItem()!=null){
	        	idoptA.setVisible(true);
	  

	  			Context contextUser;
	  			Context contextEquipment;
	  			Context contextOptR;
	  			try {
					contextUser = new InitialContext();
					UserServiceRemote proxyuser = (UserServiceRemote) contextUser.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
		  	    	User user = new User();
		  			List<User>listu = proxyuser.DisplayUser();
		  			
		  		    //ComboBox User
		  		  ObservableList<User> obListu = FXCollections.observableList(listu);
		  		    
		  		  Uop.setConverter(new javafx.util.StringConverter<User>(
		        		  ) {
					
					@Override
					public String toString(User object) {
						return String.valueOf(object.getFirstname()+"-"+object.getLastname());
					}
					
					@Override
					public User fromString(String string) {
						// TODO Auto-generated method stub
						return null;
					}
				});
		             Uop.setItems(obListu);
//		             Uop.valueProperty().addListener((obs, oldVal, newVal) -> {
//		                 String selectionText =  newVal.getFirstname() + "-" + newVal.getLastname();
//
//		                 System.out.println(selectionText);
//		               
//		             });
		             Uop.getSelectionModel().selectLast();
		             Eop.setDisable(true);
		              
		              
		              
		              
		                contextOptR = new InitialContext();
		                OperatingRangeServiceRemote proxyOptR = (OperatingRangeServiceRemote) contextOptR.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote");
			 	    	OperatingRange operatingrange = new OperatingRange();
			 	    	List<OperatingRange> listoptR = proxyOptR.DisplayOperatingRange();

			 	    	//ComboBox Equipment
			              ObservableList<OperatingRange> optR = FXCollections.observableList(listoptR);
			          
			              ORop.setConverter(new javafx.util.StringConverter<OperatingRange>(
			            		  ) {
							
							@Override
							public String toString(OperatingRange object) {
								 return String.valueOf(object.getCode()+"-"+object.getDesignation());
							}
							
							@Override
							public OperatingRange fromString(String string) {
								// TODO Auto-generated method stub
								return null;
							}
						});
			              ORop.setItems(optR);
//			              ORop.valueProperty().addListener((obs, oldVal, newVal) -> {
//			                  String selectionText =  newVal.getCode() + "-" + newVal.getDesignation();
//			                  System.out.println(selectionText);
//			                
//			              });
			              ORop.getSelectionModel().selectLast();
			              
			              
				        	ORtab.setCellValueFactory(new Callback<CellDataFeatures<Operation,String>,ObservableValue<String>>(){

					              @Override
					              public ObservableValue<String> call(CellDataFeatures<Operation, String> param) {
					                  return new SimpleStringProperty(param.getValue().getOptrange().getCode().toString());
					              }
					          }); 
				        	 Etab.setCellValueFactory(new Callback<CellDataFeatures<Operation,String>,ObservableValue<String>>(){

					              @Override
					              public ObservableValue<String> call(CellDataFeatures<Operation, String> param) {
					                  return new SimpleStringProperty(param.getValue().getChargingstations().getEquipement().getSerialNum()+"-"+param.getValue().getChargingstations().getEquipement().getDescription());
					              }
					          }); 
				        	 Utab.setCellValueFactory(new Callback<CellDataFeatures<Operation,String>,ObservableValue<String>>(){

					              @Override
					              public ObservableValue<String> call(CellDataFeatures<Operation, String> param) {
					                  return new SimpleStringProperty(param.getValue().getChargingstations().getUser().getFirstname()+"-"+param.getValue().getChargingstations().getUser().getLastname());
					              }
					          }); 
				        	 Dtab.setCellValueFactory(new PropertyValueFactory<Operation, String>("description"));
				        	 PNtab.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("phasenumber"));
				        	 UPToptab.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("unitproductiontime"));
				        	 
				        		String jndiNameOperation1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
				    			Context contextOperation1 = new InitialContext();
				    			OperationServiceRemote proxyOperation1 = (OperationServiceRemote) contextOperation1.lookup(jndiNameOperation1);
				    			//Operation opt = new Operation();
							   List<Operation> listopt1 = proxyOperation1.findOprationByChargId(idTab.getSelectionModel().getSelectedItem().getIdoptrange());
						        ObservableList<Operation> itemsopt1 = FXCollections.observableArrayList(listopt1);
						        
						        if (itemsopt1.size()==0)
						        {
						              JFXDialogLayout content=new JFXDialogLayout();
						               content.setHeading(new Text("No Operation Available"));
						         		content.setBody(new Text("This Operating Range has no current Operation "));
						         		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
						         		jfxDialog.show();
						        }
						        opt.setItems(itemsopt1);
						        
						        
						        addop.setOnMouseClicked((MouseEvent u) -> {
						        
									try {
										String jndiNameOperation2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
						    			Context contextOperation2;
										contextOperation2 = new InitialContext();
						    			OperationServiceRemote proxyOperation2 = (OperationServiceRemote) contextOperation2.lookup(jndiNameOperation2);
						    			
										
										
										Operation operation = new Operation();
										ChargingStationPK pk = new ChargingStationPK();
										
										operation.setDescription(Dop.getText());
										int a=Integer.parseInt(PNop.getText());
										operation.setPhasenumber(a);
										int b=Integer.parseInt(UPTop.getText());
										operation.setUnitproductiontime(b);
										int idoptr = ORop.getSelectionModel().getSelectedItem().getIdoptrange();	
										
										  int idE = Eop.getSelectionModel().getSelectedItem().getEquipement().getId();
										  int idU = Uop.getSelectionModel().getSelectedItem().getId();
										pk.setId_equipment(idE);
										pk.setIdUser(idU);
										proxyOperation2.addOperation(idoptr, pk, operation);
										System.out.println("created");
										
										   List<Operation> list12 = proxyOperation2.DisplayOperation();
									        ObservableList<Operation> items12 = FXCollections.observableArrayList(list12);
									 
									        opt.setItems(items12);
									        
									        
										    Alert alert = new Alert(AlertType.INFORMATION);
											alert.setTitle("Operation Added");
											alert.setHeaderText("Succesful");
											alert.showAndWait();
									} catch (Exception e1) {
										// TODO Auto-generated catch block
										e1.printStackTrace();
									}
				
								
				    	
							 });
						        
			
			
						     
						     
					
						        
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	        	      
			        
			        

	        	}
	    	  });
	        
	        
	        
		     
			back.setOnMouseClicked((MouseEvent a) -> { 
				  Parent parent= null;
				  	try {
		  				parent  =FXMLLoader.load(getClass().getResource("/views/MenuuGPAO.fxml"));
		  				Scene scene=new Scene(parent);
		  				Stage primaryStage= new Stage(); 
		  				primaryStage.setScene(scene);
		  				primaryStage.show();
		  				back.getScene().getWindow().hide();
				  	} catch (Exception e1) {
		  				// TODO Auto-generated catch block
		  				e1.printStackTrace();
		  			}
			  });
			
	
		} catch (NamingException e) {
			// TODO Auto-generated catch block
		}
   
		

    	


    }


    @FXML
    public void OnKeyReleased1(KeyEvent event)
    {
  	// System.out.print(idoptrange.getText());
		Context context1;
		try {
			
			context1 = new InitialContext();
			String OperatingRangejndiName1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	        OperatingRangeServiceRemote proxy1 =  (OperatingRangeServiceRemote) context1.lookup(OperatingRangejndiName1);
	        List<OperatingRange> list1 = proxy1.find(idoptrange.getText());
	        
	        ObservableList<OperatingRange> items1 = FXCollections.observableArrayList(list1);
	       // System.out.println(items1.get(0).getDesignation());
	        idTab.setItems(items1);
	        System.out.println(idoptrange.getText());
		 
		  
		} catch (NamingException x) {
			
			
		}
    }

    @FXML
    public void OnKeyReleased2(KeyEvent event)
    {
  	// System.out.print(find.getText());
    	
		
		try {
			Context context5;	
			context5 = new InitialContext();
		String jndiNameOperation5 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
		
		OperationServiceRemote proxyOperation5 = (OperationServiceRemote) context5.lookup(jndiNameOperation5);
		//Operation opt = new Operation();
	   List<Operation> listopt5 = proxyOperation5.findOpration(find.getText());
        ObservableList<Operation> itemsopt5 = FXCollections.observableArrayList(listopt5);
        opt.setItems(itemsopt5);
  	  
  		} catch (NamingException x) {
  			
  			
  		}
        
    }
    
    @FXML
    public void OnUpdate1(ActionEvent event)
    {      	Context context25;
	try {
		context25 = new InitialContext();
		String OperatingRangejndiName25 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
        OperatingRangeServiceRemote proxy25 =  (OperatingRangeServiceRemote) context25.lookup(OperatingRangejndiName25);

        OperatingRange opt= idTab.getSelectionModel().getSelectedItem();
        String c= String.valueOf(opt.getCode());
        String d= String.valueOf(opt.getDeadline());
        idCode.setText(c);
        idDeadline.setText(d);
        idDesignation.setText(opt.getDesignation());
        idStakingCond.setValue(opt.getStakingcondition());
        
        ok1.setOnMouseClicked((MouseEvent a) -> {
        	opt.setCode(idCode.getText());
        	opt.setDesignation(idDesignation.getText());
			int Deadline = Integer.parseInt(idDeadline.getText());
			opt.setDeadline(Deadline);
			opt.setStakingcondition(idStakingCond.getValue().toString());
						
			proxy25.update(idTab.getSelectionModel().getSelectedItem());
			
			System.out.println("modif");
			   List<OperatingRange> list25 = proxy25.DisplayOperatingRange();
      	        ObservableList<OperatingRange> items25 = FXCollections.observableArrayList(list25);
    	        idTab.setItems(items25);
    	        
    	        
    		 Alert alert1 = new Alert(AlertType.INFORMATION);
    			alert1.setTitle("Operating Range Modified");
    			alert1.setHeaderText("Succesful");
    			alert1.showAndWait();
        });
        
        

	} catch (NamingException m) {
		// TODO Auto-generated catch block
		m.printStackTrace();
	}
    }
    @FXML
    public void OnDelete1(ActionEvent event)
    {
     	Alert alert = new Alert(AlertType.CONFIRMATION);
        	alert.setTitle("WARNING");
    		alert.setHeaderText("Are You Sure?");
        	
        	if (alert.showAndWait().get () == ButtonType.OK)
        	{
        		
        		try {
        			String OpRangejndiName55 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
            		Context context55;
        			context55 = new InitialContext();      			
        	        OperatingRangeServiceRemote proxy55 =  (OperatingRangeServiceRemote) context55.lookup(OpRangejndiName55);
        	        
        	        proxy55.delete(idTab.getSelectionModel().getSelectedItem());
            	
            	
        		   List<OperatingRange> list55 = proxy55.DisplayOperatingRange();
        	        ObservableList<OperatingRange> items55 = FXCollections.observableArrayList(list55);
        	        //System.out.println(items55.get(0).getDesignation());
        	        idTab.setItems(items55);
        	        
        	        
        		 Alert alert1 = new Alert(AlertType.INFORMATION);
        			alert1.setTitle("Operating Range Deleted");
        			alert1.setHeaderText("Succesful");
        			alert1.showAndWait();
        		//System.out.println("deleted");
            	
            	} catch (NamingException a) {
        			// TODO Auto-generated catch block
        			
        		}
        		
        	}
    }
    @FXML
    public void OnUpdate2(ActionEvent event)
    {
   	 if(opt.getSelectionModel().getSelectedItem()!=null){
			try {
			
				String jndiNameOperation4 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
 			Context contextOperation4;
				contextOperation4 = new InitialContext();
 			OperationServiceRemote proxyOperation4 = (OperationServiceRemote) contextOperation4.lookup(jndiNameOperation4);
 			
				
 			Operation chs = opt.getSelectionModel().getSelectedItem();
				String c= String.valueOf(chs.getPhasenumber());
				String d= String.valueOf(chs.getUnitproductiontime());
	    		  
	    		Dop.setText(chs.getDescription());
	    		PNop.setText(c);
	    		UPTop.setText(d);
				Eop.setDisable(true);
				Uop.setDisable(true);
				ORop.setDisable(true);	    		
//	    		ORop.getSelectionModel().select(chs.getOptrange().getIdoptrange());
//	    		Eop.getSelectionModel().select(chs.getChargingstations().getEquipement().getId());
//	    		Uop.getSelectionModel().select(chs.getChargingstations().getUser().getId());
 			
	    		 ok.setOnMouseClicked((MouseEvent a) -> {	
	    		
	 				 
					chs.setDescription(Dop.getText());
					
					int x=Integer.parseInt(PNop.getText());
					int y=Integer.parseInt(UPTop.getText());		  
					chs.setPhasenumber(x);
					chs.setUnitproductiontime(y);

//					int idE = Eop.getSelectionModel().getSelectedItem().getEquipement().getId();
//					int idU = Uop.getSelectionModel().getSelectedItem().getId();
//					int idOptR = ORop.getSelectionModel().getSelectedItem().getIdoptrange();
//					
//					OperationPK operationpk = new OperationPK();	
//					
//					ChargingStationPK charginstationpk = new ChargingStationPK();
//					charginstationpk.setId_equipment(idE);
//					charginstationpk.setIdUser(idU);
//					
//					operationpk.setIdChargingStation(charginstationpk);
//					operationpk.setId(idOptR);
//					chs.setOperationPK(operationpk);
	    			 
	    			 
	    			 proxyOperation4.update(chs);
				
				
				   List<Operation> list14 = proxyOperation4.DisplayOperation();
			        ObservableList<Operation> items14 = FXCollections.observableArrayList(list14);
			 
			        opt.setItems(items14);
			        
			        
			        Alert alert1 = new Alert(AlertType.INFORMATION);
	    			alert1.setTitle("Operation Modified");
	    			alert1.setHeaderText("Succesful");
	    			alert1.showAndWait();
					
	    		 });	
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

 	 
 	 }
    }
    @FXML
    public void OnDelete2(ActionEvent event)
    {
		try {
			Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("WARNING");
			alert.setHeaderText("Are You Sure?");
	    	
	    	if (alert.showAndWait().get () == ButtonType.OK)
	    	{
			String jndiNameOperation3 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
			Context contextOperation3;
			contextOperation3 = new InitialContext();
			OperationServiceRemote proxyOperation3 = (OperationServiceRemote) contextOperation3.lookup(jndiNameOperation3);
			
			
			proxyOperation3.delete(opt.getSelectionModel().getSelectedItem());
			
			
			   List<Operation> list13 = proxyOperation3.DisplayOperation();
		        ObservableList<Operation> items13 = FXCollections.observableArrayList(list13);
		 
		        opt.setItems(items13);
		        
		        
		        Alert alert1 = new Alert(AlertType.INFORMATION);
    			alert1.setTitle("Operation Deleted");
    			alert1.setHeaderText("Succesful");
    			alert1.showAndWait();
				
	    	}
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    }
    
    public void FindEquipment (ActionEvent event)
    {
    	Integer id=Uop.getValue().getId();
        Eop.setDisable(false);
        
       
		try {
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
	
			
	    	List<ChargingStation> listE = proxyChargingStation.findByUser(id);

	    	//ComboBox Equipment
	         ObservableList<ChargingStation> obListeq = FXCollections.observableList(listE);
	         
	         Eop.setConverter(new javafx.util.StringConverter<ChargingStation>(
	       		  ) {
				
				@Override
				public String toString(ChargingStation object) {
					 return String.valueOf(object.getEquipement().getSerialNum()+"-"+object.getEquipement().getDescription());
				}
				
				@Override
				public ChargingStation fromString(String string) {
					// TODO Auto-generated method stub
					return null;
				}
			});              
	         
	         Eop.setItems(obListeq);

	         Eop.getSelectionModel().selectLast();
	    
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    
    }
  public void Tree ()
  {
	  try{
	  Context context = new InitialContext();
		String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
      OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);
      OperatingRange op = new OperatingRange();
      
		String jndiNameOperation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
		Context contextOperation = new InitialContext();
		OperationServiceRemote proxyOperation = (OperationServiceRemote) contextOperation.lookup(jndiNameOperation);
		//Operation opt = new Operation();
		
		String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
		Context contextChargingStation = new InitialContext();
		ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
		ChargingStation ch = new ChargingStation();
		
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context contextArticle = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) contextArticle.lookup(ArticlejndiName);
		
		
      idCodeTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("code"));
      idDesignationTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("designation"));
      idDeadlineTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, Integer>("deadline"));
      idStakingCondTab.setCellValueFactory(new PropertyValueFactory<OperatingRange, String>("stakingcondition"));
      
      List<OperatingRange> list = proxy.DisplayOperatingRange();
      List<Operation> listOpt = proxyOperation.DisplayOperation();
      List<Article> listA = ArticleProxy.DisplayArticle();

    
      
      ObservableList<Operation> items4 = FXCollections.observableArrayList(listOpt);
      //Long N = proxy.CountOperatingRange();
      ObservableList<OperatingRange> items22 = FXCollections.observableArrayList(list);
      System.out.println(items22.get(0).getDesignation());
      idTab.setItems(items22);
      
      TreeItem<String> GPAO = new TreeItem<String>("GPAO");
      
      for (int a = 0; a < listA.size(); a++) {
      	ObservableList<Article> itemsA = FXCollections.observableArrayList(listA);
	        System.out.println(itemsA.get(a).getArticleCode());
	 
	        //String A=("Article"+a);

	        TreeItem<String> Article = new TreeItem<String>(itemsA.get(a).getArticleCode());
	        Article.setValue(itemsA.get(a).getArticleCode());
	        
	        ObservableList<OperatingRange> items = FXCollections.observableArrayList(itemsA.get(a).getOperatingranges());
	        for (OperatingRange operatingRange : items) {
	        	TreeItem<String> Gammes = new TreeItem<String>(operatingRange.getCode());
	    		Gammes.setValue(operatingRange.getCode());
	    		Article.getChildren().addAll(Gammes);
	    		
	    	
			}

       
	        
       GPAO.getChildren().addAll(Article);
       idTree.setRoot(GPAO);
//       idTree.setCellFactory(TreeView.<String>forTreeView());

		 
		    	 
      }
      

//      final TreeView<String> treeView = new TreeView<>(GPAO);  
//      
//      treeView.getSelectionModel().getSelectedItems().addListener(new ListChangeListener<TreeItem<String>>(){
//           public void onChanged(ListChangeListener.Change<? extends TreeItem<String>> c){
//          	 
//               System.out.println("onsss"+treeView.getSelectionModel().getSelectedItems());
//               
//               String ch=treeView.getSelectionModel().getSelectedItems().toString();
//               
//               JFXDialogLayout content=new JFXDialogLayout();
//               content.setHeading(new Text("Operating Range"));
//         		content.setBody(new Text(ch));
//         		JFXDialog jfxDialog=new JFXDialog(stackPaneADD,content,JFXDialog.DialogTransition.TOP);
//         		jfxDialog.show();
//           }
//      
//      
//  }); 
      
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
  }
}
