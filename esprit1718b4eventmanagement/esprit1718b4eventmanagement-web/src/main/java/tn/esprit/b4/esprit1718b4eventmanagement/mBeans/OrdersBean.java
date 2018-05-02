package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.criteria.Order;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

@ManagedBean
@ViewScoped
public class OrdersBean implements Serializable {
	private int idOrder;
	private long reference;
	private String status;
	private Date deliveryDate;
	private Date orderDate;
	private Client client;
	
	private OrdredItemPk idPk;
	private int code;
	private int quantity;
	private String statusOrdredItem;
	private Article article;
	private Order order;
	
	private OrdredItem selectedOrdredItem;
	private List<Order> ListOrders;
	private List<OrdredItem> ListOrdredItem;
	private List<Client> Listclients;
	private List <Article> produitFini;
	
	@EJB
	ClientServiceLocal clientService;
	
	@EJB
	ArticleServiceLocal articleService;
	 
	private static final long serialVersionUID = 3350653785168926842L;
    
    @PostConstruct
    public void init() throws NamingException {
    		
		}
    
    public void save(){
    	ListOrdredItem.add(selectedOrdredItem);
    }
    
    public void delete(Client client){
    	clientService.delete(client);
    }

	public int getIdOrder() {
		return idOrder;
	}

	public void setIdOrder(int idOrder) {
		this.idOrder = idOrder;
	}

	public long getReference() {
		return reference;
	}

	public void setReference(long reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public OrdredItemPk getIdPk() {
		return idPk;
	}

	public void setIdPk(OrdredItemPk idPk) {
		this.idPk = idPk;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatusOrdredItem() {
		return statusOrdredItem;
	}

	public void setStatusOrdredItem(String statusOrdredItem) {
		this.statusOrdredItem = statusOrdredItem;
	}

	public Article getArticle() {
		return article;
	}

	public void setArticle(Article article) {
		this.article = article;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Order> getListOrders() {
		return ListOrders;
	}

	public void setListOrders(List<Order> listOrders) {
		ListOrders = listOrders;
	}

	public List<Client> getListclients() {
		return Listclients = clientService.findAll();
	}

	public void setListclients(List<Client> listclients) {
		Listclients = listclients;
	}



	public List<OrdredItem> getListOrdredItem() {
		return ListOrdredItem;
	}



	public void setListOrdredItem(List<OrdredItem> listOrdredItem) {
		ListOrdredItem = listOrdredItem;
	}



	public OrdredItem getSelectedOrdredItem() {
		return selectedOrdredItem;
	}



	public void setSelectedOrdredItem(OrdredItem selectedOrdredItem) {
		this.selectedOrdredItem = selectedOrdredItem;
	}



	public List<Article> getProduitFini() {
		return produitFini = articleService.getArticlesByType("Produit-Fini");
	}



	public void setProduitFini(List<Article> produitFini) {
		this.produitFini = produitFini;
	}
    
    
 

}