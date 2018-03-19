package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
@Stateless
public class OperatingRangeService implements OperatingRangeServiceLocal, OperatingRangeServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public int addOperatingRange(OperatingRange operatingranges) {
		em.persist(operatingranges);
		return operatingranges.getId();
	
	}
	@Override
	public void deleteOperatingRange(int idOptR) {
		OperatingRange OptRange = em.find(OperatingRange.class, idOptR);
		em.remove(OptRange);
		
	}
	@Override
	public void updateOperatingRange(int idOptR) {
		OperatingRange OptRange = em.find(OperatingRange.class, idOptR);
		OptRange.setDeadline(50);
		
		
	}
	@Override
	public String findOperatingRange(int idOptR) {
		TypedQuery<String> query=em.createQuery(
				"select o.designation from OperatingRange o where o.id=:idOptR", String.class);
		query.setParameter("idOptR", idOptR);
		return query.getSingleResult();
	}

}
