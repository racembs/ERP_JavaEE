package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.naming.Context;
import javax.naming.InitialContext;


import javafx.application.Application;

import javafx.stage.Stage;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote;

public class TestArboresence extends Application {

	/*public static void main(String[] args) throws NamingException {
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		
		
		Article article1 = new Article("1","article1","20","MatiérePrimére",50,100);
		Article article2 = new Article("2","article2","21","MatiérePrimére",30,150);
		System.out.println(ArticleProxy.addArticle(article1));
		ArticleProxy.addArticle(article2);
		article2.setId(1);
		ArticleProxy.updateArticle(article2);
		ArticleProxy.incrementArticleQuantity(1,20);
		//ArticleProxy.addNomenclature(1,2,5);
		
		System.out.println(ArticleProxy.findArticle(1).getDescription());
		ArticleProxy.updateNomeclature(1,2,20);
		
		System.out.println(ArticleProxy.getFilsArticles(1).get(0).getArticleFils());
		
	
			
	}*/
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
	
		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/EquipementService!tn.esprit.b4.esprit1718b4eventmanagement.services.EquipementServiceRemote";
		Context context = new InitialContext();

	String jndiName2 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArboresenceService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArboresenceServiceRemote";
	Context context1 = new InitialContext();

	EquipementServiceRemote Proxy = (EquipementServiceRemote) context.lookup(jndiName);
	
    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
    Calendar date = Calendar.getInstance();
    String dateF = df.format(date.getTime());
   
	System.out.println(Proxy.countequi());
/*	
	ArboresenceServiceRemote Proxy1 = (ArboresenceServiceRemote) context1.lookup(jndiName2);
	//System.out.println(	Proxy1.findArboresence(4).getId());
		
		
	Arboresence c=	Proxy1.findArboresence(4);
	*/
	
	
	//System.out.println(	c.getId());
	 //Equipment e =Proxy.findEquipementBySerie("2");
	//	System.out.println(	"ahawaaaaa"+e.getId());
	// e.setArboresence(c);
	//e.setEISDate("pffffffffffffffff");
	// Proxy.updateEquipment(e);
	 
	 
//	Integer id=c.getId();
	//Equipment equipement = new Equipment("2","article2","21","MatiérePrimére","4","4",c);
		
	//	System.out.println(	Proxy.getAllEquipment());
	
//Proxy.addEquippement(equipement);
	//System.out.println( Proxy.findEquipementBySerie("5").getFabriquant());
	}
	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String[] args) {
		launch(args);
	}

}

