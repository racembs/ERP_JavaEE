package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface NeedNomenclatureServiceLocal extends IGenericDAO<NeedNomenclature> {
	public NeedNomenclature addnomenclature(int idParent,int idChild,int netNeed) ;

}
