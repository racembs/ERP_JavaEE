package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.ejb.Stateless;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.lowagie.text.Table;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFis;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ArboPereFisPk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Arboresence;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;


@Stateless
public class AnalyseService implements AnalyseServiceLocal,AnalyseServiceRemote{
	@PersistenceContext 
	EntityManager em;

	@Override
	public Long count() {
		TypedQuery<Long> query
		=em.createQuery("SELECT COUNT(c) FROM Equipment c",Long.class);

		Long train=query.getSingleResult();
		return train;
	}
	
	@Override
	public Long Date(String datedeb,String datefin) {
		TypedQuery<Long> query
		=em.createQuery("SELECT COUNT(c) FROM Works c WHERE c.startDate <=STR_TO_DATE('" + datedeb + "', '%d/%m/%Y') "
	                + "and c.endDate >=STR_TO_DATE('" + datefin + "', '%d/%m/%Y')",Long.class);

		Long train=query.getSingleResult();
		return train;
	}
	@Override
	  public List<UsualWork> ListWorks(Integer idequi){
	    	TypedQuery<UsualWork> query = em.createQuery("SELECT a FROM UsualWork a WHERE a.equipement.Id =:input",UsualWork.class);
	    	query.setParameter("input",idequi);
	    	List<UsualWork> results = query.getResultList();
	    	return results;
	    }
	  @Override
		public Long nbrDayDate(Date startingDate,Date endingDate)
		{
		  long différence = Math.abs(startingDate.getTime()-endingDate.getTime()); //calcul différence entre les deux dates
			
		return différence/(1000 * 60 * 60 * 24);}
	 
	  @Override
		public Long countWorks(Integer idequi) {
			TypedQuery<Long> query
			=em.createQuery("SELECT COUNT(c) FROM UsualWork c WHERE a.equipement.Id =:input",Long.class);
	    query.setParameter("input",idequi);
			Long train=query.getSingleResult();
			return train;
		}
	  @Override
		public Long MTBF(Integer idequi)
		
		{ long MTBF=0;
		Integer i1=0;
		ArrayList< Date > list = new ArrayList<>();

	
		  List<UsualWork> wr=ListWorks(idequi);
		  for(UsualWork i: wr)
		  {
			  
			 list.add(i.getStartDate());
		  i1=i1+1;
			 
			
			  
		  }
			if ((i1==0)||(i1==1)){
				return MTBF;
				
			}
			else
			{
		  for(int j=0;j<list.size();j++)
		  {
		  if(j+1==list.size())
		  {
			  return MTBF;
			  
		  }
		  else
		  {			  MTBF=MTBF+nbrDayDate(list.get(j),list.get(j+1));}
		
		  }
		  
			
			}
			return MTBF;
			
	}
	  
	  
	  @Override
			public Long MTTR(Integer idequi)
			{
			long MTTR=0;
		
	  List<UsualWork> wr=ListWorks(1);
Integer i1=0;
	
	ArrayList< Date > liststar = new ArrayList<>();
	ArrayList< Date > listend = new ArrayList<>();
	
	  for(UsualWork i: wr)
	  {listend.add(i.getEndDate());
	  i1=i1+1;}
	  for(UsualWork i: wr)
	  {liststar.add(i.getStartDate());}
		if ((i1==0)||(i1==1)){
			 return MTTR;
		}
		else
		{
	  for(int j=0;j<listend.size();j++)
	  {
	  if(j+1==listend.size())
	  {
		 
		  return MTTR;
		  
	  }
	  else
	  { 
	  MTTR=MTTR+nbrDayDate(listend.get(j),liststar.get(j+1));
	
	  }
	  }
	  }
	 return MTTR;
			
	  }
	  @Override
		public Long Availibitity(Long MTTR,Long MTBF)
		{Long AVB=MTBF/(MTBF+MTTR);
	return AVB;
	  }
	  

}
  

