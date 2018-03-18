package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "Article")
public class Article implements Serializable {

		private static final long serialVersionUID = 1L;
		@OneToMany(mappedBy="articleFils")
		private List<Nomenclature> nomenclatures1;
		
		@OneToMany(mappedBy="articlePere")
		private List<Nomenclature> nomenclatures;
		
		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		@Column(name = "Id")
		private int Id;
		@Column(name = "ArticleCode")
		private String ArticleCode;
		@Column(name = "Description")
		private String Description;
		@Column(name = "UnitCode")
		private String UnitCode;
		@Column(name = "Type")
		private String Type;
		@Column(name = "Pmp")
		private float Pmp;
		@Column(name = "Quantity")
		private int Quantity;
		public int getId() {
			return Id;
		}
		public void setId(int id) {
			Id = id;
		}
		public String getArticleCode() {
			return ArticleCode;
		}
		public void setArticleCode(String articleCode) {
			ArticleCode = articleCode;
		}
		public String getDescription() {
			return Description;
		}
		public void setDescription(String description) {
			Description = description;
		}
		public String getUnitCode() {
			return UnitCode;
		}
		public void setUnitCode(String unitCode) {
			UnitCode = unitCode;
		}
		public String getType() {
			return Type;
		}
		public void setType(String type) {
			Type = type;
		}
		public float getPmp() {
			return Pmp;
		}
		public void setPmp(float pmp) {
			Pmp = pmp;
		}
		public int getQuantity() {
			return Quantity;
		}
		public void setQuantity(int quantity) {
			Quantity = quantity;
		}
		
		public Article(int id, String articleCode, String description, String unitCode, String type, float pmp,
				int quantity) {
			super();
			Id = id;
			ArticleCode = articleCode;
			Description = description;
			UnitCode = unitCode;
			Type = type;
			Pmp = pmp;
			Quantity = quantity;
		}
		public Article() {
			super();
		}
		
		
		
}
