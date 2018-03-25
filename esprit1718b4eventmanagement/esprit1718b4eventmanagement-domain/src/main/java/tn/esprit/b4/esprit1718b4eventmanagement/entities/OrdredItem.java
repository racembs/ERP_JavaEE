package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OrdredItem
 *
 */
@Entity

public class OrdredItem implements Serializable {

	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private OrdredItemPk ordredItemPk;
	private int code;
	private int quantity;
	private String status;
	
	@ManyToOne
	@JoinColumn(name="id_Order",referencedColumnName="id"
	,insertable=false,updatable=false)
	private Orders order;
	
	@ManyToOne
	@JoinColumn(name="id_Article",referencedColumnName="Id"
	,insertable=false,updatable=false)
	private Article article;
	
	@OneToMany(mappedBy="orderItem")
	private List<NeededItem> ManufacturingList = new ArrayList<>();
	
	public OrdredItem() {
		super();
	}

	public OrdredItemPk getOrdredItemPk() {
		return ordredItemPk;
	}

	public void setOrdredItemPk(OrdredItemPk ordredItemPk) {
		this.ordredItemPk = ordredItemPk;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public List<NeededItem> getManufacturingList() {
		return ManufacturingList;
	}

	public void setManufacturingList(List<NeededItem> manufacturingList) {
		ManufacturingList = manufacturingList;
	}
   
	
}
