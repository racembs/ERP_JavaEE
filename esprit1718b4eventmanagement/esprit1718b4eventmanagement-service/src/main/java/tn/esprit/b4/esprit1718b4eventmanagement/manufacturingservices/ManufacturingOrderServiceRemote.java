package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ManufacturingOrderServiceRemote extends IGenericDAO<ManufacturingOrder> {
	public int addManufactOrder(ManufacturingOrder manufacturingOrder);

}
