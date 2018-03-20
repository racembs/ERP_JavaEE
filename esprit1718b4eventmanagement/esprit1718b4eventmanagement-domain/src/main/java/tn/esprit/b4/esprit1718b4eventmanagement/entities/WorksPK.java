package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class WorksPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="id_equipement")
	private Integer idEquipment;
	@Column(name="id_user")
	private Integer idUser;
	public Integer getIdEquipment() {
		return idEquipment;
	}
	public void setIdEquipment(Integer idEquipment) {
		this.idEquipment = idEquipment;
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
		result = prime * result + ((idEquipment == null) ? 0 : idEquipment.hashCode());
		result = prime * result + ((idUser == null) ? 0 : idUser.hashCode());
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
		WorksPK other = (WorksPK) obj;
		if (idEquipment == null) {
			if (other.idEquipment != null)
				return false;
		} else if (!idEquipment.equals(other.idEquipment))
			return false;
		if (idUser == null) {
			if (other.idUser != null)
				return false;
		} else if (!idUser.equals(other.idUser))
			return false;
		return true;
	}
	public WorksPK(Integer idEquipment, Integer idUser) {
		super();
		this.idEquipment = idEquipment;
		this.idUser = idUser;
	}
	public WorksPK() {
		super();
	}
	
}
