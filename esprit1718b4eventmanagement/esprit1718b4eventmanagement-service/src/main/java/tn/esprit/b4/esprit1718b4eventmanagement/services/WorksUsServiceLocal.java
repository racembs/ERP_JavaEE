package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import javax.ejb.Local;
import java.util.List;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface WorksUsServiceLocal {
	public void addWR(UsualWork  w);
	public void addWO(UsualWork w);
	public void updateWork(UsualWork w);
	public void deleteWork(WorksPK idw);
	public List<Works> displayWRB();
	//public Equipment findById(int id);
	public List<Works> DisplayUSWorks();
}
