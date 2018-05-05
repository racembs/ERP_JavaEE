package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.ManyToOne;
import javax.persistence.*;


@Entity

public class Reclamation implements Serializable {

	@Column(name = "Code")
	private int code;
	
	@Column(name = "Subject" , nullable=true)
	private String subject;
	
	@Column(name = "Description" , nullable=true)
	private String description;
	
	@Column(name = "DateCreation" , nullable=true)
	private Date datecreation;
	
	@Column(name = "DateSolution" , nullable=true)
	private Date datesolution;
	
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	@Column(name = "ReclamationPK")
	private ReclamationPk reclamationPK;
	
	@ManyToOne 
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User User;
	
	@ManyToOne
	@JoinColumn(name="id_equipement",referencedColumnName="Id",insertable=false,updatable=false)
	private Equipment equipement;
	
	

	public Reclamation() {
		super();
	}



	public int getCode() {
		return code;
	}



	public void setCode(int code) {
		this.code = code;
	}



	public String getSubject() {
		return subject;
	}



	public void setSubject(String subject) {
		this.subject = subject;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Date getDatecreation() {
		return datecreation;
	}



	public void setDatecreation(Date datecreation) {
		this.datecreation = datecreation;
	}



	public Date getDatesolution() {
		return datesolution;
	}



	public void setDatesolution(Date datesolution) {
		this.datesolution = datesolution;
	}



	



	public ReclamationPk getReclamationPK() {
		return reclamationPK;
	}



	public void setReclamationPK(ReclamationPk reclamationPK) {
		this.reclamationPK = reclamationPK;
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



	public Reclamation(int code, String subject, String description, Date datecreation, Date datesolution,
			ReclamationPk reclamationPK, tn.esprit.b4.esprit1718b4eventmanagement.entities.User user,
			Equipment equipement) {
		super();
		this.code = code;
		this.subject = subject;
		this.description = description;
		this.datecreation = datecreation;
		this.datesolution = datesolution;
		this.reclamationPK = reclamationPK;
		User = user;
		this.equipement = equipement;
	}

	
	


 

	
}
