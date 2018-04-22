package tn.esprit.b4.esprit1718b4eventmanagement.manufacturingservices;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeedNomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

/**
 * Session Bean implementation class NeededItemService
 */
@Stateless
@LocalBean
public class NeededItemService extends GenericDAO<NeededItem> implements NeededItemServiceRemote, NeededItemServiceLocal {

    /**
     * Default constructor. 
     */
	@EJB
	ArticleServiceLocal articleServ;
	@EJB
	NeedNomenclatureServiceLocal needNomenclature;
	@EJB
	OrderItemServiceLocal orderItemService;
	@PersistenceContext
	private EntityManager em;
    public NeededItemService() {
        // TODO Auto-generated constructor stub
    	super(NeededItem.class);
    }

	@Override
	public int addNeededItem(NeededItem neededItem) {
		em.persist(neededItem);
		return neededItem.getId();
	}

	@Override
	public Map<NeededItem, NeedNomenclature> addChildrenNeededItem(NeededItem neededItem) {
		NeededItem ParentNeededItem = neededItem;
		Map<NeededItem, NeedNomenclature> map = new HashMap<>();
		List <Nomenclature> nomenclatureList =  articleServ.getFilsArticles(ParentNeededItem.getNeeded_article().getId());
		for (Nomenclature nomenclature : nomenclatureList) {
			NeededItem ChildNeededItem = new NeededItem();
			ChildNeededItem.setNeeded_article(nomenclature.getArticleFils());
			if(nomenclature.getArticleFils().getType()=="Matiére-Premiére"){
				ChildNeededItem.setActionNature("Purchase Order");
				ChildNeededItem.setLevel(99);
			}
			else{
				ChildNeededItem.setActionNature("Manufacturing Order");
				ChildNeededItem.setLevel(ParentNeededItem.getLevel()+1);
			}
			ChildNeededItem.setGrossNeed(ParentNeededItem.getNetNeed()*nomenclature.getQuantity());
			if(ChildNeededItem.getGrossNeed()-ChildNeededItem.getNeeded_article().getQuantity()<0)
				ChildNeededItem.setNetNeed(0);
			else
				ChildNeededItem.setNetNeed(ChildNeededItem.getGrossNeed()-ChildNeededItem.getNeeded_article().getQuantity());
			ChildNeededItem.setReadyLotNumber((Integer) ChildNeededItem.getNeeded_article().getQuantity()/nomenclature.getQuantity());
			ChildNeededItem.setStatus("Pending");
			ChildNeededItem.setOrderItem(ParentNeededItem.getOrderItem());
			//			ChildNeededItem.setId(addNeededItem(ChildNeededItem));
			//			needNomenclature.addnomenclature(ParentNeededItem.getId(), ChildNeededItem.getId(), ChildNeededItem.getNetNeed());
			NeedNomenclature needNomenc = new NeedNomenclature();
			NeedNomenclaturePk pk = new NeedNomenclaturePk();
			pk.setIdChild(ChildNeededItem.getId());
			pk.setIdParent(ParentNeededItem.getId());
			needNomenc.setNeedNomenclaturePk(pk);
			needNomenc.setQuantity(ChildNeededItem.getNetNeed());
			map.put(ChildNeededItem, needNomenc);	
		}
		return map;
		
	}
	
	Map<NeededItem, List<NeededItem>> map = new HashMap<>();
	Map<NeededItem, List<NeededItem>> mapASC = new TreeMap<>(new LevelUp());
	Map<NeededItem, List<NeededItem>> mapDESC = new TreeMap<>(new LevelDown());
	
	@Override
	public Map<NeededItem, List<NeededItem>> InitialiseMap(){
		return  map = new HashMap<>();	
	}
	
	@Override
	public Map<NeededItem, List<NeededItem>> InitialiseASCMap(){
		return  mapASC = new TreeMap<>(new LevelUp());	
	}
	
	@Override
	public Map<NeededItem, List<NeededItem>> InitialiseDESCMap() {
		return  mapASC = new TreeMap<>(new LevelDown());	
	}
	
