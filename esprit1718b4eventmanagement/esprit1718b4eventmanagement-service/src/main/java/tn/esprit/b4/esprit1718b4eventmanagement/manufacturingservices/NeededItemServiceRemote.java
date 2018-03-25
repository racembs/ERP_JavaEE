package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface NeededItemServiceRemote extends IGenericDAO<NeededItem> {
	public int addNeededItem(NeededItem neededItem);

}
