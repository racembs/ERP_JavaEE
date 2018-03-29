package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ManufacturingPlanningService
 */
@Stateless
@LocalBean
public class ManufacturingPlanningService extends GenericDAO<ManufacturingPlanning> implements ManufacturingPlanningServiceRemote, ManufacturingPlanningServiceLocal {

    /**
     * Default constructor. 
     */
    public ManufacturingPlanningService() {
        // TODO Auto-generated constructor stub
    	super(ManufacturingPlanning.class);
    }

}
