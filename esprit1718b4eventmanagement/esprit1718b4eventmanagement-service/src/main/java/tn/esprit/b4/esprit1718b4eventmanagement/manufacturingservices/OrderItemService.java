package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
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
	public OrdredItem mergeOrdredItem(int idOrder, int idArticle, OrdredItem orderItem) {
		OrdredItemPk ordredItemPk = new OrdredItemPk();
		ordredItemPk.setId_Article(idArticle);
		ordredItemPk.setId_Order(idOrder);
		orderItem.setOrdredItemPk(ordredItemPk);
		em.merge(orderItem);
		return orderItem;
	}
	
	@Override
	public OrdredItem reatach(OrdredItem ordredItem) {
		return em.find(OrdredItem.class, ordredItem.getOrdredItemPk());
	}

	@Override
	public List<OrdredItem> findItemsOfAnOrder(int idOrder) {
		TypedQuery<OrdredItem> query
		=em.createQuery("SELECT o FROM OrdredItem o WHERE o.ordredItemPk.id_Order =:idOrder", OrdredItem.class);
		query.setParameter("idOrder",idOrder);
		List<OrdredItem> list=query.getResultList();
		return list;
	}

	@Override
	public void deleteAllByOrder(int idOrder) {
		Query query = em.createQuery("DELETE FROM OrdredItem o WHERE o.ordredItemPk.id_Order =:idOrder");
		int deletedCount = query.setParameter("idOrder",idOrder).executeUpdate();
	}

	@Override
	public List<OrdredItem> findPendingItemsOfAnOrder(int idOrder) {
		TypedQuery<OrdredItem> query
		=em.createQuery("SELECT o FROM OrdredItem o WHERE o.ordredItemPk.id_Order =:idOrder AND o.ManufacturingList IS EMPTY", OrdredItem.class);
		query.setParameter("idOrder",idOrder);
		List<OrdredItem> list=query.getResultList();
		return list;
	}

	@Override
	public void updateStatusOrdredItem() {
		List<OrdredItem> list = findAll();
		for (OrdredItem ordredItem : list) {
			Boolean state=true;
			if(!ordredItem.getManufacturingList().isEmpty()){
				ordredItem.setStatus("In progress");
				for (NeededItem neededItem : ordredItem.getManufacturingList()) {
					if(neededItem.getStatus().equals("Pending")){
						state = false;
						update(ordredItem);
						return;
					}
				}
				if(state){
					ordredItem.setStatus("finished");
					update(ordredItem);
				}
			}
		}
	}



	

}
