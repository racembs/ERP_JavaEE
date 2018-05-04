package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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
import javax.persistence.NoResultException;
import javax.persistence.criteria.Order;

import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.itextpdf.text.log.Logger;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceLocal;
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
	private int selectedArticleId;
	private int selectedClientId;
	private int selectedOrderId;
	private OrdredItemPk selectedOrdredItemPk = new OrdredItemPk(1,1) ;
	
	public int getSelectedArticleId() {
		return selectedArticleId;
	}

	private OrdredItemPk idPk;
	private int code;
	private int quantity;
	private String statusOrdredItem;
	private Article article = new Article();
	private Order order;
	
	private Orders selectedOrder;
	private OrdredItem selectedOrdredItem = new OrdredItem();
	private List<OrdredItem> selectedOrdredItemList ;
	private List<Orders> ListOrders;
	private List<OrdredItem> ListOrdredItem = new ArrayList<>();
	private List<Client> Listclients;
	private List <Article> produitFini = new ArrayList<>();
	private List<String> test = new ArrayList<>();
	
	@EJB
	ClientServiceLocal clientService;
	
	@EJB
	ArticleServiceLocal articleService;
	
	@EJB
	OrderItemServiceLocal ordredItemService;
	
	@EJB
	OrdersServiceLocal ordersService;
	
	private static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("MyLogging");
	 
	private static final long serialVersionUID = 3350653785168926842L;
    
    @PostConstruct
    public void init() throws NamingException {
    	
		}
    
    public void testDate(){
    	
    	System.out.println(clientService.findAll().get(0).getCompany());
    }
    
    public void save(){
    	
    	OrdredItem orItem = new OrdredItem();
    	Article articlef = articleService.findArticle(selectedArticleId);
    	orItem.setArticle(articlef);
    	orItem.setQuantity(quantity);
    	orItem.setStatus("Pending");
    	boolean etat = false;
    	for (OrdredItem order : ListOrdredItem) {
			if(order.getArticle().equals(articlef))
				etat=true;
		}
    	if(etat==false)
    		ListOrdredItem.add(orItem);
    	
    	
    }
    
    public void updateQuantity(){
    	OrdredItem odredItem = ordredItemService.findOrdredItemById(selectedOrdredItemPk.getId_Order(), selectedOrdredItemPk.getId_Article());
    	odredItem.setQuantity(quantity);
    	ordredItemService.update(odredItem);
    	
    }
    
    public void updateOrdredItemList(){
    	OrdredItem orItem = new OrdredItem();
    	Article articlef = articleService.findArticle(selectedArticleId);
    	orItem.setArticle(articlef);
    	orItem.setQuantity(quantity);
    	orItem.setCode((selectedOrderId*5+33)*19+(int) Math.random()*5);
    	orItem.setStatus("Pending");
    	boolean etat = false;
    	for (OrdredItem order : selectedOrdredItemList) {
			if(order.getArticle().equals(articlef))
				etat=true;
		}
    	if(etat==false){
    		selectedOrdredItemList.add(orItem);
        	ordredItemService.addOrdredItem(selectedOrderId, articlef.getId(), orItem);
    	}
    		
    }
    
    public void delete(OrdredItem ordredItem){
    	if(ListOrdredItem.remove(ordredItem))
    		LOGGER.info("delete sucess");
    	else
    		LOGGER.info("Failed to delete");
    }
    
    public void updateSelection(OrdredItem ordredItem){
    	ordredItemService.update(ordredItem);
    }
    
    public void deleteSelection(OrdredItem ordredItem){
    	ordredItemService.delete(ordredItem);
    }
    
    public void clear(){
    	deliveryDate=null;
    	selectedClientId=0;
    	ListOrdredItem.clear();
    }
    
    public void addOrder(){
    	Orders order = new Orders();
    	order.setDelivery_date(deliveryDate);
    	order.setOrder_date(new Date());
    	order.setStatut("Pending");
    	Client clientt = clientService.find(selectedClientId);
    	order.setClient(clientt);
    	ordersService.addOrders(order);
    	int id = order.getId();
    	order.setReference((id*17+47)*10);
    	ordersService.update(order);
    	int i=0;
		for (OrdredItem ordredItem : ListOrdredItem) {
			i++;
			OrdredItem newOrdredItem = new OrdredItem();
			newOrdredItem.setQuantity(ordredItem.getQuantity());
			newOrdredItem.setStatus("pending");
			newOrdredItem.setCode((id*5+33)*19+i);
			ordredItemService.addOrdredItem(order.getId(), ordredItem.getArticle().getId(), newOrdredItem);
		}
		clear();
    }
    
    public void updateOrder(){
    	Orders order = ordersService.find(selectedOrderId);
    	order.setDelivery_date(deliveryDate);
    	ordersService.update(order);
    }
    
    public void deleteOrder(Orders order){
    	ordersService.delete(order);
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

	public List<Orders> getListOrders() {
		return ListOrders = ordersService.findAll();
	}

	public void setListOrders(List<Orders> listOrders) {
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



	public List<OrdredItem> getSelectedOrdredItemList() {
		return selectedOrdredItemList = ordredItemService.findItemsOfAnOrder(selectedOrderId);
	}



	public void setSelectedOrdredItemList(List<OrdredItem> selectedOrdredItemList) {
		this.selectedOrdredItemList = selectedOrdredItemList;
	}



	public List<Article> getProduitFini() {
		return produitFini = articleService.getArticlesByType("Produit-Fini");
	}



	public void setProduitFini(List<Article> produitFini) {
		this.produitFini = produitFini;
	}
    
	public void setSelectedArticleId(int selectedArticleId) {
		this.selectedArticleId = selectedArticleId;
	}

	public int getSelectedClientId() {
		return selectedClientId;
	}

	public void setSelectedClientId(int selectedClientId) {
		this.selectedClientId = selectedClientId;
	}

	public Orders getSelectedOrder() {
		return selectedOrder;
	}

	public void setSelectedOrder(Orders selectedOrder) {
		this.selectedOrder = selectedOrder;
	}

	public int getSelectedOrderId() {
		return selectedOrderId;
	}

	public void setSelectedOrderId(int selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}

	public OrdredItemPk getSelectedOrdredItemPk() {
		return selectedOrdredItemPk;
	}

	public void setSelectedOrdredItemPk(OrdredItemPk selectedOrdredItemPk) {
		this.selectedOrdredItemPk = selectedOrdredItemPk;
	}

	public OrdredItem getSelectedOrdredItem() {
		try{
			selectedOrdredItem = ordredItemService.findOrdredItemById(selectedOrdredItemPk.getId_Order(), selectedOrdredItemPk.getId_Article());
		}catch(NoResultException e){
			
		}
		return selectedOrdredItem;
		
	}

	public void setSelectedOrdredItem(OrdredItem selectedOrdredItem) {
		this.selectedOrdredItem = selectedOrdredItem;
	}
 

}