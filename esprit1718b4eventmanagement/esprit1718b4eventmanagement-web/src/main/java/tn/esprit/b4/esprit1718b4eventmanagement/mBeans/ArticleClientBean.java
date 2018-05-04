package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;

@FacesComponent("ArticleClientBean")
@ManagedBean(name="ArticleClientBean")
@SessionScoped
public class ArticleClientBean implements Serializable {
	
	private static final long serialVersionUID = 3350653785168926842L;
	private String articleCode;
	private String description;
	
    private static int idA;
    private static int idO;
	private float pmp;
	private int quantity;
	private int qte =0;
	private List<Article> Articles;
	private List<Orders>Ordres;
	
	@EJB
	ArticleService ArticleServices;
	
	@EJB
	OrderItemService OrderItemServices;
	
	@EJB
	OrdersService OrdersService;
	 
	    @PostConstruct
	    public void init() throws NamingException {
	    }











	public int getQte() {
		return qte;
	}


	public void setQte(int qte) {
		this.qte = qte;
	}


	
	


	
		public String getDescription() {
			return description;
		}

		public void setDescription(String description) {
			this.description = description;
		}

		public int getQuantity() {
			return quantity;
		}

		public void setQuantity(int quantity) {
			this.quantity = quantity;
		}

		public List<Article> getArticles() {
			List<Article>listA =ArticleServices.getArticlesByType("Produit-Fini");
			return listA;
		}

		public void setArticles(List<Article> Articles) {
			this.Articles = Articles;
		}


		public String getArticleCode() {
			return articleCode;
		}


		public void setArticleCode(String articleCode) {
			this.articleCode = articleCode;
		}


		public float getPmp() {
			return pmp;
		}


		public void setPmp(float pmp) {
			this.pmp = pmp;
		}
	
	    public List<String> findArticle(String query){
	        List<Article> list=ArticleServices.getArticlesByType("Produit-Fini");
	        List<String> ListArt =new ArrayList<>();
	        for(int i=0;i<list.size();i++){
	        	if(list.get(i).getArticleCode().toUpperCase().contains(query)||list.get(i).getArticleCode().toLowerCase().contains(query)){
	        		ListArt.add(list.get(i).getArticleCode());
	        	}
	        
	        }
	        return ListArt;
	        }
	    
	    
		public void Confirm()
		{
			
			//ArticleServices.DeleteArticle(41);
			
			Article article = new Article();
			article.setArticleCode("azertyu");
			 article =ArticleServices.findArticle(19);
			 
			 Orders order = new Orders();
	
			 order.setId(50);
			OrdredItem newOrdredItem = new OrdredItem();
			newOrdredItem.setQuantity(qte);
			Float x = (float) 3.5;
			List<Article> listA =ArticleServices.findArticleByCode(articleCode);
			for (int i = 0; i < listA.size(); i++) {
				idA=listA.get(i).getId();
			}
			
			OrderItemServices.addOrdredItem(1, idA, newOrdredItem);

		}

	    

}
