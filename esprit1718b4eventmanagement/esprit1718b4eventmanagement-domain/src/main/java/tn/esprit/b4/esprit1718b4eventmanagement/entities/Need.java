package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name = "tab_Need")
public class Need implements Serializable{
	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_Need")
	private int id_Need;
	@Column(name = "Description")
	private String Description;
	@Column(name = "Quantity")
	private int Quantity;
	@Column(name = "Reference")
	private String Reference;
	@Column(name = "Supplier")
	private String Supplier;
	@Column(name = "Code_Supplier")
	private String Code_Supplier;
	@Column(name = "Brand")
	private String Brand;
	@Column(name = "Price")
	private float Price;
	
	public int getId_Need() {
		return id_Need;
	}
	public void setId_Need(int id_Need) {
		this.id_Need = id_Need;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public int getQuantity() {
		return Quantity;
	}
	public void setQuantity(int quantity) {
		Quantity = quantity;
	}
	public String getReference() {
		return Reference;
	}
	public void setReference(String reference) {
		Reference = reference;
	}
	public String getSupplier() {
		return Supplier;
	}
	public void setSupplier(String supplier) {
		Supplier = supplier;
	}
	public String getCode_Supplier() {
		return Code_Supplier;
	}
	public void setCode_Supplier(String code_Supplier) {
		Code_Supplier = code_Supplier;
	}
	public String getBrand() {
		return Brand;
	}
	public void setBrand(String brand) {
		Brand = brand;
	}
	public float getPrice() {
		return Price;
	}
	public void setPrice(float price) {
		Price = price;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public Need(String description, int quantity, String reference, String supplier, String code_Supplier, String brand,
			float price) {
		super();
		Description = description;
		Quantity = quantity;
		Reference = reference;
		Supplier = supplier;
		Code_Supplier = code_Supplier;
		Brand = brand;
		Price = price;
	}
	public Need() {
		super();
	}
	
	
	
	

}
