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
@LocalBean
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
	@Override 
    public List<PreventiveWork> searchPreventiveWork(String input){
    	TypedQuery<PreventiveWork> query = em.createQuery("SELECT c FROM PreventiveWork c WHERE c.objet LIKE :input OR c.technology LIKE :input OR c.description LIKE :input OR c.CreatDate LIKE :input",PreventiveWork.class);
    	query.setParameter("input", "%" + input + "%");
    	List<PreventiveWork> results = query.getResultList();
    	return results;
    }
	@Override
	public void remove(int idw) {
		PreventiveWork w=em.find(PreventiveWork.class,idw);
		em.remove(w);
		
	}
	@Override
	public void update(PreventiveWork w) {
		em.merge(w);
		
	}
	public PreventiveWork findObject(String obj) {
		TypedQuery<PreventiveWork> query
		=em.createQuery("select n from PreventiveWork n where n.objet=:obj", PreventiveWork.class);
		query.setParameter("obj", obj);
		PreventiveWork p=query.getSingleResult();
		return p;
	}
	public PreventiveWork findID(int idw) {
		
		return em.find(PreventiveWork.class,idw);
	}
///////////////////////////////////////////////Preventive stategy efficiency///////////////////////////		

	public Double calculPefficiency() {
	//DecimalFormat df=new DecimalFormat("#%");
	Query query1 = em.createQuery("select count(*) from PreventiveWork ");
	Long done=(Long) query1.getSingleResult();
	Query query2 = em.createQuery("select count(*) from UsualWork c");
	Long tot=(Long) query2.getSingleResult();
	Double d=done.doubleValue();
	Double t=tot.doubleValue();
	return d/t;
	}
	
}
