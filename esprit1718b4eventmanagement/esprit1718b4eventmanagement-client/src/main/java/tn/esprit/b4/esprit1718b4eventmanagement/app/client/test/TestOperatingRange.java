package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote;

public class TestOperatingRange {

	public static void main(String[] args) throws NamingException, ParseException {
		String OperatingRangejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/OperatingRangeService!tn.esprit.b4.esprit1718b4eventmanagement.services.OperatingRangeServiceRemote";

		Context context = new InitialContext();
		OperatingRangeServiceRemote proxy =  (OperatingRangeServiceRemote) context.lookup(OperatingRangejndiName);

//		OperatingRange optrange = new OperatingRange();
//		proxy.addOperatingRange(optrange);
		
		OperatingRange optrange = new OperatingRange("AR","Table mount","Series",30);
		proxy.addOperatingRange(optrange);
		System.out.println("created");
		
//		proxy.deleteOperatingRange(1);
//		System.out.println("deleted");
		
//		proxy.updateOperatingRange(1);
//		System.out.println("modified");
		
		String des=proxy.findOperatingRange(1);
		System.out.println(des);
		
	}

}
