package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class OrdersService
 */
@Stateless
@LocalBean
public class OrdersService extends GenericDAO<Orders> implements OrdersServiceRemote, OrdersServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public OrdersService() {
    	
    	super(Orders.class);
    }

	@Override
	public int addOrders(Orders order) {
		em.persist(order);
		return order.getId();
	}

	@Override
	public List<Orders> findOrdersByClient(int idClient) {
		TypedQuery<Orders> query
		=em.createQuery("SELECT o FROM Orders o WHERE o.client.id =:idClient", Orders.class);
		query.setParameter("idClient",idClient);
		List<Orders> list=query.getResultList();
		return list;
	}
	
	

}
