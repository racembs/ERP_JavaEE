package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrderPk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;

@Stateless
public class ManufacturingService implements ManufacturingServiceLocal, ManufacturingServiceRemote {
	@PersistenceContext
	EntityManager em;

	@Override
	public int addClient(Client client) {
		em.persist(client);
		return client.getId();
	}

	@Override
	public Client findClientById(int id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	@Override
	public int addOrders(Orders order) {
		em.persist(order);
		return order.getId();
	}

	@Override
	public ManufacturingOrderPk addManufactOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}