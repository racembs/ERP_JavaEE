package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface ManufacturingPlanningServiceRemote extends IGenericDAO<ManufacturingPlanning> {

	public int addManufacturingPlanning(ManufacturingPlanning manuf);
	public int manufacturingDuration(Article article,int quantity);
	public Date endingManufacturingDate(Date startingDate,long duration,int hourlyPost);
	public List<ManufacturingPlanning> ReadyManufacturingPlanning(Map<NeededItem, List<NeededItem>> map,Date startingDate,int hourlyPost);
	public void updateStatusToFinished();
	public List<ManufacturingPlanning> displayManufactOfAnOrdredItem(int idOrder, int idArticle);
	public int updateIfOneNeededItem(NeededItem neededItem);
	public List<ManufacturingPlanning> AfterDeliveryManufacturingPlanning(Map<NeededItem, List<NeededItem>> map, int hourlyPost);

	public List<ManufacturingPlanning> DisplayManufacturingPlanning();
	
	public Map<NeededItem, List<NeededItem>> stakingLaterScheduling(NeededItem ParentneededItem,Date DeliveryDate,int hourlyPost);
	public Date startingManufacturingDate(Date endingDate,long duration,int hourlyPost);
	public List<ManufacturingPlanning> ReadyManufacturingPlanningWithoutSaving(Map<NeededItem, List<NeededItem>> map,int hourlyPost);
}
