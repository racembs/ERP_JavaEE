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
		ChargingStationPK  workStationPK = new ChargingStationPK();
		workStationPK.setId_equipment(1);
		workStationPK.setIdUser(1);
		ch.setWorkStationPK(workStationPK);
		proxy.ajouterChargStation(ch);
	}

}
