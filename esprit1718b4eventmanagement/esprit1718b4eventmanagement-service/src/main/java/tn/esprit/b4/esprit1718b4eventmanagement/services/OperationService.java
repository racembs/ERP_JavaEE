package tn.esprit.b4.esprit1718b4eventmanagement.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OrdredItem;
import tn.esprit.b4.esprit1718b4eventmanagement.utilities.GenericDAO;

@Stateless
public class OperationService extends GenericDAO<Operation> implements OperationServiceLocal, OperationServiceRemote {
	

	@PersistenceContext
	EntityManager em;
	public OperationService() {
		super(Operation.class);
		// TODO Auto-generated constructor stub
	}
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
	
	@Override
	public List<Operation> DisplayOperation() {

		TypedQuery<Operation> query=em.createQuery("SELECT o FROM Operation o",Operation.class);
		List <Operation> result= query.getResultList();
		return result;
	}
	
	@Override
	public List<Operation> findOprationByChargId(int idOperationgRange) {
		TypedQuery<Operation> query
		=em.createQuery("SELECT o FROM Operation o WHERE o.operationPK.id =:idOperationgRange", Operation.class);
		query.setParameter("idOperationgRange",idOperationgRange);
		List<Operation> list=query.getResultList();
		return list;
	}

}
