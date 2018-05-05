package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.FacesComponent;
import javax.naming.NamingException;
import javax.persistence.criteria.Order;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemService;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;


@FacesComponent("OrderClientBean")
@ManagedBean(name="OrderClientBean")
@SessionScoped
public class OrderClientBean implements Serializable {
	private Date orderDate;
	private Client client;
	
	private OrdredItemPk idPk;
	private int code;
	private int quantity;
	private String statusOrdredItem;
	private Article article;
	private Orders order;
	
	private OrdredItem selectedOrdredItem;
	private List<Orders> ListOrders;
	private List<OrdredItem> ListOrdredItem;
	private List<Client> Listclients;
	private List <Article> produitFini;
	
	@EJB
	ClientServiceLocal clientService;
	@EJB
	OrdersService orderService;
	@EJB
	OrderItemService OrderItemServices;
	@EJB
	ArticleServiceLocal articleService;
	 
	private static final long serialVersionUID = 3350653785168926842L;
	
    @PostConstruct
    public void init() throws NamingException {
    	
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



	public OrdredItem getSelectedOrdredItem() {
		return selectedOrdredItem;
	}

	public void setSelectedOrdredItem(OrdredItem selectedOrdredItem) {
		this.selectedOrdredItem = selectedOrdredItem;
	}

	public List<Orders> getListOrders() {
		List<Orders>ListOrders=orderService.findAll();
		return ListOrders;
	}

	public void setListOrders(List<Orders> listOrders) {
		ListOrders = listOrders;
	}

	public List<OrdredItem> getListOrdredItem() {
		List<OrdredItem>ListOrders=OrderItemServices.findAll();
		return ListOrdredItem;
	}

	public void setListOrdredItem(List<OrdredItem> listOrdredItem) {
		ListOrdredItem = listOrdredItem;
	}

	public List<Client> getListclients() {
		return Listclients;
	}

	public void setListclients(List<Client> listclients) {
		Listclients = listclients;
	}

	public List<Article> getProduitFini() {
		return produitFini;
	}

	public void setProduitFini(List<Article> produitFini) {
		this.produitFini = produitFini;
	}
    
    
    
}
