package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Client
 *
 */
@Entity

public class Client implements Serializable {

	   
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private int code;
	private String company;
	private String email;
	private long phoneNumber;
	@OneToMany(mappedBy="client")
	private List<Orders> orders = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	public Client() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public int getCode() {
		return this.code;
	}

	public void setCode(int code) {
		this.code = code;
	}   
	public String getCompany() {
		return this.company;
	}

	public void setCompany(String company) {
		this.company = company;
	}
	public Client(int code, String company) {
		super();
		this.code = code;
		this.company = company;
	}
   
}
