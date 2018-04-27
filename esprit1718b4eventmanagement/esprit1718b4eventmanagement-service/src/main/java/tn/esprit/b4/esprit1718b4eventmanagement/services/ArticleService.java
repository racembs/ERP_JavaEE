package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;


@Stateless
@LocalBean
public class ArticleService implements ArticleServiceLocal,ArticleServiceRemote{
	@PersistenceContext 
	EntityManager em;

	@Override
	public int addArticle(Article article) {
		em.persist(article);
		/*Article Newarticle=new Article(article.getArticleCode(),article.getDescription(),article.getUnitCode(),article.getType()
				,article.getPmp(),article.getQuantity());
		em.persist(Newarticle);*/
		return article.getId();
		}
	@Override
	public void DeleteArticle(int idArticle) {
		Article article =this.findArticle(idArticle);
		em.remove(article);
		
	}
	
	@Override
	public void DeleteNomenclature(Nomenclature nomenclature) {
		/*TypedQuery<Nomenclature> query
		=em.createQuery("delete n from Nomenclature n where n.nomenclauturePk.idArticlePere=:idPere and n.nomenclauturePk.idArticlesFils=:idFils", Nomenclature.class);
		query.setParameter("idPere", nomenclature.getArticlePere().getId());
		query.setParameter("idFils", nomenclature.getArticleFils().getId());*/
		
		
		
		em.remove(findNomenclature(nomenclature.getArticlePere().getId(),nomenclature.getArticleFils().getId()).get(0));
		
	}
	
	@Override
	public List<Nomenclature> findNomenclature(int idArticlePere,int idArticleFils) {
		TypedQuery<Nomenclature> query
		=em.createQuery("select n from Nomenclature n where n.nomenclauturePk.idArticlePere=:idPere and n.nomenclauturePk.idArticlesFils=:idFils", Nomenclature.class);
		query.setParameter("idPere", idArticlePere);
		query.setParameter("idFils", idArticleFils);
		List<Nomenclature> nomenclature=query.getResultList();
		return nomenclature;
		
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
	public void updateArticle(Article newArticle) {
		Article article =findArticleByCode(newArticle.getArticleCode()).get(0);
		article.setArticleCode(newArticle.getArticleCode());
		article.setDescription(newArticle.getDescription());
		article.setPmp(newArticle.getPmp());
		article.setQuantity(newArticle.getQuantity());
		article.setType(newArticle.getType());
		article.setUnitCode(newArticle.getUnitCode());
		article.setDailyConsumption(newArticle.getDailyConsumption());
		article.setDeliveryTime(newArticle.getDeliveryTime());
		article.setEtatOrdre(newArticle.getEtatOrdre());
		article.setPricipalQuantity(newArticle.getPricipalQuantity());
		
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
	public List<Nomenclature> getAllFinalArticleNomenclature(){
		TypedQuery<Nomenclature> query
		=em.createQuery("select n from Nomenclature n", Nomenclature.class);
		//query.setParameter("type", "Produit-Fini");
		List<Nomenclature> nomenclature=query.getResultList();
		return nomenclature;
		
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
	public List<Article> findArticleByCode(String code) {
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.ArticleCode like :code", Article.class);
		query.setParameter("code",code);
		List<Article> article=query.getResultList();
		
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

		TypedQuery<Article> query1=em.createQuery("SELECT a FROM Article a",Article.class);
		List <Article> result= query1.getResultList();
		return result;
	}

	//********************Methode for Orders interface***************//
	@Override
	public List<Article> findArticleByCodeORDescription(String input) {
		String type = "Produit-Fini";
		TypedQuery<Article> query
		=em.createQuery("SELECT a FROM Article a WHERE a.ArticleCode LIKE :input AND a.Type= :type OR a.Description LIKE :input AND a.Type= :type", Article.class);
		query.setParameter("input","%"+input+"%");
		query.setParameter("type",type);
		List<Article> article=query.getResultList();
		return article;
	}

}
