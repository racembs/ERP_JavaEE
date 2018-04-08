package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface NeededItemServiceRemote extends IGenericDAO<NeededItem> {
	public int addNeededItem(NeededItem neededItem);
	public Map<NeededItem, NeedNomenclature> addChildrenNeededItem(NeededItem neededItem);
	public Map<NeededItem, List<NeededItem>> CreateNeedItemTree(NeededItem ParentneededItem);
	public Map<NeededItem, List<NeededItem>> SaveNeedItemTree(Map<NeededItem, List<NeededItem>> map);
	public Map<NeededItem, List<NeededItem>> InitialiseMap();
	public Map<NeededItem, List<NeededItem>> findNeededItemTreeByOrdredItem(NeededItem ParentneededItem);
	public List<NeededItem> NeedItemList(Map<NeededItem, List<NeededItem>> map);
	public NeededItem getNeededItemParentOfOrdredItem(int idOrder, int idArticle);
	public int CheckReadyLot(NeededItem Parent);

}
