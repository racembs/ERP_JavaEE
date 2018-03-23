package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ManufactNomenclatureServiceRemote extends IGenericDAO<ManufactOrderNomenclature> {
	public ManufactOrderNomenclature addnomenclature(int idParent,int idChild,int level);

}
