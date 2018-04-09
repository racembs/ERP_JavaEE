package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.HashMap;
import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;

@Local
public interface ArboresenceServiceLocal {
	public Arboresence findArboresence(int id);
	public int addArbo(Arboresence arbo);
	public List<Arboresence> getAllArboresence();
	public void addArbo(int idArboPere, int idArboFils);
	public void updateArbo(int idArboPere, int idArboFils);
	public List<ArboPereFis> getFilsArbo(int idArboPere);
	public List<Arboresence> getPereArbo(String type);
	public Arboresence getArbo(String name) ;
	public List<Equipment> DisplayEquipmentbyarbo(Arboresence idArbo);
	List<Arboresence> verifAllArboresence(String name);
	public void updateArbo(Arboresence e) ;
	public void DeleteArbo(int idArbo);
	List<ArboPereFis> getpereArbo(int idfils) ;
}
