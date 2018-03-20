package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: UserTest
 *
 */
@Entity

public class User implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(name = "code")
	private Long code;
	
	
	
	@Column(name = "fisrtname")
	private String firstname;
	@Column(name = "lastname")
	private String lastname;
	
	@Column(name = "login")
	private String login;
	@Column(name = "password")
	private String password;
	@Column(name = "email")
	private String email;
	@Column(name = "role")
	private String role;
	@Column(name = "numtel")
	private String numtel;
	@Column(name = "statut")
	private String statut;
	@Column(name = "nb")
	private String nb;

	@OneToMany(mappedBy="User")
	private List <ChargingStation> chargingstations;
	
	private static final long serialVersionUID = 1L;

	
	
	
	
	
	
	
	
	public List<ChargingStation> getChargingstations() {
		return chargingstations;
	}
	public void setChargingstations(List<ChargingStation> chargingstations) {
		this.chargingstations = chargingstations;
	}
	public User() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	
	public Long getCode() {
		return code;
	}
	public void setCode(Long code) {
		this.code = code;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	public String getNumtel() {
		return numtel;
	}
	public void setNumtel(String numtel) {
		this.numtel = numtel;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getNb() {
		return nb;
	}
	public void setNb(String nb) {
		this.nb = nb;
	}
	public User(String firstname, String lastname, String login, String password, String email, String numtel) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.numtel = numtel;
	}

	public User(int id, Long code, String firstname, String lastname, String login, String password, String email,
			String role, String numtel, String statut, List<ChargingStation> chargingstations) {
		super();
		this.id = id;
		this.code = code;
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.numtel = numtel;
		this.statut = statut;
		this.chargingstations = chargingstations;
	}
	
	

	public User(String firstname, String lastname, String login, String password, String email, String numtel,
			String nb) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.numtel = numtel;
		this.nb = nb;
	}
	public User(String firstname, String lastname, String login, String password, String email, String role,
			String numtel, String statut, String nb) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.numtel = numtel;
		this.statut = statut;
		this.nb = nb;
	}
	public User(String firstname, String lastname, String login, String password, String email, String role,
			String numtel, String statut) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.login = login;
		this.password = password;
		this.email = email;
		this.role = role;
		this.numtel = numtel;
		this.statut = statut;
	}


	
}
