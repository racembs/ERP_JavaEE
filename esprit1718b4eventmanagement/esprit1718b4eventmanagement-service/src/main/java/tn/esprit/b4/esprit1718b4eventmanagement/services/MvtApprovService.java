package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


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

}
