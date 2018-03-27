package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;

public class TestChargingStation {

	public static void main(String[] args) throws NamingException {
		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
		Context context = new InitialContext();
		ChargingStationServiceRemote proxy = (ChargingStationServiceRemote) context.lookup(jndiName);
		ChargingStation ch = new ChargingStation();
		
		ch.setCode(10);
		
//
//	
//		proxy.addChargingStation(2, 2, ch);
//		System.out.println("created");
//		
//		
//		
//		
//		ChargingStation chs=proxy.findChargingStation(1,1);
//		System.out.println(chs.getCode());
////		
//		
//		proxy.updateChargingStation(2,2,ch);
//		System.out.println("modified");
		
		
		//proxy.deleteChargingStation(1, 2);
		System.out.println("deleted");
		
		
	}

	
}
