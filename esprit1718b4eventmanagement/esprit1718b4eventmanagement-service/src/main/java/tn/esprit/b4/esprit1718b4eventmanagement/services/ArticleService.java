package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

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


	@Override
	public List<Nomenclature> getFilsArticles(int idArticlePere) {
		TypedQuery<Nomenclature> query
		=em.createQuery("select n from Nomenclature n where n.nomenclauturePk.idArticlePere=:idPere", Nomenclature.class);
		query.setParameter("idPere", idArticlePere);
		List<Nomenclature> nomenclature=query.getResultList();
		return nomenclature;
		
	}


	@Override
	public List<Article> getAllArticles() {
		TypedQuery<Article> query
		=em.createQuery("select n from Article n", Article.class);
		
		List<Article> article=query.getResultList();
		return article;
	
	}


	@Override
	public List<Article> getArticlesByType(String type) {
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.Type= :type", Article.class);
		query.setParameter("type", type);
		List<Article> article=query.getResultList();
		return article;
	}


	@Override
	public Article findArticleByCode(String code) {
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.ArticleCode= :code", Article.class);
		query.setParameter("code",code);
		Article article=query.getSingleResult();
		return article;
	}


	@Override
	public List<Article> findArticleByCodeAndType(String code, String type) {
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.ArticleCode LIKE :code AND a.Type= :type", Article.class);
		query.setParameter("code","%"+code+"%");
		query.setParameter("type",type);
		List<Article> article=query.getResultList();
		return article;
	}
	@Override
	public List<Article> getArticleListByCode(String code) {
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.ArticleCode LIKE :code", Article.class);
		query.setParameter("code","%"+code+"%");
		List<Article> article=query.getResultList();
		return article;
	}
	
	//*************************Done By ONS****************************//
	
	@Override
	public List<Article> DisplayArticle() {

		TypedQuery<Article> query=em.createQuery("SELECT a FROM Article a",Article.class);
		List <Article> result= query.getResultList();
		return result;
	}

	
}
