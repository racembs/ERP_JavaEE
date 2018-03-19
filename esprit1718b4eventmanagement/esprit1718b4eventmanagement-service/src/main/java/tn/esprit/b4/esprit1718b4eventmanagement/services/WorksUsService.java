package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;


public class WorksUsService implements WorksUsServiceLocal, WorksUsServiceRemote {
	EntityManager em;
	/*	public WatchlistService() {
			// TODO Auto-generated constructor stub
		}
	*/
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
		public UsualWork displayWRB() {
			TypedQuery<UsualWork> query=em.createQuery("SELECT c FROM works c WHERE c.nature= :n",UsualWork.class);
			
			return query.setParameter("n", Nature.WorkRequest).getSingleResult();
		}
		
}
