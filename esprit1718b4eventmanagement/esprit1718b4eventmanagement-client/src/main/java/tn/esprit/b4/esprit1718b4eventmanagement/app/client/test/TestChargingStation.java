package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStation;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ChargingStationPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote;

public class TestChargingStation {

	public static void main(String[] args) throws NamingException {
		String jndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/ChargingStationService!tn.esprit.b4.esprit1718b4eventmanagement.services.ChargingStationServiceRemote";
		Context context = new InitialContext();
		ChargingStationServiceRemote proxy = (ChargingStationServiceRemote) context.lookup(jndiName);
		ChargingStation ch = new ChargingStation();
		
		ch.setCode(10);

//		ch.getEquipement().setId(1);
//		ch.getUser().setId(1);
//		int idE= ch.getEquipement().getId();
//		int idU= ch.getUser().getId();
//	    proxy.addChargingStation(idE, idU, ch);
		proxy.addChargingStation(1, 1, ch);
		System.out.print("created");
	}

}
