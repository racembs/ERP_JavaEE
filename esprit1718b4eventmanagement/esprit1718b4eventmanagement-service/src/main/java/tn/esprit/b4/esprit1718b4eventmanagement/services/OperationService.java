package tn.esprit.b4.esprit1718b4eventmanagement.services;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperationPK;

@Stateless
public class OperationService implements OperationServiceLocal, OperationServiceRemote {
	@PersistenceContext
	EntityManager em;
	
	@Override
	public OperationPK addOperation(int idOperatingRange, ChargingStationPK idChargingStation, Operation operations) {

		OperationPK Optpk = new OperationPK();
		Optpk.setIdChargingStation(idChargingStation);
		Optpk.setId(idOperatingRange);
		operations.setOperationPK(Optpk);
		em.persist(operations);
		return Optpk;
		
	}

	@Override
	public void deleteOperation(int idOperatingRange, ChargingStationPK WorkStationPK) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateOperation(int idOperatingRange, ChargingStationPK WorkStationPK) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Operation findOperation(int idOperatingRange, ChargingStationPK WorkStationPK) {
		// TODO Auto-generated method stub
		return null;
	}

}
