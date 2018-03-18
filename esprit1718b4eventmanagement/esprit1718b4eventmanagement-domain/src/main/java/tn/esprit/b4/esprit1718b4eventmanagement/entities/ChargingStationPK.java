package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class ChargingStationPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="id_equipement")
	private Integer id_equipment;
	
	@Column(name="idUser")
	private Integer idUser;
	
	public Integer getId_equipment() {
		return id_equipment;
	}
	public void setId_equipment(Integer id_equipment) {
		this.id_equipment = id_equipment;
	}
	public Integer getIdUser() {
		return idUser;
	}
	public void setIdUser(Integer idUser) {
		this.idUser = idUser;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
		result = prime * result + ((id_equipment == null) ? 0 : id_equipment.hashCode());
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
		ChargingStationPK other = (ChargingStationPK) obj;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		if (id_equipment == null) {
			if (other.id_equipment != null)
				return false;
		} else if (!id_equipment.equals(other.id_equipment))
			return false;
		return true;
	}
	
}