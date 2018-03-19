package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;

@Local
public interface ArticleServiceLocal {
	public int addArticle(Article article);
	public void updateArticle(Article article);
	public void incrementArticleQuantity (int idArticle,int Quantity);
	public Article findArticle(int idArticle);
	
	public void addNomenclature(int idArticlePere,int idArticleFils,int Quantity);
	public void updateNomeclature(int idArticlePere, int idArticleFils, int Quantity);

}
