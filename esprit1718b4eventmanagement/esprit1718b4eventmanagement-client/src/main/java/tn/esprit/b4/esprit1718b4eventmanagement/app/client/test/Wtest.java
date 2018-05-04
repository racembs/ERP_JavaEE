package tn.esprit.b4.esprit1718b4eventmanagement.app.client.test;

import java.lang.reflect.Proxy;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tn.esprit.b4.esprit1718b4eventmanagement.app.client.controllers.LoginController;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Article;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Client;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Equipment;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.NeededItem;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.OperatingRange;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Nature;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Orders;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.User;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.UsualWork;
import tn.esprit.b4.esprit1718b4eventmanagement.entities.Works;
import tn.esprit.b4.esprit1718b4eventmanagement.services.AnalyseServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.ArticleServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.UserServiceRemote;
import tn.esprit.b4.esprit1718b4eventmanagement.services.WorksUsServiceRemote;

public class Wtest {	

	public static void main(String[] args) throws NamingException, ParseException {
    
    /*	WorksPK worksPK =new WorksPK();
    	worksPK.setIdUser(2);
    	worksPK.setIdEquipment(1);
    	*/
    	
  // 	uw.setWorksPK(worksPK);
		
		String ArticlejndiName = "esprit1718b4eventmanagement-ear/esprit1718b4eventmanagement-service/AnalyseService!tn.esprit.b4.esprit1718b4eventmanagement.services.AnalyseServiceRemote";
		Context context = new InitialContext();
		AnalyseServiceRemote ArticleProxy = (AnalyseServiceRemote) context.lookup(ArticlejndiName);
		long MTTR=0;
		
		  List<UsualWork> wr= ArticleProxy.ListWorks(1);
	Integer i1=0;
		
		ArrayList< Date > liststar = new ArrayList<>();
		ArrayList< Date > listend = new ArrayList<>();
		
		  for(UsualWork i: wr)
		  {
			  listend.add(i.getEndDate());
		
			 
		  i1=i1+1;
			 
			
			  
		  }
		  for(UsualWork i: wr)
		  {
			  liststar.add(i.getStartDate());
		
			 
		  i1=i1+1;
			 
			
			  
		  }
			if ((i1==0)||(i1==1)){
				System.out.println("Erreur");
				
			}
			else
			{
		  for(int j=0;j<listend.size();j++)
		  {
		  if(j==listend.size())
		  {
			 
		  System.out.println("tstt");
			  
		  }
		  else
		  { System.out.println(listend.get(j));
		  System.out.println(liststar.get(j+1));
		  MTTR=MTTR+ ArticleProxy.nbrDayDate(listend.get(j),liststar.get(j+1));
System.out.println(MTTR);	
		  }
		  }
		
			}
			
			
		
	
	
     // proxy.addWR(uw);
       // List<Works> list = proxy.displayWRB();
       // ObservableList<Works> items = FXCollections.observableArrayList(list);
      //  System.out.println(list.get(0).getDescription());
   //   User s=  userService.userbyfstlstname("k k");
   //   System.out.println("heloooooooooo"+s.getEmail());
	
				}}
