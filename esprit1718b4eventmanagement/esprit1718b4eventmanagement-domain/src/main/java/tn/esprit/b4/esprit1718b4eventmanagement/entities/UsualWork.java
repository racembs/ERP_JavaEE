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
	private String requeststate;
	@Temporal(TemporalType.DATE)
	private Date WRDate;
	@Temporal(TemporalType.DATE)
	private Date WODate;
	@Enumerated(EnumType.STRING)
	private Nature nature;
	public String getEmmergency() {
		return emmergency;
	}
	public void setEmmergency(String emmergency) {
		this.emmergency = emmergency;
	}
	public String getRequeststate() {
		return requeststate;
	}
	public void setRequeststate(String requeststate) {
		this.requeststate = requeststate;
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
	public UsualWork() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UsualWork(String objet, String description, String technology, WorksPK worksPK, User user,
			Equipment equipement, String emmergency, String requeststate, Date wRDate, Nature nature) {
		super(objet, description, technology, worksPK, user, equipement);
		this.emmergency = emmergency;
		this.requeststate = requeststate;
		WRDate = wRDate;
		this.nature = nature;
	}
	



}
