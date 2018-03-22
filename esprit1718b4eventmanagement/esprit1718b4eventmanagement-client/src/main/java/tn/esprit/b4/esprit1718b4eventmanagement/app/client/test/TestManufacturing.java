package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.*;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ManufacturingServiceRemote;

public class TestManufacturing {

	public static void main(String[] args) throws NamingException, ParseException {
		String ManufactjndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ManufacturingService!tn.esprit.b4.esprit1718b4eventmanagement.services.ManufacturingServiceRemote";
		Context context = new InitialContext();
		ManufacturingServiceRemote manufactProxy =  (ManufacturingServiceRemote) context.lookup(ManufactjndiName);
		
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		
		Client client = new Client(1798,"PSE");
		client.setId(manufactProxy.addClient(client));
		
//		Client client=manufactProxy.findClientById(1);
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy");
		Date orderDat = dateFormat.parse("18/03/2018");
		Date deliveryDat = dateFormat.parse("25/03/2018");
		Orders order = new Orders(1818,orderDat,deliveryDat,"en attente");
		Orders order2 = new Orders(1818,orderDat,deliveryDat,"en attente");
		
		order.setClient(client);
		int idOrder = manufactProxy.addOrders(order);
		int idOrder2 = manufactProxy.addOrders(order2);
		
		Article PF = new Article("ARM100", "Armoire", "unit", "Produit fini", 540, 10);
		PF.setId(ArticleProxy.addArticle(PF));
		
		OrdredItem ordredItem = new OrdredItem();
		ordredItem.setCode(4518);
		ordredItem.setQuantity(10);
		ordredItem.setStatus("en attente");
		manufactProxy.addOrdredItem(1, 1, ordredItem);
		
		OrdredItem orderItem2 = manufactProxy.findOrdredItemById(1,1);
		System.out.println(orderItem2.getArticle().getDescription());
		System.out.println(orderItem2.getOrdredItemPk().getId_Article());
		
		
//		ManufacturingOrder searchMan2 = manufactProxy.addManufactChild(searchMan);
//		System.out.println(searchMan2.getSonsMO().get(0).getManufacturingOrderPk().getId_Article());

		ManufacturingOrder Father = new ManufacturingOrder();
		Father.setCode(1200);
		Father.setQuantity(7);
		Father.setOrderItem(orderItem2);
		Father.setMO_article(orderItem2.getArticle());
		Father.setId(manufactProxy.addManufactOrder(Father));
		
		ManufacturingOrder Child = new ManufacturingOrder();
		Child.setCode(4600);
		Child.setQuantity(2);
		Child.setOrderItem(Father.getOrderItem());
		Child.setMO_article(orderItem2.getArticle());
		Child.setId(manufactProxy.addManufactOrder(Child));
		
		ManufactOrderNomenclature nomenclature = manufactProxy.addnomenclature(Father.getId(), Child.getId(), 0);
		
		
		
			
	}

}

