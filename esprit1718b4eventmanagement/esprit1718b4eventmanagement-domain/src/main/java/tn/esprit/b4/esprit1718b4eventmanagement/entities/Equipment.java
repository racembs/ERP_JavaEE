package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_Equipment")
public class Equipment implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "SerialNum")
	private String SerialNum;
	@Column(name = "Description")
	private String Description;
	@Column(name = "State")
	private String State;
	@Column(name = "EISDate")
	private Date EISDate;
	@Column(name = "Fabriquant")
	private String Fabriquant;
	@Column(name = "Marque")
	private String Marque;
	

	
	
	
	
	
	
	@OneToMany(mappedBy="equipement")
	private List <ChargingStation> chargingstations;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSerialNum() {
		return SerialNum;
	}
	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public Date getEISDate() {
		return EISDate;
	}
	public void setEISDate(Date eISDate) {
		EISDate = eISDate;
	}
	
	public Equipment() {
		super();
	}
	public Equipment( String serialNum, String description, String state, Date eISDate) {
		super();
		
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
	}
	public List<ChargingStation> getChargingstations() {
		return chargingstations;
	}
	public void setChargingstations(List<ChargingStation> chargingstations) {
		this.chargingstations = chargingstations;
	}

	
	
}
