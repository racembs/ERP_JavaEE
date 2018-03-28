package tn.esprit.b4.esprit1718b4eventmanagement.services;


import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;

@Remote
public interface MvtApprovServiceRemote {
	public int addMvtApprov(MvtApprov mbtApprov);
	public void updateMvtApprov(MvtApprov mbtApprov);
	public MvtApprov  findMvtApprovById(int idMvtApprov);
	public List<MvtApprov> getAllOrders();
	public MvtApprov getOrderByArticle(int id);

}
