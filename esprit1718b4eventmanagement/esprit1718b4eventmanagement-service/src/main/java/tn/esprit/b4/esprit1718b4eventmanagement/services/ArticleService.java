package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;


@Stateless
public class ArticleService implements ArticleServiceLocal,ArticleServiceRemote{
	@PersistenceContext 
	EntityManager em;

	@Override
	public void addArticle(Article article) {
		em.persist(article);
		}

	
	@Override
	public void incrementArticleQuantity(int idArticle, int Quantity) {
		Article article =em.find(Article.class,idArticle);
		article.setQuantity(article.getQuantity()+Quantity);
		
	}


	@Override
	public void addNomenclature(int idArticlePere, int idArticleFils, int Quantity) {
		Article articlePere =em.find(Article.class,idArticlePere);
		Article articleFils =em.find(Article.class,idArticleFils);
		Nomenclature nomenclature =new Nomenclature();
		NomenclaturePk nomenclaturePk =new NomenclaturePk();
		nomenclaturePk.setIdArticleFils(idArticleFils);
		nomenclaturePk.setIdArticlePere(idArticlePere);
		nomenclature.setNomenclauturePk(nomenclaturePk);
		nomenclature.setQuantity(Quantity);
		em.persist(nomenclature);
		
		
	}
	

	
}
