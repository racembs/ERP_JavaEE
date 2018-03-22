package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.beans.Beans;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.spi.Bean;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufactOrderNomenclaturePk;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nomenclature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItemPk;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceLocal;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleService;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

@Stateless
public class ManufacturingService implements ManufacturingServiceLocal, ManufacturingServiceRemote {

	@EJB
	private ArticleServiceLocal ar ;
	@PersistenceContext
	EntityManager em;

	@Override
	public int addClient(Client client) {
		em.persist(client);
		return client.getId();
	}

	@Override
	public Client findClientById(int id) {
		Client client = em.find(Client.class, id);
		return client;
	}

	@Override
	public int addOrders(Orders order) {
		em.persist(order);
		return order.getId();
	}	

	@Override
	public OrdredItemPk addOrdredItem(int idOrder, int idArticle, OrdredItem ordredItem) {
		OrdredItemPk ordredItemPk = new OrdredItemPk();
		ordredItemPk.setId_Article(idArticle);
		ordredItemPk.setId_Order(idOrder);
		ordredItem.setOrdredItemPk(ordredItemPk);
		em.persist(ordredItem);
		return ordredItemPk;
	}
	
	@Override
	public int addManufactOrder(ManufacturingOrder manufacturingOrder) {
		em.persist(manufacturingOrder);
		return manufacturingOrder.getId();
	}

	@Override
	public OrdredItemPk updateOrdredItem(OrdredItem ordredItem) {
		em.merge(ordredItem);
		return ordredItem.getOrdredItemPk();
	}

	@Override
	public OrdredItem findOrdredItemById(int idOrder, int idArticle) {
		OrdredItemPk ordredItemPk = new OrdredItemPk();
		ordredItemPk.setId_Article(idArticle);
		ordredItemPk.setId_Order(idOrder);
		return em.find(OrdredItem.class, ordredItemPk);
	}

	@Override
	public OrdredItem reatach(OrdredItem ordredItem) {
		return em.find(OrdredItem.class, ordredItem.getOrdredItemPk());
	}

	@Override
	public ManufacturingOrder addManufactChild(ManufacturingOrder ManufFadher) {
		// TODO Auto-generated method stub
		return null;
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

//	@Override
//	public ManufacturingOrder addManufactChild(ManufacturingOrder ManufFadher1) {
//		ManufacturingOrder ManufFadher = em.find(ManufacturingOrder.class, ManufFadher1.getManufacturingOrderPk());
//
//		System.out.println(ManufFadher.getManufacturingOrderPk().getId_Article());
//		//Recuperer une liste de tous les fils de l'article de l'order de fabrication
//		List <Nomenclature> ListFils = new ArrayList<>();
//		ListFils = ar.getFilsArticles(ManufFadher.getManufacturingOrderPk().getId_Article());
//		int i=0;
//		//Affecter a cet ordre de fabrication ses fils selon la nomenclature de l'article en question
//		for (Nomenclature nomencl : ListFils) {
//			i++;
//			ManufacturingOrder manufact = new ManufacturingOrder(ManufFadher.getCode()+i
//					,ManufFadher.getPrev_start_Date(),ManufFadher.getFirm_start_date(),ManufFadher.getManufacturing_deadline(),"besoin");
//			ManufacturingOrderPk manufactPk = new ManufacturingOrderPk();
//			//Affecter a l'odre de fabrication Fils, l'id de l'article fils + l'id de la meme commande
//			manufactPk.setId_Article(nomencl.getArticleFils().getId());
//			manufactPk.setId_Order(ManufFadher.getManufacturingOrderPk().getId_Order());
//			manufact.setManufacturingOrderPk(manufactPk);
//			//Calcule de la quantité qu'on aura besoin pour fabriquer l'article pére
//			manufact.setQuantity(ManufFadher.getQuantity()*nomencl.getQuantity());
//			ManufFadher = reatach(ManufFadher);
//			manufact.setFatherMO(ManufFadher);
//			ManufFadher.getSonsMO().add(manufact);
//			em.persist(manufact);
//
//		}
//		return ManufFadher;
//	}
	
	

}
