package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import javax.ejb.Stateless;


@Stateless
public class NeedToolService implements NeedToolServiceLocal,NeedToolServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public void addTool(Tool T) {
		em.persist(T);
	}

	@Override
	public void updateTool(Tool T) {
		em.merge(T);
		
	}

	@Override
	public void deleteTool(int idT) {
		Tool T=em.find(Tool.class,idT);
		em.remove(T);
		
	}
	@Override
	public SpareParts findToolById(int idTool) {
		TypedQuery<SpareParts> query=em.createQuery("SELECT S FROM tab_need S WHERE S.id_Need= :n And DTYPE = 'SpareParts' ",SpareParts.class);
		System.out.println("yalaaaaa");
        query.setParameter("n", idTool);
		return query.getSingleResult();
		
	}

	@Override
	public SpareParts findToolByRef(String ref) {
		TypedQuery<SpareParts> query=em.createQuery("SELECT S FROM tab_need S WHERE S.Reference= :n And DTYPE = 'SpareParts' ",SpareParts.class);
		System.out.println("yalaaaaa");
        query.setParameter("n", ref);
		return query.getSingleResult();
	}
}
