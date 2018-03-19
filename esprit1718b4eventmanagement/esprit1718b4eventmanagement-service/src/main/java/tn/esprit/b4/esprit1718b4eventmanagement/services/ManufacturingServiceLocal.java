package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;

@Local
public interface ManufacturingServiceLocal {
	
	public int addClient(Client client);
	public Client findClientById(int id);
	public int addOrders(Orders order);

}
