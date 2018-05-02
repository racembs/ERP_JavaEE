package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.*;


@Entity

public class ChargingStation implements Serializable {

	@Column(name = "Code")
	private int code;
	
	@Column(name = "NaturePost" , nullable=true)
	private String naturepost;
	
	@Column(name = "NbDay" , nullable=true)
	private int nbday;
	
	@Column(name = "NbHours" , nullable=true)
	private int nbhours;
	
	@Column(name = "Description" , nullable=true)
	private String description;
	
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	@Column(name = "ChargingStationPK")
	private ChargingStationPK chargingstationPK;
	
	@ManyToOne 
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User User;
	
	@ManyToOne
	@JoinColumn(name="id_equipement",referencedColumnName="Id",insertable=false,updatable=false)
	private Equipment equipement;
	
	@OneToMany(mappedBy="chargingstations")
	private List <Operation> operations = new ArrayList<>();

	public ChargingStation() {
		super();
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getNaturepost() {
		return naturepost;
	}

	public void setNaturepost(String naturepost) {
		this.naturepost = naturepost;
	}

	public int getNbday() {
		return nbday;
	}

	public void setNbday(int nbday) {
		this.nbday = nbday;
	}

	public int getNbhours() {
		return nbhours;
	}

	public void setNbhours(int nbhours) {
		this.nbhours = nbhours;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ChargingStationPK getChargingstationPK() {
		return chargingstationPK;
	}

	public void setChargingstationPK(ChargingStationPK chargingstationPK) {
		this.chargingstationPK = chargingstationPK;
	}

	public User getUser() {
		return User;
	}

	public void setUser(User user) {
		User = user;
	}

	public Equipment getEquipement() {
		return equipement;
	}

	public void setEquipement(Equipment equipement) {
		this.equipement = equipement;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public ChargingStation(int code, String naturepost, int nbday, int nbhours, String description,User user, Equipment equipement) {
		super();
		this.code = code;
		this.naturepost = naturepost;
		this.nbday = nbday;
		this.nbhours = nbhours;
		this.description = description;
		this.User = user;
		this.equipement = equipement;
	}

	public ChargingStation(int code, String naturepost, int nbday, int nbhours, String description) {
		super();
		this.code = code;
		this.naturepost = naturepost;
		this.nbday = nbday;
		this.nbhours = nbhours;
		this.description = description;
		
	}



 

	
}
