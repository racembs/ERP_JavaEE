package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;


import java.util.List;
import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface WorkPrevServiceRemote {
	public void addWP(PreventiveWork w);
	public List<PreventiveWork> DisplayPWorks();
	public List<PreventiveWork> displayWPbyTech(int idtech);
	 public List<PreventiveWork> searchPreventiveWork(String input);
		public void remove(int idw);
		public void update(PreventiveWork w);
	
		
}
