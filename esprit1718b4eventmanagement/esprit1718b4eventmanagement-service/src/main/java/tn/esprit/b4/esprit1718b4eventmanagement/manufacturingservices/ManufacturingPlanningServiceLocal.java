package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.Date;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ManufacturingPlanningServiceLocal extends IGenericDAO<ManufacturingPlanning> {
	public long manufacturingDuration(Article article);
	public Date endingManufacturingDate(Date startingDate,long duration);
	

}
