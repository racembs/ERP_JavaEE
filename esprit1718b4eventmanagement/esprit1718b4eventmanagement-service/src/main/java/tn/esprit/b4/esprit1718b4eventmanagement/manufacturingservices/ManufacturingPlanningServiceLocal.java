package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ManufacturingPlanningServiceLocal extends IGenericDAO<ManufacturingPlanning> {

}
