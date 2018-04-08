package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Local
public interface ManufacturingPlanningServiceLocal extends IGenericDAO<ManufacturingPlanning> {
	public int manufacturingDuration(Article article,int quantity);
	public Date endingManufacturingDate(Date startingDate,long duration);
	public List<ManufacturingPlanning> ReadyManufacturingPlanning(Map<NeededItem, List<NeededItem>> map,Date startingDate);
	public void updateStatusToFinished();
	

}
