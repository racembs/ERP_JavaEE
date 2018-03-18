package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue(value="Tool")
public class Tool extends Need implements Serializable{

	private static final long serialVersionUID = 1L;
	@Column(name = "Nature")
	private String Nature;
	@Column(name = "Tool_Family")
	private String Tool_Family;
	@Column(name = "Availability")
	private String Availability;
	public String getAvailability() {
		return Availability;
	}
	public void setAvailability(String availability) {
		Availability = availability;
	}
	public String getNature() {
		return Nature;
	}
	public void setNature(String nature) {
		Nature = nature;
	}
	public String getTool_Family() {
		return Tool_Family;
	}
	public void setTool_Family(String tool_Family) {
		Tool_Family = tool_Family;
	}
	public Tool(String description, int quantity, String reference, String supplier, String code_Supplier, String brand,
			float price, String nature, String tool_Family) {
		super(description, quantity, reference, supplier, code_Supplier, brand, price);
		Nature = nature;
		Tool_Family = tool_Family;
	}
	public Tool() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Tool(String description, int quantity, String reference, String supplier, String code_Supplier, String brand,
			float price, String nature, String tool_Family, String availability) {
		super(description, quantity, reference, supplier, code_Supplier, brand, price);
		Nature = nature;
		Tool_Family = tool_Family;
		Availability = availability;
	}
	
	
}
