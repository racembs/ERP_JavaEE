package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;

@Remote
public interface MvtApprovServiceRemote {
	public int addMvtApprov(MvtApprov mbtApprov);
	public void updateMvtApprov(MvtApprov mbtApprov);
	public MvtApprov  findMvtApprovById(int idMvtApprov);

}
