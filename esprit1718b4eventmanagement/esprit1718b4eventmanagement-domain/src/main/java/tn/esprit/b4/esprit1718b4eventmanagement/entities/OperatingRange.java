package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import java.lang.String;
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
	private int id;
	
	@Column(name = "Code")
	private String Code;
	
	@Column(name = "designation")
	private String designation;
	
	@Column(name = "StakingCondition")
	private String StakingCondition;
	
	@Column(name = "Deadline")
	private int Deadline;
	
	private static final long serialVersionUID = 1L;
	
	@ManyToMany(mappedBy="operatingranges")
	private List <Article> articles;
	
	@OneToMany(mappedBy="optrange")
	private List <Operation> operations;

	public List<Article> getArticles() {
		return articles;
	}
	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}
	public OperatingRange() {
		super();
	}   
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}   
	public String getCode() {
		return this.Code;
	}

	public void setCode(String Code) {
		this.Code = Code;
	}   
	public String getDesignation() {
		return this.designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}   
	public String getStakingCondition() {
		return this.StakingCondition;
	}

	public void setStakingCondition(String StakingCondition) {
		this.StakingCondition = StakingCondition;
	}   
	public int getDeadline() {
		return this.Deadline;
	}

	public void setDeadline(int Deadline) {
		this.Deadline = Deadline;
	}
	public List<Operation> getOperations() {
		return operations;
	}
	public void setOperations(List<Operation> operations) {
		this.operations = operations;
	}
	public OperatingRange(int id, String code, String designation, String stakingCondition, int deadline,
			List<Article> articles, List<Operation> operations) {
		super();
		this.id = id;
		Code = code;
		this.designation = designation;
		StakingCondition = stakingCondition;
		Deadline = deadline;
		this.articles = articles;
		this.operations = operations;
	}
	public OperatingRange(String code, String designation, String stakingCondition, int deadline) {
		super();
		
		Code = code;
		this.designation = designation;
		StakingCondition = stakingCondition;
		Deadline = deadline;
		
	}
   
	
}
