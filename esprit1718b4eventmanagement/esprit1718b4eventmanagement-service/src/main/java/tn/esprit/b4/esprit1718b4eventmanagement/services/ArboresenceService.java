package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFisPk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
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

	@Override
	public int addArbo(Arboresence arbo) {
		em.persist(arbo);
		return arbo.getId();
		}
	@Override
	public List<Arboresence> getAllArboresence() {
		TypedQuery<Arboresence> query
		=em.createQuery("select n from Arboresence n", Arboresence.class);
		
		List<Arboresence> equi=query.getResultList();
		return equi;
	
	}

	@Override
	public void addArbo(int idArboPere, int idArboFils) {
		ArboPereFis arbo =new ArboPereFis();
		ArboPereFisPk arboPK =new ArboPereFisPk();
		arboPK .setIdArboFils(idArboFils);
		arboPK.setIdArboPere(idArboPere);
		arbo.setArboperefilsPk(arboPK );
		
		em.persist(arbo);
		
		
	}
	
	
	@Override
	public void updateArbo(int idArboPere, int idArboFils)  {
		ArboPereFis arbo =new ArboPereFis();
		ArboPereFisPk arboPK =new ArboPereFisPk();
		arboPK .setIdArboFils(idArboFils);
		arboPK.setIdArboPere(idArboPere);
		arbo.setArboperefilsPk(arboPK );
		
		em.merge(arbo);
	}
	
	
	

	@Override
	public List<ArboPereFis> getFilsArbo(int idArboPere) {
		TypedQuery<ArboPereFis> query
		=em.createQuery("select n from ArboPereFis n where n.arboperefilsPk.idArboPere=:idPere", ArboPereFis.class);
		query.setParameter("idPere", idArboPere);
		List<ArboPereFis> arbo=query.getResultList();
		return arbo;
		
	}
	
	
	
}
