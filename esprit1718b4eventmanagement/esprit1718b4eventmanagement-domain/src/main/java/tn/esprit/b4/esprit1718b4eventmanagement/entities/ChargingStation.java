package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: WorkStation
 *
 */
@Entity

public class ChargingStation implements Serializable {

	@Column(name = "Code")
	private int Code;
	
	@Column(name = "NaturePost")
	private String NaturePost;
	
	@Column(name = "NbDay")
	private int NbDay;
	
	@Column(name = "NbHours")
	private int NbHours;
	
	@Column(name = "Description")
	private String Description;
	
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	@Column(name = "WorkStationPK")
	private ChargingStationPK WorkStationPK;
	
	@ManyToOne (cascade=CascadeType.ALL)
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private Users users;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_equipement",referencedColumnName="Id",insertable=false,updatable=false)
	private Equipment equipement;
	
	@OneToMany(mappedBy="chargingstations")
	private List <Operation> operations = new ArrayList<>();

	public ChargingStation() {
		super();
	}   
	public int getCode() {
		return this.Code;
	}

	public void setCode(int Code) {
		this.Code = Code;
	}   
	public String getNaturePost() {
		return this.NaturePost;
	}

	public void setNaturePost(String NaturePost) {
		this.NaturePost = NaturePost;
	}   
	public int getNbDay() {
		return this.NbDay;
	}

	public void setNbDay(int NbDay) {
		this.NbDay = NbDay;
	}   
	public int getNbHours() {
		return this.NbHours;
	}

	public void setNbHours(int NbHours) {
		this.NbHours = NbHours;
	}   
	public String getDescription() {
		return this.Description;
	}

	public void setDescription(String Description) {
		this.Description = Description;
	}
	public ChargingStationPK getWorkStationPK() {
		return WorkStationPK;
	}
	public void setWorkStationPK(ChargingStationPK workStationPK) {
		WorkStationPK = workStationPK;
	}


	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
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
	public ChargingStation(int code, String naturePost, int nbDay, int nbHours, String description,
			ChargingStationPK workStationPK, Users users, Equipment equipement, List<Operation> operations) {
		super();
		Code = code;
		NaturePost = naturePost;
		NbDay = nbDay;
		NbHours = nbHours;
		Description = description;
		WorkStationPK = workStationPK;
		this.users = users;
		this.equipement = equipement;
		this.operations = operations;
	}
	public ChargingStation(int code) {
		super();
		Code = code;
		
	}

   
	
}
