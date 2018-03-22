package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Operation;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote;

public class TestOperation {


		public static void main(String[] args) throws NamingException {
			String jndiNameOperation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
			Context contextOperation = new InitialContext();
			OperationServiceRemote proxyOperation = (OperationServiceRemote) contextOperation.lookup(jndiNameOperation);
			Operation opt = new Operation();
			
			
			String jndiNameChargingStation = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context contextChargingStation = new InitialContext();
			ChargingStationServiceRemote proxyChargingStation = (ChargingStationServiceRemote) contextChargingStation.lookup(jndiNameChargingStation);
			ChargingStation ch = new ChargingStation();
			
			ch.setCode(10);

		
			ChargingStationPK chpk=proxyChargingStation.addChargingStation(1, 1, ch);
			System.out.println("created");
			
			opt.setDescription("done");
			proxyOperation.addOperation(1, chpk, opt);
			System.out.println("createddd");
			
			
			ChargingStation chs=proxyChargingStation.findChargingStation(1,1);
			ChargingStationPK chpk1= chs.getChargingstationPK();
			
			
			Operation optd=proxyOperation.findOperation(1, chpk1);
			System.out.println(optd.getDescription());
			
			
			proxyOperation.updateOperation(1, chpk1);
			System.out.println("modified");
			
//			proxyOperation.deleteOperation(1, chpk1);
//			System.out.println("deleted");
			
			
			
	}

}
