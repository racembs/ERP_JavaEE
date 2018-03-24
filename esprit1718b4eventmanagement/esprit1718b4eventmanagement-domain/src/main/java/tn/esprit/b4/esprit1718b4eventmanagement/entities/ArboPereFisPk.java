package tn.esprit.b4.esprit1718b4eventmanagement.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ArboPereFisPk implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="idFils")
	private Integer idArboFils;
	@Column(name="idPere")
	private Integer idArboPere;
	public Integer getIdArboFils() {
		return idArboFils;
	}
	public void setIdArboFils(Integer idArboFils) {
		this.idArboFils = idArboFils;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArboFils == null) ? 0 : idArboFils.hashCode());
		result = prime * result + ((idArboPere == null) ? 0 : idArboPere.hashCode());
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
		if (idArboPere == null) {
			if (other.idArboPere != null)
				return false;
		} else if (!idArboPere.equals(other.idArboPere))
			return false;
		return true;
	}
	public Integer getIdArboPere() {
		return idArboPere;
	}
	public void setIdArboPere(Integer idArboPere) {
		this.idArboPere = idArboPere;
	}
	


}
