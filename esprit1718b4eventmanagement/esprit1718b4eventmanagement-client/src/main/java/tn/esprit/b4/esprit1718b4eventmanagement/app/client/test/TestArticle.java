package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;

public class TestArticle {

	public static void main(String[] args) throws NamingException {
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ArticleService!tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote";
		Context context = new InitialContext();
		ArticleServiceRemote ArticleProxy = (ArticleServiceRemote) context.lookup(ArticlejndiName);
		Article article1 = new Article("1","article1","20","MatiérePrimére",50,100);
		Article article2 = new Article("2","article2","21","MatiérePrimére",50,100);
		ArticleProxy.addArticle(article1);
		ArticleProxy.addArticle(article2);
		ArticleProxy.incrementArticleQuantity(1,20);
		ArticleProxy.addNomenclature(2,3,5);
	
			
	}

}

