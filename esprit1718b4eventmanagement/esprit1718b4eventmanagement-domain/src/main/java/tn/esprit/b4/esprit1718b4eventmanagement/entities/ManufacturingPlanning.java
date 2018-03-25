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
 * Entity implementation class for Entity: ManufacturingPlanning
 *
 */
@Entity
@Table(name = "ManufacturingPlanning")
public class ManufacturingPlanning implements Serializable {

	   
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	private int quantity;
	@Temporal(TemporalType.DATE)
	private Date startingDate;
	@Temporal(TemporalType.DATE)
	private Date endingDate;
	private float duration;
	private String status;
	
	@ManyToOne
	private NeededItem neededItem;
	
	
	
	private static final long serialVersionUID = 1L;

	public ManufacturingPlanning() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	public float getDuration() {
		return duration;
	}

	public void setDuration(float duration) {
		this.duration = duration;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public NeededItem getNeededItem() {
		return neededItem;
	}

	public void setNeededItem(NeededItem neededItem) {
		this.neededItem = neededItem;
	}
	
	

   
}
