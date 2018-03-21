package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ManufacturingServiceLocal {
	
	public int addClient(Client client);
	public Client findClientById(int id);
	public int addOrders(Orders order);
	public OrdredItemPk addOrdredItem(int idOrder, int idArticle, OrdredItem ordredItem);
	public int addManufactOrder(ManufacturingOrder manufacturingOrder);
	public OrdredItemPk updateOrdredItem(OrdredItem ordredItem);
	public OrdredItem findOrdredItemById(int idOrder, int idArticle);
	public ManufacturingOrder addManufactChild(ManufacturingOrder ManufFadher);
	public OrdredItem reatach(OrdredItem ordredItem);
	

}
