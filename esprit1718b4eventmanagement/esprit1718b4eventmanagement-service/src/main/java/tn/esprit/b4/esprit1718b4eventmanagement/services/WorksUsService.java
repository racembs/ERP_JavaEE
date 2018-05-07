package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;



import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;

import javax.persistence.Query;
import javax.persistence.Tuple;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;


/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean

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
		@Override
		public List<UsualWork> displayWObyTechStart(int idtech) {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.nature =:param AND e.technicianId =:param2 And NOT e.orderstate=:p1  And NOT e.orderstate=:p2 ",UsualWork.class);
			query.setParameter("param", Nature.WorkOrder);
			query.setParameter("param2", idtech);
			query.setParameter("p1", "start");
			query.setParameter("p2", "done");
			return query.getResultList();
		}
	
		@Override
		public List<UsualWork> displayStart() {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.orderstate =:param ",UsualWork.class);
			
			query.setParameter("param", "start");
			return query.getResultList();
		}
		@Override
		public List<UsualWork> displayDone() {
		
			TypedQuery<UsualWork> query=em.createQuery("SELECT e FROM"
					+ " UsualWork e WHERE e.orderstate =:param ",UsualWork.class);
			
			query.setParameter("param", "done");
			return query.getResultList();
		}
		@Override 
	    public List<UsualWork> searchUsualWork(String input){
	    	TypedQuery<UsualWork> query = em.createQuery("SELECT c FROM UsualWork c WHERE c.objet LIKE :input OR c.technology LIKE :input OR c.description LIKE :input OR c.CreatDate LIKE :input",UsualWork.class);
	    	query.setParameter("input", "%" + input + "%");
	    	List<UsualWork> results = query.getResultList();
	    	return results;
	    }
//////////////--------PMC Method------------///////////////////////////	
		@Override
		public Double calculPMCw1() {

	        Calendar cal = new GregorianCalendar();
	        cal.add(Calendar.DAY_OF_YEAR, -7);
			//DecimalFormat df=new DecimalFormat("#%");
			Query query1 = em.createQuery("select count(*) from UsualWork c where (c.WRDate BETWEEN  :sarra AND now()) AND c.orderstate='done'");
			query1.setParameter("sarra", cal.getTime());
		
		 Long done=(Long) query1.getSingleResult();
			 Query query2 = em.createQuery("select count(*) from UsualWork c where c.WRDate BETWEEN  :d1 AND now()");
			query2.setParameter("d1", cal.getTime());
			Long tot=(Long) query2.getSingleResult();
			 Double d=done.doubleValue();
			 Double t=tot.doubleValue();
			 
			return d/t;
		
		}
		public Double calculPMCw2() {
			Calendar cal = new GregorianCalendar();
	        cal.add(Calendar.DAY_OF_YEAR, -7);
	        Calendar cal2 = new GregorianCalendar();
	        cal2.add(Calendar.DAY_OF_YEAR, -14);
			//DecimalFormat df=new DecimalFormat("#%");
			Query query1 = em.createQuery("select count(*) from UsualWork c where (c.WRDate BETWEEN :wk2 AND :sarra) AND c.orderstate='done'");
			query1.setParameter("sarra", cal.getTime());
			query1.setParameter("wk2", cal2.getTime());
			
		 Long done=(Long) query1.getSingleResult();
			 Query query2 = em.createQuery("select count(*) from UsualWork c where c.WRDate BETWEEN  :wk2 AND :sarra");
			 query2.setParameter("sarra", cal.getTime());
				query2.setParameter("wk2", cal2.getTime());
			Long tot=(Long) query2.getSingleResult();
			 Double d=done.doubleValue();
			 Double t=tot.doubleValue();
			 
			return d/t;
		}
		public Double calculPMCw3() {
			Calendar cal = new GregorianCalendar();
	        cal.add(Calendar.DAY_OF_YEAR, -14);
	        Calendar cal2 = new GregorianCalendar();
	        cal2.add(Calendar.DAY_OF_YEAR, -21);
			//DecimalFormat df=new DecimalFormat("#%");
			Query query1 = em.createQuery("select count(*) from UsualWork c where (c.WRDate BETWEEN  :wk2 AND :sarra) AND c.orderstate='done'");
			query1.setParameter("sarra", cal.getTime());
			query1.setParameter("wk2", cal2.getTime());
			
		 Long done=(Long) query1.getSingleResult();
			 Query query2 = em.createQuery("select count(*) from UsualWork c where c.WRDate BETWEEN :wk2 AND :sarra");
			 query2.setParameter("sarra", cal.getTime());
				query2.setParameter("wk2", cal2.getTime());
			Long tot=(Long) query2.getSingleResult();
			 Double d=done.doubleValue();
			 Double t=tot.doubleValue();
			 
				return d/t;
		}
		public Double calculPMCw4() {
			Calendar cal = new GregorianCalendar();
	        cal.add(Calendar.DAY_OF_YEAR, -21);
	        Calendar cal2 = new GregorianCalendar();
	        cal2.add(Calendar.DAY_OF_YEAR, -28);
			//DecimalFormat df=new DecimalFormat("#%");
			Query query1 = em.createQuery("select count(*) from UsualWork c where (c.WRDate BETWEEN :wk2 AND :sarra) AND c.orderstate='done'");
			query1.setParameter("sarra", cal.getTime());
			query1.setParameter("wk2", cal2.getTime());
			
		 Long done=(Long) query1.getSingleResult();
			 Query query2 = em.createQuery("select count(*) from UsualWork c where c.WRDate BETWEEN :wk2 AND :sarra");
			 query2.setParameter("sarra", cal.getTime());
				query2.setParameter("wk2", cal2.getTime());
			Long tot=(Long) query2.getSingleResult();
			 Double d=done.doubleValue();
			 Double t=tot.doubleValue();
			 
				return d/t;
		}
