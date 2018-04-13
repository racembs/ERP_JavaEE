package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import java.util.List;
import javax.persistence.*;


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
	private String EISDate;
	@Column(name = "Fabriquant")
	private String Fabriquant;
	@Column(name = "Marque")
	private String Marque;
	@Column(name = "Lieu")
	private String Lieu;
	@Column(name = "image")
	private String image;
	
	
	@OneToMany(mappedBy="equipement")
	private List <ChargingStation> chargingstations;
	
	@ManyToOne
	@JoinColumn(name="id_Arboe",referencedColumnName="Id",insertable=true,updatable=true)
	private Arboresence arboresence;
	
@OneToMany(mappedBy="equipement")
private List <Works> works;
	
	
	
	public String getLieu() {
		return Lieu;
	}
	public void setLieu(String lieu) {
		Lieu = lieu;
	}
	public Equipment(String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque, Arboresence arboresence) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
		this.arboresence = arboresence;
	}
	public Arboresence getArboresence() {
		return arboresence;
	}
	public void setArboresence(Arboresence arboresence) {
		this.arboresence = arboresence;
	}
	
	
	
	
	public Equipment(List<ChargingStation> chargingstations) {
		super();
		this.chargingstations = chargingstations;
		
		
		
		
	}
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
	
	public Equipment() {
		super();
	}

	public String getEISDate() {
		return EISDate;
	}
	public void setEISDate(String eISDate) {
		EISDate = eISDate;
	}
	public String getFabriquant() {
		return Fabriquant;
	}
	public void setFabriquant(String fabriquant) {
		Fabriquant = fabriquant;
	}
	public String getMarque() {
		return Marque;
	}
	public void setMarque(String marque) {
		Marque = marque;
	}
	

	
	public Equipment(String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque, List<ChargingStation> chargingstations) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
		this.chargingstations = chargingstations;
	}
	
	public Equipment(int id, String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque, List<ChargingStation> chargingstations) {
		super();
		Id = id;
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
		this.chargingstations = chargingstations;
	}
	public List<ChargingStation> getChargingstations() {
		return chargingstations;
	}
	public void setChargingstations(List<ChargingStation> chargingstations) {
		this.chargingstations = chargingstations;
	}
	public Equipment(String serialNum, String description, String state, String fabriquant, String marque,
			Arboresence arboresence) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		Fabriquant = fabriquant;
		Marque = marque;
		this.arboresence = arboresence;
	}
	public Equipment(String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
	}
	public Equipment(String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque, String lieu, Arboresence arboresence) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
		Lieu = lieu;
		this.arboresence = arboresence;
	}
	public List<Works> getWorks() {
		return works;
	}
	public void setWorks(List<Works> works) {
		this.works = works;
	}
	public Equipment(String serialNum, String description, String state, String eISDate, String fabriquant,
			String marque, String lieu, String image, Arboresence arboresence) {
		super();
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
		Fabriquant = fabriquant;
		Marque = marque;
		Lieu = lieu;
		this.image = image;
		this.arboresence = arboresence;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}


	
	
}
