package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.util.List;

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
		
		//Tool newTool = new Tool("desc1",100,"ref1","supp","code_supp1","brand1",(float) 20.3,"nature","family","available");
		//proxy.addTool(newTool);
		//System.out.println("tool created");
		
		//SpareParts newSpare = new SpareParts("mod",7,"refff","refff","code_supp1","brand1",(float) 30.74,"met","Sfamily1");
		//proxy2.addSpareParts(newSpare);
		//System.out.println("SparePart created");
		
		//SpareParts newSpare = proxy2.findSparePartsByRef("refff");
		//SpareParts newSpare = proxy2.findSparePartsById(6);
		//System.out.println("hola");
		//newSpare.setQuantity(newSpare.getQuantity()+1);
		//proxy2.updateSpareParts(newSpare);
		//System.out.println("SparePart updated");
		
		List<Tool> list = proxy.displayAll();
	       // ObservableList<Works> items = FXCollections.observableArrayList(list);
		  System.out.println("hola");
		  
		 for( int i=0 ; i < list.size() ; i++)
		 {
			 System.out.println(list.get(i).getDescription());
		 }
	        
		
	}
}
