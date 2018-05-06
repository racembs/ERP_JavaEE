package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Reclamation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ReclamationPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

@LocalBean
@Stateless
public class ReclamationService extends GenericDAO<Reclamation> implements ReclamationServiceLocal, ReclamationServiceRemote {
	
	@PersistenceContext
	EntityManager em;
	public ReclamationService() {
		super(Reclamation.class);
		// TODO Auto-generated constructor stub
	}

public void Add(Reclamation cs)
{
	em.persist(cs);
	
}


@Override
public ReclamationPk addReclamation(int idArticle, int idUser, Reclamation Rec) {
		
	ReclamationPk Rpk = new ReclamationPk();
	Rpk.setId(idArticle);
	Rpk.setIdUser(idUser);
	Rec.setReclamationPK(Rpk);
	em.persist(Rec);
	return Rpk;
}
}
