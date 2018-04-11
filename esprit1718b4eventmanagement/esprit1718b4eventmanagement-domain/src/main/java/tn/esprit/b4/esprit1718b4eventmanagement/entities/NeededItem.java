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
public class NeededItem implements Serializable, Comparable<NeededItem> {

	   
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
	
	@ManyToOne(cascade=CascadeType.MERGE)
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

	public int getNetNeed() {
		return netNeed;
	}

	public void setNetNeed(int netNeed) {
		this.netNeed = netNeed;
	}

	public int getReadyLotNumber() {
		return readyLotNumber;
	}

	public void setReadyLotNumber(int readyLotNumber) {
		this.readyLotNumber = readyLotNumber;
	}

	public void setGrossNeed(int grossNeed) {
		this.grossNeed = grossNeed;
	}

	public List<ManufacturingPlanning> getManufacturingPlanning() {
		return manufacturingPlanning;
	}

	public void setManufacturingPlanning(List<ManufacturingPlanning> manufacturingPlanning) {
		this.manufacturingPlanning = manufacturingPlanning;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((actionNature == null) ? 0 : actionNature.hashCode());
		result = prime * result + grossNeed;
		result = prime * result + level;
		result = prime * result + ((needed_article == null) ? 0 : needed_article.hashCode());
		result = prime * result + netNeed;
		result = prime * result + ((purchaseDeliveryDate == null) ? 0 : purchaseDeliveryDate.hashCode());
		result = prime * result + readyLotNumber;
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NeededItem other = (NeededItem) obj;
		if (actionNature == null) {
			if (other.actionNature != null)
				return false;
		} else if (!actionNature.equals(other.actionNature))
			return false;
		if (grossNeed != other.grossNeed)
			return false;
		if (level != other.level)
			return false;
		if (needed_article == null) {
			if (other.needed_article != null)
				return false;
		} else if (!needed_article.equals(other.needed_article))
			return false;
		if (netNeed != other.netNeed)
			return false;
		if (purchaseDeliveryDate == null) {
			if (other.purchaseDeliveryDate != null)
				return false;
		} else if (!purchaseDeliveryDate.equals(other.purchaseDeliveryDate))
			return false;
		if (readyLotNumber != other.readyLotNumber)
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}

	@Override
	public int compareTo(NeededItem o) {
		return this.getNeeded_article().getArticleCode().compareTo(o.getNeeded_article().getArticleCode());
	}

	

	
	
	
	

   
}
