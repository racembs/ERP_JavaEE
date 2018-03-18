package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;

@Stateless
public class ChargingStationService implements ChargingStationServiceLocal, ChargingStationServiceRemote {
EntityManager em;
	@Override
	public int ajouterChargStation(ChargingStation chs) {
		em.persist(chs);
		return chs.getCode();
	}

}
