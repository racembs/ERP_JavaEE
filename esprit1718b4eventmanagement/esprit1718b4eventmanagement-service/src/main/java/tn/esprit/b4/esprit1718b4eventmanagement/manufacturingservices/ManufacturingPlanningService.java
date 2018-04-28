package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingPlanning;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class ManufacturingPlanningService
 */
@Stateless
@LocalBean
public class ManufacturingPlanningService extends GenericDAO<ManufacturingPlanning>
		implements ManufacturingPlanningServiceRemote, ManufacturingPlanningServiceLocal {

	@EJB
	NeededItemServiceLocal needItem;
	
	@EJB
	NeedNomenclatureServiceLocal needNom;
	
	@EJB
	ArticleServiceLocal articleServ;
	
	@EJB
	ManufacturingPlanningServiceLocal mmm;
	
	@PersistenceContext
	private EntityManager em;

	/**
	 * Default constructor.
	 */
	public ManufacturingPlanningService() {
		// TODO Auto-generated constructor stub
		super(ManufacturingPlanning.class);
	}

	@Override
	public int addManufacturingPlanning(ManufacturingPlanning manuf) {
		em.persist(manuf);
		return manuf.getId();
	}
	
	@Override
	public int manufacturingDuration(Article article, int quantity) {
		int duration = 0;
		double calcule = 0;
		// liste of operating range
		List<OperatingRange> listOp = article.getOperatingranges();
		Set<OperatingRange> setOp = new HashSet<>(listOp);
		for (OperatingRange operatingRange : setOp) {
			List<Operation> List = operatingRange.getOperations();
			Set<Operation> set = new HashSet<>(List);
			for (Operation operation : set) {
				calcule = ((double) quantity / operation.getUnitproductiontime()) * 60;
				duration = duration + ((int) calcule);
			}
		}
		return duration;
	}

	@Override
	public Date endingManufacturingDate(Date startingDate,long duration,int hourlyPost) {
		if(duration!=0){
			float hourNbr = duration / 60;
			float dayNbr = hourNbr / (hourlyPost*8);
			int length = (int) Math.round(dayNbr + 0.5);
			Calendar c = Calendar.getInstance();
			c.setTime(startingDate);
			long startMillis = c.getTimeInMillis();
			long endMillis = 0;
			if(hourlyPost==3){
				endMillis = startMillis + duration*60*1000;
			} else {
				// if it's a work doesn't exceed 1 day
				if ((length - 1) == 1) {
					long testEndMillis = startMillis + duration * 60 * 1000;
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testEndMillis % (86400000));
					if(rest< 25200000)
						rest=rest+24*60*60*1000;
					if (rest >= endTime) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						endMillis = testEndMillis + jumpTime;
					} else {
						endMillis = testEndMillis;
					}
				} else {
					long newDuration = duration - (length - 1) * 8*hourlyPost * 60;
					long newStartMillis = startMillis + (length - 1) * 24 * 60 * 60 * 1000;

					long testEndMillis = newStartMillis + newDuration * 60 * 1000;
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testEndMillis % (86400000));
					if(rest< 25200000)
						rest=rest+24*60*60*1000;
					if (rest >= endTime) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						endMillis = testEndMillis + jumpTime;
					} else {
						endMillis = testEndMillis;
					}
				}
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(endMillis);

			Date endingDate = calendar.getTime();
			return endingDate;
		} else {
			return startingDate;
		}
		
	}
	
	public Nomenclature findNomenclatureByParentChild(Article Parent, Article Child){
		TypedQuery<Nomenclature> query
		=em.createQuery("select n from Nomenclature n where n.articlePere=:Parent AND "
				+ "n.articleFils=:Child " , Nomenclature.class);
		query.setParameter("Parent", Parent);
		query.setParameter("Child", Child);
		Nomenclature nomenclature=query.getSingleResult();
		return nomenclature;
		
	}

	@Override
	public List<ManufacturingPlanning> ReadyManufacturingPlanning(Map<NeededItem, List<NeededItem>> map,Date startingDate,int hourlyPost) {
		List<ManufacturingPlanning> listMan = new ArrayList<>();
		for (Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()) {
			if(!e.getValue().isEmpty() && e.getKey().getNetNeed()!=0){
				int readyLot=0;
				readyLot = needItem.CheckReadyLot(e.getKey(), e.getValue());
				//creating manufacturing planning of the needed Item with quantity of readyLot
				ManufacturingPlanning manuf = new ManufacturingPlanning(readyLot,startingDate,"in progress",e.getKey());
				int duration = mmm.manufacturingDuration(e.getKey().getNeeded_article(),readyLot);
				Date ending = mmm.endingManufacturingDate(startingDate,duration,hourlyPost);
				manuf.setDuration(duration);
				manuf.setEndingDate(ending);
				em.persist(manuf);
				listMan.add(manuf);
				e.getKey().getManufacturingPlanning().add(manuf);
				Set<NeededItem> set = new HashSet<>(e.getValue());
				for (NeededItem child : set) {
					Nomenclature nom = findNomenclatureByParentChild(e.getKey().getNeeded_article(), child.getNeeded_article());
					int reserved = child.getNeeded_article().getReservedQuantity();
					reserved = reserved-(readyLot*nom.getQuantity());
					child.getNeeded_article().setReservedQuantity(reserved);
					int realQuantity = child.getNeeded_article().getQuantity();
					realQuantity= realQuantity-(readyLot*nom.getQuantity());
					child.getNeeded_article().setQuantity(realQuantity);
					child.setReadyLotNumber(child.getReadyLotNumber()-readyLot);
				}
				e.getKey().setNetNeed(e.getKey().getNetNeed()-readyLot);
			}
		}
		needItem.updateNeedItemTree(map);
		return listMan;
	}

	@Override
	public void updateStatusToFinished() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		String finished = "finished";
		Query query = em.createQuery(
			      "UPDATE ManufacturingPlanning m SET m.status = :finished " +
			      "WHERE m.endingDate < :date");
			  query.setParameter("finished",finished);
			  query.setParameter("date",cal.getTime(),TemporalType.TIMESTAMP).executeUpdate();
		Set<NeededItem> set1 = new HashSet<>(needItem.findAll());
		for (NeededItem neededItem : set1) {
			if(!neededItem.getStatus().equals("finished")){
				if(!neededItem.getManufacturingPlanning().isEmpty()){
					Set<ManufacturingPlanning> set2 = new HashSet<>(neededItem.getManufacturingPlanning());
					boolean status =true;
					for (ManufacturingPlanning manufacturingPlanning : set2) {
						if(!manufacturingPlanning.getStatus().equals("finished")){
							status = false;
						}else{
							if(!manufacturingPlanning.getIs_incremented()){
								int ArtQty=manufacturingPlanning.getNeededItem().getNeeded_article().getQuantity();
								int ReservQty =manufacturingPlanning.getNeededItem().getNeeded_article().getReservedQuantity();
								int qty = manufacturingPlanning.getQuantity();
								manufacturingPlanning.getNeededItem().getNeeded_article().setQuantity(qty+ArtQty);
								manufacturingPlanning.getNeededItem().getNeeded_article().setReservedQuantity(qty+ReservQty);
								articleServ.updateArticle(manufacturingPlanning.getNeededItem().getNeeded_article());
								manufacturingPlanning.setIs_incremented(true);
							}
						}
					}
					if(status==true){
						neededItem.setStatus("finished");
						needItem.update(neededItem);
					}
				}
			}
			
		}
		
	}

	@Override
	public List<ManufacturingPlanning> displayManufactOfAnOrdredItem(int idOrder, int idArticle) {
		int x =0;
		TypedQuery<ManufacturingPlanning> query
		=em.createQuery("select m from ManufacturingPlanning m left join m.neededItem n where n.orderItem.ordredItemPk.id_Order=:idOrder AND "
				+ "n.orderItem.ordredItemPk.id_Article=:idArticle AND m.quantity>:x" , ManufacturingPlanning.class);
		query.setParameter("idOrder", idOrder);
		query.setParameter("idArticle", idArticle);
		query.setParameter("x", x);
		List<ManufacturingPlanning> manuf=query.getResultList();
		return manuf;
	}

	@Override
	public int updateIfOneNeededItem(NeededItem neededItem) {
		neededItem.setStatus("finished");
		neededItem.getNeeded_article().setQuantity(neededItem.getNeeded_article().getQuantity()-neededItem.getGrossNeed());
		articleServ.updateArticle(neededItem.getNeeded_article());
		needItem.save(neededItem);
		return neededItem.getId();
	}

	@Override
	public List<ManufacturingPlanning> AfterDeliveryManufacturingPlanning(Map<NeededItem, List<NeededItem>> map, int hourlyPost) {
		List<ManufacturingPlanning> listMan = new ArrayList<>();
		for (Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()) {
			Calendar cal = Calendar.getInstance();
			cal.setTimeInMillis(0);
			if(e.getKey().getNetNeed()!=0){
				if(!e.getValue().isEmpty()){
					Date startingDate =cal.getTime();
					Set<NeededItem> set = new HashSet<>(e.getValue());
					for (NeededItem child : set) {
						if(child.getNetNeed()!=0){
							if((child.getLevel()==99)&&((startingDate.compareTo(child.getPurchaseDeliveryDate()))<0)){
								startingDate=child.getPurchaseDeliveryDate();
							} else {
								if((!child.getManufacturingPlanning().isEmpty())&&(startingDate.compareTo(child.getManufacturingPlanning().get(child.getManufacturingPlanning().size()-1).getEndingDate())<0))
									startingDate=child.getManufacturingPlanning().get(child.getManufacturingPlanning().size()-1).getEndingDate();
							}
						}
					}
					int netQty = e.getKey().getNetNeed();
					//creating manufacturing planning of the needed Item with quantity of net need
					ManufacturingPlanning manuf = new ManufacturingPlanning(netQty,startingDate,"in progress",e.getKey());
					int duration = mmm.manufacturingDuration(e.getKey().getNeeded_article(),netQty);
					Date ending = mmm.endingManufacturingDate(startingDate, duration,hourlyPost);
					manuf.setDuration(duration);
					manuf.setEndingDate(ending);
					em.persist(manuf);

					if(!e.getKey().getManufacturingPlanning().contains(manuf)){
						manuf.setNeededItem(e.getKey());
						e.getKey().getManufacturingPlanning().add(manuf);
					}
						
					listMan.add(manuf);
				}
			}
			
		}
		needItem.updateNeedItemTree(map);
		return listMan;
	}
	
	
	/////////////////////ONS///////////////////////////////////
	public List<ManufacturingPlanning> DisplayManufacturingPlanning() {

		TypedQuery<ManufacturingPlanning> query=em.createQuery("SELECT o FROM ManufacturingPlanning o",ManufacturingPlanning.class);
		List <ManufacturingPlanning> result= query.getResultList();
		return result;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> stakingLaterScheduling(NeededItem ParentneededItem, Date DeliveryDate,
			int hourlyPost) {
		
		Date date = new Date();
		Map<NeededItem, List<NeededItem>> map = needItem.CreateNeedItemTree(ParentneededItem);
		Set<ManufacturingPlanning> ReadySet = new HashSet<>(ReadyManufacturingPlanningWithoutSaving(map, hourlyPost)) ;
		int i =0;
		for (Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()) {
			if(e.getKey().getLevel()==0 && e.getKey().getNetNeed()!=0){
				int netQty = e.getKey().getNetNeed();
				int duration = mmm.manufacturingDuration(e.getKey().getNeeded_article(),netQty);
				//creating manufacturing planning of the needed Item with quantity of net need
				ManufacturingPlanning manuf = new ManufacturingPlanning(netQty, DeliveryDate, duration, "in progress", e.getKey());
				Date starting = mmm.startingManufacturingDate(DeliveryDate, duration,hourlyPost);
				manuf.setDuration(duration);
				manuf.setStartingDate(starting);
				if(!e.getKey().getManufacturingPlanning().contains(manuf))
					e.getKey().getManufacturingPlanning().add(manuf);
				ReadySet.add(manuf);
				for (NeededItem child : e.getValue()) {
					if(child.getNetNeed()!=0){
						int netQtyChild = child.getNetNeed();
						int durationchild = mmm.manufacturingDuration(child.getNeeded_article(),netQtyChild);
						//creating manufacturing planning of the needed Item with quantity of net need
						ManufacturingPlanning manufChild = new ManufacturingPlanning(netQtyChild, manuf.getStartingDate(), durationchild, "in progress", child);
						Date startingChild = mmm.startingManufacturingDate(manuf.getStartingDate(), durationchild,hourlyPost);
						manufChild.setDuration(durationchild);
						manufChild.setStartingDate(startingChild);
						if(!child.getManufacturingPlanning().contains(manufChild))
							child.getManufacturingPlanning().add(manufChild);
						ReadySet.add(manufChild);
					}
				}
			} else {
				if(e.getKey().getNetNeed()!=0 && e.getKey().getManufacturingPlanning().size()>1 && !e.getValue().isEmpty()){
					ManufacturingPlanning manuf = e.getKey().getManufacturingPlanning().get(e.getKey().getManufacturingPlanning().size()-1);
					for (NeededItem child : e.getValue()) {
						if(child.getNetNeed()!=0 && child.getLevel()!=99){
							int netQtyChild = child.getNetNeed();
							int durationchild = mmm.manufacturingDuration(child.getNeeded_article(),netQtyChild);
							//creating manufacturing planning of the needed Item with quantity of net need
							ManufacturingPlanning manufChild = new ManufacturingPlanning(netQtyChild, manuf.getStartingDate(), durationchild, "in progress", child);
							Date startingChild = mmm.startingManufacturingDate(manuf.getStartingDate(), durationchild,hourlyPost);
							manufChild.setDuration(durationchild);
							manufChild.setStartingDate(startingChild);
							if(!child.getManufacturingPlanning().contains(manufChild))
								child.getManufacturingPlanning().add(manufChild);
							ReadySet.add(manufChild);
						}
					}
				}
			}
			
		}
		//Scheduling ready manufacturing planning with Just in time manufacturing planning
		for (NeededItem neededItem : map.keySet()) {
			if(!neededItem.getManufacturingPlanning().isEmpty()){
				if(neededItem.getManufacturingPlanning().size()>1){
					Date endingDate = neededItem.getManufacturingPlanning().get(neededItem.getManufacturingPlanning().size()-1).getStartingDate();
					Date startingDate = mmm.startingManufacturingDate(endingDate,(long) neededItem.getManufacturingPlanning().get(0).getDuration(),hourlyPost);
					neededItem.getManufacturingPlanning().get(0).setEndingDate(endingDate);
					neededItem.getManufacturingPlanning().get(0).setStartingDate(startingDate);
				} else {
					NeededItem parent = new NeededItem();
					for (Map.Entry<NeededItem,List<NeededItem>> need : map.entrySet()) {
						if(need.getValue().contains(neededItem)){
							parent = need.getKey();
						}
					}
					Date endingDate = parent.getManufacturingPlanning().get(neededItem.getManufacturingPlanning().size()-1).getStartingDate();
					Date startingDate = mmm.startingManufacturingDate(endingDate,(long) neededItem.getManufacturingPlanning().get(0).getDuration(),hourlyPost);
					neededItem.getManufacturingPlanning().get(0).setEndingDate(endingDate);
					neededItem.getManufacturingPlanning().get(0).setStartingDate(startingDate);
				}
				
			}
		}
		
		//Setting Normally purchase delivery date for purchase order
		for (Map.Entry<NeededItem, List<NeededItem>> neededItem : map.entrySet()) {
			if(!neededItem.getValue().isEmpty() && !neededItem.getKey().getManufacturingPlanning().isEmpty()
					&& neededItem.getKey().getManufacturingPlanning().size()>1){
				for (NeededItem neededItem99 : neededItem.getValue()) {
					if(neededItem99.getLevel()==99){
						Date deliv = neededItem.getKey().getManufacturingPlanning().get(neededItem.getKey().getManufacturingPlanning().size()-1).getStartingDate();
						neededItem99.setPurchaseDeliveryDate(deliv);
					}
				}
				
			}
		}
		
		return map;
	}

	@Override
	public Date startingManufacturingDate(Date endingDate, long duration, int hourlyPost) {
		if(duration!=0){
			float hourNbr = duration / 60;
			float dayNbr = hourNbr / (hourlyPost*8);
			int length = (int) Math.round(dayNbr + 0.5);
			Calendar c = Calendar.getInstance();
			c.setTime(endingDate);
			long endMillis = c.getTimeInMillis();
			long startMillis = 0;
			if(hourlyPost==3){
				startMillis = endMillis - duration*60*1000;
			} else {
				// if it's a work doesn't exceed 1 day
				if ((length - 1) == 0) {
					long testStartMillis;
					if(((endMillis%86400000) - duration*60*1000)<25200000 && hourlyPost==2){
						testStartMillis = endMillis - ((duration) * 60 * 1000)- 8*60*60*1000;
					} else {
						testStartMillis = endMillis - ((duration) * 60 * 1000);
					}
					
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testStartMillis % (86400000));
//					if(rest< 25200000)
//						rest=rest+24*60*60*1000;
					if (rest >= endTime || rest<=7*60*60*1000) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						startMillis = testStartMillis - jumpTime;
					} else {
						startMillis = testStartMillis ;
					}
				} else {
					long newDuration = duration - (length - 1) * 8*hourlyPost * 60;
					long newEndtMillis = endMillis - (length - 1) * 24 * 60 * 60 * 1000;
					
					long testStartMillis;
					if(((newEndtMillis % 86400000) - newDuration*60*1000)<25200000 && hourlyPost==2){
						testStartMillis = newEndtMillis - newDuration * 60 * 1000 -8*60*60*1000;
					} else {
						testStartMillis = newEndtMillis - newDuration * 60 * 1000 ;
					}
					
					// check if the work is more than 17h
					// 86400000 =1 day and 61200000=17h and 54000000=15h
					//endTime = 15h or 23h
					//jumpTime = 16h or 8h
					long endTime = (7+8*hourlyPost)*60*60*1000;
					long rest = (testStartMillis % (86400000));
//					if(rest< 25200000)
//						rest=rest+24*60*60*1000;
					if (rest >= endTime || rest<=7*60*60*1000) {
						long jumpTime=(24-8*hourlyPost)*60*60*1000;
						startMillis = testStartMillis - jumpTime;
					} else {
						startMillis = testStartMillis;
					}
				}
			}
			
			Calendar calendar = Calendar.getInstance();
			calendar.setTimeInMillis(startMillis);

			Date startingDate = calendar.getTime();
			return startingDate;
		} else {
			return endingDate;
		}
	}

	@Override
	public List<ManufacturingPlanning> ReadyManufacturingPlanningWithoutSaving(Map<NeededItem, List<NeededItem>> map,int hourlyPost) {
		Date date = new Date();
		List<ManufacturingPlanning> listMan = new ArrayList<>();
		for (Map.Entry<NeededItem, List<NeededItem>> e : map.entrySet()) {
			if(!e.getValue().isEmpty() && e.getKey().getNetNeed()!=0){
				int readyLot=0;
				readyLot = needItem.CheckReadyLot(e.getKey(), e.getValue());
				//creating manufacturing planning of the needed Item with quantity of readyLot
				ManufacturingPlanning manuf = new ManufacturingPlanning(readyLot,date,"in progress",e.getKey());
				int duration = mmm.manufacturingDuration(e.getKey().getNeeded_article(),readyLot);
				Date ending = mmm.endingManufacturingDate(date,duration,hourlyPost);
				manuf.setDuration(duration);
				manuf.setEndingDate(ending);
				listMan.add(manuf);
				e.getKey().getManufacturingPlanning().add(manuf);
				Set<NeededItem> set = new HashSet<>(e.getValue());
				for (NeededItem child : set) {
					Nomenclature nom = findNomenclatureByParentChild(e.getKey().getNeeded_article(), child.getNeeded_article());
					int reserved = child.getNeeded_article().getReservedQuantity();
					reserved = reserved-(readyLot*nom.getQuantity());
					child.getNeeded_article().setReservedQuantity(reserved);
					int realQuantity = child.getNeeded_article().getQuantity();
					realQuantity= realQuantity-(readyLot*nom.getQuantity());
					child.getNeeded_article().setQuantity(realQuantity);
					child.setReadyLotNumber(child.getReadyLotNumber()-readyLot);
				}
				e.getKey().setNetNeed(e.getKey().getNetNeed()-readyLot);
			}
		}
		return listMan;
	}

	
	
	

}
