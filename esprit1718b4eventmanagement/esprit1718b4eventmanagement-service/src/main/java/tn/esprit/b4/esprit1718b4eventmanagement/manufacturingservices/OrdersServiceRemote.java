package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface OrdersServiceRemote extends IGenericDAO<Orders> {
	public int addOrders(Orders order);
	public List<Orders> findOrdersByClient(int idClient);
	public void updateStatusOrder();

}
