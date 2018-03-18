package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;

@Remote
public interface ArticleServiceRemote {
	public void addArticle(Article article);
	public void incrementArticleQuantity (int idArticle,int Quantity);
	public void addNomenclature(int idArticlePere,int idArticleFils,int Quantity);
}
