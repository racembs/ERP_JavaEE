package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ManufactOrderNomenclaturePk implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="idParent")
	private Integer idParent;
	@Column(name="idChild")
	private Integer idChild;
	public Integer getIdParent() {
		return idParent;
	}
	public void setIdParent(Integer idParent) {
		this.idParent = idParent;
	}
	public Integer getIdChild() {
		return idChild;
	}
	public void setIdChild(Integer idChild) {
		this.idChild = idChild;
	}
	public ManufactOrderNomenclaturePk(Integer idParent, Integer idChild) {
		super();
		this.idParent = idParent;
		this.idChild = idChild;
	}
	public ManufactOrderNomenclaturePk() {
		super();
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idChild == null) ? 0 : idChild.hashCode());
		result = prime * result + ((idParent == null) ? 0 : idParent.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ManufactOrderNomenclaturePk other = (ManufactOrderNomenclaturePk) obj;
		if (idChild == null) {
			if (other.idChild != null)
				return false;
		} else if (!idChild.equals(other.idChild))
			return false;
		if (idParent == null) {
			if (other.idParent != null)
				return false;
		} else if (!idParent.equals(other.idParent))
			return false;
		return true;
	}
	
	
}
