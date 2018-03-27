package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.scene.control.TreeView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.cell.CheckBoxTreeCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.util.Callback;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;

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
    private TreeView<String> idCheckTree;
    @FXML
    private ImageView idupimg;
 
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
    private ComboBox<?> ORop;
    @FXML
    private ComboBox<?> Uop;
    @FXML
    private ComboBox<?> Eop;
    @FXML
    private TextArea Dop;
    @FXML
    private TextField PNop;
    @FXML
    private TextField UPTop;
    @FXML
    private ImageView addop;
    @FXML
    private ImageView uoop;
    @FXML
    private ImageView delop;
    @FXML
    private ImageView ok;
    @FXML
    private ImageView cancel;
    @FXML
    private JFXTextField find;
    @FXML
    private ImageView findbt;
    @FXML
    private ImageView addop1;
    @FXML
    private ImageView uoop1;
    @FXML
    private ImageView delop1;
    @FXML
    private ImageView ok1;
    @FXML
    private ImageView cancel1;
    @FXML
    private JFXTextField find1;
    @FXML
    private ImageView findbt1;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	idoptA.setVisible(false);
    	idStakingCond.getItems().addAll("Consecutive","Overlap","With staking delay","Parallel");
    	idStakingCond.getSelectionModel().selectLast();
    	
    	Context context;
		try {
			context = new InitialContext();
			String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	        OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);

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

	        //Long N = proxy.CountOperatingRange();
	        ObservableList<OperatingRange> items22 = FXCollections.observableArrayList(list);
	        System.out.println(items22.get(0).getDesignation());
	        idTab.setItems(items22);
	        
	        CheckBoxTreeItem<String> GPAO = new CheckBoxTreeItem<String>("GPAO");
	        
	        for (int a = 0; a < listA.size(); a++) {
	        	ObservableList<Article> itemsA = FXCollections.observableArrayList(listA);
		        System.out.println(itemsA.get(a).getArticleCode());
		        //String A=("Article"+a);

		        CheckBoxTreeItem<String> Article = new CheckBoxTreeItem<String>(itemsA.get(a).getArticleCode());
		        Article.setValue(itemsA.get(a).getArticleCode());
		        
		        ObservableList<OperatingRange> items = FXCollections.observableArrayList(itemsA.get(a).getOperatingranges());
		        for (OperatingRange operatingRange : items) {
		        	CheckBoxTreeItem<String> Gammes = new CheckBoxTreeItem<String>(operatingRange.getCode());
		    		Gammes.setValue(operatingRange.getCode());
		    		Article.getChildren().addAll(Gammes);
				}
//	         for (int i = 0; i < list.size(); i++) {
//			        //Article.setExpanded(true);
//			        
//	 	        
//		        //System.out.println(items.get(i).getCode());
//		        idTab.setItems(items);
//		    	CheckBoxTreeItem<String> Gammes = new CheckBoxTreeItem<String>(items.get(i).getCode());
//	    		Gammes.setValue(items.get(i).getCode());
//		        
//		        String G=("Gamme"+i);
//		        //CheckBoxTreeItem<String> Gamme1 = new CheckBoxTreeItem<String>(G);
//		    	
//		    	
//		    	
//		     //   Article1.getChildren().addAll(new CheckBoxTreeItem<String>(items.get(j).getCode()),Gamme1);
//		       // CheckBoxTreeItem<String> Gamme1 = new CheckBoxTreeItem<String>(items.get(j).getCode());
//		    		
//		            for (int k = 0; k < listOpt.size(); k++) {
//			        	
//			 	        ObservableList<Operation> itemss = FXCollections.observableArrayList(listOpt);
//			 	      //  if(items.get(j).getIdoptrange()==itemss.get(k).getOperationPK().getId()){
//				     //   System.out.println(itemss.get(k).getDescription());}
//	                //	  System.out.println(items.get(j).getIdoptrange());
//	                //	  System.out.println(itemss.get(k).getOperationPK().getId());
//					    
//			
//					        Gammes.getChildren().add(new CheckBoxTreeItem<String>(itemss.get(k).getDescription()));
//					
//				//}
//			        }
//		            
//		        
//		    	 
//		    	Article.getChildren().addAll(Gammes);
//	         }
	         
		        
	         GPAO.getChildren().addAll(Article);
	 	    		 idCheckTree.setRoot(GPAO);
	 		    	 idCheckTree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
		      
	        }
	        
