package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.Date;


import javax.persistence.Entity;

import javax.persistence.DiscriminatorValue;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@Entity
@DiscriminatorValue(value="PreventiveWork")
public class PreventiveWork  extends Works implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String Frequency;
	@Temporal(TemporalType.DATE)
	private Date CreatDate;
	@Temporal(TemporalType.DATE)
	private Date LastActDate;
	@Temporal(TemporalType.DATE)
	private Date TriggerD;
	
	public String getFrequency() {
		return Frequency;
	}
	public void setFrequency(String frequency) {
		Frequency = frequency;
	}
	public Date getCreatDate() {
		return CreatDate;
	}
	public void setCreatDate(Date creatDate) {
		CreatDate = creatDate;
	}
	public Date getLastActDate() {
		return LastActDate;
	}
	public void setLastActDate(Date lastActDate) {
		LastActDate = lastActDate;
	}
	public Date getTriggerD() {
		return TriggerD;
	}
	public void setTrigger(Date triggerD) {
		TriggerD = triggerD;
	}
	

}
