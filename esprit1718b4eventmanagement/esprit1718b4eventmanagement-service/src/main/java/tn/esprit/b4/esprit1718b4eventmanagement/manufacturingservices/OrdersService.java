package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
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
	
	@Override
	public void updateStatusOrder() {
		List<Orders> list = findAll();
		for (Orders orders : list) {
			Boolean state=true;
			if(!orders.getOrder_Item().isEmpty()){
				for (OrdredItem ordredItem : orders.getOrder_Item()) {
					if(ordredItem.getStatus().equals("Pending") || ordredItem.getStatus().equals("In progress")){
						state = false;
						return;
					}
				}
				if(state){
					orders.setStatut("finished");
					update(orders);
				}
					
			}
		}
	}

}
