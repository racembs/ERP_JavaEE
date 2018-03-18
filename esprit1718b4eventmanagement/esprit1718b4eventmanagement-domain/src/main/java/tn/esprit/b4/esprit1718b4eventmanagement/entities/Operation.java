package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Operation
 *
 */
@Entity

public class Operation implements Serializable {

	@Column(name = "PhaseNumber")
	private int PhaseNumber;
	
	@Column(name = "UnitProductionTime")
	private int UnitProductionTime;
	
	@Column(name = "StandardLot")
	private int StandardLot;
	
	@Column(name = "Description")
	private String Description;
	
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OperationPK OperationPK;

	public Operation() {
		super();
	} 
	

	@ManyToOne
	@JoinColumns({
			@JoinColumn(name="idUser",referencedColumnName="idUser",insertable=false,updatable=false),
			@JoinColumn(name="id_equipement",referencedColumnName="id_equipement",insertable=false,updatable=false)})
	private ChargingStation chargingstations;
	
	
	
	@ManyToOne
	@JoinColumn(name="id_OperatingRange",referencedColumnName="IdOptRange",insertable=false,updatable=false)
	private OperatingRange optrange;
	
	public int getPhaseNumber() {
		return this.PhaseNumber;
	}

	public void setPhaseNumber(int PhaseNumber) {
		this.PhaseNumber = PhaseNumber;
	}   
	public int getUnitProductionTime() {
		return this.UnitProductionTime;
	}

	public void setUnitProductionTime(int UnitProductionTime) {
		this.UnitProductionTime = UnitProductionTime;
	}   
	public int getStandardLot() {
		return this.StandardLot;
	}

	public void setStandardLot(int StandardLot) {
		this.StandardLot = StandardLot;
	}   
	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}

	public OperationPK getOperationPK() {
		return OperationPK;
	}

	public void setOperationPK(OperationPK operationPK) {
		OperationPK = operationPK;
	}

	

	public OperatingRange getOptrange() {
		return optrange;
	}

	public void setOptrange(OperatingRange optrange) {
		this.optrange = optrange;
	}

	public ChargingStation getChargingstations() {
		return chargingstations;
	}

	public void setChargingstations(ChargingStation chargingstations) {
		this.chargingstations = chargingstations;
	}
	
	
	
   
}
