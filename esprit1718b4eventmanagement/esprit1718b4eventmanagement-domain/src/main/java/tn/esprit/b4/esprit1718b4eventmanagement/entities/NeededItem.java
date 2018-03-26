package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: NeededItem
 *
 */
@Entity
@Table(name = "NeededItem")
public class NeededItem implements Serializable {

	   
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private String actionNature;
	@Temporal(TemporalType.DATE)
	private Date purchaseDeliveryDate;
	private int level;
	@Column(name = "grossNeed")
	private int grossNeed;
	@Column(name = "netNeed")
	private int netNeed;
	@Column(name = "readyLotNumber")
	private int readyLotNumber;
	private String status;
	
	@ManyToOne
	private OrdredItem orderItem;
	
	@ManyToOne
	private Article needed_article;
	
	@OneToMany(mappedBy="neededItem",fetch=FetchType.EAGER)
	private List<ManufacturingPlanning> manufacturingPlanning;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	private List<NeedNomenclature> nomenclatures;
	
	@OneToMany(mappedBy="child",fetch=FetchType.EAGER)
	private List<NeedNomenclature> nomenclatures1;
	
	
	private static final long serialVersionUID = 1L;

	public NeededItem() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getActionNature() {
		return actionNature;
	}

	public void setActionNature(String actionNature) {
		this.actionNature = actionNature;
	}

	public Date getPurchaseDeliveryDate() {
		return purchaseDeliveryDate;
	}

	public void setPurchaseDeliveryDate(Date purchaseDeliveryDate) {
		this.purchaseDeliveryDate = purchaseDeliveryDate;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public OrdredItem getOrderItem() {
		return orderItem;
	}

	public void setOrderItem(OrdredItem orderItem) {
		this.orderItem = orderItem;
	}

	public Article getNeeded_article() {
		return needed_article;
	}

	public void setNeeded_article(Article needed_article) {
		this.needed_article = needed_article;
	}

	public List<NeedNomenclature> getNomenclatures() {
		return nomenclatures;
	}

	public void setNomenclatures(List<NeedNomenclature> nomenclatures) {
		this.nomenclatures = nomenclatures;
	}

	public List<NeedNomenclature> getNomenclatures1() {
		return nomenclatures1;
	}

	public void setNomenclatures1(List<NeedNomenclature> nomenclatures1) {
		this.nomenclatures1 = nomenclatures1;
	}

	public int getGrossNeed() {
		return grossNeed;
	}

	public void setGrossNeed(int grossNeed) {
		grossNeed = grossNeed;
	}

	public int getNetNeed() {
		return netNeed;
	}

	public void setNetNeed(int netNeed) {
		netNeed = netNeed;
	}

	public int getReadyLotNumber() {
		return readyLotNumber;
	}

	public void setReadyLotNumber(int readyLotNumber) {
		readyLotNumber = readyLotNumber;
	}

	public List<ManufacturingPlanning> getManufacturingPlanning() {
		return manufacturingPlanning;
	}

	public void setManufacturingPlanning(List<ManufacturingPlanning> manufacturingPlanning) {
		this.manufacturingPlanning = manufacturingPlanning;
	}   
	
	
	

   
}
