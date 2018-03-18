package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Orders
 *
 */
@Entity

public class Orders implements Serializable {

	   
	@Id
	private int id;
	private int reference;
	@Temporal(TemporalType.DATE)
	private Date order_date;
	@Temporal(TemporalType.DATE)
	private Date delivery_date;
	private String statut;
	
	@OneToMany(mappedBy="order")
	private List<ManufacturingOrder> manufactOrders = new ArrayList<>();
	
	@ManyToOne
	private Client client;
	
	private static final long serialVersionUID = 1L;

	public Orders() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}
	public int getReference() {
		return reference;
	}
	public void setReference(int reference) {
		this.reference = reference;
	}
	public Date getOrder_date() {
		return order_date;
	}
	public void setOrder_date(Date order_date) {
		this.order_date = order_date;
	}
	public Date getDelivery_date() {
		return delivery_date;
	}
	public void setDelivery_date(Date delivery_date) {
		this.delivery_date = delivery_date;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	public List<ManufacturingOrder> getManufactOrders() {
		return manufactOrders;
	}
	public void setManufactOrders(List<ManufacturingOrder> manufactOrders) {
		this.manufactOrders = manufactOrders;
	}
   
}
