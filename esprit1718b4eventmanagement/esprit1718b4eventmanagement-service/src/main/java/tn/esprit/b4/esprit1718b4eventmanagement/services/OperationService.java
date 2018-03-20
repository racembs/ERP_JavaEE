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
	public void deleteOperation(int idOperatingRange, ChargingStationPK idChargingStation) {
		Operation Opt= findOperation(idOperatingRange, idChargingStation);
		em.remove(Opt);
		
	}

	@Override
	public void updateOperation(int idOperatingRange, ChargingStationPK idChargingStation) {
		Operation Opt= findOperation(idOperatingRange, idChargingStation);
		Opt.setDescription("Description");
		
	}

	@Override
	public Operation findOperation(int idOperatingRange, ChargingStationPK idChargingStation) {
		OperationPK Optpk = new OperationPK();
		Optpk.setId(idOperatingRange);
		Optpk.setIdChargingStation(idChargingStation);
		return em.find(Operation.class, Optpk);
	}

}
