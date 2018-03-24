package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;


@Stateless
public class ChargingStationService implements ChargingStationServiceLocal, ChargingStationServiceRemote {
	@PersistenceContext
	EntityManager em;
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
	public List<ChargingStation> find(int code) {
		TypedQuery<ChargingStation> query=em.createQuery(
				"select o from ChargingStation o where o.code=:code", ChargingStation.class);
		query.setParameter("code", code);
		List <ChargingStation> result= query.getResultList();
		return result;
	}



}
