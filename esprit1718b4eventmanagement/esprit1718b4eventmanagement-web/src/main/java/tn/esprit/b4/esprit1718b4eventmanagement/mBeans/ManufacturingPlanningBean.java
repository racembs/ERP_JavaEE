package tn.esprit.b4.esprit1718b4eventmanagement.mBeans;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.component.FacesComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.Column;
import javax.persistence.NoResultException;
import javax.persistence.criteria.Order;

import org.primefaces.context.RequestContext;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

import com.itextpdf.text.log.Logger;

import javafx.scene.control.TreeItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote;
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
	
	private List<String> stakingMethod = new ArrayList<>();
	private String choosedMethod;
	
	private List<String> shiftWorkNumber = new ArrayList<>();
	private String choosedShiftWorkNumber;
	
	private Date startingDate;
	
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
	
	@EJB
	NeedNomenclatureServiceLocal needNomenclatureService;
	
	@EJB
	ManufacturingPlanningServiceLocal manufacturingPlanningService;
	
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
    
    public int val(String choice){
    	if(choice.equals("1: from 7h to 15h")){
    		return 1;
    	} else if(choice.equals("2: from 7h to 23h")){
    		return 2;
    	} else {
    		return 3;
    	}
    }
    
    public void makePlanningAction(){
    	Map<NeededItem, List<NeededItem>> map ;
    	if(choosedMethod==null && choosedShiftWorkNumber==null){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "you have to select a method and a shift work number");
    		RequestContext.getCurrentInstance().showMessageInDialog(message);
    	} else if(choosedMethod.equals("earlier staking") && (startingDate==null || startingDate.before(new Date()))){
    		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "you have to select a valid starting date");
    		RequestContext.getCurrentInstance().showMessageInDialog(message);
    	} else {
    		
    		NeededItem Parent = new NeededItem();
			Parent.setActionNature("Manufacturing Order");
			Parent.setOrderItem(selectedOrdredItem);
			Parent.setNeeded_article(selectedOrdredItem.getArticle());
			Parent.setGrossNeed(Parent.getOrderItem().getQuantity());
			if(Parent.getGrossNeed()-(Parent.getNeeded_article().getQuantity()-Parent.getNeeded_article().getReservedQuantity())>0){
				Parent.setNetNeed(Parent.getGrossNeed()-(Parent.getNeeded_article().getQuantity()-Parent.getNeeded_article().getReservedQuantity()));
			} else {
				Parent.setNetNeed(0);
			}
			Parent.setStatus("Pending");
			if(Parent.getNetNeed()==0){
				manufacturingPlanningService.updateIfOneNeededItem(Parent);
			} else {
				if(choosedMethod.equals("earlier staking")){
					//Earlier Staking Scheduling
					map = neededItemService.InitialiseASCMap();
					map = neededItemService.CreateNeedItemTree(Parent);
					map = neededItemService.SaveNeedItemTree(map);
        			map=neededItemService.SetPurchaseDeliveryDate(map);
            		Set<NeedNomenclature> needNomenclatureList = needNomenclatureService.SaveNeedItemTreeNomenclature(map);
					Calendar calendar = Calendar.getInstance();
            		calendar.setTime(startingDate);
            		calendar.setTimeInMillis(calendar.getTimeInMillis()+25200000);
            		manufacturingPlanningService.ReadyManufacturingPlanning(map, calendar.getTime(),val(choosedShiftWorkNumber));
            		Map<NeededItem, List<NeededItem>> map2 = neededItemService.findNeededItemTreeByOrdredItem(neededItemService.find(Parent.getId()));
            		List<ManufacturingPlanning> llisst =manufacturingPlanningService.AfterDeliveryManufacturingPlanning(map2,val(choosedShiftWorkNumber));
				} else {
					//Later Staking Scheduling
					
				}
			}
    		
    		RequestContext.getCurrentInstance().execute("PF('ItemDialog').hide()");
    	} 
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
	public List<String> getStakingMethod() {
		stakingMethod.add("earlier staking");
		stakingMethod.add("later staking");
		return stakingMethod ;
	}
	public void setStakingMethod(List<String> stakingMethod) {
		this.stakingMethod = stakingMethod;
	}
	public List<String> getShiftWorkNumber() {
		shiftWorkNumber.add("1: from 7h to 15h");
		shiftWorkNumber.add("2: from 7h to 23h");
		shiftWorkNumber.add("3: 24/24h");
		return shiftWorkNumber;
	}
	public void setShiftWorkNumber(List<String> shiftWorkNumber) {
		this.shiftWorkNumber = shiftWorkNumber;
	}
	public Date getStartingDate() {
		return startingDate;
	}
	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}
	public String getChoosedMethod() {
		return choosedMethod;
	}
	public void setChoosedMethod(String choosedMethod) {
		this.choosedMethod = choosedMethod;
	}
	public String getChoosedShiftWorkNumber() {
		return choosedShiftWorkNumber;
	}
	public void setChoosedShiftWorkNumber(String choosedShiftWorkNumber) {
		this.choosedShiftWorkNumber = choosedShiftWorkNumber;
	}
//	public void givenDate(ValueChangeEvent ev) {
//		  setStartingDate((Date) ev.getNewValue());
//		  // prevent setter being called again during update-model phase
//		  ((UIInput) ev.getComponent()).setLocalValueSet(false);
//		}
//in the view on the balise we add: <	immediate="true" valueChangeListener="#{manufacturingPlanningBean.givenDate}"

	
 

}