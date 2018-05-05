package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;


import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ReclamationPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ReclamationServiceRemote extends IGenericDAO<Reclamation> {
	public void Add(Reclamation cs);
	public ReclamationPk addReclamation(int idEquipement, int idUser, Reclamation Rec);
}
