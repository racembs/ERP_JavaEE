package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;


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


public class WorksUsService implements WorksUsServiceLocal, WorksUsServiceRemote {
	@PersistenceContext(unitName="spotlight-ejb")
	EntityManager em;
		@Override
		public void addWR(UsualWork w) {
			
			w.setNature(Nature.WorkRequest);
			em.persist(w);
			
		}
		@Override
		public void addWO(UsualWork w) {
			w.setNature(Nature.WorkOrder);
			em.persist(w);
			
		}
		@Override
		public void updateWork(UsualWork w) {
			em.merge(w);
			
		}
	
		@Override
		public void deleteWork(WorksPK idw) {
			UsualWork w=em.find(UsualWork.class,idw);
			em.remove(w);
			
		}
		@Override
		public List<Works> displayWRB() {
		
			TypedQuery<Works> query=em.createQuery("SELECT e FROM"
					+ " Works e WHERE e.nature =:param",Works.class);
			query.setParameter("param", Nature.WorkRequest);
			return query.getResultList();
		}
		/*public Equipment findById(int id) {
			return em.find(Equipment.class, id);
		}
		*/
		@Override
		public List<Works> DisplayUSWorks() {
			return em.createQuery("from Works", Works.class).getResultList();
		}
}
