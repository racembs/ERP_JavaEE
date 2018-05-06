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
	

	
	
	private static final long serialVersionUID = 1L;
	
	
	@EmbeddedId
	@Column(name = "ReclamationPK")
	private ReclamationPk reclamationPK;
	
	@ManyToOne 
	@JoinColumn(name="idUser",referencedColumnName="id",insertable=false,updatable=false)
	private User User;
	
	@ManyToOne
	@JoinColumn(name="Id",referencedColumnName="Id",insertable=false,updatable=false)
	private Article article;
	
	

	public Reclamation() {
		super();
	}



	public Reclamation(int code, String subject, String description, Date datecreation, ReclamationPk reclamationPK,
			tn.esprit.b4.esprit1718b4eventmanagement.entities.User user, Article article) {
		super();
		this.code = code;
		this.subject = subject;
		this.description = description;
		this.datecreation = datecreation;
		this.reclamationPK = reclamationPK;
		User = user;
		this.article = article;
	}



	public User getUser() {
		return User;
	}



	public void setUser(User user) {
		User = user;
	}



	public Article getArticle() {
		return article;
	}



	public void setArticle(Article article) {
		this.article = article;
	}



	public void setReclamationPK(ReclamationPk reclamationPK) {
		this.reclamationPK = reclamationPK;
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





	



	public ReclamationPk getReclamationPK() {
		return reclamationPK;
	}





	
	


 

	
}
