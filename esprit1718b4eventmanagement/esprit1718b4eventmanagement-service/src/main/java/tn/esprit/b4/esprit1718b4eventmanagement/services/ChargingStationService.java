package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

}
