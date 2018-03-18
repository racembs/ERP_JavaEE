package tn.esprit.b4.esprit1718b4eventmanagement.services;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;

public interface WorksUsServiceRemote {
	public void addWR(UsualWork  w);
	public void addWO(UsualWork w);
	public void updateWork(UsualWork w);
	public void deleteWork(WorksPK idw);
	public UsualWork displayWRB();
}
