package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@DiscriminatorValue(value="UsualWork")
public class UsualWork  extends Works implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String emmergency;
	private String orderstate;
	@Temporal(TemporalType.DATE)
	private Date WRDate;
	@Temporal(TemporalType.DATE)
	private Date WODate;
	@Enumerated(EnumType.STRING)
	private Nature nature;
	private int technicianId;
	

	public UsualWork() {
		super();
		
		// TODO Auto-generated constructor stub
	}
	public String getEmmergency() {
		return emmergency;
	}
	public void setEmmergency(String emmergency) {
		this.emmergency = emmergency;
	}
	public String getOrderstate() {
		return orderstate;
	}
	public void setOrderstate(String orderstate) {
		this.orderstate = orderstate;
	}
	public Date getWRDate() {
		return WRDate;
	}
	public void setWRDate(Date wRDate) {
		WRDate = wRDate;
	}
	public Date getWODate() {
		return WODate;
	}
	public void setWODate(Date wODate) {
		WODate = wODate;
	}
	public Nature getNature() {
		return nature;
	}
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	public int getTechnicianId() {
		return technicianId;
	}
	public void setTechnicianId(int technicianId) {
		this.technicianId = technicianId;
	}
}
