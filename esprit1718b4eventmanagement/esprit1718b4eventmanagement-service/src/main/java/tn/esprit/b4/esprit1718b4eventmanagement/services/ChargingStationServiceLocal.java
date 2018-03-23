package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;

@Local
public interface ChargingStationServiceLocal {
	
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS);
	
	public void deleteChargingStation(int idEquipement , int idUser);
	
	public void updateChargingStation(int idEquipement, int idUser);
	
	public ChargingStation findChargingStation(int idEquipement, int idUser);
	public ChargingStation findChargingStationByEquipement(int idEquipement);
	public ChargingStation findChargingStationByUser(int idUser);
	
	public List<ChargingStation> DisplayChargingStation();
}
