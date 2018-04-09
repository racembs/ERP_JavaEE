package tn.esprit.b4.esprit1718b4eventmanagement.services;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

import javax.ejb.LocalBean;

import javax.persistence.Query;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;


/**
 * Session Bean implementation class UserService
 */
@Stateless

public class WorkPrevService implements WorkPrevServiceLocal, WorkPrevServiceRemote {
	@PersistenceContext(unitName="spotlight-ejb")
	EntityManager em;
	@Override
	public void addWP(PreventiveWork w) {
		
		em.persist(w);
		
	}
	@Override
	public List<PreventiveWork> DisplayPWorks() {
		return em.createQuery("from PreventiveWork", PreventiveWork.class).getResultList();
	}
	@Override
	public List<PreventiveWork> displayWPbyTech(int idtech) {
	
		TypedQuery<PreventiveWork> query=em.createQuery("SELECT e FROM"
				+ " PreventiveWork e WHERE e.technicianId =:param2 ",PreventiveWork.class);
		
		query.setParameter("param2", idtech);
		return query.getResultList();
	}
}
