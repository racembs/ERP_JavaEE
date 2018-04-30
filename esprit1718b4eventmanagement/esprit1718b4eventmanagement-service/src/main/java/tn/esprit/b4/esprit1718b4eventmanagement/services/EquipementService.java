package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;



@Stateless
public class EquipementService extends GenericDAO<Equipment>  implements EquipementServiceLocal,EquipementServiceRemote{
	@PersistenceContext 
	EntityManager em;
	  public EquipementService() {
			// TODO Auto-generated constructor stub
			 super(Equipment.class);
		}
		@Override
		public Equipment findEquipementBySerie(String serie) {
			TypedQuery<Equipment> query
			=em.createQuery("SELECT a FROM Equipment a WHERE a.SerialNum= :code", Equipment.class);
			query.setParameter("code",serie);
			Equipment equipment=query.getSingleResult();
			return equipment ;
		}
	  
		@Override
		public void updateEquipment(Equipment e) {
			em.merge(e);
		}

		@Override
		public int addEquippement(Equipment equipement) {
			em.persist(equipement);
			return equipement.getId();
			}
	
		@Override
		public List<Equipment> getAllEquipment() {
			TypedQuery<Equipment> query
			=em.createQuery("select n from Equipment n", Equipment.class);
			
			List<Equipment> equi=query.getResultList();
			return equi;
		
		}


		@Override
		public List<Equipment> findEquipementFab(String type) {
			TypedQuery<Equipment> query
			=em.createQuery("SELECT a FROM Equipment a WHERE a.Fabriquant= :type", Equipment.class);
			query.setParameter("type", type);
			List<Equipment> equi=query.getResultList();
			return equi;
		}


		@Override
		public List<Equipment> findEquipementMarque(String marque ) {
			TypedQuery<Equipment > query
			=em.createQuery("SELECT a FROM Equipment  a WHERE a.Marque= :code", Equipment.class);
			query.setParameter("code", marque );
			List<Equipment> equipment=query.getResultList();
			return equipment;
		}
		@Override
		public List<Equipment> findEquipementSerialNum(String serie ) {
			TypedQuery<Equipment > query
			=em.createQuery("SELECT a FROM Equipment  a WHERE a.SerialNum= :code", Equipment.class);
			query.setParameter("code", serie );
			List<Equipment> equipment=query.getResultList();
			return equipment;
		}
		
		
		//***********************************ONS*****************************//
		@Override
		public List<Equipment> DisplayEquipment() {

			TypedQuery<Equipment> query=em.createQuery("SELECT a FROM Equipment a",Equipment.class);
			List <Equipment> result= query.getResultList();
			return result;
		}
	
		  
	    public List<Equipment> searchEquipment(String input){
	    	TypedQuery<Equipment> query = em.createQuery("SELECT c FROM Equipment c WHERE c.SerialNum LIKE :input OR c.State LIKE :input OR c.Fabriquant LIKE :input OR c.Marque LIKE :input",Equipment.class);
	    	query.setParameter("input", "%" + input + "%");
	    	List<Equipment> results = query.getResultList();
	    	return results;
	    }

		@Override
		public Equipment findEqupment(int idArbo) {
			Equipment arbo =em.find(Equipment.class,idArbo);
			return arbo;
		}
		@Override
		public void DeleteEqupment(int idArbo) {
			Equipment arbo =this.findEqupment(idArbo);
			em.remove(arbo);
			
		}
		
		@Override
		public Long Date(String datedeb,String datefin) {
			TypedQuery<Long> query
			=em.createQuery("SELECT COUNT(c) FROM Works c WHERE c.startDate <=STR_TO_DATE('" + datedeb + "', '%d/%m/%Y') "
		                + "and c.endDate >=STR_TO_DATE('" + datefin + "', '%d/%m/%Y')",Long.class);

			Long train=query.getSingleResult();
			return train;
		}

		@Override
		public Long countequi() {
			TypedQuery<Long> query
			=em.createQuery("SELECT COUNT(c) FROM Equipment c",Long.class);

			Long train=query.getSingleResult();
			return train;
		}
	
}
