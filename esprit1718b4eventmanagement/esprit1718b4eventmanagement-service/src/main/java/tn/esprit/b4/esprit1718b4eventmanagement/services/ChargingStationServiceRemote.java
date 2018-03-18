package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;

@Remote
public interface ChargingStationServiceRemote {
	public int ajouterChargStation(ChargingStation chs);

}
