package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
@Stateless
public class OperatingRangeService implements OperatingRangeServiceLocal, OperatingRangeServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public int addOperatingRange(OperatingRange operatingranges) {
		em.persist(operatingranges);
		return operatingranges.getIdoptrange();
	
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
	
	
	@Override
	public List<OperatingRange> find(String code) {
		TypedQuery<OperatingRange> query=em.createQuery(
				"select o from OperatingRange o where o.code=:code", OperatingRange.class);
		query.setParameter("code", code);
		List <OperatingRange> result= query.getResultList();
		return result;
	}

	@Override
	public List<OperatingRange> DisplayOperatingRange() {

		TypedQuery<OperatingRange> query=em.createQuery("SELECT o FROM OperatingRange o",OperatingRange.class);
		List <OperatingRange> result= query.getResultList();
		return result;
	}
	
	public Long CountOperatingRange() {

		TypedQuery<Long> query=em.createQuery("SELECT COUNT(o) FROM OperatingRange o",Long.class);
		Long result= query.getSingleResult();
		return result;
	}

}
