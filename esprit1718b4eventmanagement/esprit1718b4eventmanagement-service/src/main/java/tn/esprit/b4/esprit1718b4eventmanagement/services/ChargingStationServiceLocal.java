package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ChargingStationServiceLocal extends IGenericDAO<ChargingStation> {
	
	public ChargingStationPK addChargingStation(int idEquipement, int idUser, ChargingStation ChS);
	
	public void deleteChargingStation(int idEquipement , int idUser);
	
	public void updateChargingStation(int idEquipement, int idUser, ChargingStation ChS);
	
	public ChargingStation findChargingStation(int idEquipement, int idUser);
	public ChargingStation findChargingStationByEquipement(int idEquipement);
	public ChargingStation findChargingStationByUser(int idUser);
	
	public List<ChargingStation> DisplayChargingStation();
	public List<ChargingStation> findd(String code);
}
