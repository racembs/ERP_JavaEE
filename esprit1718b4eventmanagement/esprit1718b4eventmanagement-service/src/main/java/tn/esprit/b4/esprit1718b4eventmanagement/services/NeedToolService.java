package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;


import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;

import java.util.List;

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
	public Tool findToolById(int idTool) {
		TypedQuery<Tool> query=em.createQuery("SELECT S FROM Tool S WHERE S.id_Need= :n",Tool.class);
		System.out.println("yalaaaaa");
        query.setParameter("n", idTool);
		return query.getSingleResult();
		
	}

	@Override
	public Tool findToolByRef(String ref) {
		TypedQuery<Tool> query=em.createQuery("SELECT S FROM Tool S WHERE S.Reference= :n",Tool.class);
		System.out.println("yalaaaaa");
        query.setParameter("n", ref);
		return query.getSingleResult();
	}

	@Override
	public List<Tool> displayAvailability(boolean av) {
		String AVv;
		if (av) { AVv = "available" ;}
		else AVv = "non available" ;
		
		TypedQuery<Tool> query=em.createQuery("select n from Tool n where n.Availability=:a", Tool.class);
		query.setParameter("a", AVv);
		List<Tool> Tool=query.getResultList();
		return Tool;
		
	}

	@Override
	public List<Tool> displayAll() {
		TypedQuery<Tool> query=em.createQuery("from Tool", Tool.class);
		List<Tool> Tool=query.getResultList();
		return Tool;
	}
}
