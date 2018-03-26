package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class NeedNomenclatureService
 */
@Stateless
@LocalBean
public class NeedNomenclatureService extends GenericDAO<NeedNomenclature> implements NeedNomenclatureServiceRemote, NeedNomenclatureServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public NeedNomenclatureService() {
        // TODO Auto-generated constructor stub
    	super(NeedNomenclature.class);
    }

	@Override
	public NeedNomenclature addnomenclature(int idParent,int idChild,int netNeed) {
		NeedNomenclaturePk id = new NeedNomenclaturePk();
		id.setIdParent(idParent);
		id.setIdChild(idChild);
		NeedNomenclature nomenclature = new NeedNomenclature();
		nomenclature.setNeedNomenclaturePk(id);
		nomenclature.setQuantity(netNeed);
		em.persist(nomenclature);
		return nomenclature;
	}
	
	@Override
	public List<NeedNomenclature> SaveNeedItemTreeNomenclature(Map<NeededItem, List<NeededItem>> map) {
		List <NeedNomenclature> list = new ArrayList<>();
		for (Map.Entry<NeededItem, List<NeededItem>> entry1 : map.entrySet()) {
			if(!entry1.getValue().isEmpty()){
				for (NeededItem ChildNeededItem : entry1.getValue()) {
					list.add(addnomenclature(entry1.getKey().getId()
							, ChildNeededItem.getId(), ChildNeededItem.getNetNeed()));
				}
			}
		}
		return list;
	}
}
