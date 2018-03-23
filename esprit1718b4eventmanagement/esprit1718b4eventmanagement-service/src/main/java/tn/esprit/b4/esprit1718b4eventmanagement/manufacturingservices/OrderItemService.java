package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class OrderItemService
 */
@Stateless
@LocalBean
public class OrderItemService extends GenericDAO<OrdredItem> implements OrderItemServiceRemote, OrderItemServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public OrderItemService() {
    	super(OrdredItem.class);
    }

	@Override
	public OrdredItemPk addOrdredItem(int idOrder, int idArticle, OrdredItem ordredItem) {
		OrdredItemPk ordredItemPk = new OrdredItemPk();
		ordredItemPk.setId_Article(idArticle);
		ordredItemPk.setId_Order(idOrder);
		ordredItem.setOrdredItemPk(ordredItemPk);
		em.persist(ordredItem);
		return ordredItemPk;
	}

	@Override
	public OrdredItem findOrdredItemById(int idOrder, int idArticle) {
		OrdredItemPk ordredItemPk = new OrdredItemPk();
		ordredItemPk.setId_Article(idArticle);
		ordredItemPk.setId_Order(idOrder);
		return em.find(OrdredItem.class, ordredItemPk);
	}

	@Override
	public OrdredItem reatach(OrdredItem ordredItem) {
		return em.find(OrdredItem.class, ordredItem.getOrdredItemPk());
	}

}
