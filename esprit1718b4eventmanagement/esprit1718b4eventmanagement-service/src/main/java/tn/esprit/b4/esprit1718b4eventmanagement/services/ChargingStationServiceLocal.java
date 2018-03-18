package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;

@Local
public interface ChargingStationServiceLocal {
	public int ajouterChargStation(ChargingStation chs);
}
