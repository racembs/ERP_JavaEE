package tn.esprit.b4.esprit1718b4eventmanagement.services;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;



@Remote
public interface EquipementServiceRemote {
	public Equipment findEquipementBySerie(String serie);
	public int addEquippement(Equipment equipement);
	public List<Equipment> findEquipementMarque(String marque );
	public List<Equipment> findEquipementFab(String type);
	public List<Equipment> getAllEquipment() ;
	public void updateEquipment(Equipment e) ;
	}
