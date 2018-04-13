package tn.esprit.b4.esprit1718b4eventmanagement.services;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.PreventiveWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;

import javax.ejb.Local;
import java.util.List;



@Local
public interface WorkPrevServiceLocal {
	public void addWP(PreventiveWork w);
	public List<PreventiveWork> DisplayPWorks();
	public List<PreventiveWork> displayWPbyTech(int idtech);
	 public List<PreventiveWork> searchPreventiveWork(String input);
		public void remove(int idw);
		public void update(PreventiveWork w);
}
