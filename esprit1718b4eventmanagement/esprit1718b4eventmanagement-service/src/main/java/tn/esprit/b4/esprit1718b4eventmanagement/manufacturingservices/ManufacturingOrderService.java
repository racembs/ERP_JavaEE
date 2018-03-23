package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ManufacturingOrderService
 */
@Stateless
@LocalBean
public class ManufacturingOrderService extends GenericDAO<ManufacturingOrder> implements ManufacturingOrderServiceRemote, ManufacturingOrderServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public ManufacturingOrderService() {
        // TODO Auto-generated constructor stub
    	super(ManufacturingOrder.class);
    }

	@Override
	public int addManufactOrder(ManufacturingOrder manufacturingOrder) {
		em.persist(manufacturingOrder);
		return manufacturingOrder.getId();
	}

}