///////////////////////////////////////////////PMPmethode///////////////////////////		
		
		public List<Double> calculPMP() {
			List<Double> list = new ArrayList<>();
			Date today = new Date(); // Fri Jun 17 14:54:28 PDT 2016 
			Calendar cal = Calendar.getInstance();
			cal.setTime(today);
			int month1 = cal.get(Calendar.MONTH)+1;
			int month2 = cal.get(Calendar.MONTH);
			int month3 = cal.get(Calendar.MONTH)-1;
			
					Query query1 = em.createQuery("select count(*) from PreventiveWork c where month(c.CreatDate)=:mo1");
					query1.setParameter("mo1", month1);
					Long done1=(Long) query1.getSingleResult();
			 		//----------------------------------------//
					Query query2 = em.createQuery("select count(*) from PreventiveWork c where month(c.CreatDate)=:mo2");
					query2.setParameter("mo2",month2);
					Long done2=(Long) query2.getSingleResult();
					//------------------------------------------------------//
					Query query3 = em.createQuery("select count(*) from PreventiveWork c where month(c.CreatDate)=:mo3");
					query3.setParameter("mo3", month3);
					Long done3=(Long) query3.getSingleResult();
					//------------------------------------------//
					
			 Query query4 = em.createQuery("select count(*) from UsualWork c");
			 Long tot=(Long) query4.getSingleResult();
			 Double t=tot.doubleValue();
			 //------------------------------------------//
			 Double d1=done1.doubleValue();
			 Double d2=done2.doubleValue();
			 Double d3=done3.doubleValue();
			
			 list.add(0,d1/t);
			 list.add(1,d2/t);
			 list.add(2,d3/t);
			return list;
		}
//////////////////////////////////////////******Worksperformed on time*********/////////////////////////		
@Override
		public Long WorkOntime() {
//DecimalFormat df=new DecimalFormat("#%");
Query query1 = em.createQuery("select count(*) from UsualWork c where c.WRDate=c.startDate");
Long done=(Long) query1.getSingleResult();

return done;
}
//////////////////////////////////////////******Top 5 productive technician*********/////////////////////////	
@SuppressWarnings("unchecked")
@Override
public  List<Object> Top5ProdTech() {
	Query query= em.createQuery("SELECT u.firstname ,COUNT(*) as num FROM UsualWork c INNER JOIN c.user u where (u.id=c.technicianId)  GROUP BY c.technicianId ORDER BY num DESC");
	query.setMaxResults(5);
	List<Object> objs = (List<Object>)query.getResultList();
 return objs;
}

}
