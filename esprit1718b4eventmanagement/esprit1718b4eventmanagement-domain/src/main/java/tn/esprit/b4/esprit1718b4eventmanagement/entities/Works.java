package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
public class Works implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	
	@Column(name = "OBJET")
	private String objet;
	@Column(name = "DESCR_W")
	private String description;
	@Column(name = "TECHNO")
	private String technology;
	@Column(name = "START_DATE")
	@Temporal(TemporalType.DATE)
	private Date startDate;
	@Column(name = "END_DATE")
	@Temporal(TemporalType.DATE)
	private Date endDate;
	@Column(name = "STATE")
	private String state;
	
	@EmbeddedId
	private WorksPK worksPK;
	

	
	////////////////////
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name="id_user",
	referencedColumnName="id",
	insertable=false,
	updatable=false)
	private User user;
	
	@ManyToOne (cascade = {CascadeType.ALL})
	@JoinColumn(name="id_equipement",
	referencedColumnName="Id",
	insertable=false,
	updatable=false)
	private Equipment equipement;

			public String getObjet() {
				return objet;
			}

			public void setObjet(String objet) {
				this.objet = objet;
			}

			public String getDescription() {
				return description;
			}

			public void setDescription(String description) {
				this.description = description;
			}

			public String getTechnology() {
				return technology;
			}

			public void setTechnology(String technology) {
				this.technology = technology;
			}

			public Date getStartDate() {
				return startDate;
			}

			public void setStartDate(Date startDate) {
				this.startDate = startDate;
			}

			public Date getEndDate() {
				return endDate;
			}

			public void setEndDate(Date endDate) {
				this.endDate = endDate;
			}

			public String getState() {
				return state;
			}

			public void setState(String state) {
				this.state = state;
			}

			public WorksPK getWorksPK() {
				return worksPK;
			}

			public void setWorksPK(WorksPK worksPK) {
				this.worksPK = worksPK;
			}

		/*	public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}*/

			public Equipment getEquipement() {
				return equipement;
			}

			public void setEquipement(Equipment equipement) {
				this.equipement = equipement;
			}

			public Works(String objet, String description, String technology, Date startDate, Date endDate,
					String state, WorksPK worksPK, User user, Equipment equipement) {
				super();
				this.objet = objet;
				this.description = description;
				this.technology = technology;
				this.startDate = startDate;
				this.endDate = endDate;
				this.state = state;
				this.worksPK = worksPK;
				this.user = user;
				this.equipement = equipement;
			}

			public Works() {
			
			}
			public Works(String objet, String description, String technology,
					WorksPK worksPK, User user, Equipment equipement) {
				super();
				this.objet = objet;
				this.description = description;
				this.technology = technology;
				
				
				this.worksPK = worksPK;
				this.user = user;
				this.equipement = equipement;
			}	
			
}
