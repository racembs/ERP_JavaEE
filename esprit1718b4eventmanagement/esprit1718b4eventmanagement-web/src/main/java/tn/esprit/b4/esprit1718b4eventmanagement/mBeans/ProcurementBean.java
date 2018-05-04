package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;

import javax.naming.NamingException;
import javax.persistence.Column;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.CategoryAxis;
import org.primefaces.model.chart.LineChartModel;
import org.primefaces.model.chart.LineChartSeries;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovService;

@FacesComponent("procurementBean")
@ManagedBean(name="procurementBean")
@SessionScoped
public class ProcurementBean implements Serializable {
	private Integer id ; 

	private Article article;
	private SpareParts spareparts;
	private int quantity;
	private Date alarmDate;
	private Date requestDate;
	private Date receptionDate;
	private int minQuantity;
	private int maxQuantity;
	private List<Article> articles;
	private String articleCode;
	private int dailyConsumption;
	private int deliveryTime;
	private List<MvtApprov> orders;

    private LineChartModel areaModel;

	
	 
	@EJB
	ArticleService articleService;
	@EJB
	MvtApprovService approvService;
	private static final long serialVersionUID = 3350653785168926842L;
	
    
    @PostConstruct
    public void init() throws NamingException {
    	AutoOrderGenerateByMinimumQuantity();
    	createAreaModel();
    	
		 }

    public List<MvtApprov> sortOrderList() {
    	
    	List <MvtApprov> ordersList=approvService.getAllOrders();
    	
    	for(int i=0;i<ordersList.size()-1;i++) {

      			for(int j=0;j<ordersList.size();j++) {
      				
    				if(i!=j&&ordersList.get(i).getArticle().getArticleCode()
    				.equals(ordersList.get(j).getArticle().getArticleCode())) {
    					
    					ordersList.remove(ordersList.get(j));
    					
    				}
    			}
    			
    		
    					
    	}
    	
    	return ordersList;
		
    }
    
    
    
    
    private void createAreaModel() {
    	List<MvtApprov> list=sortOrderList();
        areaModel = new LineChartModel();

        LineChartSeries minChart = new LineChartSeries();
        minChart.setFill(true);
        minChart.setLabel("Min");
 for(int i=0 ;i<list.size();i++) {
	 minChart.set(list.get(i).getArticle().getArticleCode()+
			 " ("+String.valueOf(approvService.calculerArticleOrders(list.get(i).getArticle().getId()))+") "
			 , list.get(i).getArticle().getPricipalQuantity());
 }
       
        
 
        LineChartSeries maxChart = new LineChartSeries();
        maxChart.setFill(true);
        maxChart.setLabel("Normal");
        for(int i=0 ;i<list.size();i++) {
        	maxChart.set(list.get(i).getArticle().getArticleCode()+
       			 " ("+String.valueOf(approvService.calculerArticleOrders(list.get(i).getArticle().getId()))+") "
       			 , list.get(i).getArticle().getMinQuantity());
        }
        areaModel.addSeries(maxChart);
        areaModel.addSeries(minChart);
        
 
       // areaModel.setTitle("Orders Chart");
      //  areaModel.setLegendPosition("ne");
        areaModel.setStacked(true);
        areaModel.setShowPointLabels(true);
 
        Axis xAxis = new CategoryAxis("Article(Nbre Of oders)");
        areaModel.getAxes().put(AxisType.X, xAxis);
        Axis yAxis = areaModel.getAxis(AxisType.Y);
        yAxis.setLabel("Quantity");
        yAxis.setMin(0);
        yAxis.setMax(600);
    }
    
