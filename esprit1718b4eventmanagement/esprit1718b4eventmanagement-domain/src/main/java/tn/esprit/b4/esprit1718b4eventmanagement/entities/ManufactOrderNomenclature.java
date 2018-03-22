package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: ManufactOrderNomenclature
 *
 */
@Entity

public class ManufactOrderNomenclature implements Serializable {

	@EmbeddedId
	private ManufactOrderNomenclaturePk manufactOrderNomenclaturePk;
	
	@ManyToOne
	@JoinColumn(name="idChild",referencedColumnName="id"
	,insertable=false,updatable=false)
	private ManufacturingOrder child;
	
	@ManyToOne
	@JoinColumn(name="idParent",referencedColumnName="id"
	,insertable=false,updatable=false)
	private ManufacturingOrder parent;

	@Column(name = "Level")
	private int level;
	
	private static final long serialVersionUID = 1L;

	public ManufactOrderNomenclature() {
		super();
	}

	public ManufactOrderNomenclaturePk getManufactOrderNomenclaturePk() {
		return manufactOrderNomenclaturePk;
	}

	public void setManufactOrderNomenclaturePk(ManufactOrderNomenclaturePk manufactOrderNomenclaturePk) {
		this.manufactOrderNomenclaturePk = manufactOrderNomenclaturePk;
	}

	public ManufacturingOrder getChild() {
		return child;
	}

	public void setChild(ManufacturingOrder child) {
		this.child = child;
	}

	public ManufacturingOrder getParent() {
		return parent;
	}

	public void setParent(ManufacturingOrder parent) {
		this.parent = parent;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
   
}
