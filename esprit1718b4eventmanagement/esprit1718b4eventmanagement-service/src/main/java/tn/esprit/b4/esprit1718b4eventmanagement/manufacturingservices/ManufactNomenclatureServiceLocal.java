package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ManufactNomenclatureServiceLocal extends IGenericDAO<ManufactOrderNomenclature> {
	public ManufactOrderNomenclature addnomenclature(int idParent,int idChild,int level);

}
