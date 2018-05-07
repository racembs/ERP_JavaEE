package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
 
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;
import org.primefaces.model.chart.PieChartModel;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ReclamationService;


@ManagedBean
public class ChartView implements Serializable {
	

	private List<Article> Articles;
	private List<Reclamation> Reclamations;
	private BarChartModel barModel;
	private PieChartModel pieModel1;
	@EJB
	ArticleService ArticleServices;
	@EJB
	ReclamationService ReclamationServices;
    @PostConstruct
    public void init() {
    	createBarModels();
    	createPieModels();
    }

    public BarChartModel getBarModel() {
        return barModel;
    }
    

    private void createBarModels() {
        createBarModel();
    }
     
    private void createBarModel() {
        barModel = initBarModel();
         
        barModel.setTitle("Demand / Offer");
        barModel.setLegendPosition("ne");
         
        Axis xAxis = barModel.getAxis(AxisType.X);
        xAxis.setLabel("Article");
         
        Axis yAxis = barModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(200);
    }

    private BarChartModel initBarModel() {
        BarChartModel model = new BarChartModel();
 
     	List<Article> list=sortOrderList();

     	ChartSeries maxChart = new ChartSeries();
      
        maxChart.setLabel("Quantity");
 for(int i=0 ;i<list.size();i++) {
	 maxChart.set(list.get(i).getArticleCode()+
			 " ("+String.valueOf((list.get(i).getQuantity())-(list.get(i).getReservedQuantity()))+") "
			 , list.get(i).getQuantity());
        
        
 }

	 ChartSeries minChart = new ChartSeries();

     
     minChart.setLabel("Reserved Quantity");
     for(int j=0 ;j<list.size();j++) {
     	minChart.set(list.get(j).getArticleCode()+
    			 " ("+String.valueOf((list.get(j).getQuantity())-(list.get(j).getReservedQuantity()))+") "
    			 , list.get(j).getReservedQuantity());
       

 

    }
     model.addSeries(maxChart);
     model.addSeries(minChart);
      
     return model;
}
    
    public List<Article> sortOrderList() {
    	
    	List <Article> ListA=ArticleServices.getArticlesByType("Produit-Fini");
    
    	return ListA;
		
    }

    
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
     
  
    private void createPieModels() {
        createPieModel1();
    }  
    
    private void createPieModel1() {
        pieModel1 = new PieChartModel();
         
 
        
     	List<Reclamation> list=ReclamationServices.findAll();

      
    
 for(int i=0 ;i<list.size();i++) {
	 pieModel1.set(list.get(i).getSubject() , ReclamationServices.findReclamation(list.get(i).getSubject()));
 }     
//        pieModel1.set("Brand 1", 540);
//        pieModel1.set("Brand 2", 325);
//        pieModel1.set("Brand 3", 702);
//        pieModel1.set("Brand 4", 421);
         
        pieModel1.setTitle("Reclamation");
        pieModel1.setLegendPosition("w");
    }
}