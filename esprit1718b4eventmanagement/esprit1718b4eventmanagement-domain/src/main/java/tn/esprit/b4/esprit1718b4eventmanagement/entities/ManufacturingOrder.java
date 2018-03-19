package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: ManufacturingOrder
 *
 */
@Entity
@Table(name = "ManufacturingOrder")
public class ManufacturingOrder implements Serializable {

	   
	@EmbeddedId
	private ManufacturingOrderPk manufacturingOrderPk;
	private int code;
	private int quantity;
	@Temporal(TemporalType.DATE)
	private Date prev_start_Date;
	@Temporal(TemporalType.DATE)
	private Date firm_start_date;
	private float manufacturing_deadline;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_Order",referencedColumnName="id"
	,insertable=false,updatable=false)
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name="id_Article",referencedColumnName="Id"
	,insertable=false,updatable=false)
	private Article article;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name="FatherMO_id_Article",referencedColumnName="id_Article",insertable=false,updatable=false,nullable=true),
		@JoinColumn(name="FatherMO_id_Order",referencedColumnName="id_Order",insertable=false,updatable=false,nullable=true)})
	private ManufacturingOrder FatherMO;
	
	@OneToMany(mappedBy="FatherMO")
	private List<ManufacturingOrder> SonsMO = new ArrayList<>();
	
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
	public ManufacturingOrderPk getManufacturingOrderPk() {
		return manufacturingOrderPk;
	}
	public void setManufacturingOrderPk(ManufacturingOrderPk manufacturingOrderPk) {
		this.manufacturingOrderPk = manufacturingOrderPk;
	}
	public Orders getOrder() {
		return order;
	}
	public void setOrder(Orders order) {
		this.order = order;
	}
	public Article getArticle() {
		return article;
	}
	public void setArticle(Article article) {
		this.article = article;
	}
	public ManufacturingOrder getFatherMO() {
		return FatherMO;
	}
	public void setFatherMO(ManufacturingOrder fatherMO) {
		FatherMO = fatherMO;
	}
	public List<ManufacturingOrder> getSonsMO() {
		return SonsMO;
	}
	public void setSonsMO(List<ManufacturingOrder> sonsMO) {
		SonsMO = sonsMO;
	}

   
}
