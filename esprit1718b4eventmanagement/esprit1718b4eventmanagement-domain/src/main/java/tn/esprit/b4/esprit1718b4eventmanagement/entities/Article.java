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
@Table(name = "Article")
public class Article implements Serializable {

		private static final long serialVersionUID = 1L;
		@OneToMany(mappedBy="articleFils",fetch=FetchType.EAGER)
		private List<Nomenclature> nomenclatures1;
		
		@OneToMany(mappedBy="articlePere",fetch=FetchType.EAGER)
		private List<Nomenclature> nomenclatures;
		
		@OneToMany(mappedBy="article",fetch=FetchType.EAGER)
		private List<OrdredItem> orderItem = new ArrayList<>();
		
		@ManyToMany(fetch=FetchType.EAGER)
		private List <OperatingRange> operatingranges;
		
		public void setOperatingranges(List<OperatingRange> operatingranges) {
			this.operatingranges = operatingranges;
		}
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
		@Column(name = "ReservedQuantity")
		private int ReservedQuantity=0;
		@Column(name = "DailyConsumption")
		private int DailyConsumption=0;
		@Column(name = "DeliveryTime")
		private int DeliveryTime=0;
		@Column(name = "PricipalQuantity",columnDefinition = "int default 100")
		private int PricipalQuantity=100;
		@Column(name = "etatOrdre",columnDefinition = "int default 0")
		private int etatOrdre=0;
	
		public int getEtatOrdre() {
			return etatOrdre;
		}
		public void setEtatOrdre(int etatOrdre) {
			this.etatOrdre = etatOrdre;
		}
		public int getPricipalQuantity() {
			return PricipalQuantity;
		}
		public void setPricipalQuantity(int pricipalQuantity) {
			PricipalQuantity = pricipalQuantity;
		}
		
		
		public int getDailyConsumption() {
			return DailyConsumption;
		}
		public void setDailyConsumption(int dailyConsumption) {
			DailyConsumption = dailyConsumption;
		}
		public int getDeliveryTime() {
			return DeliveryTime;
		}
		public void setDeliveryTime(int deliveryTime) {
			DeliveryTime = deliveryTime;
		}
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
		
		public Article(String articleCode, String description, String unitCode, String type, float pmp,
				int quantity) {
			super();
			
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
		public List<Nomenclature> getNomenclatures1() {
			return nomenclatures1;
		}
		public void setNomenclatures1(List<Nomenclature> nomenclatures1) {
			this.nomenclatures1 = nomenclatures1;
		}
		public List<Nomenclature> getNomenclatures() {
			return nomenclatures;
		}
		public void setNomenclatures(List<Nomenclature> nomenclatures) {
			this.nomenclatures = nomenclatures;
		}
		public List<OrdredItem> getOrderItem() {
			return orderItem;
		}
		public void setOrderItem(List<OrdredItem> orderItem) {
			this.orderItem = orderItem;
		}
		public List<OperatingRange> getOperatingranges() {
			return operatingranges;
		}
		public int getReservedQuantity() {
			return ReservedQuantity;
		}
		public void setReservedQuantity(int reservedQuantity) {
			ReservedQuantity = reservedQuantity;
		}
		
		
		
}
