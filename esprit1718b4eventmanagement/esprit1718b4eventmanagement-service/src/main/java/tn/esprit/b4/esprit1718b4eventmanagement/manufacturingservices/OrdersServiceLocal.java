package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface OrdersServiceLocal extends IGenericDAO<Orders> {
	public int addOrders(Orders order);

}
