package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;

import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsService;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

public class Wtest {
	public static void main(String[] args) throws NamingException, ParseException {
    	Context context;
    	context = new InitialContext();
   	UserServiceRemote userService = (UserServiceRemote) context
			.lookup("esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/UserService!tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote");
   // 	User user = userService.findByLogin("sarra");
    	
 String jndiName="esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/WorksUsService!tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote";
    //	
    	WorksUsServiceRemote proxy=(WorksUsServiceRemote) context.lookup(jndiName);
    /*	WorksPK worksPK =new WorksPK();
    	worksPK.setIdUser(2);
    	worksPK.setIdEquipment(1);
    	*/
    	//UsualWork uw =new UsualWork();
       // List<UsualWork> list = proxy.displayWO();
       // System.out.println(list.get(0).getDescription()); 
  // 	uw.setWorksPK(worksPK);
    	//System.out.println(.getDescription());
    
     // proxy.addWR(uw);
       // List<Works> list = proxy.displayWRB();
       // ObservableList<Works> items = FXCollections.observableArrayList(list);
      //  System.out.println(list.get(0).getDescription());
   //   User s=  userService.userbyfstlstname("k k");
   //   System.out.println("heloooooooooo"+s.getEmail());
    	//WorksUsService ws=new WorksUsService();
    
 List<Object> l=proxy.Top5ProdTech();
    	 for (int i = 0; i < l.size(); i++) {
             System.out.println(((Object[]) l.get(i))[0]);     //account bean, actually this is in reverse order - so this is user bean
           System.out.println(((Object[]) l.get(i))[1]);     //user bean         & this account bean
         }
	}
}
