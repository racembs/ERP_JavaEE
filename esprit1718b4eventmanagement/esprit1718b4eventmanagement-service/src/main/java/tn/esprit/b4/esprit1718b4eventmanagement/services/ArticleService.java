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
	public int addArticle(Article article) {
		em.persist(article);
		return article.getId();
		}

	
	@Override
	public void incrementArticleQuantity(int idArticle, int quantity) {
		Article article =em.find(Article.class,idArticle);
		article.setQuantity(article.getQuantity()+quantity);
		
	}


	@Override
	public void addNomenclature(int idArticlePere, int idArticleFils, int quantity) {
		Nomenclature nomenclature =new Nomenclature();
		NomenclaturePk nomenclaturePk =new NomenclaturePk();
		nomenclaturePk.setIdArticleFils(idArticleFils);
		nomenclaturePk.setIdArticlePere(idArticlePere);
		nomenclature.setNomenclauturePk(nomenclaturePk);
		nomenclature.setQuantity(quantity);
		em.persist(nomenclature);
		
		
	}


	@Override
	public void updateArticle(Article article) {
		em.merge(article);
	}


	@Override
	public Article findArticle(int idArticle) {
		Article article =em.find(Article.class,idArticle);
		return article;
	}


	@Override
	public void updateNomeclature(int idArticlePere, int idArticleFils, int quantity) {
		Nomenclature nomenclature=new Nomenclature();
		NomenclaturePk nomenclaturePk=new NomenclaturePk();
		nomenclaturePk.setIdArticlePere(1);
		nomenclaturePk.setIdArticleFils(2);
		nomenclature.setNomenclauturePk(nomenclaturePk);
		nomenclature.setQuantity(quantity);
		
		em.merge(nomenclature);
	}
	

	
}
