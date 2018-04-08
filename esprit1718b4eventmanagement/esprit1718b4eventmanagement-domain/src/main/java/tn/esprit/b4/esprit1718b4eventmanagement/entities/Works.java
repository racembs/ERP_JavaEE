package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



@Entity
public class Works implements Serializable {
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
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



	@ManyToOne
	@JoinColumn(name="id_user",
	referencedColumnName="id",insertable=true,updatable=true)
	private User user;
	
	@ManyToOne
	@JoinColumn(name="id_equipement",
	referencedColumnName="Id",insertable=true,updatable=true)
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

		
		
			public int getId() {
				return id;
			}

			public void setId(int id) {
				this.id = id;
			}

			public User getUser() {
				return user;
			}

			public void setUser(User user) {
				this.user = user;
			}

			public Equipment getEquipement() {
				return equipement;
			}

			public void setEquipement(Equipment equipement) {
				this.equipement = equipement;
			}

		
}
