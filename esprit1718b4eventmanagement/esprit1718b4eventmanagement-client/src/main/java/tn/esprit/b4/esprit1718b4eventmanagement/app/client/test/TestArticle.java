package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

public class TestArticle {

	public static void main(String[] args) throws NamingException {
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
		
	
			
	}

}

