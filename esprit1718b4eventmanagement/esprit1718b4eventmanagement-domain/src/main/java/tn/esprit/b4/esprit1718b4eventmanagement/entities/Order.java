package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Order
 *
 */
@Entity

public class Order implements Serializable {

	   
	@Id
	private int id;
	private int reference;
	@Temporal(TemporalType.DATE)
	private Date order_date;
	@Temporal(TemporalType.DATE)
	private Date delivery_date;
	private String statut;
	private static final long serialVersionUID = 1L;

	public Order() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getReference() {
		return this.reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}   
	public String getStatut() {
		return this.statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
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

   
}
