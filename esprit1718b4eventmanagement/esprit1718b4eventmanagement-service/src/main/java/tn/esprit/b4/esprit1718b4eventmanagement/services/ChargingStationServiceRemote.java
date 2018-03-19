package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;

@Remote
public interface ChargingStationServiceRemote {
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS);


}
