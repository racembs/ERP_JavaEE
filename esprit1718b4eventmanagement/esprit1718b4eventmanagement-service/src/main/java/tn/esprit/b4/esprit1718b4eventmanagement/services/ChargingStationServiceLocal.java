package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;

@Local
public interface ChargingStationServiceLocal {
	
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS);
}
