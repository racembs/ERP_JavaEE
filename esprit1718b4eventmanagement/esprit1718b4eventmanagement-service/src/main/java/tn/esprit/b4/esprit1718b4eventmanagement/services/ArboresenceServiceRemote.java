package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;

@Remote
public interface ArboresenceServiceRemote {
	public Arboresence findArboresence(int id);
	public int addArbo(Arboresence arbo);
	public List<Arboresence> getAllArboresence();
	public void addArbo(int idArboPere, int idArboFils);
	public void updateArbo(int idArboPere, int idArboFils);
	public List<ArboPereFis> getFilsArbo(int idArboPere);
	public List<Arboresence> getPereArbo(String type);
	public Arboresence getArbo(String name) ;
}
