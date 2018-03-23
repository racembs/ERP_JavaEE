package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
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
        // TODO Auto-generated constructor stub
    	super(Orders.class);
    }

	@Override
	public int addOrders(Orders order) {
		em.persist(order);
		return order.getId();
	}

}
