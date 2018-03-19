package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
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
		
		order.setClient(client);
		int idOrder = manufactProxy.addOrders(order);
	
			
	}

}

