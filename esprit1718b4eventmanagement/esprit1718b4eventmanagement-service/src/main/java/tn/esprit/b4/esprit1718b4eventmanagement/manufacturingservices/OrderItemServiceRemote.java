package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface OrderItemServiceRemote extends IGenericDAO<OrdredItem> {
	public OrdredItemPk addOrdredItem(int idOrder, int idArticle, OrdredItem ordredItem);
	public OrdredItem findOrdredItemById(int idOrder, int idArticle);
	public OrdredItem reatach(OrdredItem ordredItem);

}
