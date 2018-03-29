package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ManufacturingPlanningServiceRemote extends IGenericDAO<ManufacturingPlanning> {

}
