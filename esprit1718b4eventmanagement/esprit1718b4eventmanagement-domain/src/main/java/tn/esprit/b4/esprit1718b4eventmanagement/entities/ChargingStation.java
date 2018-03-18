package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
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
	private ChargingStationPK WorkStationPK;
	
	@ManyToOne
	@JoinColumn(name="idUser",referencedColumnName="USR_CODE",insertable=false,updatable=false)
	private User users;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="id_equipement",referencedColumnName="Id",insertable=false,updatable=false)
	private Equipment equipement;
	
	

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

	public User getUsers() {
		return users;
	}
	public void setUsers(User users) {
		this.users = users;
	}
	public Equipment getEquipement() {
		return equipement;
	}
	public void setEquipement(Equipment equipement) {
		this.equipement = equipement;
	}

   
	
}
