package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: OperatingRange
 *
 */
@Entity

public class OperatingRange implements Serializable {

	   
	@Id
	@Column(name = "IdOptRange")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idoptrange;
	
	@Column(name = "Code" , nullable=true)
	private String code;
	
	@Column(name = "designation" , nullable=true)
	private String designation;
	
	@Column(name = "StakingCondition" , nullable=true)
	private String stakingcondition;
	
	@Column(name = "Deadline" , nullable=true)
	private int deadline;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="operatingranges")
	private List <Article> articles = new ArrayList<>();
	
	@OneToMany(mappedBy="optrange")
	private List <Operation> operations;

	public int getIdoptrange() {
		return idoptrange;
	}

	public void setIdoptrange(int idoptrange) {
		this.idoptrange = idoptrange;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getStakingcondition() {
		return stakingcondition;
	}

	public void setStakingcondition(String stakingcondition) {
		this.stakingcondition = stakingcondition;
	}

	public int getDeadline() {
		return deadline;
	}

	public void setDeadline(int deadline) {
		this.deadline = deadline;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public List<Operation> getOperations() {
		return operations;
	}

	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}

	public OperatingRange(int idoptrange, String code, String designation, String stakingcondition, int deadline,
			List<Article> articles, List<Operation> operations) {
		super();
		this.idoptrange = idoptrange;
		this.code = code;
		this.designation = designation;
		this.stakingcondition = stakingcondition;
		this.deadline = deadline;
		this.articles = articles;
		this.operations = operations;
	}

	public OperatingRange(String code, String designation, String stakingcondition, int deadline) {
		super();
		this.code = code;
		this.designation = designation;
		this.stakingcondition = stakingcondition;
		this.deadline = deadline;
	}

	public OperatingRange() {
		super();
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	
}
