package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.ManufacturingOrder;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.WorksPK;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ManufacturingServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

public class Wtest {
	public static void main(String[] args) throws NamingException, ParseException {
    	Context context;
    	context = new InitialContext();
    	UserServiceRemote userService = (UserServiceRemote) context
				.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
    	User user = userService.findByLogin("sarra");
    	
    String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    	WorksPK worksPK =new WorksPK();
    	worksPK.setIdUser(1);
    	worksPK.setIdEquipment(1);
    	
    	UsualWork uw =new UsualWork();
    	/*UsualWork w=new UsualWork ("text", "text", "comboSpecialization.getValue().toString()",
    			 worksPK,user ,
    			eq,"ok", "En cours",new Date(),
    			 Nature.WorkRequest);*/
    	uw.setWorksPK(worksPK);
    	//System.out.println(.getDescription());
    
        proxy.addWR(uw);
	
			
	}
}
