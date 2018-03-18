package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class NomenclaturePk implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="idArticlePere")
	private Integer idArticlePere;
	@Column(name="idArticlesFils")
	private Integer idArticlesFils;
	
	public Integer getIdArticlePere() {
		return idArticlePere;
	}
	public void setIdArticlePere(Integer idArticlePere) {
		this.idArticlePere = idArticlePere;
	}
	public Integer getIdArticleFils() {
		return idArticlesFils;
	}
	public void setIdArticleFils(Integer idArticleFils) {
		this.idArticlesFils = idArticleFils;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idArticlePere == null) ? 0 : idArticlePere.hashCode());
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
		NomenclaturePk other = (NomenclaturePk) obj;
		if (idArticlePere == null) {
			if (other.idArticlePere != null)
				return false;
		} else if (!idArticlePere.equals(other.idArticlePere))
			return false;
		if (idArticlesFils == null) {
			if (other.idArticlesFils != null)
				return false;
		} else if (!idArticlesFils.equals(other.idArticlesFils))
			return false;
		return true;
	}
	
	

}
