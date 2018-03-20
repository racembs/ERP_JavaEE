package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;

@Local
public interface MvtApprovServiceLocal {
	public int addMvtApprov(MvtApprov mbtApprov);
	public void updateMvtApprov(MvtApprov mbtApprov);
	public MvtApprov  findMvtApprovById(int idMvtApprov);

}
