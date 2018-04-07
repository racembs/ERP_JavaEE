package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.Date;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ManufacturingPlanningServiceRemote extends IGenericDAO<ManufacturingPlanning> {

	public long manufacturingDuration(Article article,int quantity);
	public Date endingManufacturingDate(Date startingDate,long duration);
	
}
