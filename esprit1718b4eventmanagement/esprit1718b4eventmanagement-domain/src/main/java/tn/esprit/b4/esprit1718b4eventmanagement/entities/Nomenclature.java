package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.util.List;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tab_Nomenclature")
public class Nomenclature {
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private NomenclaturePk nomenclauturePk;
	
	@ManyToOne
	@JoinColumn(name="idArticlesFils",referencedColumnName="Id"
	,insertable=false,updatable=false)
	private Article articleFils;
	
	@ManyToOne
	@JoinColumn(name="idArticlePere",referencedColumnName="Id"
	,insertable=false,updatable=false)
	private Article articlePere;

	@Column(name = "Quantity")
	private int Quantity;
	public NomenclaturePk getNomenclauturePk() {
		return nomenclauturePk;
	}
	public void setNomenclauturePk(NomenclaturePk nomenclauturePk) {
		this.nomenclauturePk = nomenclauturePk;
	}
	public Article getArticlePere() {
		return articlePere;
	}
	public void setArticlePere(Article articlePere) {
		this.articlePere = articlePere;
	}
	public Article getArticleFils() {
		return articleFils;
	}
	public void setArticleFils(Article articleFils) {
		this.articleFils = articleFils;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	

	
	
	
}
