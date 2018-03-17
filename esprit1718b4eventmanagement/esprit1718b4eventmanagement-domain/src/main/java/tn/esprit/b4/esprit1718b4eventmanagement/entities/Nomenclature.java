package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_Nomenclature")
public class Nomenclature {
	private static final long serialVersionUID = 1L;
	
	@OneToMany
	private List<Article> articles;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "Quantity")
	private int Quantity;
	
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	
	
	public List<Article> getArticle() {
		return articles;
	}
	public void setArticle(List<Article> article) {
		this.articles = article;
	}
	
	public Nomenclature(List<Article> article, int id, int quantity) {
		super();
		this.articles = article;
		Id = id;
		Quantity = quantity;
	}
	public Nomenclature() {
		super();
	}

	
	
	
}
