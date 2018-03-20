package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.SpareParts;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Tool;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote;


public class TestNeeds {
	public static void main(String[] args) throws NamingException, ParseException {
		String TOOLjndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedToolService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedToolServiceRemote";
		String SparejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/NeedSparePartsService!tn.esprit.b4.esprit1718b4eventmanagement.services.NeedSparePartsServiceRemote";

		Context context = new InitialContext();
		NeedToolServiceRemote proxy =  (NeedToolServiceRemote) context.lookup(TOOLjndiName);
		NeedSparePartsServiceRemote proxy2 =  (NeedSparePartsServiceRemote) context.lookup(SparejndiName);
		
		Tool newTool = new Tool("desc1",100,"ref1","supp","code_supp1","brand1",(float) 20.3,"nature","family","available");
		proxy.addTool(newTool);
		System.out.println("tool created");
		
		SpareParts newSpare = new SpareParts("descS1",120,"refS1","supp1","code_supp1","brand1",(float) 30.74,"","Sfamily1");
		proxy2.addSpareParts(newSpare);
		System.out.println("SparePart created");
		
//		proxy.deleteOperatingRange(1);
//		System.out.println("deleted");
		
//		proxy.updateOperatingRange(1);
//		System.out.println("modified");
		
		//String des=proxy.findOperatingRange(1);
		//System.out.println(des);
		
	}
}
