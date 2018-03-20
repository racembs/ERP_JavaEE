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
			String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperationService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperationServiceRemote";
			Context context = new InitialContext();
			OperationServiceRemote proxy = (OperationServiceRemote) context.lookup(jndiName);
			Operation opt = new Operation();
			
			
			String jndiName1 = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
			Context context1 = new InitialContext();
			ChargingStationServiceRemote proxy1 = (ChargingStationServiceRemote) context1.lookup(jndiName1);
			ChargingStation ch = new ChargingStation();
			
//			ch.setCode(10);
//
//		
//			ChargingStationPK chpk=proxy1.addChargingStation(1, 1, ch);
//			System.out.println("created");
//			
//			opt.setDescription("done");
//			proxy.addOperation(1, chpk, opt);
//			System.out.println("createddd");
//			
			
			ChargingStation chs=proxy1.findChargingStation(1,1);
			ChargingStationPK chpk1= chs.getWorkStationPK();
			
			
			Operation optd=proxy.findOperation(1, chpk1);
			System.out.println(optd.getDescription());
			
			
			proxy.updateOperation(1, chpk1);
			System.out.println("modified");
			
			proxy.deleteOperation(1, chpk1);
			System.out.println("deleted");
	}

}
