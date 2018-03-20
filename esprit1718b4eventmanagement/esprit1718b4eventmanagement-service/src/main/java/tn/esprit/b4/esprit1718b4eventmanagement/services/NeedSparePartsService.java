package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
@Stateless
public class NeedSparePartsService implements NeedSparePartsServiceLocal,NeedSparePartsServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public int addSpareParts(SpareParts SpareParts) {
		em.persist(SpareParts);
		return SpareParts.getId_Need();
	}

	@Override
	public void deleteSpareParts(int idSpare) {
		SpareParts SpareParts = em.find(SpareParts.class, idSpare);
		em.remove(SpareParts);
		
	}

	@Override
	public void updateSpareParts(SpareParts SpareParts) {
		em.merge(SpareParts);
		
	}

	@Override
	public String findSpareParts(int idSpare) {
		// TODO Auto-generated method stub
		return null;
	}

}
