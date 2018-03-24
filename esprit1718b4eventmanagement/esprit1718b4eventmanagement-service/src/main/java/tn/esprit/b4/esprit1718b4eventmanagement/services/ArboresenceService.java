package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;


@Stateless
public class ArboresenceService implements ArboresenceServiceLocal,ArboresenceServiceRemote{
	@PersistenceContext 
	EntityManager em;


	@Override
	public Arboresence findArboresence (int id) {
		Arboresence arbo =em.find(Arboresence.class,id);
		return arbo;
	}




	
}
