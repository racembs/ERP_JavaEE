package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;

@Remote
public interface ArticleServiceRemote {
	public int addArticle(Article article);
	public void updateArticle(Article article);
	public void incrementArticleQuantity (int idArticle,int Quantity);
	public Article findArticle(int idArticle);
	
	public void addNomenclature(int idArticlePere,int idArticleFils,int Quantity);
	public void updateNomeclature(int idArticlePere, int idArticleFils, int Quantity);
	public List<Nomenclature> getFilsArticles(int idArticlePere);
}
