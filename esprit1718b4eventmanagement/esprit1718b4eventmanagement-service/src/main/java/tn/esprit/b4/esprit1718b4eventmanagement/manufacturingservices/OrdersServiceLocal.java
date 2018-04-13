package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface OrdersServiceLocal extends IGenericDAO<Orders> {
	public int addOrders(Orders order);
	public List<Orders> findOrdersByClient(int idClient);
	public void updateStatusOrder();

}
