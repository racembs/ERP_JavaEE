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
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

@ManagedBean
@ViewScoped
public class ManufacturingPlanningBean implements Serializable {
	
	private List<Client> ListClients;
	private int selectedClientId;
	
	private List<Orders> ListOrders;
	private int selectedOrderId;
	
	private List<OrdredItem> ListOrdredItem;
	private int selectedArticleId;
	
	private OrdredItem selectedOrdredItem;
	
	@EJB
	ClientServiceLocal clientService;
	
	@EJB
	ArticleServiceLocal articleService;
	
	@EJB
	OrderItemServiceLocal ordredItemService;
	
	@EJB
	OrdersServiceLocal ordersService;
	
	@EJB
	NeededItemServiceLocal neededItemService;
	
	private static java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger("MyLogging");
	 
	private static final long serialVersionUID = 3350653785168926842L;
    
    @PostConstruct
    public void init() throws NamingException {
    	
		}
    public void comboClientAction(){
    	ListOrders = ordersService.findOrdersByClient(selectedClientId);
    }
    
    public void comboOrderAction(){
    	ListOrdredItem = ordredItemService.findPendingItemsOfAnOrder(selectedOrderId);
    }
    
    public void selectOrdredItemChoosed(){
    	selectedOrdredItem = ordredItemService.findOrdredItemById(selectedOrderId, selectedArticleId);
    }

	public List<Client> getListClients() {
		return ListClients = clientService.findAll();
	}

	public void setListClients(List<Client> listClients) {
		ListClients = listClients;
	}

	public int getSelectedClientId() {
		return selectedClientId;
	}

	public void setSelectedClientId(int selectedClientId) {
		this.selectedClientId = selectedClientId;
	}

	public List<Orders> getListOrders() {
		return ListOrders;
	}

	public void setListOrders(List<Orders> listOrders) {
		ListOrders = ordersService.findOrdersByClient(selectedClientId);
	}

	public int getSelectedOrderId() {
		return selectedOrderId;
	}

	public void setSelectedOrderId(int selectedOrderId) {
		this.selectedOrderId = selectedOrderId;
	}

	public List<OrdredItem> getListOrdredItem() {
		return ListOrdredItem ;
	}

	public void setListOrdredItem(List<OrdredItem> listOrdredItem) {
		ListOrdredItem = listOrdredItem;
	}

	public int getSelectedArticleId() {
		return selectedArticleId;
	}

	public void setSelectedArticleId(int selectedArticleId) {
		this.selectedArticleId = selectedArticleId;
	}

	public OrdredItem getSelectedOrdredItem() {
		return selectedOrdredItem ;
	}

	public void setSelectedOrdredItem(OrdredItem selectedOrdredItem) {
		this.selectedOrdredItem = selectedOrdredItem;
	}
    
    


	
 

}