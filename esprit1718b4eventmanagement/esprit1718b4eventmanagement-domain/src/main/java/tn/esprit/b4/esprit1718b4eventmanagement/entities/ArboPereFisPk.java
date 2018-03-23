package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArboPereFisPk implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="idArboFils")
	private Integer idArboFils;
	@Column(name="idArboPere")
	private Integer idArticlesFils;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArboFils == null) ? 0 : idArboFils.hashCode());
		result = prime * result + ((idArticlesFils == null) ? 0 : idArticlesFils.hashCode());
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
		ArboPereFisPk other = (ArboPereFisPk) obj;
		if (idArboFils == null) {
			if (other.idArboFils != null)
				return false;
		} else if (!idArboFils.equals(other.idArboFils))
			return false;
		if (idArticlesFils == null) {
			if (other.idArticlesFils != null)
				return false;
		} else if (!idArticlesFils.equals(other.idArticlesFils))
			return false;
		return true;
	}
	public Integer getIdArboFils() {
		return idArboFils;
	}
	public void setIdArboFils(Integer idArboFils) {
		this.idArboFils = idArboFils;
	}
	public Integer getIdArticlesFils() {
		return idArticlesFils;
	}
	public void setIdArticlesFils(Integer idArticlesFils) {
		this.idArticlesFils = idArticlesFils;
	}


}
