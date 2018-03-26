package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface NeedNomenclatureServiceLocal extends IGenericDAO<NeedNomenclature> {
	public NeedNomenclature addnomenclature(int idParent,int idChild,int netNeed) ;
	public List<NeedNomenclature> SaveNeedItemTreeNomenclature(Map<NeededItem, List<NeededItem>> map);

}
