package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: OperatingRange
 *
 */
@Entity

public class OperatingRange implements Serializable {

	   
	@Id
	private int id;
	
	@Column(name = "Code")
	private String Code;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "StakingCondition")
	private String StakingCondition;
	
	@Column(name = "Deadline")
	private int Deadline;
	
	private static final long serialVersionUID = 1L;

	public OperatingRange() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getCode() {
		return this.Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}   
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}   
	public String getStakingCondition() {
		return this.StakingCondition;
	}

	public void setStakingCondition(String StakingCondition) {
		this.StakingCondition = StakingCondition;
	}   
	public int getDeadline() {
		return this.Deadline;
	}

	public void setDeadline(int Deadline) {
		this.Deadline = Deadline;
	}
   
}
