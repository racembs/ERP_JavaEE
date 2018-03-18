package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="SpareParts")
public class SpareParts extends Need implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Column(name = "Method")
	private String Method;
	@Column(name = "Family")
	private String Family;
	public String getMethod() {
		return Method;
	}
	public void setMethod(String method) {
		Method = method;
	}
	public String getFamily() {
		return Family;
	}
	public void setFamily(String family) {
		Family = family;
	}
	public SpareParts(String description, int quantity, String reference, String supplier, String code_Supplier,
			String brand, float price, String method, String family) {
		super(description, quantity, reference, supplier, code_Supplier, brand, price);
		Method = method;
		Family = family;
	}
	public SpareParts() {
		super();
	}
	
	

}
