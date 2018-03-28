package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;

@Stateless
public class MvtApprovService implements MvtApprovServiceLocal,MvtApprovServiceRemote {
	@PersistenceContext 
	EntityManager em;

	@Override
	public int addMvtApprov(MvtApprov mvtApprov) {
		em.persist(mvtApprov);
		return mvtApprov.getId();
	}

	@Override
	public void updateMvtApprov(MvtApprov mvtApprov) {
		em.merge(mvtApprov);
	}

	@Override
	public MvtApprov findMvtApprovById(int idMvtApprov) {
		MvtApprov mvtApprov =em.find(MvtApprov.class,idMvtApprov);
		return mvtApprov;
		
	}
	@Override
	public List<MvtApprov> getAllOrders() {
	TypedQuery<MvtApprov> query
	=em.createQuery("select m from MvtApprov m", MvtApprov.class);
	
	List<MvtApprov> ordres=query.getResultList();
	return ordres;
	}
	@Override
	public MvtApprov getOrderByArticle(int id) {
	TypedQuery<MvtApprov> query
	=em.createQuery("select m from MvtApprov m where m.article.Id=id", MvtApprov.class);
	query.setParameter("id",id);
	MvtApprov ordre=query.getSingleResult();
	return ordre;
	}

}
