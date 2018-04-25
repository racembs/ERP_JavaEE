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
	@Temporal(TemporalType.TIMESTAMP)
	private Date startingDate;
	@Temporal(TemporalType.TIMESTAMP)
	private Date endingDate;
	private float duration;
	private boolean is_incremented=false;
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

	public ManufacturingPlanning(int quantity, Date startingDate, String status, NeededItem neededItem) {
		super();
		this.quantity = quantity;
		this.startingDate = startingDate;
		this.status = status;
		this.neededItem = neededItem;
	}
	
	

	public ManufacturingPlanning(int quantity, Date endingDate, float duration, String status, NeededItem neededItem) {
		super();
		this.quantity = quantity;
		this.endingDate = endingDate;
		this.duration = duration;
		this.status = status;
		this.neededItem = neededItem;
	}

	public boolean getIs_incremented() {
		return is_incremented;
	}

	public void setIs_incremented(boolean is_incremented) {
		this.is_incremented = is_incremented;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(duration);
		result = prime * result + ((endingDate == null) ? 0 : endingDate.hashCode());
		result = prime * result + (is_incremented ? 1231 : 1237);
		result = prime * result + ((neededItem == null) ? 0 : neededItem.hashCode());
		result = prime * result + quantity;
		result = prime * result + ((startingDate == null) ? 0 : startingDate.hashCode());
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
		ManufacturingPlanning other = (ManufacturingPlanning) obj;
		if (Float.floatToIntBits(duration) != Float.floatToIntBits(other.duration))
			return false;
		if (endingDate == null) {
			if (other.endingDate != null)
				return false;
		} else if (!endingDate.equals(other.endingDate))
			return false;
		if (is_incremented != other.is_incremented)
			return false;
		if (neededItem == null) {
			if (other.neededItem != null)
				return false;
		} else if (!neededItem.equals(other.neededItem))
			return false;
		if (quantity != other.quantity)
			return false;
		if (startingDate == null) {
			if (other.startingDate != null)
				return false;
		} else if (!startingDate.equals(other.startingDate))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		return true;
	}
	
	

   
}
