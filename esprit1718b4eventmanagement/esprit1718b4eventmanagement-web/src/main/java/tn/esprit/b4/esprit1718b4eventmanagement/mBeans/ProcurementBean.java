package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.sql.Date;


import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;

import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
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
	 @EJB
	ArticleService articleService;
	 @EJB
	 MvtApprovService approvService;
	


	public ArticleService getArticleService() {
		return articleService;
	}


	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}


	private static final long serialVersionUID = 3350653785168926842L;
	
    
    @PostConstruct
    public void init() throws NamingException {
    
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



}