	@Override
	public Map<NeededItem, List<NeededItem>> CreateNeedItemTree(NeededItem ParentneededItem){
		//determine all Article childdren of the ParentneededItem
		List <Nomenclature> nomenclatureList =  articleServ.getFilsArticles(ParentneededItem.getNeeded_article().getId());
		List <NeededItem> NeededItemList = new ArrayList<>();
		for (Nomenclature nomenclature : nomenclatureList) {
			//Create ParentneededItem's Children from the article children determined previously
			NeededItem ChildNeededItem = new NeededItem();
			ChildNeededItem.setNeeded_article(nomenclature.getArticleFils());
			ChildNeededItem.setOrderItem(ParentneededItem.getOrderItem());
			if(nomenclature.getArticleFils().getType().equals("Matiére-Premiére")){
				ChildNeededItem.setActionNature("Purchase Order");
				ChildNeededItem.setLevel(99);
			}
			else{
				ChildNeededItem.setActionNature("Manufacturing Order");
				ChildNeededItem.setLevel(ParentneededItem.getLevel()+1);
			}
			ChildNeededItem.setGrossNeed(ParentneededItem.getNetNeed()*nomenclature.getQuantity());
			//NetNeed is zero if available quantity in stock is bigger than the gross need. Else NetNeed=GrossNeed-AvailableQtyInstock
			if((ChildNeededItem.getGrossNeed()-(ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity()))<0)
				ChildNeededItem.setNetNeed(0);
			else
				ChildNeededItem.setNetNeed(ChildNeededItem.getGrossNeed()-(ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity()));
			//Ready lot is the availableQty/NomenclatureQty : it's an indicator of how many lot is ready to manufact. the parent
			if((ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity())<ChildNeededItem.getGrossNeed())
				ChildNeededItem.setReadyLotNumber((Integer) (ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity())/nomenclature.getQuantity());
			else
				ChildNeededItem.setReadyLotNumber(ParentneededItem.getNetNeed());
			
			ChildNeededItem.setStatus("Pending");
			//Add this ChildNeededItem to the NeededItemList
			NeededItemList.add(ChildNeededItem);
		}
		//put into the map for any parent his children list
		map.put(ParentneededItem, NeededItemList);
		
		if(NeededItemList==null){
		}
		else{
			for (NeededItem neededItem : NeededItemList) {
				//this is a recursive call to create for each child his children if exist
				CreateNeedItemTree(neededItem);
			}
		}
		return map;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> SaveNeedItemTree(Map<NeededItem, List<NeededItem>> map) {
		for (NeededItem neededItem : map.keySet()) {
			//Add all key and that mean add all neededItem
			Article article = articleServ.findArticle(neededItem.getNeeded_article().getId());
			article.setReservedQuantity(article.getReservedQuantity()+neededItem.getGrossNeed()-neededItem.getNetNeed());
			articleServ.updateArticle(article);
			neededItem.setNeeded_article(article);
			neededItem.setId(addNeededItem(neededItem));
		}
		return map;
	}
	

	@Override
	public List<NeededItem> NeedItemList(Map<NeededItem, List<NeededItem>> map) {
		List<NeededItem> list = new ArrayList<>();
		for (NeededItem neededItem : map.keySet()) {
			//Add all key and that mean add all neededItem
			list.add(neededItem);
		}
		return list;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> findNeededItemTreeByOrdredItem(NeededItem ParentneededItem) {
		List<NeedNomenclature> listNomenclature = needNomenclature.getNeededItemChildren(ParentneededItem.getId());
		List<NeededItem> listChildren = new ArrayList<>();
		for (NeedNomenclature needednom : listNomenclature) {
			listChildren.add(needednom.getChild());
		}
		mapDESC.put(ParentneededItem, listChildren);
		
		if(listChildren==null){
		}
		else{
			for (NeededItem neededItem : listChildren) {
				findNeededItemTreeByOrdredItem(neededItem);
			}
		}
		return mapDESC;
	}

	@Override
	public NeededItem getNeededItemParentOfOrdredItem(int idOrder, int idArticle) {
		TypedQuery<NeededItem> query
		=em.createQuery("select n from NeededItem n where n.orderItem.ordredItemPk.id_Order=:idOrder AND "
				+ "n.orderItem.ordredItemPk.id_Article=:idArticle AND n.level=:level" , NeededItem.class);
		query.setParameter("idOrder", idOrder);
		query.setParameter("idArticle", idArticle);
		query.setParameter("level", 0);
		NeededItem nomenclature=query.getSingleResult();
		return nomenclature;
	}

	@Override
	public int CheckReadyLot(NeededItem Parent,List<NeededItem> children) {
		int lowestReadyLot=10000000;
		//List<NeededItem> nomenclatureList = needNomenclature.getNeededItemChildren(Parent.getId());
		for (NeededItem child : children) {
			if(lowestReadyLot>child.getReadyLotNumber()){
				lowestReadyLot=child.getReadyLotNumber();
			}
		}
		return lowestReadyLot;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> updateNeedItemTree(Map<NeededItem, List<NeededItem>> map) {
		for (NeededItem neededItem : map.keySet()) {
			em.merge(neededItem);
		}
		return map;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> CreateANDSaveNeedItemTree(NeededItem ParentneededItem) {
		//determine all Article childdren of the ParentneededItem
				List <Nomenclature> nomenclatureList =  articleServ.getFilsArticles(ParentneededItem.getNeeded_article().getId());
				List <NeededItem> NeededItemList = new ArrayList<>();
				for (Nomenclature nomenclature : nomenclatureList) {
					//Create ParentneededItem's Children from the article children determined previously
					NeededItem ChildNeededItem = new NeededItem();
					ChildNeededItem.setNeeded_article(nomenclature.getArticleFils());
					ChildNeededItem.setOrderItem(ParentneededItem.getOrderItem());
					if(nomenclature.getArticleFils().getType().equals("Matiére-Premiére")){
						ChildNeededItem.setActionNature("Purchase Order");
						ChildNeededItem.setLevel(99);
					}
					else{
						ChildNeededItem.setActionNature("Manufacturing Order");
						ChildNeededItem.setLevel(ParentneededItem.getLevel()+1);
					}
					ChildNeededItem.setGrossNeed(ParentneededItem.getNetNeed()*nomenclature.getQuantity());
					if((ChildNeededItem.getGrossNeed()-(ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity()))<0)
						ChildNeededItem.setNetNeed(0);
					else
						ChildNeededItem.setNetNeed(ChildNeededItem.getGrossNeed()-(ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity()));
					if((ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity())<ChildNeededItem.getGrossNeed())
						ChildNeededItem.setReadyLotNumber((Integer) (ChildNeededItem.getNeeded_article().getQuantity()-ChildNeededItem.getNeeded_article().getReservedQuantity())/nomenclature.getQuantity());
					else
						ChildNeededItem.setReadyLotNumber(ParentneededItem.getNetNeed());
					ChildNeededItem.setStatus("Pending");
					ChildNeededItem.getNeeded_article().setReservedQuantity(ChildNeededItem.getNeeded_article().getReservedQuantity()+ChildNeededItem.getGrossNeed()-ChildNeededItem.getNetNeed());
					articleServ.updateArticle(ChildNeededItem.getNeeded_article());
					//Add this ChildNeededItem to the NeededItemList
					NeededItemList.add(ChildNeededItem);
					ChildNeededItem.setId(addNeededItem(ChildNeededItem));
				}

				//put into the map for any parent his children list
				map.put(ParentneededItem, NeededItemList);
				
				if(NeededItemList==null){
				}
				else{
					for (NeededItem neededItem : NeededItemList) {
						//this is a recursive call to create for each child his children if exist
						CreateANDSaveNeedItemTree(neededItem);
					}
				}
				return map;
	}

	@Override
	public int SaveParentNeedItemTree(NeededItem Parent) {
		Article article = articleServ.findArticle(Parent.getNeeded_article().getId());
		article.setReservedQuantity(article.getReservedQuantity()+Parent.getGrossNeed()-Parent.getNetNeed());
		articleServ.updateArticle(article);
		Parent.setNeeded_article(article);
		Parent.setId(addNeededItem(Parent));
		return Parent.getId();
	}

	@Override
	public Map<NeededItem, List<NeededItem>> findNeededItemTreeByOrdredItemLevelAsc(NeededItem ParentneededItem) {
		List<NeedNomenclature> listNomenclature = needNomenclature.getNeededItemChildren(ParentneededItem.getId());
		List<NeededItem> listChildren = new ArrayList<>();
		for (NeedNomenclature needednom : listNomenclature) {
			listChildren.add(needednom.getChild());
		}
		mapASC.put(ParentneededItem, listChildren);
		
		if(listChildren==null){
		}
		else{
			for (NeededItem neededItem : listChildren) {
				findNeededItemTreeByOrdredItemLevelAsc(neededItem);
			}
		}
		return mapASC;
	}

	@Override
	public Map<NeededItem, List<NeededItem>> SetPurchaseDeliveryDate(Map<NeededItem, List<NeededItem>> map) {
		for (Map.Entry<NeededItem, List<NeededItem>> neededItem99 : map.entrySet()) {
			if(neededItem99.getValue().isEmpty()){
				int del = neededItem99.getKey().getNeeded_article().getDeliveryTime();
				Calendar cal = Calendar.getInstance();
				cal.setTimeInMillis(cal.getTimeInMillis()-(cal.getTimeInMillis() % (86400000)));
				cal.setTimeInMillis(cal.getTimeInMillis()+(del+1)*24*60*60*1000+28800000);
				neededItem99.getKey().setPurchaseDeliveryDate(cal.getTime());
				update(neededItem99.getKey());
			}
		}
		return map;
	}

	@Override
	public void updateStatusPurchaseOrder() {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(System.currentTimeMillis());
		String ready = "ready";
		int nb=99;
		Query query = em.createQuery(
			      "UPDATE NeededItem n SET n.status = :ready " +
			      "WHERE n.level=:nb AND n.purchaseDeliveryDate < :date");
			  query.setParameter("ready",ready);
			  query.setParameter("nb",99);
			  query.setParameter("date",cal.getTime(),TemporalType.TIMESTAMP).executeUpdate();
		
	}
	
	



}
