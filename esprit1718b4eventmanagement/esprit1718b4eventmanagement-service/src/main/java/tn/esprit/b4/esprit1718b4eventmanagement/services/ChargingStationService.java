package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;


import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

@LocalBean
@Stateless
public class ChargingStationService extends GenericDAO<ChargingStation> implements ChargingStationServiceLocal, ChargingStationServiceRemote {


	@PersistenceContext
	EntityManager em;
	public ChargingStationService() {
		super(ChargingStation.class);
		// TODO Auto-generated constructor stub
	}
	@Override
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS) {
			
		ChargingStationPK ChSpk = new ChargingStationPK();
		ChSpk.setId_equipment(idEquipement);
		ChSpk.setIdUser(idUser);
		ChS.setChargingstationPK(ChSpk);
		em.persist(ChS);
		return ChSpk;
	}
	@Override
	public void deleteChargingStation(int idEquipement , int idUser) {
		
		ChargingStation Chs = findChargingStation(idEquipement, idUser);
		em.remove(Chs);
		
	}
	

	
	
	@Override
	public void updateChargingStation(int idEquipement, int idUser, ChargingStation ChS) {
		ChargingStationPK ChSpk = new ChargingStationPK();
		ChSpk.setId_equipment(idEquipement);
		ChSpk.setIdUser(idUser);
		ChS.setChargingstationPK(ChSpk);
		em.merge(ChS);
		
	}
	@Override
	public ChargingStation findChargingStation(int idEquipement, int idUser) {
		ChargingStationPK chsPk = new ChargingStationPK();
		chsPk.setId_equipment(idEquipement);
		chsPk.setIdUser(idUser);
		return em.find(ChargingStation.class, chsPk);
		
	}
	


	
	@Override
	public ChargingStation findChargingStationByEquipement(int idEquipement) {
		ChargingStationPK chsPk = new ChargingStationPK();
		chsPk.setId_equipment(idEquipement);
		return em.find(ChargingStation.class, chsPk);
		
	}
	
	@Override
	public ChargingStation findChargingStationByUser(int idUser) {
		ChargingStationPK chsPk = new ChargingStationPK();
		chsPk.setIdUser(idUser);
		return em.find(ChargingStation.class, chsPk);
		
	}

	public List<ChargingStation> DisplayChargingStation() {

		TypedQuery<ChargingStation> query=em.createQuery("SELECT o FROM ChargingStation o",ChargingStation.class);
		List <ChargingStation> result= query.getResultList();
		return result;
	}
	
	@Override
	public List<ChargingStation> findd(String code) {
		
		TypedQuery<ChargingStation> query=em.createQuery(
				"select o from ChargingStation o where o.description like :code", ChargingStation.class);
		query.setParameter("code", "%"+code+"%");
		List <ChargingStation> result= query.getResultList();
		return result;
	}

	@Override
	public List<ChargingStation> findByUser(Integer idUser) {
		TypedQuery<ChargingStation> query
		=em.createQuery("SELECT o FROM ChargingStation o WHERE o.chargingstationPK.idUser  =:idUser", ChargingStation.class);
		query.setParameter("idUser",idUser);
		List<ChargingStation> list=query.getResultList();
		return list;
	}

	public void ajouter(ChargingStation cs) {
		em.persist(cs);
		
	}
}
