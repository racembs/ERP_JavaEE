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

	@Column(name = "PhaseNumber" , nullable=true)
	private int phasenumber;
	
	@Column(name = "UnitProductionTime" , nullable=true)
	private int unitproductiontime;
	
	@Column(name = "StandardLot" , nullable=true)
	private int standardlot;
	
	@Column(name = "Description" , nullable=true)
	private String description;
	
	
	private static final long serialVersionUID = 1L;
	
	@EmbeddedId
	private OperationPK operationPK;

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

	public int getPhasenumber() {
		return phasenumber;
	}



	public void setPhasenumber(int phasenumber) {
		this.phasenumber = phasenumber;
	}



	public int getUnitproductiontime() {
		return unitproductiontime;
	}



	public void setUnitproductiontime(int unitproductiontime) {
		this.unitproductiontime = unitproductiontime;
	}



	public int getStandardlot() {
		return standardlot;
	}



	public void setStandardlot(int standardlot) {
		this.standardlot = standardlot;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public OperationPK getOperationPK() {
		return operationPK;
	}



	public void setOperationPK(OperationPK operationPK) {
		this.operationPK = operationPK;
	}



	public ChargingStation getChargingstations() {
		return chargingstations;
	}



	public void setChargingstations(ChargingStation chargingstations) {
		this.chargingstations = chargingstations;
	}



	public OperatingRange getOptrange() {
		return optrange;
	}



	public void setOptrange(OperatingRange optrange) {
		this.optrange = optrange;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
   
}
