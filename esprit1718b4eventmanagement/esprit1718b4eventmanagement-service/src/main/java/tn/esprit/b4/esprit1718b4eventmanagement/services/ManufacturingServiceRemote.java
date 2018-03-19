package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrderPk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;

@Remote
public interface ManufacturingServiceRemote {
	
	public int addClient(Client client);
	public Client findClientById(int id);
	public int addOrders(Orders order);
	public ManufacturingOrderPk addManufactOrder();

}
