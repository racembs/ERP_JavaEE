package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "tab_Arboresence")
public class Arboresence implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="arboFils",fetch=FetchType.EAGER)
	private List<ArboPereFis> arbo;
	
	@OneToMany(mappedBy="arboPere",fetch=FetchType.EAGER)
	private List<ArboPereFis> arbos;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "name")
	private String name;
	
	
	
	
	
	

	@OneToMany(mappedBy="arboresence")
	private List <Equipment> equipments;
	
	
	
	
	

	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Equipment> getEquipments() {
		return equipments;
	}

	public void setEquipments(List<Equipment> equipments) {
		this.equipments = equipments;
	}


	



	




	

	
	
	
	
	

}