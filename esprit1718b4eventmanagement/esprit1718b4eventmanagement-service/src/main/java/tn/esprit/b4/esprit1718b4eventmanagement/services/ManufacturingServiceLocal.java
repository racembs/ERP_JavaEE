package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrderPk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;

@Local
public interface ManufacturingServiceLocal {
	
	public int addClient(Client client);
	public Client findClientById(int id);
	public int addOrders(Orders order);
	public ManufacturingOrderPk addManufactOrder(int idCommande, int idArticle, ManufacturingOrder manuf);
	public ManufacturingOrder findManufactOrderById(int idOrder, int idArticle);
	public ManufacturingOrderPk addManufactChild(int idArticleFils,int quantité,ManufacturingOrderPk manuPk);

}
