package tn.esprit.b4.esprit1718b4eventmanagement.services;


import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ReclamationPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ReclamationServiceLocal extends IGenericDAO<Reclamation> {
	public void Add(Reclamation cs);
	public ReclamationPk addReclamation(int idArticle, int idUser, Reclamation Rec);
}
