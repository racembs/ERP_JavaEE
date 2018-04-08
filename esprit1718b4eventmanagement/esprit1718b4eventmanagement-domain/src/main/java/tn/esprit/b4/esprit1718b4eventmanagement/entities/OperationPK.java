package tn.esprit.b4.esprit1718b4eventmanagement.entities;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
@Embeddable
public class OperationPK implements Serializable{
	private static final long serialVersionUID = 1L;
	@Column(name="id_ChargingStation")
	private ChargingStationPK idChargingStation;
	
	@Column(name="id_OperatingRange")
	private Integer id;

	public ChargingStationPK getIdChargingStation() {
		return idChargingStation;
	}

	public void setIdChargingStation(ChargingStationPK idChargingStation) {
		this.idChargingStation = idChargingStation;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((idChargingStation == null) ? 0 : idChargingStation.hashCode());
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
		OperationPK other = (OperationPK) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (idChargingStation == null) {
			if (other.idChargingStation != null)
				return false;
		} else if (!idChargingStation.equals(other.idChargingStation))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "OperationPK [idChargingStation=" + idChargingStation + ", id=" + id + "]";
	}
	
	
	
	
}
