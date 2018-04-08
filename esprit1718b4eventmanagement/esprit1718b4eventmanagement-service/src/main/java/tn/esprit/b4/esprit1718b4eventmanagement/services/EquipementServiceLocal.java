package tn.esprit.b4.esprit1718b4eventmanagement.services;



import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;



@Local
public interface EquipementServiceLocal {
	public Equipment findEquipementBySerie(String serie);
	public int addEquippement(Equipment equipement);
	public List<Equipment> findEquipementMarque(String marque );
	public List<Equipment> findEquipementFab(String type);
	public List<Equipment> getAllEquipment() ;
	public void updateEquipment(Equipment e) ;
	public List<Equipment> searchEquipment(String input);
	public List<Equipment> DisplayEquipment();
	public Equipment findEqupment(int idArbo);
	public void DeleteEqupment(int idArbo);
	
	

}