//	   	 CheckBoxTreeItem<String> G1 = new CheckBoxTreeItem<String>("Gamme1");
//    	 CheckBoxTreeItem<String> G2 = new CheckBoxTreeItem<String>("Gamme2");
//    	 CheckBoxTreeItem<String> G3 = new CheckBoxTreeItem<String>("Gamme3");
//    	 CheckBoxTreeItem<String> G4 = new CheckBoxTreeItem<String>("Gamme4");
//    	 CheckBoxTreeItem<String> G5 = new CheckBoxTreeItem<String>("Gamme5");
//    	 
//    	 CheckBoxTreeItem<String> O1 = new CheckBoxTreeItem<String>("Operation1");
//    	 CheckBoxTreeItem<String> O2 = new CheckBoxTreeItem<String>("Operation2");
//    	 
//    	 CheckBoxTreeItem<String> Article1 = new CheckBoxTreeItem<String>("Article1");
//    	 Article1.setExpanded(true);
//    	 Article1.getChildren().addAll(G1, G2, G3, G4, G5);
//    	 G1.getChildren().addAll(O1,O2);
    	 
//    	 
//    	 idCheckTree.setRoot(Article1);
//    	 idCheckTree.setCellFactory(CheckBoxTreeCell.<String>forTreeView());
    
	        
	        
	        
///////////////////////////////////////////////***************///////////////////////////////
	        addop1.setOnMouseClicked((MouseEvent e) -> {
	
				try {
					String OperatingRangejndiName42 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";

					Context context42;
					context42 = new InitialContext();
					OperatingRangeServiceRemote proxy42 =  (OperatingRangeServiceRemote) context42.lookup(OperatingRangejndiName42);
					//OperatingRange optrange1 = new OperatingRange("AR","Table mount","Series",30);
					OperatingRange optrange = new OperatingRange();
					optrange.setCode(idCode.getText());
					optrange.setDesignation(idDesignation.getText());
					int Deadline = Integer.parseInt(idDeadline.getText());
					optrange.setDeadline(Deadline);
					optrange.setStakingcondition(idStakingCond.getValue().toString());
								
					proxy42.addOperatingRange(optrange);
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
    	
			 });
	        
	        
	        delop1.setOnMouseClicked((MouseEvent e) -> {
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

	            	Integer id= idTab.getSelectionModel().getSelectedItem().getIdoptrange();
	            	proxy55.deleteOperatingRange(id);
	            	
	        		   List<OperatingRange> list55 = proxy55.DisplayOperatingRange();
	        	        ObservableList<OperatingRange> items55 = FXCollections.observableArrayList(list55);
	        	        System.out.println(items55.get(0).getDesignation());
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
	    	  });
	        
	        uoop1.setOnMouseClicked((MouseEvent e) -> {
	        	Context context2;
	    		try {
	    			context2 = new InitialContext();
	    			String OperatingRangejndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
	    	        OperatingRangeServiceRemote proxy2 =  (OperatingRangeServiceRemote) context2.lookup(OperatingRangejndiName2);

	    	        OperatingRange opt= idTab.getSelectionModel().getSelectedItem();
	    	        String c= String.valueOf(opt.getCode());
	    	        String d= String.valueOf(opt.getDeadline());
	    	        idCode.setText(c);
	    	        idDeadline.setText(d);
	    	        idDesignation.setText(opt.getDesignation());
	    	        idStakingCond.setValue(opt.getStakingcondition());
	    	        
	    	        idupimg.setOnMouseClicked((MouseEvent a) -> {
	    	        	opt.setCode(idCode.getText());
	    	        	opt.setDesignation(idDesignation.getText());
	    				int Deadline = Integer.parseInt(idDeadline.getText());
	    				opt.setDeadline(Deadline);
	    				opt.setStakingcondition(idStakingCond.getValue().toString());
	    							
	    				proxy2.updateOperatingRange(opt);
	    				
	    				System.out.println("modif");
	    				   List<OperatingRange> list2 = proxy2.DisplayOperatingRange();
	    	        });
	    	        
	    	        

	    		} catch (NamingException b) {
	    			// TODO Auto-generated catch block
	    			b.printStackTrace();
	    		}
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
	        	 

				   //List<Operation> listopt = proxyOperation.DisplayOperation();
				   List<Operation> listopt = proxyOperation.findOprationByChargId(idTab.getSelectionModel().getSelectedItem().getIdoptrange());
			        ObservableList<Operation> itemsopt = FXCollections.observableArrayList(listopt);
			        opt.setItems(itemsopt);
		
	        	}
	    	  });
		} catch (NamingException e) {
			// TODO Auto-generated catch block
		}
   
		
		
    	


    }

    
  
  

}
