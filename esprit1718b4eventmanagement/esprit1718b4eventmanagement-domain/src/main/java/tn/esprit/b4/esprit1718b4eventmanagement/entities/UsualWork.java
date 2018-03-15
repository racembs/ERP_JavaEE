package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
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

}
