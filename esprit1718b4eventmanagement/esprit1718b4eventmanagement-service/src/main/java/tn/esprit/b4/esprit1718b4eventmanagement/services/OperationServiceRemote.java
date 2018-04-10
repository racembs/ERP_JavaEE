package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Remote;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.IGenericDAO;

@Remote
public interface OperationServiceRemote  extends IGenericDAO<Operation> {
	
public OperationPK addOperation(int idOperatingRange, ChargingStationPK WorkStationPK, Operation operations);
	
	public void deleteOperation(int idOperatingRange, ChargingStationPK WorkStationPK);
	
	public void updateOperation(int idOperatingRange, ChargingStationPK WorkStationPK);
	
	public Operation findOperation(int idOperatingRange, ChargingStationPK WorkStationPK);

	public List<Operation> DisplayOperation();
	
	public List<Operation> findOprationByChargId(int idOperationgRange);
	public List<Operation> findOpration(String description);
}
