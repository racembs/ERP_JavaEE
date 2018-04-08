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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private long reference;
	@Temporal(TemporalType.DATE)
	private Date order_date;
	@Temporal(TemporalType.DATE)
	private Date delivery_date;
	private String statut;
	
	@OneToMany(mappedBy="order",cascade=CascadeType.REMOVE)
	private List<OrdredItem> order_Item = new ArrayList<>();
	
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
	public long getReference() {
		return reference;
	}
	public void setReference(long reference) {
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
	public List<OrdredItem> getOrder_Item() {
		return order_Item;
	}
	public void setOrder_Item(List<OrdredItem> order_Item) {
		this.order_Item = order_Item;
	}
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public Orders(long reference, Date order_date, Date delivery_date, String statut) {
		super();
		this.reference = reference;
		this.order_date = order_date;
		this.delivery_date = delivery_date;
		this.statut = statut;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Orders other = (Orders) obj;
		if (id != other.id)
			return false;
		return true;
	}
   
}
