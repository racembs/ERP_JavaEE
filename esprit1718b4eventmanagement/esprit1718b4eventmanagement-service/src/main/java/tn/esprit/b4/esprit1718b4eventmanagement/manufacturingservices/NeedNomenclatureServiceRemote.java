package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface NeedNomenclatureServiceRemote extends IGenericDAO<NeedNomenclature> {
	public NeedNomenclature addnomenclature(int idParent,int idChild,int netNeed) ;

}
