package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface OrderItemServiceLocal extends IGenericDAO<OrdredItem> {
	public OrdredItemPk addOrdredItem(int idOrder, int idArticle, OrdredItem ordredItem);
	public OrdredItem findOrdredItemById(int idOrder, int idArticle);
	public OrdredItem reatach(OrdredItem ordredItem);
	public List<OrdredItem> findItemsOfAnOrder(int idOrder);
	public void deleteAllByOrder(int idOrder);

}
