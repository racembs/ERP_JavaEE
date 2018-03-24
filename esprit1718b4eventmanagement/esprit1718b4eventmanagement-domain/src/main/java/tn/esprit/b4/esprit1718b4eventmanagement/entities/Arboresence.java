package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.ManyToMany;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tab_Arboresence")
public class Arboresence implements Serializable {


	private static final long serialVersionUID = 1L;
	@OneToMany(mappedBy="arboFils",fetch=FetchType.EAGER)
	private List<ArboPereFis> arboFis;
	
	@OneToMany(mappedBy="arboPere",fetch=FetchType.EAGER)
	private List<ArboPereFis> arboPer;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "name")
	private String name;
	
	@Column(name = "type")
	private String type;
	@Column(name = "code")
	private String code;
	
	


	public Arboresence(String name, String type, String code) {
		super();
		this.name = name;
		this.type = type;
		this.code = code;
	}

	public List<ArboPereFis> getArboPer() {
		return arboPer;
	}

	public void setArboPer(List<ArboPereFis> arboPer) {
		this.arboPer = arboPer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Arboresence() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Arboresence(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	@OneToMany(mappedBy="arboresence")
	private List <Equipment> equipments;
	
	
	
	
	

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

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

	public List<ArboPereFis> getArboFis() {
		return arboFis;
	}

	public void setArboFis(List<ArboPereFis> arboFis) {
		this.arboFis = arboFis;
	}




	



	




	

	
	
	
	
	

}