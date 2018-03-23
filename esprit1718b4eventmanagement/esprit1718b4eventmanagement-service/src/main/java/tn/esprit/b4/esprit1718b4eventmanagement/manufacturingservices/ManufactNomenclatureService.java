package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ManufactNomenclatureService
 */
@Stateless
@LocalBean
public class ManufactNomenclatureService extends GenericDAO<ManufactOrderNomenclature> implements ManufactNomenclatureServiceRemote, ManufactNomenclatureServiceLocal {

    /**
     * Default constructor. 
     */
	@PersistenceContext
	private EntityManager em;
    public ManufactNomenclatureService() {
        // TODO Auto-generated constructor stub
    	super(ManufactOrderNomenclature.class);
    }

	@Override
	public ManufactOrderNomenclature addnomenclature(int idParent, int idChild, int level) {
		ManufactOrderNomenclaturePk id = new ManufactOrderNomenclaturePk();
		id.setIdParent(idParent);
		id.setIdChild(idChild);
		ManufactOrderNomenclature nomenclature = new ManufactOrderNomenclature();
		nomenclature.setManufactOrderNomenclaturePk(id);
		nomenclature.setLevel(level);
		em.persist(nomenclature);
		return nomenclature;
	}

}
