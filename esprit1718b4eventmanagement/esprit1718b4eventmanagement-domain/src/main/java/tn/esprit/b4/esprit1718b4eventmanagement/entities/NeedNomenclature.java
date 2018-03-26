package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: NeedNomenclature
 *
 */
@Entity

public class NeedNomenclature implements Serializable {

	@EmbeddedId
	private NeedNomenclaturePk needNomenclaturePk;
	
	@ManyToOne
	@JoinColumn(name="idChild",referencedColumnName="id"
	,insertable=false,updatable=false)
	private NeededItem child;
	
	@ManyToOne
	@JoinColumn(name="idParent",referencedColumnName="id"
	,insertable=false,updatable=false)
	private NeededItem parent;

	@Column(name = "quantity")
	private int quantity;
	
	private static final long serialVersionUID = 1L;

	public NeedNomenclature() {
		super();
	}

	public NeededItem getChild() {
		return child;
	}

	public void setChild(NeededItem child) {
		this.child = child;
	}

	public NeededItem getParent() {
		return parent;
	}

	public void setParent(NeededItem parent) {
		this.parent = parent;
	}

	public NeedNomenclaturePk getNeedNomenclaturePk() {
		return needNomenclaturePk;
	}

	public void setNeedNomenclaturePk(NeedNomenclaturePk needNomenclaturePk) {
		this.needNomenclaturePk = needNomenclaturePk;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	

	
   
}
