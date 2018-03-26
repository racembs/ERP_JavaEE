package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.*;
import tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ClientServiceRemote;
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
		String jndiNameManufacturingPlan = "exported/esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingPlanningService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.ManufacturingPlanningServiceRemote";
		String jndiNameOrdredItem = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrderItemService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrderItemServiceRemote";
		String jndiNameOrders= "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OrdersService!tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices.OrdersServiceRemote";
		String jndiNameArticle = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		ServiceLocator s=ServiceLocator.getInstance(); 
		ClientServiceRemote proxyClientServiceRemote=(ClientServiceRemote) s.getProxy(jndiNameClient);
		NeedNomenclatureServiceRemote proxyNomenclature=(NeedNomenclatureServiceRemote) s.getProxy(jndiNameNomenclature);
		NeededItemServiceRemote proxyNeededItem=(NeededItemServiceRemote) s.getProxy(jndiNameNeededItem);
		NeededItemServiceRemote proxyManufacturing=(NeededItemServiceRemote) s.getProxy(jndiNameManufacturingPlan);
		OrderItemServiceRemote proxyOrdredItem=(OrderItemServiceRemote) s.getProxy(jndiNameOrdredItem);
		OrdersServiceRemote proxyOrders=(OrdersServiceRemote) s.getProxy(jndiNameOrders);
		ArticleServiceRemote proxyArticleServiceRemote=(ArticleServiceRemote) s.getProxy(jndiNameArticle);
		
		Client client = new Client("1798","PSE",22827736);
		client.setId(proxyClientServiceRemote.addClient(client));
		
//		Client client=manufactProxy.findClientById(1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
		Date orderDat = dateFormat.parse("18/03/2018");
		Date deliveryDat = dateFormat.parse("25/03/2018");
		Orders order = new Orders(1818,orderDat,deliveryDat,"en attente");
		Orders order2 = new Orders(1818,orderDat,deliveryDat,"en attente");
		
		order.setClient(client);
		int idOrder = proxyOrders.addOrders(order);
		int idOrder2 = proxyOrders.addOrders(order2);
		
		Article PF = new Article("ARM200", "Armoire", "unit", "Produit fini", 540, 10);
		PF.setId(proxyArticleServiceRemote.addArticle(PF));
		
		OrdredItem ordredItem = new OrdredItem();
		ordredItem.setCode(4518);
		ordredItem.setQuantity(10);
		ordredItem.setStatus("en attente");
		//proxyOrdredItem.addOrdredItem(1, 1, ordredItem);
		
		OrdredItem orderItem2 = proxyOrdredItem.findOrdredItemById(1,1);
		System.out.println(orderItem2.getArticle().getDescription());
		System.out.println(orderItem2.getOrdredItemPk().getId_Article());
		
		
//		NeededItem searchMan2 = manufactProxy.addManufactChild(searchMan);
//		System.out.println(searchMan2.getSonsMO().get(0).getManufacturingOrderPk().getId_Article());

		NeededItem Father = new NeededItem();
		Father.setOrderItem(orderItem2);
		Father.setNeeded_article(orderItem2.getArticle());
		Father.setId(proxyNeededItem.addNeededItem(Father));
		
		NeededItem Child = new NeededItem();
		Child.setOrderItem(Father.getOrderItem());
		Child.setNeeded_article(orderItem2.getArticle());
		Child.setId(proxyNeededItem.addNeededItem(Child));
		
		NeedNomenclature nomenclature = proxyNomenclature.addnomenclature(Father.getId(), Child.getId(), 10);
		
		
		
			
	}

}

