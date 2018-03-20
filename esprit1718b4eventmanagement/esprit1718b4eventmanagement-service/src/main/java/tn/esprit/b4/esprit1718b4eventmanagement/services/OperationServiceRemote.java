package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperationPK;

@Remote
public interface OperationServiceRemote {
	
public OperationPK addOperation(int idOperatingRange, ChargingStationPK WorkStationPK, Operation operations);
	
	public void deleteOperation(int idOperatingRange, ChargingStationPK WorkStationPK);
	
	public void updateOperation(int idOperatingRange, ChargingStationPK WorkStationPK);
	
	public Operation findOperation(int idOperatingRange, ChargingStationPK WorkStationPK);

}
