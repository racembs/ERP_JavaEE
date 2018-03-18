package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;

@Local
public interface ArticleServiceLocal {
public void addArticle(Article article );
public void incrementArticleQuantity (int idArticle,int Quantity);
public void addNomenclature(int idArticlePere,int idArticleFils,int Quantity);

}
