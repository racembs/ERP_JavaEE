package tn.esprit.b4.esprit1718b4eventmanagement.services;


import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ReclamationPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ReclamationServiceLocal extends IGenericDAO<Reclamation> {
	public void Add(Reclamation cs);
	public ReclamationPk addReclamation(int idArticle, int idUser, Reclamation Rec);
	public Long findReclamation(String subject);
}
