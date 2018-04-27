package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.*;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.ServiceLocator;

public class TestManufacturing {

	public static void main(String[] args) throws NamingException, ParseException {
		String jndiNameClient = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ClientService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote";
		String jndiNameNomenclature = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedNomenclatureService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeedNomenclatureServiceRemote";
		String jndiNameNeededItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeededItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.NeededItemServiceRemote";
		String jndiNameManufacturingPlan = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
		String jndiNameOrdredItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrderItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote";
		String jndiNameOrders= "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrdersService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote";
		String jndiNameArticle = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		ServiceLocator s=ServiceLocator.getInstance(); 
		ClientServiceRemote proxyClientServiceRemote=(ClientServiceRemote) s.getProxy(jndiNameClient);
		NeedNomenclatureServiceRemote proxyNomenclature=(NeedNomenclatureServiceRemote) s.getProxy(jndiNameNomenclature);
		NeededItemServiceRemote proxyNeededItem=(NeededItemServiceRemote) s.getProxy(jndiNameNeededItem);
		ManufacturingPlanningServiceRemote proxyManufacturing=(ManufacturingPlanningServiceRemote) s.getProxy(jndiNameManufacturingPlan);
		OrderItemServiceRemote proxyOrdredItem=(OrderItemServiceRemote) s.getProxy(jndiNameOrdredItem);
		OrdersServiceRemote proxyOrders=(OrdersServiceRemote) s.getProxy(jndiNameOrders);
		ArticleServiceRemote proxyArticleServiceRemote=(ArticleServiceRemote) s.getProxy(jndiNameArticle);
		
//		Client client = new Client("1798","PSE",22827736);
//		client.setId(proxyClientServiceRemote.addClient(client));
		
//		Client client=proxyClientServiceRemote.find(1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
		Date orderDat = dateFormat.parse("18/03/2018");
		Date deliveryDat = dateFormat.parse("25/06/2018");
		Orders order = new Orders(1818,orderDat,deliveryDat,"en attente");
		Orders order2 = new Orders(1818,orderDat,deliveryDat,"en attente");
		
		Calendar cale = Calendar.getInstance();
		cale.set(2018, 05, 9, 9, 0);

		Date dt = cale.getTime();
		
		Date deliveryDate = proxyManufacturing.endingManufacturingDate(dt, 1020,2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd kk:mm");
		
//		order.setClient(client);
//		int idOrder = proxyOrders.addOrders(order);
//		int idOrder2 = proxyOrders.addOrders(order2);
		
		Article PF = new Article("ARM200", "Armoire", "unit", "Produit fini", 540, 10);
		//PF.setId(proxyArticleServiceRemote.addArticle(PF));
		
		OrdredItem ordredItem = new OrdredItem();
		ordredItem.setCode(4518);
		ordredItem.setQuantity(10);
		ordredItem.setStatus("Pending");
//		proxyOrdredItem.addOrdredItem(1, 1, ordredItem);
		
		OrdredItem orderItem2 = proxyOrdredItem.findOrdredItemById(2,33);
		System.out.println(orderItem2.getArticle().getType());
		System.out.println(orderItem2.getOrdredItemPk().getId_Article());
		
		
//		NeededItem searchMan2 = manufactProxy.addManufactChild(searchMan);
//		System.out.println(searchMan2.getSonsMO().get(0).getManufacturingOrderPk().getId_Article());

//		NeededItem Father = new NeededItem();
//		Father.setOrderItem(orderItem2);
//		Father.setNeeded_article(orderItem2.getArticle());
//		Father.setId(proxyNeededItem.addNeededItem(Father));
//		
//		NeededItem Child = new NeededItem();
//		Child.setOrderItem(Father.getOrderItem());
//		Child.setNeeded_article(orderItem2.getArticle());
//		Child.setId(proxyNeededItem.addNeededItem(Child));
//		
//		NeedNomenclature nomenclature = proxyNomenclature.addnomenclature(Father.getId(), Child.getId(), 10);
		
//		NeededItem Parent = new NeededItem();
//		Parent.setOrderItem(orderItem2);
//		Parent.setNeeded_article(orderItem2.getArticle());
//		Parent.setGrossNeed(orderItem2.getQuantity());
//		Parent.setNetNeed(Parent.getGrossNeed()-Parent.getNeeded_article().getQuantity());
//		//Parent.setReadyLotNumber();
//		Parent.setStatus("Pending");
		
		NeededItem Parent = new NeededItem();
		Parent.setActionNature("Manufacturing Order");
		Parent.setOrderItem(orderItem2);
		Parent.setNeeded_article(orderItem2.getArticle());
		Parent.setGrossNeed(Parent.getOrderItem().getQuantity());
		if(Parent.getGrossNeed()-(Parent.getNeeded_article().getQuantity()-Parent.getNeeded_article().getReservedQuantity())>0){
			Parent.setNetNeed(Parent.getGrossNeed()-(Parent.getNeeded_article().getQuantity()-Parent.getNeeded_article().getReservedQuantity()));
		} else {
			Parent.setNetNeed(0);
		}
		
		Parent.setStatus("Pending");
		
		Map<NeededItem, List<NeededItem>> map = new HashMap<>();
		List<NeedNomenclature> needNomenclatureList = new ArrayList<>();
//		map= proxyNeededItem.CreateNeedItemTree(Parent);
//		map=proxyNeededItem.SaveNeedItemTree(map);
		//map= proxyNeededItem.CreateANDSaveNeedItemTree(Parent);
//									NeededItem Parent=proxyNeededItem.find(1);
//									proxyNeededItem.InitialiseDESCMap();
//									map = proxyNeededItem.findNeededItemTreeByOrdredItem(Parent);
			//map=proxyNeededItem.findNeededItemTreeByOrdredItem(Parent);
//		needNomenclatureList = proxyNomenclature.SaveNeedItemTreeNomenclature(map);
		
//		NeededItem ParentneededItem = proxyNeededItem.getNeededItemParentOfOrdredItem(2, 33);
//		map=proxyNeededItem.InitialiseMap();
//		map= proxyNeededItem.findNeededItemTreeByOrdredItem(ParentneededItem);
		
		proxyNeededItem.InitialiseASCMap();
		map = proxyManufacturing.stakingLaterScheduling(Parent, deliveryDate, 1);
		
//		for(Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()){
//		System.out.println(e.getKey().getNeeded_article().getArticleCode()+ " "+ e.getKey().getNetNeed());
//		Set<NeededItem> set = new HashSet<>(e.getValue()); 
//		System.out.println("**********Valeur*******");
//		for (NeededItem child : set) {
//			System.out.println(child.getNeeded_article().getArticleCode()+ " "+ child.getNetNeed());
//		}
//		System.out.println("***********************");
//	}
		
		
		
		for(Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()){
			for (ManufacturingPlanning manuf : e.getKey().getManufacturingPlanning()) {
				System.out.println(manuf.getNeededItem().getNeeded_article().getArticleCode());
				System.out.println("Starting : "+manuf.getStartingDate());
				System.out.println("Ending : "+manuf.getEndingDate());
				System.out.println("Duration : "+manuf.getDuration());
				System.out.println();
			}
			System.out.println("***********************");
		}
		
//		for(Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()){
//			System.out.println("readyLot "+proxyNeededItem.CheckReadyLot(e.getKey(), e.getValue()));
//			System.out.println("Parent");
//			System.out.println(e.getKey().getNeeded_article().getArticleCode() +" "+e.getKey().getLevel());
//			
//			System.out.println("Children");
//			for (NeededItem needChild : e.getValue()) {
//				System.out.println(needChild.getNeeded_article().getArticleCode() +" "+needChild.getLevel());
//			}
//		}
		
		
		
		//int duration =proxyManufacturing.manufacturingDuration(ParentneededItem.getNeeded_article(), 10);
//		int duration = 5280;
//	    System.out.println(duration);
//	    
		Calendar cal = Calendar.getInstance();
		cal.set(2018, 03, 22, 07, 0);

		Date dte = cal.getTime();
		
		Date d = proxyManufacturing.endingManufacturingDate(dte, 1020,2);
		
		SimpleDateFormat sdfe = new SimpleDateFormat("EEE MMM dd kk:mm");
	    System.out.println(sdfe.format(d));
//	    
//	    proxyNeededItem.updateStatusPurchaseOrder();
//	    proxyOrdredItem.updateStatusOrdredItem();
	    
//	    List<ManufacturingPlanning> ListMan = proxyManufacturing.ReadyManufacturingPlanning(map, dt);
//	    for (ManufacturingPlanning manufacturingPlanning : ListMan) {
//			System.out.println(manufacturingPlanning.getNeededItem().getNeeded_article().getArticleCode());
//			System.out.println(manufacturingPlanning.getNeededItem().getNeeded_article().getQuantity());
//			System.out.println(manufacturingPlanning.getQuantity());
//			System.out.println(manufacturingPlanning.getStartingDate());
//			System.out.println(manufacturingPlanning.getEndingDate());
//			System.out.println("next");
//		}
	    //proxyManufacturing.updateStatusToFinished();
	    
	}

}

