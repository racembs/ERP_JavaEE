package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Stateless;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;

@Stateless
public class NeedSparePartsService implements NeedSparePartsServiceLocal {
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
	public SpareParts findSparePartsById(int idSpare) {
		TypedQuery<SpareParts> query=em.createQuery("SELECT S FROM SpareParts S WHERE S.id_Need= :n ",SpareParts.class);
        query.setParameter("n", idSpare);
		return query.getSingleResult();
		
	}

	@Override
	public SpareParts findSparePartsByRef(String ref) {
		
		SpareParts SP = null;
		String jpql = "SELECT S FROM SpareParts S WHERE S.Reference= :n ";
		Query query = em.createQuery(jpql);
		query.setParameter("n", ref);
		try {
			SP = (SpareParts) query.getSingleResult();
			System.out.println("SpareParts found " + SP.getDescription());
			return SP;
		} catch (Exception e) {
			System.err.println("SpareParts Not found");
		}

		return null;
	}

	@Override
	public List<SpareParts> displayAll() {
		TypedQuery<SpareParts> query=em.createQuery("from SpareParts", SpareParts.class);
		List<SpareParts> SpareParts=query.getResultList();
		return SpareParts;
	}


}
