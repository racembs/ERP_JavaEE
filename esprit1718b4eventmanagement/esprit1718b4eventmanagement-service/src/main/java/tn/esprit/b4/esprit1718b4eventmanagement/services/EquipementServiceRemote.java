package tn.esprit.b4.esprit1718b4eventmanagement.services;



import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;



@Remote
public interface EquipementServiceRemote {
	public List<Equipment> findEquipementSerialNum(String serie );
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
	public Long Date(String datedeb,String datefin) ;
	public Long countequi();
	}
