package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class NeededItemService
 */
@Stateless
@LocalBean
public class NeededItemService extends GenericDAO<NeededItem> implements NeededItemServiceRemote, NeededItemServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public NeededItemService() {
        // TODO Auto-generated constructor stub
    	super(NeededItem.class);
    }

	@Override
	public int addNeededItem(NeededItem neededItem) {
		em.persist(neededItem);
		return neededItem.getId();
	}

}
