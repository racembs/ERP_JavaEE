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
 * Entity implementation class for Entity: ManufacturingOrder
 *
 */
@Entity
@Table(name = "ManufacturingOrder")
public class ManufacturingOrder implements Serializable {

	   
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int code;
	private int quantity;
	@Temporal(TemporalType.DATE)
	private Date prev_start_Date;
	@Temporal(TemporalType.DATE)
	private Date firm_start_date;
	private float manufacturing_deadline;
	private String type;
	private String status;
	
	@ManyToOne
	private OrdredItem orderItem;
	
	@OneToOne
	private Article MO_article;
	
	@OneToMany(mappedBy="parent",fetch=FetchType.EAGER)
	private List<ManufactOrderNomenclature> nomenclatures;
	
	@OneToMany(mappedBy="child",fetch=FetchType.EAGER)
	private List<ManufactOrderNomenclature> nomenclatures1;
	
	
	private static final long serialVersionUID = 1L;

	public ManufacturingOrder() {
		super();
	}   
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}   
	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}   
	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public Date getPrev_start_Date() {
		return prev_start_Date;
	}
	public void setPrev_start_Date(Date prev_start_Date) {
		this.prev_start_Date = prev_start_Date;
	}
	public Date getFirm_start_date() {
		return firm_start_date;
	}
	public void setFirm_start_date(Date firm_start_date) {
		this.firm_start_date = firm_start_date;
	}
	public float getManufacturing_deadline() {
		return manufacturing_deadline;
	}
	public void setManufacturing_deadline(float manufactring_deadline) {
		this.manufacturing_deadline = manufactring_deadline;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public ManufacturingOrder(int code, Date prev_start_Date, Date firm_start_date, float manufacturing_deadline,
			String type) {
		super();
		this.code = code;
		this.prev_start_Date = prev_start_Date;
		this.firm_start_date = firm_start_date;
		this.manufacturing_deadline = manufacturing_deadline;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public OrdredItem getOrderItem() {
		return orderItem;
	}
	public void setOrderItem(OrdredItem orderItem) {
		this.orderItem = orderItem;
	}
	public Article getMO_article() {
		return MO_article;
	}
	public void setMO_article(Article mO_article) {
		MO_article = mO_article;
	}
	public List<ManufactOrderNomenclature> getNomenclatures() {
		return nomenclatures;
	}
	public void setNomenclatures(List<ManufactOrderNomenclature> nomenclatures) {
		this.nomenclatures = nomenclatures;
	}
	public List<ManufactOrderNomenclature> getNomenclatures1() {
		return nomenclatures1;
	}
	public void setNomenclatures1(List<ManufactOrderNomenclature> nomenclatures1) {
		this.nomenclatures1 = nomenclatures1;
	}
	
	

   
}
