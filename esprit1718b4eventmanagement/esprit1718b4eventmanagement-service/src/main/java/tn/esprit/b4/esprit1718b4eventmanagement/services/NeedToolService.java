package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;

public class NeedToolService implements NeedToolServiceLocal,NeedToolServiceRemote {
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
	public Tool displayTools() {
		// TODO Auto-generated method stub
		return null;
	}

}
