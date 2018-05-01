package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;

import javax.naming.NamingException;
import javax.persistence.Column;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.MvtApprovService;

@FacesComponent("procurementBean")
@ManagedBean(name="procurementBean")
@SessionScoped
@ApplicationScoped
public class ProcurementBean implements Serializable {
	private Integer id ; 

	private Article article;
	private SpareParts spareparts;
	private int quantity;
	private Date alarmDate;
	private Date requestDate;
	private Date receptionDate;
	private List<Article> articles;
	private String articleCode;
	private int dailyConsumption;
	private int deliveryTime;
	private List<MvtApprov> orders;
	
	 
	@EJB
	ArticleService articleService;
	 @EJB
	 MvtApprovService approvService;
	private static final long serialVersionUID = 3350653785168926842L;
	
    
    @PostConstruct
    public void init() throws NamingException {
    
		 }

public void addOrder() {
	approvService.addMvtApprov(new MvtApprov(article, null, quantity,null, null,null));
}

public void updateArticleParamaters() {
	article.setDailyConsumption(dailyConsumption);
	article.setDeliveryTime(deliveryTime);
	articleService.updateArticle(article);
}
public void reset() {
	this.quantity=10;
	this.receptionDate=null;
}

public void confirmReception(int orderId) {
	java.util.Date Date=new java.util.Date();
	MvtApprov order=approvService.findMvtApprovById(orderId);
	order.setReceptionDate(Date);
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
		 List<Article>list =articleService.getArticlesByType("Matiére-Premiére");
			
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
	
	
	
}