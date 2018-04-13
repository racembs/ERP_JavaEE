package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;

import javax.ejb.Local;
import java.util.List;



@Local
public interface WorksUsServiceLocal {
	public void addWR(UsualWork  w);
	public void addWO(UsualWork w);
	public void updateWork(UsualWork w);
	public void deleteWork(int idw);
	public List<UsualWork> displayWRB();
	//public Equipment findById(int id);
	public UsualWork findById(int idW);
	public List<Works> DisplayUSWorks();
	public List<UsualWork> displayWO();
	public List<UsualWork> displayWObyTech(int idtech);
	public List<UsualWork> displayWObyTechStart(int idtech);
	public List<UsualWork> displayStart();
	public List<UsualWork> displayDone();
	
}
