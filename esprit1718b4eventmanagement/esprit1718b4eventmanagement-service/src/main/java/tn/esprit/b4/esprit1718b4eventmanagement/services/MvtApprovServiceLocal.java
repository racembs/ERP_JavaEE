package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Local;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.MvtApprov;

@Local
public interface MvtApprovServiceLocal {
	public int addMvtApprov(MvtApprov mbtApprov);
	public void updateMvtApprov(MvtApprov mbtApprov);
	public MvtApprov  findMvtApprovById(int idMvtApprov);
	public List<MvtApprov> getAllOrders();
	public MvtApprov getOrderByArticle(int id);

}
