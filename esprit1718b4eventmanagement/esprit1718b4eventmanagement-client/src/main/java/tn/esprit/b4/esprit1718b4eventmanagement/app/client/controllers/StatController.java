
package tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers;


import java.net.URL;
import java.sql.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.ScatterChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;

/**
 * FXML Controller class
 *
 * @author ons
 */
public class  StatController implements Initializable {

    @FXML
    private Label txtCurrentWindow;
    @FXML
    private Separator id1;
    @FXML
    private Label id2;
    @FXML
    private Label OptRStat;
    @FXML
    private PieChart idPc;
    @FXML
    private BarChart<?, ?> idBC;
    @FXML
    private NumberAxis idBCy;
    @FXML
    private CategoryAxis idBCx;
    @FXML
    private AreaChart<?, ?> idAC;
    @FXML
    private NumberAxis idACy;
    @FXML
    private CategoryAxis idACx;
    @FXML
    private ScatterChart<?, ?> idSC;
    @FXML
    private NumberAxis idSCy;
    @FXML
    private CategoryAxis idSCx;
    @FXML
    private Label idA;
    @FXML
    private Label idE;
    @FXML
    private Label idCS;
    @FXML
    private Label idO;
    @FXML
    private Label idMP;
    @FXML
    private ImageView back;
    @FXML
    private ImageView idStat;
    @FXML
    private ComboBox<String> idComboStat;
    @FXML
    private DatePicker idStart;
    @FXML
    private DatePicker idEnd;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    	
    	back.setOnMouseClicked((MouseEvent a) -> { 
			  Parent parent= null;
			  	try {
	  				parent  =FXMLLoader.load(getClass().getResource("/views/ChargingStationMenu.fxml"));
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
    	 
    	
    	idStat.setOnMouseClicked((MouseEvent a) -> { 
    	 	idBC.getData().clear(); 
			  Parent parent= null;
			  	try {
			  	Date date1=java.sql.Date.valueOf(idStart.getValue());
			  	Date date2=java.sql.Date.valueOf(idEnd.getValue());
			  	System.out.println(date1);
			  	System.out.println(date2);

			  	
			  	Alert alert = new Alert(Alert.AlertType.WARNING);
			  	if(date2.compareTo(date1)<=0){
			  	    	
			  	    		 alert.setTitle("Wrong Date");
			  	             alert.setHeaderText("Request Date is wrong");
			  	            alert.showAndWait();
			  	    	 }

		       	String jndiNameManufacturingPlan1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
	        	Context context1;
	        	context1 = new InitialContext();
	        	ManufacturingPlanningServiceRemote proxyManufacturing1=(ManufacturingPlanningServiceRemote) context1.lookup(jndiNameManufacturingPlan1);
	        	List<ManufacturingPlanning> manufactList =proxyManufacturing1.DisplayManufacturingPlanning();
	        	
	        	Set<ManufacturingPlanning> set = new HashSet<>(manufactList);
	        	
	        	for (ManufacturingPlanning manufacturingPlanning : set) {
	        		String ch1="";
	        		Integer x=0;
	        		if((manufacturingPlanning.getStartingDate().compareTo(date1)>=0)&&(manufacturingPlanning.getEndingDate().compareTo(date2)<=0))
	        		{
	        			System.out.println(manufacturingPlanning.getQuantity());
						Integer qt=manufacturingPlanning.getQuantity();
						
						//System.out.println(manufacturingPlanning.getNeededItem().getNeeded_article().getOperatingranges().size());
						String ch=manufacturingPlanning.getNeededItem().getNeeded_article().getArticleCode();
						List <OperatingRange> Listopt = manufacturingPlanning.getNeededItem().getNeeded_article().getOperatingranges();
						Set<OperatingRange> set1 = new HashSet<>(Listopt);
						for(OperatingRange operatingrange : set1){
							System.out.println(operatingrange.getDesignation());
							
							List <Operation> Listop = operatingrange.getOperations();
							Set<Operation> set2 = new HashSet<>(Listop);
							for(Operation operation : set2){
								System.out.println(operation.getUnitproductiontime());
								
								
							Integer UPT=operation.getUnitproductiontime();
							
							
							x= x+(qt/UPT);
					  
//							XYChart.Series series2 = new XYChart.Series<>();
//					        series2.getData().add(new XYChart.Data(ch1,x));
//					        idBC.getData().add(series2);
//					        idBC.setTitle("Manufacturing Time By Article");
							}
						}
						
						ch1= manufacturingPlanning.getNeededItem().getNeeded_article().getArticleCode();
				      
						XYChart.Series series2 = new XYChart.Series<>();
				        series2.getData().add(new XYChart.Data(ch1,x));
				        idBC.getData().add(series2);
				        idBC.setTitle("Manufacturing Time By Article");
				       
	        		}
	        		
	        	}
	        	

	        	
			  	} catch (Exception e1) {
	  				// TODO Auto-generated catch block
	  				e1.printStackTrace();
	  			}
			 	
		  });
//        OptRStat.setText("20");
//        idA.setText("1");
//        idE.setText("1");
//        idCS.setText("5");
//        idO.setText("11");
//        idMP.setText("2");
        
        
        
			Context contextEquipment;
			try {
				contextEquipment = new InitialContext();
				EquipementServiceRemote proxyEquipment = (EquipementServiceRemote) contextEquipment.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote");
		    	Equipment equipment = new Equipment();
		    	List<Equipment> listE;// = proxyEquipment.DisplayEquipment();
		    	listE = proxyEquipment.DisplayEquipment();
		        ObservableList<Equipment> obListeq = FXCollections.observableArrayList(listE);
		        int e =listE.size();
		        String size1=String.valueOf(e);
		        idE.setText(size1);
	            
	    		String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
				Context contextChargingStation;
				contextChargingStation = new InitialContext();
				ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
				ChargingStation ch = new ChargingStation();
				List<ChargingStation> list = proxyChargingStation.DisplayChargingStation();
			    ObservableList<ChargingStation> items = FXCollections.observableArrayList(list);
			    int c =list.size();
		        String size2=String.valueOf(c);
			    idCS.setText(size2);
	
			    
		 
		        
			    
		        
				String jndiNameOperation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
    			Context contextOperation;
				contextOperation = new InitialContext();
    			OperationServiceRemote proxyOperation = (OperationServiceRemote) contextOperation.lookup(jndiNameOperation);
    		    Operation operation = new Operation();
    		    List<Operation> list13 = proxyOperation.DisplayOperation();
    		    
			    ObservableList<Operation> items13 = FXCollections.observableArrayList(list13);
			    int o =list13.size();
		        String size4=String.valueOf(o);
		        idO.setText(size4);
		       
   				String ArticlejndiName1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		    	Context contextArticle1;
                contextArticle1 = new InitialContext();
		        ArticleServiceRemote ArticleProxy1 = (ArticleServiceRemote) contextArticle1.lookup(ArticlejndiName1);
    	        Article article = new Article();
	            List<Article> listA1 = ArticleProxy1.DisplayArticle();
	            ObservableList<Article> obListA1 = FXCollections.observableList(listA1);
	            int a =listA1.size();
		        String size5=String.valueOf(a);
		        idA.setText(size5);
		        
	        	String jndiNameManufacturingPlan = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
	        	Context context5;
	        	context5 = new InitialContext();
	        	ManufacturingPlanningServiceRemote proxyManufacturing=(ManufacturingPlanningServiceRemote) context5.lookup(jndiNameManufacturingPlan);
	        	List<ManufacturingPlanning> manufactList =proxyManufacturing.DisplayManufacturingPlanning();
	        	 ObservableList<ManufacturingPlanning> obListMan = FXCollections.observableList(manufactList);
	        	  int m =manufactList.size();
			      String size6=String.valueOf(m);
			      idMP.setText(size6);
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
    
        idComboStat.getItems().addAll("Consecutive","Overlap","With staking delay","Parallel");
        idComboStat.getSelectionModel().selectLast();
        
        Integer sf=0;
        Integer f=0;
        Integer mp=0;
		
        try {
        	String ArticlejndiName11 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
        	Context contextArticle11;
			contextArticle11 = new InitialContext();
			 ArticleServiceRemote ArticleProxy11 = (ArticleServiceRemote) contextArticle11.lookup(ArticlejndiName11);
		        Article article = new Article();
		        List<Article> listA1 = ArticleProxy11.DisplayArticle();
		       // ObservableList<Article> obListA1 = FXCollections.observableList(listA1);
		        
		        XYChart.Series series = new XYChart.Series();
		        series.setName("Quantity");
		        XYChart.Series series1 = new XYChart.Series();
		        series1.setName("ReservedQuantity");
		        XYChart.Series series2 = new XYChart.Series();
		        series2.setName("PricipalQuantity");
		        for (int i = 0; i < listA1.size(); i++) {
//		        	String ch=listA1.get(i).getArticleCode();
//		        	Integer x=listA1.get(i).getQuantity();
		        	 series.getData().add(new XYChart.Data(listA1.get(i).getArticleCode(),listA1.get(i).getQuantity()));
		        	 series1.getData().add(new XYChart.Data(listA1.get(i).getArticleCode(),listA1.get(i).getReservedQuantity()));
		        	 series2.getData().add(new XYChart.Data(listA1.get(i).getArticleCode(),listA1.get(i).getPricipalQuantity()));
				
		        	   idSC.getData().addAll(series,series1,series2);
				       idSC.setTitle("Scatter Chart");	 
		
		        if(listA1.get(i).getType().equals("Produit-Semi-Fini"))
		        {
		        	sf++;
		        	
		        }
		        if(listA1.get(i).getType().equals("Produit-Fini"))
		        {
		        	f++;
		        	
		        }
		        if(listA1.get(i).getType().equals("Matiére-Premiére"))
		        {
		        	mp++;
		        	
		        	
		        }
		        
	
		        }
		        Integer sf1=sf;
		        Integer f1=f;
		        Integer mp1=mp;
		        System.out.print("onssss"+sf1+""+f1+""+mp1);
		        ObservableList<PieChart.Data> pieChartData= FXCollections.observableArrayList(
		                new PieChart.Data("SemiFinishedProduct",sf1),
		                new PieChart.Data("FinishedProduct",f1),
		                new PieChart.Data("Stock",mp1)
		        );
		        
		        idPc.setData(pieChartData);
		        idPc.setTitle("Pie Chart");
//		        XYChart.Series series6 = new XYChart.Series();
//		        series6.getData().add(new XYChart.Data
//		        series6.getData().add(new XYChart.Data
//		        series6.getData().add(new XYChart.Data
	
		        
		        
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
        
        
//        series.getData().add(new XYChart.Data("0",20));
//        series.getData().add(new XYChart.Data("1",21));
//        series.getData().add(new XYChart.Data("2",22));
//        series.getData().add(new XYChart.Data("3",23));
//        series.getData().add(new XYChart.Data("4",24));
//        series.getData().add(new XYChart.Data("5",25));
        
//        XYChart.Series series1 = new XYChart.Series();
//        series1.getData().add(new XYChart.Data("0",30));
//        series1.getData().add(new XYChart.Data("1",25));
//        series1.getData().add(new XYChart.Data("2",17));
//        series1.getData().add(new XYChart.Data("3",31));
//        series1.getData().add(new XYChart.Data("4",15));
//        series1.getData().add(new XYChart.Data("5",33));
        
//        XYChart.Series series2 = new XYChart.Series<>();
//        series2.getData().add(new XYChart.Data("A",3000));
//        series2.getData().add(new XYChart.Data("B",2500));
//        series2.getData().add(new XYChart.Data("C",1700));
//        series2.getData().add(new XYChart.Data("D",3100));
//        series2.getData().add(new XYChart.Data("E",1500));
//        series2.getData().add(new XYChart.Data("F",3300));
         
         
        

        
//        idAC.getData().addAll(series, series1);
//        idAC.setTitle("Area Chart");
        

         
//        idBC.getData().add(series2);
//        idBC.setTitle("Bar Chart");
    }    
    public void Change(ActionEvent event)
    {
	    
			
			try {
				String OperatingRangejndiName42 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";
				Context context42;
				context42 = new InitialContext();
				OperatingRangeServiceRemote proxy42 =  (OperatingRangeServiceRemote) context42.lookup(OperatingRangejndiName42);
				OperatingRange optrange = new OperatingRange();
				//List<OperatingRange> list12 = proxy42.DisplayOperatingRange();
				
			    //ObservableList<OperatingRange> items12 = FXCollections.observableArrayList(list12);
		
		        
		        
	        if(idComboStat.getValue()=="Consecutive")
	        {
	        	System.out.println("Consecutive");
	        	List<OperatingRange> list14 = proxy42.Display("Consecutive");
	    	    int opt =list14.size();
		        String size3=String.valueOf(opt);
		        OptRStat.setText(size3);
	        }
	        if(idComboStat.getValue()=="Parallel")
	        {
	        	System.out.println("Parallel");
	        	List<OperatingRange> list14 = proxy42.Display("Parallel");
	    	    int opt =list14.size();
		        String size3=String.valueOf(opt);
		        OptRStat.setText(size3);
	        }
	        if(idComboStat.getValue()=="With staking delay")
	        {
	        	System.out.println("With staking delay");
	        	List<OperatingRange> list14 = proxy42.Display("With staking delay");
	    	    int opt =list14.size();
		        String size3=String.valueOf(opt);
		        OptRStat.setText(size3);
	        }
	        if(idComboStat.getValue()=="Overlap")
	        {
	        	System.out.println("Overlap");
	        	List<OperatingRange> list14 = proxy42.Display("Overlap");
	    	    int opt =list14.size();
		        String size3=String.valueOf(opt);
		        OptRStat.setText(size3);
	        }
				
			} catch (NamingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
    }
    
    
}
