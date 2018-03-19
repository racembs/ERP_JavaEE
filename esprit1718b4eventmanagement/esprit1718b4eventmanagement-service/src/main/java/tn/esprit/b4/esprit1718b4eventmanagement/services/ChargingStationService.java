package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;


@Stateless
public class ChargingStationService implements ChargingStationServiceLocal, ChargingStationServiceRemote {
	@PersistenceContext
	EntityManager em;
	@Override
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS) {
		
		
		ChargingStationPK ChSpk = new ChargingStationPK();
		ChSpk.setId_equipment(idEquipement);
		ChSpk.setIdUser(idUser);
		ChS.setWorkStationPK(ChSpk);
		em.persist(ChS);
		return ChSpk;
	}
	@Override
	public void deleteChargingStation(int idEquipement , int idUser) {
		
		ChargingStation Chs = findChargingStation(idEquipement, idUser);
		em.remove(Chs);
		
	}
	

	
	
	@Override
	public void updateChargingStation(int idEquipement, int idUser) {
		ChargingStation Chs = findChargingStation(idEquipement, idUser);
		Chs.setDescription("desc");
		
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


}