    public LineChartModel getAreaModel() {
        return areaModel;
    }
public void updateMinMaxQuantity() {
	Article article=articleService.getArticleListByCode(articleCode).get(0);
	article.setPricipalQuantity(maxQuantity);
	article.setMinQuantity(minQuantity);
	articleService.updateArticle(article);
	createAreaModel();
}

public void updateMinMaxQuantityForAlArticles() {
	List<Article> list =articleService.getAllArticles();
	 for(int i=0;i<list.size();i++) {
		 list.get(i).setMinQuantity(minQuantity);
		 list.get(i).setPricipalQuantity(maxQuantity);
		 articleService.updateArticle(list.get(i));
		}
	 createAreaModel();
}

public List<String> findArticles(String query){
	 List<Article> list =articleService.getArticlesByType("Matiére-Premiére");
	 List<String> stringList=new ArrayList<>();
	 for(int i=0;i<list.size();i++) {
		 if(list.get(i).getArticleCode().toUpperCase().contains(query)||list.get(i).getArticleCode().toLowerCase().contains(query)) {
			 stringList.add(list.get(i).getArticleCode());
		 }
		
		}
	 return stringList;
}
public void addOrder() {
	article=articleService.findArticleByCode(articleCode).get(0);
	approvService.addMvtApprov(new MvtApprov(article, null, quantity,null, null,receptionDate));
}

public void updateArticleParamaters() {
	article.setDailyConsumption(dailyConsumption);
	article.setDeliveryTime(deliveryTime);
	articleService.updateArticle(article);
}
public void reset() {
	this.quantity=0;
	this.receptionDate=null;
}
public void refresh() {
	createAreaModel();
}

public void confirmReception(int orderId) {
	java.util.Date Date=new java.util.Date();
	MvtApprov order=approvService.findMvtApprovById(orderId);
	order.setReceptionDate(Date);
	order.getArticle().setQuantity(order.getArticle().getQuantity()+order.getQuantity());
	order.getArticle().setEtatOrdre(0);
	articleService.updateArticle(order.getArticle());
	approvService.updateMvtApprov(order);
}
	public ArticleService getArticleService() {
		return articleService;
	}


	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Article getArticle() {
		return article;
	}


	public void setArticle(Article article) {
		this.article = article;
	}


	public SpareParts getSpareparts() {
		return spareparts;
	}


	public void setSpareparts(SpareParts spareparts) {
		this.spareparts = spareparts;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public Date getAlarmDate() {
		return alarmDate;
	}


	public void setAlarmDate(Date alarmDate) {
		this.alarmDate = alarmDate;
	}


	public Date getRequestDate() {
		return requestDate;
	}


	public void setRequestDate(Date requestDate) {
		this.requestDate = requestDate;
	}


	public Date getReceptionDate() {
		return receptionDate;
	}


	public void setReceptionDate(Date receptionDate) {
		this.receptionDate = receptionDate;
	}


	public MvtApprovService getApprovService() {
		return approvService;
	}


	public void setApprovService(MvtApprovService approvService) {
		this.approvService = approvService;
	}
	
	public List<Article> getArticles() {
		 //List<Article>list =articleService.getArticlesByType("Matiére-Premiére");
		 List<Article> list =articleService.findArticleByCode(articleCode);	
			return list;
	}


	public List<MvtApprov> getOrders() {
		List <MvtApprov> list =approvService.getAllOrders();
		return list;
	}

	public void setOrders(List<MvtApprov> orders) {
		this.orders = orders;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}



	public String getArticleCode() {
		return articleCode;
	}


	public void setArticleCode(String articleCode) {
		this.articleCode = articleCode;
	}

	public int getDailyConsumption() {
		return dailyConsumption;
	}

	public void setDailyConsumption(int dailyConsumption) {
		this.dailyConsumption = dailyConsumption;
	}

	public int getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(int deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	
	



public int getMinQuantity() {
		return minQuantity;
	}

	public void setMinQuantity(int minQuantity) {
		this.minQuantity = minQuantity;
	}

	public int getMaxQuantity() {
		return maxQuantity;
	}

	public void setMaxQuantity(int maxQuantity) {
		this.maxQuantity = maxQuantity;
	}

	
	
public void AutoOrderGenerateByMinimumQuantity() {
		
		List<Article> list = articleService.getArticlesByType("Matiére-Premiére");

		for(int i=0;i<list.size();i++) {
			
			if(list.get(i).getQuantity()<=list.get(i).getMinQuantity()&&list.get(i).getEtatOrdre()==0) {
				
				java.util.Date Date=new java.util.Date();
				java.util.Date alarmDate=new java.util.Date();
				
				alarmDate.setDate(alarmDate.getDate()+list.get(i).getDeliveryTime()+1);
				
				int needed=list.get(i).getPricipalQuantity()-list.get(i).getQuantity();

				approvService.addMvtApprov(new MvtApprov(list.get(i),null,needed,alarmDate,Date,null));
				list.get(i).setEtatOrdre(1);
				articleService.updateArticle(list.get(i));
			}
		}
	}
	
	
	
}