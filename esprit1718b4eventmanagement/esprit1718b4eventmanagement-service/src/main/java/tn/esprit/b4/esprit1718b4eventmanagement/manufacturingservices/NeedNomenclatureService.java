package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclaturePk;
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

}
