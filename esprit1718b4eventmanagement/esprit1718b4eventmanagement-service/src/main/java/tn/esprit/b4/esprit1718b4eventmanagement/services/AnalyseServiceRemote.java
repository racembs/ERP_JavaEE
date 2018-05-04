package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;

@Remote
public interface AnalyseServiceRemote {
	public Long count();
	public Long Date(String datedeb,String datefin);
	 public List<UsualWork> ListWorks(Integer idequi);
	 public Long nbrDayDate(Date startingDate,Date endingDate);
		public Long MTBF(Integer idequi);
		public Long MTTR(Integer idequi);
		public Long countWorks(Integer idequi) ;
		public Long Availibitity(Long MTTR,Long MTBF);
}
