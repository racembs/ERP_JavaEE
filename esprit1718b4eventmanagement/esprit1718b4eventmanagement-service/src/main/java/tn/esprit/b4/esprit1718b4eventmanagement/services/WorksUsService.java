package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
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
		public void deleteWork(int idw) {
			UsualWork w=em.find(UsualWork.class,idw);
			em.remove(w);
			
		}
		@Override
		public List<UsualWork> displayWRB() {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.nature =:param",UsualWork.class);
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
		@Override
		public List<UsualWork> displayWO() {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.nature =:param",UsualWork.class);
			query.setParameter("param", Nature.WorkOrder);
			return query.getResultList();
		}
		@Override
		public List<UsualWork> displayWObyTech(int idtech) {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.nature =:param AND e.technicianId =:param2 ",UsualWork.class);
			query.setParameter("param", Nature.WorkOrder);
			query.setParameter("param2", idtech);
			return query.getResultList();
		}
		@Override
		public UsualWork findById(int idW) {
			TypedQuery<UsualWork> query=em.createQuery("SELECT S FROM UsualWork S WHERE S.id= :n",UsualWork.class);
	        query.setParameter("n", idW);
			return query.getSingleResult();
		}
}
