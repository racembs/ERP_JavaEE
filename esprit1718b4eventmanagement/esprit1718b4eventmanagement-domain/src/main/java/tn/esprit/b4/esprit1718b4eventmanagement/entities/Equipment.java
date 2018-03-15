package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tab_Equipment")
public class Equipment implements Serializable {

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private int Id;
	@Column(name = "SerialNum")
	private String SerialNum;
	@Column(name = "Description")
	private String Description;
	@Column(name = "State")
	private String State;
	@Column(name = "EISDate")
	private String EISDate;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	public String getSerialNum() {
		return SerialNum;
	}
	public void setSerialNum(String serialNum) {
		SerialNum = serialNum;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getEISDate() {
		return EISDate;
	}
	public void setEISDate(String eISDate) {
		EISDate = eISDate;
	}
	
	public Equipment() {
		super();
	}
	public Equipment( String serialNum, String description, String state, String eISDate) {
		super();
		
		SerialNum = serialNum;
		Description = description;
		State = state;
		EISDate = eISDate;
	}

}
